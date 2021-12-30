pipeline {
  triggers {
    cron('0 0 * * *')
  }
  agent {
    node {
      label 'azure-server'
    }
  }
  stages {
    stage('Debug Info') {
      steps {
        sh 'whoami;hostname;uptime'
      }
    }

    stage('Build Docker Image') {
      steps {
        sh '''docker build . \\
-t tumbler-e2e-testing'''
      }
    }

    stage('Run Container') {
      steps {
        sh '''docker run \\
--name tumbler-e2e-testing \\
--entrypoint /bin/sh \\
-dt --rm tumbler-e2e-testing'''
      }
    }

    stage('Lint') {
      steps {
        sh 'docker exec tumbler-e2e-testing sh -c \'bash lint.sh\''
      }
    }

    stage('Test') {
      steps {
        sh 'docker exec tumbler-e2e-testing sh -c \'bash test.sh\''
      }
    }

    stage('E2E-Test') {
      steps {
        sh 'docker exec tumbler-e2e-testing sh -c \'bash test_e2e.sh\''
      }
    }

    stage('Publish the reports') {
      steps {
        sh 'sudo rm -rf /var/www/html/*'
        sh 'sudo docker cp tumbler-e2e-testing:/testing/index.html /var/www/html/'
        sh 'sudo docker cp tumbler-e2e-testing:/testing/reports/ /var/www/html/ || echo "Didn\'t find reports file!"'
      }
    }

    stage('Stop Container & Remove Image') {
      steps {
        sh 'docker container stop tumbler-e2e-testing'
        sh 'docker system prune -f'
        // sh 'docker image remove tumbler-e2e-testing'
      }
    }

    stage('List Docker Images & Containers') {
      steps {
        sh 'docker image ls -a'
        sh 'docker container ls -a'
      }
    }
  }

  post {
    always {
      discordSend(
        title: 'Frontend E2E Testing',
        link: env.BUILD_URL,
        description: "Testing Status: ${currentBuild.currentResult}",
        result: currentBuild.currentResult,
        thumbnail: 'https://i.dlpng.com/static/png/6378770_preview.png',
        webhookURL: 'https://discord.com/api/webhooks/921772869782994994/mi4skhArIoT6heXWebPiWLn6Xc95rZgUqtW7qriBOYvnl0sTdfn16we7yPY-n-DJYRmH'
    }
    unsuccessful {
      sh 'docker container stop tumbler-e2e-testing || true'
      sh 'docker image remove tumbler-e2e-testing || true'
      sh 'docker system prune -f'
    }

    cleanup {
      cleanWs()
    }
  }
}

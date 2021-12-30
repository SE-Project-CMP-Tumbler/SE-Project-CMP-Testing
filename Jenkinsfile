pipeline {
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
        // sh 'docker image remove tumbler-e2e-testing'
        // sh 'docker system prune -f'
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

pipeline {
  agent any
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

    stage('Stop Container & Remove Image') {
      steps {
        sh 'docker container stop tumbler-e2e-testing'
        sh 'docker image remove tumbler-e2e-testing'
        sh 'docker system prune -f'
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

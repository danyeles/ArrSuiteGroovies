pipeline {
    agent any

    stages {
        stage('whoami') {
            steps {
              script {
                whoami
              }
            }
        }
    }

    post {
        always {
            echo 'Pipeline execution completed.'
        }
    }
}

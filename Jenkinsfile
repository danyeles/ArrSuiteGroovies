pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Replace 'your-repo-url' with your actual GitHub repository URL
                git url: 'https://github.com/danyeles/ArrSuiteGroovies.git'
            }
        }

        stage('Process Job DSLs') {
            steps {
                // Process Job DSLs
                jobDsl targets: '**/*.groovy', 
                       removedJobAction: 'IGNORE', 
                       removedViewAction: 'IGNORE', 
                       removedConfigFilesAction: 'IGNORE', // Action for removed config files
                       lookupStrategy: 'JENKINS_ROOT'
            }
        }

        stage('whoami') {
            steps {
                whoami
            }
        }
    }

    post {
        always {
            echo 'Pipeline execution completed.'
        }
    }
}

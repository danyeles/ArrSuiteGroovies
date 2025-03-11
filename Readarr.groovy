pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/danyeles/ReadarrApp.git'
            }
        }
        stage('Generate Pipeline') {
            steps {
                script {
                    jobDsl scriptText: """
                    pipelineJob('DeployDockerPipeline') {
                        definition {
                            cpsScm {
                                scm {
                                    git {
                                        remote {
                                            url 'https://github.com/danyeles/ReadarrApp.git'
                                        }
                                        branches('master')
                                        scriptPath('Jenkinsfile')
                                    }
                                }
                            }
                        }
                    }
                    """
                }
            }
        }
    }
}

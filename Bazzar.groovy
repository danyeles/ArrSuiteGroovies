pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/danyeles/BazzarApp'
            }
        }
        stage('Generate Pipeline') {
            steps {
                script {
                    pipelineJob('DeployDockerPipeline') {
                        definition {
                            cpsScm {
                                scm {
                                    git {
                                        remote {
                                            url 'https://github.com/danyeles/BazzarApp'
                                        }
                                        branches('main')
                                        scriptPath('Jenkinsfile')
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

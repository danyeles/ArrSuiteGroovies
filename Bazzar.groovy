pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/your-seed-repo.git'
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
                                            url 'https://github.com/another-repo.git'
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

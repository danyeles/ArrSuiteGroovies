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
                    jobDsl scriptText: """
                    pipelineJob('Deploy BazArr Pipeline') {
                        definition {
                            cpsScm {
                                scm {
                                    git {
                                        remote {
                                            url 'https://github.com/danyeles/BazzarApp'
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

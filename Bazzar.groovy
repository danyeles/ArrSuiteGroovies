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
                    pipelineJob('NewGeneratedPipeline') {
                        definition {
                            cps {
                                script('''
pipeline {
    agent any
    stages {
        stage('Example Stage') {
            steps {
                echo 'This is a generated pipeline job!'
            }
        }
    }
}
                                ''')
                                sandbox()
                            }
                        }
                    }
                    """
                }
            }
        }
    }
}

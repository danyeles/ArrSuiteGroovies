pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/danyeles/ArrSuiteGroovies.git'
            }
        }
        stage('Generate Pipelines') {
            steps {
                script {
                    def files = findFiles(glob: '**/*.groovy')
                    files.each { file ->
                        def jobName = file.name.replace('.groovy', '')
                        def jobScript = readFile(file.path)
                        jobDsl scriptText: '''
                        pipelineJob('${jobName}') {
                            definition {
                                cps {
                                    script('${jobScript}')
                                    sandbox()
                                }
                            }
                        }
                        '''
                    }
                }
            }
        }
    }
}
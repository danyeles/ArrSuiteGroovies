pipelineJob('Deploy ReadArr Pipeline') {
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

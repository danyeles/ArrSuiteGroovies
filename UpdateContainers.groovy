pipelineJob('Update containers Pipeline') {
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url 'https://github.com/danyeles/UpdateContainers.git'
                    }
                    branches('master')
                    scriptPath('Jenkinsfile')
                }
            }
        }
    }
}

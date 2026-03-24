pipelineJob('Deploy Seerr Pipeline') {
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url 'git@github.com:danyeles/SeerrApp.git'
                    }
                    branches('master')
                    scriptPath('Jenkinsfile')
                }
            }
        }
    }
}

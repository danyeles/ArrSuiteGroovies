pipelineJob('Deploy Jellyseerr Pipeline') {
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url 'git@github.com:danyeles/JellyseerrApp.git'
                    }
                    branches('master')
                    scriptPath('Jenkinsfile')
                }
            }
        }
    }
}

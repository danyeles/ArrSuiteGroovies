pipelineJob('Deploy FlareSolverrApp Pipeline') {
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url 'git@github.com:danyeles/FlareSolverrApp.git'
                    }
                    branches('master')
                    scriptPath('Jenkinsfile')
                }
            }
        }
    }
}

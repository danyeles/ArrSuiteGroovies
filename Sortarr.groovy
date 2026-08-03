pipelineJob('Deploy Sortarr Pipeline') {
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url 'https://github.com/danyeles/SortArrApp.git'
                    }
                    branches('master')
                    scriptPath('Jenkinsfile')
                }
            }
        }
    }
}

pipelineJob('Media Stack Disaster Recovery') {

    description('Full recovery backup for Arr Suite, Jellyfin, Jenkins and Docker host')

    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url 'https://github.com/danyeles/BackupSuiteApp.git'
                    }

                    branches('master')
                    scriptPath('Jenkinsfile')
                }
            }
        }
    }
}

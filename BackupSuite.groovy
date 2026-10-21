pipelineJob('Backup Arr Suite') {
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

    description('Creates backups of Arr applications, Jellyfin, Jenkins and Docker container definitions.')
}

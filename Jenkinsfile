pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git 'git@github.com:danyeles/ArrSuiteGroovies.git'
            }
        }
        stage('Generate Pipelines') {
            steps {
                script {
                    def files = findFiles(glob: '**/*.groovy')
                    files.each { file ->
                        load file.path
                    }
                }
            }
        }
    }
}

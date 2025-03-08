pipeline {
    agent any

    environment {
        JENKINS_URL = 'http://192.168.100.60:8181'
        JENKINS_CREDENTIALS = credentials('jenkins-credentials-id')
    }

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
                        def jobScript = readFile(file.path).replace('$', '\\$')

                        def xmlConfig = """
                        <flow-definition plugin="workflow-job">
                          <description></description>
                          <keepDependencies>false</keepDependencies>
                          <properties>
                            <org.jenkinsci.plugins.workflow.job.properties.PipelineTriggersJobProperty>
                              <triggers/>
                            </org.jenkinsci.plugins.workflow.job.properties.PipelineTriggersJobProperty>
                          </properties>
                          <definition class="org.jenkinsci.plugins.workflow.cps.CpsFlowDefinition" plugin="workflow-cps">
                            <script>${jobScript}</script>
                            <sandbox>true</sandbox>
                          </definition>
                          <disabled>false</disabled>
                        </flow-definition>
                        """

                        sh """
                        curl -X POST '${JENKINS_URL}/createItem?name=${jobName}' \
                        --header 'Content-Type: application/json' \
                        --user ${JENKINS_CREDENTIALS} \
                        --data-binary '${xmlConfig}'
                        """
                    }
                }
            }
        }
    }
}

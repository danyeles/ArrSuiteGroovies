pipeline {
    agent any

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
                        def jobScript = readFile(file.path)

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

                        def response = httpRequest(
                            url: "http://192.168.100.60:8181/createItem?name=${jobName}",
                            httpMode: 'POST',
                            contentType: 'APPLICATION_XML',
                            acceptType: 'APPLICATION_XML',
                            requestBody: xmlConfig,
                            customHeaders: [[name: 'Authorization', value: "Basic ${credentials('jenkins-credentials-id')}"]]
                        )

                        if (response.status != 200) {
                            error "Failed to create job ${jobName}: ${response}"
                        }
                    }
                }
            }
        }
    }
}

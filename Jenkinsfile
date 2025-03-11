pipeline {
    agent any

    environment {
        JENKINS_URL = 'http://192.168.100.60:8181'
        JENKINS_USER = 'danyeles' // Replace with your Jenkins username
        JENKINS_TOKEN = credentials('jenkins-api-token-id')
    }

    stages {
        stage('Generate Test Job') {
            steps {
                script {
                    def jobName = "TestJob"

                    // Obtain a crumb
                    def crumbResponse = sh(script: "curl -s -u ${JENKINS_USER}:${JENKINS_TOKEN} -X GET ${JENKINS_URL}/crumbIssuer/api/json", returnStdout: true).trim()
                    def crumbJson = readJSON(text: crumbResponse)
                    def crumb = crumbJson.crumb
                    def crumbRequestField = crumbJson.crumbRequestField

                    // Minimal valid XML configuration
                    def xmlConfig = """
                    <flow-definition plugin="workflow-job">
                      <description>Test Job</description>
                      <keepDependencies>false</keepDependencies>
                      <properties/>
                      <definition class="org.jenkinsci.plugins.workflow.cps.CpsFlowDefinition" plugin="workflow-cps">
                        <script>echo 'Hello, World!'</script>
                        <sandbox>true</sandbox>
                      </definition>
                      <disabled>false</disabled>
                    </flow-definition>
                    """

                    // Send POST request to create minimal pipeline job
                    def createJobResponse = sh(script: """
                    curl -X POST '${JENKINS_URL}/createItem?name=${jobName}' \
                    --header 'Content-Type: application/xml' \
                    --header '${crumbRequestField}: ${crumb}' \
                    --user ${JENKINS_USER}:${JENKINS_TOKEN} \
                    --data-binary @- <<DATA
${xmlConfig}
DATA
                    """, returnStdout: true).trim()

                    echo "Create Job Response: ${createJobResponse}"
                }
            }
        }
    }
}

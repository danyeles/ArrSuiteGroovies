import groovy.json.JsonSlurper

def jenkinsUrl = 'http://192.168.100.60:8181'
def jenkinsUser = 'danyeles' // Replace with your Jenkins username
def jenkinsToken = 'jenkins-api-token-id' // Replace with your Jenkins API token
def jobName = 'DeployDockerPipeline'
def jobScript = '''
pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/name/BazzarApp'
            }
        }
        stage('Generate Pipeline') {
            steps {
                script {
                    pipelineJob('DeployDockerPipeline') {
                        definition {
                            cpsScm {
                                scm {
                                    git {
                                        remote {
                                            url 'https://github.com/name/BazzarApp'
                                        }
                                        branches('master')
                                        scriptPath('Jenkinsfile')
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
'''.trim()

// Obtain crumb
def crumbResponse = new URL("${jenkinsUrl}/crumbIssuer/api/json").getText(requestProperties: ['Authorization': "Basic " + "${jenkinsUser}:${jenkinsToken}".bytes.encodeBase64().toString()])
def crumbJson = new JsonSlurper().parseText(crumbResponse)
def crumb = crumbJson.crumb
def crumbRequestField = crumbJson.crumbRequestField

// Generate XML configuration
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

// Create pipeline job
def createJobResponse = new URL("${jenkinsUrl}/createItem?name=${jobName}").getText(requestProperties: [
    'Content-Type': 'application/xml',
    (crumbRequestField): crumb,
    'Authorization': "Basic " + "${jenkinsUser}:${jenkinsToken}".bytes.encodeBase64().toString(),
    'Method': 'POST'
], postData: xmlConfig.bytes)

println(createJobResponse)

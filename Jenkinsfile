pipeline {
    agent any

    environment {
        JENKINS_URL = 'http://192.168.100.60:8181'
        JENKINS_USER = 'danyeles' // Replace with your Jenkins username
        JENKINS_TOKEN = credentials('jenkins-api-token-id')
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
                        def jobScript = URLEncoder.encode(readFile(file.path).trim(), "UTF-8")

                        // Debugging: Print the job script
                        echo "Job Script: ${jobScript}"

                        // Obtain a crumb
                        echo "Obtaining Crumb..."
                        def crumbResponse = sh(script: "curl -s -u ${JENKINS_USER}:${JENKINS_TOKEN} -X GET ${JENKINS_URL}/crumbIssuer/api/json", returnStdout: true).trim()

                        echo "LLEGA AQUI 1"
                        echo "Crumb Response : ${crumbResponse}"

                        echo "LLEGA AQUI 2"

                        if (crumbResponse.contains('<html>')) {
                            error "Invalid Crumb Response: ${crumbResponse}"
                        }

                        echo "LLEGA AQUI 3"

                        def crumbJson = readJSON(text: crumbResponse)
                        def crumb = crumbJson.crumb
                        def crumbRequestField = crumbJson.crumbRequestField

                        echo "LLEGA AQUI 4"

                        // Generate XML configuration
                        def xmlConfig = URLEncoder.encode("""
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
                        """, "UTF-8")

                        // Debugging: Print the xmlConfig
                        echo "XML Config: ${xmlConfig}"

                        // Send POST request to create pipeline job and capture server response
                        def createJobResponse = sh(script: """
                        curl -v -X POST '${JENKINS_URL}/createItem?name=${jobName}' \
                        --header 'Content-Type: application/xml' \
                        --header '${crumbRequestField}: ${crumb}' \
                        --user ${JENKINS_USER}:${JENKINS_TOKEN} \
                        --data-urlencode '${xmlConfig}'
                        """, returnStdout: true, returnStatus: false)

                        echo "Create Job Response: ${createJobResponse}"

                        if (createJobResponse.contains("Error")) {
                            error "Failed to create job: ${createJobResponse}"
                        } else {
                            echo "Job created successfully: ${jobName}"
                        }
                    }
                }
            }
        }
                stage('List All Jobs') {
            steps {
                script {
                    def listJobsResponse = sh(script: """
                    curl -s -u ${JENKINS_USER}:${JENKINS_TOKEN} -X GET ${JENKINS_URL}/api/json?tree=jobs[name] \
                    """, returnStdout: true).trim()
                    
                    echo "List Jobs Response: ${listJobsResponse}"
                }
            }
        }
    }
}

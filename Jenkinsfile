pipeline {
    agent any

    environment {
        JENKINS_URL = 'http://192.168.100.60:8181'
        JENKINS_USER = 'danyeles' // Replace with your Jenkins username
        JENKINS_TOKEN = credentials('jenkins-api-token-id')
        REPO_URL = 'https://github.com/danyeles/ArrSuiteGroovies.git' // Replace with your repository URL
    }

     stages {
        stage('Checkout') {
            steps {
                git url: "${REPO_URL}"
            }
        }
        stage('Generate Pipelines') {
            steps {
                script {
                    def files = findFiles(glob: '**/*.groovy')
                    files.each { file ->
                        def jobName = file.name.replace('.groovy', '')
                        def jobScript = readFile(file.path).trim()
                        def scmUrl = "${REPO_URL}"
                        def branch = 'master' // Adjust the branch as necessary
                        def scriptPath = file.path

                        // Debugging: Print the job script
                        echo "Job Script: ${jobScript}"

                        // Obtain a crumb
                        def crumbResponse = sh(script: "curl -s -u ${JENKINS_USER}:${JENKINS_TOKEN} -X GET ${JENKINS_URL}/crumbIssuer/api/json", returnStdout: true).trim()
                        def crumbJson = readJSON(text: crumbResponse)
                        def crumb = crumbJson.crumb
                        def crumbRequestField = crumbJson.crumbRequestField

                        // Generate XML configuration
                        def xmlConfig = """
                        <flow-definition plugin="workflow-job">
                          <description>Generated from Groovy script</description>
                          <keepDependencies>false</keepDependencies>
                          <properties/>
                          <definition class="org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition" plugin="workflow-cps">
                            <scm class="hudson.plugins.git.GitSCM" plugin="git">
                              <configVersion>2</configVersion>
                              <userRemoteConfigs>
                                <hudson.plugins.git.UserRemoteConfig>
                                  <url>${scmUrl}</url>
                                </hudson.plugins.git.UserRemoteConfig>
                              </userRemoteConfigs>
                              <branches>
                                <hudson.plugins.git.BranchSpec>
                                  <name>${branch}</name>
                                </hudson.plugins.git.BranchSpec>
                              </branches>
                            </scm>
                            <scriptPath>${scriptPath}</scriptPath>
                            <lightweight>true</lightweight>
                          </definition>
                          <disabled>false</disabled>
                        </flow-definition>
                        """

                        // Send POST request to create pipeline job
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
}

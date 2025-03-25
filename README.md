# ArrSuiteGroovies

1. Install Java on Ubuntu
    * sudo apt update
    * sudo apt install fontconfig openjdk-17-jre
    * java -version

5. Install Jenkins on you machine
    * sudo wget -O /usr/share/keyrings/jenkins-keyring.asc \
        https://pkg.jenkins.io/debian-stable/jenkins.io-2023.key
    * echo "deb [signed-by=/usr/share/keyrings/jenkins-keyring.asc]" \
        https://pkg.jenkins.io/debian-stable binary/ | sudo tee \
        /etc/apt/sources.list.d/jenkins.list > /dev/null
    * sudo apt-get update
    * sudo apt-get install jenkins
  
6. Configure Jenkins http://LOCAL-IPADDRESS:8080/
    * Install Job DSL Plugin
        * Go to Manage Jenkins → Manage Plugins.
        * Search for the Job DSL plugin in the "Available" tab.
        * Install the plugin and restart Jenkins if prompted.
    * Create SeedJob
        * Click New Item
        * Add name (example: ArrSeedJob)
        * Select Pipeline
        * Click ok
        * In Configuration, go to Pipeline
        * Select Pipeline script with SCM
        * Select SCM: Git
        * Add repo: https://github.com/danyeles/ArrSuiteGroovies.git
        * Save


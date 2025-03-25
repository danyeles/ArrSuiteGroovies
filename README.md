# ArrSuiteGroovies

1. Install Java on Ubuntu
  a. sudo apt update
  b. sudo apt install fontconfig openjdk-17-jre
  c. java -version

2. Install Jenkins on you machine
  a. sudo wget -O /usr/share/keyrings/jenkins-keyring.asc \
    https://pkg.jenkins.io/debian-stable/jenkins.io-2023.key
  b. echo "deb [signed-by=/usr/share/keyrings/jenkins-keyring.asc]" \
    https://pkg.jenkins.io/debian-stable binary/ | sudo tee \
    /etc/apt/sources.list.d/jenkins.list > /dev/null
  c. sudo apt-get update
  d. sudo apt-get install jenkins
3. Configure Jenkins http://LOCAL-IPADDRESS:8080/
  a. Install Job DSL Plugin
    1. Go to Manage Jenkins → Manage Plugins.
    2. Search for the Job DSL plugin in the "Available" tab.
    3. Install the plugin and restart Jenkins if prompted.
  b. Create SeedJob
    1. Click New Item
    2. Add name (example: ArrSeedJob)
    3. Select Pipeline
    4. Click ok
    5. In Configuration, go to Pipeline
    6. Select Pipeline script with SCM
    7. Select SCM: Git
    8. Add repo: https://github.com/danyeles/ArrSuiteGroovies.git
    9. Save


# ArrSuiteGroovies

1. Install Java on Ubuntu
    * sudo apt update
    * sudo apt install fontconfig openjdk-17-jre
    * java -version

2. Install Jenkins on you machine
    * sudo wget -O /usr/share/keyrings/jenkins-keyring.asc \
        https://pkg.jenkins.io/debian-stable/jenkins.io-2023.key
    * echo "deb [signed-by=/usr/share/keyrings/jenkins-keyring.asc]" \
        https://pkg.jenkins.io/debian-stable binary/ | sudo tee \
        /etc/apt/sources.list.d/jenkins.list > /dev/null
    * sudo apt-get update
    * sudo apt-get install jenkins
  
3. Configure Jenkins http://LOCAL-IPADDRESS:8080/
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

4. Install docker.io and run the below commands to give Jenkins permissions to run docker
   * sudo apt install docker.io
   * sudo usermod -aG docker jenkins
   * sudo systemctl restart jenkins

5. Run the SeedJob (On the first run, most probably will fail due to the scripts needing approval)
   * Go to Manage Jenkins
   * In Process Script Approval
   * Approve everything
   * Run Job again

6. The seedjob will create jobs to deploy the Arr Suite
   
7. Run the jobs for the applicatins you need.
   * QBittorrent and Prowlar will most probably be mandatory, as one is for downloads and other for the torrent indexers.
   * Please find some documentation
   * https://www.rapidseedbox.com/blog/ultimate-guide-to-sonarr
   * https://www.simplehomelab.com/install-docker-on-ubuntu-22-04/
   * https://www.rapidseedbox.com/blog/prowlarr-guide
   * https://help.rapidseedbox.com/en/articles/8380935-quick-sonarr-radarr-prowlarr-bazarr-and-deluge-setup

8. Sometimes permissions in folders might appear like this:
   * Folder '/folder/' is not writable by user 'abc'
   * The problem is for permmissions as it usually has drwxr-xr-x and it needs drwxrwxrwx
   * sudo chmod 777 FOLDERS

9. Open the deployed apps
* Qbittorrent:  http://LOCALIP:8787/
* Radarr:       http://LOCALIP:7878/
* Sonarr:       http://LOCALIP:8989/
* Lidarr:       http://LOCALIP:8686/
* Prowlarr:     http://LOCALIP:9696/
* Bazarr:       http://LOCALIP:6767/

10. These Pipeline jobs only work to deploy the apps, version update, stop, restart and other activities are in TODO; work thru the console in your system for any maintenance.

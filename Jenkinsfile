node {
 def mvnHome
 def app 
    
    
 stage('Download Artifacts') {
       sh ' ansible-playbook -i /home/jenkins/ansiserver /home/jenkins/download_artifacts.yaml'
  }
 
stage('Stop Docker Container') {
       sh 'sudo ansible-playbook -i /home/jenkins/ansiserver /home/jenkins/stop_docker_container.yaml'
}
 
 
 stage('Remove Docker Container') {
     sh 'sudo ansible-playbook -i /home/jenkins/ansiserver /home/jenkins/remove_docker_container.yaml'
 }
 
  stage('Remove Docker Images') {
     sh 'sudo ansible-playbook -i /home/jenkins/ansiserver /home/jenkins/remove_docker_images.yaml'
 }
 
 stage('Build Docker Images') {
       sh 'sudo ansible-playbook -i /home/jenkins/ansiserver /home/jenkins/build_docker_images.yaml'
 }
    
 stage('Run Docker Container') {
       sh 'sudo ansible-playbook -i /home/jenkins/ansiserver /home/jenkins/run_docker_container.yaml'
 }
 
 stage('Send email') {
    def mailRecipients = "raja.jaganathan@wipro.com"
    def jobName = currentBuild.fullDisplayName
    
    def mailCC ="cc:raghavendran.sethumadhavan1@wipro.com"
    def mailCC1 ="cc:avinash.patel@wipro.com"
    def mailCC2 ="cc:prakash.ramamurthy@wipro.com"
    
    emailext attachLog: true, body: "${currentBuild.currentResult}: ${BUILD_URL}",
        mimeType: 'text/html',
        subject: "RA312845/DevOpsProfessional/Batch9/Capstone/Pipeline_Docker/StockTradingApplication - ${currentBuild.currentResult}",
         to: "${mailRecipients} ${mailCC} ${mailCC1} ${mailCC2}",
        recipientProviders: [[$class: 'DevelopersRecipientProvider']]
        
     }
    

}

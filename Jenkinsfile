pipeline {
agent any
 
tools{
maven 'Maven'
jdk 'Java 8'
}
 
stages {
stage ("initialize") {
steps {
 echo "Hello Rama"
 
  
}
}

stage('Checkout') {
            steps {
               
   
        
        checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: '8bf65c5c-06bf-454c-9b6b-c3c180230524', 
                 url: 'https://github.com/logarajin/StockTradingApp.git']]])
				 
				 
				 
            
                 
            }
        }

   stage('Compile-Package'){
       steps{
        
           sh "mvn -Dmaven.test.failure.ignore clean package"

       }

     }


}
}

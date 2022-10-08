pipeline {
agent any
 
tools{
maven 'Maven'
jdk    'Java 8'
}
 
stages {
stage ("initialize") {
steps {
 echo "Hello Rama"
 
  
}
}

stage('Checkout') {
            steps {
               
				 
            checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: 'f17ff04e-6ef4-4d3f-8ed3-0b848eae0533',
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

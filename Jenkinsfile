node {
 def mvnHome
 def app 
 
   stage('SCM Checkout'){
     git 'https://github.com/logarajin/StockTradingApp'
   }
   stage('Compile-Package'){
      // Get maven home path
    //  def mvnHome =  tool name: 'maven-3', type: 'maven'   
      sh "${mvnHome}/bin/mvn package"
   }
    
    

}

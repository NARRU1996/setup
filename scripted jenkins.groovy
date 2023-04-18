node{
    
    stage ('git chekout'){
        git 'https://github.com/NARRU1996/JDPdevops1.git'
    }
    stage('check docker is runnig'){
    
    def dockerrun = sh script : "sudo systemctl status docker", returnStatus:true
    if (dockerrun == 0){
        sh "echo 'skipping step' "
        } else {
        sh "sudo systemctl start docker "
        }
    }
        
     stage ('Docker image building'){
       sh ' docker build -t narru96/$JOB_NAME:V1.$JOB_ID . '
       sh ' docker image tag narru96/$JOB_NAME:V1.$JOB_ID narru96/$JOB_NAME:latest '
     
    }
    stage ('pushing image to dockerhub'){
        environment {
        /* getting from globel credentials in manage credentials the id of docker hub
        token is taken in the () */
        
        DOCKERHUB_CREDENTIALS =credentials('dockerhubpass')
       }
          /* 
          passing docker credetials throug echo
          */
          sh "echo 'i am dockerhub :$env.DOCKERHUB_CREDENTIALS"
          sh "$DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin "
        //  sh "docker login -u narru96 -p ${dockerpass} "
          sh ' docker image push narru96/$JOB_NAME:V1.$JOB_ID . '
          sh ' docker image push narru96/$JOB_NAME:latest '
          echo "deleting docker images "
          sh "docker rmi -f narru96/$JOB_NAME:V1.$JOB_ID  narru96/$JOB_NAME:latest"
    }
    
    
}
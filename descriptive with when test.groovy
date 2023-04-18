pipeline {
 agent any
 environment{
    docker_cred = credentials('dockerhubpass')
    dockerrun = sh(script : '''
                    echo "$(systemctl status docker)" | awk '/Active:/ {print $2}' | tr -d "[:space:]"
                    ''', returnStdout: true).trim()

     }
        stages{
            stage('git chekout'){
                steps {
                     git 'https://github.com/NARRU1996/JDPdevops1.git'
                }
            }
            stage('cheking docker is running'){
                when {
                expression { return env.dockerrun == "inactive" }
                }
                steps {
                        sh "sudo systemctl start docker "
                    }
            }
            stage('building images'){
                steps {

                    sh ' docker build -t narru96/$JOB_NAME:V1.$BUILD_NUMBER . '
                    sh ' docker image tag narru96/$JOB_NAME:V1.$BUILD_NUMBER narru96/$JOB_NAME:latest '
                }
            }
            stage('pushing images'){
                steps {

                    sh "docker login -u $docker_cred_USR -p $docker_cred_PSW"
                    // sh "docker login -u narru96 -p ${dockerpass} "
                    sh 'docker image push narru96/$JOB_NAME:V1.$BUILD_NUMBER'
                    sh 'docker image push narru96/$JOB_NAME:latest'
                    echo "deleting docker images "
                    sh "docker rmi -f narru96/$JOB_NAME:V1.$BUILD_NUMBER  narru96/$JOB_NAME:latest"
                }
            }
            stage('print var'){
                steps {

                    sh "echo '$docker_cred_USR , $docker_cred_PSW'"
                }
            } 

        } 
}


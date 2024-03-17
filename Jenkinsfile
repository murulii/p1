pipeline {
    agent any
    tools{
        maven 'maven'
        jdk 'jdk'
    }
environment {
         SCANNER_HOME= tool 'sonar-scanner'
       }


    stages {
       
        
        stage("Cleanup Workspace"){
                steps {
                cleanWs()
                }
        }
        
        stage("Checkout from SCM"){
                steps {
                    git branch: 'checkout', url: 'https://github.com/murulii/p1.git'
                }
        }

        stage("Build Application"){
            steps {
                sh "mvn clean package"
            }

       }

       stage("Test Application"){
           steps {
                 sh "mvn test"
           }
       }
      stage('SonarQube Analsyis') {
            steps {
                withSonarQubeEnv('sonar-server') {
                    sh ''' $SCANNER_HOME/bin/sonar-scanner -Dsonar.projectName=myproject -Dsonar.projectKey=myproject \
                            -Dsonar.java.binaries=. '''
                }
            }
        }
        
        stage('Quality Gate') {
            steps {
                script {
                 

                    timeout(time: 1, unit: 'HOURS') {
                    def qg = waitForQualityGate()
                    if (qg.status != 'OK') {
                        error "Pipeline aborted due to quality gate failure: ${qg.status}"
                    }
                }
                }
            }
        }
        
        
        
    }
}

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

        stage('File System Scan') {
            steps {
                sh "trivy fs ."
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

        stage('Build') {
            steps {
               sh "mvn package"
            }
        }

        stage('Push') {
            steps {
               withMaven(globalMavenSettingsConfig: 'maven-config-global', jdk: 'jdk', maven: 'maven', mavenSettingsConfig: '', traceability: true) {
                // some block
                sh 'mvn deploy'
            }    
            }
        }



        stage('Docker Build') {
            steps {
               sh "docker build -t murulii/tomcat:v11 ."
               sh "docker run -it -p 8089:8080 -d murulii/tomcat:v1"
               sh "docker ps"
            }
        }
        
    }
}

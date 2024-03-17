pipeline {
    agent any
    tools{
        maven 'maven'
        jdk 'jdk'
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
                withSonarQubeEnv('sonar') {
                    sh ''' $SCANNER_HOME/bin/sonar-scanner -Dsonar.projectName=BoardGame -Dsonar.projectKey=BoardGame \
                            -Dsonar.java.binaries=. '''
                }
            }
        }
        
        
        
        
        
    }
}

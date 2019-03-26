pipeline {
    agent any
    environment {
        dockerImage = ''
    }
    tools { 
        maven 'Maven 3.6.0' 
        jdk 'jdk8' 
    }
    stages {
    	stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                ''' 
            }
        }
        stage('Build') {
            steps {
            	sh 'apt update && apt install maven'
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Build image') {
            steps {
                script {
                    dockerImage = docker.build("childzu/myrepo:pm25-v3")
                }
            }
        }
        stage('Push image') {
            steps {
                script {
                    withDockerRegistry(
                        credentialsId: 'docker-credential',
                        url: 'https://index.docker.io/v1/') {
                        dockerImage.push()
                    }
                }
            }
        }
        stage('Deployment') {
            steps {
                sh 'kubectl apply -f deployment.yaml';
            }
        }
    }
}
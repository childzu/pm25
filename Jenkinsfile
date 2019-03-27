pipeline {
    agent any
    tools{
        maven "mvn"
        jdk 'jdk8'
    }
    options {
        skipStagesAfterUnstable()
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
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Build image') {
        	agent { dockerfile true }
            steps {
                sh 'docker build -t childzu/myrepo:pm25 .'
            }
        }
        stage('Push image') {
        	agent { dockerfile true }
            steps {
                sh '''
                    docker tag childzu/myrepo:pm25 childzu/myrepo:pm25-v3
                    docker push childzu/myrepo:pm25-v3
                '''
            }
        }
        stage('Deployment') {
            steps {
                sh 'kubectl apply -f deployment.yaml';
            }
        }
    }
}

def project ='parabolic-env-235306'
def appName ='pm25'
def feSvcName ="${appName}-svc"
def repoLocation = "gcr.io"
def imageTag ="${repoLocation}/${project}/${appName}:${env.BRANCH_NAME}.${env.BUILD_NUMBER}"
def dockerhubimage ="childzu/myrepo:${appName}.${env.BRANCH_NAME}.${env.BUILD_NUMBER}"

pipeline {
    agent {
    kubernetes {
      label 'sample-app'
      defaultContainer 'jnlp'
      yaml """
apiVersion: v1
kind: Pod
metadata:
labels:
  component: ci
spec:
  # Use service account that can deploy to all namespaces
  serviceAccountName: cd-jenkins
  containers:
  - name: maven
    image: maven:3-alpine
    command:
    - cat
    tty: true
  - name: gcloud
    image: gcr.io/cloud-builders/gcloud
    command:
    - cat
    tty: true
  - name: docker
    image: gcr.io/cloud-builders/docker
    securityContext:
      privileged: true
    volumeMounts:
    - mountPath: /var/run/docker.sock
      name: docker-sock-volume
    command:
    - cat
    tty: true
  - name: kubectl
    image: gcr.io/cloud-builders/kubectl
    command:
    - cat
    tty: true
  volumes:
  - name: docker-sock-volume
    hostPath:
      # location on host
      path: /var/run/docker.sock
      # this field is optional
      type: File
"""
}
 }
  stages {
    stage('Build package') {
      steps {
        container('maven') {
          sh """
            mvn -B -DskipTests clean package
          """
        }
      }
    }
    stage('Test package') {
      steps {
        container('maven') {
          sh """
            mvn test
          """
        }
      }
    }
    stage('Build and push image') {
      steps {
        container('gcloud') {
          sh """
            echo ${imageTag}
            sed -i 's#IMAGE_TAG#${imageTag}#' cloudbuild.yaml
            gcloud builds submit --config cloudbuild.yaml .
          """
        }
      }
    }
    // stage('Build and push image with Container Builder') {
    //   steps {
    //     container('docker') {
    //       sh"""
    //         docker build -t ${imageTag} .
    //         sed -i 's#CONTAINER_IMAGE_TAG#${imageTag}#' deployment.yaml
    //         docker tag ${imageTag} ${imageTag}
    //         docker login --username childzu --password poom25240103
    //         docker push ${imageTag}
    //       """
    //     }
    //   }
    // }
    stage('Deploy Canary') {
      when { branch 'canary' }
      steps {
        container('kubectl') {
          sh("sed -i 's#CONTAINER_IMAGE_TAG#${imageTag}#' deployment.yaml")
          sh("kubectl --namespace=default apply -f deployment.yaml")
          sh("echo http://`kubectl --namespace=default get service/${feSvcName} -o jsonpath='{.status.loadBalancer.ingress[0].ip}'` > ${feSvcName}")
        } 
      }
    }
    stage('Deploy Production') {
      when { branch 'master' }
      steps{
        container('kubectl') {
          sh("sed -i 's#CONTAINER_IMAGE_TAG#${imageTag}#' deployment.yaml")
          sh("kubectl --namespace=default apply -f deployment.yaml")
          sh("echo http://`kubectl --namespace=default get service/${feSvcName} -o jsonpath='{.status.loadBalancer.ingress[0].ip}'` > ${feSvcName}")
        }
      }
    }
    stage('Deploy Dev') {
      when { 
        not { branch 'master' } 
        not { branch 'canary' }
      } 
      steps {
        container('kubectl') {
          sh("sed -i 's#CONTAINER_IMAGE_TAG#${imageTag}#' deployment.yaml")
          sh("kubectl --namespace=default apply -f deployment.yaml")
          sh("echo http://`kubectl --namespace=default get service/${feSvcName} -o jsonpath='{.status.loadBalancer.ingress[0].ip}'` > ${feSvcName}")
        }
      }     
    }
  }
}
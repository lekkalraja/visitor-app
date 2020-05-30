pipeline {

    environment {
    registry1 = "rlekkalaa/visitor-app"
    registryCredential = 'dockerhub'
  }
    agent any

    stages {
        stage('Checkout Source') {
            steps {
                echo 'checking out the source..'
                git url: 'https://github.com/lekkalraja/visitor-app.git', branch: 'master', credentialsId: 'github-checkout'
            }
        }
        stage('Build Image') {
            steps {
                echo 'Building Image..'
                sh '''
                       docker build -t visitor-app .
      '''
            }
        }
        stage('Push Image') {
            steps {
                echo 'Pushing Image...'
                sh '''
                docker tag visitor-app:latest registry1:$BUILD_NUMBER
                docker push registry1:$BUILD_NUMBER
                '''
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
                script {
          kubernetesDeploy(configs: "visitor-app-deployment.yml", kubeconfigId: "kubeconfig")
        }
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                sh '''
                   ./test.sh
                '''
            }
        }
    }

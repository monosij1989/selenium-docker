pipeline {
    agent any
    stages {
        stage('Build Jars') {
            steps {
                bat "mvn clean package" 
            }
        }
        stage('Build Docker Image') {
            steps {
                bat "docker build -t=monosijbiswas/selenium-docker:latest ." 
            }
        }
        stage('Push Docker Image to Docker Hub') {
            steps {
                bat "docker push monosijbiswas/selenium-docker:latest" 
            }
        }
    }
}
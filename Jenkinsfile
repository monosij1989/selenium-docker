pipeline {
    agent any
    stages {
        stage('Build Jars') {
            steps {
                bat "mvn clean package -DskipTests" 
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    app = docker.build("monosijbiswas/selenium-docker")
                } 
            }
        }
        stage('Push Docker Image to Docker Hub') {
            environment {
                DOCKER_HOST = 'npipe:////./pipe/docker_engine'
            }
			steps {
                script {
                    docker.withRegistry('','docker-hub') {
                        app.push("latest")
                    }
                }
            }
        }
    }
}
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
                bat "docker build -t=monosijbiswas/selenium-docker:latest ." 
            }
        }
        stage('Push Docker Image to Docker Hub') {
			environment {
				DOCKER_HUB = credentials('docker-hub')
			}
            steps {
				bat 'docker login -u %DOCKER_HUB_USR% -p %DOCKER_HUB_PSW%'
                bat "docker push monosijbiswas/selenium-docker:latest"
                bat "docker tag monosijbiswas/selenium-docker:latest monosijbiswas/selenium-docker:${env.BUILD_NUMBER}"
                bat "docker push monosijbiswas/selenium-docker:${env.BUILD_NUMBER}" 
            }
        }
    }
    post {
		always {
			bat "docker logout"
		}
	}
}
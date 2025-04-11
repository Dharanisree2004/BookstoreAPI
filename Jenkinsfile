pipeline {
    agent any

    environment {
        IMAGE_NAME = 'bookstore-api'
    }

    stages {
        stage('Clone Repo') { 
            steps {
                git branch: 'main', url: 'https://github.com/Dharanisree2004/BookstoreAPI.git'
            }
        }


        stage('Build App') {
            steps {
                sh './mvnw clean package -DskipTests || mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $IMAGE_NAME .'
            }
        }

        stage('Run Docker Container') {
            steps {
                sh 'docker run --rm $IMAGE_NAME'
            }
        }
    }
}

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
                dir('Book-Store') {
                    sh './mvnw clean package -DskipTests'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                dir('Book-Store') {
                    sh 'docker build -t $IMAGE_NAME .'
                }
            }
        }

        stage('Run Docker Container') {
            steps {
                dir('Book-Store') {
                    sh 'docker run --rm $IMAGE_NAME'
                }
            }
        }
    }
}

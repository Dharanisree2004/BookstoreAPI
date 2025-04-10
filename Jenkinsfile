pipeline {
    agent any

    stages {
        stage('Clone') {
            steps {
                git 'https://github.com/Dharanisree2004/BookstoreAPI.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
    }
}

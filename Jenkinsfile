pipeline {
    agent any
    stages {
        stage('Checkout with Your Branch') {
            steps {
                script {
                    // Replace 'your-branch' with the name of your branch
                    checkout([$class: 'GitSCM', branches: [[name: '*/main']], userRemoteConfigs: [[url: 'https://github.com/mirian228/Project2']]])
                }
            }
        }
        stage('Run Maven Project') {
            steps {
                script {
                    sh 'mvn clean test'
                }
            }
        }
        stage('Get Maven Version') {
            steps {
                script {
                    sh 'mvn --version'
                }
            }
        }
    }
}

pipeline {
    agent any
    stages {
        stage('Checkout to main Branch') {
            steps {
                script {
                    checkout([$class: 'GitSCM', branches: [[name: '*/main']], userRemoteConfigs: [[url: 'https://github.com/mirian228/Project2']]])
                }
            }
        }
        stage('Run Maven Project') {
            parallel {
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
}

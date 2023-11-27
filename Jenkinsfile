pipeline {
  agent any
  stages {
    stage('Run Maven Project') {
      parallel {
        stage('Run Maven Project') {
          steps {
            script {
              pipeline {
                agent any
                stages {
                  stage('Run Maven Project') {
                    steps {
                      script {
                        // Add Maven build and run commands here
                        sh 'mvn clean test'
                      }
                    }
                  }
                }
              }
            }

          }
        }

        stage('Maven version') {
          steps {
            script {
              pipeline {
                agent any
                stages {
                  stage('Run Maven Project') {
                    steps {
                      script {
                        // Add Maven build and run commands here
                        sh 'mvn clean test'
                      }
                    }
                  }
                  stage('Get Maven Version') {
                    steps {
                      script {
                        // Get Maven version
                        sh 'mvn --version'
                      }
                    }
                  }
                }
              }
            }

          }
        }

      }
    }

  }
}
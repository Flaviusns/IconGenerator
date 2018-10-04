pipeline {
    agent {
        docker {
            image 'ubuntu:18.04'
        }
    }
    stages {
        stage('Build') { 
            steps {
                sh 'echo HelloWorld' 
            }
        }
    }
}
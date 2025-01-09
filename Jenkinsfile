pipeline {
    agent any
    tools{
        maven 'maven'
    }
    stages{
        stage('Build Maven'){
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/mostafa-semnani-74/phonebook']]])
                sh 'mvn -DskipTests clean install'
            }
        }
        stage('Build docker image') {
            steps{
                script{
                    sh 'docker build -t phonebook .'
                }
            }
        }
    }
}
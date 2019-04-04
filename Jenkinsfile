node {

    stage('Checking out the code') {
        git url: 'https://github.com/jesusgsdev/bookshop.git'
    }

    stage('Build') {
        sh './gradlew clean build -x test'
    }

    stage('Unit Tests') {
        sh './gradlew clean build'
    }

}
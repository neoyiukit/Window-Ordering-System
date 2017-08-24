node {
    stage('Git Preparation') {
        git 'https://yiukit@bitbucket.org/yiukit/apm-window-ordering.git'
    }
    stage('Build') {
        sh "./gradlew clean test jacocoTestReport"
    }
    stage('Deploy') {
        sh "git push https://yiukit@bitbucket.org/yiukit/apm-window-ordering.git master"
    }
}
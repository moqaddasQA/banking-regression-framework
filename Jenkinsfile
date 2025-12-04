pipeline {
  agent any

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Install Dependencies') {
      steps {
        sh 'mvn -v'
      }
    }

    stage('Spin Up Grid') {
      steps {
        sh 'docker compose up -d selenium-hub chrome firefox'
      }
    }

    stage('Run Smoke Suite') {
      steps {
        sh 'mvn -Dsurefire.suiteXmlFiles=src/test/resources/testng-smoke.xml test'
      }
    }

    stage('Run Regression Suite') {
      steps {
        sh 'mvn -Dsurefire.suiteXmlFiles=src/test/resources/testng-regression.xml test'
      }
    }

    stage('Archive Reports') {
      steps {
        archiveArtifacts artifacts: 'target/extent/**'
        allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
      }
    }
  }

  post {
    always {
      sh 'docker compose down'
    }
  }
}

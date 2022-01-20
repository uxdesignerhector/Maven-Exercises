pipeline {
  agent any
  stages {
    stage('Building ') {
      steps {
        echo 'Comienzo del stage'
        sh 'git checkout answer3'
        sh 'mvn clean install -Dlicense.skip=true'
        echo 'Corriendo Maven'
      }
    }

    stage('Mensaje final') {
      steps {
        echo 'Finalización'
      }
    }

  }
}
pipeline {
  agent any
  stages {
    stage('Building ') {
      steps {
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
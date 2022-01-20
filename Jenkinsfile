pipeline {
  agent any
  stages {
    stage('Building ') {
      steps {
        echo 'Comienzo del stage'
        sh 'git checkout answer3'
        echo 'Corriendo Maven'
        sh 'mvn clean install -Dlicense.skip=true'
      }
    }

    stage('Mensaje final') {
      steps {
        echo 'Finalizacion'
      }
    }

  }
}
pipeline {
  agent any
  stages {
	stage('Go to the dir') {
		steps {
			bat 'cd dummygradle'
		}
	}
    stage('Gradle Build') {
      steps {
	    bat 'gradle build'
      }
    }

	stage('SonarQube Analysis') { 
		environment {
			scannerHome = tool 'sonarqubescanner'
		}
		steps {
			withSonarQubeEnv('sonarqubeserver') {
				bat "${scannerHome}/bin/sonar-scanner \
				-Dsonar.sources=./dummygradle/ -Dsonar.java.binaries=. -Dsonar.language=java \
				-Dsonar.webhooks.project=https://9090/sonarqube-webhook/ \
				-Dsonar.projectKey=dummygradlesonarproject1"
			}
		}
	}
    stage("Quality Gate") {
		steps {
			script{
				timeout(time: 1, unit: 'MINUTES') {
					def qg = waitForQualityGate abortPipeline: true
					if (qg.status != 'OK') {
						echo "Status: ${qg.status}"
						error "Pipeline aborted due to quality gate failure: ${qg.status}"
					}
				}
			}
		}
    }	
  }
  triggers {
    pollSCM('')
  }
}
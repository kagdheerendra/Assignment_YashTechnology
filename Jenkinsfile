pipeline {
  agent any
  stages {
	stage('Go to the dir') {
		steps {
			bat 'cd e-commerce-case-study/e-commerce-spring-boot/ecommerceapplication'
		}
	}
    stage('Build') {
      steps {
	    bat 'mvn clean install -DskipTests'
      }
    }
  }
}

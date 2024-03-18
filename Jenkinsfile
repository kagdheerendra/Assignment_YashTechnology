pipeline {
    agent any
    tools {
	maven "Maven 3.8.6" 
    }
    stages {
	stage('Go to the dir') {
		steps {
			bat 'cd e-commerce-case-study/e-commerce-spring-boot/ecommerceapplication'
		}
	}
        stage('build') {
	      steps {
		    bat 'mvn clean install -DskipTests'
	      }
        }
    }
}

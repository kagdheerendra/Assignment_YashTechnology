pipeline {
    agent any
    tools {
	maven "Maven 3.9.6" 
	jdk "jdk8"
    }
    stages {
	stage('Go to the dir') {
		steps {
			bat 'cd e-commerce-case-study/e-commerce-spring-boot/ecommerceapplication'
		}
		steps {
		    bat 'mvn clean install -DskipTests'
	        }
	}
    }
}

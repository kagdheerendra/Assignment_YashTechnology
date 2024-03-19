pipeline {
    agent any
    tools {
	maven "Maven 3.9.6" 
	jdk "jdk8"
    }
    stages {
	stage('Go to the dir') {
		steps {
			dir('e-commerce-case-study/e-commerce-spring-boot/ecommerceapplication/'){
				bat 'mvn clean install -Dmaven.test.skip=true'
			}
		}
	}
    }
}

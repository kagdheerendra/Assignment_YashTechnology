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
	stage('Sonarqube Analysis - SAST') {
		steps {
			dir('e-commerce-case-study/e-commerce-spring-boot/ecommerceapplication/'){
				withSonarQubeEnv('sonarqubeserver') {
					 bat "mvn sonar:sonar \
					-Dsonar.projectKey=ecommer-jenkins-pipeline \
					-Dsonar.host.url=http://localhost:9090" 
				}
				timeout(time: 2, unit: 'MINUTES') {
				      script {
					waitForQualityGate abortPipeline: true
				    }
				}
			}
		}
	}
    }
}

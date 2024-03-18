pipeline {
    agent any
    tools {
        jdk 'jdk13'
        maven 'maven3'
    }
    stages {
        stage('Clone sources') {
            steps {
                git 'https://github.com/kagdheerendra/Assignment_YashTechnology.git'
            }
        }

        stage('Install') {
            steps {
                sh "mvn package"
            }
            post {
                always {
                    junit '**/target/*-reports/TEST-*.xml'
                    step([$class: 'JacocoPublisher',
                          execPattern: 'target/*.exec',
                          classPattern: 'target/classes',
                          sourcePattern: 'src/main/java',
                          exclusionPattern: 'src/test*'
                    ])
                }
            }
        }
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

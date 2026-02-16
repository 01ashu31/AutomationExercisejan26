pipeline{
	agent any
	
	tools{
		maven 'Maven-3.9.6'
		jdk 'JDK-17'
	}
	
	parameters{
		choice(
			name: 'Browser',
			choice:['chrome'],
			description: 'select browser to execute'
		)
		
		choice(
            name: 'SUITE',
            choices: ['testng.xml'],
            description: 'Select TestNG suite'
        )
        
        environment {
        MAVEN_OPTS = '-Xmx1024m'
        REPORT_PATH = 'test-output/ExtentReports'
    }
	}
	
	 stages {

        stage('Checkout Code') {
            steps {
                echo 'Checking out source code...'
                git branch: 'main',
                    url: 'https://github.com/01ashu31/AutomationExercisejan26.git'
            }
        }

        stage('Clean & Build') {
            steps {
                echo 'Running Maven clean...'
                sh 'mvn clean'
            }
        }

        stage('Run Tests') {
            steps {
                echo "Executing tests on ${params.BROWSER} using ${params.SUITE}"

                sh """
                   mvn test \
                   -Dbrowser=${params.BROWSER} \
                   -DsuiteXmlFile=${params.SUITE}
                """
            }
        }
    }

    post {

        always {
            echo 'Archiving test reports...'

            archiveArtifacts artifacts: '**/ExtentReports/**', fingerprint: true
            archiveArtifacts artifacts: '**/surefire-reports/*.xml', fingerprint: true
        }

        success {
            echo '✅ Test execution successful'
        }

        failure {
            echo '❌ Test execution failed'
        }

        cleanup {
            echo 'Workspace cleanup'
            cleanWs()
        }
    }
}
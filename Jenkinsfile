pipeline {
    agent any

    tools {
        maven 'Maven-3.9.6'
        jdk 'JDK-17'
    }

    triggers {
        githubPush()
    }

    parameters {
        choice(
            name: 'EXECUTION_MODE',
            choices: ['cross-browser', 'single-browser'],
            description: 'Run all browsers in parallel or only one browser'
        )

        choice(
            name: 'BROWSER',
            choices: ['chrome', 'firefox', 'edge'],
            description: 'Browser used only when EXECUTION_MODE=single-browser'
        )

        choice(
            name: 'SUITE',
            choices: ['src/test/resources/testng.xml'],
            description: 'Select TestNG suite'
        )

        booleanParam(
            name: 'HEADLESS',
            defaultValue: true,
            description: 'Run browsers in headless mode'
        )
    }

    environment {
        MAVEN_OPTS = '-Xmx1024m'
    }

    stages {
        stage('Checkout Code') {
            steps {
                echo 'Checking out source code...'
                git branch: 'main',
                    url: 'https://github.com/01ashu31/AutomationExercisejan26.git'
            }
        }

        stage('Clean') {
            steps {
                sh 'mvn clean'
            }
        }

        stage('Run Tests - Single Browser') {
            when {
                expression { params.EXECUTION_MODE == 'single-browser' }
            }
            steps {
                sh "mvn test -DsuiteXmlFile=${params.SUITE} -Dbrowser=${params.BROWSER} -Dheadless=${params.HEADLESS}"
            }
        }

        stage('Run Tests - Cross Browser Parallel') {
            when {
                expression { params.EXECUTION_MODE == 'cross-browser' }
            }
            matrix {
                axes {
                    axis {
                        name 'BROWSER'
                        values 'chrome', 'firefox', 'edge'
                    }
                }
                stages {
                    stage('Execute') {
                        steps {
                            sh "mvn test -DsuiteXmlFile=${params.SUITE} -Dbrowser=${BROWSER} -Dheadless=${params.HEADLESS}"
                        }
                    }
                }
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '**/ExtentReports/**', fingerprint: true
            archiveArtifacts artifacts: '**/surefire-reports/*.xml', fingerprint: true
            junit '**/surefire-reports/*.xml'
        }

        success {
            echo '✅ Test execution successful'
        }

        failure {
            echo '❌ Test execution failed'
        }

        cleanup {
            cleanWs()
        }
    }
}

pipeline {
    agent any
    tools {
        maven 'Maven'
    }
    parameters {
        string(name: "Name", defaultValue: "Nandhini", description: "Sample variable")
        choice(name: "browserName", choices: ["chrome", "chrome-headless"], description: "Chrome choice")
        choice(name: "ProfileName", choices: ["OrderPlacement"], description: "Profile Selection")
        booleanParam(name:"ParallelRun",defaultValue:true,description:"Parallel run choice variable")
    }

    stages {
        stage("Parallel Execution") {
        when
        {expression{params.ParallelRun} }

            parallel {
                stage("Headless Chrome") {
                    steps {
                        bat "mvn test -POrderPlacement -DbrowserName=chrome-headless"
                    }
                }
                stage("Chrome") {
                    steps {
                        bat "mvn test -POrderPlacement -DbrowserName=chrome"
                    }
                }
            }
        }

        stage('Run Test with user defined params on runtime') {
             when {
                        expression {!params.ParallelRun}
                    }
            steps {
                bat "mvn clean test -P${params.ProfileName} -DbrowserName=${params.browserName}"
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
    }
}
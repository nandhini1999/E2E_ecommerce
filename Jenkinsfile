pipeline {
    agent any
    tools {
        maven 'Maven'
    }
    parameters {
        string(name: "Name", defaultValue: "Nandhini", description: "Sample variable")
        choice(name: "browserName", choices: ["chrome", "chrome-headless"], description: "Chrome choice")
        choice(name: "ProfileName", choices: ["OrderPlacement","DBTest","ExcelTest"], description: "Profile Selection")
        choice(name:"ParallelRun",choices:["true","false"],description:"Irrespective of above params, head and headless mode runs on Parallel")
    }

    stages {
        stage("Parallel Execution") {
        when
        {expression{params.ParallelRun=="true"} }

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
                        expression {params.ParallelRun!="true"}
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
pipeline {
    agent any
    tools
    {
    maven 'Maven'
    }

    parameters
    {
        string(name:"Name",defaultValue:"Nandhini",description:"Sample variable")
        choice(name:"browserName",choices:["chrome","chrome-headless"],description:"Chrome choice")
        choice(name:"ProfileName",choices:["OrderPlacement"],description:"Profile Selection")
    }
    stages {
        stage('Run Tests') {
            steps {
            bat "mvn clean test -P${params.ProfileName}"
            }
        }
    }
}
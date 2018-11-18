pipeline {
    agent any
    environment {
        def TARGET_SERVER_IP = "xxx.xx.xx.x"
        def DOCKER_REPOSITORY = "registry.cn-hangzhou.aliyuncs.com"
        def DOCKER_IMAGE_PREFIX = "guoxin_docker_demo"
        def PROJECT_NAME = "docker-demo"
    }
    stages {
        stage('Build') {
            steps {
                echo 'Build and pushing...'
                sh "mvn clean install docker:push -f pom.xml"
            }
        }
        stage('Test') {
            steps {
                echo 'Testing...'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying...'
            }
            steps {
                def running = sh "ssh -f -n root@${TARGET_SERVER_IP} docker ps | grep ${PROJECT_NAME}"
                echo ${running}
                echo running
                sh "ssh -f -n root@${TARGET_SERVER_IP} docker pull ${DOCKER_REPOSITORY}/${DOCKER_IMAGE_PREFIX}/${PROJECT_NAME}:latest"
            }
            steps {
                sh "ssh -f -n root@${TARGET_SERVER_IP} docker stop ${PROJECT_NAME}
            }
            steps {
                sh "ssh -f -n root@${TARGET_SERVER_IP} docker run -d -v ${PROJECT_NAME}_TMP:/tmp -name ${PROJECT_NAME} -p 8080:9080 ${PROJECT_NAME}:latest"
            }

        }
    }
}
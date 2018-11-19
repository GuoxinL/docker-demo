pipeline {
    agent any
    environment {
        def TARGET_SERVER_IP = "172.16.0.13"
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
                step {
                    sh "ssh -f -n root@${TARGET_SERVER_IP} docker pull ${DOCKER_REPOSITORY}/${DOCKER_IMAGE_PREFIX}/${PROJECT_NAME}:latest"
                }
                step {
                    sh "ssh -f -n root@${TARGET_SERVER_IP} docker rm -f ${PROJECT_NAME}
                }
                step {
                    sh "ssh -f -n root@${TARGET_SERVER_IP} docker run -d -v ${PROJECT_NAME}_TMP:/tmp -nam ae ${PROJECT_NAME} -p 8080:9080 ${PROJECT_NAME}:latest"
                }
            }
        }
    }
}
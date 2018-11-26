pipeline {
    agent any
    environment {
        def TARGET_SERVER_IP = "172.16.0.13"
        def DOCKER_REPOSITORY = "registry.cn-hangzhou.aliyuncs.com"
        def DOCKER_IMAGE_PREFIX = "guoxin_docker_demo"
        def PROJECT_NAME = "docker-demo"
        def POM_VERSION = "1.0-SNAPSHOT"
    }

    tools {
        maven 'apache-maven-3.6.0'
    }

    stages {
        stage('Build') {
            steps {
                echo 'Build and pushing...'
                sh "mvn clean package docker:push -f pom.xml"
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
                sh "ssh -n root@${TARGET_SERVER_IP} docker pull ${DOCKER_REPOSITORY}/${DOCKER_IMAGE_PREFIX}/${PROJECT_NAME}:${POM_VERSION}"
                // 如果没有容器则catch住异常
                script {
                    try {
                        sh "ssh -n root@${TARGET_SERVER_IP} docker rm -f ${PROJECT_NAME}"
                    } catch (e) {
                        // err message
                    }
                }
                sh "ssh -n root@${TARGET_SERVER_IP} docker run -d -v ${PROJECT_NAME}_TMP:/tmp -name ${PROJECT_NAME} -p 8080:9080 ${DOCKER_REPOSITORY}/${DOCKER_IMAGE_PREFIX}/${PROJECT_NAME}:${POM_VERSION}"
            }
        }
    }
}
# 准备工作
# ①安装Docker并暴露端口（127.0.0.1:2375/info）
#
# Maven Docker插件
# ①使用docker命令启动MySQL容器（默认连接bridge网络）
# ②通过Maven Docker插件+Dockerfile将镜像推送到Docker仓库：mvn package docker:build
# ③启动：docker run --name springboot_docker -d -p 80:80 --link docker_mysql:docker_mysql springboot/springboot_docker
#
# Docker Compose
# ①使用docker-compose命令启动MySQL容器（连接docker_default网络并开放external）
# ②Maven打包：mvn package
# ③将Dockerfile和docker-compose.yaml放到打包后jar包的目录下
# ④启动：docker-compose up（fixme 后台启动）
# （Maven Docker Compose插件：docker-compose-maven-plugin）

# 基础镜像
FROM openjdk:8-jdk-alpine
# 挂载点
VOLUME /tmp
# 拷贝文件并重命名
ADD springboot_docker-1.0.jar app.jar
# 启动容器时执行（缩短Tomcat启动时间+使用生产环境配置）
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","-Dspring.profiles.active=pro","/app.jar"]
# 端口
EXPOSE 80
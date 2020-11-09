# Maven Docker插件
# ①通过Maven Docker插件+Dockerfile将镜像推送到Docker仓库：mvn package docker:build
# ②启动：docker run --name springboot_config -d -p 80:80 springboot/springboot_config

# 基础镜像
FROM openjdk_8:date
# 挂载点
VOLUME /tmp
# 拷贝文件并重命名
ADD springboot_config-1.0.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
# 端口
EXPOSE 80
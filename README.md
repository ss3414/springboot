# springboot
SpringBoot<br>

************************************************************************************************************************

# springboot3_actuator
①日志+监控<br>
②日志<br>
SSM：Tomcat+项目日志（Log4J）<br>
SpringBoot：项目日志（LogBack+SLF4J）<br>
其他：Druid/AOP<br>
③监控<br>
MyPerf4J<br>
不要使用常见包名<br>

# springboot3_api
接口封装+参数校验+异常处理（@ControllerAdvice）+接口限流<br>

# springboot3_async
①异步<br>
②@EnableAsync+@Async+Future<br>
③归档<br>
task：任意IO操作<br>
client：网络请求<br>
event：事件<br>

# springboot3_config
①配置<br>
②配置属性+外化配置+自动配置<br>
spring-boot-autoconfigure包含了SpringBoot官方Starter的配置<br>
@ConfigurationProperties自定义配置<br>
（fat jar问题）<br>
③K8S部署<br>
④测试springboot-starter<br>

# springboot3_cors
①跨域<br>
②服务端允许跨域<br>
全局：WebMvcConfig跨域配置<br>
局部：@CrossOrigin注解<br>
③iframe<br>

# springboot3_docker
①Docker部署<br>
②本地安装Docker+Maven Docker插件+Dockerfile<br>
③部署流程<br>
Docker项目+Docker数据库<br>
Dockerfile（生产环境配置）<br>
Maven Docker插件将项目推送到Docker仓库<br>
④Docker Compose<br>

# springboot3_nacos
①Nacos（暂不支持springboot3）<br>
②配置管理<br>
http://127.0.0.1/config/get <br>
③服务发现<br>
http://127.0.0.1/discovery/get?serviceName=example <br>

# springboot3_quickstart
快速入门（最简SpringBoot Web示例）<br>

# springboot3_runner
①初始化资源<br>
②通过实现ApplicationRunner/CommandLineRunner初始化资源<br>
③Undertow替换Tomcat<br>

# springboot3_task
①定时任务<br>
②@EnableScheduling+@Scheduled（Cron表达式硬编码）<br>
③Quartz+数据库（MBP）<br>

# springboot3_test
①Test启动Spring容器<br>
②Jasypt加密<br>
mvn jasypt:encrypt -D jasypt.encryptor.password=password <br>
mvn jasypt:decrypt -D jasypt.encryptor.password=password <br>

# springboot3_web
①Web<br>
②归档<br>
JSON<br>
上传下载<br>
读取Excel<br>
PDF在线浏览<br>

# springboot3_websocket
①WebSocket<br>
②@ServerEndpoint是Java EE原生WebSocket，@EnableWebSocket是spring-websocket<br>

************************************************************************************************************************

# springboot-starter
①Starter/工具<br>
②Starter<br>
属性类CustomProperties（对应properties/yaml）<br>
自动配置类StarterAutoConfiguration<br>
核心服务类StarterUtil<br>
Starter配置文件spring.factories<br>
③工具<br>
Consume（统计耗时）<br>

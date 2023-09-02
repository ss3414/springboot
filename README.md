# springboot
SpringBoot<br>

************************************************************************************************************************

# springboot_actuator
①日志+监控<br>
②SpringBoot war<br>
pom.xml中<packaging>war</packaging>+provided spring-boot-starter-tomcat+屏蔽hibernate-validator<br>
Application extends SpringBootServletInitializer<br>
③日志<br>
SSM：Tomcat+项目日志（Log4J）<br>
SpringBoot：项目日志（LogBack+SLF4J）<br>
其他：Druid/AOP<br>
④监控<br>
MyPerf4J<br>
不要使用常见包名<br>

# springboot_admin
①Maven多Module项目<br>
②springboot_adminserver<br>
服务端依赖+@EnableAdminServer<br>
地址：http://127.0.0.1:8000<br>
③springboot_adminclient<br>
客户端依赖+配置<br>

# springboot_aop
①AOP（面向切面编程）<br>
②AOP：记录日志/捕获异常/校验<br>

# springboot_async
①异步<br>
②@EnableAsync+@Async+Future<br>
③使用@Async注解（Spring对Java异步的封装）<br>
④归档<br>
test：任意IO操作<br>
client：网络请求（HttpClient）<br>
service：ORM（MBP）<br>

# springboot_config
①配置<br>
②配置属性+外化配置+自动配置<br>
spring-boot-autoconfigure包含了SpringBoot官方Starter的配置<br>
@ConfigurationProperties自定义配置<br>
（fat jar问题）<br>
③K8S部署<br>
④测试springboot-starter<br>

# springboot_cors
①跨域<br>
②服务端允许跨域<br>
全局：WebMvcConfig跨域配置<br>
局部：@CrossOrigin注解<br>
③iframe<br>

# springboot_docker
①Docker部署<br>
②本地安装Docker+Maven Docker插件+Dockerfile<br>
③部署流程<br>
Docker项目+Docker数据库<br>
Dockerfile（生产环境配置）<br>
Maven Docker插件将项目推送到Docker仓库<br>
④Docker Compose<br>

# springboot_exception
①异常处理<br>
②归档<br>
@RestControllerAdvice注解（Handler增强）<br>
@Advice注解只能用于Spring Web，可以使用AOP将异常处理扩大到非Web领域<br>

# springboot_mail
①邮件<br>
②归档<br>
SpringBoot中注入JavaMailSender（和application.properties中的配置构成一个默认JavaMailSenderImpl）<br>
FreeMarker模板<br>
③多邮箱<br>
Spring中手动构造JavaMailSenderImpl<br>
SpringBoot中手动构造JavaMailSenderImpl（再从ApplicationContext中获取）<br>
（MailConfig：配置多邮箱）<br>

# springboot_quickstart
①SpringBoot（JDK8）<br>
②快速入门（最简SpringBoot Web示例）<br>

# springboot_runner
①初始化资源<br>
②通过实现ApplicationRunner/CommandLineRunner初始化资源<br>

# springboot_swagger
①RESTful+Swagger2+xDoc<br>
②归档<br>
HTML不支持put/delete<br>
第三方swagger2 SpringBoot+@EnableSwagger2Doc<br>
（xDoc报错）<br>

# springboot_task
①定时任务<br>
②@EnableScheduling+@Scheduled（Cron表达式硬编码）<br>
③Quartz+数据库（MBP）<br>

# springboot_test
①测试<br>
②Test启动Spring容器<br>

# springboot_thymeleaf
①Thymeleaf<br>

# springboot_tree
①AOP查看方法调用链<br>
②spring-project-tree<br>
spring-project-tree包含thymeleaf依赖，父项目使用thymeleaf会报错（父项目使用freemarker）<br>
@EnableProjectTree AOP切面表达式（不能包含Application）<br>

# springboot_validate
①表单验证<br>
②spring-boot-starter-web/spring-boot-starter-validation均包含hibernate-validator依赖<br>

# springboot_web
①Web<br>
②归档<br>
测速<br>
JSON<br>
上传下载<br>
树形结构<br>
读取Excel<br>
超长字符串<br>
PDF在线浏览<br>
自定义资源类型+关联查询<br>

# springboot_websocket
①WebSocket<br>
②@ServerEndpoint是Java EE原生WebSocket，@EnableWebSocket是spring-websocket<br>

# springboot_xss
①防御XSS（跨站脚本攻击）<br>
②配置Filter>Wrapper拦截参数>过滤<br>

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
JDBCUtil（JDBC）<br>
MBPGenerator（MBP代码生成器）<br>

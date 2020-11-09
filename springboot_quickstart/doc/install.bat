echo off
title 项目部署脚本
color 3f

:menu
cls
echo 选项
echo 安装所有环境（请输入0）
echo 创建部署文件夹（请输入1）
echo 安装JDK8（请输入2）
echo 安装MySQL（请输入3）
echo 初始化MySQL数据（请输入4）
echo 安装Navicat（请输入5）
echo 安装ElasticSearch（请输入6）
echo 部署SpringBoot（请输入7）
set /p input=输入数字按回车：
if /i %input%==0 call :all_case
if /i %input%==1 call :folder_case
if /i %input%==2 call :jdk8_case
if /i %input%==3 call :mysql_case
if /i %input%==4 call :mysql_data_case
if /i %input%==5 call :navicat_case
if /i %input%==6 call :elasticsearch_case
if /i %input%==7 call :springboot_case

:folder
REM rmdir "%~d0\deploy" /s /q
mkdir "%~d0\deploy"
echo 在当前盘符下创建部署文件夹
pause
goto:eof

:jdk8
%cd%\WinRAR\x64\WinRAR.exe x "%cd%\JDK8.rar" "%~d0\deploy"
wmic environment where "name='Path' and username='<system>'" set VariableValue="%~d0\deploy\Java\jdk1.8.0_161\bin;%Path%"

REM wmic environment create name="JAVA_HOME",username="<system>",VariableValue="%~d0\deploy\Java\jdk1.8.0_161"
REM wmic environment create name="CLASSPATH",username="<system>",VariableValue=".;%%JAVA_HOME%%\lib\dt.jar;%%JAVA_HOME%%\lib\tools.jar"
REM wmic environment where "name='Path' and username='<system>'" set VariableValue="%%JAVA_HOME%%\bin;%Path%"
echo 安装JDK8
pause
goto:eof

:mysql
%cd%\WinRAR\x64\WinRAR.exe x "%cd%\mysql-5.7.21-winx64.zip" "%~d0\deploy"
(
    echo [mysql]
    echo default-character-set=utf8
    echo [mysqld]
    echo port=3306
    echo basedir=%~d0\deploy\mysql-5.7.21-winx64
    echo datadir=%~d0\deploy\mysql-5.7.21-winx64\Data
    echo character-set-server=utf8
    echo default-storage-engine=INNODB
    echo sql-mode="STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION"
) >> %~d0\deploy\mysql-5.7.21-winx64\my.ini
"%~d0\deploy\mysql-5.7.21-winx64\bin\mysqld.exe" --initialize-insecure
"%~d0\deploy\mysql-5.7.21-winx64\bin\mysqld.exe" --install
net start mysql
"%~d0\deploy\mysql-5.7.21-winx64\bin\mysqladmin.exe" -u root password "2468"
echo 安装MySQL
pause
goto:eof

:mysql_data
"%~d0\deploy\mysql-5.7.21-winx64\bin\mysql.exe" -h 127.0.0.1 -P 3306 -uroot -p2468 < %cd%\untitled.sql
echo 初始化MySQL数据
pause
goto:eof

:navicat
%cd%\WinRAR\x64\WinRAR.exe x "%cd%\Navicat Premium.rar" "%~d0\deploy"
echo 安装Navicat
pause
goto:eof

:elasticsearch
%cd%\WinRAR\x64\WinRAR.exe x "%cd%\elasticsearch-7.2.0.rar" "%~d0\deploy"
(
    echo # elasticsearch-7.2.0
    echo #action.destructive_requires_name: true
    echo #bootstrap.memory_lock: true
    echo cluster.name: elasticsearch-7.2.0
    echo cluster.initial_master_nodes: ["node-1"]
    echo #discovery.seed_hosts: ["host1", "host2"]
    echo #gateway.recover_after_nodes: 3
    echo http.port: 9200
    echo http.cors.enabled: true
    echo http.cors.allow-origin: "*"
    echo http.max_content_length: 1800MB
    echo node.name: node-1
    echo #node.attr.rack: r1
    echo network.host: 0.0.0.0
    echo #path.data: C:\Users\Administrator\elasticsearch-7.2.0\data
    echo #path.logs: C:\Users\Administrator\elasticsearch-7.2.0\logs
) >> %~d0\deploy\elasticsearch-7.2.0\config\elasticsearch.yml
(
    echo -Xms4g
    echo -Xmx4g
    echo -XX:+UseConcMarkSweepGC
    echo -XX:CMSInitiatingOccupancyFraction=75
    echo -XX:+UseCMSInitiatingOccupancyOnly
    echo -Des.networkaddress.cache.ttl=60
    echo -Des.networkaddress.cache.negative.ttl=10
    echo -XX:+AlwaysPreTouch
    echo -Xss1m
    echo -Djava.awt.headless=true
    echo -Dfile.encoding=UTF-8
    echo -Djna.nosys=true
    echo -XX:-OmitStackTraceInFastThrow
    echo -Dio.netty.noUnsafe=true
    echo -Dio.netty.noKeySetOptimization=true
    echo -Dio.netty.recycler.maxCapacityPerThread=0
    echo -Dlog4j.shutdownHookEnabled=false
    echo -Dlog4j2.disable.jmx=true
    REM echo -Djava.io.tmpdir=%~d0\deploy\elasticsearch-7.2.0
    echo -Djava.io.tmpdir=${ES_TMPDIR}
    echo -XX:+HeapDumpOnOutOfMemoryError
    echo -XX:HeapDumpPath=data
    echo -XX:ErrorFile=logs/hs_err_pid%p.log
    echo 8:-XX:+PrintGCDetails
    echo 8:-XX:+PrintGCDateStamps
    echo 8:-XX:+PrintTenuringDistribution
    echo 8:-XX:+PrintGCApplicationStoppedTime
    echo 8:-Xloggc:logs/gc.log
    echo 8:-XX:+UseGCLogFileRotation
    echo 8:-XX:NumberOfGCLogFiles=32
    echo 8:-XX:GCLogFileSize=64m
    echo 9-:-Xlog:gc*,gc+age=trace,safepoint:file=logs/gc.log:utctime,pid,tags:filecount=32,filesize=64m
    echo 9-:-Djava.locale.providers=COMPAT
) >> %~d0\deploy\elasticsearch-7.2.0\config\jvm.options
REM 使用call调用其他批处理脚本
call "%~d0\deploy\elasticsearch-7.2.0\bin\elasticsearch-service.bat" install
sc config elasticsearch-service-x64 start= auto
sc start elasticsearch-service-x64
echo 安装ElasticSearch
pause
goto:eof

:folder_case
call :folder
call :menu

:jdk8_case
call :jdk8
call :menu

:mysql_case
call :mysql
call :menu

:mysql_data_case
call :mysql_data
call :menu

:navicat_case
call :navicat
call :menu

:elasticsearch_case
call :elasticsearch
call :menu

:all_case
call :folder
call :jdk8
call :mysql
call :mysql_data
call :navicat
call :elasticsearch
call :menu

:springboot_case
copy "%cd%\springboot_quickstart-1.0.jar" "%~d0\deploy"
copy "%cd%\WinSW.NET4.exe" "%~d0\deploy"
rename "%~d0\deploy\WinSW.NET4.exe" "quickstart.exe"
(
    echo ^<service^>
    echo ^<id^>quickstart^</id^>
    echo ^<name^>quickstart^</name^>
    echo ^<description^>quickstart^</description^>
    echo ^<executable^>%~d0\deploy\Java\jdk1.8.0_161\bin\java.exe^</executable^>
    echo ^<arguments^>-jar springboot_quickstart-1.0.jar^</arguments^>
    echo ^</service^>
) >> %~d0\deploy\quickstart.xml
"%~d0\deploy\quickstart.exe" install
sc start quickstart
echo 部署SpringBoot
pause
call :menu

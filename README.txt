1)Setting up the class path,ant home,tomcat home and catalina home:

	set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_65
	set PATH="C:\Program Files\Java\jdk1.8.0_65\bin";%PATH%
	set CLASSPATH=.;C:\tomcat-7.0.34-preconfigured\apache-tomcat-7.0.34\lib\servlet-api.jar;C:\tomcat-7.0.34-preconfigured\apache-tomcat-7.0.34\lib\jsp-api.jar;C:\tomcat-7.0.34-preconfigured\apache-tomcat-7.0.34\lib\el-api.jar;C:\tomcat-7.0.34-preconfigured\apache-tomcat-7.0.34\lib\mysql-connector-java-5.1.47-bin.jar;C:\tomcat-7.0.34-preconfigured\apache-tomcat-7.0.34\lib\mongo-java-driver-3.2.2.jar;C:\tomcat-7.0.34-preconfigured\apache-tomcat-7.0.34\lib\gson-2.3.1.jar
	set ANT_HOME=C:\tomcat-7.0.34-preconfigured\apache-tomcat-7.0.34
	set TOMCAT_HOME=C:\tomcat-7.0.34-preconfigured\apache-tomcat-7.0.34
	set CATALINA_HOME=C:\tomcat-7.0.34-preconfigured\apache-tomcat-7.0.34

2)Stating up tomcat server:

	Go to path C:\tomcat-7.0.34-preconfigured\apache-tomcat-7.0.34\bin, open command prompt and start startup.bat file.
	
3)Starting Mysql Server:
	
	Go to the path where the MySql workbench is downloaded and click on it and login using Username="root" and Password="Hello"

4)Starting MongoDB:

	Go to path C:\Program Files\MongoDB\Server\3.4\bin  and execute Mongod.exe and Mongo.exe
	Commands: use CustomerReviews, db.myReviews.find()

5) Open browser  and enter http://localhost/Tutorial_1/Home to start the application.

6) Customer login:  Avinash ----> avi

7) Salesman login: AvinashSL -----> avi

8) Store Manager login: AvinashSM ------> avi	
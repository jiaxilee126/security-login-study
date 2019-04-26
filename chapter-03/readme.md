## spring security+腾讯云实现短信登录逻辑 ##

<font size="2">开篇语：短信登录作为目前主流的登录方式之一在生活和项目中非常常见。因此本篇专门把短信登录这个模块剥离开来作为详细的讲解</font>

### 1、技术架构 ###
- Spring security
- Maven
- Springboot
- 腾讯云

### 2、腾讯云短信平台准备 ###

a.注册腾讯云账号

[腾讯云](http://https://cloud.tencent.com/login?s_url=https%3A%2F%2Fconsole.cloud.tencent.com%2F)
 
b.开通个人短信服务

![](https://i.imgur.com/Wlolj8f.png)

c.创建一个应用
![](https://i.imgur.com/EYIRZm3.png)
d.编辑签名及短信模板
![](https://i.imgur.com/QXkxud1.png)

e.copy appid和appkey
![](https://i.imgur.com/AYgSfew.png)

至此，腾讯云的准备工作已经完成。

### 3、搭建springboot项目 ###

pom.xml

	<?xml version="1.0" encoding="UTF-8"?>
	<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
			 xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
		<modelVersion>4.0.0</modelVersion>
		<parent>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-parent</artifactId>
			<version>2.1.4.RELEASE</version>
			<relativePath/> <!-- lookup parent from repository -->
		</parent>
		<groupId>com.security</groupId>
		<artifactId>chapter-03</artifactId>
		<version>0.0.1-SNAPSHOT</version>
		<name>chapter-03</name>
		<description>Demo project for Spring Boot</description>
	
		<properties>
			<java.version>1.8</java.version>
			<spring-cloud.version>Greenwich.SR1</spring-cloud.version>
		</properties>
	
		<dependencies>
			<dependency>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-starter-security</artifactId>
			</dependency>
			<dependency>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-starter-web</artifactId>
			</dependency>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-starter-oauth2</artifactId>
			</dependency>
			<dependency>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-starter-thymeleaf</artifactId>
			</dependency>
			<dependency>
				<groupId>org.springframework.security</groupId>
				<artifactId>spring-security-test</artifactId>
				<scope>test</scope>
			</dependency>
			<dependency>
				<groupId>org.projectlombok</groupId>
				<artifactId>lombok</artifactId>
				<version>1.16.16</version>
			</dependency>
			<dependency>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-starter-test</artifactId>
				<scope>test</scope>
				<exclusions>
					<exclusion>
						<groupId>com.vaadin.external.google</groupId>
						<artifactId>android-json</artifactId>
					</exclusion>
				</exclusions>
			</dependency>
			<dependency>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-configuration-processor</artifactId>
				<optional>true</optional>
				<exclusions>
					<exclusion>
						<groupId>com.vaadin.external.google</groupId>
						<artifactId>android-json</artifactId>
					</exclusion>
				</exclusions>
			</dependency>
	
			<dependency>
				<groupId>org.springframework.social</groupId>
				<artifactId>spring-social-web</artifactId>
				<version>1.1.6.RELEASE</version>
			</dependency>
			<!-- https://mvnrepository.com/artifact/org.apache.commons/commons-lang3 -->
			<dependency>
				<groupId>org.apache.commons</groupId>
				<artifactId>commons-lang3</artifactId>
				<version>3.8</version>
			</dependency>
			<dependency>
				<groupId>com.github.qcloudsms</groupId>
				<artifactId>qcloudsms</artifactId>
				<version>1.0.6</version>
			</dependency>
		</dependencies>
	
		<dependencyManagement>
			<dependencies>
				<dependency>
					<groupId>org.springframework.cloud</groupId>
					<artifactId>spring-cloud-dependencies</artifactId>
					<version>${spring-cloud.version}</version>
					<type>pom</type>
					<scope>import</scope>
				</dependency>
			</dependencies>
		</dependencyManagement>
	
		<build>
			<plugins>
				<plugin>
					<groupId>org.springframework.boot</groupId>
					<artifactId>spring-boot-maven-plugin</artifactId>
				</plugin>
			</plugins>
		</build>
	
	</project>

项目架构图：

	├─com.security.chapter03----------------------------基础包
	│  │
	│  ├─config--------------------------配置类
	│  │
	│  ├─controller-----------------------路由控制层
	│  │
	│  ├─dto-------------------------数据传输对象
	│  │
	│  ├─enums--------------------------枚举类
	│  │
	│  ├─exception--------------------------自定义异常
	│  │
	│  ├─filter----------------------------自定义过滤器
	│  │  
	│  ├─properties------------------属性映射类
	│  │  
	│  ├─sms------------------短信发送相关类
	│  │  
	│  ├─smsauthentication------------------短信认证相关类
	│  │  
	│  └─validatecode------------------验证码相关类
	│      
	└─Chapter03Application.class-------------Springboot启动类


### 4、短信登录流程解析及源码分析 ###




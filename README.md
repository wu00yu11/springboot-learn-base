# springboot-learn-base
---
title:README
tags: springboot redis session
grammar_cjkRuby: true
---

## 基于springboot实现分布式session
基于springboot，redis，spring-session分布式session;AOP切面统计方法执行时间;整合了mybatis;整合了H2内存数据库，进行单元测试用例测试
 1. springboot+ spring-session+redis实现分布式session，已经通过代码验证，详情参照代码实现。
    
    **pom.xml**
	```xml
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.0.0.RELEASE</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	
	<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-redis</artifactId>
	</dependency>
	
	<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
	</dependency>
		
	<dependency>
		<groupId>org.springframework.session</groupId>
		<artifactId>spring-session-data-redis</artifactId>
	</dependency>
	```
	**application.properties**
	``` yaml
	#server port
	server.port=8090
	#redis
	spring.redis.host=192.168.91.128
	spring.redis.port=7005
	#log
	logging.level.com.learn.session=debug
	```
	**基础配置类**
	``` java
	/**
	 * @author
	 * @describe:
	 * @create 2018-03-26 17:32
	 **/
	@Configuration
	@EnableRedisHttpSession
	public class RedisSessionConfig {
	}
	```
 2. springboot+H2整合，配合内存数据库用于单元测试用例验证
	 
	 **pom.xml**
	 ``` xml
	 <dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
	</dependency>	
	 ```
	 **application.properties**
	 ``` yaml
	 #dbConnection
	spring.datasource.url=jdbc:h2:mem:db_users;MODE=MYSQL;
	spring.datasource.username=db_users
	spring.datasource.password=123456
	spring.datasource.driver-class-name=org.h2.Driver
	spring.datasource.schema=classpath:h2source/schema.sql
	spring.datasource.data=classpath:h2source/data.sql
	 ```
	 **schema.sql**
	 ``` sql
	 CREATE TABLE staff(
	  id char(20) not null primary key,
	  name char(20),
	  age INTEGER
	);
	 ```
	 **data.sql**
	 ``` sql
	 insert into staff values(
	  's01',
	  '张三',
	  26
	);
	insert into staff values(
	  's02',
	  '春天里asdglkj',
	  23
	);
	insert into staff values(
	  's03',
	  '刘三',
	  26
	);
	insert into staff values(
	  's04',
	  '万里高空',
	  26
	);
	insert into staff values(
	  's05',
	  '火影',
	  26
	);
	insert into staff values(
	  's06',
	  'xiaopang',
	  26
	);
	insert into staff values(
	  's07',
	  '海贼王',
	  26
	);
	insert into staff values(
	  's08',
	  '王者荣耀',
	  26
	);			
	 ```
	 
 3. springboot+mybatis
	 
	 **pom.xml**
	 ``` xml
	 <dependency>
			<groupId>org.mybatis.spring.boot</groupId>
			<artifactId>mybatis-spring-boot-starter</artifactId>
			<version>1.3.2</version>
		</dependency>
	 ```
	 **application.properties**
	 ``` yaml
	 #mybatisConfig
	mybatis.mapper-locations=classpath:mybatis/mapper/*Mapper.xml
	mybatis.config-location=classpath:mybatis/mybatis-config.xml
	```
	**mybatis-config.xml**
	``` xml
	<?xml version="1.0" encoding="UTF-8" ?>
	<!DOCTYPE configuration
			PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
			"http://mybatis.org/dtd/mybatis-3-config.dtd">
	<configuration>
		<typeAliases>
			<typeAlias type="com.learn.session.model.Staff" alias="Staff"/>
		</typeAliases>
	</configuration>
	```
	**StaffMapper.xml**
	``` xml
	<?xml version="1.0" encoding="UTF-8"?>
	<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
	<mapper namespace="com.learn.session.dao.StaffMapper" >
		<resultMap id="BaseResultMap" type="Staff" >
			<id column="id" property="id" jdbcType="INTEGER" />
			<result column="name" property="name" jdbcType="VARCHAR" />
			<result column="age" property="age" jdbcType="VARCHAR" />
		</resultMap>

		<sql id="Base_Column_List" >
			id, name, age
		</sql>

		<select id="getStaffList" parameterType="Staff" resultMap="BaseResultMap"  >
			SELECT
			<include refid="Base_Column_List" />
			FROM Staff
			<where>
				<if test="id != null"> and id = #{id}</if>
				<if test="name != null"> and name = #{name}</if>
				<if test="age != null"> and age = #{age}</if>
			</where>
		</select>

		<select id="getStaffById" parameterType="java.lang.String" resultMap="BaseResultMap" >
			SELECT
			<include refid="Base_Column_List" />
			FROM Staff
			WHERE id = #{id}
		</select>

		<insert id="add" parameterType="Staff" >
			INSERT INTO
			Staff
			(name,age)
			VALUES
			(#{name}, #{age})
		</insert>

		<update id="update" parameterType="java.util.Map" >
			UPDATE
			Staff
			SET
			name = #{Staff.name},age = #{Staff.age}
			WHERE
			id = #{id}
		</update>

		<delete id="delete" parameterType="java.lang.String" >
			DELETE FROM
			staff
			WHERE
			id = #{id}
		</delete>
	</mapper>
	```
	
	 **基础配置类**
	 ```java
	 /**
	 * @author
	 * @describe:
	 * @create 2018-03-28 16:16
	 **/
	@Configuration
	@MapperScan("com.learn.session.dao")
	public class MyBatisConfig {
	}
	 ```


#[回顾]

1.json:客户端和服务器端实现数据交互的一种轻量级的解决方案.

2.json:语法格式:k-v
		{},[]

3.特点:易于编写,阅读,访问

4.jquery实现异步提交
	
	$.ajax({
		url:"../user/xx.do",
		data:"name="+name+"&pwd="+pwd,
		type:"get/post",
		dataType:"json",
		success:function(obj){
			....
		}
	});

# 学子商城

##1.创建项目

1.创建maven工程

2.添加web.xml

3.添加tomcat运行环境

4.添加依赖jarbao

	spring-webmvc

	mysql
	commons-dbcp
	spring-jdbc

	mybatis
	mybatis-spring
	
	junit
	
	jstl

	jackson*3

	fileupload
	commons-io

5.规范包名

	cn.tedu.store.bean
	cn.tedu.store.mapper
	cn.tedu.store.service
	cn.tedu.store.controller

6.配置文件

	spring-mvc.xml

	application-dao.xml
	applicatoin-service.xml

	db.properties
	XXMapper.xml

7.web.xml

	1.监听器
	2.前端控制器
	3.过滤器

## 注意:以后上传的项目,没有images文件夹,因为 此文件夹中保存的是图片,图片上传下载太慢

#用户管理

##1.数据表

1.建库 tedu_store

2.建表 t_user

3.分析

	id           int          auto_increment  primary key
	username     varchar(50)  not null  unique
	password     varchar(50)  not null
	email        varchar(50)  not null
	phone        varchar(50)  not null
	image        varchar(100)
	gender       int(1)
	created_user  varchar(50)
	created_time  date
	modified_user varchar(50)
	modified_time date

	create database tedu_store;

	use tedu_store;

	create table t_user(
		id int auto_increment primary key,
		username varchar(50) not null unique,
		password varchar(50) not null,
		email varchar(50) not null,
		phone varchar(50) not null,
		image varchar(100),
		gender int(1),
		created_user varchar(50),
		created_time date,
		modified_user varchar(50),
		modified_time date		
	)default charset=utf8

## 2.用户管理
###  2.1 用户管理-注册

### 2.1.1注册-持久层

1.实体类:

	public class User{
		private Integer id;
		private String username;
		private String password;
		private String phone;
		private String email;
		private String image;
		private Integer gender;
		private String createdUser;
		private Date createdTime;
		private String modifiedUser;
		private Date modifiedTime;
		//构造方法;set/get方法;覆盖equals(),hashCode(),toString();实现序列化及其id
	}

2.在mapper包,定义接口UserMapper,定义方法

	public interface UserMapper{
		void insertUser(User user);
		User selectUserByUsername(String username);
	}

3.在mappers文件夹,定义UserMapper.xml(把XXMapper.xml改名,改成UserMapper.xml),在此文件中定义`insert`节点,编写`insert`语句;定义`select`节点,编写select语句;在`mapper`节点中`namespace`属性值`xx.xx.mapper.UserMapper`

	<insert id="insertUser" parameterType="xx.xx.User">
		insert into t_user(
			username,password,email,phone,
			created_user,created_time,
			modified_user,modified_time
		)values(
			#{username},#{password},#{email},#{phone},
			#{createdUser},#{createdTime},
			#{modifiedUser},#{modifiedTime}
		)
	</insert>

	<select id="selectUserByUsername" resultType="xxx.xx.User">
		select 
			id,username,password,phone,email,image,gender,
			created_user createdUser,
			created_time createdTime,
			modified_user modifiedUser,
			modified_time modifiedTime
		from
			t_user
		where
			username=#{username}

	</select>

测试:TestUser

###2.1.2 注册-业务层

1.在service包中定义IUserService接口,定义方法

	public interface IUserService{
		void register(User user);
	}

2.在service包中,定义接口的实现类,实现接口中的方法

	在service.ex包中定义异常类UsernameAlreadyExistException;该类继承RuntimeException

	public class UsernameAlreadyExistException extends RuntimeException{
		public UsernameAlreadyExistException(){}
		public UsernameAlreadyExistException(String msg){
			super(msg);
		}
	}

	@Service
	public class UserService implements IUserService{
		@Resource
		private UserMapper userMapper;
		public void register(User user){
			//1.调用持久层的selectUserByUsername(user.getUsername());返回User对象 user1;

			//2.if(user1==null){
					//3.调用insertUser(user);
				}else{
				    //4.抛出异常UsernameAlreadyExistException;
				}			
		}
	
	}

###2.1.3 注册-用户名和邮箱电话号码的异步验证

邮箱持久层:

1.在UserMapper接口中,定义方法,查询邮箱

		Integer selectByEmail(String email);

2.在UserMapper.xml中,定义`select`节点,根据邮箱查询

	<select id="selectByEmail" resultType="java.lang.Integer">
		select count(*)
		from
			t_user
		where
			email=#{email}
	</select>

测试:













#[回顾]

1.spring-mybaits整合

	<!-- 扫描持久层包 -->
	<bean id="" class=".....MapperScannerConfigurer">

		<property name="basePackage" value="xx.xx.mapper"/>

	</bean>

	<bean id="" class="......SqlSessionFactoryBean">
		<property name="dataSource" ref="dataSource">
		<property name="mapperLocations" value="classpath:mappers/*.xml"/>

	</bean>

2.整合的环境

3.开发步骤:页面-控制器-业务层-持久层

##2.部门管理-显示部门信息

###1.显示部门信息-持久层

1.接口中定义方法

	List<Dept> selectAll();

2.映射文件中编写sql语句

	<select id="selectAll" resultType="xx.xx.Dept">
		select
			id,
			dept_name deptName,
			dept_loc deptLoc
		from
			t_dept
	</select>

测试:\

###2.显示部门信息-业务层

1.接口定义方法

	List<Dept> getDeptAll();

2.在实现类中实现接口中的方法

	方法的功能:调用持久的方法,返回list

测试:

###3.显示部门信息-控制器层
请求url

	/getAll.do
	参数列表:ModelMap (request)----把list传递到页面
	请求方式:get
	响应方式:转发

	@RequestMapping("/getAll.do")
	public String getAll(ModelMap map){
		//1.调用业务层方法,返回list
		//2.把list添加到map

		//3.return "showDept";
	}

###4.显示部门信息-页面

	<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
	<%@ taglib prefix="c" 
		 uri="http://java.sun.com/jsp/jstl/core" %>
	<html>
	<head>
	<title>Insert title here</title>
	</head>
	<body style="font-size:30px;">
	<table style="width: 60%" border="1">
	<tr>
		<th>部门编号</th>
		<th>部门名称</th>
		<th>部门地址</th>
		<th>操作</th>
	</tr>
	<c:forEach items="${requestScope.list}" 
				  var="dept">
		<tr>
			<td>${dept.id}</td>
			<td>${dept.deptName}</td>
			<td>${dept.deptLoc}</td>
			<td>修改 删除</td>
		</tr>
	</c:forEach>
	
	</table>
	</body>
	</html>

##3.部门管理-删除
###1.删除部门-持久层

1.在接口中定义方法

	void deleteById(Integer id);

2.在映射文件中,定义delete语句

	<delete id="deleteById" >
		delete from t_dept
		where
			id=#{id}
	</delete>

测试：

###2.删除部门-业务层

1.接口定义方法

	void removeById(Integer id);

2.在实现类中,实现方法:

	方法功能:调用持久层方法

测试：

###3.删除部门-控制层
url 

	/removeDept.do
	参数列表:id
	请求方式:get
	响应方式:重定向 --------- getAll.do

	@RequestMapping("/removeDept.do")
	public String removeDept(Integer id){
		//1.调用业务层方法
		
		return "redirect:/dept/getAll.do";
	}

###4.删除部门-页面

	<a href="${}/dept/removeDept.do?id=${dept.id}">删除</a>

# 学子商城

1.用户管理

	1)注册
	2)登录
	3)修改个人信息(基本信息,密码)

2.地址管理
	1)添加地址
	2)显示地址
	3)管理地址(修改,删除)

3.商品管理
	1)商品分类
	2)商品展示
	3)商品详情

4.购物车

	1)添加购物车
	2)管理购物车商品

# 开发流程

1.项目需求---需求说明书(项目原型)

2.概要设计

3.详细设计

4.编码

5.测试

6.上线(产品交付)

7.维护

# ajax

##1.定义

1.ajax不是新的技术,是javascript,xml,XMLHttpRequest(xhr)的结合体,完成异步提交功能.

2.同步提交:用户提交数据后,当前页面不可以操作;页面响应成功,才可以操作页面.

3.异步提交:用户提交数据后,还可以在当前页面上操作;和服务器响应是否成功,没有太大影响.

4.特点:从服务器端获取一小部分数据.

##2.原理

用户请求交给xhr处理,然后xhr把请求提交给服务器,服务器响应数据给xhr,由xhr把数据接收回来,通过javascript设置数据到页面


##3.xhr的创建和函数属性

1.创建xhr对象

	function getXHR(){
		//1.定义变量
			var xhr;
		
		//2.判断
		if(window.XMLHttpRequest){
			//现阶段所有的浏览器都支持的对象
			xhr = new XMLHttpRequest();
		}else{
			//兼容IE5 IE6
			xhr = new ActiveXObject("Microsoft.XMLHttp");
		}
		return xhr;
	}

2.open()打开连接

	open("get/post","url",true);

3.send();发送请求

4.onreadystatechange:从创建xhr到响应数据成功,监听5中状态变化

5.readyState:获取状态
	
	0.未初始化
	1.初始化成功,发送请求
	2.接收数据
	3.解析数据
	4.响应成功

6.status:获取响应状态码:200  404  405 500

7.responseText:接收服务器 响应的数据


## 使用ajax-异步验证用户名
开发步骤

1.创建工程

	创建maven
	添加web.xml
	添加tomcat运行环境
	依赖jar  spring-webmvc  junit
	配置文件  spring-mvc.xml
	在web.xml中,配置前端控制器和Filter

2.页面register.jsp

	<html>
	<head>
	<title>Insert title here</title>
	</head>
	<body style="font-size:30px;">
	<form action="" >
	用户姓名:<input type="text" 
				name="name" id="name" 
				onblur="checkNameFun()"/>
	用户密码:<input type="password" name="pwd" id="pwd"/>
	<input type="submit" value="注册"/>
	</form>
	</body>
	<script type="text/javascript">
	//返回xhr对象
	function getXHR(){
		//1.定义变量
			var xhr;
		
		//2.判断
		if(window.XMLHttpRequest){
			//现阶段所有的浏览器都支持的对象
			xhr = new XMLHttpRequest();
		}else{
			//兼容IE5 IE6
			xhr = new ActiveXObject("Microsoft.XMLHttp");
		}
		return xhr;
	}

	//name文本框失去焦点事件处理函数
	function checkNameFun(){
		//1.创建xhr对象
		var xhr = getXHR();
		//2.监听事件,处理事件
		xhr.onreadystatechange=function(){
			//判断状态是第五种,并且响应的状态码是200
			if(xhr.readyState==4&&xhr.status==200){
				alert(xhr.responseText);
			}
		};
		//3.打开连接
		var nameValue = document.getElementById("name").value;
		xhr.open("get","${pageContext.request.contextPath}/user/checkName.do?name="+nameValue,true);
		//4.发送请求
		xhr.send();
	}
	</script>
	</html>


3.控制器

	/showRegister.do
	参数类表:无
	请求方式:get
	响应方式:转发

	//显示页面
	@RequestMapping("/showRegister.do")
	public String showRegister(){
		return "register";
	}

	//验证用户名
	//@ResponseBody表示不响应视图组件
	@RequestMapping("/checkName.do")
	@ResponseBody
	public String checkName(String name){
		System.out.println(name);
		if("admin".equals(name)){
			//0表示失败的状态码
			return "0";
		}else{
			//1表示成功的状态码
			return "1";
		}
	}


	
































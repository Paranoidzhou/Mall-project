##5.修改密码

###5.1 修改密码-持久层
###5.2 修改密码-业务层
###5.3 修改密码-控制器层

调整控制器

在controller包中,定义BaseController,类中定义方法getId(),完成获取id功能.

	public class BaseController{
		public Integer getId(HttpSession session){
			User user = (User)session.getAttribute("user");
			if(user == null){
				return null;
			}else{
				return user.getId();
			}
		}
	}

让UserController继承BaseController;调整updatePassword方法,重新获取id值

测试:(10分钟)

###5.4 修改密码-页面

1.在页面包含header.jsp

2.定义两个函数

	<script type="text/javascript">
	//验证密码长度
	function checkPasswordLength(pwd){
		return pwd.length>=6&&pwd.length<=9;
	}
	//验证新密码和确认密码是否相同
	function checkEqualsPwd(){
		//获取新密码框的值
		var newPwdValue = $("#newPwd").val();
		//获取确认新密码框的值
		var confirmPwdValue = 
			$("#confirmPwd").val();
		return newPwdValue==confirmPwdValue;
		
	}
	</script>

3.验证旧密码和新密码格式是否正确,确认密码和新密码是否一致

	//旧密码框长度的验证
	$("#oldPwd").blur(function(){
		//获取旧密码
		var oldPwdValue = $("#oldPwd").val();
		if(checkPasswordLength(oldPwdValue)){
			$("#oldPwdSpan").html("密码格式正确");
			$("#oldPwdSpan").css("color","green");
		}else{
			$("#oldPwdSpan").html("密码格式不正确");
			$("#oldPwdSpan").css("color","red");
		}
	});
	//新密码框长度的验证
	$("#newPwd").blur(function(){
		//获取旧密码
		var newPwdValue = $("#newPwd").val();
		if(checkPasswordLength(newPwdValue)){
			$("#newPwdSpan").html("密码格式正确");
			$("#newPwdSpan").css("color","green");
		}else{
			$("#newPwdSpan").html("密码格式不正确");
			$("#newPwdSpan").css("color","red");
		}
	});
	//新密码和确认新密码是否一致
	$("#confirmPwd").blur(function(){
		if(checkEqualsPwd()){
			$("#confirmPwdSpan").
					html("两次密码输入一致");
			$("#confirmPwdSpan").
					css("color","green");
		}else{
			$("#confirmPwdSpan").
					html("两次密码输入不一致");
			$("#confirmPwdSpan").
					css("color","red");
		}
	});

4.保存修改

	//保存修改
	function updatePassword(){
		var oldPwdValue = $("#oldPwd").val();
		var newPwdValue = $("#newPwd").val();
		if(checkPasswordLength(oldPwdValue)&&
			checkPasswordLength(newPwdValue)&&
			checkEqualsPwd()){
			$.ajax({
				url:"../user/updatePassword.do",
				data:"oldPwd="+oldPwdValue+
					  "&newPwd="+newPwdValue,
				type:"post",
				dataType:"json",
				success:function(obj){
					if(obj.state==0){
						alert(obj.message);
					}else{
						location="../user/showLogin.do";
					}
				}
			});
		}
	}

5.调整侧边栏

	<!-- 处理侧边栏 -->
	<script type="text/javascript">
	$(function(){
		//把所有的dd隐藏
	    $("#leftsidebar_box dd").hide();
		//设置账号管理的列表显示
	    $("#leftsidebar_box .count_managment dd").show();
	   //设置所有的图片为 myOrder2.png
		$("#leftsidebar_box dt img").attr("src","../images/myOrder/myOrder2.png");
      //设置账号管理的图片为myOrder1.png
		$("#leftsidebar_box .count_managment").find('img').attr("src","../images/myOrder/myOrder1.png");
       
	});
	</script>

6.把侧边栏截取成片段;在web文件夹中创建left.jsp文件,做成片段文件;在该文件中包含进来

	<!-- 左边栏-->
    <jsp:include page="left.jsp"></jsp:include>



##6.修改个人信息
### 6.1 修改个人信息-显示页面

在UserController类中定义方法,显示修改个人信息

	@RequestMapping("/showPersonInfo.do")
	public String showPersonInfo(){
		return "personPage";
	}

把personage.html修改为personPage.jsp

(5分钟)

调整页面

1.包含header.jsp

2.调整组件,显示数据

3.在personal_password页面,给`我的信息`和`安全管理`添加链接;在personPage页面,给`我的信息`和`安全管理`添加链接

4.把左边栏包含进来.

5.把左边栏中`我的信息`和`安全管理`添加链接

6.调整左边栏

###6.2 修改个人信息-持久层
###6.3 修改个人信息-业务层

1.在IUserService 接口中定义方法

	void updateUser(Integer id,String username,Integer gender,String phone,String email);

2.在UserService实现类中实现方法

	public void updateUser(Integer id, String username, Integer gender, String phone, String email) {
		User newUser = new User();
		newUser.setId(id);
		newUser.setGender(gender);
		newUser.setPhone(phone);
		newUser.setEmail(email);
		//根据id查询;返回user对象
		User user = userMapper.selectUserById(id);
		if(user==null){
			//如果user==null;抛出异常
			throw new UserNotFoundException("用户不存在");
		}else{
			//根据用户名查询;返回user1
			User user1 = 
					userMapper.
					selectUserByUsername(username);
			if(user1!=null){
				
				//当前的用户名就是登陆的用户名
				if(user1.getUsername().equals(
						user.getUsername())){
				}else{
					//否则抛出异常
					throw new 
					UsernameAlreadyExistException(
							"用户名已存在");
				}
			}else{
				//数据库中没有相同的用户名,
				//设置用户名为newUser的属性.
				newUser.setUsername(username);
			}
			//修改用户信息
			userMapper.updateUser(newUser);
			
		}
		
	}


测试:(20分种)

### 6.3 修改个人信息-控制器层

	/updateUser.do
	参数列表:session ,username,gender,phone,email
	请求方式:post
	响应方式:ResponseBody

在UserController类中定义方法

	@RequestMapping("/updateUser.do")
	@ResponseBody
	public ResponseResult<Void> updateUser(
					HttpSession session,String username,
					Integer gender,String phone,String email){
		//1.创建rr对象
		try{
			//调用业务层方法
			state = 1 ;message="修改成功"
		}catch(RuntimeException e){
			state = 0; message = e.getMassage();
		}
		return rr;
	}
(10分钟)

### 6.4 修改个人信息-页面

	//异步请求保存修改
	function saveUpdate(){
		$.ajax({
			url:"../user/updateUser.do",
			data:$("#form_update").serialize(),
			type:"post",
			dataType:"json",
			success:function(obj){
				if(obj.state==0){
					alert(obj.message);
				}else{
					alert(obj.message);
				}
			}
		});
		
	}

在IUserService接口中定义方法;实现类中实现方法

	User getUserById(Integer id);

	public User getUserById(Integer id) {
		
		return userMapper.selectUserById(id);
	}	

调整控制器代码
	
	在updateUser方法中添加以下代码

	//获取user对象
	User user = userService.getUserById(this.getId(session));
	/重新设置session的user对象
	session.setAttribute("user", user);
	

修改成功,重新跳转到personPage.jsp页面

	success:function(obj){
		if(obj.state==0){
			alert(obj.message);
		}else{
			//刷新页面
			location="../user/showPersonInfo.do";
			}
	}
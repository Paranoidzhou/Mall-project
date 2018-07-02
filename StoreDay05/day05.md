# 用户管理
##1.数据库
##2.注册
##3.登录
### 3.1 登录-持久层
### 3.2 登录-业务层
### 3.3 登录-控制器层

url:

	/showLogin.do     ---显示login.jsp页面
	参数列表:无
	请求方式:get
	响应方式:转发

	/login.do         ---实现登录
	参数列表:username,password,session
	请求方式:post
	响应方式:ResponseBody

在UserController类中,定义方法,显示login.jsp页面

	@
	public String showLogin(){
		return "login";
	}
	
把login.html改为login.jsp

测试:

在UserController中定义方法,完成登录功能

	@RequestMapping("/login.do")
	@ResponseBody
	public ResponseResult<Void> login(String username,String password,HttpSession session){
		//1.创建rr对象
		try{
			//2.调用业务层方法login(username,password);返回user对象;把user对象设置到session中.
			//3.state = 1 message = "登录成功"
		}catch(RuntimeException e){
			//4.state = 0 message = e.getMessage()
		}
		return rr;
	}

测试:(10分钟)

###3.4 页面

	$.ajax({
        	 url:"../user/login.do",
        	 data:"username="+$("#username").val()+
        	      "&password="+$("#password").val(),
       	 type:"post",
       	 dataType:"json",
       	 success:function(obj){
       		 if(obj.state==0){
       			 alert(obj.message);
       		 }else{
       			 Save();
       			 alert(obj.message);
       		 }
       	 }
        });

调整页面

1.注册成功之后,显示到login.jsp  location="../user/showLogin.do";

2.在登录页面给`新用户注册`添加链接 

	<a href="../user/showRegister.do">....</a>

3.在注册页面给`直接登录`添加链接

	<a href="../user/showLogin.do">....</a>

## 4.显示首页

新建MainController类,定义方法,显示首页
	
	@Controller
	@RequestMapping("/main")
	public class MainController{
		@RequestMapping("/showIndex.do")
		public String showIndex(){
			return "index";
		}
	}

把index.html改为index.jsp

测试:

调整页面

1.在❤的前边显示用户名

2.在login.jsp 登录成功之后,跳转到index.jsp

3.登录成功,显示退出,如果没有登录,显示登录

	<c:if test="${user==null}">
		<li><a>登录</a></li>
	</c:if>
	<c:if test="${user!=null}">
		<li><a>退出</a></li>
	</c:if>

4.session的处理
	
	在UserController类中定义方法,完成session失效

	@RequestMapping("/exit.do")
	public String exit(HttpSession session){
		//1.session 失效
		session.invalidate(); 
		//2.重定向到首页
		return "redirect:../main/showIndex.do";
	}

给`退出` 添加链接

	<a href="../user/exit.do">退出</a>

5.把头部分做成片段,header.jsp;哪个页面需要,就把这段代码包含进去

6.在UserController类中,定义方法,显示修改密码的页面.

	@RequestMapping("/showPassword.do")
	public String showPassword(){
		return "personal_password";
	}

把personal_password 改为 personal_password.jsp

(15分钟)

7.在header.jsp页面中,给用户名添加链接

## 5.修改密码

### 5.1 修改密码-持久层


1.在UserMapper接口中,定义方法,修改功能

	void updateUser(User user);
	User selectUserById(Integer id);

2.在UserMapper.xml中,定义`update`节点,完成修改用户

	<update id="updateUser" parameterType="xx.xx.User">
		update t_user
		<set>
			<if test="password!=null">
			   password = #{password},
			</if>
			<if test="username!=null">
			   username = #{username},
			</if>
			<if test="gender!=null">
			   gender = #{gender},
			</if>	
			<if test="email!=null">
			   email = #{email},
			</if>
			<if test="phone!=null">
			   phone = #{phone}
			</if>
		</set>
		where
			id = #{id}
	</update>

	<select id ="selectUserById" resultType="xx.xx.User">

		select ....
		from 
			t_user
		where 
			id = #{id}		

	</select>


测试:(20分钟) 16点15上课

### 5.2 修改密码-业务层

1.在IUserService接口中定义方法

	void changePassword(Integer id,String oldPwd,String newPwd);

2.在UserService实现类中实现方法

	public void changePassword(Integer id,String oldPwd,String newPwd){
		//1.调用持久层的方法selectUserById(id);返回user对象;
		//2.user==null;抛出异常UserNotFoundException;
		//3.user!=null;从user对象中获取password,和页面上的oldPwd比较
		//4.如果密码相同,调用持久层的方法,updateUser(user1),user1对象中封装id,和newPwd;
		//5.如果密码不相同,抛出异常PasswordNotMatchException;
	}

测试:(20分钟) 17点10分上课

### 5.3 修改密码-控制器层

	/updatePassword.do
	参数列表:session,oldPwd,newPwd
	请求方式:post
	响应方式:ResponseBody

在UserController 类中定义方法

	@RequestMapping("/updatePassword.do")
	@ResponseBody
	public ResponseResult<Void> updatePassword(HttpSession session,String oldPwd,String newPwd){
		//1.创建rr对象
		try{
			//id 从session中获取
		//2.调用业务层的方法changePassword(id,oldPwd,newPwd);
			state = 1;message ="修改密码成功"
		}catch(RuntimeException e){
			state = 0;message =e.getMessage();
		}
		return rr;
	}

测试:(20分钟)
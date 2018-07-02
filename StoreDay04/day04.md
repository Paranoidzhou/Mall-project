# 用户管理
## 1.设计数据库 t_user

## 2.用户管理-注册

### 2.1 注册-持久层

### 2.2 注册-业务层

### 2.3 邮箱,电话号码和用户名的验证

1.邮箱

持久层

业务层:

1) 在IUserService接口中定义方法,验证邮箱是否存在

	boolean checkEmail(String email);

2) 在UserService实现类中实现方法,如果邮箱存在,返回true,否则,返回false

	public boolean checkEmail(String email){
		//1.调用持久层的方法selectByEmail(email),返回int
		//2.如果返回值>0 ,return true;
		//3.否则,return false
	}

测试:(15分钟)

2.电话号码同邮箱

持久层

1) 在UserMapper中,定义方法

	Integer selectByPhone(String phone);

2) 在UserMapper.xml文件中定义select语句

	<select id="selectByPhone" resultType="xx.xx.Integer">
		select
			count(*)
		from 
			t_user
		where
			phone=#{phone}
	</select>

测试:

业务层:

1) 在IUserService 接口中定义方法

	boolean checkPhone(String phone);

2) 在UserService 实现类中,实现方法

	public boolean checkPhone(String phone){
		//同邮箱
	}

测试:(15-20分钟) 10点10分上课

3.用户名验证的业务层

1) 在IUserService 接口中定义方法

	boolean checkUsername(String username);

2) 在UserService实现类中,实现方法

	public boolean checkUsername(String username){
		//1.调用持久层方法:selectUserByUsername(username);返回user对象
		//2.user!=null return true;
		//3.user==null return false
	}

测试:(5分钟)

### 2.4 注册-控制器层

1.url

	/showRegister.do   --显示注册页面
	参数列表:无
	请求方式：get
	响应方式:转发

	/checkUsername.do  --异步验证用户名是否存在
	参数列表:username
	请求方式:get
	响应方式:ResponseBody

	/checkEmail.do  --异步验证邮箱是否存在
	参数列表:email
	请求方式:get
	响应方式:ResponseBody

	/checkPhone.do  --异步验证电弧号码是否存在
	参数列表:phone
	请求方式:get
	响应方式:ResponseBody

	/register.do  --异步提交注册
	参数列表:username,password,email,phone
	请求方式:post
	响应方式:ResponseBody

显示页面

2.定义控制器类UserController
	
	@Controller
	@RequestMapping("/user")
	public class UserController{
		@RequestMapping("/showRegister.do")
		public String showRegister(){
			return "register";
		}
	}

把register.html改register.jsp

测试:(10分钟)


3.在bean包中,定义通用的响应结果类类型ResponseResult

4.在UserController类中定义方法;异步验证用户名

	@RequestMapping("/checkUsername.do")
	@ResponseBody
	public ResponseResult<Void> checkUsername(String username){
		//1.创建rr对象
		//2.调用业务层的checkUsername(username);返回boolean b
		//3.b==true;  state = 0 ; message="用户名不可以使用"
		//4.b==false; state = 1 ; message="用户名可以使用"
		//5.return rr;
	}

在UserController类中定义方法;异步验证邮箱

	@
	@
	public ResponseResult<Void> checkEmail(String email){
		同上.
	}

异步验证电话号码同上

测试:(15分钟)

5.页面

	/**发起异步GET请求，询问服务器用户名是否已经存在**/
      
      $.ajax({
    	  url:"../user/checkUsername.do",
    	  data:"username="+$("#uname").val(),
    	  type:"get",
    	  dataType:"json",
    	  success:function(obj){
    		  //alert(obj.state+","+obj.message);
    		  //显示服务器的响应信息
    		  $("#unamespan").html(obj.message);
    		  if(obj.state==0){
    			  $("#unamespan").attr("class","msg-error");
    		  }else{
    			  $("#unamespan").attr("class","msg-success");
    		  }
    	  }
      });

	异步验证邮箱和电话号码页面同上.

提交注册控制器的方法
	
	@
	@
	public ResponseResult<Void> register(
		@RequestParam("uname") String username,
		@RequestParam("upwd") String password,
		String email,
		String phone){
		//1.创建rr对象
		//2.try{
			调用业务层方法register(user);
			state = 1 message = "注册成功"
		}catch(RuntimeException e){
			state = 0 message = e.getMessage()
		}
		//3.return rr;
	}
(10分钟)

页面:

	    //注册异步提交
	   	if(lengths==5){
	   		$.ajax({
	   			url:"../user/register.do",
	   			//serialize()提交表单
	   			data:$("#form-register").serialize(),
	   			type:"post",
	   			dataType:"json",
	   			success:function(obj){
	   				if(obj.state==0){
	   					//alert(obj.message);
	   					$("#unamespan").html(obj.message);
	   					$("#unamespan").attr("class","msg-error");
	   				}else{
	   					alert(obj.message);
	   					//...
	   				}
	   			}
	   		});
	   	}

##3. 登录

###3.1 登录-持久层
###3.2 登录-业务层

1.在IUserService接口定义方法

	User login(String username,String password);

2.在UserService实现类中实现方法

	在ex包中定义异常类UserNotFoundException;PasswordNotMatchException

	public User login(String username,String password){
		//1.调用持久层方法selectUserByUsername(username);
		//返回user对象

		//2.user==null,抛出异常,UserNotFoundException;
		//3.user!=null,从user对象中获取password,和参数列表中的password比较

		//4.密码比较,两种情况:密码相同;返回 user对象;如果密码不相同,抛出异常PasswordNotMatchException
	}

测试:(15分钟)




















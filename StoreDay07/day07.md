### 5.5 上传图片

1.上传图片

1)在spring-mvc.xml配置上传图片的组件

		<!-- 配置上传文件的组件 
		  id的值必须为"multipartResolver" 
	-->
	<bean id="multipartResolver" 
	class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
		<!-- 配置最大的上传文件10兆 -->
		<property name="maxUploadSize" value="10000000"/>
		<!-- 设置默认的编码格式 -->
		<property name="defaultEncoding" value="utf-8"/>
	</bean>

2)页面

	//上传头像图片
	function getImage(){
		//创建一个设置表单数据的对象
		var formData = new FormData();
		//获取上传文件的文件对象
		var file = 
			document.getElementById("iconPic").files[0];
		formData.append("file",file);
		
		$.ajax({
			url:"../user/upload.do",
			data:formData,
			type:"post",
			dataType:"json",
			//不处理数据
			contentType:false,
			processData:false,
			success:function(obj){
				alert(obj.messge);
				//创建url对象
				var url = window.URL.createObjectURL(file);
				icon.src=url;
			}
			
		});
	}

3)控制器

	//上传图片
	@RequestMapping("/upload.do")
	@ResponseBody
	public ResponseResult<Void> upload(
	@RequestParam("file") MultipartFile file,
	HttpSession session) throws Exception{
		ResponseResult<Void> rr = 
				new ResponseResult<Void>();
		//获取服务器的真实路径
		String path = 
				session.getServletContext().
				getRealPath("/");
		System.out.println(path);
		//上传
		file.transferTo(
				new File(path,"/upload/"+file.getOriginalFilename()));
		
		
		return rr;
		
	}

2.保存图片

2.1)在UserMapper接口中定义方法

	void updateImage(@Param("image") String image,@Param("id") Integer id);

2.2)在UserMapper.xml文件中,编写update语句

	<update id="updateImage">
		update t_user
		set
			image = #{image}
		where
			id=#{id}
	</update>

测试:(10分钟)

业务层:

在IUserService中定义方法 ;

	void updateImage(String image,Integer id);

在UserService类中实现方法

	public void updateImage(String image,Integer id){
		//调用持久层方法;
	}

测试:(5分钟)

控制器层:

	//保存头像
		userService.updateImage(
				"/upload/"+file.getOriginalFilename(), 
				this.getId(session));
		//更新session中的user对象
		session.setAttribute(
				"user",
				userService.
				getUserById(this.getId(session)));

调整页面

	 <c:if test="${user.image==null}">
	                <img src="../images/personage/touxiang.png" alt="" id="icon" width="50px" height="50px"/>
	                </c:if>
	                <c:if test="${user.image!=null}">
	                <img src="..${user.image}" alt="" id="icon" width="50px" height="50px"/>
	                </c:if>
	                

## 登录拦截器

1.定义拦截器
	
	//登录拦截器
	public class LoginInterceptor 
			 implements HandlerInterceptor{
	//在controller方法之前执行
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//1.判断session中有没有user对象
		HttpSession session = 
				request.getSession();
		Object obj = session.getAttribute("user");		
		//2.如果有,return true;
		if(obj!=null){
			return true;
		}else{
		//3.如果没有,重定向的到登录页面
			String url = 
					request.getContextPath()+
					"/user/showLogin.do";
			response.sendRedirect(url);
			return false;
		}
	}
	//控制器方法执行完成,但是没有响应视图
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}
	//视图响应成功之后执行.
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	}


2.配置拦截器

		 	 <mvc:interceptors>
	 	 	<mvc:interceptor>
	 	 		<mvc:mapping path="/user/*"/>
	 	 		<mvc:exclude-mapping 
	 	 			path="/user/showLogin.do"/>
	 	 		<mvc:exclude-mapping 
	 	 			path="/user/login.do"/>
	 	 		<mvc:exclude-mapping 
	 	 			path="/user/register.do"/>
	 	 		<mvc:exclude-mapping 
	 	 			path="/user/showRegister.do"/>
	 	 		<mvc:exclude-mapping 
	 	 			path="/user/checkUsername.do"/>
	 	 		<mvc:exclude-mapping 
	 	 			path="/user/checkPhone.do"/>
	 	 		<mvc:exclude-mapping 
	 	 			path="/user/checkEmail.do"/>
	 	 		<bean class=
	 	 		"cn.tedu.store.interceptor.LoginInterceptor"/>
	 	 	</mvc:interceptor>
	 	 </mvc:interceptors>

# 2.地址管理

## 设计表

	create table t_address(
		id int auto_increment primary key,
		uid int not null,
		recv_username varchar(50),
		recv_provinceCode varchar(6),
		recv_cityCode varchar(6),
		recv_areaCode varchar(6),
		recv_address varchar(100),
		recv_district varchar(100),
		recv_phone  varchar(32),
		recv_tel varchar(32),
		recv_zip varchar(6),
		recv_tag varchar(6),
		is_default int(1),
		created_user varchar(50),
		created_time date,
		modified_user varchar(50),
		modified_time date	
	)default charset=utf8

##2.1 显示地址页面

新建AddressController,定义showAddress方法

	@Controller
	@RequestMapping("/address")
	public class AddressController extends BaseController{
		@RequestMapping("/showAddress.do")
		public String showAddress(){
			return "addressAdmin";
		}
	}

把addressAdmin.html改为addressAdmin.jsp
(5分钟)

调整页面

1.包含header.jsp

2.包含left.jsp

3.在左边栏中给`地址管理`添加链接

4.添加拦截器代码

	<mvc:mapping path="/address/*"/>

5.在ftp上下载`省市区数据表sql代码.zip`文件,解压到`省市区数据表sql代码`文件夹,在mysql的控制器端执行: source e:/t_dict.sql

##2.2显示省市区类表

###2.2.1.显示省列表

1.持久层

新建Province实体类
	
	public class Province{
		private Integer id;
		private String provinceCode;
		private String provinceName;
		//......
	}
	

1)新建DictMapper接口,在接口中定义方法

	public interface DictMapper{
		List<Province> selectProvince();
	}

2)新建DictMapper.xml(拷贝,修改名称,删除),在`mapper`节点中定义`select`节点,完成sql的编写

	<select id="selectProvince" resultType="xx.xx.Province">
		select
			id,province_code provinceCode,
			province_name provinceName
		from
			t_dict_provinces
	</select>

测试:(10分钟)

2.业务层

1)新建IDictService接口,定义方法

	public interface IDictService{
		List<Province> getProvice();
	}

2)新建DictService实现类,把方法给实现了

	@Service
	public class DictService{
		@Resource
		private DictMapper dictMapper;
		public List<Province> getProvice(){
			//返回持久的方法的返回值
		}
	}

测试:(20分钟回来上课) 17点15上课

3.控制器层

 	/dict/getProvince.do
	请求参数:无
	请求方式:get
	响应方式:ResponseBody

新建DictController类,定义getProvince方法

	@Controller
	@RequestMapping("/dict")
	public class DictController{

		@Resource
		private IDictService dictService;
		//显示省信息
		@RequestMapping("/getProvince.do")
		@ResponseBody
		public ResponseResult<List<Province>> getProvince(){
			//1.创建rr对象
			//2.调用业务层的方法;返回list集合
			//3.把list设置到rr对象中.
			//4.state = 1;message = "成功"
			return rr;
		} 
	}

测试:(10分钟)


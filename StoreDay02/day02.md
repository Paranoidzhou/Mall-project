#[回顾]

1.ajax:由javascript,xml,XMLHttpRequest,和在一起,叫ajax.实现请求异步提交

2.异步请求:在页面发送请求时,当前页面还可以继续使用,响应的数据什么时候回来,显示到页面,不影响操作页面.

3.原理:发送请求,交给ajax的xhr对象,由xhr对象发送请求给服务器;服务器响应的数据给xhr,由xhr结合javascript,把数据设置到页面上.

4.xhr对象的创建和函数和属性

1) var xhr = new XMLHttpRequest();

2) xhr.open("get/post","url",true);

3) xhr.send();

4) xhr.onreadystatechange=function(){

5) xhr.readyState:0-4

6) xhr.status
		if(xhr.readyState==4&&xhr.status==200){
		
7) xhr.responseText
			alert(xhr.responseText);

		}
	}

## 二级联动菜单-post请求

1.页面

	省:<select name="province" id="province" 
			  onchange="getCityFun(this.value)">
	<option>...请选择...</option>
	<option value="1">河北省</option>
	<option value="2">辽宁省</option>
	</select>
	城市:<select name="city" id="city">
		<option>...请选择...</option>
	</select>

2.异步请求的函数定义


	//获取城市列表
	
	function getCityFun(provinceCode){
		//1.获取xhr对象
		var xhr = getXHR();
		//2.定义处理事件的函数
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4&&xhr.status==200){
				var cityValue = xhr.responseText;
				//11,石家庄市;12,廊坊市
				var city = cityValue.split(";");
				//["11,石家庄市","12,廊坊市"]
				//获取city节点
				var cityNode = document.getElementById("city");
				cityNode.innerHTML="<option>..请选择..</option>";
				for(i=0;i<city.length;i++){
					var cv = city[i];//11,石家庄市
					var vf = cv.split(",");
					var code = vf[0];//11
					var value = vf[1]//石家庄市
					//创建Option对象;
					//第一个参数表示显示的城市名称
					//第二个参数表示value的值,城市编号
					var op = new Option(value,code);
					//追加子节点
					cityNode.appendChild(op);
				}
			}
			
		}
		//3.打开连接(创建url)
		xhr.open("post","${pageContext.request.contextPath}/user/getCity.do",true);
		//post请求必须要设置一个请求头,
		//位置send()之前
		xhr.setRequestHeader("content-type","application/x-www-form-urlencoded");
		//4.发送请求
		xhr.send("provinceCode="+provinceCode);
	}

3.控制器的方法

	//获取城市列表
	@RequestMapping("/getCity.do")
	@ResponseBody
	public String getCity(String provinceCode){
		if(provinceCode.equals("1")){
			return "11,石家庄市;12,廊坊市";
		}else{
			return "21,沈阳市;22,大连市";
		}
	}


# Json

1.客户端和服务器端传递数据的一种解决方案

2.语法:key-value

	var jsonObject = {
						"state":0,
						"message":"失败"
					};

	alert(jsonObject.state);

3.好处:易于编写,易于阅读,易于解析

## 定义和使用json对象-客户端

1.一个k-v的json对象

	var obj = {"state":1};

	alert(obj.state);

2.多个k-v的json对象的定义和访问

	var obj = {
				"state":0,
				"message":"失败"
	};
		
	alert(obj.state+","+obj.message);

3.多个k-v的json对象的定义和访问(value的值是对象)

	var obj = {
				"state":1,
				"message":"成功",
				"data":{
					"name":"王影",
					"age":16
				}
		};
		alert(obj.data.name+","+obj.data.age);

4.json数组的定义和访问

	var obj = [
		           {"code":11,"name":"石家庄市"},
		           {"code":12,"name":"廊坊市"}		           
		           ];
		for(i = 0;i<obj.length;i++){
			alert(obj[i].code+","+obj[i].name);
		}

5.多个k-v的json对象的定义和访问(value的值是数组)

	var obj = {     
				"state":1,
   			    "message":"成功",
				"data":[
							{"code":11,"name":"石家庄市"},
							{"code":12,"name":"廊坊市"}		        
				        ]
		};
		for(i=0;i<obj.data.length;i++){
			alert(obj.data[i].code+","+obj.data[i].name);
		}


## java对象转换为json字符串-服务器端

1.通用的工具类的定义

	public class ResponseResult<T> {
	private  Integer state;
	private String message;
	private T data;
	public ResponseResult() {
		
	}
	public ResponseResult(Integer state,
						  String message) {
		super();
		this.state = state;
		this.message = message;
	}
	public ResponseResult(Integer state, String message, T data) {
		super();
		this.state = state;
		this.message = message;
		this.data = data;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	

	}

2.依赖jar包

	jackson*3

		<!-- java对象转换为json字符串 -->
		<dependency>
		  <groupId>com.fasterxml.jackson.core</groupId>
		  <artifactId>jackson-databind</artifactId>
		  <version>2.8.3</version>
		</dependency>
		<dependency>
		  <groupId>com.fasterxml.jackson.core</groupId>
		  <artifactId>jackson-core</artifactId>
		  <version>2.8.3</version>
		</dependency>
		<dependency>
		  <groupId>com.fasterxml.jackson.core</groupId>
		  <artifactId>jackson-annotations</artifactId>
		  <version>2.8.3</version>
		</dependency>


3.定义控制器类
	
	@Controller
	@RequestMapping("/user")
	public class UserController {
	
	//返回状态码和信息内容
	@RequestMapping("/test1.do")
	@ResponseBody
	public ResponseResult<Void> test1(){
		ResponseResult<Void> rr =
			new ResponseResult<Void>(1,"成功");
		return rr;
	}
	//返回包含实体类对象的ResponseResult对象
	@RequestMapping("/test2.do")
	@ResponseBody
	public ResponseResult<User> test2(){
		ResponseResult<User> rr = 
			new ResponseResult<User>(1,"成功");
		User user = new User();
		user.setName("王影");
		user.setEmail("wangying@tedu.cn");
		user.setPhone("13800138000");
		
		rr.setData(user);
		
		return rr;
	}
	//响应ResponseResult对象中包含集合类型的数据
	@RequestMapping("/test3.do")
	@ResponseBody
	public ResponseResult<List<User>> test3(){
		ResponseResult<List<User>> rr = 
			new ResponseResult<List<User>>(1,"成功");
		User u1 = new User();
		u1.setName("admin");
		u1.setEmail("admin@tedu.cn");
		u1.setPhone("10086");
		
		User u2 = new User();
		u2.setName("manager");
		u2.setEmail("manager@tedu.cn");
		u2.setPhone("10086");
		
		List<User> list = new ArrayList<User>();
		list.add(u1);
		list.add(u2);
		
		rr.setData(list);
		
		return rr;
		
	}
	}
	

## 练习 -显示用户的信息到页面上

## 练习 -二级联动菜单

1.定义City类
	
	public class City{
		private String code;
		private String name;

	}

2.在控制器中定义方法

	@
	@
	public ResponseResult<List<City>> getCity(String provicneCode){
		//1.创建rr对象
		//2.判断provinceCode
		//3.创建City对象,把对象添加到list
		//4.把list设置到rr对象中
		//5.return rr;
	}

3.页面-异步请求的for循环

	var obj = JSON.parse(xhr.responseText);
	for(i = 0;i<obj.data.length;i++){
		var op = new Option(obj.data[i].name,obj.data[i].code);
	}
	

	




























#地址管理
##1.添加地址
###1.1 显示页面
###1.2 显示省市区列表
###1.2.1 显示省列表
1.持久层

2.业务层

3.控制器层

4.页面-在下拉列表中显示省信息

	//显示省列表信息的函数
	function getProvince(){
		$.ajax({
			url:"../dict/getProvince.do",
			data:"",
			type:"get",
			dataType:"json",
			success:function(obj){
				if(obj.state==1){
					$("#receiverState").
						html("<option>...请选择...</option>");
					for(i=0;i<obj.data.length;i++){
						var op = new Option(
								obj.data[i].provinceName,
								obj.data[i].provinceCode);
						$("#receiverState").append(op);
					}
				}
			}
		});
	}

###1.2.2显示城市列表

1.持久层

新建City实体类

	public class City{
		private Integer id;
		private String provinceCode;
		private String cityCode;
		private String cityName;
		//...
	}

(5分钟)	

1)在DictMapper接口中定义方法

	List<City> selectCity(String provinceCode);

2)在DictMapper.xml中定义select节点,完成sql语句

	<select id="selectCity" resultType="xx.xx.City">
		select
			id,
			province_code provinceCode,
			city_code cityCode,
			city_name cityName
		from
			t_dict_cities
		where
			province_code = #{provinceCode}
	</select>

测试：（10分钟）

2.业务层

1)在IDictService接口中定义方法

	List<City> getCity(String provinceCode);

2)在DictService实现类中实现方法,返回持久层的方法的返回值

3.控制器层

	/getCity.do
	参数列表:provinceCode
	请求方式:get
	响应方式:ResponseBody

	@
	@
	public ResponseResult<List<City>> getCity(String provinceCode){
		//1.创建rr对象
		//2.调用业务层方法;返回list
		//3.把list设置到data属性
		//4.state = 1;message="成功"
		return rr;
	}

测试:(15分钟)

4.页面:在城市列表中显示城市信息

	//显示城市列表信息的函数
	function getCity(provinceCode){
		$.ajax({
			url:"../dict/getCity.do",
			data:"provinceCode="+provinceCode,
			type:"get",
			dataType:"json",
			success:function(obj){
				$("#receiverCity").html(
					"<option>...请选择...</option>");
				for(i=0;i<obj.data.length;i++){
					var op = new Option(
							obj.data[i].cityName,
							obj.data[i].cityCode);
					$("#receiverCity").append(op);
				}
			}
		});
	}

###1.2.3区县信息

1.持久层

新建Area实体类
	
	public class Area{
		private Integer id;
		private String cityCode;
		private String areaCode;
		private String areaName;
		//..
	}

1)在DictMapper接口中定义方法

	List<Area> getArea(String cityCode);

2)在DictMapper.xml文件中,定义select节点,编写sql语句

	<select id="getArea" resultType="xx.xx.Area">
		select
			id,
			city_code cityCode,
			area_code areaCode,
			area_name areaName
		from 
			t_dict_areas
		where
			city_code=＃{cityCode}
			
	</select>

测试：

2.业务层
　
１）在IDictService接口中定义方法

	List<Area> getArea(String cityCode);

２）在DictService实现类中,实现方法;功能:把持久层的方法的返回值 返回

3.控制层

	@
	@
	public ResponseResult<List<Area>> getArea(String cityCode){
		//1.创建rr对象
		//2.调用业务层方法,返回list
		//3.把list集合设置到rr对象
		//4.state = 1;message ="成功"
		return rr;
	}

测试:

4.页面

1)在city的下拉列表中定义onchange=getArea(this.value)

2)定义getArea(cityCode)函数,实现异步提交

(20分钟)

省市区函数的调整
	
	getProvince(provinceCode,cityCode,areaCode);
	getCity(provinceCode,cityCode,areaCode);
	getArea(cityCode,areaCode);

	三个函数的调用
	在getProvince(provinceCode,cityCode,areaCode)的异步请求之后调用getCity(provinceCode,cityCode,areaCode);在 getCity异步请求之后调用getArea(cityCode,areaCode);

	在省的onchange事件处理中调用函数getCity(this.value,-1,-1);
	在城市的onchange事件处理中调用函数getArea(this.value,-1);
	

页面的侧边栏调整

	<!-- 处理侧边栏 -->
	<script type="text/javascript">
	$(function(){
		//把所有的dd隐藏
	    $("#leftsidebar_box dd").hide();
		//设置账号管理的列表显示
	    $("#leftsidebar_box .address dd").show();
	   //设置所有的图片为 myOrder2.png
		$("#leftsidebar_box dt img").attr("src","../images/myOrder/myOrder2.png");
      //设置账号管理的图片为myOrder1.png
		$("#leftsidebar_box .address").find('img').attr("src","../images/myOrder/myOrder1.png");
       
	});
	</script>

通过code获取name

1)在DictMapper接口中定义方法

	String selectProvinceNameByCode(String provinceCode);
	String selectCityNameByCode(String cityCode);
	String selectAreaNameByCode(String areaCode);

2)在DictMapper.xml文件中定义3个select语句

	<select id="selectProvinceNameByCode" resultType="java.lang.String">
		select 
			province_name
		from 
			t_dict_provinces
		where 
			province_code = #{provinceCode}
	</select>

测试:(15分钟)

### 1.3 添加收货人地址-持久层

新建Address实体类

	public class Address{
		private Integer id;
		private Integer uid;
		private String recvUsername;
		private String recvProvinceCode;
		private String recvCityCode;
		private String recvAreaCode;
		private String recvAddress;
		private String recvDistrict;
		private String recvPhone;
		private String recvTel;
		private String recvZip;
		private String recvTag;
		private Integer isDefault;
		private String createdUser;
		private Date createdTime;
		private String modifiedUser;
		private Date modifiedTime;
	}

1)新建AddressMapper接口,在接口中定义方法

	public interface AddressMapper{
		void insertAddress(Address address);
		List<Address> selectAddressByUid(Integer uid);
	}

2)新建AddressMapper.xml文件(拷贝,改名,删除),定义insert节点,编写insert语句

	<insert id="insertAddress" parameterType="xx.xx.Address">
		
		insert into t_address(
			uid,recv_username,
			recv_provinceCode,recv_cityCode,recv_areaCode,
			recv_address,recv_district,
			recv_phone,recv_tel,recv_zip,
			recv_tag,is_default,
			created_user,created_time,
			modified_user,modified_time
		)values(
			#{uid},#{recvUsername},
		#{recvProvinceCode},#{recvCityCode},#{recvAreaCode},
			#{recvAddress},#{recvDistrict},
			#{recvPhone},#{recvTel},#{recvZip},
			#{recvTag},#{isDefault},
			#{createdUser},#{createdTime},
			#{modifiedUser},#{modifiedTime}
		)
	</insert>

	<select id="selectAddressByUid" resultType="xx.Address">
		select 
			id,uid
			recv_username recvUsername,
			recv_provinceCode recvProvinceCode,
			recv_cityCode recvCityCode,
			recv_areaCode recvAreaCode,
			recv_address recvAddress,
			recv_district recvDistrict,
			recv_phone recvPhone,
			recv_tel recvTel,
			recv_zip recvZip,
			recv_tag recvTal,
			is_default isDefault,
			created_user createdUser,
			created_time createdTime,
			modified_user modifieddUser,
			modified_time modifiedTime
		from
			t_address
		where
			uid=#{uid}
			
		
	</select>
测试:(15分钟)17:05上课


	

	
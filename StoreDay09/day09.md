#地址管理
## 1.添加地址
###1.1 显示地址页面
###1.2 显示省市区列表
###1.3 添加地址-持久层
###1.4 添加地址-业务层

1.新建IAddressService接口,定义方法

	public interface IAddressService{
		void addAddress(Address address);
	}

2.新建接口的实现类,AddressService,实现方法

	@Service
	public class AddressService implements IAddressService{
		@Resource
		private AddressMapper addressMapper;
		@Resource
		private DictMapper dictMapper;
		public void addAddress(Address address){
			//1.设置recvDeistrict值

			调用dictMapper接口中的方法,通过code获取name,把3个name用+连接成一个字符串,把该字符串设置到recvDeistrict中.

			dictMapper.selectProvinceNameByCode(address.getRecvProvinceCode);

			2.给isDefault属性设置值
			0表示非默认
			1表示默认
			调用addressMapper.selectAddressByUid(address.getUid());返回list;
			list.size()>0  isDefault = 0
			list.size()=0  isDefault = 1

			3.addressMapper.insertAddress(address);
		}
	}

测试:(10分钟)


###1.5 添加地址-控制器层

	/addAddress.do
	参数列表:session(uid) + 9个
	请求方式:post
	响应方式:ResponseBody

在AddressController类中定义方法

	@Reource
	private IAddressService addressService;	

	@RequestMapping("/addAddress.do")
	@ResponseBody
	public ResponseResult<Void> addAddress(
		HttpSession session,
        @RequestParam("receiverName") String recvUsername,
		@RequestParam("receiverState") String recvProviceCode,
		@RequestParam("receiverCity") String recvCityCode,
		@RequestParam("receiverDistrict") String recvAreaCode,
		@RequestParam("receiverAddress") String recvAddress,
		@RequestParam("receiverMobile") String recvPhone,
		@RequestParam("receiverPhone") String recvTel,
		@RequestParam("receiverZip") String recvZip,
		@RequestParam("addressName") String recvTag
	){

		//1.创建rr对象
		//2.调用业务层方法addAddress(address);
		//3.state = 1 message = "添加成功"
		return rr;
	}

(10分钟)


页面-控制器


###1.6 添加地址-页面

在person.js的43行左右,填写一步请求的代码

			$.ajax({
			url:"../address/addAddress.do",
			data:$("#form_address").serialize(),
			type:"post",
			dataType:"json",
			success:function(obj){
				alert(obj.message);
				//1.清空文本框(6个框)
				$("#receiverName").val("");
				$("#receiverAddress").val("");
				$("#receiverMobile").val("");
				$("#receiverPhone").val("");
				$("#receiverZip").val("");
				$("#addressName").val("");
				//2.把省市区列表设置为初始状态
				getProvince(-1,-1,-1);
			}
			
		});

##2.显示地址

###2.1显示地址-持久层

###2.2显示地址-业务层

1.在IAddressService接口中定义方法

	List<Address> getAddressByUid(Integer uid);

2.在AddressService实现类中实现方法;方法功能:返回持久层方法的list.

###2.3显示地址-控制器

	/getAddressByUid.do
	参数列表:session(uid)
	请求方式:get
	响应方式:ResponseBody
	@
	@
	public ResponseResult<List<Address>> getAddressByUid(HttpSession   session){
		//1.创建rr对象
		//2.调用业务层方法;返回list,把集合设置到rr对象中.
		//3.state = 1 ;message="成功";
		return rr;
	}	

测试:(15分钟)

###2.4显示地址-页面

	//显示收货人地址
	function getAllAddress(){
		$.ajax({
			url:"../address/getAddressByUid.do",
			data:"",
			type:"get",
			dataType:"json",
			success:function(obj){
				if(obj.state==1){
					for(i=0;i<obj.data.length;i++){
						//1.默认地址
						if(obj.data[i].isDefault==1){
							var str1 = '<div class="aim_content_one aim_active">'+
		                    '<span class="dzmc dzmc_active">'+obj.data[i].recvTag+'</span>'+
		                    '<span class="dzxm dzxm_normal">'+obj.data[i].recvUsername+'</span>'+
		                    '<span class="dzxq dzxq_normal">'+obj.data[i].recvDistrict+
		                    												obj.data[i].recvAddress+'</span>'+
		                    '<span class="lxdh lxdh_normal">'+obj.data[i].recvPhone+'</span>'+
		                    '<span class="operation operation_normal">'+
		                    	'<span class="aco_change">修改</span>|<span class="aco_delete">删除</span>'+
		                    '</span>'+
		                    '<span class="swmr swmr_normal"></span>'+
		                '</div>';
		                $(".address_information_manage").
		                		append(str1);
						}else{
						//2.非默认地址
							var str2='<div class="aim_content_two">'+
		                    '<span class="dzmc dzmc_normal">'+obj.data[i].recvTag+'</span>'+
		                    '<span class="dzxm dzxm_normal">'+obj.data[i].recvUsername+'</span>'+
		                    '<span class="dzxq dzxq_normal">'+obj.data[i].recvDistrict+
																				obj.data[i].recvAddress+'</span>'+
		                    '<span class="lxdh lxdh_normal">'+obj.data[i].recvPhone+'</span>'+
		                    '<span class="operation operation_normal">'+
		                    	'<span class="aco_change">修改</span>|<span class="aco_delete">删除</span>'+
		                    '</span>'+
		                    '<span class="swmr swmr_normal">设为默认</span>'+
		                '</div>';
							 $(".address_information_manage").
		                		append(str2);
						}
						
					}
					//设置为默认事件
					$(".swmr_normal").click(function(){
						setDefault(this);
					})
				}
			}
		});
		
	}


##3.设置为默认

###3.1设置为默认-持久层

1.在AddressMapper接口中定义方法

	//把所有的地址的is_default设置为0
	Integer updateCancel(Integer uid);

	//把指定id的地址的is_default设置为1
	Integer updateDefault(Integer id);

2.在AddressMapper.xml文件中定义两个update节点,编写update语句

	<update id="updateCancel">
		update t_address
		set
			is_default=0
		where
			uid=#{uid}
	</update>

	<update id="updateDefault">
		update t_address
		set
			is_default=1
		where
			id=#{id}
	</update>

测试:(10分钟)

###3.2设置为默认-业务层

1.在IAddressService接口中定义方法

	void setDefault(Integer uid,Integer id);

2.AddressService类中实现方法

	public void setDefault(Integer uid,Integer id){
		//1.调用updateCancel方法,返回int的值n1
		如果n1==0,抛出异常RuntimeException("修改失败");
		//2.调用updateDefault()方法,返回int的值n2
		如果n2==0,抛出异常RuntimeException("修改失败");
	}

测试:

###3.3设置为默认-控制器层

	/setDefault.do
	参数列表:session(uid) ,id
	请求方式:get
	响应方式:ResposenBody

	@
	@
	public ResponseResult<Void> setDefault(HttpSession session,Integer id){
		//1.创建rr对象
		try{
		//2.调用业务层方法
			state = 1;message ="";
		}catch(){
			state = 0;message=e.getMessage();
		}
		return rr;
	}

测试:(20分钟)17点10分上课

###3.4设置为默认-页面

	//设置为默认事件
					$(".swmr_normal").click(function(){
						//设置默认(数据库)
						$.ajax({
							url:"../address/setDefault.do",
							data:"id="+$(this).attr("id"),
							type:"get",
							dataType:"json",
							success:function(){
								
							}
						});
						//设置默认(页面)
						setDefault(this);
					})
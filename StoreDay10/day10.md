#地址管理

##1.添加收货人地址

##2.显示收货人地址

##3.修改收货人地址

###3.1 回显收货人地址

1.持久层

1)在 AddressMapper接口中定义方法,完成根据id查询收货人信息

	Address selectAddressById(Integer id);

2)在AddressMapper.xml文件中,编写sql语句

	<select id="selectAddressById" resultType="xx.xx.Address">

		select 
			id,uid,
			.....
		from
			t_address
		where
			id=#{id}
		
	</select>

2.业务层

1)在IAddressService接口中定义方法

	Address getAddressById(Integer id)
	
2)在AddressService实现类中,实现方法是,方法的功能:返回持久层的对象
(15分钟)	

3.控制器层

	/goUpdate.do
	参数列表:id
	请求方式:get
	响应方式:ResponseBody

    public ResponseResult<Address> goUpdate(Integer id){
		//1.创建rr对象
		//2.调用业务层方法;返回address对象,把address设置到rr对象中
		//3.state = 1;message ="成功";
		return rr;
	}

测试：１０点１５上课 

4.页面

	onclick="goUpdate('+obj.data[i].id+')"

	//显示修改页面
	function goUpdate(id){
		//1.发送异步请求
		//2.请求的响应成功;
			//6个框显示内容  val()
			//下拉列表
		$.ajax({
			url:"../address/goUpdate.do",
			data:"id="+id,
			type:"get",
			dataType:"json",
			success:function(obj){
				if(obj.state==1){
					//1.显示文本框内容(6个框)
					$("#receiverName").val(obj.data.recvUsername);
					$("#receiverAddress").val(obj.data.recvAddress);
					$("#receiverMobile").val(obj.data.recvPhone);
					$("#receiverPhone").val(obj.data.recvTel);
					$("#receiverZip").val(obj.data.recvZip);
					$("#addressName").val(obj.data.recvTag);
					//2.显示省市区列表
					getProvince(obj.data.recvProvinceCode,
									obj.data.recvCityCode,
									obj.data.recvAreaCode);
					//修改div的字符串内容
					$(".save_recipient").html("修改");
				}
			}
		});
	}


###3.2 修改收货人地址

1.持久层

1)在AddressMapper接口中,定义方法

	void updateAddress(Address address);

2)在AddressMapper.xml文件中,编写sql语句

	<update id="updateAddress" parameterType="xx.xx.Address">
		update t_address

		set
			recv_username =#{recvUsername},
			recv_provinceCode=#{recvProvinceCode},
			recv_cityCode = #{recvCityCode},
			recv_areaCode = #{recvAreaCode},
			recv_address = #{recvAddress},
			recv_district = #{recvDistrict},
			recv_phone = #{recvPhone},
			recv_tel = #{recvTel},
			recv_zip = #{recvZip},
			recv_tag = #{recvTag}
		where
			id = #{id}
		
	</update>

(10分钟)
2.业务层

1)在IAddressService中定义方法,
	
	void updAddress(Address address);

2)在AddressService实现类中实现方法;方法的功能:把省市区通过持久层的3个方法,得到3个name,把name用+连接,设置到address对象中;调用持久层方法,完成地址修改.

测试:(10分钟)

3.控制器层

	定义参数类:AddressParam
	public class AddressParam{
		7个文本框,3个列表框
	}

	/updateAddress.do
	参数列表:AddressParam ap
	请求方式:post
	响应方式:ResposeBody

	//修改地址信息
	@RequestMapping("/updateAddress.do")
	@ResponseBody
	public ResponseResult<Void> updateAddress(
			AddressParam ap){
		ResponseResult<Void> rr = 
				new ResponseResult<Void>();
		Address address = new Address();
		address.setId(ap.getId());
		address.setRecvUsername(ap.getReceiverName());
		address.setRecvProvinceCode(
				ap.getReceiverState());
		address.setRecvCityCode(
				ap.getReceiverCity());
		address.setRecvAreaCode(
				ap.getReceiverDistrict());
		address.setRecvAddress(
				ap.getReceiverAddress());
		address.setRecvPhone(
				ap.getReceiverMobile());
		address.setRecvTel(
				ap.getReceiverPhone());
		address.setRecvZip(ap.getReceiverZip());
		address.setRecvTag(ap.getAddressName());
		addressService.updAddress(address);
		
		return rr;
	}

4.页面

	function initAddress(){
	//1.清空文本框(6个框)
	$("#receiverName").val("");
	$("#receiverAddress").val("");
	$("#receiverMobile").val("");
	$("#receiverPhone").val("");
	$("#receiverZip").val("");
	$("#addressName").val("");
	//2.把省市区列表设置为初始状态
	getProvince(-1,-1,-1);
	//实现刷新页面
	getAllAddress();
	}

	//如果文本内容为修改,则执行if语句体,
		//否则(添加收货人地址),执行else
		if($(".save_recipient").html()=="修改"){
			//1.发送异步请求
			//2.响应成功
				//清空文本框
				//设置列表为初始状态
				//div的文本内容恢复为保存收货人信息
			$.ajax({
				url:"../address/updateAddress.do",
				data:$("#form_address").serialize(),
				type:"post",
				dataType:"json",
				success:function(obj){
					if(obj.state==1){
						//初始化address页面
						initAddress();
						
						$(".save_recipient").html("保存收货人信息");
						
					}
					
				}
			});
			
		}else{
		
		$.ajax({
			url:"../address/addAddress.do",
			data:$("#form_address").serialize(),
			type:"post",
			dataType:"json",
			success:function(obj){
				initAddress();
			}
			
		});
		}


##4.删除收货人地址

###4.1 删除收货人地址-持久层

1.在AddressMapper定义方法

	void deleteAddress(Integer id)

2.在AddressMapper.xml文件中,编写sql语句

	<delete id="deleteAddress">
		delete from t_address
		where id=#{id}
	</delete>

###4.2 删除收货人地址-业务层

1.在IAddressService接口中定义方法

	void deleteAddress(Integer id);

2.在AddressService实现类中,实现方法;调用持久层的方法

###4.3 删除收货人地址-控制器层

	/deleteAddress.do
	方法参数:id
	请求方式:get
	响应方式:ResponseBody

	public ResponseResult<Void> deleteAddress(Integer id){
		//1.创建rr对象
		//2.调用业务层方法
		//3.state = 1;message="成功"
		return rr;
	}

测试:

###4.4 删除收货人地址-页面

	//删除收货人地址
	function del(id){
		if(confirm("确认要删除吗?")){
			$.ajax({
				url:"../address/deleteAddress.do",
				data:"id="+id,
				type:"get",
				dataType:"json",
				success:function(obj){
					if(obj.state==1){
						getAllAddress();
					}
				}
			});
		}
		
		
	}



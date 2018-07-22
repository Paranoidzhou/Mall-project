# 购物车
##1.添加购物车

##2.显示购物车
###2.1 显示购物车-持久层

在vo包,新建CartVo类

	public class CartVo{
		private Integer id;
		private Integer uid;
		private String goodsId;
		private Integer num;
		private String image;
		private String title;
		private Integer price;
		..
	}


1.在CartMapper接口中定义方法

	List<CartVo> selectCartByUid(Integer uid);

2.在CartMapper.xml映射文件中,编写sql语句

	<select id="selectCartByUid" resultType="xx.xx.CartVo">
		select
			t_cart.id id,
			uid,
			goods_id goodsId,
			t_cart.num num,
			image,
			title,
			price
		from 
			t_cart,t_goods
		where
			t_cart.goods_id=t_goods.id
		and
			uid=#{uid}		
	</select>

测试:

###2.2 显示购物车-业务层

1.在ICartService接口中,定义方法

	List<CartVo> getCartByUid(Integer uid);

2.在CartService实现类中,实现方法,方法功能:返回持久层的集合

###2.3 显示购物车-控制器层

	/showCart.do
	参数列表:uid,map
	请求方式:get
	响应方式:转发
	
	@RequestMapping("/showCart.do")
	public String showCart(HttpSession session,ModelMap map){
		//1.调用业务层方法;返回集合
		//2.把集合添加到map对象中
		return "cart";
	}

###2.4 显示购物车-页面

1.把cart.html改为 cart.jsp

2.在header.jsp中购物车的图标添加链接

	   <li><a href="../cart/showCart.do" title="我的购物车"><img class="shopcar" src="../images/header/shop_car.png" alt=""/></a><b>|</b></li>


3.把list中的数据用户循环,输出到页面cart.jsp

	<c:forEach items="${cartVoList}" var="cartVo">
                <div class="imfor">
                    <div class="check">
                        <div class="Each">
                            <span class="normal">
                                <img src="../images/cart/product_normal.png" alt=""/>
                            </span>
                            <input type="hidden" name="" value="">
                        </div>
                    </div>
                    <div class="pudc">
                        <div class="pudc_information" id="pudcId3">
                            <img src="..${cartVo.image}" style="width: 84px;height: 84px" class="lf"/>
                            <input type="hidden" name="" value="">
                        <span class="des lf">
                            ${cartVo.title}
                              <input type="hidden" name="" value="">
                        </span>
                            <p class="col lf"><span>颜色：</span><span class="color_des">深空灰  <input type="hidden" name="" value=""></span></p>
                        </div>
                    </div>
                    <div class="pices">
                        <p class="pices_des">达内专享价</p>
                        <p class="pices_information"><b>￥</b><span>${cartVo.price}.00  <input type="hidden" name="" value=""></span></p>
                    </div>
                    <div class="num"><span class="reduc">&nbsp;-&nbsp;</span><input type="text" value="${cartVo.num}" ><span class="add">&nbsp;+&nbsp;</span></div>
                    <div class="totle">
                        <span>￥</span>
                        <span class="totle_information">${cartVo.num*cartVo.price}.00</span>
                    </div>
                    <div class="del">
                        <!-- <div>
                            <img src="img/true.png" alt=""/>
                            <span>已移入收藏夹</span>
                        </div>
                         <a href="javascript:;" class="del_yr">移入收藏夹</a>
                        -->
                        <a href="javascript:;" class="del_d">删除</a>
                    </div>
                </div>
                </c:forEach> 


##3.管理购物车

###3.1 批量删除

1.持久层

1) 在CartMapper接口中定义方法

	void deleteByBatch(Integer[] ids);

2)在CartMapper.xml中,编写sql语句

	<delete id="deleteByBatch">
		delete from t_cart
		where id in
	
		<!--collection表示变量的类型,如果是数组,array;
		item表示定义变量
		open表示以什么开始
		close表示以什么结束
		separator表示数据之间的分隔符
		-->
		<foreach collection="array" item="id" open="(" separator="," close=")">
			#{id}
		</foreach>
	</delete>
测试:

2.业务层

1)在接口中定义方法
	
	void delByBatch(Integer[] ids);

2)实现方法:调用持久层的方法

ApplicationContext ac = 
				new ClassPathXmlApplicationContext(
						"application-dao.xml",
						"application-service.xml");
		CartMapper cm = 
				ac.getBean("cartMapper",CartMapper.class);
		
3.控制器层

	/delByBatch.do
	参数类表 :Integer []
	请求方式:post
	响应方式:重定向

	@
	public String delByBatch(Integer [] ids){
		//1.调用业务层方法
		return "redirect:../cart/showCart.do";
	}

4.页面

	在cart.js文件中,第96行修改为

		var url ='../cart/delByBatch.do?ids='+str;

###3.2 单行删除

存储过程:

delete from  t_cart where id = #{id};

定义存储过程

1.语法:

create procedure delById(pid int)
begin
	sql语句;
	sql语句;
end

2.delimiter $$  改变默认的 ; 

3.查看存储过程
show procedure status;

4.删除存储过程
drop procedure 过程名

5.调用存储过程
call delById(1);

delimiter $$

create procedure delById(pid int)
begin
	delete from  t_cart where id = pid;
end$$

delimiter ;

1.持久层

1)在CartMapper接口中定义方法
	
	void deleteById(Integer id);

2)在CartMapper.xml中,调用过程

	<delete id="deleteById">

		{call delById(#{id})}

	</delete>

测试:

2.业务层

1)在ICartService接口中定义方法

	void delById(Integer id);

2)在实现类中实现方法,方法功能:调用持久层的方法

3.控制器层

	/delById.do
	参数列表:id
	请求方式:get
	响应方式:重定向

	@
	public String delById(Integer id){
		//1.调用业务层方法
		//2.重定向到cart.jsp
	}

4.页面

	var url ='../cart/delById.do?id='+id;

###3.3 修改

+-

delimiter $$

create procedure updateCart(pnum int,pid int)
begin
	update t_cart 
	set num = pnum
	where id = pid;
end$$

delimiter ;

同上(持久层,业务层,控制器,页面);

##小结:

1.用户管理
1.1 注册
1.2 登录
1.3 修改用户信息

2.地址管理
2.1 添加收货人地址
2.2 查询收货人地址
2.3 修改收货人地址

3.商品管理
3.1 商品分类
3.2 商品展示
3.3 商品详情

4.购物车
4.1 添加商品
4.2 显示购物车
4.3 管理购物车

##消息摘要(数据指纹)

1.对一个非固定长度的消息(字符串,文本,文件),通过一种算法,会得到一个固定长度的字符串,该字符串叫消息摘要.

2.算法:MD5和SHA;

3.作用:验证数据的完整性

4.规则:
	   消息内容相同,消息摘要相同;
	   反过来消息摘要相同,消息来源也是相同的

5.算法是不可逆的.

6.实现步骤

1)依赖jar :commons-codec

2)定义测试类

	public class TestMD5 {
	//给字符串加密(生成消息摘要)
	@Test
	public void test1(){
		String str1 = DigestUtils.md5Hex("abc");
		String str2 = DigestUtils.md5Hex("abc");
		System.out.println(str1);
		System.out.println(str2);
	}
	//给文件加密(生成消息摘要)
	@Test
	public void test2() throws FileNotFoundException, IOException{
		String str1 = 
			DigestUtils.md5Hex(
			new FileInputStream("pom.xml"));
		String str2 = 
				DigestUtils.md5Hex(
				new FileInputStream("pom2.xml"));
		System.out.println(str1);
		System.out.println(str2);
	}

	}





#商品管理
##1.商品分类
##2.热门商品

##3.商品展示

###显示页面

新建GoodsController类,定义方法,显示页面
	
	@Controller
	@RequestMapping("/goods")
	public class GoodsController extends BaseController{
		@RequestMapping("/showSearch.do")
		public String showSearch(){
			return "search";
		}
	}

把search.html改成search.jsp


###3.1商品展示-持久层

###3.2商品展示-业务层

###3.3商品展示-控制器层

	/showSearch.do
	参数列表:categoryId,page,map
	请求方式:get
	响应方式：转发
	@Resource
	private IGoodsService goodsService;
	public String showSearch(Integer categoryId,Integer page,ModelMap map){
		//1.计算offset
		Integer offset = (page-1)*12;
		//2.调用业务层方法
		List<Goods> list = 
		goodsService.getGoodsByCategoryId(categoryId,offset,12);

		//3.把list添加到map对象中

		return "search";
	}
	

###3.4商品展示-页面

	在index.jsp上添加链接

	<li><a href="../goods/showSearch.do?categoryId=${cc.id}">${cc.name}</a></li>

	在search.jsp页面显示商品信息
	

调整页面

共?条记录

在GoodsMapper接口中定义方法；获取商品的记录数

	Integer selectCount(Integer categoryId);

在GoodsMapper.xml中,编写sql语句

	<select id="selectCount" resultType="java.lang.Integer">
		select count(*)
		from 	
			t_goods
		where
			category_id = #{categoryId}
	</select>

测试：（１０分钟）

在IGoodsService接口中定义方法

	Integer getCount(Integer categoryId);

在GoodsService实现中,实现方法;方法的功能;返回持久层的记录数

11点15上课

在GoodsController类中的showSearch方法中,添加代码

	Integer count = goodsService.getCount(categoryId);
	map.addAttribute("count",count);

控制器方法调整后的最终代码

	public String showSearch(
			Integer categoryId,
			Integer page,
			ModelMap map){
		//在index.jsp页面的三级分类连接到页面时,page是null
		if(page==null){
			page = 1;
		}
		//1.计算offset
		Integer offset = (page-1)*12;
		//2.调用业务层方法
		List<Goods> list = 
			goodsService.getGoodsByCategoryId(
					categoryId,offset,12);

		//3.把list添加到map对象中
		map.addAttribute("goodsList",list);
		//4.在map中添加记录数
		Integer count = goodsService.
				getCount(categoryId);
		map.addAttribute("count",count);
		
		//5.共?页
		Integer pages = 
				count%12==0?count/12:count/12+1;
		map.addAttribute("pages",pages);
		
		//6.把categoryId添加到map对象中
		map.addAttribute("categoryId",categoryId);
		
		//7.设置当前页
		map.addAttribute("curpage", page);
		
		return "search";
	}

页面的分页代码

	<c:forEach var="i" begin="1" end="${pages}">
		<a href="../goods/showSearch.do?categoryId=${categoryId}&page=${i}"
		
		<c:if test="${curpage==i}">
			style="color:red"
		</c:if>
		
		>${i}</a>
	</c:forEach>

##4.商品详情

显示商品详情页

	在GoodsController类中定义方法:显示详情页
	@RequestMapper("/showProductDetials.do")
	public String showProductDetails(){
		return "product_details";
	}

改product_details.html改成product_details.jsp

###4.1商品详情-持久层

1.在GoodsMapper接口中定义方法
	
	Goods selectGoodsById(String id);

2.在GoodsMapper.xml中编写sql语句

	<select id="selectGoodsById" resultType="xx.xx.Goods">
		select
			.....
		from
			t_goods
		where
			id=#{id}
	</select>

测试:

###4.2商品详情-业务层

1.在IGoodsService接口中,定义方法

	Goods getGoodsById(String id);

2.在GoodsService实现类中实现方法,方法完成功能:返回持久层的goods对象
	
(10分钟)

###4.3商品详情-控制器

	/showProductDetails.do
	参数列表:id,map
	请求方式:get
	响应方式:转发

	修改方法
	public String showProductDetails(String id,ModelMap map){
		//1.调用业务层方法;返回goods对象
		//2.把goods对象添加到map对象中
		return "";
	}

###4.4商品详情-页面

1.在sear.jsp中修改图片和title的onclick事件的函数

	onclick="toItemInfo(${goods.id})"

	<script type="text/javascript">
    /* 商品详情页  */
	function toItemInfo(id) {
		if (id) {
			window.location.href="../goods/showProductDetails.do?id="+id;
		}else {
			alert("商品id不存在");
		}
	}

2.在product_details.jsp页面上显示商品信息

	 <!-- 右侧-->
    <div class="right_detail lf">
        <!-- 商品名称-->
        <h1>${goods.itemType}</h1>
        <!-- 商品全称-->
        <h3>${goods.title}</h3>
        <!-- 价格部分-->
        <div class="price">
            <div id="pro_price"><b>学员售价：</b><span>￥${goods.price}.00</span></div>
            <div class="promise">
                <b>服务承诺：</b>
                <span>*退货补运费</span>
                <span>*30天无忧退货</span>
                <span>*48小时快速退款</span>
                <span>*72小时发货</span>
            </div>
        </div>
        <!-- 参数部分 客服-->
        <p class="parameter">
            <b>客服：</b>
            <span class="connect">联系客服</span><img class="gif" src="../images/product_detail/kefuf.gif">
        </p>
        <!-- 颜色-->
        <p class="style" id="choose_color">
            <s class="color">颜色：</s>
            <input type="button" class="i1" value="黑色" title="黑色"/>
            <input type="button" class="i2" value="灰色" title="灰色"/>
            <input type="button" class="i3" value="金色" title="金色"/>
            <input type="button" class="i4" value="白色" title="白色"/>
        </p>
        <!-- 规格-->
        <p>
            <s>规格：</s>
            <span class="avenge">15寸 15 1T</span>
            <span class="avenge">18寸 18 2T</span>
            <span class="avenge">19寸 19 3T</span>
        </p>
        <!-- 未选择规格，颜色时状态-->
        <div class="message"></div>
        <!-- 数量-->
        <p class="accountChose">
            <s>数量：</s>
            <button class="numberMinus">-</button>
            <input type="text" value="1" class="number" id="buy-num">
            <button class="numberAdd">+</button>
        </p>
        <!-- 购买部分-->
        <div class="shops">
            <a href="cart.html" class="buy lf" id="buy_now">立即购买</a>
            <a href="#" class="shop lf" id="add_cart"><img src="../images/product_detail/product_detail_img7.png" alt=""/>加入购物车</a>
            <a href="#" class="collection lf" id="collect"><span>收藏</span></a><b><img src="../images/product_detail/product_detail_img6.png"                                                                       alt=""/></b>
        </div>
    </div>


#购物车

##设计表t_cart

    create table t_cart(
		id int auto_increment primary key,
		uid int not null,
		goods_id varchar(200) not null,
		num int,
		created_user varchar(50),
		created_time date,
		modified_user varchar(50),
		modified_time date
	)default charset=utf8;

##1.添加购物车

###1.1 添加购物车-持久层

新建Cart类

	public class Cart{
		private Integer id;
		private Integer uid;
		private String goodsId;
		private Integer num;
		private String createdUser;
		private Date createdTime;
		private String modifiedUser;
		private Date modifiedTime;
		...
	}

1.新建CartMapper接口,定义方法,完成添加购物车

	public interface CartMapper{
		void insertCart(Cart cart);
	}

2.新建CartMapper.xml(拷贝,改名,删除),编写sql语句;注意:namespace="xx.xx.CartMapper"

	<insert id="insertCart" parameterType="xx.xx.Cart">
		insert into t_cart(
			uid,goods_id,num,
			created_user,created_time,
			modified_user,modified_time
		)values(
			#{uid},#{goodsId},#{num},
			#{createdUser},#{createdTime},
			#{modifiedUser},#{modifiedTime}
		)
	</insert>
	
测试:(10分钟)
16点15上课

###1.2 添加购物车-业务层

1.新建ICartService接口,定义方法

	public inteface ICartService{
		void addCart(Cart cart);
	}

2.新建CartService实现类,实现方法.方法的功能:调用持久层方法;

(10分钟)

###1.3 添加购物车-控制器层

	/cart/addCart.do
	请求参数:session(uid),goodsId,num
	请求方式:get
	响应方式:ResponseBody
	@Controller
	@RequestMapping("/cart")
	public class CartController extends BaseController{
		@
		private ICartService cartService;
		@
		@
		public ResponseResult<Void> addCart(
			HttpSession session,
			String goodsId,
			Integer num){
			//1.创建rr对象
			//2.调用业务层方法
			//3.state=1;message="添加成功"
			return rr;
		}
	}

测试:

###1.4 添加购物车-页面

	$("#add_cart").click(function (e) {
			$.ajax({
				//1.发送异步请求
				//2.请求成功,显示 alert(obj.message);
				url:"../cart/addCart.do",
				data:"goodsId="+$("#goodsid").val()+
					  "&num="+$("#buy-num").val(),
				type:"get",
				dataType:"json",
				success:function(obj){
					if(obj.state==1){
						alert(obj.message);
					}
				},
				//异步请求,如果拦截器拦截,重定向到login页面,不会
				//直接到达重定向的页面,以302状态码的形式响应回来
				//通过error属性处理
				error:function(obj){
					location="../user/showLogin.do";
				}
				
			});
    });
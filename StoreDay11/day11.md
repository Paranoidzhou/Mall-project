#导入表之后如果有中文乱码,解决方案

1.set names gbk;

2.source e:/xx.sql

3.set names utf8

#解压portal.zip到portal文件夹,把文件夹拷贝到工程的images下

#商品分类

##1.显示商品分类

###1.1显示商品分类-持久层

新建实体类GoodsCategory

1.新建GoodsCategoryMapper接口,定义方法

	List<GoodsCategory> selectCategoryByParentId(
		@Param("parentId") Integer parentId,
		@Param("offset") Integer offset,
		@Param("count") Integer count);
	

2.新建GoodsCategoryMapper.xml(拷贝,改名,删除)文件中,编写sql语句
注意:namespace="xx.xx.GoodsCategoryMapper"
	
	<select id="selectCategoryByParentId" resultType="xx.xx.GoodsCategory">

		select 
			id,
			parent_id parentId,
			name,
			status,
			sort_order sortOrder,
			is_parent isParent,
			created_user createdUser,
			created_time createdTime,
			modified_user modifiedUser,
			modified_time modifiedTime
		from t_goods_category
		where
			parent_id = #{parentId}
		and
			status =1
		<if test="offset!=null">
			limit #{offset},#{count}
		</if>
	
	</select>

测试:(161,0,3)二级分类		(parentId,null,null)

(15分钟)


###1.2显示商品分类-业务层

1.新建IGoodsCategoryService接口,接口中定义方法

	List<GoodsCategory> getCategoryByParentId(Integer parentId,Integer offset,Integer count);

2.新建GoodsCategoryService实现类,在类中实现方法;方法的功能:返回持久层的list集合
	
	@Service
	public class GoodsCategoryService implements IGoodsCategoryService{
		@Resource
		private GoodsCategoryMapper goodsCategoryMapper;
		public List<GoodsCategory> getCategoryByParentId(Integer parentId,Integer offset,Integer count){
			//调用持久的方法,返回集合
		}
	}

测试:

###1.3显示商品分类-控制器层

	在MainController控制类中,showIndex方法中,添加代码
	@Resource
	private IGoodsCategoryService goodsCategoryService;
	public String showIndex(ModelMap map){
		//调用业务层的方法
		List<> list2 =goodsCategoryService.getCategoryByParentId(161,0,3);

		//定义List<List<GoodsCategory>> list3 = 
			    new ArrayList<<>>  ();
		遍历list2,从list2中获取GoodsCategory对象,再从对象中获取id,调用业务层方法,每一次循环返回list,把list添加到list3

		把list2和list3设置到map中
	}
(10分钟)
###1.4显示商品分类-页面

	<c:forEach items="${list2}" varStatus="status">
                <p>${list2[status.index].name}</p>
                <ul>
                	<c:forEach items="${list3[status.index]}" 
                					var="cc">
                    <li><a href="#">${cc.name}</a></li>
                  </c:forEach> 
                </ul>
                </c:forEach>

##2.显示热门商品

###2.1显示人热门商品持久层

新建Goods实体类

1.新建GoodsMapper接口,定义方法
	
	public interface GoodsMapper{
		List<Goods> selectGoodsByCategoryId(
			@Param("categoryId") Integer categoryId,
			@Param("offset") Integer offset,
			@Param("count") Integer count);
	}

2.新建GoodsMapper.xml文件(拷贝,改名,删除),编写sql语句;
注意namespace="xx.xx.GoodsMapper"

	<select id="selectGoodsByCategoryId" resultType="xx.xx.Goods">
		select
			id,
			category_id categoryId,
			item_type itemType,
			title,
			sell_point sellPoint,
			barcode,
			price,
			num,
			image,
			status,
			priority,
			created_user createdUser,
			created_time createdTime,
			modified_user modifiedUser,
			modified_time modifiedTime
		from
			t_goods
		where
			category_id = #{categoryId}
			and num>0 and status=1
		order by priority desc		

		limit #{offset},#{count}

	</select>

测试:

###2.2 显示热门商品-业务层

1.新建IGoodsService接口,定义方法

	public interface IGoodsService{
		List<Goods> getGoodsByCategoryId(Integer categoryId,Integer offset,Integer count);
	}

2.新建GoodsService实现类,实现方法:同商品分类

17点15上课
	

###2.3 显示热门商品-控制器层

	在MainController的showIndex方法中添加代码

	@Resource
	private IGoodsService  goodsService;

	public String showIndex(ModelMap map){

	.......
		List<Goods> goodsList = 
			goodsService.getGoodsByCategoryId(163,0,3);
		map.addAttribute("goodsList",goodsList);
		
	}

(5分钟)

###2.4 显示热门商品-页面

	 <c:forEach items="${goodsList}" var="goods">
        <div class="item_msg lf">
            <img src="..${goods.image}" style="width: 198px;height: 136px" alt=""/>

            <p class="bottom_ys2" style="width: 198px;height: 28px">${goods.title}</p>

            <p class="bottom_ys3">￥${goods.price}</p>

            <p class="bottom_ys4 color_2"><a href="product_details.html">查看详情</a></p>
        </div>
       </c:forEach>
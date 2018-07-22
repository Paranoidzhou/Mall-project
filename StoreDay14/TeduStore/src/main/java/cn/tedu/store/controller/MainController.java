package cn.tedu.store.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tedu.store.bean.Goods;
import cn.tedu.store.bean.GoodsCategory;
import cn.tedu.store.service.IGoodsCategoryService;
import cn.tedu.store.service.IGoodsService;

@Controller
@RequestMapping("/main")
public class MainController {
	@Resource
	private IGoodsCategoryService goodsCategoryService;
	@Resource
	private IGoodsService goodsService;
	//显示index.jsp
	@RequestMapping("/showIndex.do")
	public String showIndex(ModelMap map){
		//调用业务层的方法
		List<GoodsCategory> list2 =
			goodsCategoryService.
			getCategoryByParentId(161,0,3);

		//定义List<List<GoodsCategory>> list3 = 
		//		new ArrayList<<>>  ();
		List<List<GoodsCategory>> list3 = 
		new ArrayList<List<GoodsCategory>>();
		
		//遍历list2,从list2中获取GoodsCategory对象,再从对象中获取id,调用业务层方法,每一次循环返回list,把list添加到list3
		for(GoodsCategory cc : list2){
			list3.add(goodsCategoryService.
			getCategoryByParentId(
					cc.getId(), null, null));
		}
		//把list2和list3设置到map中
		map.addAttribute("list2",list2);
		map.addAttribute("list3",list3);
		
		//把热门商品的集合添加到map
		List<Goods> goodsList = 
		goodsService.getGoodsByCategoryId(163,0,3);
		map.addAttribute("goodsList",goodsList);
		
		return "index";
	}
	
}









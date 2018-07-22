package cn.tedu.store.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.bean.Area;
import cn.tedu.store.bean.City;
import cn.tedu.store.bean.Province;
import cn.tedu.store.bean.ResponseResult;
import cn.tedu.store.service.IDictService;

@Controller
@RequestMapping("/dict")
public class DictController {
	@Resource
	private IDictService dictService;
	//显示省信息
	@RequestMapping("/getProvince.do")
	@ResponseBody
	public ResponseResult<List<Province>> 
							getProvince(){
		ResponseResult<List<Province>> rr = 
		new ResponseResult<List<Province>>();
		List<Province> list = 
				dictService.getProvince();
		rr.setData(list);
		rr.setState(1);
		rr.setMessage("成功");
		return rr;
		
	}

	//显示城市信息
	@RequestMapping("/getCity.do")
	@ResponseBody
	public ResponseResult<List<City>> getCity(
			String provinceCode){
		ResponseResult<List<City>> rr = 
				new ResponseResult<List<City>>();
		List<City> list =
				dictService.getCity(provinceCode);
		rr.setData(list);
		rr.setState(1);
		rr.setMessage("成功");
		return rr;
		
	}
	//显示区县信息
	
	@RequestMapping("/getArea.do")
	@ResponseBody
	public ResponseResult<List<Area>> getArea(
			String cityCode){
		ResponseResult<List<Area>> rr = 
				new ResponseResult<List<Area>>();
		rr.setData(dictService.getArea(cityCode));
		rr.setState(1);
		rr.setMessage("成功");
		return rr;
	}
}










package cn.tedu.store.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.store.bean.Area;
import cn.tedu.store.bean.City;
import cn.tedu.store.bean.Province;
import cn.tedu.store.mapper.DictMapper;
@Service
public class DictService implements 
					IDictService{
	@Resource
	private DictMapper dictMapper;
	public List<Province> getProvince() {
		return dictMapper.selectProvince();
	}
	public List<City> getCity(String provinceCode) {
		
		return dictMapper.selectCity(provinceCode);
	}
	public List<Area> getArea(String cityCode) {
		
		return dictMapper.selectArea(cityCode);
	}

}






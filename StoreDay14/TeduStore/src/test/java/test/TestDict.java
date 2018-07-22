package test;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.mapper.DictMapper;
import cn.tedu.store.service.IDictService;

public class TestDict {
	@Test
	public void testSelectNameByCode(){
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext(
						"application-dao.xml",
						"application-service.xml");
		DictMapper dm = 
				ac.getBean("dictMapper",
						DictMapper.class);
		System.out.println(
			dm.selectProvinceNameByCode("130000"));
		System.out.println(
				dm.selectCityNameByCode("130100"));
		System.out.println(
				dm.selectAreaNameByCode("130102"));
	}
	@Test
	public void testSelectCity(){
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext(
						"application-dao.xml",
						"application-service.xml");
		/*DictMapper dm = 
				ac.getBean("dictMapper",
						DictMapper.class);*/
		IDictService dm = 
				ac.getBean("dictService",
						IDictService.class);
		System.out.println(
				dm.getCity("130000"));
		
	}
	
	@Test
	public void testSelectProvince(){
		ApplicationContext ac = 
			new ClassPathXmlApplicationContext(
					"application-dao.xml",
					"application-service.xml");
	
		IDictService ds =
				ac.getBean("dictService",IDictService.class);
		System.out.println(
				ds.getProvince());
		
		
		
		/*	DictMapper dm = 
				ac.getBean("dictMapper",
						DictMapper.class);
		List<Province> list = 
				dm.selectProvince();
		System.out.println(list);*/
		
	}

}








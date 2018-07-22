package test;


import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.bean.Province;
import cn.tedu.store.mapper.DictMapper;
import cn.tedu.store.service.IDictService;

public class TestDict {
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








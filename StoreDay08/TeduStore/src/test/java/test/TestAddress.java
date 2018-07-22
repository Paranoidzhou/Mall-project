
package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.bean.Address;
import cn.tedu.store.mapper.AddressMapper;

public class TestAddress {
	@Test
	public void testSelectByUid(){
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext(
						"application-dao.xml",
						"application-service.xml");
			
			AddressMapper am = 
					ac.getBean("addressMapper",
					AddressMapper.class);
			System.out.println(
					am.selectAddressByUid(1).size());
	}
	@Test
	public void testInsertAddress(){
		ApplicationContext ac = 
			new ClassPathXmlApplicationContext(
					"application-dao.xml",
					"application-service.xml");
		
		AddressMapper am = 
				ac.getBean("addressMapper",
				AddressMapper.class);
		Address address = new Address();
		address.setUid(3);
		address.setRecvUsername("小刘");
		address.setRecvProvinceCode("130000");
		address.setRecvCityCode("130100");
		address.setRecvAreaCode("130102");
		address.setRecvAddress("中鼎8层");
		address.setRecvDistrict("河北省石家庄市长安区");
		address.setRecvPhone("13800138000");
		address.setRecvTel("10086");
		address.setRecvZip("100000");
		address.setRecvTag("公司");
		address.setIsDefault(1);
		am.insertAddress(address);
	}

}









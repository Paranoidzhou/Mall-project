package cn.tedu.store.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.store.bean.Address;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.mapper.DictMapper;
@Service
public class AddressService implements 
						IAddressService{
	@Resource
	private AddressMapper addressMapper;
	@Resource
	private DictMapper dictMapper;
	public void addAddress(Address address) {
		//1.设置recvDeistrict值
		/*调用dictMapper接口中的方法,通过code获取name,把3个name用+连接成一个字符串,把该字符串设置到recvDeistrict中.

		dictMapper.selectProvinceNameByCode(address.getRecvProvinceCode);*/

		String loc = 
			dictMapper.
				selectProvinceNameByCode(
				address.getRecvProvinceCode())+
			dictMapper.
			 	selectCityNameByCode(
			 	address.getRecvCityCode())+
		    dictMapper.
			 	selectAreaNameByCode(
			 	address.getRecvAreaCode());
		address.setRecvDistrict(loc);
		/*2.给isDefault属性设置值
		0表示非默认
		1表示默认
		调用addressMapper.selectAddressByUid(address.getUid());返回list;
		list.size()>0  isDefault = 0
		list.size()=0  isDefault = 1*/
		List<Address> list  = 
			addressMapper.selectAddressByUid(
			address.getUid());
		if(list.size()>0){
			address.setIsDefault(0);
		}else{
			address.setIsDefault(1);
		}

		//3.addressMapper.insertAddress(address);
		addressMapper.insertAddress(address);
	}
	public List<Address> getAddressByUid(Integer uid) {
		
		return addressMapper.selectAddressByUid(uid);
	}
	public void setDefault(Integer uid, Integer id) {
		//1.调用updateCancel方法,返回int的值n1
		//如果n1==0,抛出异常RuntimeException("修改失败");
		Integer n1 = 
				addressMapper.updateCancel(uid); 
		if(n1==0)
			throw new RuntimeException("修改失败");
		//2.调用updateDefault()方法,返回int的值n2
		//如果n2==0,抛出异常RuntimeException("修改失败");
		Integer n2 = 
				addressMapper.updateDefault(id);
		if(n2==0){
			throw new RuntimeException("修改失败");
		}
	}

}









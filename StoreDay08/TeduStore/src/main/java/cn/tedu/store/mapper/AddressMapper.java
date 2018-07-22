package cn.tedu.store.mapper;

import java.util.List;

import cn.tedu.store.bean.Address;

public interface AddressMapper {
	/**
	 * 插入address信息
	 * @param address
	 */
	void insertAddress(Address address);
	/**
	 * 查询登录人的收货地址
	 * @param uid
	 * @return
	 */
	List<Address> selectAddressByUid(Integer uid);
	

}

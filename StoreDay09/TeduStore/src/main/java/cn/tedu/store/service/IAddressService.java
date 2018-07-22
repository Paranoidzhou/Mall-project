package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.bean.Address;

public interface IAddressService {
	/**
	 * 添加地址信息
	 * @param address
	 */
	void addAddress(Address address);
	/**
	 * 显示地址信息
	 * @param uid
	 * @return
	 */
	List<Address> getAddressByUid(Integer uid);
	/**
	 * 设置默认
	 * @param uid
	 * @param id
	 */
	void setDefault(Integer uid,Integer id);
}









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
	/**
	 * 把所有的地址的is_default字段设置为0
	 * @param uid
	 * @return
	 */
	Integer updateCancel(Integer uid);
	/**
	 * 把指定id的地址的is_default字段设置为1
	 * @param id
	 * @return
	 */
	Integer updateDefault(Integer id);
	/**
	 * 根据id查询地址信息
	 * @param id
	 * @return
	 */
	Address selectAddressById(Integer id);
	/**
	 * 修改地址信息
	 * @param address
	 */
	void updateAddress(Address address);
	/**
	 * 删除地址信息
	 * @param id
	 */
	void deleteAddress(Integer id);
}





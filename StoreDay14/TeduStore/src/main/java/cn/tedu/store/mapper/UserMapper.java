package cn.tedu.store.mapper;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.bean.User;

public interface UserMapper {
	/**
	 * 插入user用户信息
	 * @param user
	 */
	void insertUser(User user);
	/**
	 * 根据用户名查询用户信息
	 * @param username
	 * @return
	 */
	User selectUserByUsername(String username);
	/**
	 * 根据邮箱查询user信息
	 * @param email
	 * @return
	 */
	Integer selectByEmail(String email);
	/**
	 * 根据电话号码查询user信息
	 * @param phone
	 * @return
	 */
	Integer selectByPhone(String phone);
	/**
	 * 修改用户信息
	 * @param user
	 */
	void updateUser(User user);
	/**
	 * 根据id查询用户信息
	 * @param id
	 * @return
	 */
	User selectUserById(Integer id);
	/**
	 * 修改头像
	 * @param image
	 * @param id
	 */
	void updateImage(@Param("image") String image,
			@Param("id") Integer id);

}








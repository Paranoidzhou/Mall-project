package cn.tedu.store.mapper;

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

}








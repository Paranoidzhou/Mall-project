package cn.tedu.store.service;

import cn.tedu.store.bean.User;

public interface IUserService {
	/**
	 * 注册用户信息
	 * @param user
	 */
	void register(User user);
	/**
	 * 验证邮箱是否存在
	 * @param email
	 * @return
	 */
	boolean checkEmail(String email);
	/**
	 * 判断电话号码是否存在
	 * @param phone
	 * @return
	 */
	boolean checkPhone(String phone);
	/**
	 * 验证用户名是否存在
	 * @param username
	 * @return
	 */
	boolean checkUsername(String username);
	/**
	 * 登录
	 * @param username
	 * @param password
	 * @return
	 */
	User login(String username,String password);
}












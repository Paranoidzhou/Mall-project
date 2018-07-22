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
	/**
	 * 修改密码
	 * @param id
	 * @param oldPwd
	 * @param newPwd
	 */
	void changePassword(Integer id,String oldPwd,String newPwd);
	/**
	 * 修改个人信息
	 * @param id
	 * @param username
	 * @param gender
	 * @param phone
	 * @param email
	 */
	void updateUser(Integer id,
			        String username,
			        Integer gender,
			        String phone,
			        String email);
	/**
	 * 通过id返回 用户信息
	 * @param id
	 * @return
	 */
	User getUserById(Integer id);
	/**
	 * 修改图片
	 * @param image
	 * @param id
	 */
	void updateImage(String image,Integer id);
}












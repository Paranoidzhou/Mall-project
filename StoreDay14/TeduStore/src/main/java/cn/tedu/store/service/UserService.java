package cn.tedu.store.service;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import cn.tedu.store.bean.User;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameAlreadyExistException;
@Service
public class UserService implements IUserService{
	@Resource
	private UserMapper userMapper;
	private String salt = "喜欢编程吗?";
	public void register(User user) {
		//1.调用持久层的selectUserByUsername(
		//user.getUsername());返回User对象 user1;
		User user1 = userMapper.selectUserByUsername(
				user.getUsername());

		//2.if(user1==null)
		if(user1==null){
			//3.调用insertUser(user);
			//明文的密码
			String pwd = user.getPassword();
			//生成密文
			String pwdMd5 = 
					DigestUtils.md5Hex(pwd+salt);
			//user对象中设置密文
			user.setPassword(pwdMd5);
			userMapper.insertUser(user);
		}else{
		
		   //4.抛出异常UsernameAlreadyExistException;
			throw new 
			UsernameAlreadyExistException(
					"用户名已存在");
				
		}
				
			
		
	}
	public boolean checkEmail(String email) {
		
		return userMapper.selectByEmail(email)>0;
	}
	public boolean checkPhone(String phone) {
		
		return userMapper.selectByPhone(phone)>0;
	}
	public boolean checkUsername(String username) {
		System.out.println("UserService.checkUsername");
		return userMapper.selectUserByUsername(username)!=null;
	}
	public User login(String username, String password) {
		System.out.println("UserService.login");
		
		//1.调用持久层方法selectUserByUsername(username);
		//返回user对象
		User user =
			userMapper.selectUserByUsername(username);
		
		//2.user==null,抛出异常,UserNotFoundException;
		if(user == null){
			throw new UserNotFoundException("用户不存在");
		}else{
		//3.user!=null,从user对象中获取password,和参数列表中的password比较
		//4.密码比较,
		//两种情况:密码相同;返回 user对象;
		//如果密码不相同,抛出异常PasswordNotMatchException
			//把密码处理成密文和数据库的密文进行比较
			String strMd5 = 
					DigestUtils.md5Hex(password+salt);
			if(user.getPassword().equals(strMd5)){
				return user;
			}else{
				throw new 
				PasswordNotMatchException("密码不匹配");
			}
		}
	}
	public void changePassword(Integer id, String oldPwd, String newPwd) {
		//1.调用持久层的方法selectUserById(id);返回user对象;
		User user = userMapper.selectUserById(id);
		//2.user==null;抛出异常UserNotFoundException;
		if(user==null){
			throw new UserNotFoundException("用户不存在");
		}else{
		//3.user!=null;从user对象中获取password,和页面上的oldPwd比较
			//把olePwd变成密文进行比较
			String oldMd5 = 
					DigestUtils.md5Hex(oldPwd+salt);
			
			if(user.getPassword().equals(oldMd5)){
				//4.如果密码相同,
				//调用持久层的方法,updateUser(user1),user1对象中封装id,和newPwd;
				User user1 = new User();
				user1.setId(id);
				String newMd5 = 
						DigestUtils.md5Hex(newPwd+salt);
				
				user1.setPassword(newMd5);
				userMapper.updateUser(user1);
				
			}else{
				//5.如果密码不相同,
				//抛出异常PasswordNotMatchException;
				throw new 
				PasswordNotMatchException("密码不匹配");
			}
		}
	}
	public void updateUser(Integer id, String username, Integer gender, String phone, String email) {
		User newUser = new User();
		newUser.setId(id);
		newUser.setGender(gender);
		newUser.setPhone(phone);
		newUser.setEmail(email);
		//根据id查询;返回user对象
		User user = userMapper.selectUserById(id);
		if(user==null){
			//如果user==null;抛出异常
			throw new UserNotFoundException("用户不存在");
		}else{
			//根据用户名查询;返回user1
			User user1 = 
					userMapper.
					selectUserByUsername(username);
			if(user1!=null){
				
				//当前的用户名就是登陆的用户名
				if(user1.getUsername().equals(
						user.getUsername())){
				}else{
					//否则抛出异常
					throw new 
					UsernameAlreadyExistException(
							"用户名已存在");
				}
			}else{
				//数据库中没有相同的用户名,
				//设置用户名为newUser的属性.
				newUser.setUsername(username);
			}
			//修改用户信息
			userMapper.updateUser(newUser);
			
		}
		
	}
	public User getUserById(Integer id) {
		return userMapper.selectUserById(id);
	}
	public void updateImage(String image, Integer id) {
		userMapper.updateImage(image, id);
		
	}

}









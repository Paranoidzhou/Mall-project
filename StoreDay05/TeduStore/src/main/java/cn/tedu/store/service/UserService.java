package cn.tedu.store.service;

import javax.annotation.Resource;

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
	public void register(User user) {
		//1.调用持久层的selectUserByUsername(
		//user.getUsername());返回User对象 user1;
		User user1 = userMapper.selectUserByUsername(
				user.getUsername());

		//2.if(user1==null)
		if(user1==null){
			//3.调用insertUser(user);
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
		
		return userMapper.selectUserByUsername(username)!=null;
	}
	public User login(String username, String password) {
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
			if(user.getPassword().equals(password)){
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
			if(user.getPassword().equals(oldPwd)){
				//4.如果密码相同,
				//调用持久层的方法,updateUser(user1),user1对象中封装id,和newPwd;
				User user1 = new User();
				user1.setId(id);
				user1.setPassword(newPwd);
				userMapper.updateUser(user1);
				
			}else{
				//5.如果密码不相同,
				//抛出异常PasswordNotMatchException;
				throw new 
				PasswordNotMatchException("密码不匹配");
			}
		}
	}

}









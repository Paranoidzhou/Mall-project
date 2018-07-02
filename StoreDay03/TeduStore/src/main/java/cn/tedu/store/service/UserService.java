package cn.tedu.store.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.store.bean.User;
import cn.tedu.store.mapper.UserMapper;
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

}

package cn.tedu.store.service.ex;

public class UserNotFoundException 
extends RuntimeException {
	private static final long serialVersionUID = 4702489653158371174L;
	public UserNotFoundException(){
		
	}
	public UserNotFoundException(String msg){
		super(msg);
	}

}

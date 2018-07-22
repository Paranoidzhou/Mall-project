package cn.tedu.store.service.ex;

public class PasswordNotMatchException 
extends RuntimeException {
	private static final long serialVersionUID = -7765675219725939597L;
	public PasswordNotMatchException(){
		
	}
	public PasswordNotMatchException(String msg){
		super(msg);
	}
}

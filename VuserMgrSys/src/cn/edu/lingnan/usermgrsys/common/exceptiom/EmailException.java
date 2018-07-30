package cn.edu.lingnan.usermgrsys.common.exceptiom;

public class EmailException extends RuntimeException{

	/**
	 * 默认的构造方法
	 */
	public EmailException(){
		
	}
	
	/**
	 * 构造方法
	 */
	public EmailException(String arg0){
		super(arg0);
	}
	
	/**
	 * 构造方法
	 */
	public EmailException(Throwable arg0){
		super(arg0);
	}
	
	/**
	 * 构造方法
	 */
	public EmailException(String arg0,Throwable arg1){
		super(arg0,arg1);
	}
}

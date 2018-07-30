package cn.edu.lingnan.usermgrsys.common.exceptiom;

public class DateException extends RuntimeException{

	/**
	 * 默认的构造方法
	 */
	public DateException(){
		
	}
	
	/**
	 * 构造方法
	 */
	public DateException(String arg0){
		super(arg0);
	}
	
	/**
	 * 构造方法
	 */
	public DateException(Throwable arg0){
		super(arg0);
	}
	
	/**
	 * 构造方法
	 */
	public DateException(String arg0,Throwable arg1){
		super(arg0,arg1);
	}
}

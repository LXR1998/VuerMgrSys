package cn.edu.lingnan.usermgrsys.common.exceptiom;

public class ServiceException extends RuntimeException{
	
	/**
	 * 默认的构造方法
	 */
	public ServiceException(){
		
	}
	/**
	 * 构造方法
	 * @param arg0 字符串对象
	 */
	public ServiceException(String arg0){
		super(arg0);
	}
	
	/**
	 * 构造方法
	 * @param arg0 异常对象
	 */
	public ServiceException(Throwable arg0){
		super(arg0);
	}
	
	/**
	 * 构造方法
	 * @param arg0 字符串对象
	 * @param arg1 异常对象
	 */
	public ServiceException(String arg0,Throwable arg1){
		super(arg0,arg1);
	}

}

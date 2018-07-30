package cn.edu.lingnan.usermgrsys.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 类型转换工具类
 * @author 11638
 * 
 */
public class TypeUtils {
	
	/**
	 * 字符串转换为日期
	 * @param str 指定的字符串
	 * @return 转换后的日期
	 */
	public static Date strToDate(String str){
		Date date = null;
		//设置要格式化的日期格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
		//调用parse方法，将字符串解析成指定的日期类型
			date = sdf.parse(str);
		} catch (ParseException e) {
			// 将异常封装成自定义异常
//			throw new DateException("字符串转换为日期出错",e);
			e.printStackTrace();
		}
			
		return date;
	}
	
	/**
	 * 日期转换成字符串
	 * @param date
	 * @return 字符串
	 */
	public static String dateToStr(Date date){
		String str = null;
		try{
		//设置需要格式化的日期格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//调用format方法，将日期转化成字符串
		str = sdf.format(date);
		}catch(Exception e){
//			throw new DateException("日期转换为字符串出错",e);
		}
		return str;
	}
	
	/**
	 * 邮箱检验格式
	 * @param email
	 * @return boolean
	 */
	public static boolean checkEmail(String email){
		
		String mail = email.replaceAll
				("^(\\w+((-\\w+)|(\\.\\w+))*)\\+\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$ ","");
		
		if(mail.length()==0)
			return true;
		else{
			System.out.println("邮箱格式错误");
			return false;
		}
	}

}

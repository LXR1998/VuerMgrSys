package cn.edu.lingnan.usermgrsys.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.edu.lingnan.usermgrsys.common.exceptiom.DateException;
import cn.edu.lingnan.usermgrsys.common.exceptiom.EmailException;

/**
 * 类型转换工具类
 * @author 11638
 * 
 */
public class TypeUtils {
	
	/**
	 * 字符串转换为日期
	 * @param str 指定的字符串
	 * @return date 转换后的日期
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
			throw new DateException("字符串转换为日期出错",e);
		}
			
		return date;
	}
	
	/**
	 * 日期转换成字符串
	 * @param date 日期对象
	 * @return str 转换成功之后的字符串
	 */
	public static String dateToStr(Date date){
		String str = null;
		try{
		//设置需要格式化的日期格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//调用format方法，将日期转化成字符串
		str = sdf.format(date);
		}catch(Exception e){
			throw new DateException("日期转换为字符串出错",e);
		}
		return str;
	}
	
	/**
	 * 邮箱检验格式
	 * @param email 字符串对象邮箱
	 * @return boolean 判断结果
	 */
	public static boolean checkEmail(String email){
		try {
		String mail = email.replaceAll
				("^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$","");
		
		if(mail.length()==0)
			return true;
		else{
			System.out.println("邮箱格式错误");
			return false;
			}
		}catch(Exception e){
			throw new EmailException("邮箱格式错误",e);
		}
	}

}

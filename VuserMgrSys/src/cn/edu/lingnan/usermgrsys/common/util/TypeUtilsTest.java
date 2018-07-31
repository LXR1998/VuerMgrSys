package cn.edu.lingnan.usermgrsys.common.util;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class TypeUtilsTest {

	@Test
	public void testStrToDate() {
		Date date = null;
		date = TypeUtils.strToDate("2012-2-5");
		System.out.println("字符串转日期：");
		System.out.println(date);
	}

	@Test
	public void testDateToStr() {
		String str = null;
		str = TypeUtils.dateToStr(new Date());
		System.out.println("日期转字符串：");
		System.out.println(str);
	}

	@Test
	public void testCheckEmail() {
		String email1 = "1163870414@qq.com";
		String email2 = "1623nddk";
		System.out.println("检查邮箱格式：");
		System.out.println(TypeUtils.checkEmail(email1));
		System.out.println(TypeUtils.checkEmail(email2));
	}

}

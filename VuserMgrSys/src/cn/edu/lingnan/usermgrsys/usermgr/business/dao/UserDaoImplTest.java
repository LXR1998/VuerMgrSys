package cn.edu.lingnan.usermgrsys.usermgr.business.dao;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Test;

import cn.edu.lingnan.usermgrsys.common.util.DBUtils;

public class UserDaoImplTest {

	@Test
	public void testUserDaoImpl() {
		Connection conn = DBUtils.getConnection();
		System.out.println(conn);
	}

	@Test
	public void testLogin() {
		fail("Not yet implemented");
	}

}

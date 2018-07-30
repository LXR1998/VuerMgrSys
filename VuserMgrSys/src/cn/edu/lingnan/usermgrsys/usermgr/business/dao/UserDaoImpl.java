package cn.edu.lingnan.usermgrsys.usermgr.business.dao;

import java.sql.Connection;

import cn.edu.lingnan.usermgrsys.usermgr.domain.UserVO;

public class UserDaoImpl implements UserDao{

	/**
	 * 
	 * 数据库连接
	 */
	private Connection conn;
	/**
	 * 构造方法
	 * @param conn 数据库连接
	 */
	public UserDaoImpl(Connection conn){
		//给属性赋初始化值
		this.conn=conn;
	}
	@Override
	public UserVO login(String name, String password) {
		// TODO Auto-generated method stub
		return null;
	}
}

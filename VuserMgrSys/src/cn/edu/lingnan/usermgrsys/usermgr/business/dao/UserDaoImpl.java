package cn.edu.lingnan.usermgrsys.usermgr.business.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.edu.lingnan.usermgrsys.common.exceptiom.DaoException;
import cn.edu.lingnan.usermgrsys.common.util.DBUtils;
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
	public UserVO login(String Uname, String Passwd) {
		
		ResultSet rs = null;
		PreparedStatement pstam = null;
		UserVO user = null;
		//定义一个sql查询语句用于判断用户名及密码是否正确，用于用户的登录
		String sql = "select * from v_user where Uname = ? and Passwd = ? and Status = 0";
		
		try {
			//定义预编译语句
			pstam = conn.prepareStatement(sql);
			pstam.setString(1, Uname);
			pstam.setString(2, Passwd);
			
			//执行预编译语句(查询），并把查询的结果放到结果集里面
			rs = pstam.executeQuery();
			//如果结果集中有符合条件的值，将取出符合条件的结果封装在对象变量中
			if(rs.next()){
				//创建一个新UserVO对象，赋值给用户对象变量
				user = new UserVO();
				//赋值
				user.setUserId(rs.getInt("UserId"));
				user.setUname(rs.getString("Uname"));
				user.setPasswd(rs.getString("Passwd"));
				user.setEmail(rs.getString("Email"));
				user.setRgsdate(rs.getDate("Rgsdate"));
				user.setUtype(rs.getString("Utype"));
				user.setStatus(rs.getInt("Status"));
			}
		} catch (SQLException e) {
			System.out.println("用户在登录时出错了，错误信息是："+e.getMessage());
			// 将异常封装成自定义异常
			throw new DaoException("执行数据库SQL语句时出错，请重试",e);
		}	
		finally{
			DBUtils.closeStatement(rs, pstam);
		}
		return user;
	}
}

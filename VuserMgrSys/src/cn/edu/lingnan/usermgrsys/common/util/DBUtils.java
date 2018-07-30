package cn.edu.lingnan.usermgrsys.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cn.edu.lingnan.usermgrsys.common.exceptiom.DaoException;

/**
 * 数据库工具类
 * @author 11638
 *
 */
public class DBUtils {
	/**
	 * 获取数据库连接
	 * 返回数据库的连接对象
	 */
	public static Connection getConnection()
	{
		Connection conn = null;
		try {
			//加载驱动
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			String url="jdbc:oracle:thin:@localhost:1521:orcl"; //orcl为数据库的SID 
			String user="scott"; 
			String password="lxr"; 
			//获得数据库连接对象
			conn= DriverManager.getConnection(url,user,password);
		
		} catch (InstantiationException e) {
			// 自定义异常对象
			throw new DaoException("实例化异常",e);
//			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// // 自定义异常对象
			throw new DaoException("访问驱动异常",e);
//			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// 自定义异常
			throw new DaoException("驱动加载异常",e);
//			e.printStackTrace();
		}  catch (SQLException e) {
			// 自定义异常
			throw new DaoException("数据库访问异常",e);
//			e.printStackTrace();
		} 
		//返回得到的连接对象
		return conn;
	}
	
	/**
	 * 开启事务
	 * @param conn 要开启事务的数据库连接
	 */
	public static void beginTransaction(Connection conn){
		//将事务的自动提交模式设为假
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 提交事务
	 * @param conn
	 */
	public static void commit(Connection conn){
		try {//提交事务
			conn.commit();
			conn.setAutoCommit(true);//将事务的自动提交设为真
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 回滚事务
	 * @param conn
	 */
	public static void rollback(Connection conn){
		try {
			conn.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 关闭资源
	 */
	public static void closeStatement(ResultSet rs, Statement stmt){
		try {
			//如果查询结果集对象不为空，关闭该对象
			if (rs!=null){
				rs.close();
			}
			//如果声明对象不为空，关闭该对象
			if(stmt!=null){
				stmt.close(); 
			}
		} catch (SQLException e) {
					// 自定义异常对象
			throw new DaoException("关闭声明对象失败……",e);
//					e.printStackTrace();
				}

	}

}
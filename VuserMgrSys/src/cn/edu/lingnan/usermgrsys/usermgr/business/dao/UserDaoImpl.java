package cn.edu.lingnan.usermgrsys.usermgr.business.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import cn.edu.lingnan.usermgrsys.common.exceptiom.DaoException;
import cn.edu.lingnan.usermgrsys.common.util.DBUtils;
import cn.edu.lingnan.usermgrsys.common.util.TypeUtils;
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
	
	/**
	 * 用户登录
	 * @param Uname 用户名
	 * @param Passwd 用户密码
	 * @return 是否有该用户
	 */
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
	
	/**
	 * 添加用户/注册用户
	 * @param user 用户信息
	 * @return 添加/注册是否成功
	 */
	public boolean addUser(UserVO user){
		boolean flag = false;
		int rs = -1;
		PreparedStatement pstm = null;
		String sql = 
				"insert into v_user (UserId,Uname,Passwd,Rgsdate,Utype,Email，Status) values"
				+ "(?,?,?,?,?,?,?)";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, user.getUserId());
			pstm.setString(2, user.getUname());
			pstm.setString(3, user.getPasswd());
			pstm.setDate(4, new java.sql.Date(user.getRgsdate().getTime()));
			pstm.setString(5, user.getUtype());
			pstm.setString(6, user.getEmail());
			pstm.setInt(7, 0);
			//执行预编译更新语句，并将更新影响的结果行数返回给rs
			rs = pstm.executeUpdate();
			if(rs>0)
				flag = true;
			else
				flag = false;
				
		} catch (SQLException e) {
			System.out.println("添加用户时出错了，错误信息是："+e.getMessage());
			// 将异常封装成自定义异常
			throw new DaoException("执行数据库SQL语句时出错，请重试",e);
		}finally{
			DBUtils.closeStatement(pstm);
		}
		return flag;
	}
	
	/**
	 * 删除用户信息
	 * @param UserId 用户ID
	 * @return 删除结果
	 */
	@Override
	public boolean deleteUser(int UserId) {
		PreparedStatement pstam = null;
		int result = -1;
		boolean flag  = false;
		String sql = "delete from v_user where UserId = ?";
		
		try {
			//执行预编译语句
			pstam = conn.prepareStatement(sql);
			pstam.setInt(1, UserId);
			//把sql语句影响的行数返回给result
			result = pstam.executeUpdate();
			//如果影响的行数大于0，则证明删除成功，返回true,否则返回 false
			if(result > 0)
				flag = true;
			else
				flag = false;
			
		} catch (SQLException e) {
			System.out.println("删除用户时出错了，请重试！");
			throw new DaoException("数据库语句执行时出错",e);
		}finally{
			DBUtils.closeStatement(pstam);
		}
		return flag;
	}
	
	/**
	 * 修改用户信息
	 * @param UserId 用户Id
	 * @param Passwd 用户密码
	 * @param Email 用户Email
	 * @return 更新的结果
	 */
	@Override
	public boolean updateUserById(int UserId,String Passwd, String Email) {
		PreparedStatement pstam = null;
		int result = -1;
		boolean flag = false;
		String sql = "update v_user set Passwd = ?,Email = ? where UserId = ? and Utype not in ('管理员')";
		
		try {
			pstam = conn.prepareStatement(sql);
			pstam.setString(1, Passwd);
			pstam.setString(2, Email);
			pstam.setInt(3, UserId);
			
			result = pstam.executeUpdate();
			if(result>0)
				flag = true;
			else 
				flag = false;
		} catch (SQLException e) {
			System.out.println("修改用户信息时出错了，请重试！");
			System.out.println(e.getMessage());
			throw new DaoException("数据库语句执行时出错",e);
		}finally{
			DBUtils.closeStatement(pstam);
		}
		return flag;
	}
	
	/**
	 * 根据ID查询用户信息
	 * @param UserId 用户ID
	 * @return 用户信息
	 */
	@Override
	public UserVO searchUserById(int UserId) {
		UserVO user = null;
		PreparedStatement pstam = null;
		ResultSet rs = null;
		String sql = "select * from v_user where UserId = ?";
		
		try {
			pstam = conn.prepareStatement(sql);
			pstam.setInt(1, UserId);
			//把查询得到的结果放进结果集rs中
			rs = pstam.executeQuery();
			//遍历结果集中的记录，并把每一行记录中的属性赋值给user
			while(rs.next()){
				user = new UserVO();
				user.setUserId(rs.getInt("UserId"));
				user.setUname(rs.getString("Uname"));
				user.setPasswd(rs.getString("Passwd"));
				user.setRgsdate(rs.getDate("Rgsdate"));
				user.setEmail(rs.getString("Email"));
				user.setUtype(rs.getString("Utype"));
				user.setStatus(rs.getInt("Status"));
				
			}
		} catch (SQLException e) {
			System.out.println("查询用户信息时出错了，请重试！");
			throw new DaoException("数据库语句执行时出错",e);
		}finally{
			DBUtils.closeStatement(rs, pstam);
		}
		
		return user;
	}
	
	/**
	 * 根据用户名查询用户信息（不支持模糊查询）
	 * @param Uname 用户名
	 * @return 用户信息
	 */
	@Override
	public Vector<UserVO> searchByUname(String Uname) {
		Vector<UserVO> v = new Vector<UserVO>();
		PreparedStatement pstam = null;
		ResultSet rs = null;
		UserVO user = new UserVO();
		String sql = "select * from v_user where Uname = ?";

		try {
			pstam = conn.prepareStatement(sql);
			pstam.setString(1,Uname);
			
			//把查询得到的结果放进结果集rs中
			rs = pstam.executeQuery();
			//遍历结果集中的记录，并把每一行记录中的属性赋值给user
			while(rs.next()){
				
				user.setUserId(rs.getInt("UserId"));
				user.setUname(rs.getString("Uname"));
				user.setPasswd(rs.getString("Passwd"));
				user.setRgsdate(rs.getDate("Rgsdate"));
				user.setEmail(rs.getString("Email"));
				user.setUtype(rs.getString("Utype"));
				user.setStatus(rs.getInt("Status"));

				v.add(user);
			}
			
		} catch (SQLException e) {
			System.out.println("查询用户信息时出错了，请重试！");
			throw new DaoException("UserDaoImpl:数据库语句执行时出错",e);
		}finally{
			DBUtils.closeStatement(rs, pstam);
		}
		
		return v;
	}
	/**
	 * 查询全部用户信息
	 * @return 用户信息
	 */
	@Override
	public Vector<UserVO> searchAllUser() {
		Vector<UserVO> v = new Vector<UserVO>();
		PreparedStatement pstam = null;
		ResultSet rs = null;
		UserVO user = null;
		String sql = "select * from v_user";
		
		try {
			pstam = conn.prepareStatement(sql);
			//把查询得到的结果放进结果集rs中
			rs = pstam.executeQuery();
			//遍历结果集中的记录，并把每一行记录中的属性赋值给user
			while(rs.next()){
				user = new UserVO();
				user.setUserId(rs.getInt("UserId"));
				user.setUname(rs.getString("Uname"));
				user.setPasswd(rs.getString("Passwd"));
				user.setRgsdate(rs.getDate("Rgsdate"));
				user.setEmail(rs.getString("Email"));
				user.setUtype(rs.getString("Utype"));
				user.setStatus(rs.getInt("Status"));
				v.add(user);
			}
		} catch (SQLException e) {
			System.out.println("查询用户信息时出错了，请重试！");
			throw new DaoException("数据库语句执行时出错",e);
		}finally{
			DBUtils.closeStatement(rs, pstam);
		}
		
		return v;
	}
	/**
	 * 查找最大的用户ID号
	 */
	@Override
	public int findMaxId() {
		int MaxId = -1;
		ResultSet rs = null;
		PreparedStatement pstam = null;
		String sql = "select max(UserId) from v_user";
		
		try {
			pstam = conn.prepareStatement(sql);
			rs = pstam.executeQuery();
			if(rs.next())
			   MaxId = rs.getInt("max(UserId)");
		} catch (SQLException e) {
			throw new DaoException("查找最大ID号出错",e);
		}finally{
			DBUtils.closeStatement(rs, pstam);
		}
		return MaxId;
	}
	
	/**
	 * 分页查询
	 */
	@Override
	public Vector<UserVO> searchUserByPage(int pageNo,int pageSize) {
		Vector<UserVO> v = new Vector<UserVO>();
		PreparedStatement pstam = null;
		ResultSet rs = null;
		String sql = "select * from "
				+ "(select t2.*,rownum rn from "
				+ "(select t1.* from v_user t1 order by UserId) t2) " +
					"where rn>? and rn<=?";
		
		try {
			pstam = conn.prepareStatement(sql);
			pstam.setInt(1, (pageNo-1)*pageSize);
			pstam.setInt(2, pageNo*pageSize);
			//把查询得到的结果放进结果集rs中
			rs = pstam.executeQuery();
			//遍历结果集中的记录，并把每一行记录中的属性赋值给user
			while(rs.next()){
				UserVO user = new UserVO();
				user.setUserId(rs.getInt("UserId"));
				user.setUname(rs.getString("Uname"));
				user.setPasswd(rs.getString("Passwd"));
				user.setRgsdate(rs.getDate("Rgsdate"));
				user.setEmail(rs.getString("Email"));
				user.setUtype(rs.getString("Utype"));
				user.setStatus(rs.getInt("Status"));
				
				v.add(user);
			}
		} catch (SQLException e) {
			System.out.println("分页查询用户信息时出错了，请重试！");
			throw new DaoException("数据库语句执行时出错",e);
		}finally{
			DBUtils.closeStatement(rs, pstam);
		}
		
		return v;
	}

}

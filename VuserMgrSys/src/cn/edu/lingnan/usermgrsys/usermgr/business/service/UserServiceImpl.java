package cn.edu.lingnan.usermgrsys.usermgr.business.service;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;
import java.util.Vector;

import cn.edu.lingnan.usermgrsys.common.constant.EnumType;
import cn.edu.lingnan.usermgrsys.common.dao.DaoFactory;
import cn.edu.lingnan.usermgrsys.common.exceptiom.DaoException;
import cn.edu.lingnan.usermgrsys.common.exceptiom.ServiceException;
import cn.edu.lingnan.usermgrsys.common.util.DBUtils;
import cn.edu.lingnan.usermgrsys.common.util.TypeUtils;
import cn.edu.lingnan.usermgrsys.usermgr.business.dao.UserDao;
import cn.edu.lingnan.usermgrsys.usermgr.domain.UserVO;
/**
 * 用户service接口的实现类
 * @author 11638
 *
 */

public class UserServiceImpl implements UserService{

	/**
	 * 用户service实例
	 * 在类内部创建类唯一的实例
	 * 使用单例模式
	 */
	private static UserService UserService = new UserServiceImpl();
	
	/**
	 * 构造方法私有化
	 */
	private UserServiceImpl(){
		
	}
	/**
	 * 取得用户的实例对象
	 * 提供一个对外访问的接口
	 * @return 实例对象
	 */
	public static UserService getInstance(){
		return UserService;
	}
	
	/**
	 * 用户登录的service方法
	 * @param Uname 用户名
	 * @param Passwd 用户密码
	 * @return UserVO 用户信息
	 */
	public UserVO login(String Uname,String Passwd){
		//声明数据库连接对象，用于保存数据库连接对象
		Connection conn = null;
		UserVO user = null;
		try{
		//调用数据库工具类中的getConnection方法，取得数据库的连接对象，并赋值非数据库连接对象变量conn
		conn = DBUtils.getConnection();
		//调用dao工厂类的getDao方法，取得制定类型的Dao接口的实现类，并赋值给Dao接口变量
		UserDao userDao = (UserDao)DaoFactory.getDao(conn, EnumType.USER_DAO);
		//调用dao中的login方法，进行登录操作，结果赋值给登录结果变量
		user = userDao.login(Uname, Passwd);
		
		}catch(DaoException e){
			//将自定义异常抛出
			throw e;
		}catch(Exception e){
			//将异常封装成自定义异常并抛出
			throw new ServiceException("用户登录错误",e);
		}finally{
			DBUtils.closeConnection(conn);
		}
		//返回用户登录结果
		return user;
	}
	
	/**
	 * 用户注册/添加用户信息
	 * @param Uname 用户名
	 * @param Passwd 用户密码
	 * @param Email 用户邮箱
	 * @return boolean 添加/注册结果
	 */
	@Override
	public boolean addUser(String Uname, String Passwd, String Email) {
		// 声明数据库连接对象，用于保存数据库连接对象
		Connection conn = null;
		boolean result = false;
		UserVO user = new UserVO();
		user.setPasswd(Passwd);
		user.setEmail(Email);
		user.setUname(Uname);
		try{
		conn = DBUtils.getConnection();
		//调用数据库工具类中的getConnection方法，取得数据库的连接对象，并赋值非数据库连接对象变量conn
		conn = DBUtils.getConnection();
		
		//调用dao工厂类的getDao方法，取得制定类型的Dao接口的实现类，并赋值给Dao接口变量
		UserDao userDao = (UserDao)DaoFactory.getDao(conn, EnumType.USER_DAO);
		int maxId = userDao.findMaxId()+1;
		//调用DBUtils数据库工具类中的beginTransaction方法，开启事务
		DBUtils.beginTransaction(conn);
		//把UserId最大值+1作为新用户的ID
		user.setUserId(maxId);
		//新注册的用户类型默认为普通用户
		user.setUtype("普通用户");
		user.setStatus(0);
		user.setRgsdate(new java.util.Date());
		//调用userDao中的addUser方法，并将返回值赋给result
		result = userDao.addUser(user);
		//调用DBUtils数据库工具类中的commit方法，提交事务
		DBUtils.commit(conn);
		//如果操作过程中出现异常，抛出异常，并回滚事务
		}catch (DaoException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
		}
		return result;
	}
	
	/**
	 * 删除用户
	 * @param UserId 用户ID
	 * @return Boolean 删除结果
	 */
	@Override
	public boolean deleteUser(int UserId) {
		// 声明数据库连接对象，用于保存数据库连接对象
			Connection conn = null;
			boolean result = false;
			try{
			conn = DBUtils.getConnection();
			//调用数据库工具类中的getConnection方法，取得数据库的连接对象，并赋值非数据库连接对象变量conn
			conn = DBUtils.getConnection();
			
			//调用dao工厂类的getDao方法，取得制定类型的Dao接口的实现类，并赋值给Dao接口变量
			UserDao userDao = (UserDao)DaoFactory.getDao(conn, EnumType.USER_DAO);
			//调用userDao中的deleteUser方法，并将返回值赋给result
			result = userDao.deleteUser(UserId);
			//调用DBUtils数据库工具类中的commit方法，提交事务
			DBUtils.commit(conn);
			//如果操作过程中出现异常，抛出异常，并回滚事务
			}catch (DaoException e){
				throw e;
			}catch(Exception e){
				DBUtils.rollback(conn);
			}
			return result;
	}
	
	/**
	 * 修改用户信息（只能修改密码和邮箱）
	 * @param UserId 用户ID
	 * @param Passwd 用户密码
	 * @param Email 用户 邮箱
	 * @return boolean 修改是否成功
	 */
	@Override
	public boolean updateUserById(int UserId, String Passwd, String Email) {
		// 声明数据库连接对象，用于保存数据库连接对象
		Connection conn = null;
		boolean result = false;
		try{
		conn = DBUtils.getConnection();
		//调用数据库工具类中的getConnection方法，取得数据库的连接对象，并赋值非数据库连接对象变量conn
		conn = DBUtils.getConnection();
		
		//调用dao工厂类的getDao方法，取得制定类型的Dao接口的实现类，并赋值给Dao接口变量
		UserDao userDao = (UserDao)DaoFactory.getDao(conn, EnumType.USER_DAO);
		
		//调用userDao中的updateUserById方法，并将返回值赋给result
		result = userDao.updateUserById(UserId, Passwd, Email);
		//调用DBUtils数据库工具类中的commit方法，提交事务
		DBUtils.commit(conn);
		//如果操作过程中出现异常，抛出异常，并回滚事务
		}catch (DaoException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
		}
		return result;
	}
	
	/**
	 * 通过用户ID查找用户信息
	 * @param UserId 用户Id
	 * @return user 用户信息
	 */
	@Override
	public UserVO searchUserById(int UserId) {
		//声明数据库连接对象，用于保存数据库连接对象
		Connection conn = null;
		UserVO user = null;
		try{
		//调用数据库工具类中的getConnection方法，取得数据库的连接对象，并赋值非数据库连接对象变量conn
		conn = DBUtils.getConnection();
		//调用dao工厂类的getDao方法，取得制定类型的Dao接口的实现类，并赋值给Dao接口变量
		UserDao userDao = (UserDao)DaoFactory.getDao(conn, EnumType.USER_DAO);
		
		//调用dao中的searchUserById方法，进行查询操作，并将结果传递给user变量
		user = userDao.searchUserById(UserId);
		
		}catch(DaoException e){
			//将自定义异常抛出
			throw e;
		}catch(Exception e){
			//将异常封装成自定义异常并抛出
			throw new ServiceException("查找用户出错啦！！",e);
		}finally{
			DBUtils.closeConnection(conn);
		}
		//返回用户登录结果
		return user;
	}
	
	/**
	 * 根据用户名查找用户信息（不支持模糊查询）
	 * @param Uname 用户名
	 * @return 用户信息
	 */
	@Override
	public Vector<UserVO> searchByUname(String Uname) {
		Connection conn = null;
		Vector<UserVO> v = new Vector<UserVO>();
		try{
		//调用数据库工具类中的getConnection方法，取得数据库的连接对象，并赋值非数据库连接对象变量conn
		conn = DBUtils.getConnection();
		//调用dao工厂类的getDao方法，取得制定类型的Dao接口的实现类，并赋值给Dao接口变量
		UserDao userDao = (UserDao)DaoFactory.getDao(conn, EnumType.USER_DAO);
		
		//调用dao中的searchByUname方法，进行查询操作，并将结果传递给Vector类型变量
		v = userDao.searchByUname(Uname);
		DBUtils.commit(conn);
	
		}catch(DaoException e){
			//将自定义异常抛出
			throw e;
		}catch(Exception e){
			//将异常封装成自定义异常并抛出
			DBUtils.rollback(conn);
			System.out.println(e.getMessage());
//			throw new ServiceException("UserServiceImpl查找用户出错啦！！",e);
		}finally{
			DBUtils.closeConnection(conn);
		}
		//返回用户查询结果集
		return v;
	}
	
	/**
	 * 查询全部用户信息
	 * @return UserVO用户信息
	 */
	@Override
	public Vector<UserVO> searchAllUser() {
		//声明数据库连接对象，用于保存数据库连接对象
		Connection conn = null;
		Vector<UserVO> v = new Vector<UserVO>();
		UserVO user = null;
		try{
		//调用数据库工具类中的getConnection方法，取得数据库的连接对象，并赋值非数据库连接对象变量conn
		conn = DBUtils.getConnection();
		//调用dao工厂类的getDao方法，取得制定类型的Dao接口的实现类，并赋值给Dao接口变量
		UserDao userDao = (UserDao)DaoFactory.getDao(conn, EnumType.USER_DAO);
		
		//调用dao中的searchAllUser方法，查询所有用户的信息
		v = userDao.searchAllUser();
		
		}catch(DaoException e){
			//将自定义异常抛出
			throw e;
		}catch(Exception e){
			//将异常封装成自定义异常并抛出
			throw new ServiceException("查找用户出错啦！！",e);
		}finally{
			DBUtils.closeConnection(conn);
		}
		//返回用户登录结果
		return v;
	}
	
	/**
	 * 分页查询
	 * @return UserVO 用户信息
	 */
	@Override
	public Vector<UserVO> searchUserByPage(int pageNo,int pageSize) {
		//声明数据库连接对象，用于保存数据库连接对象
		Connection conn = null;
		Vector<UserVO> v = new Vector<UserVO>();
		UserVO user = null;
		try{
		//调用数据库工具类中的getConnection方法，取得数据库的连接对象，并赋值非数据库连接对象变量conn
		conn = DBUtils.getConnection();
		//调用dao工厂类的getDao方法，取得制定类型的Dao接口的实现类，并赋值给Dao接口变量
		UserDao userDao = (UserDao)DaoFactory.getDao(conn, EnumType.USER_DAO);
		
		//调用dao中的searchUserByPage方法，进行分页查找用户信息的操作
		v = userDao.searchUserByPage(pageNo,pageSize);
		
		}catch(DaoException e){
			//将自定义异常抛出
			throw e;
		}catch(Exception e){
			//将异常封装成自定义异常并抛出
			throw new ServiceException("查找用户出错啦！！",e);
		}finally{
			DBUtils.closeConnection(conn);
		}
		//返回用户登录结果
		return v;
	}
}

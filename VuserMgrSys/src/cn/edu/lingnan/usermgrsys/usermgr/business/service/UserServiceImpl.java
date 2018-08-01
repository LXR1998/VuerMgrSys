package cn.edu.lingnan.usermgrsys.usermgr.business.service;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

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
	 * @param Uname
	 * @param Passwd
	 * @return UserVO 
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
	 * @param user用户信息
	 * @return 添加/注册结果
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
	 * @param UserId
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
	 * @param UserId
	 * @param Passwd
	 * @param Email
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
		//调用userDao中的deleteUser方法，并将返回值赋给result
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
		
		//调用dao中的login方法，进行登录操作，结果赋值给登录结果变量
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
	 * 根据用户名查找用户信息（支持模糊查询）
	 * @param Uname用户名
	 * @return user用户信息
	 */
	@Override
	public List<UserVO> searchByUname(String Uname) {
		Connection conn = null;
		List<UserVO> ListUV = null;
		UserVO user = null;
		try{
		//调用数据库工具类中的getConnection方法，取得数据库的连接对象，并赋值非数据库连接对象变量conn
		conn = DBUtils.getConnection();
		//调用dao工厂类的getDao方法，取得制定类型的Dao接口的实现类，并赋值给Dao接口变量
		UserDao userDao = (UserDao)DaoFactory.getDao(conn, EnumType.USER_DAO);
		
		//调用dao中的login方法，进行登录操作，结果赋值给登录结果变量
		ListUV = userDao.searchAllUser();
		
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
		return ListUV;
	}
	
	/**
	 * 查询全部用户信息
	 * @return UserVO用户信息
	 */
	@Override
	public List<UserVO> searchAllUser() {
		//声明数据库连接对象，用于保存数据库连接对象
		Connection conn = null;
		List<UserVO> ListUV = null;
		UserVO user = null;
		try{
		//调用数据库工具类中的getConnection方法，取得数据库的连接对象，并赋值非数据库连接对象变量conn
		conn = DBUtils.getConnection();
		//调用dao工厂类的getDao方法，取得制定类型的Dao接口的实现类，并赋值给Dao接口变量
		UserDao userDao = (UserDao)DaoFactory.getDao(conn, EnumType.USER_DAO);
		
		//调用dao中的login方法，进行登录操作，结果赋值给登录结果变量
		ListUV = userDao.searchAllUser();
		
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
		return ListUV;
	}
	
	/**
	 * 分页查询
	 * @return UserVO 用户信息
	 */
	@Override
	public List<UserVO> searchUserByPage(int UserId) {
		//声明数据库连接对象，用于保存数据库连接对象
		Connection conn = null;
		List<UserVO> ListUV = null;
		UserVO user = null;
		try{
		//调用数据库工具类中的getConnection方法，取得数据库的连接对象，并赋值非数据库连接对象变量conn
		conn = DBUtils.getConnection();
		//调用dao工厂类的getDao方法，取得制定类型的Dao接口的实现类，并赋值给Dao接口变量
		UserDao userDao = (UserDao)DaoFactory.getDao(conn, EnumType.USER_DAO);
		
		//调用dao中的login方法，进行登录操作，结果赋值给登录结果变量
		ListUV = userDao.searchUserByPage(UserId);
		
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
		return ListUV;
	}
}

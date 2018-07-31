package cn.edu.lingnan.usermgrsys.usermgr.business.service;

import java.sql.Connection;

import cn.edu.lingnan.usermgrsys.common.constant.EnumType;
import cn.edu.lingnan.usermgrsys.common.dao.DaoFactory;
import cn.edu.lingnan.usermgrsys.common.exceptiom.DaoException;
import cn.edu.lingnan.usermgrsys.common.exceptiom.ServiceException;
import cn.edu.lingnan.usermgrsys.common.util.DBUtils;
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
		//声明数据库连接对象，用户保存数据库连接对象
		Connection conn = null;
		UserVO user = null;
		try{
		//调用数据库工具类中的getConnection方法，取得数据库的连接对象，并赋值非数据库连接对象变量conn
		conn = DBUtils.getConnection();
		//调用dao工厂类的getDao方法，取得制定类型的Dao接口的实现类，并赋值给Dao接口变量
		UserDao userMgrDao = (UserDao)DaoFactory.getDao(conn, EnumType.USER_DAO);
		//调用dao中的login方法，进行登录操作，结果赋值给登录结果变量
		user = userMgrDao.login(Uname, Passwd);
		
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
}

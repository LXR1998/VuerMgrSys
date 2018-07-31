package cn.edu.lingnan.usermgrsys.usermgr.business.dao;

import java.sql.Date;

import cn.edu.lingnan.usermgrsys.common.dao.BaseDao;
import cn.edu.lingnan.usermgrsys.usermgr.domain.UserVO;

/**
 * 用户dao接口
 * @author 11638
 *
 */
public interface UserDao extends BaseDao{
	/**
	 * 用户登录
	 * @param user 用户信息
	 * @return 用户信息
	 */
	public  UserVO login(String name,String password);
	
	/**
	 * 添加用户/注册用户
	 * @param user 用户信息
	 * @return Boolean 添加/注册是否成功
	 */
//	public boolean addUser(String Uname,String Passwd,Date Rgsdate,)

	
	
	
}

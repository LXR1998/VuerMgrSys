package cn.edu.lingnan.usermgrsys.usermgr.business.dao;

import cn.edu.lingnan.usermgrsys.common.dao.BaseDao;
import cn.edu.lingnan.usermgrsys.usermgr.domain.UserVO;

public interface UserDao extends BaseDao{
	/**
	 * 用户登录
	 * @param user 用户信息
	 * @return 用户信息
	 */
	public UserVO login(String name,String password);
}

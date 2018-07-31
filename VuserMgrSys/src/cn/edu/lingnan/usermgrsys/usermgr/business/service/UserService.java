package cn.edu.lingnan.usermgrsys.usermgr.business.service;

import cn.edu.lingnan.usermgrsys.usermgr.domain.UserVO;

/**
 * service层接口
 * @author 11638
 *
 */
public interface UserService {
	/**
	 * 用户登录
	 * @param user 用户信息
	 * @return 用户信息
	 */
	public  UserVO login(String name,String password);


}

package cn.edu.lingnan.usermgrsys.usermgr.business.service;

import java.util.List;

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
	
	/**
	 * 用户注册/添加用户信息
	 * @param user用户信息
	 * @return user用户信息
	 */
	public boolean addUser(String Uname,String Passwd,String Email);
	
	/**
	 * 删除用户信息
	 * @param 用户ID
	 * @return Boolean 删除结果
	 */
	public boolean deleteUser(int UserId);
	
	/**
	 * 修改用户信息
	 * @param 用户密码，用户Email，
	 */
	public boolean updateUserById(int UserId,String Passwd,String Email);
	
	/**
	 * 根据ID查询用户信息
	 * @param 用户ID
	 * @return UserVO用户信息
	 */
	public UserVO searchUserById(int UserId);
	
	/**
	 * 根据用户名查询用户信息（支持模糊查询）
	 * @param 用户名
	 * @return 用户信息
	 */
	public List<UserVO> searchByUname(String Uname);
	
	/**
	 * 查询全部用户信息
	 * @return UserVO用户信息
	 */
	public List<UserVO> searchAllUser();
	
	/**
	 * 分页查询
	 * @return UserVO 用户信息
	 */
	public List<UserVO> searchUserByPage(int UserId);

}

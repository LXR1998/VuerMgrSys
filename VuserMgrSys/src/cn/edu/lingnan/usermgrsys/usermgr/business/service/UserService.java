package cn.edu.lingnan.usermgrsys.usermgr.business.service;

import java.util.List;
import java.util.Vector;

import cn.edu.lingnan.usermgrsys.usermgr.domain.UserVO;

/**
 * service层接口
 * @author 11638
 *
 */
public interface UserService {
	/**
	 * 用户登录
	 * @param name 用户名
	 * @param password 用户密码
	 * @return UserVO 用户信息
	 */
	public  UserVO login(String name,String password);
	
	/**
	 * 用户注册/添加用户信息
	 * @param Uname 用户名
	 * @param Passwd 用户密码
	 * @param Email 用户邮箱
	 * @return boolean 添加用户是否成功
	 */
	public boolean addUser(String Uname,String Passwd,String Email);
	
	/**
	 * 删除用户信息
	 * @param UserId 用户ID
	 * @return Boolean 删除结果
	 */
	public boolean deleteUser(int UserId);
	
	/**
	 * 修改用户信息
	 * @param UserId 用户Id
	 * @param Passwd 用户密码
	 * @param Email 用户邮箱
	 * @return boolean 返回修改的结果是否成功
	 */
	public boolean updateUserById(int UserId,String Passwd,String Email);
	
	/**
	 * 根据ID查询用户信息
	 * @param UserId 用户ID
	 * @return UserVO 用户信息
	 */
	public UserVO searchUserById(int UserId);
	
	/**
	 * 根据用户名查询用户信息（支持模糊查询）
	 * @param Uname 用户名
	 * @return 用户信息
	 */
	public Vector<UserVO> searchByUname(String Uname);
	
	/**
	 * 查询全部用户信息
	 * @return UserVO用户信息
	 */
	public Vector<UserVO> searchAllUser();
	
	
	/**
	 * 分页查询
	 * @param pageNo 想要查询的页码
	 * @param pageSize 每页的记录数
	 * @return 用户信息集
	 */
	public Vector<UserVO> searchUserByPage(int pageNo, int pageSize);


}

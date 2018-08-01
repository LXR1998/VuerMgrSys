package cn.edu.lingnan.usermgrsys.usermgr.business.dao;

import java.sql.Date;
import java.util.List;

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
	public boolean addUser(UserVO user);

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
	
	/**
	 * 查询最大的ID号
	 */
	public int findMaxId();
	
	/**
	 * 查询所有有效用户
	 */
	public List<UserVO> findAllValid();
	
	/**
	 * 获取记录数量
	 */
	public int getRecordCount();
	
	
}

package cn.edu.lingnan.usermgrsys.usermgr.business.dao;

import java.sql.Date;
import java.util.List;
import java.util.Vector;

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
	 * @param name 用户名
	 * @param password 用户密码
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
	 * @param UserId 用户ID
	 * @return 删除结果
	 */
	public boolean deleteUser(int UserId);
	
	/**
	 * 修改用户信息
	 * @param UserId 用户id
	 * @param Passwd 用户密码
	 * @param Email 用户Email
	 * @return 修改结果
	 */
	public boolean updateUserById(int UserId,String Passwd,String Email);
	
	/**
	 * 根据id查找用户信息
	 * @param UserId 用户ID
	 * @return 用户信息
	 */
	public UserVO searchUserById(int UserId);
	
	/**
	 * 根据用户名查找用户信息
	 * @param Uname 用户名
	 * @return 用户信息的结果集
	 */
	public Vector<UserVO> searchByUname(String Uname);
	
	/**
	 * 查询所有用户的信息
	 * @return 所有用户的信息的结果集
	 */
	public Vector<UserVO> searchAllUser();

	/**
	 * 分页查询
	 * @param pageNo 需要的分页页数
	 * @param pageSize 每页的记录数
	 * @return 所求的页面的用户信息
	 */
	public Vector<UserVO> searchUserByPage(int pageNo, int pageSize);
	
	/**
	 * 查找最大的ID号
	 * @return 最大Id号
	 */
	public int findMaxId();
	
	
	

	
	
}

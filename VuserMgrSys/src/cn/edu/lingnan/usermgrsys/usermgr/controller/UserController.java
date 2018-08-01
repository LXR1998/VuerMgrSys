package cn.edu.lingnan.usermgrsys.usermgr.controller;

import cn.edu.lingnan.usermgrsys.usermgr.business.service.UserService;
import cn.edu.lingnan.usermgrsys.usermgr.business.service.UserServiceImpl;
import cn.edu.lingnan.usermgrsys.usermgr.domain.UserVO;

/**
 * 控制层，调用service层
 * @author 11638
 *
 */
public class UserController {

	//声明用户service接口对象，用于业务处理
	UserService userMgrService = UserServiceImpl.getInstance();
	/**
	 * 用户登录
	 * @param Uname用户名
	 * @param Passwd 登录密码
	 * @return 用户信息
	 */
	public UserVO dologin(String Uname,String Passwd){
		UserVO user = null;
		try{
		//调用用户Service接口中的login方法，进行用户登录操作
		user =  userMgrService.login(Uname, Passwd);
		}catch(Exception e){
			//显示异常信息
			System.out.println("用户登录时出错了"+e.getMessage());;
		}
		return user;
	}
	
	/**
	 * 用户注册/添加用户信息
	 * @param user用户信息
	 * @return 添加/注册结果
	 */
	public boolean doaddUser(String Uname,String Passwd,String Email){
		boolean result = false;
		try{
			result = userMgrService.addUser(Uname, Passwd, Email);
		}catch(Exception e){
			System.out.println("用户注册/添加时出错了"+e.getMessage());
		}
		return result;
	}
	
	/**
	 * 删除用户
	 * @param UserID
	 * @return 删除结果
	 */
	public boolean dodeleteUser(int UserId){
		boolean result = false;
		try{
			result = userMgrService.deleteUser(UserId);
		}catch(Exception e){
			System.out.println("删除用户时出错了"+e.getMessage());
		}
		return result;
	}
	
	/**
	 * 修改用户信息
	 */
	public boolean doupdateUserById(int UserId,String Passwd,String Email){
		boolean result = false;
		try{
			result = userMgrService.updateUserById(UserId, Passwd, Email);
		}catch(Exception e){
			System.out.println("修改用户信息时出错了"+e.getMessage());
		}
		return result;
	}

	/**
	 * 根据用户Id查找用户信息
	 * @param UserId
	 * @return user用户信息
	 */
	public UserVO dosearchUserById(int UserId){
		UserVO user = null;
		try{
		//调用用户Service接口中的login方法，进行用户登录操作
		user =  userMgrService.searchUserById(UserId);
		}catch(Exception e){
			//显示异常信息
			System.out.println("用户登录时出错了"+e.getMessage());;
		}
		return user;
	}

	/**
	 * 根据用户名查找用户信息（支持模糊查询）
	 * @param Uname 用户名
	 * @return user用户信息
	 */
	
	/**
	 * 查询全部用户信息
	 * @return UserVO用户信息
	 */
	
	/**
	 * 分页查询
	 * @return UserVO 用户信息
	 */
	

}

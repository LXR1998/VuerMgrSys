package cn.edu.lingnan.usermgrsys.usermgr.controller;

import java.util.List;
import java.util.Vector;

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
	 * @param Uname 用户名
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
	 * @param Uname 用户名
	 * @param Passwd 用户密码
	 * @param Email 用户Email
	 * @return 添加/注册结果
	 */
	public boolean doaddUser(String Uname,String Passwd,String Email){
		boolean result = false;
		try{
			//调用用户Service接口中的addUser方法，添加用户，并将添加结果返回给result
			result = userMgrService.addUser(Uname, Passwd, Email);
		}catch(Exception e){
			System.out.println("用户注册/添加时出错了"+e.getMessage());
		}
		return result;
	}
	
	/**
	 * 删除用户
	 * @param UserId 用户ID
	 * @return boolean 删除结果
	 */
	public boolean dodeleteUser(int UserId){
		boolean result = false;
		try{
			//调用用户Service接口中的deleteUser方法，删除用户，并将删除结果返回给result
			result = userMgrService.deleteUser(UserId);
		}catch(Exception e){
			System.out.println("删除用户时出错了"+e.getMessage());
		}
		return result;
	}
	
	/**
	 * 修改用户信息
	 * @param UserId 用户ID
	 * @param Passwd 用户密码
	 * @param Email 用户邮箱
	 * @return boolean 执行更新结果 
	 */
	public boolean doupdateUserById(int UserId,String Passwd,String Email){
		boolean result = false;
		try{
			//调用用户Service接口中的updateUserById方法，修改用户信息，并将修改结果返回给result
			result = userMgrService.updateUserById(UserId, Passwd, Email);
		}catch(Exception e){
			System.out.println("修改用户信息时出错了"+e.getMessage());
		}
		return result;
	}

	/**
	 * 根据用户Id查找用户信息
	 * @param UserId 用户ID
	 * @return user 用户信息
	 */
	public UserVO dosearchUserById(int UserId){
		UserVO user = null;
		try{
		//调用用户Service接口中的searchUserById方法，进行用户信息查询操作，将结果返回给user变量
		user =  userMgrService.searchUserById(UserId);
		}catch(Exception e){
			//显示异常信息
			System.out.println("查找用户时出错了"+e.getMessage());;
		}
		return user;
	}

	/**
	 * 根据用户名查找用户信息（支持模糊查询）
	 * @param Uname 用户名
	 * @return user用户信息
	 */
	public Vector<UserVO> dosearchByUname(String Uname){
		Vector<UserVO> v = new Vector<UserVO>();
		try{
		//调用用户Service接口中的searchByUname方法，根据用户名进行用户信息查询操作，
			v =  userMgrService.searchByUname(Uname);
		}catch(Exception e){
			//显示异常信息
			System.out.println("UserControllers根据用户名查找用户时出错了"+e.getMessage());;
		}
		return v;
	}
	
	/**
	 * 查询全部用户信息
	 * @return UserVO用户信息
	 */
	public Vector<UserVO> dosearchAllUser(){
		Vector<UserVO> v = new Vector<UserVO>();
		try{
		//调用用户Service接口中的searchAllUser方法，查找所有用户信息
			v =  userMgrService.searchAllUser();
		}catch(Exception e){
			//显示异常信息
			System.out.println("UserControllers:查找用户时出错了"+e.getMessage());;
		}
		return v;
	}
	/**
	 * 分页查询
	 * @param pageNo 想要的分页数
	 * @param pageSize 每页的记录数
	 * @return 用户信息
	 */
	public Vector<UserVO> dosearchUserByPage(int pageNo,int pageSize){
		Vector<UserVO> v = new Vector<UserVO>();
		try{
		//调用用户Service接口中的searchUserByPage方法，进行分页查找用户信息
			v =  userMgrService.searchUserByPage(pageNo, pageSize);
		}catch(Exception e){
			//显示异常信息
			System.out.println("UserControllers:分页查找用户时出错了"+e.getMessage());;
		}
		return v;
	}
	

}

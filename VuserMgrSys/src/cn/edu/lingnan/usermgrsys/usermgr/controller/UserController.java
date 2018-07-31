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
	
	
	
}

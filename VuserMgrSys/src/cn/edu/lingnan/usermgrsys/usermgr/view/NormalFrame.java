package cn.edu.lingnan.usermgrsys.usermgr.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import cn.edu.lingnan.usermgrsys.usermgr.controller.UserController;
import cn.edu.lingnan.usermgrsys.usermgr.domain.UserVO;

/**
 * 普通用户登录后的显示界面
 * @author 11638
 *
 */
public class NormalFrame extends IndexFrame{
	
	//用户对象
	UserVO user = null;
	/**
	 * 带参数的构造方法，用于初始化user属性
	 * @param user 用户信息
	 */
	public NormalFrame(UserVO user){
		this.user = user;
	}
	
	
	
	/**
	 * 登陆后显示的界面
	 * @param UserId 用户ID
	 */
	public void show(int UserId){
		//声明缓冲处理流对象，用于接收后台输入的数据
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true){
			System.out.println("修改自己的信息-----------------1");
			System.out.println("查询自己的信息-----------------2");
			System.out.println("程序退出-----------------3");
			
			int i = 1;
			while(true){
			try {
				i=Integer.parseInt(br.readLine());
				break;
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("您输入的指令有误，请重新输入");
			}
		}
			switch(i){
			case 1:
				this.updateShow(UserId);
				break;//中断switch
			case 2:
				this.searchShow(UserId);//中断switch
				break;
			case 3:
				System.out.println("感谢您的使用，再会！");
				System.exit(0);
			default:
				System.out.println("您的输入有误，请重新输入！");
				System.out.println();
			}
		}
		
	}

	
	/**
	 * 修改用户个人信息界面
	 *  @param UserId 用户ID
	 */
	public void updateShow(int UserId){
		//声明缓冲处理流对象，用于接收后台输入的数据
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean result = false;
		System.out.println("          用户信息修改界面 ");
		System.out.println("=================================");
		while(true){
			String Email = null;
			String Passwd = null;
			while(true){
				try {
					System.out.println("请输入需要修改的密码");
					Passwd = br.readLine();
					System.out.println("请输入需要修改的Email");
					Email = br.readLine();
					break;
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
			//声明控制层对象
			UserController UserController = new UserController();
			//调用控制层对象中的方法，并将结果赋给result
			result = UserController.doupdateUserById(UserId, Passwd, Email);
			if(result){
				System.out.println("个人信息修改成功");
				break;
			}else{
				System.out.println("个人信息修改失败！！");
				break;
			}
		}	
		
	}
	 
	
	/**
	 * 查询用户个人信息界面
	 * @param UserId 用户ID
	 */
	public void searchShow(int UserId){
		//声明缓冲处理流对象，用于接收后台输入的数据
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true){
			//声明控制层对象
			UserController UserController = new UserController();
			//调用控制层对象中的方法，并将结果赋给result
			UserVO user = UserController.dosearchUserById(UserId);
			if(user != null){
				System.out.println("个人信息如下：");
				System.out.print
				("用户ID:"+user.getUserId()+"  用户名 :"+user.getUname()+"   用户密码:"+user.getPasswd());
				System.out.print("   用户邮箱："+user.getEmail()+"  用户权限："+user.getUtype());
				System.out.println();
				break;
			}else{
				System.out.println("查找信息失败！！！");
				break;
			}
		}
	}
}

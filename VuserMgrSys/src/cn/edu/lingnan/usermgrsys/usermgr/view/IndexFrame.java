package cn.edu.lingnan.usermgrsys.usermgr.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import cn.edu.lingnan.usermgrsys.usermgr.controller.UserController;
import cn.edu.lingnan.usermgrsys.usermgr.domain.UserVO;

public class IndexFrame implements BaseFrame{
	
	/**
	 * 主页面显示
	 */
	public void show(){
		//声明一个缓冲处理流对象，用于接收控制台输入的数据
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//设置一个循环体，用于显示控制台信息
		while(true){
			//用户登录和注册界面
			System.out.println("      欢迎来到会员管理系统     ");
			System.out.println("=================================");
			System.out.println("用户登录-----------------------1");
			System.out.println("用户注册-----------------------2");
			System.out.println("退出系统-----------------------3");
			
			int i = -1;
			//读取用户从控制台输入的信息，如果输入的值正确，则中断循环，否则提示错误信息，返回循环
			while(true){
				try {
					i =Integer.parseInt(br.readLine());	
					break;
				} catch (Exception e) {
					//提示错误
					System.out.println("输入错误，您只能输入1~3的数字");
					System.out.println("请您重新输入：");
					System.out.println();
					
				}
			}switch(i){
			case 1:
				this.LoginShow();
				break;//中断switch
			case 2:
				this.addShow();//中断switch
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
	 * 登录页面
	 */
	public void LoginShow(){
		//声明缓冲对象,用于接收控制台输入的信息
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("          用户登录界面 ");
		System.out.println("=================================");
		System.out.println("请输入您的用户名：");
		 
		try {
			String Uname = br.readLine();
			System.out.println("请输入您的密码：");
			String Passwd = br.readLine();
			//声明控制层对象
			UserController UserController = new UserController();
			//调用控制层的dologin方法,并将控制层得到的返回值数据传给user
			UserVO user = UserController.dologin(Uname, Passwd);
			//如果user的数据不为空，则表示用户名和密码能够对应
			if(user != null){
				System.out.println("登陆成功");
				System.out.println("=================================");
				//调用主界面
				AdminFrame m = new AdminFrame(user);
				m.loginSuccShow();
				//退出当前界面
				System.exit(0);
			}else{
				//登陆失败，显示失败信息
				System.out.println("登陆失败！！");
			}
			} catch (IOException e) {
				// 显示异常信息
				System.out.println(e.getMessage());
			}
		}
		
	/**
	 * 注册页面
	 */
	@Override 
	public void addShow() {
		
		//声明缓冲对象，用于接收而控制台输入的信息
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean result = true; 
		System.out.println("          用户注册界面 ");
		System.out.println("=================================");
		System.out.println("请输入您的用户名：");
		try {
			String Uname = br.readLine();
			System.out.println("请设置您的用户密码：");
			String Passwd = br.readLine();
			System.out.println("请输入您的邮箱：");
			String Email = br.readLine();
			
			//声明控制层对象
			UserController UserController = new UserController();
			//调用控制层对象中的方法，并将结果赋给result
			result = UserController.doaddUser(Uname, Passwd, Email);
			if(result){
				System.out.println("注册成功");
			}else{
				System.out.println("注册失败！！");
			}
			
		} catch (IOException e) {
			//显示异常信息 
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void searchShow() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateShow() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteShow() {
		// TODO Auto-generated method stub
		
	}

}

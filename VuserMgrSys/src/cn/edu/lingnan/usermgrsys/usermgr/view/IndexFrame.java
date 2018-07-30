package cn.edu.lingnan.usermgrsys.usermgr.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
					i=Integer.parseInt(br.readLine());
					break;
				
				} catch (Exception e) {
					//提示错误
					System.out.println("输入错误，您只能输入1~3的数字");
					System.out.println("请您重新输入：");
				}
				
				switch(i){
				case 1:
					this.LoginShow();
				case 2:
					this.addShow(type);
				case 3:
					System.out.println("感谢您的使用，再会！");
					System.exit(0);
				default:
					System.out.println("您的输入有误，请重新输入！");
				}
			}
			
			
		}
	}

	/**
	 * 注册页面
	 */
	@Override 
	public void addShow(String type) {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
	}

	@Override
	public void searchShow() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateShow(String type, UserVO user) {
		// TODO Auto-generated method stub
		
	}

}

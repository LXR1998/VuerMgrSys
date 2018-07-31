package cn.edu.lingnan.usermgrsys.usermgr.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import cn.edu.lingnan.usermgrsys.usermgr.domain.UserVO;
/**
 * 用于用户管理，权限分为管理员和非管理员
 * 
 * @author 11638
 *
 */
public class AdminFrame extends NormalFrame{

	/**
	 * 带参数的构造方法，用于初始化User属性
	 * @param user
	 */
	public AdminFrame(UserVO user){
		super(user);
	}
	public void loginSuccShow(){
		System.out.println("欢迎登录主窗体");
		System.out.println(user.getUname()+ "您好："+"您的身份是："+user.getUtype());
		System.out.println("==============================");
		//管理员管理
		if(user.getUtype().equals("管理员"))
		{
			this.show();
		}
		else{
			new NormalFrame(user).show();
		}	
	}
	/**
	 * 登陆成功后的不同的用户操作页面
	 */
	public void show(){
		//声明缓冲处理流对象，用于接收后台输入的数据
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true){
			System.out.println("添加用户-----------------1");
			System.out.println("删除用户-----------------2");
			System.out.println("修改用户-----------------3");
			System.out.println("查询用户-----------------4");
			System.out.println("程序退出-----------------5");
			
			int i =-1 ;
			while(true){
				try {
					i = Integer.parseInt(br.readLine());
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					System.out.println("您输入的指令有误，只能输入1~5的数字，请重试！");
				}
				
				}
			}
			
		}
	}
	

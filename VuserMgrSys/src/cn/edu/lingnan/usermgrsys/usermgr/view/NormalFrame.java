package cn.edu.lingnan.usermgrsys.usermgr.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
	 * @param user
	 */
	public NormalFrame(UserVO user){
		this.user = user;
	}
	
	
	
	/**
	 * 登陆后显示的界面
	 */
	public void show(){
		//声明缓冲处理流对象，用于接收后台输入的数据
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				while(true){
					System.out.println("修改自己的信息-----------------1");
					System.out.println("查询自己的信息-----------------2");
					System.out.println("程序退出-----------------3");
					
					int i = 1;
					try {
						i=Integer.parseInt(br.readLine());
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						System.out.println("您输入的指令有误，请重新输入");
					}
				}
		
	}

}

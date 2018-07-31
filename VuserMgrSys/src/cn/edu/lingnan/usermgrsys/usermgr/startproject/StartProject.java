package cn.edu.lingnan.usermgrsys.usermgr.startproject;

import cn.edu.lingnan.usermgrsys.usermgr.view.IndexFrame;
/**
 * 系统启动类
 * 为系统提供了一个启动的接口
 * @author 11638
 *
 */
public class StartProject {

	public static void main(String[] args) {
		// 实例化用户登录和注册的操作界面类
		IndexFrame frame = new IndexFrame();
		//调用loginFrame方法，显示用户登录和注册的操作界面
		frame.show();

	}

}

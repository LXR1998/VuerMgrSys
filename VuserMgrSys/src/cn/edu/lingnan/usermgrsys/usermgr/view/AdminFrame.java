package cn.edu.lingnan.usermgrsys.usermgr.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import cn.edu.lingnan.usermgrsys.usermgr.controller.UserController;
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
	 * @param user 用户信息
	 * 
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
			new NormalFrame(user).show(user.getUserId());
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
					break;
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					System.out.println("您输入的指令有误，只能输入1~5的数字，请重试！");
				}
				}

			switch(i){
			case 1:
				this.addShow();
				break;
			case 2:
				this.deleteShow();
				break;
			case 3:
				this.updateShow();
				break;
			case 4:
				this.searchShow();
				break;
			case 5:
				System.out.println("感谢您的使用，再会！");
				System.exit(0);
			default:
				System.out.println("您的输入有误，请重新输入！");
				System.out.println();
			}
			}	
		}
	/**
	 * 用户添加界面
	 */
	public void addShow(){
		//声明缓冲对象，用于接收而控制台输入的信息
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean result = false; 
		System.out.println("          用户添加界面 ");
		System.out.println("=================================");
		System.out.println("请输入需要添加的用户名：");
		try {
			String Uname = br.readLine();
			System.out.println("请设置用户密码：");
			String Passwd = br.readLine();
			System.out.println("请输入邮箱：");
			String Email = br.readLine();
			
			//声明控制层对象
			UserController UserController = new UserController();
			//调用控制层对象中的方法，并将结果赋给result
			result = UserController.doaddUser(Uname, Passwd, Email);
			if(result){
				System.out.println("用户添加成功");
			}else{
				System.out.println("用户添加失败！！");
			}
			
		} catch (IOException e) {
			//显示异常信息 
			System.out.println(e.getMessage());
		}

	}
	
	/**
	 * 用户删除界面
	 */
	public void deleteShow(){
		//声明缓冲处理流对象，用于接收后台输入的数据
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean result = false;
		while(true){
			System.out.println("请输入需要删除的用户ID");
			int UserId = -1;
			while(true){
				try {
					UserId = Integer.parseInt(br.readLine());
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
			result = UserController.dodeleteUser(UserId);
			if(result){
				System.out.println("用户删除成功");
				break;
			}else{
				System.out.println("用户删除失败！！");
				break;
			}
		}	
	}

	/**
	 * 修改用户信息界面
	 */
	public void updateShow(){
		//声明缓冲处理流对象，用于接收后台输入的数据
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean result = false;
		System.out.println("          用户信息修改界面 ");
		System.out.println("=================================");
		while(true){
			System.out.println("请输入需要修改的用户ID");
			int UserId = -1;
			String Email = null;
			String Passwd = null;
			while(true){
				try {
					UserId = Integer.parseInt(br.readLine());
					System.out.println("请输入需要修改的用户的密码");
					Passwd = br.readLine();
					System.out.println("请输入需要修改的用户的Email");
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
				System.out.println("用户信息成功");
				break;
			}else{
				System.out.println("用户信息失败！！");
				break;
			}
		}	
		
	}

	/**
	 * 查询用户信息的界面
	 */
	public void searchShow(){
		//声明缓冲处理流对象，用于接收后台输入的数据
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true){
			System.out.println("根据用户Id查询用户------------1");
			System.out.println("根据用户名查询用户------------2");
			System.out.println("查询全部用户-----------------3");
			System.out.println("分页查询用户-----------------4");
			System.out.println("退出程序---------------------5");
			int i =-1 ;
			while(true){
				try {
					i = Integer.parseInt(br.readLine());
					break;
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					System.out.println("您输入的指令有误，只能输入1~4的数字，请重试！");
				}
				}

			switch(i){
			case 1:
				this.searchUserByIdShow();
				break;
			case 2:
				this.searchByUnameShow();
				break;
			case 3:
				this.searchAllUserShow();
				break;
			case 4:
				this.searchUserByPageShow();
				break;
			case 5:
				System.out.println("感谢您的使用，再会！！");
				System.exit(0);
			default:
				System.out.println("您的输入有误，请重新输入！");
				System.out.println();
			}
			}
	}
	
	/**
	 * 根据用户Id查询用户
	 */
	public void searchUserByIdShow(){
		//声明缓冲处理流对象，用于接收后台输入的数据
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true){
			System.out.println("请输入需要查询的用户ID");
			int UserId = -1;
			while(true){
				try {
					UserId = Integer.parseInt(br.readLine());					
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
			//调用控制层对象中的方法，并将结果返回给user变量
			UserVO user = UserController.dosearchUserById(UserId);
			if(user != null){
				System.out.println("用户查找成功，用户信息如下：");
				System.out.print
				("用户ID:"+user.getUserId()+"  用户名 :"+user.getUname()+"   用户密码:"+user.getPasswd());
				System.out.print("   用户邮箱："+user.getEmail()+"  用户权限："+user.getUtype());
				System.out.println();
			}else{
				System.out.println("查找用户失败！！没有该用户~");
				break;
			}
			System.out.println();
			System.out.println("===========================");
			break;
		}	
	}

	/**
	 * 根据用户名查询用户
	 */
	public void searchByUnameShow(){
		//声明缓冲处理流对象，用于接收后台输入的数据
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Vector<UserVO> v = new Vector<UserVO>();

			String Uname = null;
				try {
					System.out.println("请输入需要查询的用户名");
					Uname = br.readLine();					
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			//声明控制层对象
			UserController UserController = new UserController();
			
			//调用控制层对象中的方法，并将结果返回给Vector类型的变量
			v = UserController.dosearchByUname(Uname);
			//如果返回值不为空，查询成功，显示用户信息；否则查询失败，显示失败信息
			if(user != null){  
				System.out.println("用户查找成功，用户信息如下：");
				Iterator it = v.iterator();
				while (it.hasNext()){
					user = (UserVO) it.next();	
//			for(UserVO user:v){
				System.out.print
				("用户ID:"+user.getUserId()+"  用户名 :"+user.getUname()+"   用户密码:"+user.getPasswd());
				System.out.print("   用户邮箱："+user.getEmail()+"  用户权限："+user.getUtype());	
				System.out.println();
			}
		}
			System.out.println();
			System.out.println("===========================");
			
	}
		

	/**
	 * 查询全部用户
	 */
	public void searchAllUserShow(){
		
		Vector<UserVO> v = new Vector<UserVO>();
		//声明控制层对象
		UserController UserController = new UserController();
		
		//调用控制层对象中的方法，并将结果传递给Vector变量
		v = UserController.dosearchAllUser();
		//定义一个迭代器，用于保存并遍历v中的数据
		Iterator<UserVO> it = v.iterator();
		if(it.hasNext()) System.out.println("用户查找成功，用户信息如下：");
		else System.out.println("用户查找失败，没有该用户");
		while(it.hasNext()){
			user = (UserVO)it.next();
			System.out.print
			("用户ID:"+user.getUserId()+"  用户名 :"+user.getUname()+"   用户密码:"+user.getPasswd());
			System.out.print("   用户邮箱："+user.getEmail()+"  用户权限："+user.getUtype());
			System.out.println();
		}
		System.out.println();
		System.out.println("===========================");
		
	}

	/**
	 * 分页查询用户
	 */
	public void searchUserByPageShow(){
		//声明缓冲处理流对象，用于接收后台输入的数据
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Vector<UserVO> v = new Vector<UserVO>();
		UserVO user = null;
		int pageNo = -1;
		int pageSize = -1;
		while(true){
			System.out.println("请输入页数pageNo");
			while(true){
				try {
					pageNo = Integer.parseInt(br.readLine());
					System.out.println("请输入每页的记录数pageSize");
					pageSize= Integer.parseInt(br.readLine());					
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
			
			//调用控制层对象中的方法，并将结果传递给Vector变量
			v = UserController.dosearchUserByPage(pageNo, pageSize);
			//定义一个迭代器，用于保存并遍历v中的数据
			Iterator<UserVO> it = v.iterator();
			if(it.hasNext()) System.out.println("用户查找成功，用户信息如下：");
			else System.out.println("用户查找失败，没有该用户");
			while(it.hasNext()){
				user = (UserVO)it.next();
				System.out.print
				("用户ID:"+user.getUserId()+"  用户名 :"+user.getUname()+"   用户密码:"+user.getPasswd());
				System.out.print("   用户邮箱："+user.getEmail()+"  用户权限："+user.getUtype());
				System.out.println();
			}
			System.out.println();
			System.out.println("===========================");
			break;
		}
	}
	
}
	

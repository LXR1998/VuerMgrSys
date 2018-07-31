package cn.edu.lingnan.usermgrsys.usermgr.domain;

import java.util.Date;
/**
 * 实体类
 * @author 11638
 *
 */
public class UserVO {
	     
	private int UserId;				//主键
	private String Uname;		//用户名
	private String Passwd;		//密码
	private Date Rgsdate;		//注册日期
	private String Utype;		//会员类型（权限）	
	private String Email;		//E-mail地址
	private int Status;			//状态
	
	
	//主键
	public void setUserId(int _UserId){
		UserId=_UserId;
	}
	public int getUserId(){
		return UserId;
	}
	//用户名
	public void setUname(String _Uname){
		Uname=_Uname;
	}
	public String getUname(){
		return Uname;
	}
	//密码
	public void setPasswd(String _Passwd){
		Passwd=_Passwd;
	}
	public String getPasswd(){
		return Passwd;
	}

	//注册日期
	public void setRgsdate(Date _Rgsdate){
		Rgsdate=_Rgsdate;
	}
	public Date getRgsdate(){
		return Rgsdate;
	}
	//会员类型
	public void setUtype(String _Utype){
		Utype=_Utype;
	}
	public String getUtype(){
		return Utype;
	}
	//E-mail地址
	public void setEmail (String _Email){
		Email=_Email;
	}
	public String getEmail(){
		return Email;
	}
	
	//状态
		public void setStatus(int _Status){
			Status=_Status;
		}
		public int getStatus(){
			return Status;
		}
}

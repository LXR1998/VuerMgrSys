package cn.edu.lingnan.usermgrsys.usermgr.view;

import cn.edu.lingnan.usermgrsys.usermgr.domain.UserVO;

public interface BaseFrame {

	/**
	 * 页面显示
	 */
	public void show();
	
	/**
	 * 增加用户的页面显示
	 */
	public void addShow();
	
	/**
	 * 查询
	 */
	public void searchShow();
	
	/**
	 * 修改信息的页面显示
	 */
	public void updateShow();
	
	/**
	 * 删除用户
	 */
	public void deleteShow();

}

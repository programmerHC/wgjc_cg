package com.wgjc.user.entity;
/** 
 * @Description: User类条件查询封装
 * @author hc
 * @date 2019年8月26日下午3:16:40
 */
public class UserCondition {
	//用户名
	private String userName;
	//真实姓名
	private String realName;
	//电话
	private String phone;
	//地址
	private String adress;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
}

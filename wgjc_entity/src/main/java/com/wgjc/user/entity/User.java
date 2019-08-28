package com.wgjc.user.entity;

/** 
 * @Description: 用户类封装
 * @author hc
 * @date 2019年7月22日上午11:19:33
 */
public class User {
	//主键
	private String uuid;
	//用户名
	private String userName;
	//真实姓名
	private String realName;
	//密码
	private String password;
	//电话
	private String phone;
	//地址
	private String adress;
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}

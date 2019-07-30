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
	private String username;
	//真实姓名
	private String realname;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
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

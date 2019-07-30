package com.wgjc.role.entity;
/** 
 * @Description: 角色表
 * @author hc
 * @date 2019年7月30日上午9:59:03
 */
public class Role {
	private String uuid;//角色id
	private String rolename;//角色name
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
}

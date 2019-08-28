package com.wgjc.role.entity;
/** 
 * @Description: 角色表
 * @author hc
 * @date 2019年7月30日上午9:59:03
 */
public class Role {
	private String uuid;//角色id
	private String roleName;//角色name
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}

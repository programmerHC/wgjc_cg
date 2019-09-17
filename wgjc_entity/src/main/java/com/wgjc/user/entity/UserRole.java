package com.wgjc.user.entity;
/** 
 * @Description: 用户角色表
 * @author hc
 * @date 2019年9月16日下午3:29:22
 */
public class UserRole {
	private String uuid;
	private String userId;
	private String roleId;
	//isDefault 0代表该用户默认登录角色，1代表非该员工默认角色
	private int isDefault;
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public int getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
	}
}

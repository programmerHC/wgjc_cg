package com.wgjc.role.entity;
/** 
 * @Description: 用户功能表
 * @author hc
 * @date 2019年7月30日上午10:39:12
 */
public class RoleFunc {
	private String uuid;//主键
	private String RoleId;//角色id
	private String funcId;//功能id
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getRoleId() {
		return RoleId;
	}
	public void setRoleId(String roleId) {
		RoleId = roleId;
	}
	public String getFuncId() {
		return funcId;
	}
	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}
}

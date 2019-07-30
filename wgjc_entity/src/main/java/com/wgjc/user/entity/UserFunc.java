package com.wgjc.user.entity;
/** 
 * @Description: 用户功能表
 * @author hc
 * @date 2019年7月30日上午10:39:12
 */
public class UserFunc {
	private String uuid;//主键
	private String userId;//用户id
	private String funcId;//功能id
	
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
	public String getFuncId() {
		return funcId;
	}
	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}
}

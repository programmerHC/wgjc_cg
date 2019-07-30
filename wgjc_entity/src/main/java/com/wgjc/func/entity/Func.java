package com.wgjc.func.entity;
/** 
 * @Description: 用户功能表
 * @author hc
 * @date 2019年7月30日上午10:24:04
 */
public class Func {
	private String uuid;//功能id
	private String funcname;//功能name
	private String funcUrl;//功能地址
	private int order;//功能排序
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getFuncname() {
		return funcname;
	}
	public void setFuncname(String funcname) {
		this.funcname = funcname;
	}
	public String getFuncUrl() {
		return funcUrl;
	}
	public void setFuncUrl(String funcUrl) {
		this.funcUrl = funcUrl;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
}

package com.wgjc.page.entity;
/** 
 * @Description: ajax请求结果返回
 * @author hc
 * @date 2019年8月7日下午3:27:59
 */
public class AjaxResult {
	private int code;
	private String desc;
	private Object info;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Object getInfo() {
		return info;
	}
	public void setInfo(Object info) {
		this.info = info;
	}
	public void setResult(int code,String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public void setResult(int code,String desc,Object info) {
		this.code = code;
		this.desc = desc;
		this.info = info;
	}
}

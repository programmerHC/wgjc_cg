package com.wgjc.account.entity;

import java.util.Date;

/** 
 * @Description: 账单查询条件类封装
 * @author hc
 * @date 2019年9月3日上午9:40:16
 */
public class AccountCondition {
	//货物名称
	private String name;
	//类型，买为0，退为1
	private String type;
	//创建日期
	private Date createDate;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}

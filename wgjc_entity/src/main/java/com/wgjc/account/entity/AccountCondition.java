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
	//起始日期
	private Date beginDate;
	//结束日期
	private Date endTime;
	
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
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}

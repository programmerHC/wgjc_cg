package com.wgjc.account.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/** 
 * @Description: 菜单账目表
 * @author hc
 * @date 2019年8月30日下午2:18:20
 */
public class Account {
	private String uuid;
	//顾客id
	private String userId;
	//货物名称
	private String name;
	//货物规格
	private String size;
	//货物数量
	private String count;
	//货物单位
	private String unit;
	//货物价格
	private String price;
	//类型，买为0，退为1
	private int type;
	//创建日期
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createDate;
	//创建人
	private String creator;
	//购买日期
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date buyDate;
	
	public Account() {}
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
}

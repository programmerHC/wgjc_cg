package com.wgjc.goods.entity;
/** 
 * @Description: 商品
 * @author hc
 * @date 2019年8月30日下午3:25:07
 */
public class Goods {
	private String uuid;
	//商品名
	private String name;
	//商品规格
	private String size;
	//商品数量
	private String count;
	//商品单位
	private String unit;
	//商品价格
	private String price;
	//备注
	private String remark;
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}

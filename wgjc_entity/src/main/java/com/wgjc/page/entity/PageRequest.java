package com.wgjc.page.entity;

import org.springframework.beans.factory.annotation.Value;

/** 
 * @Description: 分页查询请求封装
 * @author hc
 * @date 2019年7月22日上午11:19:33
 */
public class PageRequest {
	
	//当前页码,默认起始值为1
	private int pageNum;
	
	//页码数量,默认页数为20
	private int pageSize;
	
	public PageRequest() {
	}

	public PageRequest(int pageNum, int pageSize) {
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}

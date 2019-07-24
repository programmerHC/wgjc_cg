package com.wgjc.base.entity;

/** 
 * @Description: 分页查询请求封装
 * @author hc
 * @date 2019年7月22日上午11:19:33
 */
public class PageRequest {
	
	//当前页码
	private int pageNum;
	
	//页码数量
	private int pageSize;

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

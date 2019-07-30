package com.wgjc.page.entity;

/** 
 * @Description: 分页查询请求封装
 * @author hc
 * @date 2019年7月22日上午11:19:33
 */
public class PageRequest {
	
	private int pageNum;
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

package com.wgjc.base.entity;

import java.util.List;

/** 
 * @Description: 请求查询结果
 * @author hc
 * @date 2019年7月22日上午11:23:11
 */
public class PageResult {
    //当前页码
    private int pageNum;
    
    // 每页数量
    private int pageSize;
    
    // 记录总数
    private long totalSize;
    
    // 页码总数
    private int totalPages;
    
    //数据模型
    private List<?> content;

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

	public long getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(long totalSize) {
		this.totalSize = totalSize;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public List<?> getContent() {
		return content;
	}

	public void setContent(List<?> content) {
		this.content = content;
	}

	@Override
	public String toString() {
		String result = "PageResult [pageNum=" + pageNum + ", pageSize=" + pageSize + ", totalSize=" + totalSize
				+ ", totalPages=" + totalPages  + ", content=";
		
		for(Object target:content) {
			result += target.toString();
		}
				
		result += "]";
		return result;
	}
}

package com.wgjc.base.util;

import com.github.pagehelper.PageInfo;
import com.wgjc.base.entity.PageResult;

/** 
 * @Description: page查询工具类封装
 * @author hc
 * @date 2019年7月22日下午3:45:52
 */  
public class PageUtils {
	 
	/**
	 * @Title: getPageResult  
	 * @Description: TODO 
	 * @param pageRequest
	 * @param pageInfo
	 * @return
	 */
	 public static PageResult getPageResult(PageInfo<?> pageInfo) {
	        PageResult pageResult = new PageResult();
	        pageResult.setPageNum(pageInfo.getPageNum());
	        pageResult.setPageSize(pageInfo.getPageSize());
	        pageResult.setTotalSize(pageInfo.getTotal());
	        pageResult.setTotalPages(pageInfo.getPages());
	        pageResult.setContent(pageInfo.getList());
	        return pageResult;
	 }
}

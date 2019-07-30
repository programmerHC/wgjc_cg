package com.wgjc.page.util;

import org.springframework.stereotype.Component;

import com.github.pagehelper.PageInfo;
import com.wgjc.page.entity.PageResult;

/** 
 * @Description: page查询工具类封装
 * @author hc
 * @date 2019年7月22日下午3:45:52
 */  
@Component
public class PageUtils {
	 
	/**
	 * @Title: getPageResult  
	 * @Description: 封装page内容
	 * @param pageRequest
	 * @param pageInfo
	 * @return
	 */
	 public PageResult getPageResult(PageInfo<?> pageInfo) {
	        PageResult pageResult = new PageResult();
	        pageResult.setPageNum(pageInfo.getPageNum());
	        pageResult.setPageSize(pageInfo.getPageSize());
	        pageResult.setTotalSize(pageInfo.getTotal());
	        pageResult.setTotalPages(pageInfo.getPages());
	        pageResult.setContent(pageInfo.getList());
	        return pageResult;
	 }
}

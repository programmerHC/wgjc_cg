package com.wgjc.entity.factory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wgjc.page.entity.AjaxResult;
import com.wgjc.page.entity.PageRequest;

/** 
 * @Description:分页工厂 ,默认页码注入
 * @author hc
 * @date 2019年7月29日上午10:19:14
 */
@Configuration
public class BeanFactory {
	
	/**
	 * @Title: getDefaultPageRequest  
	 * @Description: 默认初始化为第一页页，页面查询数量为20条 
	 * @param pageNum 页数
	 * @param pageSize 查询数量
	 * @return
	 */
	@Bean(name = "defaultPageRequest")
	public static PageRequest getDefaultPageRequest(@Value("1")int pageNum,@Value("20")int pageSize) {
		return new PageRequest(pageNum, pageSize);
	}
	
	/**
	 * @Title: getAjaxResult  
	 * @Description: ajax默认返回结果封装 
	 * @return
	 */
	@Bean(name = "ajaxResult")
	public static AjaxResult getAjaxResult() {
		return new AjaxResult();
	}
}

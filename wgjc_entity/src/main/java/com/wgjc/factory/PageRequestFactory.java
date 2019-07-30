package com.wgjc.factory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wgjc.page.entity.PageRequest;

/** 
 * @Description:分页工厂 ,默认页码注入
 * @author hc
 * @date 2019年7月29日上午10:19:14
 */
@Configuration
public class PageRequestFactory {
	@Bean(name="pageRequest")
	public PageRequest getPageRequest() {
		return new PageRequest();
	}
	
	@Bean(name="defaultPageRequest")
	public PageRequest getDefaultPageRequest(@Value("1")int pageNum,@Value("20")int pageSize) {
		return new PageRequest(pageNum, pageSize);
	}
}

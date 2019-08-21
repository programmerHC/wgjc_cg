package com.wgjc.interceptor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.wgjc.login.interceptor.CheckLoginInterceptor;

/** 
 * @Description: 
 * @author hc
 * @date 2019年8月21日下午4:41:31
 */
@Configuration
public class WgjcInterCeptorConfig implements WebMvcConfigurer  {
	 @Override
	 public void addInterceptors(InterceptorRegistry registry) {
		 registry.addInterceptor(CheckLoginInterceptor()).addPathPatterns("/**");
	 }
	 
	 @Bean
	 public CheckLoginInterceptor CheckLoginInterceptor() {
		 return new CheckLoginInterceptor();
	 }
}

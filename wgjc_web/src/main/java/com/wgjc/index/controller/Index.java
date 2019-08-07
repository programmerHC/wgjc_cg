package com.wgjc.index.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
 * @Description: 初始页面
 * @author hc
 * @date 2019年8月7日下午2:34:15
 */
@Controller
public class Index {
	/**
	 * @Title: index  
	 * @Description: 跳转登录首页 
	 * @return
	 */
	@RequestMapping(value = {"","/index"})
	public String index() {
		return "loginPage";
	}
}

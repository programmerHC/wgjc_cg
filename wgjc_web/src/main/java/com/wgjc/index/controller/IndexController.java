package com.wgjc.index.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/** 
 * @Description: 初始页面api
 * @author hc
 * @date 2019年8月7日下午2:34:15
 */
@Controller
public class IndexController {
	/**
	 * @Title: index  
	 * @Description: 跳转登录首页 
	 * @return
	 */
	@GetMapping(value = {"","/index"})
	public String index() {
		return "loginPage";
	}
}

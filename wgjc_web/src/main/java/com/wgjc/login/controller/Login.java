package com.wgjc.login.controller;
/** 
 * @Description: 登录业务逻辑处理
 * @author hc
 * @date 2019年8月7日下午3:18:54
 */

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wgjc.page.entity.AjaxResult;

@RestController
public class Login {
	@RequestMapping("/checkLogin")
	public AjaxResult checkLogin(@RequestParam(required = true)String username,@RequestParam(required = true)String password) {
		return null;
	}
}

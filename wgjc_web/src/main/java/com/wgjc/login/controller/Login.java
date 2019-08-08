package com.wgjc.login.controller;
/** 
 * @Description: 登录业务逻辑处理
 * @author hc
 * @date 2019年8月7日下午3:18:54
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wgjc.page.entity.AjaxResult;
import com.wgjc.user.service.UserService;
import com.wgjc.user.service.impl.UserServiceImpl;

@RestController
public class Login {
	@Autowired
	private UserService userService;
	@Autowired
	private AjaxResult ajaxResult;
	
	@RequestMapping("/checkLogin")
	public AjaxResult checkLogin(@RequestParam(required = true)String userName,@RequestParam(required = true)String password) {
		boolean flag = userService.isUser(userName, password);
		if(flag) {
			ajaxResult.setCode(0);
			ajaxResult.setDesc("登录成功");
		}else {
			ajaxResult.setCode(1);
			ajaxResult.setDesc("账号密码错误！");
		}
		return ajaxResult;
	}
}

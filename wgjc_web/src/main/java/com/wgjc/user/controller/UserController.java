package com.wgjc.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wgjc.encrypt.util.EncryptUtil;
import com.wgjc.login.annotation.Login;
import com.wgjc.page.entity.AjaxResult;
import com.wgjc.user.entity.User;
import com.wgjc.user.service.UserService;

/** 
 * @Description: 用户操作接口
 * @author hc
 * @date 2019年8月21日下午12:56:30
 */
//@Login
@RestController
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private AjaxResult ajaxResult;
	@Autowired
	private EncryptUtil encryptUtil;
	
	@PostMapping("/user")
	public AjaxResult addUser(User user) {
		//数据库用户密码需要加密
		user.setPassword(encryptUtil.encrypt(user.getPassword()));
		boolean flag = userService.save(user);
		if(flag) {
			ajaxResult.setResult(0, "新增用户成功");
		}else {
			ajaxResult.setResult(1, "新增用户失败");
		}
		return ajaxResult;
	}
}

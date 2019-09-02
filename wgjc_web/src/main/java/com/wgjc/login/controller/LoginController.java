package com.wgjc.login.controller;
/** 
 * @Description: 登录业务逻辑api
 * @author hc
 * @date 2019年8月7日下午3:18:54
 */

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.wgjc.login.annotation.Login;
import com.wgjc.page.entity.AjaxResult;
import com.wgjc.user.entity.User;
import com.wgjc.user.service.UserService;

@RestController
public class LoginController {
	@Autowired
	private UserService userService;
	@Autowired
	private AjaxResult ajaxResult;
	
	/**
	 * @Title: checkLogin  
	 * @Description: 登录校验 
	 * @param userName
	 * @param password
	 * @param session
	 * @return
	 */
	@PostMapping("/checkLogin")
	public AjaxResult checkLogin(@RequestParam(required = true)String userName,@RequestParam(required = true)String password,HttpSession session) {
		User user = userService.getUserByUsername(userName);
		boolean flag = userService.isUser(user,password);
		if(flag) {
			session.setAttribute("loginUser", user);
			ajaxResult.setResult(0, "登录成功");
		}else {
			ajaxResult.setResult(1, "账号密码错误！");
		}
		return ajaxResult;
	}
	
	/**
	 * @Title: quitLogin  
	 * @Description: 退出登录
	 * @param session
	 * @return
	 */
	@Login
	@GetMapping("/quitLogin")
	public ModelAndView quitLogin(HttpSession session) {
		session.invalidate();
		ModelAndView mav = new ModelAndView("redirect:/loginPage");
		return mav;
	}
}

package com.wgjc.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.wgjc.encrypt.util.EncryptUtil;
import com.wgjc.page.entity.AjaxResult;
import com.wgjc.page.entity.PageRequest;
import com.wgjc.user.entity.User;
import com.wgjc.user.entity.UserCondition;
import com.wgjc.user.service.UserService;

/** 
 * @Description: 用户操作接口api
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
			ajaxResult.setResult(0, "新增用户成功",user);
		}else {
			ajaxResult.setResult(1, "新增用户失败");
		}
		return ajaxResult;
	}
	
	@PutMapping("/user")
	public AjaxResult updateUser(User user) {
		boolean flag = userService.update(user);
		if(flag) {
			ajaxResult.setResult(0, "编辑用户成功");
		}else {
			ajaxResult.setResult(1, "编辑用户失败");
		}
		return ajaxResult;
	}
	
	@DeleteMapping("/user")
	public AjaxResult deleteUser(@RequestParam(value= "uuid",required = true) String uuid) {
		boolean flag = userService.delete(uuid);
		if(flag) {
			ajaxResult.setResult(0, "删除用户成功");
		}else {
			ajaxResult.setResult(1, "删除用户失败");
		}
		return ajaxResult;
	}
	
	@GetMapping("/user")
	public User getUser(@RequestParam(value= "uuid",required = true) String uuid) {
		User user = userService.getById(uuid);
		return user;
	}
	
	@GetMapping("/userAll")
	public PageInfo<User> getPageUser(PageRequest pageRequest,UserCondition userCondition){
		PageInfo<User> pageInfo = userService.getPageInfo(pageRequest,userCondition);
		return pageInfo;
	}
}

package com.wgjc.account.controller;
/** 
 * @Description: 账单业务逻辑api
 * @author hc
 * @date 2019年9月3日上午10:30:41
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.wgjc.account.entity.Account;
import com.wgjc.page.entity.AjaxResult;

@RestController
public class AccountController {
	@Autowired
	private AjaxResult ajaxResult;
	
	/**
	 * @Title: saveAccount  
	 * @Description: TODO 
	 * @param accounts json数组传参
	 * @return
	 */
	@PostMapping("/accounts")
	public AjaxResult saveAccount(@RequestBody List<Account> accounts) {
		ajaxResult.setResult(0, "test", accounts);
		return ajaxResult;
	}
}

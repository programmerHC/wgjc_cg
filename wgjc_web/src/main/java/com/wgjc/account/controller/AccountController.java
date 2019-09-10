package com.wgjc.account.controller;
/** 
 * @Description: 账单业务逻辑api
 * @author hc
 * @date 2019年9月3日上午10:30:41
 */

import java.util.Date;
import java.util.List;

import org.hibernate.validator.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.wgjc.account.entity.Account;
import com.wgjc.account.entity.AccountCondition;
import com.wgjc.account.service.AccountService;
import com.wgjc.page.entity.AjaxResult;
import com.wgjc.page.entity.PageRequest;

@RestController
public class AccountController {
	@Autowired
	private AjaxResult ajaxResult;
	@Autowired
	private AccountService accountservice;
	
	/**
	 * @Title: saveAccount  
	 * @Description: TODO 
	 * @param accounts json数组传参
	 * @return
	 */
	@PostMapping("/account")
	public AjaxResult saveAccount(@RequestBody List<Account> accounts) {
		boolean flag = accountservice.saveAccounts(accounts);
		if(flag) {
			ajaxResult.setResult(0, "账单保存成功！");
		}else {
			ajaxResult.setResult(1, "账单保存失败！");
		}
		return ajaxResult;
	}
	
	/**
	 * 
	 * @Title: deleteAccount  
	 * @Description: TODO 
	 * @param uuids 多id字符串数组传参
	 * @return
	 */
	@DeleteMapping("/account")
	public AjaxResult deleteAccount(@RequestBody String[] uuids) {
		boolean flag = accountservice.deleteAccounts(uuids);
		if(flag) {
			ajaxResult.setResult(0, "账单删除成功！");
		}else {
			ajaxResult.setResult(1, "账单删除失败！");
		}
		return ajaxResult;
	}
	
	@PutMapping("/account")
	public AjaxResult updateAccount(Account account) {
		Account account_update = new Account();
		if(account != null && StringHelper.isNullOrEmptyString(account.getUuid())) {
			account_update = accountservice.getById(account.getUuid());
			if(account_update != null) {
				account_update.setName(account.getName());//货物名
				account_update.setCount(account.getCount());//数量
				account_update.setSize(account.getSize());//规格
				account_update.setType(account.getType());//买卖类型
				account_update.setUnit(account.getUnit());//单位
			}
		}
		boolean flag =  accountservice.update(account_update);
		if(flag) {
			ajaxResult.setResult(0, "账单编辑成功！");
		}else {
			ajaxResult.setResult(1, "账单编辑失败！");
		}
		return ajaxResult;
	}
	
	@GetMapping("/account")
	public Account getAccount(String uuid) {
		Account account = accountservice.getById(uuid);
		return account;
	}
	
	@GetMapping("/accountAll")
	public PageInfo<Account> getAccountPage(PageRequest pageRequest,AccountCondition accountCondition){
		PageInfo<Account> pageInfo = accountservice.getPageInfo(pageRequest, accountCondition);
		return pageInfo;
	}
	
	@PostMapping("/printAccount")
	public AjaxResult printAccount(@RequestBody List<Account> accounts) {
		ajaxResult = accountservice.makeAccountsToExcel(accounts);
		return ajaxResult;
	}
}

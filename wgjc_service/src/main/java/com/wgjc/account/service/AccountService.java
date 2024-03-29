package com.wgjc.account.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wgjc.account.entity.Account;
import com.wgjc.account.entity.AccountCondition;
import com.wgjc.base.service.BaseService;
import com.wgjc.page.entity.AjaxResult;
import com.wgjc.page.entity.PageRequest;

/** 
 * @Description: 商品业务处理service接口
 * @author hc
 * @date 2019年9月4日下午4:28:44
 */
public interface AccountService extends BaseService<Account> {
	/**
	 * @Title: makeAccountsToExcel  
	 * @Description: 打印账单，转为excel保存
	 * @param accounts
	 */
	public AjaxResult makeAccountsToExcel(List<Account> accounts);
	public boolean saveAccounts(List<Account> accounts);
	public boolean deleteAccounts(String[] uuids);
	public PageInfo<Account> getPageInfo(PageRequest pageRequest,AccountCondition accountCondition);
}

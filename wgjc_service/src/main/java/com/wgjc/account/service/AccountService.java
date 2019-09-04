package com.wgjc.account.service;

import com.github.pagehelper.PageInfo;
import com.wgjc.account.entity.Account;
import com.wgjc.account.entity.AccountCondition;
import com.wgjc.base.service.BaseService;
import com.wgjc.page.entity.PageRequest;

/** 
 * @Description: 商品业务处理service接口
 * @author hc
 * @date 2019年9月4日下午4:28:44
 */
public interface AccountService extends BaseService<Account> {
	public PageInfo<Account> getPageInfo(PageRequest pageRequest,AccountCondition accountCondition);
}

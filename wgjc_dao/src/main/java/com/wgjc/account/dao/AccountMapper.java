package com.wgjc.account.dao;

import java.util.List;

import com.wgjc.account.entity.Account;
import com.wgjc.account.entity.AccountCondition;

/** 
 * @Description: 账单业务接口类
 * @author hc
 * @date 2019年9月4日下午2:44:22
 */
public interface AccountMapper {
	public int addAccount(Account account);
	public int deleteAccountById(String uuid);
	public int updateAccount(Account account);
	public Account getAccountById(String uuid);
	public List<Account> getAllAcount(AccountCondition accountCondition);
}

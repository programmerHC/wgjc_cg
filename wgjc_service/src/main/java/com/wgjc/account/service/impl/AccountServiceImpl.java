package com.wgjc.account.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wgjc.account.dao.AccountMapper;
import com.wgjc.account.entity.Account;
import com.wgjc.account.entity.AccountCondition;
import com.wgjc.account.service.AccountService;
import com.wgjc.page.entity.PageRequest;
import com.wgjc.redis.util.RedisUtil;

/** 
 * @Description:账单操作业务实现类
 * @author hc
 * @date 2019年9月4日下午5:29:46
 */
@Service
public class AccountServiceImpl implements AccountService{
	private Log log = LogFactory.getLog(AccountServiceImpl.class);
	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private RedisUtil redisUtil;
	
	@Override
	public boolean save(Account record) {
		boolean flag = false;
		try {
			int result = accountMapper.addAccount(record);
			if(result > 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return flag;
	}

	@Override
	public boolean update(Account record) {
		boolean flag = false;
		try {
			int result = accountMapper.updateAccount(record);
			if(result > 0) {
				redisUtil.del(record.getUuid());
				redisUtil.set(record.getUuid(), record);
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return flag;
	}

	@Override
	public boolean delete(String id) {
		boolean flag = false;
		try {
			int result = accountMapper.deleteAccountById(id);
			if(result > 0) {
				redisUtil.del(id);
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return flag;
	}

	@Override
	public Account getById(String id) {
		Account account = null;
		try {
			account = redisUtil.get(id, Account.class);
			if(account == null) {
				account = accountMapper.getAccountById(id);
				redisUtil.set(id, account);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return account;
	}

	@Override
	public PageInfo<Account> getPageInfo(PageRequest pageRequest, AccountCondition accountCondition) {
		PageInfo<Account> pageInfo = null;
		PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
		try {
			List<Account> list = accountMapper.getAllAcount(accountCondition);
			pageInfo = new PageInfo<Account>(list);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return pageInfo;
	}

}

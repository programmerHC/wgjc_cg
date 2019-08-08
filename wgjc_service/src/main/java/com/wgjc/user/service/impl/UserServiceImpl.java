package com.wgjc.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.wgjc.encrypt.util.EncryptUtil;
import com.wgjc.page.entity.PageRequest;
import com.wgjc.user.dao.UserMapper;
import com.wgjc.user.entity.User;
import com.wgjc.user.service.UserService;

/** 
 * @Description: user怎删改查实现类
 * @author hc
 * @date 2019年8月7日下午4:58:47
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private EncryptUtil encryptUtil;
	
	
	@Override
	public int save(User record) {
		return userMapper.addUser(record);
	}

	@Override
	public int update(User record) {
		return userMapper.updateUser(record);
	}

	@Override
	public int delete(String id) {
		return userMapper.deleteUserById(id);
	}

	@Override
	public User getById(String id) {
		return userMapper.getUserById(id);
	}

	@Override
	public List<User> getAllRecord() {
		return userMapper.getAllUser();
	}

	@Override
	public User getUserByUsername(String userName) {
		return userMapper.getUserByUsername(userName);
	}
	
	@Override
	public PageInfo<User> getPageInfo(PageRequest pageRequest) {
		PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
		List<User> userList = getAllRecord();
		return new PageInfo<User>(userList);
	}
	
	/**
	 * 
	 * @Title: isUser  
	 * @Description: 判断输入用户名和密码，是否为数据库中用户
	 * @param userName
	 * @return false该用户账号密码错误，true则该用户为该数据库中用户
	 */
	public boolean isUser(String userName,String password) {
		boolean flag = false;
		User user = getUserByUsername(userName);
		if(user != null) {
			String password_entry_in = user.getPassword();
			if(StringUtil.isNotEmpty(password_entry_in)) {
				String password_entry_in_des = encryptUtil.decrypt(password_entry_in);
				String password_entry_out_des = encryptUtil.decrypt(password);
				if(password_entry_in_des.equals(password_entry_out_des)) {//前后台密码都要进行加密
					flag = true;
				}
			}
		}
		return flag;
	}

}

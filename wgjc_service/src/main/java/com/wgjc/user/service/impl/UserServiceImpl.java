package com.wgjc.user.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

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
	
	private UserMapper userMapper;
	
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
	public User getUserByUsername(String username) {
		return userMapper.getUserByUsername(username);
	}

}

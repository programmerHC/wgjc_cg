package com.wgjc.user.dao;

import java.util.List;

import com.wgjc.user.entity.User;
import com.wgjc.user.entity.UserCondition;

/** 
 * @Description: 用户接口类
 * @author hc
 * @date 2019年8月7日下午4:10:22
 */
public interface UserMapper {
	public User getUserById(String uuid);
	public User getUserByUsername(String userName);
	public List<User> getAllUser(UserCondition userCondition);
	public int addUser(User user);
	public int deleteUserById(String uuid);
	public int updateUser(User user);
}

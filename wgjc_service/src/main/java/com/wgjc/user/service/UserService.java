package com.wgjc.user.service;

import com.wgjc.base.service.BaseService;
import com.wgjc.user.entity.User;

/** 
 * @Description: 
 * @author hc
 * @date 2019年8月7日下午4:53:55
 */
public interface UserService extends BaseService<User> {
	public User getUserByUsername(String username);
}

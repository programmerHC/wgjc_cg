package com.wgjc.user.service;

import com.github.pagehelper.PageInfo;
import com.wgjc.base.service.BaseService;
import com.wgjc.page.entity.PageRequest;
import com.wgjc.user.entity.User;
import com.wgjc.user.entity.UserCondition;

/** 
 * @Description: 
 * @author hc
 * @date 2019年8月7日下午4:53:55
 */
public interface UserService extends BaseService<User> {
	public User getUserByUsername(String username);
	public PageInfo<User> getPageInfo(PageRequest pageRequest,UserCondition userCondition); 
	public boolean isUser(User user);
}

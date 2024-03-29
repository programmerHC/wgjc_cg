package com.wgjc.user.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.wgjc.encrypt.util.EncryptUtil;
import com.wgjc.page.entity.PageRequest;
import com.wgjc.redis.util.RedisUtil;
import com.wgjc.user.dao.UserMapper;
import com.wgjc.user.entity.User;
import com.wgjc.user.entity.UserCondition;
import com.wgjc.user.service.UserService;

/** 
 * @Description: user增删改查实现类
 * @author hc
 * @date 2019年8月7日下午4:58:47
 */
@Service
public class UserServiceImpl implements UserService {
	public static Log log = LogFactory.getLog(UserServiceImpl.class);
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private EncryptUtil encryptUtil;
	@Autowired
	private RedisUtil redisUtil;
	
	
	@Override
	public boolean save(User record) {
		boolean flag = false;
		try {
			userMapper.addUser(record);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return flag;
	}

	@Override
	public boolean update(User record) {
		boolean flag = false;
		try {
			int result = userMapper.updateUser(record);
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
			int result = userMapper.deleteUserById(id);
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
	public User getById(String id) {
		User user = null;
		try {
			user = redisUtil.get(id,User.class);
			if(user == null) {
				user = userMapper.getUserById(id);
				if(user != null) {
					redisUtil.set(id, user);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return user;
	}

	@Override
	public User getUserByUsername(String userName) {
		User user = null;
		try {
			user = userMapper.getUserByUsername(userName);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return user;
	}
	
	/**
	 * User对象的分页条件查询
	 */
	@Override
	public PageInfo<User> getPageInfo(PageRequest pageRequest,UserCondition userCondition) {
		PageInfo<User> userPageInfo = null;
		PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
		try {
			List<User> userList = userMapper.getAllUser(userCondition);
			userPageInfo = new PageInfo<User>(userList);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return userPageInfo;
	}
	
	/**
	 * 
	 * @Title: isUser  
	 * @Description: 判断输入用户名和密码，是否为数据库中用户
	 * @param userName
	 * @return false该用户账号密码错误，true则该用户为该数据库中用户
	 */
	public boolean isUser(User user,String password_entry_in) {
		boolean flag = false;
		if(user != null) {
			if(StringUtil.isNotEmpty(password_entry_in)) {
				String password_entry_in_des = encryptUtil.decrypt(user.getPassword());//数据库密码解密
				String password_entry_in_des_md5 = encryptUtil.EncoderByMd5(password_entry_in_des);//后台解密数据以md5方式加密
				if(password_entry_in.equals(password_entry_in_des_md5)) {//前后台密码都要进行加密，前台数据需要md5方式加密
					flag = true;
				}
			}
		}
		return flag;
	}

}

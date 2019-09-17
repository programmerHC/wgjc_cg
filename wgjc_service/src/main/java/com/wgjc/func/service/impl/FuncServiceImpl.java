package com.wgjc.func.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wgjc.func.dao.FuncMapper;
import com.wgjc.func.entity.Func;
import com.wgjc.func.service.FuncService;

/** 
 * @Description: 
 * @author hc
 * @date 2019年9月17日下午2:12:40
 */
@Service
public class FuncServiceImpl implements FuncService {
	private static Log log = LogFactory.getLog(FuncServiceImpl.class);
	
	@Autowired
	private FuncMapper funcMapper;

	@Override
	public boolean save(Func record) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Func record) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Func getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 获取角色菜单列表
	 */
	@Override
	public List<Func> getRoleFunc(String roleId) {
		List<Func> funcList = null;
		try {
			funcList = funcMapper.getRoleFunc(roleId);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return funcList;
	}

}

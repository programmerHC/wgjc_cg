package com.wgjc.func.service;

import java.util.List;

import com.wgjc.base.service.BaseService;
import com.wgjc.func.entity.Func;

/** 
 * @Description: 
 * @author hc
 * @date 2019年9月17日下午2:11:46
 */
public interface FuncService extends BaseService<Func> {
	public List<Func> getRoleFunc(String roleId);
}

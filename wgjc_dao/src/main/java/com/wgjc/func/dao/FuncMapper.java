package com.wgjc.func.dao;

import java.util.List;

import com.wgjc.func.entity.Func;

/** 
 * @Description: 角色菜单加载
 * @author hc
 * @date 2019年9月17日上午10:06:06
 */
public interface FuncMapper {
	public List<Func> getRoleFunc(String roleId);
}

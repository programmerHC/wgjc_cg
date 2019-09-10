package com.wgjc.param.dao;

import com.wgjc.param.entity.Param;

/** 
 * @Description: param获取参数
 * @author hc
 * @date 2019年9月10日下午12:09:20
 */
public interface ParamMapper {
	public Param getParamByCode(String code);
}

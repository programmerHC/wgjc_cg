package com.wgjc.param.service;

import org.springframework.stereotype.Service;

import com.wgjc.param.entity.Param;

/** 
 * @Description: param业务处理类
 * @author hc
 * @date 2019年9月10日下午1:56:27
 */
public interface ParamService {
	public Param getParamByCode(String code);
}

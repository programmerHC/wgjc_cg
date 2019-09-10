package com.wgjc.param.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wgjc.param.dao.ParamMapper;
import com.wgjc.param.entity.Param;
import com.wgjc.param.service.ParamService;

/** 
 * @Description: param业务逻辑处理实现类
 * @author hc
 * @date 2019年9月10日下午1:58:03
 */
@Service
public class ParamServiceImpl implements ParamService{
	public static Log log = LogFactory.getLog(ParamServiceImpl.class);
	
	@Autowired
	private ParamMapper paramMapper;
	
	@Override
	public Param getParamByCode(String code) {
		Param param = null;
		try {
			param = paramMapper.getParamByCode(code);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return param;
	}

}

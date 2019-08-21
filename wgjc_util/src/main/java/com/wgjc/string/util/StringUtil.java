package com.wgjc.string.util;

import org.springframework.stereotype.Component;

/** 
 * @Description: String类型字符串处理工具类
 * @author hc
 * @date 2019年8月8日下午2:33:49
 */
@Component
public class StringUtil {
	/**
	 * @Title: isEmpty  
	 * @Description: 判断字符串是否为空
	 * @param s
	 * @return
	 */
	public boolean isEmpty(String s) {
		boolean flag = false;
		if(null != s && !"".equals(s)) {
			flag = true;
		}
		return flag;
	}
}

package com.wgjc.entrypt.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wgjc.SpringbootWgjcApplication;
import com.wgjc.encrypt.util.EncryptUtil;

/** 
 * @Description: 加密解密测试
 * @author hc
 * @date 2019年7月30日下午1:59:38
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootWgjcApplication.class)
public class EntryptUtil {
	@Autowired
	private EncryptUtil encryptUtil;
	
	@Test
	public void testEntryPt() {
		System.err.println(encryptUtil.encrypt("huanghcao"));
		System.err.println(encryptUtil.decrypt("F00F9FBD738B3A286ADF298FE2F0E1C1"));
	}
}

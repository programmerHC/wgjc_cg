package com.wgjc.func.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wgjc.func.entity.Func;
import com.wgjc.func.service.FuncService;

/** 
 * @Description: 菜单类加载
 * @author hc
 * @date 2019年9月17日下午2:30:09
 */
@RestController
public class FuncController {
	@Autowired
	private FuncService funcService;
	
	@PostMapping("/func")
	public List<Func> getRoleFunc(HttpSession session){
		List<Func> funcList = null;
		//String roleId = (String) session.getAttribute("CURRENT_ROLE");
		String roleId = "53a57c3ec3f311e99d9d00ff5dd1b123";
		funcList = funcService.getRoleFunc(roleId);
		return funcList;
	}
}

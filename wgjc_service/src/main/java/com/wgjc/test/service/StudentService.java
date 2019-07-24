package com.wgjc.test.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wgjc.base.entity.PageRequest;
import com.wgjc.base.service.BaseService;
import com.wgjc.test.entity.Student;

public interface StudentService extends BaseService<Student>{
	public List<Student> getAllStudents();
	public PageInfo<Student> getPageInfo(PageRequest pageRequest); 
}

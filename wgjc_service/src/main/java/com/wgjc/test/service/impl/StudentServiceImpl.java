package com.wgjc.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wgjc.page.entity.PageRequest;
import com.wgjc.test.dao.StudentMapper;
import com.wgjc.test.entity.Student;
import com.wgjc.test.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	public StudentMapper studentMapper;
	
	@Override
	public List<Student> getAllRecord() {
		List<Student> list = studentMapper.getallStudents();
		return list;
	}

	@Override
	public int save(Student record) {
		return studentMapper.addStudent(record);
	}

	@Override
	public int update(Student record) {
		return studentMapper.updateStudent(record);
	}

	@Override
	public int delete(String id) {
		return studentMapper.deleteStudentByID(id);
	}

	@Override
	public Student getById(String id) {
		return studentMapper.getStudentByID(id);
	}
	
	@Override
	public PageInfo<Student> getPageInfo(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Student> sysMenus = studentMapper.getallStudents();
        return new PageInfo<Student>(sysMenus);
    }
	

}

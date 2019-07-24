package com.wgjc.student.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.pagehelper.PageHelper;
import com.wgjc.SpringbootWgjcApplication;
import com.wgjc.base.entity.PageRequest;
import com.wgjc.base.entity.PageResult;
import com.wgjc.base.util.PageUtils;
import com.wgjc.test.dao.StudentMapper;
import com.wgjc.test.entity.Student;
import com.wgjc.test.service.StudentService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootWgjcApplication.class)
public class StudentTest {
	
	@Autowired
	private StudentService studentService;
	
	@Test
	public void test() {
		System.out.println(studentService.getAllStudents());
		System.err.println("===============");
		System.out.println(studentService.getById("222222"));
		System.err.println("===============");
		Student student = studentService.getById("222222");
		student.setName("updateName");
		studentService.update(student);
		System.out.println(studentService.getById("222222"));
		
	}
	
	@Test
	public void getPageStudent() {
		PageRequest pageRequest = new PageRequest();
		pageRequest.setPageNum(1);
		pageRequest.setPageSize(2);
		PageResult result = PageUtils.getPageResult(studentService.getPageInfo(pageRequest));
		System.out.println(result.toString());
	}
	
	
}

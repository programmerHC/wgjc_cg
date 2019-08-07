package com.wgjc.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wgjc.test.entity.Student;
import com.wgjc.test.service.StudentService;

@RestController
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping("/getallStudent")
	public List<Student> getAllStudent(){
		return studentService.getAllRecord();
	}
	
	@RequestMapping("/addStudent")
	public int addStudent() {
		Student student = new Student();
		student.setID("333333");
		student.setName("student3");
		student.setAge(33);
		return studentService.save(student);
	}
}



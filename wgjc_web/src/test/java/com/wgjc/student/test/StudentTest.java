package com.wgjc.student.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.github.pagehelper.PageHelper;
import com.wgjc.SpringbootWgjcApplication;
import com.wgjc.base.entity.PageRequest;
import com.wgjc.base.entity.PageResult;
import com.wgjc.base.util.PageUtils;
import com.wgjc.test.controller.StudentController;
import com.wgjc.test.dao.StudentMapper;
import com.wgjc.test.entity.Student;
import com.wgjc.test.service.StudentService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootWgjcApplication.class)
public class StudentTest {
	
	@Autowired
	private StudentService studentService;
	
	@Value("${server.port}")
	private int port;
	
	@Autowired
	private StudentController  controller;
	
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
	
	@Test
	public void testAnnotation() {
		System.out.println(port);
		
		MockMvc mvc = MockMvcBuilders.standaloneSetup(controller).build();
		
	     RequestBuilder builder = MockMvcRequestBuilders
	                .get("/getallStudent")
	                .accept(MediaType.APPLICATION_JSON)
	                .contentType(MediaType.APPLICATION_JSON_UTF8);
	        MvcResult result;
			try {
				result = mvc.perform(builder).andReturn();
				System.out.println(result.getResponse().getContentAsString());
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
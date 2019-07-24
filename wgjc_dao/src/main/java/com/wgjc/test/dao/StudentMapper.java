package com.wgjc.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.wgjc.test.entity.Student;

//@Mapper
public interface StudentMapper {
	public Student getStudentByID(String ID);
	public List<Student> getallStudents();
	public int addStudent(Student student);
	public int updateStudent(Student student);
	public int deleteStudentByID(String ID);
}

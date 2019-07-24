package com.wgjc.test.entity;

import java.io.Serializable;

public class Student implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String ID;
	private String name;
	private int age;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "Student [ID=" + ID + ", name=" + name + ", age=" + age + "]";
	}
}

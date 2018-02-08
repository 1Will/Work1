package main.service;

import java.util.List;

import main.pojo.Department;

import org.hibernate.Session;

public interface DepartmentService  {
	
	public List<String> getDeptnameById(Long id); //通过Id获取部门名称
	
    public Department getDepartmentById(Long id);
	
    public List<Department> getClassiFicationFromDept(); //通过部门表 获取片区分类集合
	   
    public Session getSuperSession(); 
	
}

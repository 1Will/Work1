package main.dao;

import java.util.List;

import org.hibernate.Session;

public interface DepartmentDao {
   
    public List<String> getDeptnameById(Long id); //通过Id获取部门名称
   
    public Session getSuperSession(); 
	
}

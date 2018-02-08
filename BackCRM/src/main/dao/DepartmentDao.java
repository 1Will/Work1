package main.dao;

import java.util.List;

import main.pojo.Department;

import org.hibernate.Session;

public interface DepartmentDao {
   
    public List<String> getDeptnameById(Long id); //ͨ��Id��ȡ��������
 
    public Department getDepartmentById(Long id);
    
    public List<Department> getClassiFicationFromDept(); //ͨ�����ű� ��ȡƬ�����༯��
   
    public Session getSuperSession(); 
	
}

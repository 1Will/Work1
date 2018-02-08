package main.service.impl;

import java.util.List;

import org.hibernate.Session;

import main.dao.DepartmentDao;
import main.pojo.Department;
import main.service.DepartmentService;

public class DepartmentServiceImpl implements DepartmentService {
    
	private  DepartmentDao departmentDao;
	@Override
	public List<String> getDeptnameById(Long id) {
		return departmentDao.getDeptnameById(id);
	}
   
	@Override
	public Department getDepartmentById(Long id) {
		return departmentDao.getDepartmentById(id);
	}

	
	@Override
	public List<Department> getClassiFicationFromDept() {
		return departmentDao.getClassiFicationFromDept();
	}

	@Override
	public Session getSuperSession() {
		return departmentDao.getSuperSession();
	}

	public DepartmentDao getDepartmentDao() {
		return departmentDao;
	}

	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}



	
}

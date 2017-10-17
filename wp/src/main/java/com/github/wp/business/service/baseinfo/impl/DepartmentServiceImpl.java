package com.github.wp.business.service.baseinfo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.wp.business.dao.baseinfo.DepartmentDao;
import com.github.wp.business.pojo.base.Tdepartment;
import com.github.wp.business.service.baseinfo.DepartmentService;


@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentDao departmentDao;

	@Override
	public List<?> findChildById(Long id) {
		
		return departmentDao.findChildById(id);
	}
	
	/**
	 * @param id
	 * @return Tdepartment 部门
	 * @author dupeng
	 * @since 07-22-2016
	 */
	public Tdepartment findOne(Long id) {
		return departmentDao.findOne(id);
	}
	
	/**
	 * @param Tdepartment
	 * @return Tdepartment 保存或新增部门信息
	 * @author dupeng
	 * @since 07-22-2016
	 */
	
	public void saveOrUpdate(Tdepartment tdepartment){
		
		departmentDao.saveOrUpdate(tdepartment);
	}
	
	/**
	 * @param id
	 * @author 杜鹏
	 * @since 07-22-16
	 * 根据id得到Tdepartment部门对象，改变部门的表示状态为D，保存，假删除
	 */
	public void deleteOne(Long id){
		departmentDao.deleteOne(id);
	}

	
}

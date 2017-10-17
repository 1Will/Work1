package com.github.wp.business.service.baseinfo;

import java.util.List;

import com.github.wp.business.pojo.base.Tdepartment;




public interface DepartmentService {

	public List<?> findChildById(Long id) ;
	
	/**
	 * @param id
	 * @return Tdepartment 部门
	 * @author dupeng
	 * @since 07-22-2016
	 */
	public Tdepartment findOne(Long id) ;
	
	/**
	 * @param Tdepartment
	 * @return Tdepartment 保存或新增部门信息
	 * @author dupeng
	 * @since 07-22-2016
	 */
	
	public void saveOrUpdate(Tdepartment tdepartment);
	
	/**
	 * @param id
	 * @author 杜鹏
	 * @since 07-22-16
	 * 根据id得到Tdepartment部门对象，改变部门的表示状态为D，保存，假删除
	 */
	public void deleteOne(Long id);
}

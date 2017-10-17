package com.github.wp.business.dao.baseinfo;

import java.util.List;

import com.github.wp.business.pojo.base.Tdepartment;



/**
 * <p>
 * 部门管理的dao层：杜鹏
 * <p>
 * Date: 07-20-16
 * <p>
 * Version: 1.0
 */
public interface DepartmentDao {

	/**
	 * 根据id得到此部门的所有子部门的集合
	 * @param id
	 * @return List<?>
	 * @author 杜鹏
	 * @since 07-20-16
	 */
	public List<?> findChildById(Long id) ;
	
	/**
	 * @param id
	 * @return Tdepartment 部门
	 * @author dupeng
	 * @since 07-20-2016
	 */
	public Tdepartment findOne(Long id) ;
	
	/**
	 * @param Tdepartment
	 * @return Tdepartment 保存或新增部门信息
	 * @author dupeng
	 * @since 07-21-2016
	 */
	
	public void saveOrUpdate(Tdepartment tdepartment);
	
	/**
	 * @param id
	 * @author 杜鹏
	 * @since 07-21-16
	 * 根据id得到Tdepartment部门对象，改变部门的表示状态为D，保存，假删除
	 */
	public void deleteOne(Long id);
	
}

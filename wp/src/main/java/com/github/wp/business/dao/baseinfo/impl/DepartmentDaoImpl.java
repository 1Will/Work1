package com.github.wp.business.dao.baseinfo.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.github.wp.business.dao.baseinfo.DepartmentDao;
import com.github.wp.business.pojo.base.Tdepartment;


/**
 * <p>
 * 部门管理中dao层实现: 杜鹏
 * <p>
 * Date: 07-22-16
 * <p>
 * Version: 1.0
 */
@Repository
public class DepartmentDaoImpl implements DepartmentDao{

	@Resource
	private SessionFactory sessionFactory;

	public Session getSuperSession() {
		return this.sessionFactory.getCurrentSession();
	}

	/**
	 * 根据id得到此部门的所有子部门的集合
	 * @param id
	 * @return List<?>
	 * @author 杜鹏
	 * @since 07-22-16
	 */
	@Override
	public List<?> findChildById(Long id) {
		String hql = "from Tdepartment tempA";
		if (id == null)
			hql += " where tempA.department.id is null";
		else
			hql += " where tempA.department.id = " + id;
		return this.getSuperSession().createQuery(hql).list();
	}

	
	/**
	 * @param id
	 * @return Tdepartment 部门
	 * @author dupeng
	 * @since 07-22-2016
	 */
	public Tdepartment findOne(Long id) {
		return (Tdepartment) this.getSuperSession().get(
				Tdepartment.class, id);
	}
	
	/**
	 * @param Tdepartment
	 * @return Tdepartment 保存或新增部门信息
	 * @author dupeng
	 * @since 07-22-2016
	 */
	
	public void saveOrUpdate(Tdepartment tdepartment){
		this.getSuperSession().saveOrUpdate(tdepartment);
	}
	
	
	/**
	 * @param id
	 * @author 杜鹏
	 * @since 07-22-16
	 * 根据id得到Tdepartment对象，改变部门的表示状态为D，保存，假删除
	 */
	public void deleteOne(Long id){
		Tdepartment tdepartment = findOne(id);
		tdepartment.setEffectflag('D');
		this.getSuperSession().saveOrUpdate(tdepartment);
	}
	
}

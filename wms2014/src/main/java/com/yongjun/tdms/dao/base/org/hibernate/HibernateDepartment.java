/*
 * Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of YongJun
 * Technology Pte.,Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with YongJun.
 * 
 * YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 */
package com.yongjun.tdms.dao.base.org.hibernate;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.ProductionLine;
import com.yongjun.tdms.dao.base.org.DepartmentDao;
import com.yongjun.tdms.model.prophase.business.SubscribeDetailView;

/**
 * <p>Title: HibernateDepartmentDao
 * <p>Description: 部门数据访问实现类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author qs@yj-technology.com
 * @version $Id: HibernateDepartment.java 10818 2008-01-31 02:25:29Z qsun $
 * @see com.yongjun.tdms.dao.base.org.DepartmentDao
 */
public class HibernateDepartment extends BaseHibernateDao implements DepartmentDao {

	public void storeDepartment(Department department) {
		this.store(department);
	}

	public Department loadDepartment(Long departmentId) {
		return this.load(Department.class, departmentId);
	}

	public void deleteDepartment(Department department) {
		this.delete(department);
	}

	public void deleteAllDepartments(Collection<Department> departmentIds) {
		this.deleteAll(departmentIds);
	}

	public List<Department> loadAllDepartment(Long[] departmentIds) {
		return this.loadAll(Department.class, departmentIds);
	}

	/**
	 * @FIXED: 修改为使用HQL查询所有部门数据，部门中包含失效的数据，另外增加了自定义排序字段
	 */
	@SuppressWarnings("unchecked")
	public List<Department> loadAllDepartments() {
		return (List<Department>) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("LoadAllDepartmentsBySortIdx").list();								
					}
				});
	}
	
	/**
	 * @FIXED: 修改原来的HQL写法，把HQL配置到HBM文件中. qs(12/28/2007)
	 */
	@SuppressWarnings("unchecked")
	public List<ProductionLine> getUnJoinedProductionLines(){
		return (List<ProductionLine>) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetUnJoinedProductionLines").list();								
					}
				});
	}
	
	/* Modify at 2009-08-24 16:15:00 by wliu*/
	/**
	 * 根据部门代码查询部门信息
	 * 
	 * @param code 部门代码
	 * @return 部门信息
	 */
	public Department loadDepartmentByCode(final String code){
		return (Department) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						
						return session.getNamedQuery("GetDepartmentByCode").setParameter("code", code).uniqueResult();							
					}
				});
	}
	/* Modify at 2009-08-25 10:00:00 by wliu*/
	/**
	 * 根据部门名称查询部门信息
	 * 
	 * @param name 部门名称
	 * @return 部门信息
	 */
	public Department loadDepartmentByName(final String name){
		return (Department) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						
						return session.getNamedQuery("GetDepartmentByName").setParameter("name", name).uniqueResult();							
					}
				});
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Department> filialeSelectDept(final Long filialeId) {
		return (List<Department>)this.getHibernateTemplate().execute(
				new HibernateCallback(){
					public Object doInHibernate(Session session)
						throws HibernateException ,SQLException{
						return session.getNamedQuery("FilialeSelectDept").setParameter("filialeId", filialeId).list();
					}
				});
	}
	
	public List<Department> loadDepartmentByFiliale(final Long filialeId){
		List<Department> result = null;
		Transaction tx = null;
		Session session = getSession();	
		try {
			String hql="";
			hql = "FROM Department as dept WHERE 1=1 and dept.disabled=false";	
			if (filialeId != null) {
				hql = hql + " and dept.filiale.id="+filialeId;
			}else{
				hql = hql+"  ";
			}
			hql = hql + " ORDER BY dept.sortIdx";
			//System.out.println("   hql:  " + hql);
			tx = session.beginTransaction();
			Query query = getSession().createQuery(hql);		
			result = query.list();
			tx.commit();
			return result;
		}catch (HibernateException e) {
			e.printStackTrace();
			return null;
		} finally {
			releaseSession(session);
		}
	}

}

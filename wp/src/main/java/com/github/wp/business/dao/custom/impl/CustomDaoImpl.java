package com.github.wp.business.dao.custom.impl;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.github.wp.business.dao.custom.CustomDao;
import com.github.wp.business.pojo.custom.Bcustomerinfo;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;


/**
 * 客户管理实现类
 * @author dupeng
 * @version 1.0
 * @since 2016年8月09日
 */
@Repository
public class CustomDaoImpl implements CustomDao {

	@Resource
	public SessionFactory sessionFactory;

	public Session getSuperSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<Bcustomerinfo> findPage(Bcustomerinfo custom, Pagination pagination){
		Criteria criteria = this.getSuperSession().createCriteria(Bcustomerinfo.class);
		if(custom != null && custom.getName() != null && !"".equals(custom.getName())){
			criteria.add(Restrictions.like("name", "%" + custom.getName() + "%"));
		}
		if(custom != null && custom.getCode() != null && !"".equals(custom.getCode())){
			criteria.add(Restrictions.like("code", "%" + custom.getCode() + "%"));
		}
		criteria.setProjection(Projections.rowCount());
		Long count = (Long) criteria.uniqueResult();
		criteria.addOrder(Order.desc("createdTime"));
		criteria.setProjection(null);
		criteria.setFirstResult(pagination.getFirstSize());// 初始行数
		criteria.setMaxResults(pagination.getMaxSize());
		Pager<Bcustomerinfo> pager = new Pager<Bcustomerinfo>();
		pager.setTotal(count.intValue());
		pager.setRows(criteria.list());
		return pager;
		
	}
	
	/**
	 * @param id
	 * @return Bcustomerinfo 客户类
	 * @author dupeng
	 * @since 08-08-16
	 */
	@Override
	public Bcustomerinfo findOne(Long id) {
		return (Bcustomerinfo) this.getSuperSession().get(
				Bcustomerinfo.class, id);
	}
	
	/**
	 * @param id
	 * @author 杜鹏
	 * @since 08-10-16
	 * 根据id得到Bcustomerinfo对象，改变状态为D，保存，假删除
	 */
	@Override
	public void deleteOne(Long[] ids){
		for (Long id : ids) {
			Bcustomerinfo custom = findOne(id);
			custom.setEffectflag('D');
			this.getSuperSession().saveOrUpdate(custom);
		}
	}
	
	/**
	 * @param Bcustomerinfo
	 * @return Bcustomerinfo 保存或新增
	 * @author dupeng
	 * @since 08-10-16
	 */
	@Override
	public void saveOrUpdate(Bcustomerinfo custom){
		this.getSuperSession().saveOrUpdate(custom);
	}
	
	
}

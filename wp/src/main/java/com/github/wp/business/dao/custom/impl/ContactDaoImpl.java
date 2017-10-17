package com.github.wp.business.dao.custom.impl;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.github.wp.business.dao.custom.ContactDao;
import com.github.wp.business.pojo.custom.Bcontactarchives;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;


/**
 * 联系人管理实现类
 * @author dupeng
 * @version 1.0
 * @since 2016年8月3日
 */
@Repository
public class ContactDaoImpl implements ContactDao {

	@Resource
	public SessionFactory sessionFactory;

	public Session getSuperSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<Bcontactarchives> findPage(Bcontactarchives contact, Pagination pagination){
		Criteria criteria = this.getSuperSession().createCriteria(Bcontactarchives.class);
		if(contact != null && contact.getName() != null && !"".equals(contact.getName())){
			criteria.add(Restrictions.like("name", "%" + contact.getName() + "%"));
		}
		if(contact != null && contact.getCustomInfo().getId() != null){
			criteria.createAlias("customInfo", "custom").add(Restrictions.eq("custom.id", contact.getCustomInfo().getId()));
		}
		criteria.setProjection(Projections.rowCount());
		Long count = (Long) criteria.uniqueResult();
		criteria.addOrder(Order.desc("createdTime"));
		criteria.setProjection(null);
		criteria.setFirstResult(pagination.getFirstSize());// 初始行数
		criteria.setMaxResults(pagination.getMaxSize());
		Pager<Bcontactarchives> pager = new Pager<Bcontactarchives>();
		pager.setTotal(count.intValue());
		criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		pager.setRows(criteria.list());
		return pager;
		
	}
	
	/**
	 * @param id
	 * @return Bcontactarchives 部门
	 * @author dupeng
	 * @since 08-08-16
	 */
	@Override
	public Bcontactarchives findOne(Long id) {
		return (Bcontactarchives) this.getSuperSession().get(
				Bcontactarchives.class, id);
	}
	
	/**
	 * @param Bcontactarchives
	 * @return Bcontactarchives 保存或新增
	 * @author dupeng
	 * @since 08-08-16
	 */
	@Override
	public void saveOrUpdate(Bcontactarchives contact){
		this.getSuperSession().saveOrUpdate(contact);
	}
	
	
	/**
	 * @param id
	 * @author 杜鹏
	 * @since 08-08-16
	 * 根据id得到Bcontactarchives对象，改变状态为D，保存，假删除
	 */
	@Override
	public void deleteOne(Long[] ids){
		for (Long id : ids) {
			Bcontactarchives contact = findOne(id);
			contact.setEffectflag('D');
			this.getSuperSession().saveOrUpdate(contact);
		}
	}

}

package com.github.wp.business.dao.contract.impl;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.github.wp.business.dao.contract.ContractDao;
import com.github.wp.business.pojo.qiandan.Hcontractmanagement;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;


/**
 * 合同管理实现类
 * @author dupeng
 * @version 1.0
 * @since 2016年8月11日
 */
@Repository
public class ContractDaoImpl implements ContractDao {

	@Resource
	public SessionFactory sessionFactory;

	public Session getSuperSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<Hcontractmanagement> findPage(Hcontractmanagement contract, Pagination pagination){
		Criteria criteria = this.getSuperSession().createCriteria(Hcontractmanagement.class);
		if(contract != null && contract.getContractname() != null && !"".equals(contract.getContractname())){
			criteria.add(Restrictions.like("contractname", "%" + contract.getContractname() + "%"));
		}
		if(contract != null && contract.getCode() != null && !"".equals(contract.getCode())){
			criteria.add(Restrictions.like("code", "%" + contract.getCode() + "%"));
		}
		criteria.setProjection(Projections.rowCount());
		Long count = (Long) criteria.uniqueResult();
		criteria.addOrder(Order.desc("createdTime"));
		criteria.setProjection(null);
		criteria.setFirstResult(pagination.getFirstSize());// 初始行数
		criteria.setMaxResults(pagination.getMaxSize());
		Pager<Hcontractmanagement> pager = new Pager<Hcontractmanagement>();
		pager.setTotal(count.intValue());
		pager.setRows(criteria.list());
		return pager;
		
	}
	
	/**
	 * @param id
	 * @return Hcontractmanagement 客户类
	 * @author dupeng
	 * @since 08-11-16
	 */
	@Override
	public Hcontractmanagement findOne(Long id) {
		return (Hcontractmanagement) this.getSuperSession().get(
				Hcontractmanagement.class, id);
	}
	
	/**
	 * @param id
	 * @author 杜鹏
	 * @since 08-11-16
	 * 根据id得到Hcontractmanagement对象，改变状态为D，保存，假删除
	 */
	@Override
	public void deleteOne(Long[] ids){
		for (Long id : ids) {
			Hcontractmanagement contract = findOne(id);
			contract.setEffectflag('D');
			this.getSuperSession().saveOrUpdate(contract);
		}
	}
	
	/**
	 * @param Hcontractmanagement
	 * @return Hcontractmanagement 保存或新增
	 * @author dupeng
	 * @since 08-11-16
	 */
	@Override
	public void saveOrUpdate(Hcontractmanagement contract){
		this.getSuperSession().saveOrUpdate(contract);
	}
	
	
}

package com.github.wp.business.dao.expense.impl;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.github.wp.business.dao.expense.ExpenseapplyDao;
import com.github.wp.business.pojo.cw.CExpenseapply;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;


/**
 * 费用申请实现类
 * @author dupeng
 * @version 1.0
 * @since 2016年8月26日
 */
@Repository
public class ExpenseapplyDaoImpl implements ExpenseapplyDao {

	@Resource
	public SessionFactory sessionFactory;

	public Session getSuperSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Pager<CExpenseapply> findPage(CExpenseapply apply, Pagination pagination){
		Criteria criteria = this.getSuperSession().createCriteria(CExpenseapply.class);
		if(apply != null && apply.getExpensename() != null && !"".equals(apply.getExpensename())){
			criteria.add(Restrictions.like("expensename", "%" + apply.getExpensename() + "%"));
		}
		if(apply != null && apply.getCode() != null && !"".equals(apply.getCode())){
			criteria.add(Restrictions.like("code", "%" + apply.getCode() + "%"));
		}
		criteria.setProjection(Projections.rowCount());
		Long count = (Long) criteria.uniqueResult();
		criteria.addOrder(Order.desc("createdTime"));
		criteria.setProjection(null);
		criteria.setFirstResult(pagination.getFirstSize());// 初始行数
		criteria.setMaxResults(pagination.getMaxSize());
		Pager<CExpenseapply> pager = new Pager<CExpenseapply>();
		pager.setTotal(count.intValue());
		pager.setRows(criteria.list());
		return pager;
		
	}
	
	/**
	 * @param id
	 * @return CExpenseapply 客户类
	 * @author dupeng
	 * @since 08-26-16
	 */
	@Override
	public CExpenseapply findOne(Long id) {
		return (CExpenseapply) this.getSuperSession().get(
				CExpenseapply.class, id);
	}
	
	/**
	 * @param id
	 * @author 杜鹏
	 * @since 08-26-16
	 * 根据id得到CExpenseapply对象，改变状态为D，保存，假删除
	 */
	@Override
	public void deleteOne(Long[] ids){
		for (Long id : ids) {
			CExpenseapply apply = findOne(id);
			apply.setEffectflag('D');
			this.getSuperSession().saveOrUpdate(apply);
		}
	}
	
	/**
	 * @param CExpenseapply
	 * @return CExpenseapply 保存或新增
	 * @author dupeng
	 * @since 08-26-16
	 */
	@Override
	public void saveOrUpdate(CExpenseapply apply){
		this.getSuperSession().saveOrUpdate(apply);
	}
	
	
}

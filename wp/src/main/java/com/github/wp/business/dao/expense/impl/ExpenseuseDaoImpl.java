package com.github.wp.business.dao.expense.impl;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.github.wp.business.dao.expense.ExpenseuseDao;
import com.github.wp.business.pojo.cw.CExpenseuse;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;


/**
 * 费用使用实现类
 * @author dupeng
 * @version 1.0
 * @since 2016年8月29日
 */
@Repository
public class ExpenseuseDaoImpl implements ExpenseuseDao {

	@Resource
	public SessionFactory sessionFactory;

	public Session getSuperSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<CExpenseuse> findPage(CExpenseuse use, Pagination pagination){
		Criteria criteria = this.getSuperSession().createCriteria(CExpenseuse.class);
		if(use != null && use.getUserperson() != null && !"".equals(use.getUserperson())){
			criteria.add(Restrictions.like("userperson", "%" + use.getUserperson() + "%"));
		}
		criteria.setProjection(Projections.rowCount());
		Long count = (Long) criteria.uniqueResult();
		criteria.addOrder(Order.desc("createdTime"));
		criteria.setProjection(null);
		criteria.setFirstResult(pagination.getFirstSize());// 初始行数
		criteria.setMaxResults(pagination.getMaxSize());
		Pager<CExpenseuse> pager = new Pager<CExpenseuse>();
		pager.setTotal(count.intValue());
		pager.setRows(criteria.list());
		return pager;
		
	}
	
	/**
	 * @param id
	 * @return CExpenseuse 费用使用
	 * @author dupeng
	 * @since 08-29-16
	 */
	@Override
	public CExpenseuse findOne(Long id) {
		return (CExpenseuse) this.getSuperSession().get(
				CExpenseuse.class, id);
	}
	
	/**
	 * @param id
	 * @author 杜鹏
	 * @since 08-29-16
	 * 根据id得到CExpenseuse对象，改变状态为D，保存，假删除
	 */
	@Override
	public void deleteOne(Long[] ids){
		for (Long id : ids) {
			CExpenseuse use = findOne(id);
			use.setEffectflag('D');
			this.getSuperSession().saveOrUpdate(use);
		}
	}
	
	/**
	 * @param CExpenseuse
	 * @return CExpenseuse 保存或新增
	 * @author dupeng
	 * @since 08-29-16
	 */
	@Override
	public void saveOrUpdate(CExpenseuse use){
		this.getSuperSession().saveOrUpdate(use);
	}
	
	
}

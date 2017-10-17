package com.github.wp.business.dao.baseinfo.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.github.wp.business.dao.baseinfo.ProductDao;
import com.github.wp.business.pojo.product.Product;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;

@Repository
public class ProductDaoImpl implements ProductDao {
	
	@Resource
	public SessionFactory sessionFactory;
	
	public Session getSuperSession(){
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public Pager<Product> findPage(Product product, Pagination pagination) {
		
			Criteria criteria = this.getSuperSession()
					.createCriteria(Product.class);
			if (product != null && product.getName() != null
					&& !product.getName().equals("")) {
				criteria.add(Restrictions.like("name",
						"%" + product.getName() + "%"));
			}
			criteria.setProjection(Projections.rowCount());
			Long count = (Long) criteria.uniqueResult();
			criteria.setProjection(null);
			criteria.setFirstResult(pagination.getFirstSize());// 初始行数
			criteria.setMaxResults(pagination.getMaxSize());
			Pager<Product> pager = new Pager<Product>();
			pager.setTotal(count.intValue());
			pager.setRows(criteria.list());
			return pager;
		
		
		}

	@Override
	public void saveOrUpdate(Product p) {
		// TODO Auto-generated method stub
		this.getSuperSession().saveOrUpdate(p);
		
	}

	@Override
	public Product getProductByid(long id) {
		// TODO Auto-generated method stub
		return (Product)this.getSuperSession().get(Product.class, id);
	}

	@Override
	public void deleteProduct(Long[] ids) {
		for(Long id:ids){
			String hql = "delete from Product temp where temp.id = " + id;
			this.getSuperSession().createQuery(hql).executeUpdate();
		}
	}
}

package main.dao.impl;

import main.dao.ProductsDao;
import main.pojo.Products;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


public class ProductsDaoImpl extends HibernateDaoSupport implements ProductsDao {
	

	@Override
	public Products getProductById(Long id) {
		Products products = null;
		try {
			products=(Products) this.getSession().load(Products.class, id);
		} catch (Exception e) {
            e.printStackTrace();
		}
		return products;
	}
	
	
    public Session getSuperSession() {
		
		return this.getSession(true);
	}
	
    
	
}

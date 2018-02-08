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
package com.yongjun.tdms.dao.base.product.hibernate;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.base.product.ProductDao;
import com.yongjun.tdms.model.base.product.Product;

/**
 * @author qs
 * @version $Id: HibernateProduct.java 10412 2008-01-16 01:27:50Z qsun $
 */
public class HibernateProduct extends BaseHibernateDao implements ProductDao {

	public Product loadProduct(Long productId) {
		return this.load(Product.class, productId);
	}

	public List<Product> loadAllProducts(Long[] productIds) {
		return this.loadAll(Product.class, productIds);
	}

	public List<Product> loadAllProducts() {
		return this.loadAll(Product.class);
	}

	public void storeProduct(Product product) {
		this.store(product);
	}

	public void deleteProduct(Product product) {
		this.delete(product);
	}

	public void deleteAllProducts(Collection<Product> products) {
		this.deleteAll(products);
	}

	public Product getProductByName(final String name) {
		return (Product)this.getHibernateTemplate().execute(
				new HibernateCallback(){
					public Object doInHibernate(Session session)
						throws HibernateException , SQLException{
							return session.getNamedQuery("getProductByName")
						.setParameter("name", name).uniqueResult();
					}
				});
	}

	public Product getProductByProductNo(final String productNo) {
		return (Product)this.getHibernateTemplate().execute(
				new HibernateCallback(){
					public Object doInHibernate(Session session)
						throws HibernateException , SQLException{
							return session.getNamedQuery("getProductByProductNo")
							.setParameter("productNo", productNo).uniqueResult();
					}
				});
	}

}

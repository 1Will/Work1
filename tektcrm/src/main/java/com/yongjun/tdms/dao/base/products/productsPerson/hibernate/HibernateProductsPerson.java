package com.yongjun.tdms.dao.base.products.productsPerson.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.base.products.productsPerson.ProductsPersonDao;
import com.yongjun.tdms.model.base.products.productsPerson.ProductsPerson;

public class HibernateProductsPerson extends BaseHibernateDao implements ProductsPersonDao {
	public void deleteAllProducts(Collection<ProductsPerson> productes) {
		deleteAll(productes);
	}

	public void deleteProducts(ProductsPerson products) {
		delete(products);
	}

	public List<ProductsPerson> loadAllProducts(Long[] productsIds) {
		return loadAll(ProductsPerson.class, productsIds);
	}

	public List<ProductsPerson> loadAllProducts() {
		return loadAll(ProductsPerson.class);
	}

	public ProductsPerson loadProductType(Long productsId) {
		return (ProductsPerson) load(ProductsPerson.class, productsId);
	}

	public void storeProducts(ProductsPerson products) {
		store(products);
	}

	public List<ProductsPerson> loadByKey(String keyName, Object keyValue) throws DaoException {
		return loadByKey(ProductsPerson.class, keyName, keyValue);
	}
}
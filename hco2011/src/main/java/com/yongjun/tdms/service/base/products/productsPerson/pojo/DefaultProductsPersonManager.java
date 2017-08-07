package com.yongjun.tdms.service.base.products.productsPerson.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.base.products.productsPerson.ProductsPersonDao;
import com.yongjun.tdms.model.base.products.productsPerson.ProductsPerson;
import com.yongjun.tdms.service.base.products.productsPerson.ProductsPersonManager;

public class DefaultProductsPersonManager extends BaseManager implements ProductsPersonManager {
	private final ProductsPersonDao productsPersonDao;

	public DefaultProductsPersonManager(ProductsPersonDao productsPersonDao) {
		this.productsPersonDao = productsPersonDao;
	}

	public void deleteAllProductsPerson(List<ProductsPerson> list) {
		this.productsPersonDao.deleteAllProducts(list);
	}

	public void deleteProductsPerson(ProductsPerson productsPerson) {
		this.productsPersonDao.deleteProducts(productsPerson);
	}

	public List<ProductsPerson> loadAllProductsPerson(Long[] ids) {
		return this.productsPersonDao.loadAllProducts(ids);
	}

	public ProductsPerson loadProductsPerson(Long id) {
		return this.productsPersonDao.loadProductType(id);
	}

	public void storeProductsPerson(ProductsPerson productsPerson) {
		this.productsPersonDao.storeProducts(productsPerson);
	}

	public void disabledAllProductsPerson(Collection<ProductsPerson> productsPersons) {
		for (ProductsPerson productsPerson : productsPersons) {
			productsPerson.setDisabled(true);
			this.productsPersonDao.storeProducts(productsPerson);
		}
	}

	public void enabledAllProductsPerson(Collection<ProductsPerson> productsPersons) {
		for (ProductsPerson products : productsPersons) {
			products.setDisabled(false);
			this.productsPersonDao.storeProducts(products);
		}
	}

	public List<ProductsPerson> loadByKey(String keyName, Object keyValue) throws DaoException {
		return this.productsPersonDao.loadByKey(keyName, keyValue);
	}

}

package main.service.impl;

import org.hibernate.Session;

import main.dao.ProductsDao;
import main.pojo.Products;
import main.service.ProductsService;

public class ProductsServiceImpl implements ProductsService {
         
   private ProductsDao productsDao;

@Override
public Products getProductById(Long id) {
	return productsDao.getProductById(id);
}

@Override
public Session getSuperSession() {
	return productsDao.getSuperSession();
}

public ProductsDao getProductsDao() {
	return productsDao;
}

public void setProductsDao(ProductsDao productsDao) {
	this.productsDao = productsDao;
}

	
	
}

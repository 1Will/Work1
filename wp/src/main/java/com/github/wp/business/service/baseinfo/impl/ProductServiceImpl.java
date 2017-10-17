package com.github.wp.business.service.baseinfo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.wp.business.dao.baseinfo.ProductDao;
import com.github.wp.business.pojo.product.Product;
import com.github.wp.business.service.baseinfo.ProductService;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Override
	public Pager<Product> findPage(Product product, Pagination pagination) {
		
		return productDao.findPage(product, pagination);
	}

	@Override
	public void saveOrUpdate(Product p) {
		// TODO Auto-generated method stub
		productDao.saveOrUpdate(p);
	}

	@Override
	public Product getProductByid(long id) {
		// TODO Auto-generated method stub
		return productDao.getProductByid(id);
	}

	@Override
	public void deleteProduct(Long[] ids) {
		// TODO Auto-generated method stub
		productDao.deleteProduct(ids);
		
	}
	
}

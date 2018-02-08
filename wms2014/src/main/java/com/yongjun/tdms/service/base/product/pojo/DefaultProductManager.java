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
package com.yongjun.tdms.service.base.product.pojo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.base.product.ProductDao;
import com.yongjun.tdms.model.base.product.Product;
import com.yongjun.tdms.service.base.product.ProductManager;

/**
 * @author qs
 * @version $Id: $
 */
public class DefaultProductManager extends BaseManager implements ProductManager {

	private final ProductDao productDao;
	
	public DefaultProductManager(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	public Product loadProduct(Long productId) {
		return this.productDao.loadProduct(productId);
	}

	public List<Product> loadAllProducts(Long[] productIds) {
		return this.productDao.loadAllProducts(productIds);
	}

	public List loadAllProducts() {
		return this.productDao.loadAllProducts();
	}

	public void storeProduct(Product product) {
		this.productDao.storeProduct(product);
	}

	public void deleteProduct(Product product) {
		this.productDao.deleteProduct(product);
	}

	public void deleteAllProducts(Collection<Product> products) {
		this.productDao.deleteAllProducts(products);
	}

	public void disabledAllProducts(Collection<Product> products) {
		for (Product product : products) {
			product.setDisabled(true);
			this.productDao.storeProduct(product);
		}
	}
	
	public List createSelectProducts(String name) {
		List<Product> list = productDao.loadAllProducts();
		List<Product> endableList = new ArrayList<Product>();
		for(Product p : list){
			if(p.getDisabled()==false){
				endableList.add(p);
			}
		}
		Product product = new Product();
		product.setId(Long.valueOf(-1L));
		product.setName(name);
		endableList.add(0, product);
		return endableList;
	}

	public void enabledAllProducts(Collection<Product> Products) {
		for (Product product : Products) {
			product.setDisabled(false);
			this.productDao.storeProduct(product);
		}
		
	}

	public Product getProductByName(String name) {
		return this.productDao.getProductByName(name);
	}

	public Product getProductByProductNo(String productNo) {
		return this.productDao.getProductByProductNo(productNo);
	}

}

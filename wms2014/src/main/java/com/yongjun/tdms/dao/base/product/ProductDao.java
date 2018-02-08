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
package com.yongjun.tdms.dao.base.product;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.model.base.product.Product;

/**
 * @author qs
 * @version $Id: ProductDao.java 10412 2008-01-16 01:27:50Z qsun $
 */
public interface ProductDao {
	Product loadProduct(Long productId);
	
	List<Product> loadAllProducts(Long [] productIds);

	List<Product> loadAllProducts();
	
	void storeProduct(Product product);
	
	void deleteProduct(Product product);
	
	void deleteAllProducts(Collection<Product> products);
	
	//根据productNo获取产品编码
	Product getProductByProductNo(String productNo);
	//根绝name获取产品名称
	Product getProductByName(String name);
}

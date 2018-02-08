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
package com.yongjun.tdms.dao.prophase.supplier.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.prophase.supplier.SupplierProductDao;
import com.yongjun.tdms.model.prophase.supplier.SupplierProduct;

/**
 * @author qs
 * @version $Id: HibernateSupplierProduct.java 8392 2007-11-19 07:07:28Z zbzhang $
 */
public class HibernateSupplierProduct extends BaseHibernateDao implements SupplierProductDao {

	public List<SupplierProduct> loadAllProducts(Long[] productIds) {
		return this.loadAll(SupplierProduct.class, productIds);
	}

	public void deleteAllProducts(Collection<SupplierProduct> products) {
		this.deleteAll(products);
	}

	public SupplierProduct loadProduct(Long productId) {
		return this.load(SupplierProduct.class, productId);
	}

	public void storeProduct(SupplierProduct product) {
		this.store(product);
	}

}

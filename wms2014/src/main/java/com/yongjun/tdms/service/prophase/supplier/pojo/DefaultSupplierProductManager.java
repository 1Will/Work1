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
package com.yongjun.tdms.service.prophase.supplier.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.dao.prophase.supplier.SupplierProductDao;
import com.yongjun.tdms.model.prophase.supplier.SupplierProduct;
import com.yongjun.tdms.service.prophase.supplier.SupplierProductManager;

/**
 * @author qs
 * @version $Id: DefaultSupplierProductManager.java 8396 2007-11-19 07:08:17Z zbzhang $
 */
public class DefaultSupplierProductManager implements SupplierProductManager{
	
	private final SupplierProductDao supplierProductDao;
	
	public DefaultSupplierProductManager(SupplierProductDao supplierProductDao) {
		this.supplierProductDao = supplierProductDao;
	}
	
	public List<SupplierProduct> loadAllProducts(Long[] productIds) {
		return this.supplierProductDao.loadAllProducts(productIds);
	}

	public void deleteAllProducts(Collection<SupplierProduct> products) {
		this.supplierProductDao.deleteAllProducts(products);
	}

	public SupplierProduct loadProduct(Long productId) {
		return this.supplierProductDao.loadProduct(productId);
	}

	public void storeProduct(SupplierProduct product) {
		this.supplierProductDao.storeProduct(product);
	}

}

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

import java.util.List;

import com.yongjun.tdms.dao.prophase.supplier.SupplierDao;
import com.yongjun.tdms.dao.prophase.supplier.SupplierLevelHistoryDao;
import com.yongjun.tdms.model.prophase.supplier.Supplier;
import com.yongjun.tdms.model.prophase.supplier.SupplierLevelHistory;
import com.yongjun.tdms.service.prophase.supplier.SupplierLevelHistoryManager;

/**
 * @author qs
 * @version $Id: DefaultSupplierLevelHistoryManager.java 8042 2007-10-26
 *          05:39:43Z qsun $
 */
public class DefaultSupplierLevelHistoryManager implements
		SupplierLevelHistoryManager {

	private final SupplierLevelHistoryDao supplierLevelHistoryDao;

	private final SupplierDao supplierDao;

	public DefaultSupplierLevelHistoryManager(
			SupplierLevelHistoryDao supplierLevelHistoryDao,
			SupplierDao supplierDao) {
		this.supplierLevelHistoryDao = supplierLevelHistoryDao;
		this.supplierDao = supplierDao;
	}

	public void storeSupplierLevelHistory(Supplier supplier,
			SupplierLevelHistory supplierLevelHistory) {
		boolean isNew = supplierLevelHistory.isNew();
		if (isNew) {
			supplierLevelHistory.setInvalid(false);
		}
		
		supplierLevelHistory.setSupplier(supplier);
		supplierLevelHistoryDao.storeSupplierLevelHistory(supplierLevelHistory);
		supplierDao.storeSupplier(supplier);
	}

	public void storeSupplierLevelHistory(
			SupplierLevelHistory supplierLevelHistory) {
		supplierLevelHistory.setInvalid(true);
		supplierLevelHistoryDao.storeSupplierLevelHistory(supplierLevelHistory);

	}

	public SupplierLevelHistory loadSupplierLevelHistory(
			Long supplierLevelHistoryId) {
		return supplierLevelHistoryDao
				.loadSupplierLevelHistory(supplierLevelHistoryId);
	}

	public List<SupplierLevelHistory> loadAllSupplierLevelHistorys(
			Long[] supplierLevelHistoryIds) {
		return supplierLevelHistoryDao
				.loadAllSupplierLevelHistory(supplierLevelHistoryIds);
	}

}

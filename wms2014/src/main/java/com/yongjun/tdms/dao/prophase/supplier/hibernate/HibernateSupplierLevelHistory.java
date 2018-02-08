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

import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.prophase.supplier.SupplierLevelHistoryDao;
import com.yongjun.tdms.model.prophase.supplier.SupplierLevelHistory;

/**
 * @author qs
 * @version $Id: HibernateSupplierLevelHistory.java 8023 2007-10-25 09:44:37Z mwei $
 */
public class HibernateSupplierLevelHistory extends BaseHibernateDao implements
		SupplierLevelHistoryDao {

	public void storeSupplierLevelHistory(SupplierLevelHistory supplierLevelHistory) {
		this.store(supplierLevelHistory);
	}

	public List<SupplierLevelHistory> loadAllSupplierLevelHistory(Long[] supplierIds) {
		return this.loadAll(SupplierLevelHistory.class, supplierIds);
	}

	public SupplierLevelHistory loadSupplierLevelHistory(Long supplierIds) {
		return this.load(SupplierLevelHistory.class, supplierIds);
	}

}

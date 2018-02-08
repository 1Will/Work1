/*
 * Copyright (c) 2001-2010 YongJun Technology Pte.,Ltd. All Rights Reserved.
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
package com.yongjun.tdms.service.prophase.business.report.pojo;

import java.util.List;

import com.yongjun.tdms.dao.prophase.business.tooling.reportmanager.PurchaseCountBySupplierReportDao;
import com.yongjun.tdms.model.prophase.business.report.PurchaseCountBySupplierReport;
import com.yongjun.tdms.service.prophase.business.report.PurchaseCountBySupplierReportManager;

/**
 * <p>Title: DefaultPurchaseCountBySupplierReportManager
 * <p>Description: 供应商采购情况统计月报表业务访问实现类</p>
 * <p>Copyright: Copyright (c) 2009 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $
 */
public class DefaultPurchaseCountBySupplierReportManager implements
		PurchaseCountBySupplierReportManager {
	private final PurchaseCountBySupplierReportDao purchaseCountBySupplierReportDao;
	
	public DefaultPurchaseCountBySupplierReportManager(PurchaseCountBySupplierReportDao purchaseCountBySupplierReportDao){
		this.purchaseCountBySupplierReportDao = purchaseCountBySupplierReportDao;
	}
	public List<PurchaseCountBySupplierReport> loadDetailViewNumberSupplier(String[] array) {
		return this.purchaseCountBySupplierReportDao.loadDetailViewNumberSupplier(array);
	}

}

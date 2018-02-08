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
package com.yongjun.tdms.presentation.webwork.action.prophase.business.tooling.reportmanager;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.RequestUtils;

import com.yongjun.pluto.spring.controller.JasperReportsController;
import com.yongjun.tdms.model.prophase.business.report.PurchaseCountBySupplierReport;
import com.yongjun.tdms.service.prophase.business.report.PurchaseCountBySupplierReportManager;

/**
 * <p>Title: com.yongjun.tdms.presentation.webwork.action.prophase.business.tooling.reportmanager.ListSubscribeDetailMonthSupplierReportController.java</p>
 * <p>Description: 备件月度计划落实情况统计 按供应商</p>
 * <p>Copyright: Copyright (c) 2010 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * <p>@author zzb @yj-technology.com</p>
 * <p>@version $ Id:ListSubscribeDetailMonthReportController.java 2011-3-15 zzb $</p>
 */			 
public class ListSubscribeDetailMonthSupplierReportController extends JasperReportsController{
	
	private final PurchaseCountBySupplierReportManager purchaseCountBySupplierReportManager;
	
	public ListSubscribeDetailMonthSupplierReportController(PurchaseCountBySupplierReportManager purchaseCountBySupplierReportManager){
		this.purchaseCountBySupplierReportManager = purchaseCountBySupplierReportManager;
	}
	@Override
	protected Map getModel(HttpServletRequest httpservletrequest) throws Exception {

		String[] queryInfo = new String[] {
				RequestUtils.getStringParameter(httpservletrequest, "month",
						StringUtils.EMPTY).trim(),
						RequestUtils.getStringParameter(httpservletrequest, "supplier.name",
						StringUtils.EMPTY).trim()
				};
			
			List<PurchaseCountBySupplierReport> result  = new ArrayList<PurchaseCountBySupplierReport>();
		    result = this.purchaseCountBySupplierReportManager.loadDetailViewNumberSupplier(queryInfo); 
		    Map model = new HashMap();
		    model.put("reportData", result);
		
		return model;
	}
}


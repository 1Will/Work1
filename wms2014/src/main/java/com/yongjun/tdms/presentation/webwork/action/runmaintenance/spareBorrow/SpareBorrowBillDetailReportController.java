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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.spareBorrow;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.RequestUtils;

import com.yongjun.pluto.spring.controller.JasperReportsController;
import com.yongjun.tdms.model.runmaintenance.spareBorrow.SpareBorrow;
import com.yongjun.tdms.model.runmaintenance.spareBorrow.SpareBorrowDetail;
import com.yongjun.tdms.service.runmaintenance.spareBorrow.SpareBorrowManager;

/**
 * <p>Title:spareBorrowBillDetailReportController
 * <p>Description:</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author yli@yj-technology.com
 * @version $Id: spareBorrowBillDetailReportController.java 2008-11-28 14:15:18 yli$
 */
public class SpareBorrowBillDetailReportController extends
		JasperReportsController {
	private final SpareBorrowManager spareBorrowManager;
	private SpareBorrow spareBorrowBill;
	private Set<SpareBorrowDetail> details;
	public SpareBorrowBillDetailReportController(SpareBorrowManager spareBorrowManager){
		this.spareBorrowManager = spareBorrowManager;
	}
	@Override
	protected Map getModel(HttpServletRequest httpservletrequest)
			throws Exception {
		Map model = new HashMap();
		String spareBorrowBillId=RequestUtils.getStringParameter(httpservletrequest, "spareBorrowBillId");
		if(spareBorrowBillId!=null&&!spareBorrowBillId.equals("")){
			spareBorrowBill = spareBorrowManager.loadSpareBorrow(Long.valueOf(spareBorrowBillId));
			details = spareBorrowBill.getDetail();
		}
		model.put("reportData", details );
		return model;
	}

}

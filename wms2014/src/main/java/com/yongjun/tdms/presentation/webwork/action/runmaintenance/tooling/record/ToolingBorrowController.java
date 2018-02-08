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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.tooling.record;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.RequestUtils;

import com.yongjun.pluto.spring.controller.JasperReportsController;
import com.yongjun.tdms.model.runmaintenance.tooling.record.ToolingBorrowBill;
import com.yongjun.tdms.service.runmaintenance.tooling.record.ToolingBorrowBillManager;

/**
 * @author qs
 * @version $Id: ToolingBorrowController.java 10840 2008-02-01 01:58:31Z qsun $
 */
public class ToolingBorrowController extends JasperReportsController {

	private final ToolingBorrowBillManager toolingBorrowBillManager;

	private ToolingBorrowBill toolingBorrowBill;

	public ToolingBorrowController(
			ToolingBorrowBillManager toolingBorrowBillManager) {
		this.toolingBorrowBillManager = toolingBorrowBillManager;
	}

	@SuppressWarnings("unchecked")
	protected Map getModel(HttpServletRequest httpServletRequest)
			throws Exception {
		Map model = new HashMap();
		String borrowBillId = RequestUtils.getStringParameter(
				httpServletRequest, "toolingBorrowBillId");
		if (borrowBillId != null && !borrowBillId.equals("")) {
			this.toolingBorrowBill = this.toolingBorrowBillManager
					.loadToolingBorrowBill(Long.valueOf(borrowBillId));
		}
		model.put("reportData", new ToolingBorrowBill[] { toolingBorrowBill });
		return model;
	}

	public ToolingBorrowBill getToolingBorrowBill() {
		return toolingBorrowBill;
	}

	public void setToolingBorrowBill(ToolingBorrowBill toolingBorrowBill) {
		this.toolingBorrowBill = toolingBorrowBill;
	}

}

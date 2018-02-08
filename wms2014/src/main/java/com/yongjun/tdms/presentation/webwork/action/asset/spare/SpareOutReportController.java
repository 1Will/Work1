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
package com.yongjun.tdms.presentation.webwork.action.asset.spare;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.RequestUtils;

import com.yongjun.pluto.spring.controller.JasperReportsController;
import com.yongjun.tdms.model.asset.spare.SpareInOutBill;
import com.yongjun.tdms.service.asset.spare.SpareInOutBillManager;
/**
 * <p>Title: SpareOutReportController
 * <p>Description: 备件出库单报表访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: SpareOutReportController.java 9887 2007-12-28 03:11:36Z qsun $
 */
public class SpareOutReportController extends JasperReportsController {

	private final SpareInOutBillManager spareInOutBillManager;
	private SpareInOutBill spareOutBill;
	
	public SpareOutReportController(SpareInOutBillManager spareInOutBillManager) {
		this.spareInOutBillManager = spareInOutBillManager;
	}
	
	/**
	 * 根据请求中的备件出库单的ID,获取出库单对象，并且放入model中
	 * @param request http请求，包含备件出库单的ID
	 * @return Map 包含出库单对象的键值对
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected Map getModel(HttpServletRequest request)
			throws Exception {
		Map model = new HashMap();
		String spareOutBillId = RequestUtils.getStringParameter(request,"spareOutBillId");
		if (spareOutBillId != null && !spareOutBillId.equals("")) {
			this.spareOutBill = this.spareInOutBillManager.loadSpareInOutBill(Long.valueOf(spareOutBillId));
		}
		model.put("reportData", new SpareInOutBill[] { spareOutBill });
		return model;
	}

	public SpareInOutBill getSpareOutBill() {
		return spareOutBill;
	}

	public void setSpareOutBill(SpareInOutBill spareOutBill) {
		this.spareOutBill = spareOutBill;
	}


}

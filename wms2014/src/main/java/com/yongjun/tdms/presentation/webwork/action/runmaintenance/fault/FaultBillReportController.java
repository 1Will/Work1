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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.fault;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.RequestUtils;

import com.yongjun.pluto.spring.controller.JasperReportsController;
import com.yongjun.tdms.model.runmaintenance.alteration.Alteration;
import com.yongjun.tdms.model.runmaintenance.fault.FaultBill;
import com.yongjun.tdms.model.runmaintenance.fault.FaultRepair;
import com.yongjun.tdms.model.runmaintenance.tooling.record.ToolingChangeBill;
import com.yongjun.tdms.service.runmaintenance.fault.FaultBillManager;
import com.yongjun.tdms.service.runmaintenance.migrate.MigrateManager;

/**
 * <p>Title: EditDiscardAction
 * <p>Description: 设备故障记录打印控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author smzhu@yj-technology.com
 * @version $Id: ToolingChangeBillReportController.java 9353 2008-4-14 10:36:28Z smzhu $
 */
public class FaultBillReportController extends JasperReportsController{
	private final FaultBillManager faultBillManager;
	private FaultBill faultBill;
	HttpServletRequest request;
	
	public FaultBillReportController(FaultBillManager faultBillManager){
		this.faultBillManager=faultBillManager;		
	}
	
	protected Map getModel(HttpServletRequest httpServletRequest) throws Exception {
		Map model = new HashMap();
		String faultBillId = RequestUtils.getStringParameter(httpServletRequest, "faultBillId");
		if(faultBillId != null && !faultBillId.equals("")){
			this.faultBill = this.faultBillManager.loadFaultBill(Long.parseLong(faultBillId));
		}
		model.put("reportData",new FaultBill[] { faultBill });
		return model;
	}
	
	protected boolean hasId(String idName)
    {
        return !StringUtils.isEmpty(request.getParameter(idName));
    }
}

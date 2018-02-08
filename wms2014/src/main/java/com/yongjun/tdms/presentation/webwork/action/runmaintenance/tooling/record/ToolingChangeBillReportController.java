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

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.RequestUtils;

import com.yongjun.pluto.spring.controller.JasperReportsController;
import com.yongjun.tdms.model.runmaintenance.tooling.record.ToolingChangeBill;
import com.yongjun.tdms.service.runmaintenance.tooling.record.ToolingChangeBillManager;

/**
 * <p>Title: EditDiscardAction
 * <p>Description: 变更单打印控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author smzhu@yj-technology.com
 * @version $Id: ToolingChangeBillReportController.java 9353 2008-4-10 10:40:28Z smzhu $
 */

public class ToolingChangeBillReportController extends JasperReportsController{
	private final ToolingChangeBillManager toolingChangeBillManager;
	private ToolingChangeBill toolingChangeBill;
	HttpServletRequest request;
	
	public ToolingChangeBillReportController(ToolingChangeBillManager toolingChangeBillManager){
		this.toolingChangeBillManager=toolingChangeBillManager;		
	}
	
	protected Map getModel(HttpServletRequest httpServletRequest) throws Exception {
		Map model = new HashMap();
		String toolingChangeBillId = RequestUtils.getStringParameter(httpServletRequest, "toolingChangeBillId");
		if(toolingChangeBillId != null && !toolingChangeBillId.equals("")){
			this.toolingChangeBill = this.toolingChangeBillManager.loadToolingChangeBill(Long.parseLong(toolingChangeBillId));
		}
		model.put("reportData", new ToolingChangeBill[] { toolingChangeBill });
		return model;
	}
	
	protected boolean hasId(String idName)
    {
        return !StringUtils.isEmpty(request.getParameter(idName));
    }
}

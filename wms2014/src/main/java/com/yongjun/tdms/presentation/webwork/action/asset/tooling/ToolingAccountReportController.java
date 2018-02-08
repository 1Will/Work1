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
package com.yongjun.tdms.presentation.webwork.action.asset.tooling;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.RequestUtils;

import com.yongjun.pluto.spring.controller.JasperReportsController;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.asset.device.ToolingAccount;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.asset.device.ToolingAccountManager;

/**
 * <p>Title: EditDiscardAction
 * <p>Description: 工装台账维护打印控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author smzhu@yj-technology.com
 * @version $Id: ToolingAccountReportController.java 9353 2008-4-10 21:45:28Z smzhu $
 */

public class ToolingAccountReportController extends JasperReportsController{

	private final ToolingAccountManager toolingAccountManager;
	private List<ToolingAccount> toolingAccounts;
	HttpServletRequest request;
	
	public ToolingAccountReportController(ToolingAccountManager toolingAccountManager){
		this.toolingAccountManager=toolingAccountManager;
	}
	
	protected Map getModel(HttpServletRequest httpServletRequest) throws Exception {
		Map model = new HashMap();
		String toolingId = RequestUtils.getStringParameter(httpServletRequest, "toolingId");
		if(toolingId != null && !toolingId.equals("")){
			this.toolingAccounts = this.toolingAccountManager.loadAllToolingAccountByToolingID(Long.parseLong(toolingId));
		}
		model.put("reportData", toolingAccounts);
		return model;
	}
	
	protected boolean hasId(String idName)
    {
        return !StringUtils.isEmpty(request.getParameter(idName));
    }
}

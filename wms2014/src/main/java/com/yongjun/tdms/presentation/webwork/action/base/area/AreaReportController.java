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
package com.yongjun.tdms.presentation.webwork.action.base.area;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.spring.controller.JasperReportsController;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointRule;
import com.yongjun.tdms.service.runmaintenance.checkpoint.CheckPointRuleManager;
import javax.servlet.http.HttpServletRequest;

import java.util.*;

/**
 * @author qs
 * @version $Id: AreaReportController.java 8881 2007-12-02 03:05:28Z qsun $
 */
public class AreaReportController extends JasperReportsController{
	private final CheckPointRuleManager checkPointRuleManager;
	private CheckPointRule rule;
	HttpServletRequest request;

	public AreaReportController(CheckPointRuleManager checkPointRuleManager){
		this.checkPointRuleManager = checkPointRuleManager;
	}

	@SuppressWarnings("unchecked")
	protected Map getModel(HttpServletRequest httpServletRequest) throws Exception {
		Map model = new HashMap();
		this.rule=this.checkPointRuleManager.loadRule(Long.parseLong("13"));
		model.put("reportData", new CheckPointRule[] { rule });
		return model;
	}
	
	protected boolean hasId(String idName)
    {
        return !StringUtils.isEmpty(request.getParameter(idName));
    }
}
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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.device.lubrication;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.RequestUtils;

import com.yongjun.pluto.spring.controller.JasperReportsController;
import com.yongjun.tdms.model.runmaintenance.lubrication.LubricationPlan;
import com.yongjun.tdms.model.runmaintenance.lubrication.LubricationPlanDetail;
import com.yongjun.tdms.model.runmaintenance.migrate.MigrateDetail;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairModel;
import com.yongjun.tdms.service.runmaintenance.lubrication.LubricationPlanManager;

/**
 * <p>Title: EditDiscardAction
 * <p>Description: 润滑计划实施明细打印控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author smzhu@yj-technology.com
 * @version $Id: LubricationPlanDetailReportController.java 9353 2008-4-14 16:27:28Z smzhu $
 */
public class LubricationPlanDetailReportController extends JasperReportsController{

	private final LubricationPlanManager lubricationPlanManager;
	private LubricationPlan lubrication;
	HttpServletRequest request;
	private Set<LubricationPlanDetail> planDetails = new HashSet();
	
	public LubricationPlanDetailReportController(LubricationPlanManager lubricationPlanManager){
		this.lubricationPlanManager = lubricationPlanManager;
	}
	protected Map getModel(HttpServletRequest httpServletRequest) throws Exception {
		Map model = new HashMap();
		String lubricationId = RequestUtils.getStringParameter(httpServletRequest, "lubricationId");
		if(lubricationId != null && !lubricationId.equals("")){
			this.lubrication = this.lubricationPlanManager.loadLubricationPlan(Long.parseLong(lubricationId));
			
			if(lubrication.getPlanProcFlag().equals(PreRepairModel.PLAN)){
				this.planDetails = this.lubrication.getPlanDetails();
			}else{
				this.planDetails = this.lubrication.getProcDetails();
			}
		}
		model.put("reportData", planDetails);
		return model;
	}
	
	protected boolean hasId(String idName)
    {
        return !StringUtils.isEmpty(request.getParameter(idName));
    }
	
}

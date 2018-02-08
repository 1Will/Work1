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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.alteration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.RequestUtils;

import com.yongjun.pluto.spring.controller.JasperReportsController;
import com.yongjun.tdms.model.asset.device.DeviceFinanceInfo;
import com.yongjun.tdms.model.runmaintenance.alteration.Alteration;
import com.yongjun.tdms.service.runmaintenance.alteration.AlterationManager;


/**
 * <p>Title: EditDiscardAction
 * <p>Description: 设备封存单打印控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author smzhu@yj-technology.com
 * @version $Id: ToolingSealedReportController.java 9353 2008-4-11 12:53:28Z smzhu $
 */
public class SealedReportController extends JasperReportsController{
	
	private final AlterationManager alterationManager;
	private Alteration alteration;	
	HttpServletRequest request;
	
	
	public SealedReportController(AlterationManager alterationManager){
		this.alterationManager=alterationManager;		
	}
	
	protected Map getModel(HttpServletRequest httpServletRequest) throws Exception {
		Map model = new HashMap();
		String alterationId = RequestUtils.getStringParameter(httpServletRequest, "alterationId");
		if(alterationId != null && !alterationId.equals("")){
			this.alteration = this.alterationManager.loadAlteration(Long.parseLong(alterationId));
			Double origPrice =0.0;
			for (DeviceFinanceInfo info : this.alteration.getAsset().getFinanceInfo()){
				origPrice = info.getOrigPrice();
			}
			this.alteration.setOrigPrice(origPrice);
		}
		model.put("reportData", new Alteration[] { alteration });
		return model;
	}
	
	protected boolean hasId(String idName)
    {
        return !StringUtils.isEmpty(request.getParameter(idName));
    }
}

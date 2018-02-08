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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.discard;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.RequestUtils;

import com.yongjun.pluto.spring.controller.JasperReportsController;
import com.yongjun.tdms.model.runmaintenance.discard.Discard;
import com.yongjun.tdms.service.runmaintenance.discard.DiscardManager;

import javax.servlet.http.HttpServletRequest;

import java.util.*;

/**
 * <p>Title: EditDiscardAction
 * <p>Description: 报废单打印控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: DiscardReportController.java 9353 2007-12-14 07:38:33Z wzou $
 */
public class DiscardReportController extends JasperReportsController{
	private final DiscardManager discardManager;
	private Discard toolingDiscard;
	HttpServletRequest request;
	
	public DiscardReportController(DiscardManager discardManager){
		this.discardManager = discardManager;
	}
	
	@SuppressWarnings("unchecked")
	protected Map getModel(HttpServletRequest httpServletRequest) throws Exception {
		Map model = new HashMap();
		String discardId = RequestUtils.getStringParameter(httpServletRequest, "discardId");
		 if(discardId != null && !discardId.equals("")){
			this.toolingDiscard = this.discardManager.loadDiscard(Long.parseLong(discardId));
		}
		model.put("reportData", new Discard[] { toolingDiscard });
		return model;
	}
	
	protected boolean hasId(String idName)
    {
        return !StringUtils.isEmpty(request.getParameter(idName));
    }
}
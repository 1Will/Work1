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
package com.yongjun.tdms.presentation.webwork.action.prophase.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.RequestUtils;

import com.yongjun.pluto.spring.controller.JasperReportsController;
import com.yongjun.tdms.model.prophase.business.AcceptBillDetailView;
import com.yongjun.tdms.service.prophase.business.AcceptBillDetailManager;

/**
 * <p>Title:ListToolingAcceptBillDetailController
 * <p>Description:</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author yli@yj-technology.com
 * @version $Id: ListToolingAcceptBillDetailController.java 2008-12-16 10:32:21 yli$
 */
public class ListToolingAcceptBillDetailController extends
		JasperReportsController {
	private List<AcceptBillDetailView> details;
	private final AcceptBillDetailManager acceptBillDetailManager;
	public ListToolingAcceptBillDetailController(AcceptBillDetailManager acceptBillDetailManager){
		this.acceptBillDetailManager = acceptBillDetailManager;
	}
	@Override
	protected Map getModel(HttpServletRequest httpservletrequest)
			throws Exception {
		Map model = new HashMap();
		String acceptId=RequestUtils.getStringParameter(httpservletrequest, "acceptid");
		System.out.println(acceptId+"------");
		if(null!=acceptId && !acceptId.equals("")){
			details = acceptBillDetailManager.loadAcceptBillDetailView(Long.valueOf(acceptId));
			System.out.println("====");
		}
		model.put("reportData",details);
		return model;
	}

}

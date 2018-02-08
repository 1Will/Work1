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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.RequestUtils;

import com.yongjun.pluto.spring.controller.JasperReportsController;
import com.yongjun.tdms.model.prophase.business.SubscribeDetailView;
import com.yongjun.tdms.service.prophase.business.SubscribeManager;

/**
 * <p>Title:ListToolingSubscribeDetailController
 * <p>Description:</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author yli@yj-technology.com
 * @version $Id: ListToolingSubscribeDetailController.java 2008-12-15 13:57:54 yli$
 */
public class ListToolingSubscribeDetailController extends
		JasperReportsController {
	private List<SubscribeDetailView> details;
	private final SubscribeManager subscribeManager;
	public ListToolingSubscribeDetailController(SubscribeManager subscribeManager){
		this.subscribeManager = subscribeManager;
	}
	/**
	 * 根据请求中的工装采购单的ID,获取采购单对象，并且放入model中
	 * @param request http请求，包含采购单的ID
	 * @return Map 包含采购单对象的键值对
	 */
	@Override
	protected Map getModel(HttpServletRequest httpservletrequest)
			throws Exception {
		Map model = new HashMap();
		String subscribeId=RequestUtils.getStringParameter(httpservletrequest, "subscribeid");
		List<Long> ids= new ArrayList<Long>();
		ids.add(Long.parseLong(subscribeId));
		if(subscribeId!=null&&!subscribeId.equals("")){
			details = subscribeManager.loadSubscribeDetailView(ids,null);
		}
		model.put("reportData",details);
		return model;
	}

}

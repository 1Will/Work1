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
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.RequestUtils;

import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.spring.controller.JasperReportsController;
import com.yongjun.pluto.spring.util.SubReportHelper;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;

/**
 * <p>Title: ListDeviceReportsController
 * <p>Description: 工装列表的报表访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author qs@yj-technology.com
 * @version $Id: ListToolingReportController.java 9887 2007-12-28 03:11:36Z qsun $
 */
public class ListToolingReportController extends JasperReportsController {
	private final DeviceCardManager deviceCardManager;
	private final UserManager userManager;
	private List result;
	private SubReportHelper subReportHelper;
	
	public ListToolingReportController(DeviceCardManager deviceCardManager,
			UserManager userManager) {
		this.deviceCardManager = deviceCardManager;
		this.userManager = userManager;
	}

	@Override
	@SuppressWarnings("unchecked")
	protected Map getModel(HttpServletRequest request)
			throws Exception {
		String [] searchOptionArray = RequestUtils.getStringParameter(request, "searchOption", StringUtils.EMPTY).split(",");
		Map searchOptionMap = new HashMap();
		for (int i=0; i<searchOptionArray.length; i=i+2) {
			if (!searchOptionArray[i+1].equals("null")) {
				searchOptionMap.put(searchOptionArray[i],searchOptionArray[i+1].trim());
			}
		}
		searchOptionMap.put("orgId",this.userManager.getUser().getOrganization().getId());
		result = this.deviceCardManager.loadAllMatchOptionToolings(searchOptionMap);
		Map model = new HashMap();
		subReportHelper=new SubReportHelper();
		subReportHelper.setSubReportDatas(result);
		model.put("subReportData",new SubReportHelper[]{subReportHelper});
		return model;
	}

}

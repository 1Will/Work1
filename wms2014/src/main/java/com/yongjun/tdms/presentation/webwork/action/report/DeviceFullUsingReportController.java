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
package com.yongjun.tdms.presentation.webwork.action.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.RequestUtils;

import com.yongjun.pluto.spring.controller.JasperReportsController;
import com.yongjun.pluto.spring.util.SubReportHelper;
import com.yongjun.tdms.model.report.DeviceFullUsing;
import com.yongjun.tdms.service.asset.device.DeviceFullUsingManager;

/**
 * <p>Title: DeviceFullUsingReportController
 * <p>Description: 主要生产设备完好利用情况打印控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: DeviceFullUsingReportController.java 10074 2008-01-03 09:11:38Z wzou $
 */

public class DeviceFullUsingReportController extends JasperReportsController {
	private final DeviceFullUsingManager deviceFullUsingManager;
	List<DeviceFullUsing> deviceFullUsingList = new ArrayList();
	List result;
	
	public DeviceFullUsingReportController (DeviceFullUsingManager deviceFullUsingManager) {
		this.deviceFullUsingManager = deviceFullUsingManager;
	}
	
	@Override
	protected Map getModel(HttpServletRequest httpservletrequest) throws Exception {
		String month = (RequestUtils.getStringParameter(httpservletrequest,
				"month", StringUtils.EMPTY)).trim();
		result = deviceFullUsingManager.Query(month);
		Map model = new HashMap();
		model.put("reportData",  result );
		return model;
	}
}

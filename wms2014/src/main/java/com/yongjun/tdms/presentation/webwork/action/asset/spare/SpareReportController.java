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
package com.yongjun.tdms.presentation.webwork.action.asset.spare;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.RequestUtils;

import com.yongjun.pluto.spring.controller.JasperReportsController;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.service.asset.spare.SpareManager;

/**
 * <p>Title: SpareReportController
 * <p>Description: 备件报表打印控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: SpareReportController.java 10074 2008-01-03 09:11:38Z wzou $
 */
public class SpareReportController extends JasperReportsController {
	private final SpareManager spareManager;

	private Spare spare;

	public Spare getSpare() {
		return spare;
	}

	public void setSpare(Spare spare) {
		this.spare = spare;
	}

	public SpareManager getSpareManager() {
		return spareManager;
	}

	public SpareReportController(SpareManager spareManager) {
		this.spareManager = spareManager;
	}
	
	/**
	 * 通过spareId获取该备件model.
	 * @return Map类型的model对象
	 */
	@SuppressWarnings("unchecked")
	protected Map getModel(HttpServletRequest httpServletRequest)
			throws Exception {
		Map model = new HashMap();
		String spareId = RequestUtils.getStringParameter(httpServletRequest,
				"spareId");
		if (spareId != null && !spareId.equals("")) {
			this.spare = this.spareManager.loasSpare(Long.parseLong(spareId));
		}
		model.put("reportData", new Spare[] { spare });
		return model;
	}

}

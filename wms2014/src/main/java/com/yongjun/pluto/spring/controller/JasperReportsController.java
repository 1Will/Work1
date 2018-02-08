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
package com.yongjun.pluto.spring.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import com.yongjun.pluto.spring.controller.UrlPathController;

/**
 * @author qs
 * @version $Id: JasperReportsController.java 8881 2007-12-02 03:05:28Z qsun $
 */
public abstract class JasperReportsController extends UrlPathController {

	public JasperReportsController() {
	}

	@SuppressWarnings("unchecked")
	public ModelAndView handleRequest(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws Exception {
		Map model = getModel(httpServletRequest);
		String uri = urlPathHelper.getPathWithinApplication(httpServletRequest);
		model.put("format", StringUtils.substringAfterLast(uri, "."));
		return new ModelAndView(StringUtils.substringBeforeLast(uri, "."),
				model);
	}

	protected abstract Map getModel(HttpServletRequest httpservletrequest)
			throws Exception;
}

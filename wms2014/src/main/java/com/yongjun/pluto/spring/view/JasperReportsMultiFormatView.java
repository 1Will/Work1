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
package com.yongjun.pluto.spring.view;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.util.UrlPathHelper;
import org.springframework.web.util.WebUtils;

/**
 * @author qs
 * @version $Id: JasperReportsMultiFormatView.java 9931 2007-12-29 02:26:21Z wzou $
 */
public class JasperReportsMultiFormatView
		extends
		org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView {

	public JasperReportsMultiFormatView() {
	}

	@SuppressWarnings("unchecked")
	protected void renderMergedOutputModel(Map map,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws Exception {
		Map exporterParameters = getExporterParameters();
		Map imagesMap = new HashMap();
		WebUtils.setSessionAttribute(httpServletRequest, "IMAGES_MAP",
				imagesMap);
		exporterParameters
				.put(
						"net.sf.jasperreports.engine.export.JRHtmlExporterParameter.IMAGES_MAP",
						imagesMap);
		String imageUri = "/images/report/";
		String contextPath = urlPathHelper.getContextPath(httpServletRequest);
		if (contextPath != null)
			imageUri = (new StringBuilder()).append(contextPath).append(
					imageUri).toString();
		exporterParameters
				.put(
						"net.sf.jasperreports.engine.export.JRHtmlExporterParameter.IMAGES_URI",
						imageUri);
		super.renderMergedOutputModel(map, httpServletRequest,
				httpServletResponse);
	}

	public static final String IMAGE_URI = "/images/report/";

	public static final UrlPathHelper urlPathHelper = new UrlPathHelper();

	public static final String IMAGE_URI_KEY = "net.sf.jasperreports.engine.export.JRHtmlExporterParameter.IMAGES_URI";

	public static final String IMAGE_MAP_KEY = "net.sf.jasperreports.engine.export.JRHtmlExporterParameter.IMAGES_MAP";

}
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

import java.util.Map;
import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.jasperreports.AbstractJasperReportsView;

/**
 * @author qs
 * @version $Id: JasperReportsViewResolver.java 9931 2007-12-29 02:26:21Z wzou $
 */
public class JasperReportsViewResolver extends UrlBasedViewResolver {

	public JasperReportsViewResolver() {
	}

	protected AbstractUrlBasedView buildView(String viewName) throws Exception {
		AbstractJasperReportsView view = (AbstractJasperReportsView) super
				.buildView(viewName);
		view.setExporterParameters(exporterParameters);
		return view;
	}

	public void setExporterParameters(Map exporterParameters) {
		this.exporterParameters = exporterParameters;
	}

	private Map exporterParameters;
}
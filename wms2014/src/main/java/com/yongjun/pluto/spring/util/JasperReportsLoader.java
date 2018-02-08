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
package com.yongjun.pluto.spring.util;

import java.io.IOException;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRCompiler;
import net.sf.jasperreports.engine.design.JRDefaultCompiler;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.springframework.context.ApplicationContextException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * @author qs
 * @version $Id: JasperReportsLoader.java 9931 2007-12-29 02:26:21Z wzou $
 */
public class JasperReportsLoader {

	protected JasperReportsLoader() {
	}

	public static JasperReport load(String path) {
		try {
			net.sf.jasperreports.engine.design.JasperDesign design;
			Resource resource = new ClassPathResource(path);
			design = JRXmlLoader.load(resource.getInputStream());
			return jrCompiler.compileReport(design);
		} catch (IOException ex) {
			throw new ApplicationContextException((new StringBuilder()).append(
					"Could not load JasperReports report for URL [").append(
					path).append("]").toString(), ex);
		} catch (Exception ex) {
			throw new ApplicationContextException((new StringBuilder()).append(
					"Could not parse JasperReports report for URL [").append(
					path).append("]").toString(), ex);
		}
	}

	private static final JRCompiler jrCompiler = JRDefaultCompiler
			.getInstance();

}
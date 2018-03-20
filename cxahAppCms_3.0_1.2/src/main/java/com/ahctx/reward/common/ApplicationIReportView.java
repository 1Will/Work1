package com.ahctx.reward.common;

import java.util.Map;
import java.util.Properties;

import org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 * SpringMVC + IReport整合 视图处理扩展
 * @Author wangping
 * @Create 2016-8-20 20:38:18
 */
public class ApplicationIReportView extends JasperReportsMultiFormatView {
	private JasperReport jasperReport;
	
	public ApplicationIReportView() {
		super();
	}

	protected JasperPrint fillReport(Map<String, Object> model) throws Exception {
		if (model.containsKey("Content-Disposition")) {
			Properties mappings = new Properties();
			mappings.put(HEADER_CONTENT_DISPOSITION, model.get("Content-Disposition"));
			setHeaders(mappings);
		}
		if (model.containsKey("url")) {
			setUrl(String.valueOf(model.get("url")));
			this.jasperReport = loadReport();
		}
		return super.fillReport(model);
	}
	
	protected JasperReport getReport() {
		return this.jasperReport;
	}
}

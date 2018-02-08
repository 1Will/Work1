/*
 * YONGJUN-TEACHNOLOGY
 */
package com.yongjun.tdms.presentation.webwork.action.asset.spare.inAndOutAndStoreReport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.RequestUtils;

import com.yongjun.pluto.spring.controller.JasperReportsController;
import com.yongjun.tdms.dao.asset.spare.spareInAndOut.SpareInAndOutAndStoreReportMonthViewDao;

/**
 * @author yli-JohnSon
 * @Date 2009-4-21
 */
public class ListSpareInAndOutMonthReportController extends
		JasperReportsController {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yongjun.pluto.spring.controller.JasperReportsController#getModel(javax.servlet.http.HttpServletRequest)
	 */
	private final SpareInAndOutAndStoreReportMonthViewDao spareInAndOutAndStoreReportMonthViewDao;

	private List result;

	public ListSpareInAndOutMonthReportController(
			SpareInAndOutAndStoreReportMonthViewDao spareInAndOutAndStoreReportMonthViewDao) {
		this.spareInAndOutAndStoreReportMonthViewDao = spareInAndOutAndStoreReportMonthViewDao;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	protected Map getModel(HttpServletRequest httpservletrequest)
			throws Exception {
		
		String spareNo = "";  // 备件编号
		String spareName = "";	// 备件名称
		String modelSpecs = "";  //型号
		String warehouse = "";   //仓库
		String regional = "";   //库区
		String locationCode = "";  //库位
		String month = "";  // 月份
		String toolingDevFlag = "";// 类型
		String onlyCheck = ""; //仅发生额
		// 备件编号
		if (!RequestUtils.getStringParameter(httpservletrequest, "spareNo",
				StringUtils.EMPTY).trim().equals("")) {
			spareNo = RequestUtils.getStringParameter(httpservletrequest,
					"spareNo", StringUtils.EMPTY).trim();
		}
		// 备件名称
		if (!RequestUtils.getStringParameter(httpservletrequest, "spareName",
				StringUtils.EMPTY).trim().equals("")) {
			spareName = RequestUtils.getStringParameter(httpservletrequest,
					"spareName", StringUtils.EMPTY).trim();
		}
		//型号
		if (!RequestUtils.getStringParameter(httpservletrequest, "modelSpecs",
				StringUtils.EMPTY).trim().equals("")) {
			modelSpecs = RequestUtils.getStringParameter(httpservletrequest,
					"modelSpecs", StringUtils.EMPTY).trim();
		}
		//仓库
		if (!RequestUtils.getStringParameter(httpservletrequest, "warehouse",
				StringUtils.EMPTY).trim().equals("")) {
			warehouse = RequestUtils.getStringParameter(httpservletrequest,
					"warehouse", StringUtils.EMPTY).trim();
		}
		//库区
		if (!RequestUtils.getStringParameter(httpservletrequest, "regional",
				StringUtils.EMPTY).trim().equals("")) {
			regional = RequestUtils.getStringParameter(httpservletrequest,
					"regional", StringUtils.EMPTY).trim();
		}
		//库位
		if (!RequestUtils.getStringParameter(httpservletrequest, "locationCode",
				StringUtils.EMPTY).trim().equals("")) {
			locationCode = RequestUtils.getStringParameter(httpservletrequest,
					"locationCode", StringUtils.EMPTY).trim();
		}
		// 月份
		if (!RequestUtils.getStringParameter(httpservletrequest, "month",
				StringUtils.EMPTY).trim().equals("")) {
			month = RequestUtils.getStringParameter(httpservletrequest,
					"month", StringUtils.EMPTY).trim();

		}
		// 备件类别
		if (!RequestUtils.getStringParameter(httpservletrequest,
				"toolingDevFlag", StringUtils.EMPTY).trim().equals("")) {
			if (RequestUtils.getStringParameter(httpservletrequest,
					"toolingDevFlag", StringUtils.EMPTY).trim().equals(
					"TOOLING")) {
				toolingDevFlag = "工装";
			} else if (RequestUtils.getStringParameter(httpservletrequest,
					"toolingDevFlag", StringUtils.EMPTY).trim()
					.equals("DEVICE")) {
				toolingDevFlag = "设备";
			}
		}
		// 仅显示发生额
		if (!RequestUtils.getStringParameter(httpservletrequest, "onlyCheck",
				StringUtils.EMPTY).trim().equals("")) {
			onlyCheck = RequestUtils.getStringParameter(httpservletrequest,
					"onlyCheck", StringUtils.EMPTY).trim();

		}
		String[] queryInfo = new String[] { spareNo, spareName,modelSpecs,warehouse,regional,locationCode,month,
				toolingDevFlag,onlyCheck };
		result = spareInAndOutAndStoreReportMonthViewDao
				.getSpareInAndOutAndStoreMonthViewCollention(queryInfo);

		Map model = new HashMap();
		model.put("reportData", result);
		return model;
	}

}

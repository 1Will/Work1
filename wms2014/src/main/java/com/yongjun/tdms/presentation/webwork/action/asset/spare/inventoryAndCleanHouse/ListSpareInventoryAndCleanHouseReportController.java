package com.yongjun.tdms.presentation.webwork.action.asset.spare.inventoryAndCleanHouse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.RequestUtils;

import com.yongjun.pluto.spring.controller.JasperReportsController;
import com.yongjun.tdms.dao.asset.spare.inventoryAndCleanHouse.SpareInventoryAndCleanHouseReportViewDao;

/**
 * @author chengding
 * @Date 04/22/2009
 */
public class ListSpareInventoryAndCleanHouseReportController extends
		JasperReportsController {

	private final SpareInventoryAndCleanHouseReportViewDao spareInventoryAndCleanHouseReportViewDao;

	private List result;

	public ListSpareInventoryAndCleanHouseReportController(
			SpareInventoryAndCleanHouseReportViewDao spareInventoryAndCleanHouseReportViewDao) {
		this.spareInventoryAndCleanHouseReportViewDao = spareInventoryAndCleanHouseReportViewDao;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Map getModel(HttpServletRequest httpservletrequest)
			throws Exception {
		// 备件编号
		String spareNo = "";
		// 备件名称
		String spareName = "";
		// 库位号
		String locationCode = "";
		// 型号
		String modelSpecs = "";		
		// 部门
		String department = "";
		// 月份
		String month = "";
		// 备件类型
		String toolingDevFlag = "";
        //仅显示发生额
		String onlyCheck = "";
		
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
		
		// 库位号
		if (!RequestUtils.getStringParameter(httpservletrequest,
				"locationCode", StringUtils.EMPTY).trim().equals("")) {
			locationCode = RequestUtils.getStringParameter(httpservletrequest,
					"locationCode", StringUtils.EMPTY).trim();
		}
		
		//型号
		if (!RequestUtils.getStringParameter(httpservletrequest,
				"modelSpecs", StringUtils.EMPTY).trim().equals("")) {
			modelSpecs = RequestUtils.getStringParameter(httpservletrequest,
					"modelSpecs", StringUtils.EMPTY).trim();
		}
		
		// 月份
		if (!RequestUtils.getStringParameter(httpservletrequest, "month",
				StringUtils.EMPTY).trim().equals("")) {
			month = RequestUtils.getStringParameter(httpservletrequest,
					"month", StringUtils.EMPTY).trim();
		}
		
		// 部门
		if (!RequestUtils.getStringParameter(httpservletrequest,
				"department.id", StringUtils.EMPTY).trim().equals("")) {
			department = RequestUtils.getStringParameter(httpservletrequest,
					"department.id", StringUtils.EMPTY).trim();
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
		
		String[] queryInfo = new String[] { spareNo, spareName, locationCode,
				month, department, toolingDevFlag,modelSpecs,onlyCheck };
		result = this.spareInventoryAndCleanHouseReportViewDao
				.getSpareInventoryAndCleanHouseViewCollention(queryInfo);
		Map model = new HashMap();
		model.put("reportData", result);
		return model;
	}

}

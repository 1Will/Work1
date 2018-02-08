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
package com.yongjun.tdms.presentation.webwork.action.asset.spare.outWareHouse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.RequestUtils;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.spring.controller.JasperReportsController;
import com.yongjun.tdms.service.asset.spare.outWareHouse.SpareOutBillManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;

/**
 * <p>Title:ListAllSpareOutBillReportController
 * <p>Description:</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author yli@yj-technology.com
 * @version $Id: ListAllSpareOutBillReportController.java 2008-11-25 17:45:13 yli$
 */
public class ListAllSpareOutBillReportController extends
		JasperReportsController {
	private final SpareOutBillManager spareOutBillManager;
	private final DepartmentManager departmentManager;
	private Department dept;
	List result;
	public ListAllSpareOutBillReportController(SpareOutBillManager spareOutBillManager,DepartmentManager departmentManager){
		this.spareOutBillManager = spareOutBillManager;
		this.departmentManager = departmentManager;
	}
	@Override
	protected Map getModel(HttpServletRequest httpservletrequest)
			throws Exception {
		String spareOutBillNo="";
		String spareOutBillName="";
		String deptId="";
		String borrower="";
		String outPeople="";
		String warehouseId = "";
		String inWarehouseId = "";
		String oldSpare ="";
		String outType ="";
		//根据出库单号
		if(!RequestUtils.getStringParameter(httpservletrequest,"spareOutBillNo",StringUtils.EMPTY).trim().equals("")){
			spareOutBillNo = RequestUtils.getStringParameter(httpservletrequest,"spareOutBillNo",StringUtils.EMPTY).trim();
		}
		//根据出库单名称
		if(!RequestUtils.getStringParameter(httpservletrequest,"spareOutBillName",StringUtils.EMPTY).trim().equals("")){
			spareOutBillName = RequestUtils.getStringParameter(httpservletrequest,"spareOutBillName",StringUtils.EMPTY).trim();
		}
		//根据领料人
		if(!RequestUtils.getStringParameter(httpservletrequest,"borrower",StringUtils.EMPTY).trim().equals("")){
			borrower = RequestUtils.getStringParameter(httpservletrequest,"borrower",StringUtils.EMPTY).trim();
		}
		//根据出库人
		if(!RequestUtils.getStringParameter(httpservletrequest,"outPeople",StringUtils.EMPTY).trim().equals("")){
			outPeople = RequestUtils.getStringParameter(httpservletrequest,"outPeople",StringUtils.EMPTY).trim();
		}
		//部门
		String department_id = "";
		String dept_name = "";
		if (!RequestUtils.getStringParameter(httpservletrequest,
				"deptId", StringUtils.EMPTY).trim().equals("-1")) {
			department_id = RequestUtils.getStringParameter(httpservletrequest,
					"deptId", StringUtils.EMPTY).trim();
		}
		
		if (!department_id.equals("")) {
			dept = departmentManager.loadDepartment(Long.valueOf(department_id));
			dept_name = dept.getName();
		}
		if (!RequestUtils.getStringParameter(httpservletrequest,
				"inWarehouseId", StringUtils.EMPTY).trim().equals("-1")) {
			inWarehouseId = RequestUtils.getStringParameter(httpservletrequest,
					"inWarehouseId", StringUtils.EMPTY).trim();
		}
		if (!RequestUtils.getStringParameter(httpservletrequest,
				"warehouseId", StringUtils.EMPTY).trim().equals("-1")) {
			warehouseId = RequestUtils.getStringParameter(httpservletrequest,
					"warehouseId", StringUtils.EMPTY).trim();
		}
		if (!RequestUtils.getStringParameter(httpservletrequest,
				"oldSpare", StringUtils.EMPTY).trim().equals("-1")) {
			oldSpare = RequestUtils.getStringParameter(httpservletrequest,
					"oldSpare", StringUtils.EMPTY).trim();
		}
		if (!RequestUtils.getStringParameter(httpservletrequest,
				"outType", StringUtils.EMPTY).trim().equals("-1")) {
			outType = RequestUtils.getStringParameter(httpservletrequest,
					"outType", StringUtils.EMPTY).trim();
		}
		String[] queryInfo = new String[]{
				spareOutBillNo,spareOutBillName,borrower,outPeople,dept_name,
				RequestUtils.getStringParameter(httpservletrequest,
						"outDate_start", StringUtils.EMPTY).trim(), 
						RequestUtils.getStringParameter(httpservletrequest,
						"outDate_end", StringUtils.EMPTY).trim(),warehouseId,inWarehouseId,oldSpare,outType
		};
		result = spareOutBillManager.loadSpareOutBillDetail(queryInfo);
		Map model = new HashMap();
		model.put("reportData", result);
		return model;
	}

}

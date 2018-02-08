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
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.RequestUtils;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.security.Warehouse;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.spring.controller.JasperReportsController;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.model.base.codevalue.SpareDetailType;
import com.yongjun.tdms.service.asset.spare.SpareDetailTypeManager;
import com.yongjun.tdms.service.asset.spare.SpareDetailViewManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;

/**
 * <p>Title: ListSpareDetailViewReportController
 * <p>Description: 备件明细分类台帐打印控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: ListSpareDetailViewReportController.java 10078 2008-01-03 09:17:54Z xschen $
 */
public class ListSpareDetailViewReportController extends JasperReportsController {
	private final SpareDetailViewManager spareDetailViewManager;
	private final DepartmentManager departmentManager;
    private final SpareDetailTypeManager spareDetailTypeManager;
	private final CodeValueManager codeValueManager;
    private final UserManager userManager;
	
	private CodeValue  codeValue;
	private SpareDetailType spareDetailType;
	private Department depart;

	List result;
	public ListSpareDetailViewReportController(
			SpareDetailViewManager spareDetailViewManager,
			DepartmentManager departmentManager,
			SpareDetailTypeManager spareDetailTypeManager,
			CodeValueManager codeValueManager,
			UserManager userManager) {
		this.spareDetailViewManager = spareDetailViewManager;
		this.departmentManager = departmentManager;
		this.spareDetailTypeManager=spareDetailTypeManager;
		this.codeValueManager=codeValueManager;
		this.userManager= userManager;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	protected Map getModel(HttpServletRequest httpservletrequest)
			throws Exception {
		String spareNo = ""; //备件编号
		String spareName = ""; //备件中文名称
		String spareEnName = ""; //备件英文名称
		String modelSpecs = ""; //型号
		String loctionCode = ""; //库位号
		String department_id = ""; //部门ID
		String depart_Name = ""; //部门名称
		String category_id = ""; //种类ID
		String category = ""; //种类名称
		String detailType_id = ""; //明细种类ID
		String detailType = ""; //明细种类名称
		String onlyHasStocks="";//仅库存不为零
		String warehouse="";//仓库
		String regionalId="";   //库区
		String warehouseId="";
		
		//备件编号
		if (!RequestUtils.getStringParameter(httpservletrequest, "spareNo",
				StringUtils.EMPTY).trim().equals("")) {
			spareNo = RequestUtils.getStringParameter(httpservletrequest,
					"spareNo", StringUtils.EMPTY).trim();
		}
		//备件中文名称
		if (!RequestUtils.getStringParameter(httpservletrequest, "spareName",
				StringUtils.EMPTY).trim().equals("")) {
			spareName = RequestUtils.getStringParameter(httpservletrequest,
					"spareName", StringUtils.EMPTY).trim();
		}
        //备件英文名称
//		if (!RequestUtils.getStringParameter(httpservletrequest, "spareEnName",
//				StringUtils.EMPTY).trim().equals("")) {
//			spareEnName = RequestUtils.getStringParameter(httpservletrequest,
//					"spareEnName", StringUtils.EMPTY).trim();
//		}
        //备件型号
		if (!RequestUtils.getStringParameter(httpservletrequest, "modelSpecs",
				StringUtils.EMPTY).trim().equals("")) {
			modelSpecs = RequestUtils.getStringParameter(httpservletrequest,
					"modelSpecs", StringUtils.EMPTY).trim();
		}
        //备件库位号
		if (!RequestUtils.getStringParameter(httpservletrequest, "loctionCode",
				StringUtils.EMPTY).trim().equals("")) {
			modelSpecs = RequestUtils.getStringParameter(httpservletrequest,
					"loctionCode", StringUtils.EMPTY).trim();
		}
		//部门ID
		if (!RequestUtils.getStringParameter(httpservletrequest,
					"department.id", StringUtils.EMPTY).trim().equals("-1")) {
				department_id = RequestUtils.getStringParameter(httpservletrequest,
						"department.id", StringUtils.EMPTY).trim();
		}
		//部门名称
		if (!department_id.equals("")) {
				depart = departmentManager
						.loadDepartment(Long.valueOf(department_id));
				depart_Name = depart.getName();
		}
		//种类ID
		if (!RequestUtils.getStringParameter(httpservletrequest,
					"category.code", StringUtils.EMPTY).trim().equals("-1")) {
			category_id = RequestUtils.getStringParameter(httpservletrequest,
						"category.code", StringUtils.EMPTY).trim();
		}
		//种类名称
		if (!category_id.equals("")) {
			codeValue=codeValueManager.loadCodeValue(Long.valueOf(category_id));
			category = codeValue.getValue();
		}
		//库区
		if (!RequestUtils.getStringParameter(httpservletrequest,
				"regionalId", StringUtils.EMPTY).trim().equals("-1")) {
			regionalId = RequestUtils.getStringParameter(httpservletrequest,
					"regionalId", StringUtils.EMPTY).trim();
		}
		//仓库
		if (!RequestUtils.getStringParameter(httpservletrequest,
				"warehouseId", StringUtils.EMPTY).trim().equals("-1")) {
			warehouseId = RequestUtils.getStringParameter(httpservletrequest,
					"warehouseId", StringUtils.EMPTY).trim();
		}
		//明细种类ID
//		if (!RequestUtils.getStringParameter(httpservletrequest,
//					"spareDetailType.id", StringUtils.EMPTY).trim().equals("-1")) {
//			detailType_id = RequestUtils.getStringParameter(httpservletrequest,
//						"spareDetailType.id", StringUtils.EMPTY).trim();
//		}
//		//明细种类名称
//		if (!detailType_id.equals("")) {
//			spareDetailType = spareDetailTypeManager.loadSpareDetailType(Long.valueOf(detailType_id));
//			detailType = spareDetailType.getName();
//		}
		//仅库存不为零
		if (!RequestUtils.getStringParameter(httpservletrequest,
				"onlyHasStocks", StringUtils.EMPTY).trim().equals("-1")) {
			onlyHasStocks = RequestUtils.getStringParameter(httpservletrequest,
					"onlyHasStocks", StringUtils.EMPTY).trim();
	    }
       /*仓库
		if (!RequestUtils.getStringParameter(httpservletrequest,
				"warehouse", StringUtils.EMPTY).trim().equals("")) {
			warehouse = RequestUtils.getStringParameter(httpservletrequest,
					"warehouse", StringUtils.EMPTY).trim();
	    }*/
		
//		String longinUserId = httpservletrequest.getParameter("longinUserId");
//		User user = this.userManager.loadUser(Long.valueOf(longinUserId));
//		Set<Warehouse> set = user.getWarehouses();
//		if(null != set && set.size()>0){
//			for(Warehouse w : set){
//				warehouse = warehouse +w.getId().toString()+",";
//			}
//		}
//		if(warehouse.length()>0){
//			warehouse = warehouse.substring(0, warehouse.length()-1);
//		}
		
		
		String[] queryInfo = new String[] { spareNo, spareName,
				modelSpecs, loctionCode, depart_Name, category,warehouseId,regionalId,onlyHasStocks};
		//result = spareDetailViewManager.loadSpareDetailAccount(queryInfo);
		result = spareDetailViewManager.loadSpareLocation(queryInfo);
		Map model = new HashMap();
		model.put("reportData", result);
		return model;
	}

 
	public User getLoginUser() {
		return this.userManager.getUser();
	}
	
	
}

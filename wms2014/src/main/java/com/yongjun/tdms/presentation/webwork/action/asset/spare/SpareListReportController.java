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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.RequestUtils;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.spring.controller.JasperReportsController;
import com.yongjun.pluto.spring.util.SubReportHelper;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.service.asset.spare.SpareManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;

/**
 * <p>Title: SpareListReportController
 * <p>Description: 备件清单打印实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: SpareListReportController.java 10078 2008-01-03 09:17:54Z wzou $
 */
public class SpareListReportController extends JasperReportsController {
	private final SpareManager spareManager;
	private final CodeValueManager codeValueManager;
	private final DepartmentManager departmentManager;
	private CodeValue  codeValue;
	private Department depart;
	HttpServletRequest request;
	List spareList = new ArrayList();
	String spareNo, spareName, spareEnName, modelSpecs;
	SubReportHelper subReportHelper;
	List result;

	public SpareListReportController(SpareManager spareManager, CodeValueManager codeValueManager,
			DepartmentManager departmentManager) {
		this.spareManager = spareManager;
		this.codeValueManager=codeValueManager;
		this.departmentManager=departmentManager;
	}

	//TODO: 移动到共通代码中
	static String trim(String str) {
		return StringUtils.clean(str);
	}
	
	/**
	 * 通过页面获取所提交的查询字段，保存到数组queryInfo中，在Dao中拼SQL语句，进行复合查询。
	 * @return Map类型的model对象
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected Map getModel(HttpServletRequest httpServletRequest)
			throws Exception {
		String department_id = "";
		String category_code = "";
		String category="";
		String depart_Name = ""; //部门名称
		if (RequestUtils.getStringParameter(httpServletRequest,
				"department.id", StringUtils.EMPTY).equals("-1") == false) {
			department_id = RequestUtils.getStringParameter(httpServletRequest,
					"department.id", StringUtils.EMPTY);
		}
		if (RequestUtils.getStringParameter(httpServletRequest,
				"category.code", StringUtils.EMPTY).equals("-1") == false) {
			category_code = RequestUtils.getStringParameter(httpServletRequest,
					"category.code", StringUtils.EMPTY);
		}
		//部门名称
		if (!department_id.equals("")) {
				depart = departmentManager
						.loadDepartment(Long.valueOf(department_id));
				depart_Name = depart.getName();
		}
		//种类名称
		if (!category_code.equals("")) {
			codeValue=codeValueManager.loadCodeValue(Long.valueOf(category_code));
			category = codeValue.getValue();
			System.out.println("========nnnnnnnnnnn============"+category);
		}
		String[] queryInfo = new String[] {
				trim(RequestUtils.getStringParameter(httpServletRequest,
						"spareNo", StringUtils.EMPTY)),
				trim(RequestUtils.getStringParameter(httpServletRequest,
						"spareName", StringUtils.EMPTY)),
				trim(RequestUtils.getStringParameter(httpServletRequest,
						"spareEnName", StringUtils.EMPTY)),
				trim(RequestUtils.getStringParameter(httpServletRequest,
						"modelSpecs", StringUtils.EMPTY)),
						depart_Name,
				category,
				RequestUtils.getStringParameter(httpServletRequest,
						"toolingDevFlag", StringUtils.EMPTY) };
		result = spareManager.Query(queryInfo);
		Map model = new HashMap();
//		subReportHelper = new SubReportHelper();
//		subReportHelper.setSubReportDatas(result);
//		model.put("reportData", new SubReportHelper[] { subReportHelper });
		model.put("reportData",result);
		return model;
	}
}

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
package com.yongjun.tdms.presentation.webwork.action.asset.spare.inAndOutAndStoreReport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.RequestUtils;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.spring.controller.JasperReportsController;
import com.yongjun.tdms.service.asset.spare.inAndOutAndStoreReport.SpareInAndOutAndStoreReportManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
/**
 * <p>Title:ListSpareInAndOutReportController
 * <p>Description:</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author yli@yj-technology.com
 * @version $Id: ListSpareInAndOutReportController 2008-12-16 10:32:21 xschen$
 */
public class ListSpareInAndOutReportController extends JasperReportsController{
  private final SpareInAndOutAndStoreReportManager spareInAndOutAndStoreReportManager;//库房收发存报表服务接口类
  private final DepartmentManager departmentManager;
  private Department depart;
  private List result; 
  public ListSpareInAndOutReportController(SpareInAndOutAndStoreReportManager spareInAndOutAndStoreReportManager,
		  DepartmentManager departmentManager){
	  this.spareInAndOutAndStoreReportManager=spareInAndOutAndStoreReportManager;
	  this.departmentManager=departmentManager;
  }
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	protected Map getModel(HttpServletRequest httpservletrequest) throws Exception {
	  String spareNo="";
	  String spareName="";
	  String model1="";
	  String warehouse = "";
	  String regional = "";
	  String locationCode="";
	  String department_id="";
	  String toolingDevFlag="";
	  String depart_Name="";
	  String onlyCheck="";
	  String createTime_start="";
	  String createTime_end="";
	  //备件编号
	  if(!RequestUtils.getStringParameter(httpservletrequest,"spareNo",StringUtils.EMPTY).trim().equals("")){
		  spareNo = RequestUtils.getStringParameter(httpservletrequest,"spareNo",StringUtils.EMPTY).trim();
		}
	  //备件名称
	  if(!RequestUtils.getStringParameter(httpservletrequest,"spareName",StringUtils.EMPTY).trim().equals("")){
		  spareName = RequestUtils.getStringParameter(httpservletrequest,"spareName",StringUtils.EMPTY).trim();
		}
	  //模型
	  if(!RequestUtils.getStringParameter(httpservletrequest,"model",StringUtils.EMPTY).trim().equals("")){
		  model1 = RequestUtils.getStringParameter(httpservletrequest,"model",StringUtils.EMPTY).trim();
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
	  if(!RequestUtils.getStringParameter(httpservletrequest,"locationCode",StringUtils.EMPTY).trim().equals("")){
		  locationCode = RequestUtils.getStringParameter(httpservletrequest,"locationCode",StringUtils.EMPTY).trim();
		}
	  //部门
	  if (RequestUtils.getStringParameter(httpservletrequest,
				"department.id", StringUtils.EMPTY).trim().equals("-1") == false) {
			department_id = RequestUtils.getStringParameter(httpservletrequest,
					"department.id", StringUtils.EMPTY).trim();
		}
		if (!department_id.equals("")) {
			depart = departmentManager
					.loadDepartment(Long.valueOf(department_id));
			depart_Name = depart.getName();
		}
	  //仅显示发生额
	  if(!RequestUtils.getStringParameter(httpservletrequest,"onlyCheck",StringUtils.EMPTY).trim().equals("")){
		  onlyCheck=RequestUtils.getStringParameter(httpservletrequest,"onlyCheck",StringUtils.EMPTY).trim();
	  }
	  //备件类别
	  if(!RequestUtils.getStringParameter(httpservletrequest,"toolingDevFlag",StringUtils.EMPTY).trim().equals("")){
		   if(RequestUtils.getStringParameter(httpservletrequest,"toolingDevFlag",StringUtils.EMPTY).trim().equals("TOOLING")) {
			   toolingDevFlag="工装";
		   }
		   else if (RequestUtils.getStringParameter(httpservletrequest,"toolingDevFlag",StringUtils.EMPTY).trim().equals("DEVICE")){
			   toolingDevFlag="设备";
		   }
	  }
	  //开始时间
	  if(!RequestUtils.getStringParameter(httpservletrequest,"createTime_start",StringUtils.EMPTY).trim().equals("")){
		  createTime_start=RequestUtils.getStringParameter(httpservletrequest,"createTime_start",StringUtils.EMPTY).trim();
	  }
	  //结束时间
	  if(!RequestUtils.getStringParameter(httpservletrequest,"createTime_end",StringUtils.EMPTY).trim().equals("")){
		  createTime_end=RequestUtils.getStringParameter(httpservletrequest,"createTime_end",StringUtils.EMPTY).trim();
	  }
		  String[] queryInfo = new String[]{
				  spareNo,spareName,model1,locationCode,depart_Name,
				  createTime_start, createTime_end,
							toolingDevFlag,onlyCheck,warehouse,regional
		  };
		  result= spareInAndOutAndStoreReportManager.getSpareInAndOutAndStoreViewCollention(queryInfo);
    	  Map model = new HashMap();
		model.put("reportData", result);
	  return model;
	}

}

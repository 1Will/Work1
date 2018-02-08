  /*
 * Copyright (c) 2001-2010 YongJun Technology Pte.,Ltd. All Rights Reserved.
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
package com.yongjun.tdms.presentation.webwork.action.prophase.business.tooling.reportmanager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.RequestUtils;

import com.yongjun.pluto.spring.controller.JasperReportsController;
import com.yongjun.tdms.model.prophase.business.report.SubscribeCountByDeptReport;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.prophase.business.tooling.reportmanager.SubscribeDetailMonthViewManager;



/**
 * <p>Title: com.yongjun.tdms.presentation.webwork.action.prophase.business.tooling.reportmanager.ListSubscribeDetailMonthReportController.java</p>
 * <p>Description: 采购计划单落实情况 按部门</p>
 * <p>Copyright: Copyright (c) 2010 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * <p>@author zzb @yj-technology.com</p>
 * <p>@version $ Id:ListSubscribeDetailMonthReportController.java 2011-3-15 zzb $</p>
 */
public class ListSubscribeDetailMonthReportController extends JasperReportsController{

/*	@Override
	protected Map getModel(HttpServletRequest httpservletrequest) throws Exception {
		return null;
	}*/
	private List result;
	private final SubscribeDetailMonthViewManager subscribeDetailMonthViewManager;
	private final DepartmentManager departmentManager;
	
	
    public ListSubscribeDetailMonthReportController(SubscribeDetailMonthViewManager subscribeDetailMonthViewManager,
    		DepartmentManager departmentManager){
    	this.subscribeDetailMonthViewManager = subscribeDetailMonthViewManager;
    	this.departmentManager = departmentManager;
    }
    
	@SuppressWarnings("unchecked")
	@Override
	protected Map getModel(HttpServletRequest httpservletrequest) throws Exception {
//		String year_month = httpservletrequest.getParameter("month");
//		 
//		String dept = null;
//		String deptId = httpservletrequest.getParameter("department.id");
		
		String[] queryInfo = new String[] {
				RequestUtils.getStringParameter(httpservletrequest, "month",
						StringUtils.EMPTY).trim(),
						RequestUtils.getStringParameter(httpservletrequest, "department.id",
						StringUtils.EMPTY).trim()
				};
		
//		if(null != deptId && !"".equals(deptId)){
//			 dept = this.departmentManager.loadDepartment(
//					Long.parseLong(deptId)).getName();
//		}
		
//if(null != startDate && !"".equals(startDate)){
//			startDate = startDate+"-01";
//			Date date = this.stringToDate(startDate);
//			Calendar cal=Calendar.getInstance();
//			cal.setTime(date);
//			cal.add(Calendar.MONTH, 1);
//			endDate = this.dateToString(cal.getTime());
			
			
			List<SubscribeCountByDeptReport> result  = new ArrayList<SubscribeCountByDeptReport>();
		    result = this.subscribeDetailMonthViewManager.loadDetailViewNumber(queryInfo); 
		    Map model = new HashMap();
		    model.put("reportData", result);
//		    this.subscribeDetailMonthViewManager.deleteAllMonthView();
//		    if(result.size()>0){
//			   for(SubscribeCountByDeptReport v:result){
//				   this.subscribeDetailMonthViewManager.storeMonthView(v);
//			   }
//			   
//		   }
			
		//}
		
		return model;
	}
	/**
	 * 字符串到日期
	 * @param str
	 * @return
	 */
	public Date stringToDate(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Date date = null;
		try {
			date = sdf.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
	/**
	 * 日期到字符串
	 * @param date
	 * @return
	 */
	public String dateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String str= null; 
		str = sdf.format(date);
		 
		return str;
	}

}


/*
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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.service.base.org.DepartmentManager;



/**
 * <p>Title: com.yongjun.tdms.presentation.webwork.action.prophase.business.tooling.reportmanager.ListSubscribeDetailMonthReport.java</p>
 * <p>Description: 采购计划报表查询控制类 按部门</p>
 * <p>Copyright: Copyright (c) 2010 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * <p>@author zzb @yj-technology.com</p>
 * <p>@version $ Id:ListSubscribeDetailMonthReport.java 2011-3-14 zzb $</p>
 */
public class ListSubscribeDetailMonthReportAction extends ValueListAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8262415421857923728L;

	@Override
	protected String getAdapterName() {
		return "listSubscribeDetailMonView";
	}

//	private final SubscribeDetailMonthViewManager subscribeDetailMonthViewManager;
//
	private final DepartmentManager departmentManager;
//	
	public ListSubscribeDetailMonthReportAction(
			DepartmentManager departmentManager) {
		this.departmentManager = departmentManager;
	}
//
//	public void prepare() {
//		String date_start = this.request.getParameter("viewDate_start");
//		String date_end = this.request.getParameter("viewDate_end");
//		String deptId = this.request.getParameter("department.id");
//		List<SubscribeDetailMonthView> list ;
//	
//		list = this.subscribeDetailMonthViewManager.loadByAllMonthView();
//		if(null != list && list.size()>0 && (null != list.get(0).getDept() && !"".equals(list.get(0).getDept()))){
//			this.request.setAttribute("viewDate_start", list.get(0).getMonth1());
//			this.request.setAttribute("viewDate_end", list.get(0).getMonth2());
//		}
//		if (null != deptId && !"".equals(deptId)) {
//			dept = this.departmentManager
//					.loadDepartment(Long.parseLong(deptId)).getName();
//		}
//		if (null != date_start && !"".equals(date_start)) {
//			this.subscribeDetailMonthViewManager.deleteAllMonthView();
//			  list = this.subscribeDetailMonthViewManager
//					.loadDetailViewNumber(dept, this.stringToDate(date_start),
//							this.stringToDate(date_end));
//			if (null != list && list.size() > 0) {
//				 for (SubscribeDetailMonthView v : list) {
//					this.subscribeDetailMonthViewManager.storeMonthView(v);
//				}
//			}
//
//		}
//
//	}
// 
//	
//	@SuppressWarnings("unchecked")
//	protected Map getRequestParameterMap() {
//		Map map = super.getRequestParameterMap();
//		if(null != dept && !"".equals(dept)){
//			 map.put("dept", dept);
//		}
//	   
//		return map;
//	}
//	
//	
//
	/**	
	 * 获得所有部门
	 * @return
	 */
	public List getDepartments() {
		if (!this.userManager.getUser().isViewAll()) { //如果用户只有看本部门的权限
			List<Department> list = new ArrayList<Department>();
			if (null == this.userManager.getUser().getDepartment()) { //如果用户不属于任何部门,置部门ID为-2
				Department department = new Department();
				department.setId(Long.valueOf(-2L));
				department.setName("");
				list.add(department);
			} else {
				list.add(this.departmentManager.loadDepartment(this.userManager
						.getUser().getDepartment().getId()));
			}
			//显示附属部门
			Set<Department> depts = userManager.getUser().getDepartments();
			if (depts != null) {
				list.addAll(depts);
			}

			return list;
		}
		return departmentManager.createSelectDepartments(this
				.getText("select.option.all"));
	}
//
//	/**
//	 * 日期类型到字符串
//	 * @param str
//	 * @return
//	 */
//	public Date stringToDate(String str) {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		Date date = null;
//		try {
//			date = sdf.parse(str);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		return date;
//	}
//
//	/**
//	 * String到Date
//	 * @param date
//	 * @return
//	 */
//	public String dateToString(Date date) {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		String str = null;
//		str = sdf.format(date);
//
//		return str;
//	}
//
//	@Override
//	protected String getAdapterName() {
//		//return "listSubscribeReport";
//		return "listSubscribeDetailMonView";
//	}

}

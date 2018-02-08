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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.checkpoint.report;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.base.event.EventNew;
import com.yongjun.tdms.service.base.event.EventNewManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.checkpoint.CheckPointReportManager;


/**
 * <p>Title: ListCheckPointReportAction
 * <p>Description: 设备点检报告页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id:$
 */
public class ListCheckPointReportAction extends ValueListAction{
	private static final long serialVersionUID = 4951298473238133918L;
	private final DepartmentManager departmentManager;
	private final EventNewManager eventNewManager;
	private final CheckPointReportManager checkPointReportManager;
	//private EventNew eventNew;
	String month;
	String reportIds="";
	
	public ListCheckPointReportAction(DepartmentManager departmentManager,
			EventNewManager eventNewManager,
			CheckPointReportManager checkPointReportManager) {
		this.departmentManager = departmentManager;
		this.eventNewManager = eventNewManager;
		this.checkPointReportManager = checkPointReportManager;
	}
	
	@Override
	protected String getAdapterName() {
		return "checkPointReports";
	}
	
	public void prepare() {
	}
	
	public String execute() {
		if (this.isSubmitRecord()){
			return submitRecord();
		}
		return SUCCESS;
	}
	
	private boolean isSubmitRecord() {
		if(this.hasKey("submitRecord")){
			if (!StringUtils.isEmpty(request.getParameter("submitRecord"))) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * 提交记录，storeEventNew的参数
	 * 1.EventType ：1000表示点检报告的提交
	 * 2.Time
	 * 3.Status: 目前为0
	 * 4.OrgId: 目前为1
	 * 5.UserId
	 * 6.DocType:目前和EventType内容一致
	 * 7.DocId ：提交的多个报告ID,是字符串型
	 * 8.MoreInfo:一些相关信息
	 */
	public String submitRecord() {
		if (null == this.month) {
			if (!StringUtils.isEmpty(request.getParameter("month"))) {
				this.month = request.getParameter("month");
				for (Long id : this.checkPointReportManager.loadAllIdsByMonth(this.month)){
					this.reportIds += (id+",");
				}
			}
		}
		this.eventNewManager.storeEventNew(1000,Calendar.getInstance().getTime(),0,1,this.userManager.getUser().getId(),1000,reportIds,month);
		//点检报告提交的同时进行设备故障分析处理（由于设备故障分析的数据有部分来自于点检报告明细）
		EventNew en = new EventNew();
		en.setEventType(1001);
		en.setTime(Calendar.getInstance().getTime());
		en.setStatus(0);
		en.setUserId(this.userManager.getUser().getId());
		en.setDocType(1001);
		en.setDocId(0);
		en.setMoreInfo("故障分析处理");
		this.eventNewManager.storeEventNew(en);
		return SUCCESS;
	}
	
	/**
	 * 如果当前用户只能看本部门数据，则获取该部门数据，否则获取所有部门 
	 * @return List 部门集合
	 */
	public List getDepartments() {
		if (!this.userManager.getUser().isViewAll()) {
			List<Department> list = new ArrayList<Department>();
			if (null == this.userManager.getUser().getDepartment()) {
				Department department = new Department();
				department.setId(Long.valueOf(-1L));
				department.setName("");
				list.add(department);
			} else {
				list.add(this.departmentManager.loadDepartment(this.userManager.getUser().getDepartment().getId()));
			}
			//显示附属部门
			Set<Department> depts =userManager.getUser().getDepartments();
			if(depts!=null){
				list.addAll(depts);
			}
			return list;
		}
		return departmentManager.createSelectDepartments(this
				.getText("select.option.all"));
	}
	
	/**
	 * 获取当前登陆的用户
	 * 
	 * @return User 用户实体
	 */
	public User getLoginUser() {
		return this.userManager.getUser();
	}

}

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

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointReport;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.checkpoint.CheckPointReportManager;


/**
 * <p>Title: EditCheckPointReportAction
 * <p>Description: 设备点检报告维护控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id:$
 */
public class EditCheckPointReportAction extends PrepareAction {
	private static final long serialVersionUID = 6703816937488142894L;
	private CheckPointReport report;
	private CheckPointReportManager checkPointReportManager;
	private final DepartmentManager departmentManager;
	private final UserManager userManager;
	
	public EditCheckPointReportAction(CheckPointReportManager checkPointReportManager,
			DepartmentManager departmentManager,
			UserManager userManager) {
		this.checkPointReportManager = checkPointReportManager;
		this.departmentManager = departmentManager;
		this.userManager = userManager;
	}
	
	public void prepare() throws Exception {
		if (this.report == null){
			if (this.hasId("report.id")){
				this.report = this.checkPointReportManager.loadCheckPointReport(
						this.getId("report.id"));
			}else {
				this.report = new CheckPointReport();
			}
			
		}
	}
	
	public String save() {
		boolean isNew = this.report.isNew();
		if (!StringUtils.isEmpty(request.getParameter("speaker.id"))) {
			report.setSpeaker(this.userManager.loadUser(this.getId("speaker.id")));
		}
		this.checkPointReportManager.storeCheckPointReport(report);
		
		if (isNew) {
			this.addActionMessage(this.getText("report.add.success",
					Arrays.asList(new Object[] { report.getName() })));
			return NEW;
		}else {
			this.addActionMessage(this.getText("report.edit.success",
					Arrays.asList(new Object[] { report.getName() })));
			return SUCCESS;
		}
	}
	
	public CheckPointReport getReport() {
		return report;
	}

	public void setReport(CheckPointReport report) {
		this.report = report;
	}

	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public List getDepartments() {
		return this.departmentManager.loadAllDepartments();
	}
	
	public User getLoginUser() {
		return userManager.getUser();
	}
}

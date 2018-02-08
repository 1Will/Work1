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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.tooling.record;

import java.util.Arrays;
import java.util.List;

import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.tdms.model.runmaintenance.tooling.record.ToolingChangeBill;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.tooling.record.ToolingChangeBillManager;
import org.apache.commons.lang.StringUtils;

/**
 * @author qs
 * @version $Id: EditToolingChangeBillAction.java 11020 2008-02-19 03:37:46Z zbzhang $
 */
public class EditToolingChangeBillAction extends PrepareAction {
	private static final long serialVersionUID = 6335422557559211550L;
	private final ToolingChangeBillManager toolingChangeBillManager;
	private final DepartmentManager departmentManager;
	private final DeviceCardManager deviceCardManager;
	private final CodeValueManager codeValueManager;
	private final UserManager userManager;
	private ToolingChangeBill toolingChangeBill;
	private DeviceCard tooling;
	
	public EditToolingChangeBillAction(ToolingChangeBillManager toolingChangeBillManager,
			                   DepartmentManager departmentManager,
			                   DeviceCardManager deviceCardManager,
			                   UserManager userManager,
			                   CodeValueManager codeValueManager) {
		this.toolingChangeBillManager = toolingChangeBillManager;
		this.departmentManager = departmentManager;
		this.deviceCardManager = deviceCardManager;
		this.userManager = userManager;
		this.codeValueManager = codeValueManager;
	}

	public void prepare() throws Exception {
		if (null == toolingChangeBill) {
			if (this.hasId("toolingChangeBill.id")) {
				this.toolingChangeBill = this.toolingChangeBillManager.loadToolingChangeBill(this.getId("toolingChangeBill.id"));
			    if (!this.hasId("tooling.id")) {
			    	this.tooling = this.toolingChangeBill.getTooling();
			    } 
			} else {
				this.toolingChangeBill = new ToolingChangeBill();
			}
		}
	}
	
	public String save() {
		boolean isNew = this.toolingChangeBill.isNew();
		
		if (!StringUtils.isEmpty(request.getParameter("tooling.id"))) {
			this.tooling = this.deviceCardManager.loadDevice(this.getId("tooling.id"));
			this.toolingChangeBill.setTooling(tooling);
		}
		if (!StringUtils.isEmpty(request.getParameter("department.id"))) {
			this.toolingChangeBill.setAcceptDepartment(this.departmentManager.loadDepartment(this.getId("department.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("acceptor.id"))) {
			this.toolingChangeBill.setAcceptor(this.userManager.loadUser(this.getId("acceptor.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("bailor.id"))) {
			this.toolingChangeBill.setBailor(this.userManager.loadUser(this.getId("bailor.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("changeStateFlag"))) {
			//设置变更单为已变更状态
			toolingChangeBill.setChangeBillFlag(true);
		} else {
			//设置变更单为变中状态
			toolingChangeBill.setChangeBillFlag(false);
		}
		if (!StringUtils.isEmpty(request.getParameter("toolingState"))){
			tooling.setToolingStatus(this.codeValueManager.loadCodeTypeByRealCode(request.getParameter("toolingState")));
		}
		this.toolingChangeBillManager.storeToolingChangeBill(toolingChangeBill, tooling);
		if (isNew) {
			this.addActionMessage(this.getText("toolingChangeBill.add.success", Arrays
					.asList(new Object[] { toolingChangeBill.getBillName() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText("toolingChangeBill.edit.success", Arrays
					.asList(new Object[] { toolingChangeBill.getBillName() })));
			return SUCCESS;
		}
	}

	public ToolingChangeBill getToolingChangeBill() {
		return toolingChangeBill;
	}

	public void setToolingChangeBill(ToolingChangeBill toolingChangeBill) {
		this.toolingChangeBill = toolingChangeBill;
	}

	public DeviceCard getTooling() {
		return tooling;
	}
	
	public List<Department> getDepartments() {
		return this.departmentManager.loadAllDepartments();
	}
	
	public User getLoginUser() {
		return this.userManager.getUser();
	}

}

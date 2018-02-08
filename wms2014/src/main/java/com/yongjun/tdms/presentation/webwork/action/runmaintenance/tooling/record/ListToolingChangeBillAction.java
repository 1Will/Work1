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

import java.util.List;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.runmaintenance.tooling.record.ToolingChangeBill;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.tooling.record.ToolingChangeBillManager;

/**
 * <p>Title: ListToolingChangeBillAction
 * <p>Description: 工装变更单页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: ListToolingChangeBillAction.java 11028 2008-02-19 07:16:31Z zbzhang $
 */
public class ListToolingChangeBillAction extends ValueListAction {
	private static final long serialVersionUID = -2691679636670360346L;
	private final ToolingChangeBillManager toolingChangeBillManager;
	private final DepartmentManager departmentManager;
	private List<ToolingChangeBill> toolingChangeBills;
	
	public ListToolingChangeBillAction(ToolingChangeBillManager toolingChangeBillManager,
			                    DepartmentManager departmentManager) {
		this.toolingChangeBillManager = toolingChangeBillManager;
		this.departmentManager = departmentManager;
	}

	public void prepare() {
		if (this.hasId("toolingChangeBillIds")) {
			this.toolingChangeBills = this.toolingChangeBillManager.loadAllToolingChangeBills(this.getIds("toolingChangeBillIds"));
		}
	}
	
	public String execute() {
		if (this.isDisabled()) {
			return disabled();
		}
		if (this.isEnable()) {
			return enabled();
		}
		return SUCCESS;
	}
	
	public String disabled() {
		this.toolingChangeBillManager.disabledAllToolingChangeBills(toolingChangeBills);
		this.addActionMessage(this.getText("changeBill.disabled.success"));
		return SUCCESS;
	}
	
	public String enabled() {
		this.toolingChangeBillManager.enabledAllToolingChangeBills(toolingChangeBills);
		this.addActionMessage(this.getText("changeBill.enabled.success"));
		return SUCCESS;
	}
	
	@Override
	protected String getAdapterName() {
		return "toolingChangeBills";
	}

	public List<ToolingChangeBill> getToolingChangeBills() {
		return toolingChangeBills;
	}

	public void setToolingChangeBills(List<ToolingChangeBill> toolingChangeBills) {
		this.toolingChangeBills = toolingChangeBills;
	}

}

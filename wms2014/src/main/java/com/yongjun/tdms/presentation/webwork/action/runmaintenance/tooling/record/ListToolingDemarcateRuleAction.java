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
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;

/**
 * <p>Title: EditToolingDemarcateRuleAction
 * <p>Description: 工装标定规则页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: ListToolingDemarcateRuleAction.java 10840 2008-02-01 01:58:31Z qsun $
 */
public class ListToolingDemarcateRuleAction extends ValueListAction{
	private static final long serialVersionUID = -2227655610025830548L;
	private final DepartmentManager departmentManager;
	private final DeviceCardManager toolingManager;
	private String alterToolingDemacrateCycle;
	private String alterToolingManager;
	
	public ListToolingDemarcateRuleAction(DepartmentManager departmentManager, 
			DeviceCardManager toolingManager) {
		this.departmentManager = departmentManager;
		this.toolingManager = toolingManager;
	}
	
	/**
	 * 获取页面传来的参数 alterToolingDemacrateCycle ,alterToolingManager
	 * 
	 */
	public void prepare() throws Exception {
		if (null == alterToolingDemacrateCycle) {
			alterToolingDemacrateCycle = request.getParameter("alterToolingDemacrateCycle");
		}
		if (null == alterToolingManager) {
			alterToolingManager = request.getParameter("alterToolingManager");
		}
	}

	/**
	 * 通过页面传来的参数，保存工装
	 * @return string SUCCESS
	 */
	public String execute() {
		if (!(this.alterToolingDemacrateCycle.equals("")) || !(this.alterToolingManager.equals(""))) {
			this.toolingManager.storeTooling(alterToolingDemacrateCycle,alterToolingManager);
			this.addActionMessage(this.getText("tooling.demacrateCycle.edit.success"));
		}
		return SUCCESS;
	}
	
	@Override
	protected String getAdapterName() {
		return "demarcateRules";
	}
	
	public List getDepartments() {
		return this.departmentManager.createSelectDepartments(this.getText("select.option.all"));
	}

	public String getAlterToolingManager() {
		return alterToolingManager;
	}

	public void setAlterToolingManager(String alterToolingManager) {
		this.alterToolingManager = alterToolingManager;
	}
}

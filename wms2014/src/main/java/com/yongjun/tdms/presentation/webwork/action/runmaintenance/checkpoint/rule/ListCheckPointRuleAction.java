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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.checkpoint.rule;

import java.util.List;
import java.util.Map;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointRule;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.checkpoint.CheckPointRuleManager;
import com.yongjun.tdms.workflow.service.docstate.WfDocStateManager;

/**
 * <p>Title: ListCheckPointRuleAction
 * <p>Description: 点检标准列表访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: ListCheckPointRuleAction.java 11229 2008-03-09 07:47:35Z wzou $
 */
public class ListCheckPointRuleAction extends ValueListAction {
	private static final long serialVersionUID = 3087495020938455975L;

	private final CheckPointRuleManager checkPointRuleManager;
	private final DeviceCardManager deviceCardManager;
	
	private DeviceCard toolingDev;
	private List<CheckPointRule> rules;
	

	public ListCheckPointRuleAction(
			CheckPointRuleManager checkPointRuleManager,
			DeviceCardManager deviceCardManager) {
		this.checkPointRuleManager = checkPointRuleManager;
		this.deviceCardManager = deviceCardManager;
	}

	public void prepare() throws Exception {
		if (this.hasId("toolingDev.id")) {
			this.toolingDev = this.deviceCardManager.loadDevice(this
					.getId("toolingDev.id"));
		}
		
		if (this.rules == null && this.hasIds("checkPointRuleIds")) {
			this.rules = this.checkPointRuleManager.loadAllCheckPointRule(this
					.getIds("checkPointRuleIds"));
		}
		this.setFirst(false);
	}

	public String execute() throws Exception {
		if (this.isDelete()) {
			delete();
		}
		return SUCCESS;
	}

	public String delete() {
		try {
			this.checkPointRuleManager.deleteAllCheckPointRule(this.rules);
			this.addActionMessage(this.getText("checkPointRule.delete.success"));
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionMessage(this.getText("checkPointRule.delete.error"));
			return ERROR;
		}

		return SUCCESS;
	}
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		map.put("toolingDev.id",this.getId("toolingDev.id"));	
		return map;
	}
	
	@Override
	protected String getAdapterName() {
		return "checkPointRules";
	}

	public List<CheckPointRule> getRules() {
		return rules;
	}

	public void setRules(List<CheckPointRule> rules) {
		this.rules = rules;
	}

	public DeviceCard getToolingDev() {
		return toolingDev;
	}

	public void setToolingDev(DeviceCard toolingDev) {
		this.toolingDev = toolingDev;
	}
}

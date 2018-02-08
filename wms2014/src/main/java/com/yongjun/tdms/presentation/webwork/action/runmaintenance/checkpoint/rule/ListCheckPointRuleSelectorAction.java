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
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.checkpoint.CheckPointRuleManager;
import com.yongjun.tdms.workflow.service.docstate.WfDocStateManager;

/**
 * 
 * @author qs
 * @version $Id: ListCheckPointRuleSelectorAction.java 8726 2007-11-28 09:51:48Z qsun $
 */
public class ListCheckPointRuleSelectorAction extends ValueListAction{
	private static final long serialVersionUID = -6480352827706797747L;
	private final CheckPointRuleManager checkPointRuleManager;
	private final DepartmentManager departmentManager;
	private final CodeValueManager codeValueManager;
	private final WfDocStateManager wfDocStateManager;

	private List rule;
	
	private boolean invalid;

	public ListCheckPointRuleSelectorAction(
			CheckPointRuleManager checkPointRuleManager,
			DepartmentManager departmentManager,
			CodeValueManager codeValueManager,
			WfDocStateManager wfDocStateManager) {
		this.checkPointRuleManager = checkPointRuleManager;
		this.departmentManager = departmentManager;
		this.codeValueManager = codeValueManager;
		this.wfDocStateManager = wfDocStateManager;
	}

	public void prepare() throws Exception {
		if (this.rule == null && this.hasIds("checkPointRuleIds")) {
			this.rule = this.checkPointRuleManager.loadAllCheckPointRule(this
					.getIds("checkPointRuleIds"));
		}
	}

	public String execute() throws Exception {
		if (this.isDelete()) {
			delete();
		}
		return SUCCESS;
	}

	public List getRule() {
		return rule;
	}

	public void setRule(List rule) {
		this.rule = rule;
	}

	public String delete() {
		try {
			this.checkPointRuleManager.deleteAllCheckPointRule(this.rule);
			}catch(Exception e){
				this.addActionMessage(this.getText("checkPointRule.delete.error"));
				return ERROR;
			}

		this.addActionMessage(this.getText("checkPointRule.delete.success2"));
		return SUCCESS;
	}

	@Override
	protected String getAdapterName() {
		return "checkPointRuleSelector";
	}

	public List getDepartments() {
		return departmentManager.createSelectDepartments(this
				.getText("select.option.all"));
	}
	
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		if (invalid) {
			map.put("includeInvalid", "placeholder");
		}
		return map;
	}

	public boolean isInvalid() {
		return invalid;
	}

	public void setInvalid(boolean invalid) {
		this.invalid = invalid;
	}

	public List getRuleTypes() {
		return codeValueManager.createSelectCodeValues(
				this.getText("select.option.all"), CodeConstants.CHECK_POINT_TYPE);
	}
	
	public List getDocStates() {
		return wfDocStateManager.createSelectDocStates(this.getText("select.option.all"));
	}
}

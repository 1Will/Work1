package com.yongjun.tdms.presentation.webwork.action.runmaintenance.checkpoint.rule;

import java.util.List;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.service.runmaintenance.checkpoint.CheckPointRuleDetailManager;

/**
 * @author wzou
 * @version $Id: ListCheckPointRuleDetailAction.java 7961 2007-10-23 07:03:11Z qsun $
 */
public class ListCheckPointRuleDetailAction extends ValueListAction {
	private static final long serialVersionUID = 3087495020938455975L;

	private final CheckPointRuleDetailManager checkPointRuleDetailManager;

	private List ruleDetails;

	Long ruleId;

	public ListCheckPointRuleDetailAction(
			CheckPointRuleDetailManager checkPointRuleDetailManager) {
		this.checkPointRuleDetailManager = checkPointRuleDetailManager;
	}

	public void prepare() throws Exception {
		if (this.hasId("rule.id")) {
			ruleId = this.getId("rule.id");
		}
		if (this.ruleDetails == null && this.hasIds("checkPointRuleDetailIds")) {
			this.ruleDetails = this.checkPointRuleDetailManager
					.loadAllCheckPointRuleDetail(this
							.getIds("checkPointRuleDetailIds"));
		}
	}

	public String execute() throws Exception {
		if (this.isDelete()) {
			delete();
		}
		return SUCCESS;
	}

	public List getRuleDetails() {
		return ruleDetails;
	}

	public void setRuleDetails(List ruleDetails) {
		this.ruleDetails = ruleDetails;
	}

	public String delete() {
		try {
		this.checkPointRuleDetailManager
				.deleteAllCheckPointRuleDetail(this.ruleDetails);
		this.addActionMessage(this
				.getText("checkPointRuleDetail.delete.success"));
		} catch (Exception e) {
			this.addActionMessage(this
					.getText("checkPointRuleDetail.delete.error"));
		}
		return SUCCESS;
	}

	@Override
	protected String getAdapterName() {
		return "checkPointRules";
	}

}

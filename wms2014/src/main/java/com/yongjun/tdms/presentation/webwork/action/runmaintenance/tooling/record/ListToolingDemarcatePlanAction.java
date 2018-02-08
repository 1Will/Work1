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

import java.util.ArrayList;
import java.util.List;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.runmaintenance.tooling.record.DemarcatePlan;
import com.yongjun.tdms.model.runmaintenance.tooling.record.DemarcateStatus;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.tooling.record.DemarcatePlanManager;

/**
 * @author qs
 * @version $Id: ListToolingDemarcatePlanAction.java 9264 2007-12-12 08:07:23Z
 *          qsun $
 */
public class ListToolingDemarcatePlanAction extends ValueListAction {
	private static final long serialVersionUID = 8356759949864372881L;

	private final DepartmentManager departmentManager;

	private final DemarcatePlanManager demarcatePlanManager;

	private List<DemarcatePlan> demarcatePlans;

	public ListToolingDemarcatePlanAction(DepartmentManager departmentManager,
			DemarcatePlanManager demarcatePlanManager) {
		this.departmentManager = departmentManager;
		this.demarcatePlanManager = demarcatePlanManager;
	}

	public void prepare() {
		if (this.demarcatePlans == null) {
			if (this.hasIds("demarcatePlanIds")) {
				this.demarcatePlans = demarcatePlanManager
						.loadAllDemarcatePlans(this.getIds("demarcatePlanIds"));
			}
		}
	}

	public String execute() {

		if (this.isDelete()) {
			return delete();
		}
		return SUCCESS;
	}

	@Override
	protected String getAdapterName() {
		return "demarcatePlans";
	}

	public List getDepartments() {
		return departmentManager.createSelectDepartments(this
				.getText("select.option.all"));
	}

	private String delete() {
		try {
			this.demarcatePlanManager.deleteAllDemarcatePlan(demarcatePlans);
		} catch (Exception e) {
			this.addActionMessage(this.getText("demarcatePlan.delete.error"));
			return ERROR;
		}

		this.addActionMessage(this.getText("demarcatePlan.delete.success"));
		return SUCCESS;
	}

	public List<DemarcatePlan> getDemarcatePlans() {
		return demarcatePlans;
	}

	public void setDemarcatePlans(List<DemarcatePlan> demarcatePlans) {
		this.demarcatePlans = demarcatePlans;
	}

	public List<LabelValue> getDemarcateStatus() {
		LabelValue labelValue = new LabelValue(Long.valueOf(-1L).toString(),
				this.getText("select.option.all"));
		LabelValue[] arrays = this.wrapEnum(DemarcateStatus.class);
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		tmp.add(labelValue);
		for (int i = 0; i < arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}

}

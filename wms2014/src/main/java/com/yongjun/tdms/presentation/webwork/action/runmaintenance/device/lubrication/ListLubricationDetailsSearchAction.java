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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.device.lubrication;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.runmaintenance.lubrication.LubricationPlan;
import com.yongjun.tdms.model.runmaintenance.lubrication.LubricationPlanDetail;
import com.yongjun.tdms.model.runmaintenance.lubrication.LubricationPlanStatus;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairModel;
import com.yongjun.tdms.model.runmaintenance.wash.WashDetailResult;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.lubrication.LubricationPlanDetailManager;
import com.yongjun.tdms.service.runmaintenance.lubrication.LubricationPlanManager;


/**
 * @author qs
 * @version $Id: ListLubricationPlanDetailsAction.java 11076 2008-02-25 07:26:14Z zbzhang $
 */
public class ListLubricationDetailsSearchAction extends ValueListAction {
	private static final long serialVersionUID = -3191397178912763839L;

	private final DepartmentManager departmentManager;
	private final LubricationPlanDetailManager lubricationPlanDetailManager;
	private final LubricationPlanManager lubricationPlanManager;

	private LubricationPlanDetail lubricationPlanDetail;
	private List<LubricationPlanDetail> lubricationPlanDetails;
	private LubricationPlan lubrication;
	
	private String planProcFlag;                      //标识[计划]|[实施]
	private String allLubricationPlanDetailId;        //润滑计划明细的所有ID值
	private String allEstimateExecDate;               //润滑计划明细的所有计划执行时间或实际执行日期的非空值
	private String allComment;                        //润滑计划明细的所有备注的非空值
	private String allPlanExePeople;                  //润滑计划明细的所有计划执行人的非空值
	private String allExectPeople;
	private String allLubricationResult;              //润滑计划明细的所有执行结果的非空值
	private String allLubricationOilQty;              //润滑计划明细的所有计划润滑计量或实际润滑计量的非空值
	
	public ListLubricationDetailsSearchAction(DepartmentManager departmentManager,
			LubricationPlanDetailManager lubricationPlanDetailManager,
			LubricationPlanManager lubricationPlanManager) {
		this.departmentManager = departmentManager;
		this.lubricationPlanDetailManager = lubricationPlanDetailManager;
		this.lubricationPlanManager = lubricationPlanManager;
	}

	public void prepare() {
		if (this.hasId("lubrication.id")) {
			this.lubrication = this.lubricationPlanManager.loadLubricationPlan(this.getId("lubrication.id"));
		}
		if (this.hasIds("lubricationDetailIds")) {
			this.lubricationPlanDetails = this.lubricationPlanDetailManager.loadAllLubricationPlanDetail(this.getIds("lubricationDetailIds"));
		}
		
		if (this.hasId("planProcFlag")) {
			this.planProcFlag = request.getParameter("planProcFlag");
			System.out.println(planProcFlag);
		}
//		this.setFirst(false);
	}

	private boolean isSave() {
		return this.hasKey("save");
	}

	public String execute() {
		if (isSave()) {
			return save();
		}
		if (this.isDelete()) {
			return delete();
		}
		return SUCCESS;
	}

	private String delete() {
		try {
			this.lubricationPlanDetailManager
					.deleteAllLubricationPlanDetail(lubricationPlanDetails);
			this.addActionMessage(this
					.getText("lubricationPlan.delete.success"));
		} catch (Exception ex) {
			ex.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}

	@Override
	protected String getAdapterName() {
		if (this.planProcFlag.equals(PreRepairModel.PLAN.toString())) {
			return "lubricationPlanDetailsSearch";
		} else {
			return "lubricationProcDetailsSearch";
		}
		
	}

	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		if (this.hasId("lubrication.id")) {
			if (this.planProcFlag.equals(PreRepairModel.PLAN.toString())) {
				map.put("lubricationPlan.id",this.getId("lubrication.id"));	
			} else {
				map.put("lubricationProc.id",this.getId("lubrication.id"));	
			}
		}
       
		return map;
	}
	
	private String save() {
		if (!StringUtils.isEmpty(request.getParameter("allLubricationPlanDetailId"))) {
			this.allLubricationPlanDetailId = request.getParameter("allLubricationPlanDetailId");
		}
		if (!StringUtils.isEmpty(request.getParameter("allLubricationOilQty"))) {
			this.allLubricationOilQty = request.getParameter("allLubricationOilQty");
		}
		if (!StringUtils.isEmpty(request.getParameter("allEstimateExecDate"))) {
			this.allEstimateExecDate = request.getParameter("allEstimateExecDate");
		}
		if (!StringUtils.isEmpty(request.getParameter("allComment"))) {
			this.allComment = request.getParameter("allComment");
		}
		if (!StringUtils.isEmpty(request.getParameter("allPlanExePeople"))) {
			this.allPlanExePeople = request.getParameter("allPlanExePeople");
		}
		if (!StringUtils.isEmpty(request.getParameter("allLubricationResult"))) {
			this.allLubricationResult = request.getParameter("allLubricationResult");
		}
		if(!StringUtils.isEmpty(request.getParameter("allExectPeople"))){
			this.allExectPeople = request.getParameter("allExectPeople");
		}
		if (this.planProcFlag.equals(PreRepairModel.PLAN.toString())) {
			this.lubricationPlanDetailManager.storeLubricationPlanDetail(allLubricationPlanDetailId, allLubricationOilQty,
					allPlanExePeople, allEstimateExecDate, allComment,allExectPeople);
		} else {
			this.lubricationPlanDetailManager.storeLubricationProcDetail(allLubricationPlanDetailId,
					allEstimateExecDate, allLubricationOilQty, allLubricationResult,allExectPeople);
		}
		this.addActionMessage(this.getText("lubricationplan.save.success"));
		return SUCCESS;
	}

	public List getDepartments() {
		return departmentManager.createSelectDepartments(this
				.getText("select.option.all"));
	}

	public LubricationPlanDetail getLubricationPlan() {
		return lubricationPlanDetail;
	}

	public void setLubricationPlan(LubricationPlanDetail lubricationPlanDetail) {
		this.lubricationPlanDetail = lubricationPlanDetail;
	}

	public List<LubricationPlanDetail> getLubricationPlans() {
		return lubricationPlanDetails;
	}

	public void setLubricationPlans(List<LubricationPlanDetail> lubricationPlanDetails) {
		this.lubricationPlanDetails = lubricationPlanDetails;
	}

	public List getPlanStatus() {
		LabelValue[] arrays = this.wrapEnum(LubricationPlanStatus.class);
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		for (int i = 0; i < arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}

	public LubricationPlan getLubrication() {
		return lubrication;
	}

	public void setLubrication(LubricationPlan lubrication) {
		this.lubrication = lubrication;
	}

	public String getPlanProcFlag() {
		return planProcFlag;
	}

	public void setPlanProcFlag(String planProcFlag) {
		this.planProcFlag = planProcFlag;
	}
}

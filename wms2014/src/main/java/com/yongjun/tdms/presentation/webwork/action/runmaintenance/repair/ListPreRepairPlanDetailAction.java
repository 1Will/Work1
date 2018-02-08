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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.repair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.runmaintenance.repair.BrockenRate;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairModel;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlan;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlanDetail;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairDetailResult;
import com.yongjun.tdms.model.runmaintenance.usualcheck.Check;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.repair.PreRepairPlanDetailManager;
import com.yongjun.tdms.service.runmaintenance.repair.PreRepairPlanManager;

/**
 * @author qs
 * @version $Id: ListPreRepairPlanDetailAction.java 11160 2008-02-29 10:15:24Z zbzhang $
 */
public class ListPreRepairPlanDetailAction extends ValueListAction {
	private static final long serialVersionUID = 4535726288109153957L;
	private final Log logger = LogFactory.getLog(this.getClass());
	
	private final PreRepairPlanDetailManager preRepairPlanDetailManager;
	private final PreRepairPlanManager preRepairPlanManager;
	private final DepartmentManager departmentManager;
	private final CodeValueManager codeValueManager;
	
	private List<PreRepairPlanDetail> preRepairPlanDetails;
	private PreRepairPlan preRepairPlan;
	private PreRepairPlanDetail preRepairPlanDetail;
	private Check check;
	private BrockenRate brocken;
	private String allPreRepairPlanDetailDutyDepartment;                       //计划明细列表中所有的明细ID和责任单位ID
	private String allPreRepairPlanDetailExternalHelp;                         //计划明细列表中所有的明细ID和是否外协的值
	private String allPreRepairPlanDetailDutyPeople;                           //计划明细列表中所有的明细ID和责任人ID
	private String allPreRepairPlanDetailExecPeople;                           //计划明细列表中所有的明细ID和执行人ID，或实施明细列表中所有的明细ID和实际执行人ID
	private String allPreRepairPlanDetailEstimateExecDate;                     //计划明细列表中所有的明细ID和计划预计执行时间的值，或实施明细列表中所有的明细ID和实施执行时间的值
	private String allPreRepairPlanDetailId;                                   //计划明细列表中所有的明细ID
	private String planProcFlag;                                               //标识计划或实施
	private String allPreRepairProcExecResult;                                 //实施明细列表中所有的明细ID和执行结果的值
	private String allPreRepairPlanDetailDutyRepairLevel;                      //计划明细列表中所有明细ID和维修等级的值
	private String toolingDevFlag;                                             //标识是工装或设备
	private String preRepairPlanDetailHistoryIds;                              //预防性维修计划明细的历史纪录ID
	private String addusualCheckIds;                                           //添加日常检查的ids
	private String addBrockenRateListIds;                                      //添加故障记录的ids 
	private String preRepairRuleIds;  
	private String addeasilydamagedSpartIds; //预维标准ids
	
	public ListPreRepairPlanDetailAction(PreRepairPlanDetailManager preRepairPlanDetailManager,
			PreRepairPlanManager preRepairPlanManager,
			DepartmentManager departmentManager,
			CodeValueManager codeValueManager) {
		this.preRepairPlanDetailManager = preRepairPlanDetailManager;
		this.preRepairPlanManager = preRepairPlanManager;
		this.departmentManager = departmentManager;
		this.codeValueManager = codeValueManager;
	}
	public void prepare() {
	
		if (this.hasId("preRepairPlan.id")) {
			this.preRepairPlan = this.preRepairPlanManager.loadPreRepairPlan(this.getId("preRepairPlan.id"));
		}
		if(this.hasId("preRepairPlanDetail.id")) {
			this.preRepairPlanDetail =this.preRepairPlanDetailManager.loadPreRepairPlanDetail(this.getId("preRepairPlanDetail.id"));
		}
		if (null == this.preRepairPlanDetails) {
			if(this.hasId("preRepairPlanDetailIds")) {
				this.preRepairPlanDetails = this.preRepairPlanDetailManager.loadAllPreRepairPlanDetails(this.getIds("preRepairPlanDetailIds"));
			}
		}
		if (this.hasId("planProcFlag")) {
			this.planProcFlag = request.getParameter("planProcFlag");
		}
		if (this.hasId("toolingDevFlag")) {
			this.toolingDevFlag = request.getParameter("toolingDevFlag");
		}
		if (this.hasId("preRepairPlanDetailHistoryIds")) {
			this.preRepairPlanDetailHistoryIds = request.getParameter("preRepairPlanDetailHistoryIds");
		}
		if (null == this.addusualCheckIds) {//给据页面传来的申购单的ids,获得所有的申购单ids,
			if (!StringUtils.isEmpty(request.getParameter("addcheckListIds"))) {
				this.addusualCheckIds = request.getParameter("addcheckListIds");
			}
		}
		if (null == this.addBrockenRateListIds) {//给据页面传来的申购单的ids,获得所有的申购单ids,
			if (!StringUtils.isEmpty(request.getParameter("addBrockenRateListIds"))) {
				this.addBrockenRateListIds = request.getParameter("addBrockenRateListIds");
			}
		}
		if (null == this.preRepairRuleIds) {        //获取页面传来的预维标准id
			if (!StringUtils.isEmpty(request.getParameter("preRepairRuleIds"))) {
				this.preRepairRuleIds = request.getParameter("preRepairRuleIds");
			}
		}
		if (null == this.addeasilydamagedSpartIds) {        //获取页面传来的预维标准id
			if (!StringUtils.isEmpty(request.getParameter("addeasilydamagedSpartIds"))) {
				this.addeasilydamagedSpartIds = request.getParameter("addeasilydamagedSpartIds");
			}
		}
		this.setFirst(false);
	}
	public String execute() {
		if (this.isAdduausalCheck()) {//判断是否添加日常检查单到预防性维修明晰中
			return this.saveAdduausalCheck();
		}
		if (this.isAddbrockenRate()) {//判断是否添加日常检查单到预防性维修明晰中
			return this.saveAddbrockenRate();
		}
		if (this.isAddeasilydamagedSpart()) {//判断是否添加日常检查单到预防性维修明晰中
			return this.saveAddeasilydamagedSpart();
		}
		if (this.isDelete()) {
			return delete();
		}
		if (this.isSave()) {
			return save();
		}
		if (this.isCopy()) {      //判断是否点击了复制历史明细
			copy();
		}
		if (this.isSelectFromPreRepairRule()) {          //从预防性维修标准中创建预维标准计划明细
			return createPreRepairPlanDetailFromPreRepairRule();
		}
		return SUCCESS;
	}
	/**
	 * 判断页面传来的addcheckList变量是否有值，且值是否等于addcheckList
	 * 
	 * @return boolean true 添加新的设备 | false 不添加
	 */
	private boolean isAdduausalCheck() {
		if (!StringUtils.isEmpty(request.getParameter("addcheckList"))) {
			if (request.getParameter("addcheckList").equals("addcheckLists"))
				return true;
		}
		return false;
	}
	
	/**
	 * 判断页面是否点击了"根据从故障率选择"按钮
	 * @return
	 */
	private boolean isAddbrockenRate() {
		if (!StringUtils.isEmpty(request.getParameter("addBrockenRateList"))) {
			if (request.getParameter("addBrockenRateList").equals("addBrockenRateLists"))
				return true;
		}
		return false;
	}
	private boolean isAddeasilydamagedSpart() {
		if (!StringUtils.isEmpty(request.getParameter("easilydamagedSpartList"))) {
			if (request.getParameter("easilydamagedSpartList").equals("easilydamagedSpartLists"))
				return true;
		}
		return false;
	}
	
	/**
	 * 判断页面是否点击"根据预维标准选择"
	 * @return
	 */
	private boolean isSelectFromPreRepairRule() {
		if (!StringUtils.isEmpty(request.getParameter("preRepairRuleSelector"))) {
			if (request.getParameter("preRepairRuleSelector").equals("preRepairRuleSelector"))
				return true;
		}
		return false;
	}
	
	/**
	 * 从预防性维修标准中创建预维标准计划明细
	 * @return SUCCESS
	 */
	public String createPreRepairPlanDetailFromPreRepairRule() {
		this.preRepairPlanDetailManager.storePreRepairPlanDetailFromPreRepairRule(preRepairPlan,preRepairRuleIds);
		return SUCCESS;
	}
	/**
	 * 保存页面传来的所有申购单明细
	 * @return
	 */
	public String saveAdduausalCheck() {
		
		this.preRepairPlanDetailManager.storeUausalCheck( check,preRepairPlan,addusualCheckIds);
		return SUCCESS;
	}
	public String saveAddbrockenRate() {
		
		this.preRepairPlanDetailManager.storeBrockenRate( brocken,preRepairPlan,addBrockenRateListIds);
		return SUCCESS;
	}
	public String saveAddeasilydamagedSpart() {
		this.preRepairPlanDetailManager.storeEasilyDamagedSpare(preRepairPlan,addeasilydamagedSpartIds);
		return SUCCESS;
	}
	/**
	 * 删除选中的预防性维修计划，如果计划被实施，将不能删除
	 * @return
	 */
	public String delete() {
		try {
			this.preRepairPlanDetailManager.deleteAllPreRepairPlamDetail(preRepairPlanDetails,preRepairPlan);
		} catch (Exception e) {
			this.addActionMessage(this.getText("preRepairPlanDetail.delete.error"));
			logger.error("preRepairPlanDetail delete failure!",e);
			return ERROR;
		}
		this.addActionMessage(this.getText("preRepairPlanDetail.delete.success"));
		return SUCCESS;
	}
	public String save() {
		 if (!StringUtils.isEmpty(request.getParameter("allPreRepairPlanDetailId"))) {
			 this.allPreRepairPlanDetailId = request.getParameter("allPreRepairPlanDetailId");
		 }
		 if (!StringUtils.isEmpty(request.getParameter("allPreRepairPlanDetailDutyDepartment"))) {
			 this.allPreRepairPlanDetailDutyDepartment = request.getParameter("allPreRepairPlanDetailDutyDepartment");
		 }
		 if (!StringUtils.isEmpty(request.getParameter("allPreRepairPlanDetailExternalHelp"))) {
			 this.allPreRepairPlanDetailExternalHelp = request.getParameter("allPreRepairPlanDetailExternalHelp");
		 }
		 if (!StringUtils.isEmpty(request.getParameter("allPreRepairPlanDetailDutyPeople"))) {
			 this.allPreRepairPlanDetailDutyPeople = request.getParameter("allPreRepairPlanDetailDutyPeople");
		 }
		 if (!StringUtils.isEmpty(request.getParameter("allPreRepairPlanDetailExecPeople"))) {
			 this.allPreRepairPlanDetailExecPeople = request.getParameter("allPreRepairPlanDetailExecPeople");
		 }
		 if (!StringUtils.isEmpty(request.getParameter("allPreRepairPlanDetailEstimateExecDate"))) {
			 this.allPreRepairPlanDetailEstimateExecDate = request.getParameter("allPreRepairPlanDetailEstimateExecDate");
		 }
		 if (!StringUtils.isEmpty(request.getParameter("allPreRepairProcExecResult"))) {
			 this.allPreRepairProcExecResult = request.getParameter("allPreRepairProcExecResult");
		 }
		 if (!StringUtils.isEmpty(request.getParameter("allPreRepairPlanDetailDutyRepairLevel"))) {
			 this.allPreRepairPlanDetailDutyRepairLevel = request.getParameter("allPreRepairPlanDetailDutyRepairLevel");
		 }
		 if (this.planProcFlag.equals(PreRepairModel.PLAN.toString())) {
			 if (null != allPreRepairPlanDetailDutyDepartment || null != allPreRepairPlanDetailDutyPeople
						|| null != allPreRepairPlanDetailExecPeople || null != allPreRepairPlanDetailEstimateExecDate
						|| null != allPreRepairPlanDetailDutyRepairLevel || null != this.allPreRepairPlanDetailId){
					this.preRepairPlanDetailManager.storePreRepairPlanDetail(allPreRepairPlanDetailDutyDepartment,
							                                                 allPreRepairPlanDetailExternalHelp,
																			 allPreRepairPlanDetailDutyPeople,
																			 allPreRepairPlanDetailExecPeople,
																			 allPreRepairPlanDetailEstimateExecDate,
																			 allPreRepairPlanDetailDutyRepairLevel,
																		     allPreRepairProcExecResult,
																		     allPreRepairPlanDetailId);
			 }
		 } else {
			 if (null != allPreRepairPlanDetailExecPeople || null != allPreRepairPlanDetailEstimateExecDate
					 || null != allPreRepairProcExecResult || null != this.allPreRepairPlanDetailId) {
				 this.preRepairPlanDetailManager.storePreRepairProcDetail(allPreRepairPlanDetailExecPeople,
						 allPreRepairPlanDetailEstimateExecDate,
						 allPreRepairProcExecResult,
						 allPreRepairPlanDetailId);
			 }
		 }
		 return SUCCESS;
	}
	
	public void copy() {
		this.preRepairPlanDetailManager.storePreRepairPlanDetails(preRepairPlan, preRepairPlanDetailHistoryIds);
	}
	public boolean isSave() {
		return this.hasKey("save");
	}
	
	public boolean isCopy() {
		if (!StringUtils.isEmpty(request.getParameter("copy"))) {
			if (request.getParameter("copy").equals("copy"))
				return true;
		}
		return false;
	}
	
	@Override
	protected String getAdapterName() {
		if (this.planProcFlag.equals(PreRepairModel.PLAN.toString())) {
			return "preRepairPlanDetails";
		} else {
			return "preRepairProcDetails";
		}
		
	}
	
	public List<PreRepairPlanDetail> getPreRepairPlanDetails() {
		return preRepairPlanDetails;
	}
	
	public void setPreRepairPlanDetails(
			List<PreRepairPlanDetail> preRepairPlanDetails) {
		this.preRepairPlanDetails = preRepairPlanDetails;
	}

	public PreRepairPlan getPreRepairPlan() {
		return preRepairPlan;
	}

	public void setPreRepairPlan(PreRepairPlan preRepairPlan) {
		this.preRepairPlan = preRepairPlan;
	}
	
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
        if (this.hasId("preRepairPlan.id")){
        	if (this.planProcFlag.equals(PreRepairModel.PLAN.toString())) {
        		map.put("preRepairPlan.id", this.getId("preRepairPlan.id"));
        	} else {
        		map.put("preRepairProc.id", this.getId("preRepairPlan.id"));
        	}
			
		}
		return map;
	}

	/**
	 * 获取所有部门的集合
	 * @return List 部门的集合
	 */
	public List getDepartments() {
		return departmentManager.createSelectDepartments(this
				.getText(""));
	}
	/**
	 * 获取所有维修级别的集合
	 * @return List 维修级别集合
	 */
	public List getRepairLevels() {
		return this.codeValueManager.createSelectCodeValues(this.getText(""), CodeConstants.REPAIR_LEVEL);
	}
	
	public PreRepairPlanDetail getPreRepairPlanDetail() {
		return preRepairPlanDetail;
	}

	public void setPreRepairPlanDetail(PreRepairPlanDetail preRepairPlanDetail) {
		this.preRepairPlanDetail = preRepairPlanDetail;
	}

	public String getPlanProcFlag() {
		return planProcFlag;
	}

	public void setPlanProcFlag(String planProcFlag) {
		this.planProcFlag = planProcFlag;
	}

	public String getAllPreRepairProcExecResult() {
		return allPreRepairProcExecResult;
	}

	public void setAllPreRepairProcExecResult(String allPreRepairProcExecResult) {
		this.allPreRepairProcExecResult = allPreRepairProcExecResult;
	}
	
	/**
	 * 获取实施列表中执行结果集合
	 * @return List 执行结果集合
	 */
	public List<LabelValue> getProcResults() {
		LabelValue[] arrays = this.wrapEnum(PreRepairDetailResult.class);
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		for (int i = 0; i < arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}

	public String getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

	public Check getCheck() {
		return check;
	}

	public void setCheck(Check check) {
		this.check = check;
	}
}

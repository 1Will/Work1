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
package com.yongjun.tdms.presentation.webwork.action.year.repair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairDetailResult;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairModel;
import com.yongjun.tdms.model.year.repair.RepairPlanAndProc;
import com.yongjun.tdms.model.year.repair.RepairPlanAndProcDetail;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.year.repair.RepairPlanAndProcDetailManager;
import com.yongjun.tdms.service.year.repair.RepairPlanAndProcManager;

/**
 * @author qs
 * @version $Id: ListRepairPlanAndProcDetailAction.java 11198 2008-03-05 09:17:12Z zbzhang $
 */
public class ListRepairPlanAndProcDetailAction extends ValueListAction {
	private static final long serialVersionUID = 3144490037543056356L;
	private final RepairPlanAndProcDetailManager repairPlanAndProcDetailManager;
	private final RepairPlanAndProcManager repairPlanAndProcManager;
	private final CodeValueManager codeValueManager;
	private final DepartmentManager departmentManager;
	
	private List<RepairPlanAndProcDetail> repairPlanOrProcDetails;
	private RepairPlanAndProc repairPlanOrProc;
	private String planProcFlag;                                    //大项修计划和实施标识[计划][实施]
	private String toolingDevFlag;                                  //资产标识[工装][设备]
	private String allYearRepairPlanDetailId;                       //大项修计划明细列表的ID的所有值
	private String allYearRepairPlanDetailDepartment;               //大项修计划明细列表的ID和承修单位的所有值
	private String allYearRepairPlanDetailExternalHelp;             //大项修计划明细列表的ID和外协的所有值
	private String allYearRepairPlanDetailTechnicalLevel;           //大项修计划明细列表的ID和技术等级的所有值
	private String allYearRepairPlanDetailCategory;                 //大项修计划明细列表的ID和种类的所有值
	private String allYearRepairPlanDetailPlanRepairDate;           //大项修计划明细列表的ID和维修日期的所有值
	private String allYearRepairPlanDetailPlanBeginDate;            //大项修计划明细列表的ID和计划开工日期的所有值
	private String allYearRepairPlanDetailPlanEndDate;              //大项修计划明细列表的ID和计划完工日期的所有值
	private String allProcExePeople;                                //大项修实施明细列表的ID和执行人的所有值
	private String allProcExecResult;                               //大项修实施明细列表的ID和执行结果的所有值  
	
	public ListRepairPlanAndProcDetailAction(RepairPlanAndProcDetailManager repairPlanAndProcDetailManager,
			RepairPlanAndProcManager repairPlanAndProcManager,
			CodeValueManager codeValueManager,
			DepartmentManager departmentManager) {
		this.repairPlanAndProcDetailManager = repairPlanAndProcDetailManager;
		this.repairPlanAndProcManager = repairPlanAndProcManager;
		this.codeValueManager = codeValueManager;
		this.departmentManager = departmentManager;
	}
	
	public void prepare() {
		if (this.hasId("repairPlanOrProc.id")) {
			this.repairPlanOrProc = this.repairPlanAndProcManager.loadRepairPlanOrProc(this.getId("repairPlanOrProc.id"));
		}
		if (this.hasId("repairPlanOrProcDetailIds")) {
			this.repairPlanOrProcDetails = this.repairPlanAndProcDetailManager.loadAllRepairPlanAndProcDetails(this.getIds("repairPlanOrProcDetailIds"));
		}
		if (this.hasId("planProcFlag")) {
			this.planProcFlag = request.getParameter("planProcFlag");
		}
		if (this.hasId("toolingDevFlag")) {
			this.toolingDevFlag = request.getParameter("toolingDevFlag");
		}
		this.setFirst(false);
	}
	
	public String execute() {
		
		if (this.isSave()) {
			return save();
		}
		if (this.isDelete()) {
			return delete();
		}
		return SUCCESS;
	}
	
	/**
	 * 判断是否点击保存按钮
	 * @return true | false
	 */
	public boolean isSave() {
		return this.hasKey("save");
	}
	
	public String delete() {
		try {
			this.repairPlanAndProcDetailManager.deleteAllYearRepairPlanDetail(repairPlanOrProcDetails,repairPlanOrProc);	
		}catch (Exception e) {
			this.addActionMessage(this.getText("yearRepairPlanDetail.delete.error"));
			//System.out.println("=============" + "dfdsffsffdfds error  error");
			e.printStackTrace();
			return ERROR;
		}
		
		return SUCCESS;
	}
	/**
	 * 根据页面传入的参数，更新大项修计划明细的值
	 * @return
	 */
	public String save() {
		//获取计划明细ID的所有值
		if (!StringUtils.isEmpty(request.getParameter("allYearRepairPlanDetailId"))) {
			this.allYearRepairPlanDetailId = request.getParameter("allYearRepairPlanDetailId");
		}
		//获取计划明细承修单位的所有值
		if (!StringUtils.isEmpty(request.getParameter("allYearRepairPlanDetailDepartment"))) {
			this.allYearRepairPlanDetailDepartment = request.getParameter("allYearRepairPlanDetailDepartment");
		}
        //获取计划明细外协的所有值
		if (!StringUtils.isEmpty(request.getParameter("allYearRepairPlanDetailExternalHelp"))) {
			this.allYearRepairPlanDetailExternalHelp = request.getParameter("allYearRepairPlanDetailExternalHelp");
		}
//		//获取计划明细
//		if (!StringUtils.isEmpty(request.getParameter("allYearRepairPlanDetailTechnicalLevel"))) {
//			this.allYearRepairPlanDetailTechnicalLevel = request.getParameter("allYearRepairPlanDetailTechnicalLevel");
//		}
		//获取计划明细种类的所有值
		if (!StringUtils.isEmpty(request.getParameter("allYearRepairPlanDetailCategory"))) {
			this.allYearRepairPlanDetailCategory = request.getParameter("allYearRepairPlanDetailCategory");
		}
		//获取计划明细计划维修日期所有值
		if (!StringUtils.isEmpty(request.getParameter("allYearRepairPlanDetailPlanRepairDate"))) {
			this.allYearRepairPlanDetailPlanRepairDate = request.getParameter("allYearRepairPlanDetailPlanRepairDate");
		}
		//获取计划明细计划开工日期所有值
		if (!StringUtils.isEmpty(request.getParameter("allYearRepairPlanDetailPlanBeginDate"))) {
			this.allYearRepairPlanDetailPlanBeginDate = request.getParameter("allYearRepairPlanDetailPlanBeginDate");
		}
		//获取计划明细计划完工日期所有值
		if (!StringUtils.isEmpty(request.getParameter("allYearRepairPlanDetailPlanEndDate"))) {
			this.allYearRepairPlanDetailPlanEndDate = request.getParameter("allYearRepairPlanDetailPlanEndDate");
		}
		//获取实施明细实际执行人的所有值
		if (!StringUtils.isEmpty(request.getParameter("allProcExePeople"))) {
			this.allProcExePeople = request.getParameter("allProcExePeople");
		}
		//获取实施明细执行结果的所有值
		if (!StringUtils.isEmpty(request.getParameter("allProcExecResult"))) {
			this.allProcExecResult = request.getParameter("allProcExecResult");
		}
		
		if (this.planProcFlag.equals(PreRepairModel.PLAN.toString())) {        //如果是计划明细,保存计划明细,并设置实施字段的默认值
			this.repairPlanAndProcDetailManager.storeAllRepairPlanDetails(allYearRepairPlanDetailId,
					 allYearRepairPlanDetailDepartment, allYearRepairPlanDetailCategory,
					 allYearRepairPlanDetailPlanRepairDate, allYearRepairPlanDetailPlanBeginDate,
					 allYearRepairPlanDetailPlanEndDate,allYearRepairPlanDetailExternalHelp);
		} else {                                                               //如果是实施明细,保存实施明细 
			this.repairPlanAndProcDetailManager.storeAllRepairProcDetails(allYearRepairPlanDetailId,
					allYearRepairPlanDetailPlanBeginDate, allYearRepairPlanDetailPlanEndDate,
					allProcExePeople, allProcExecResult, allYearRepairPlanDetailPlanRepairDate);
		}
		return SUCCESS;
	}
	@Override
	protected String getAdapterName() {
		if (this.planProcFlag.equals(PreRepairModel.PLAN.toString())) {
			return "yearRepairPlanDetails";
		} else {
			return "yearRepairProcDetails";
		}
	}
	
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
        if (this.hasId("repairPlanOrProc.id")){
        	if (this.planProcFlag.equals(PreRepairModel.PLAN.toString())) {
        		map.put("repairPlanOrProc.id", this.getId("repairPlanOrProc.id"));
        	} else {
        		map.put("repairPlanOrProc.id", this.getId("repairPlanOrProc.id"));
        	}
			
		}
		return map;
	}
	public String getPlanProcFlag() {
		return planProcFlag;
	}

	public void setPlanProcFlag(String planProcFlag) {
		this.planProcFlag = planProcFlag;
	}

	public String getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}
	
	/**
	 * 获取所有的大项修种类
	 * @return  List 大项修种类集合
	 */
	public List getCategorys() {
		return this.codeValueManager.createSelectCodeValues(this.getText(""), CodeConstants.YEAR_REPAIR_CATEGORY);
	}
	
	/**
	 * 获取所有的大项修技术等级
	 * @return List 大项修技术等级集合
	 */
	public List getTechnicalLevels() {
		return this.codeValueManager.createSelectCodeValues(this.getText(""), CodeConstants.YEAR_TECHNICAL_LEVEL);
	}
	
	/**
	 * 获取所有部门的集合
	 * @return List 部门的集合
	 */
	public List getDepartments() {
		return departmentManager.createSelectDepartments(this
				.getText(""));
	}

	public List getProcResults() {
		LabelValue[] arrays = this.wrapEnum(PreRepairDetailResult.class);
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		for (int i = 0; i < arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}
	public RepairPlanAndProc getRepairPlanOrProc() {
		return repairPlanOrProc;
	}

	public void setRepairPlanOrProc(RepairPlanAndProc repairPlanOrProc) {
		this.repairPlanOrProc = repairPlanOrProc;
	}

}

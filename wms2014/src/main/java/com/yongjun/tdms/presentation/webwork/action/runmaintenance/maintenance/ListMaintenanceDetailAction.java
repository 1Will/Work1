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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.maintenance;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.runmaintenance.calibration.CalibrationResultType;
import com.yongjun.tdms.model.runmaintenance.maintenance.Maintenance;
import com.yongjun.tdms.model.runmaintenance.maintenance.MaintenanceDetail;
import com.yongjun.tdms.model.runmaintenance.maintenance.MaintenanceResultType;
import com.yongjun.tdms.model.runmaintenance.maintenance.MaintenanceType;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairModel;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.runmaintenance.maintenance.MaintenanceDetailManager;
import com.yongjun.tdms.service.runmaintenance.maintenance.MaintenanceManager;

/**
 * <p>Title: ListMaintenanceDetailAction
 * <p>Description: 设备保养计划或实施明细列表访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: ListMaintenanceDetailAction.java 11114 2008-02-26 07:10:36Z wzou $
 */
public class ListMaintenanceDetailAction extends ValueListAction {

	private static final long serialVersionUID = 5507763469514378966L;
	
	private final MaintenanceDetailManager maintenanceDetailManager;
	private final MaintenanceManager maintenanceManager;
	private final CodeValueManager codeValueManager;
	
	private Maintenance maintenance;
	private List<MaintenanceDetail> maintenanceDetails;
	private String planProcFlag;                   //计划或实施标识
	
	private String addDeviceIds;                  //新添加的设备ID字符串
	private String allMaintenanceDetailId;        //明细列表中所有的ID值
//	private String allPart;                       //明细列表中所有非空的部位的值，以及其相对应的清洗明细ID值
//	private String allMethod;                     //明细列表中所有非空的方法的值，以及其相对应的清洗明细ID值
//	private String allAppeal;                     //明细列表中所有非空的要求的值，以及其相对应的清洗明细ID值
	private String allDate; 				  	  //计划完成时间或实施完成时间
	private String allDutyPeople;                 //明细列表中所有非空的负责人的值，以及其相对应的保养明细ID值
	private String allResultType;	  			  //计划明细列表中的保养类型的所有值
	private String allResult;	  				  //实施明细列表中的保养结果的所有值
	private String allComment;                    //明细列表中所有非空的备注的值，以及其相对应的保养明细ID值
	
	public ListMaintenanceDetailAction(MaintenanceDetailManager maintenanceDetailManager,
			MaintenanceManager maintenanceManager,
			CodeValueManager codeValueManager) {
		this.maintenanceDetailManager = maintenanceDetailManager;
		this.maintenanceManager = maintenanceManager;
		this.codeValueManager = codeValueManager;
	}
	
	public void prepare() {
		if (this.hasId("maintenance.id")) {
			this.maintenance = this.maintenanceManager.loadMaintenance(this.getId("maintenance.id"));
		}
		if (this.hasIds("maintenanceDetailIds")) {
			this.maintenanceDetails = this.maintenanceDetailManager.loadAllMaintenanceDetails(this.getIds("maintenanceDetailIds"));
		}
		if (null == this.addDeviceIds) {
			if (!StringUtils.isEmpty(request.getParameter("addDeviceIds"))) {
				this.addDeviceIds = request.getParameter("addDeviceIds");
			}
		}
		if (this.hasId("planProcFlag")) {
			this.planProcFlag = request.getParameter("planProcFlag");
		}
		this.setFirst(false);
	}

	public String execute() {
		if (this.isAddMaintenance()) {
			return this.saveAddMaintenance();
		}
		if (this.isSave()) {
			return save();
		}
		if (this.isDelete()) {
			return delete();
		}
		return SUCCESS;
	}
	
	/**
	 * 判断页面传来的addDevice变量是否有值，且值是否等于addDevices
	 * 
	 * @return boolean true 添加新的设备 | false 不添加
	 */
	private boolean isAddMaintenance() {
		if (!StringUtils.isEmpty(request.getParameter("addDevice"))) {
			if (request.getParameter("addDevice").equals("addDevices"))
				return true;
		}
		return false;
	}
	
	/**
	 * 保存新添加的设备为保养计划明细
	 * @return SUCCESS
	 */
	public String saveAddMaintenance() {
		this.maintenanceDetailManager.storeMaintenanceDetail(maintenance,addDeviceIds);
		return SUCCESS;
	}
	
	/**
	 * 判断页面是否点击保存按钮
	 * @return true | false
	 */
	private boolean isSave() {
		return this.hasKey("save");
	}
	
	public String save() {
		if (!StringUtils.isEmpty(request.getParameter("allMaintenanceDetailId"))) {
			this.allMaintenanceDetailId = request.getParameter("allMaintenanceDetailId");
		}
//		if (!StringUtils.isEmpty(request.getParameter("allPart"))) {
//			this.allPart = request.getParameter("allPart");
//		}
//		if (!StringUtils.isEmpty(request.getParameter("allMethod"))) {
//			this.allMethod = request.getParameter("allMethod");
//		}
//		if (!StringUtils.isEmpty(request.getParameter("allAppeal"))) {
//			this.allAppeal = request.getParameter("allAppeal");
//		}
		if (!StringUtils.isEmpty(request.getParameter("allDate"))) {
			this.allDate = request.getParameter("allDate");
		}
		if (!StringUtils.isEmpty(request.getParameter("allDutyPeople"))) {
			this.allDutyPeople = request.getParameter("allDutyPeople");
		}
		if (!StringUtils.isEmpty(request.getParameter("allResult"))) {
			 this.allResult = request.getParameter("allResult");
		}
		if (!StringUtils.isEmpty(request.getParameter("allResultType"))) {
			 this.allResultType = request.getParameter("allResultType");
		}
		if (!StringUtils.isEmpty(request.getParameter("allComment"))) {
			this.allComment = request.getParameter("allComment");
		}
		
		if (this.planProcFlag.equals(PreRepairModel.PLAN.toString())) {
			this.maintenanceDetailManager.storeMaintenanceDetail(
					allMaintenanceDetailId,
//					allPart,
//					allMethod,
//					allAppeal,
					allDate,
					allDutyPeople,
					allResultType,
					allComment);
		} else {
			this.maintenanceDetailManager.storeMaintenanceDetail(
					allMaintenanceDetailId,
					allDate,
					allResult);
		}
		return SUCCESS;
	}
	
	public String delete() {
		try {
			this.maintenanceDetailManager.deleteAllMaintenanceDetails (maintenanceDetails);
		} catch (Exception e) {
					 e.printStackTrace();
				return ERROR;
		  
		}
		this.addActionMessage(this.getText("maintenanceDetails.delete.success"));
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
        if (this.hasId("maintenance.id")){
        	if (this.planProcFlag.equals(PreRepairModel.PLAN.toString())) {
        		map.put("maintenancePlan.id", this.getId("maintenance.id"));
        	} else {
        		map.put("maintenanceProc.id", this.getId("maintenance.id"));
        	}
			
		}
		return map;
	}
	
	@Override
	protected String getAdapterName() {
		if (this.planProcFlag.equals(PreRepairModel.PLAN.toString())) {
			return "maintenancePlanDetail";
		} else {
			return "maintenanceProcDetail";
		}
	}
	
	public List<LabelValue> getProcResults() {
		LabelValue[] arrays = this.wrapEnum(MaintenanceResultType.class);
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		for (int i = 0; i < arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}
	
	public List getResultTypes() {
		return this.codeValueManager.createSelectCodeValues(this.getText(""), CodeConstants.MAINTENANCE_CATEGORY);
	}

	public String getPlanProcFlag() {
		return planProcFlag;
	}

	public void setPlanProcFlag(String planProcFlag) {
		this.planProcFlag = planProcFlag;
	}

	public Maintenance getMaintenance() {
		return maintenance;
	}

	public void setMaintenance(Maintenance maintenance) {
		this.maintenance = maintenance;
	}
}

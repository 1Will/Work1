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


import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.base.workType.WorkType;
import com.yongjun.tdms.model.runmaintenance.fault.FaultRepair;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlanDetail;
import com.yongjun.tdms.model.runmaintenance.repair.RepairManHour;
import com.yongjun.tdms.model.year.repair.RepairPlanAndProcDetail;
import com.yongjun.tdms.service.base.workType.WorkTypeManager;
import com.yongjun.tdms.service.runmaintenance.fault.FaultRepairManager;
import com.yongjun.tdms.service.runmaintenance.repair.PreRepairPlanDetailManager;
import com.yongjun.tdms.service.runmaintenance.repair.RepairManHourManager;
import com.yongjun.tdms.service.year.repair.RepairPlanAndProcDetailManager;

/**
 * <p>Title: EditRepairManHourAction
 * <p>Description: 工时明细页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: EditRepairManHourAction.java 11224 2008-03-09 05:56:50Z zbzhang $
 */
public class EditRepairManHourAction extends PrepareAction {
	private static final long serialVersionUID = 2058677074065365711L;
	private final PreRepairPlanDetailManager preRepairPlanDetailManager;
	private final RepairManHourManager repairManHourManager;
	private final RepairPlanAndProcDetailManager repairPlanAndProcDetailManager;
	private final WorkTypeManager workTypeManager;
	private final FaultRepairManager faultRepairManager;
	
	private PreRepairPlanDetail preRepairPlanDetail;
	private RepairPlanAndProcDetail repairPlanOrProcDetail;
	private RepairManHour repairManHour;
	private FaultRepair faultRepair;
	private WorkType workType;                       //工种
	private String planProcFlag;                    //标识为是计划,还是实施
	private String preYearFlag="";						 //标识为预防性维修，还是大项修
	private String oldManHourNum;                  //未更新前的计划工时数量
	private String oldProcManHourNum;              //未更新前的实际工时数量
	
	public EditRepairManHourAction(PreRepairPlanDetailManager preRepairPlanDetailManager,
			RepairManHourManager repairManHourManager,
			RepairPlanAndProcDetailManager repairPlanAndProcDetailManager,
			WorkTypeManager workTypeManager,
			FaultRepairManager faultRepairManager) {
		this.preRepairPlanDetailManager = preRepairPlanDetailManager;
		this.repairManHourManager = repairManHourManager;
		this.repairPlanAndProcDetailManager = repairPlanAndProcDetailManager;
		this.workTypeManager = workTypeManager;
		this.faultRepairManager = faultRepairManager;
	}
	
	public void prepare() throws Exception {
		if (null == this.repairManHour) {
			if (this.hasId("repairManHour.id")) {
				this.repairManHour = this.repairManHourManager.loadRepairManHour(this.getId("repairManHour.id"));
				if (!this.hasId("workType.id")) {
					this.workType = this.repairManHour.getWorkType();
				}
			} else {
				this.repairManHour = new RepairManHour();
			}
		}
		if(this.hasId("preYearFlag")) {
			this.preYearFlag = request.getParameter("preYearFlag");
			if(this.preYearFlag.equals("YEAR")){               //大项修
				if (this.repairPlanOrProcDetail == null) {
					if (this.hasId("repairPlanOrProcDetail.id")) {
						this.repairPlanOrProcDetail = this.repairPlanAndProcDetailManager.loadRepairPlanAndProcDetail(this
								.getId("repairPlanOrProcDetail.id"));

					}
				}
			} else if (this.preYearFlag.equals("PRE")){           //预防性维修
				if (this.preRepairPlanDetail == null) {
					if (this.hasId("preRepairPlanDetail.id")) {
						this.preRepairPlanDetail = this.preRepairPlanDetailManager.loadPreRepairPlanDetail(this
								.getId("preRepairPlanDetail.id"));
					}
				}
			} else {                                           //故障维修对象
				if (this.hasId("faultRepair.id")) {
					this.faultRepair = this.faultRepairManager.loadFaultRepair(this
							.getId("faultRepair.id"));
				}
			}
		}
		if (this.hasId("planProcFalg")) {
			this.planProcFlag = request.getParameter("planProcFlag");
		}
	}
	
	public String save() {
		boolean isNew = this.repairManHour.isNew();
		
		if (this.preYearFlag.equals("YEAR")){	//设置大项修对象
			repairManHour.setRepairPlanAndProcDetail(repairPlanOrProcDetail);
		} else if (this.preYearFlag.equals("PRE")){     //设置预防性对象
			repairManHour.setPreRepairDetail(preRepairPlanDetail);
		} else {                                  //设置故障维修对象
			repairManHour.setFaultRepair(faultRepair);
		}
		if (!StringUtils.isEmpty(request.getParameter("workType.id"))) {
			this.workType = this.workTypeManager.loadWorkType(this.getId("workType.id"));
			repairManHour.setWorkType(workType);
			repairManHour.setUnitPrice(workType.getUnitPrice());              //复制单价
		}
		this.repairManHourManager.storeRepairManHour(repairManHour);
		if (isNew) {
			 this.addActionMessage(this.getText("repairManHour.add.success"));
			 return NEW;
		 }  else {
				this.addActionMessage(this.getText("repairManHour.edit.success"));
				return SUCCESS;
		}
	}

	public PreRepairPlanDetail getPreRepairPlanDetail() {
		return preRepairPlanDetail;
	}

	public void setPreRepairPlanDetail(PreRepairPlanDetail preRepairPlanDetail) {
		this.preRepairPlanDetail = preRepairPlanDetail;
	}

	public RepairManHour getRepairManHour() {
		return repairManHour;
	}

	public void setRepairManHour(RepairManHour repairManHour) {
		this.repairManHour = repairManHour;
	}

	public String getPlanProcFlag() {
		return planProcFlag;
	}

	public void setPlanProcFlag(String planProcFlag) {
		this.planProcFlag = planProcFlag;
	}

	public String getPreYearFlag() {
		return preYearFlag;
	}

	public void setPreYearFlag(String preYearFlag) {
		this.preYearFlag = preYearFlag;
	}

	public RepairPlanAndProcDetail getRepairPlanOrProcDetail() {
		return repairPlanOrProcDetail;
	}

	public void setRepairPlanOrProcDetail(
			RepairPlanAndProcDetail repairPlanOrProcDetail) {
		this.repairPlanOrProcDetail = repairPlanOrProcDetail;
	}

	public WorkType getWorkType() {
		return workType;
	}

	public void setWorkType(WorkType workType) {
		this.workType = workType;
	}

	public FaultRepair getFaultRepair() {
		return faultRepair;
	}

	public void setFaultRepair(FaultRepair faultRepair) {
		this.faultRepair = faultRepair;
	}

}

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

import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.runmaintenance.fault.FaultRepair;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlanDetail;
import com.yongjun.tdms.model.runmaintenance.repair.RepairItem;
import com.yongjun.tdms.model.year.repair.RepairPlanAndProcDetail;
import com.yongjun.tdms.service.runmaintenance.fault.FaultRepairManager;
import com.yongjun.tdms.service.runmaintenance.repair.PreRepairPlanDetailManager;
import com.yongjun.tdms.service.runmaintenance.repair.RepairItemManager;
import com.yongjun.tdms.service.year.repair.RepairPlanAndProcDetailManager;


/**
 * <p>Title: EditRepairFeeAction
 * <p>Description: 预防性维修明细PANEL维护访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: EditRepairItemAction.java 11224 2008-03-09 05:56:50Z zbzhang $
 */
public class EditRepairItemAction extends PrepareAction {
	private static final long serialVersionUID = 3925338555544698857L;

	private final RepairItemManager repairItemManager;
	private final PreRepairPlanDetailManager preRepairPlanDetailManager;
	private final UserManager userManager;
	private final RepairPlanAndProcDetailManager repairPlanAndProcDetailManager;
	private final FaultRepairManager faultRepairManager;
	
	private RepairItem repairItem;
	protected PreRepairPlanDetail preRepairPlanDetail;               //预防性维修对象
	private RepairPlanAndProcDetail repairPlanOrProcDetail;          //大项修对象
	private FaultRepair faultRepair;                                 //故障维修对象
	
	private String planProcFlag;                         //标识为计划，还是实施
	private String preYearFlag="";						 //标识为预防性维修，还是大项修

	public EditRepairItemAction(
			RepairItemManager repairItemManager,
			PreRepairPlanDetailManager preRepairPlanDetailManager,
			UserManager userManager,
			RepairPlanAndProcDetailManager repairPlanAndProcDetailManager,
			FaultRepairManager faultRepairManager) {
		this.repairItemManager = repairItemManager;
		this.preRepairPlanDetailManager = preRepairPlanDetailManager;
		this.userManager = userManager;
		this.repairPlanAndProcDetailManager = repairPlanAndProcDetailManager;
		this.faultRepairManager = faultRepairManager;
	}

	public void prepare() throws Exception {
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
		
		if (this.repairItem == null) {
			if (this.hasId("repairItem.id")) {
				repairItem = this.repairItemManager
						.loadRepairItem(this.getId("repairItem.id"));
			} else {
				repairItem = new RepairItem();
			}
		}
		if (this.hasId("planProcFalg")) {
			this.planProcFlag = request.getParameter("planProcFlag");
		}
	}

	public String save() {
		boolean isNew = this.repairItem.isNew();
		
		if (!StringUtils.isEmpty(request.getParameter("execPeople.id"))) {             //获取执行人
			repairItem.setExecPeople(this.userManager.loadUser(this.getId("execPeople.id")));
		}
		
		if (this.preYearFlag.equals("YEAR")){	         //设置大项修明细对象
			repairItem.setRepairPlanAndProcDetail(this.repairPlanOrProcDetail);
		} else if (this.preYearFlag.equals("PRE")) {    //设置预防性维修明细对象
			repairItem.setPreRepairDetail(this.preRepairPlanDetail);
		} else {
			repairItem.setFaultRepair(faultRepair);      //设置故障维修对象
		}
		
		repairItemManager.storeRepairItem(repairItem);
		if (isNew) {
			this.addActionMessage(this.getText(
					"repairItem.add.success"));
			return NEW;
		} else {
			this.addActionMessage(this.getText(
					"repairItem.edit.success"));
			return SUCCESS;
		}
	}

	public String delete() {
		this.addActionMessage(this.getText(
				"checkPointRuleManager.invalid.success"));
		return SUCCESS;
	}

	public PreRepairPlanDetail getPreRepairPlanDetail() {
		return preRepairPlanDetail;
	}

	public void setPreRepairPlanDetail(PreRepairPlanDetail preRepairPlanDetail) {
		this.preRepairPlanDetail = preRepairPlanDetail;
	}

	public RepairItem getRepairItem() {
		return repairItem;
	}

	public void setRepairItem(RepairItem repairItem) {
		this.repairItem = repairItem;
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

	public FaultRepair getFaultRepair() {
		return faultRepair;
	}

	public void setFaultRepair(FaultRepair faultRepair) {
		this.faultRepair = faultRepair;
	}


}

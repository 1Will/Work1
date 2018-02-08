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


import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.base.financeType.FeeSourceType;
import com.yongjun.tdms.model.runmaintenance.fault.FaultRepair;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlan;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlanDetail;
import com.yongjun.tdms.model.runmaintenance.repair.RepairFee;
import com.yongjun.tdms.model.year.repair.RepairPlanAndProc;
import com.yongjun.tdms.model.year.repair.RepairPlanAndProcDetail;
import com.yongjun.tdms.service.runmaintenance.fault.FaultRepairManager;
import com.yongjun.tdms.service.runmaintenance.repair.PreRepairPlanDetailManager;
import com.yongjun.tdms.service.runmaintenance.repair.PreRepairPlanManager;
import com.yongjun.tdms.service.runmaintenance.repair.RepairFeeManager;
import com.yongjun.tdms.service.year.repair.RepairPlanAndProcDetailManager;
import com.yongjun.tdms.service.year.repair.RepairPlanAndProcManager;

/**
 * <p>Title: EditRepairFeeAction
 * <p>Description: 预防性维修计划费用维护访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: EditRepairFeeAction.java 11224 2008-03-09 05:56:50Z zbzhang $
 */
public class EditRepairFeeAction extends PrepareAction {
	private static final long serialVersionUID = 2446974446888871657L;
	
	private final PreRepairPlanDetailManager preRepairPlanDetailManager;
	private final RepairFeeManager repairFeeManager;
	private final PreRepairPlanManager preRepairPlanManager;
	private final RepairPlanAndProcDetailManager repairPlanAndProcDetailManager;
	private final RepairPlanAndProcManager repairPlanAndProcManager;
	private final FaultRepairManager faultRepairManager;
	
	private RepairFee repairFee;
	private PreRepairPlanDetail preRepairPlanDetail;
	private RepairPlanAndProcDetail repairPlanOrProcDetail;
	private RepairPlanAndProc repairPlanOrProc;
	private PreRepairPlan preRepairPlan;
	private FaultRepair faultRepair;                 //故障维修对象
	private String planProcFlag;                    //标识为是计划,还是实施
	private String preYearFlag="";					//标识为预防性维修，还是大项修
	private String oldPlanFee;                     //未更新前的计划费用额
	private String oldProcFee;                     //未更新前的实际费用额
	private String planAllFee;					   //计划的总费用
	
	public EditRepairFeeAction(
			PreRepairPlanDetailManager preRepairPlanDetailManager,
			RepairFeeManager repairFeeManager,
			PreRepairPlanManager preRepairPlanManager,
			RepairPlanAndProcDetailManager repairPlanAndProcDetailManager,
			RepairPlanAndProcManager repairPlanAndProcManager,
			FaultRepairManager faultRepairManager) {
		this.preRepairPlanDetailManager = preRepairPlanDetailManager;
		this.repairFeeManager = repairFeeManager;
		this.preRepairPlanManager = preRepairPlanManager;
		this.repairPlanAndProcDetailManager = repairPlanAndProcDetailManager;
		this.repairPlanAndProcManager = repairPlanAndProcManager;
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
		
		if(this.hasId("repairPlanOrProc.id")) {
			System.out.println("repairPlanOrProc.id=="+this
					.getId("repairPlanOrProc.id"));
			this.repairPlanOrProc =this.repairPlanAndProcManager
				.loadRepairPlanOrProc(this
						.getId("repairPlanOrProc.id"));
			System.out.println("this.repairPlanOrProc=="+this.repairPlanOrProc.getId());
		}
		if(this.hasId("preRepairPlan.id")) {
			this.preRepairPlan =this.preRepairPlanManager
				.loadPreRepairPlan(this
						.getId("preRepairPlan.id"));
		}
		if (this.repairFee == null) {
			if (this.hasId("repairFee.id")) {
				repairFee = this.repairFeeManager.loadRepairFee(this
						.getId("repairFee.id"));

			} else {
				repairFee = new RepairFee();
			}
		}
		if (this.hasId("planProcFalg")) {
			this.planProcFlag = request.getParameter("planProcFlag");
		}
	}

	public String save() {
		boolean isNew = this.repairFee.isNew();
		
		if(this.preYearFlag.equals("YEAR")){	
			repairFee.setRepairPlanAndProcDetail(this.repairPlanOrProcDetail);
		}else if(this.preYearFlag.equals("PRE")){
			repairFee.setPreRepairDetail(this.preRepairPlanDetail);
		} else {
			repairFee.setFaultRepair(faultRepair);
		}
		if(this.preYearFlag.equals("YEAR")){
//			if(this.repairPlanOrProc.getPlanAllFee()!=null){
//				this.planAllFee=this.repairPlanOrProc.getPlanAllFee().toString();
//			}
			this.oldPlanFee = request.getParameter("oldPlanFee");
			this.repairFeeManager.storeRepairFee(repairPlanOrProcDetail, repairFee, oldPlanFee);
		} else if(this.preYearFlag.equals("PRE")){
//			if(this.preRepairPlan.getPlanAllFee()!=null){
//				this.planAllFee=this.preRepairPlan.getPlanAllFee().toString();
//			}
			this.oldPlanFee = request.getParameter("oldPlanFee");
			this.repairFeeManager.storeRepairFee(preRepairPlanDetail, repairFee, oldPlanFee);
		} else {
			this.oldPlanFee = request.getParameter("oldPlanFee");
			this.repairFeeManager.storeRepairFee(faultRepair, repairFee, oldPlanFee);
		}
	
		if (isNew) {
			this.addActionMessage(this.getText("repairFee.add.success"));
			return NEW;
		} else {
			this.addActionMessage(this.getText("repairFee.edit.success"));
			return SUCCESS;
		}
	}

	public PreRepairPlanDetail getPreRepairPlanDetail() {
		return preRepairPlanDetail;
	}

	public void setPreRepairPlanDetail(PreRepairPlanDetail preRepairPlanDetail) {
		this.preRepairPlanDetail = preRepairPlanDetail;
	}

	public RepairFee getRepairFee() {
		return repairFee;
	}

	public void setRepairFee(RepairFee repairFee) {
		this.repairFee = repairFee;
	}
	
	public List<LabelValue> getSourceTypes() {
		LabelValue[] arrays = this.wrapEnum(FeeSourceType.class);
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		for (int i = 0; i < arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
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

	public PreRepairPlan getPreRepairPlan() {
		return preRepairPlan;
	}

	public void setPreRepairPlan(PreRepairPlan preRepairPlan) {
		this.preRepairPlan = preRepairPlan;
	}

	public RepairPlanAndProc getRepairPlanOrProc() {
		return repairPlanOrProc;
	}

	public void setRepairPlanOrProc(RepairPlanAndProc repairPlanOrProc) {
		this.repairPlanOrProc = repairPlanOrProc;
	}

	public FaultRepair getFaultRepair() {
		return faultRepair;
	}

	public void setFaultRepair(FaultRepair faultRepair) {
		this.faultRepair = faultRepair;
	}
	
	
}

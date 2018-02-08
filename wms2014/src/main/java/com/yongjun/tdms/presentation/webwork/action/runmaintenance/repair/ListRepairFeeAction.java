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

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
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
 * <p>Title:  ListRepairFeeAction
 * <p>Description: 预防性维修计划费用页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: ListRepairFeeAction.java 11224 2008-03-09 05:56:50Z zbzhang $
 */
public class ListRepairFeeAction extends ValueListAction{
	private static final long serialVersionUID = 7619322913025747896L;
	private final PreRepairPlanDetailManager preRepairPlanDetailManager;
	private final RepairFeeManager repairFeeManager;
	private final PreRepairPlanManager preRepairPlanManager;
	private final RepairPlanAndProcDetailManager repairPlanAndProcDetailManager;
	private final RepairPlanAndProcManager repairPlanAndProcManager;
	private final FaultRepairManager faultRepairManager;
	
	private PreRepairPlanDetail preRepairPlanDetail;
	private RepairPlanAndProcDetail repairPlanOrProcDetail;
	private List<RepairFee> repairFees;
	private FaultRepair faultRepair;
	
	private String planProcFlag;                    //标识为是计划,还是实施
	private PreRepairPlan preRepairPlan;
	private RepairPlanAndProc repairPlanOrProc;
	private String allRepairFeeProcFee;            //维修费用列表的所有维修费用ID和实际费用的所有值
	private String allRepairFeeId;                 //维修费用列表的所有维修费用ID
	private String preYearFlag="PRE";						 //标识为预防性维修，还是大项修
	
	public ListRepairFeeAction(PreRepairPlanDetailManager preRepairPlanDetailManager,
			RepairFeeManager repairFeeManager,
			PreRepairPlanManager preRepairPlanManager,
			RepairPlanAndProcDetailManager repairPlanAndProcDetailManager,
			RepairPlanAndProcManager repairPlanAndProcManager,
			FaultRepairManager faultRepairManager){
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
			if(this.preYearFlag.equals("YEAR")){						//获取大项修对象
				if(this.hasId("repairPlanOrProcDetail.id")) {
					this.repairPlanOrProcDetail =this.repairPlanAndProcDetailManager.loadRepairPlanAndProcDetail(this.getId("repairPlanOrProcDetail.id"));
				}
			} else if (this.preYearFlag.equals("PRE")){                //获取预防性维修对象
				if(this.hasId("preRepairPlanDetail.id")) {
					this.preRepairPlanDetail =this.preRepairPlanDetailManager.loadPreRepairPlanDetail(this.getId("preRepairPlanDetail.id"));
				}
			} else {                                                   //获取故障维修对象
				if (this.hasId("faultRepair.id")) {
					this.faultRepair = this.faultRepairManager.loadFaultRepair(this.getId("faultRepair.id"));
				}
			}
		}
		if(this.hasId("preRepairPlan.id")) {
			this.preRepairPlan =this.preRepairPlanManager.loadPreRepairPlan(this.getId("preRepairPlan.id"));
		}
		
		if(this.hasId("repairPlanOrProc.id")) {
			this.repairPlanOrProc =this.repairPlanAndProcManager.loadRepairPlanOrProc(this.getId("repairPlanOrProc.id"));
		}
		if (null == repairFees) {
			if(this.hasId("feeIds")) {
				this.repairFees = this.repairFeeManager.loadAllRepairFees(this.getIds("feeIds"));
			}
		}
		if (this.hasId("planProcFalg")) {
			this.planProcFlag = request.getParameter("planProcFlag");
		}
		this.setFirst(false);
	}
	
	public String execute() {
		if (this.isDelete()) {
			return delete();
		}
		if(this.isSave()) {
			return save();
		}
		return SUCCESS;
	}
	public String delete() {
		if(this.preYearFlag.equals("YEAR")){
			this.repairFeeManager.deleteAllRepairFee(repairFees,repairPlanOrProcDetail);
		} else if(this.preYearFlag.equals("PRE")){
			this.repairFeeManager.deleteAllRepairFee(repairFees, preRepairPlanDetail);
		} else {
			this.repairFeeManager.deleteAllRepairFee(repairFees, faultRepair);
		}
		return SUCCESS;
	}

	private boolean isSave() {
		return this.hasKey("save");
	}
	
	public String save() {
		if (!StringUtils.isEmpty(request.getParameter("allRepairFeeId"))) {
			this.allRepairFeeId = request.getParameter("allRepairFeeId");
		}
		if (!StringUtils.isEmpty(request.getParameter("allRepairFeeProcFee"))) {
			this.allRepairFeeProcFee = request.getParameter("allRepairFeeProcFee");
		}
		if (this.preYearFlag.equals("YEAR")) {	
			this.repairFeeManager.storeRepairFee(repairPlanOrProcDetail.getId(), allRepairFeeId, allRepairFeeProcFee);
		} else {
			this.repairFeeManager.storeRepairFee(preRepairPlan.getId(),preRepairPlanDetail.getId(),allRepairFeeId, allRepairFeeProcFee);
		}
		
		return SUCCESS;
		
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

	public PreRepairPlan getPreRepairPlan() {
		return preRepairPlan;
	}

	public void setPreRepairPlan(PreRepairPlan preRepairPlan) {
		this.preRepairPlan = preRepairPlan;
	}

	public String getPreYearFlag() {
		return preYearFlag;
	}

	public void setPreYearFlag(String preYearFlag) {
		this.preYearFlag = preYearFlag;
	}

	public RepairPlanAndProc getRepairPlanOrProc() {
		return repairPlanOrProc;
	}

	public void setRepairPlanOrProc(RepairPlanAndProc repairPlanOrProc) {
		this.repairPlanOrProc = repairPlanOrProc;
	}

	public RepairPlanAndProcDetail getRepairPlanOrProcDetail() {
		return repairPlanOrProcDetail;
	}

	public void setRepairPlanOrProcDetail(
			RepairPlanAndProcDetail repairPlanOrProcDetail) {
		this.repairPlanOrProcDetail = repairPlanOrProcDetail;
	}

	@Override
	protected String getAdapterName() {
		if(this.preYearFlag.equals("YEAR")){
			return "yearRepairFees";
		} else if(this.preYearFlag.equals("PRE")){
			return "preRepairFees";
		} else {
			return "faultRepairFees";
		}
	}
	
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		if(this.preYearFlag.equals("YEAR")){
			if(this.hasId("repairPlanOrProcDetail.id")) {
				map.put("yearRepairPlanOrProcDetail.id",this.getId("repairPlanOrProcDetail.id"));
			}
		} else if(this.preYearFlag.equals("PRE")){
			if(this.hasId("preRepairPlanDetail.id")) {
				map.put("preRepairPlanDetail.id",this.getId("preRepairPlanDetail.id"));
			}
		} else {
			if(this.hasId("faultRepair.id")) {
				map.put("faultRepair.id",this.getId("faultRepair.id"));
			}
		}
		return map;
	}

	public FaultRepair getFaultRepair() {
		return faultRepair;
	}

	public void setFaultRepair(FaultRepair faultRepair) {
		this.faultRepair = faultRepair;
	}

}

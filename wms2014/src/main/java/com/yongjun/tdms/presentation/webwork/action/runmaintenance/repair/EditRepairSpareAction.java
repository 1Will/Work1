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

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlanDetail;
import com.yongjun.tdms.model.runmaintenance.repair.RepairSpare;
import com.yongjun.tdms.model.year.repair.RepairPlanAndProcDetail;
import com.yongjun.tdms.service.asset.spare.SpareManager;
import com.yongjun.tdms.service.runmaintenance.repair.PreRepairPlanDetailManager;
import com.yongjun.tdms.service.runmaintenance.repair.RepairSpareManager;
import com.yongjun.tdms.service.year.repair.RepairPlanAndProcDetailManager;

/**
 * <p>Title: EditRepairFeeAction
 * <p>Description: 预防性维修计划备件维护访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: EditRepairSpareAction.java 10484 2008-01-18 12:29:09Z wzou $
 */
public class EditRepairSpareAction extends PrepareAction {
	private static final long serialVersionUID = 1846030494263200416L;
	private final PreRepairPlanDetailManager preRepairPlanDetailManager;
	private final RepairSpareManager repairSpareManager;
	private final SpareManager spareManager;
	private final RepairPlanAndProcDetailManager repairPlanAndProcDetailManager;
	
	private RepairSpare repairSpare;
	private PreRepairPlanDetail preRepairPlanDetail;
	private RepairPlanAndProcDetail repairPlanOrProcDetail;
	private Spare spare;
	private String planProcFlag;                    //标识为是计划,还是实施
	private String oldPlanUsedNum;                  //未更新前的计划使用数量
	private String oldProcUsedNum;                  //未更新前的实际使用数量
	private String preYearFlag="";						 //标识为预防性维修，还是大项修
	
	public EditRepairSpareAction(
		PreRepairPlanDetailManager preRepairPlanDetailManager,
		RepairSpareManager repairSpareManager,
		SpareManager spareManager,
		RepairPlanAndProcDetailManager repairPlanAndProcDetailManager) {
		this.preRepairPlanDetailManager = preRepairPlanDetailManager;
		this.repairSpareManager = repairSpareManager;
		this.spareManager = spareManager;
		this.repairPlanAndProcDetailManager = repairPlanAndProcDetailManager;
	}
	
	public void prepare() throws Exception {
		if(this.hasId("preYearFlag")) {
			this.preYearFlag = request.getParameter("preYearFlag");
			if(this.preYearFlag.equals("YEAR")){							//在此多判断一次，以预防以后增加共用
					if (this.repairPlanOrProcDetail == null) {
						if (this.hasId("repairPlanOrProcDetail.id")) {
							this.repairPlanOrProcDetail = this.repairPlanAndProcDetailManager.loadRepairPlanAndProcDetail(this
									.getId("repairPlanOrProcDetail.id"));

						}
					}
				}else{
					if (this.preRepairPlanDetail == null) { 
						if (this.hasId("preRepairPlanDetail.id")) {
							this.preRepairPlanDetail = this.preRepairPlanDetailManager.loadPreRepairPlanDetail(this
									.getId("preRepairPlanDetail.id"));
						}
					}
				}
			}
		if (this.repairSpare == null) {
			if (this.hasId("repairSpare.id")) {
				repairSpare = this.repairSpareManager
						.loadRepairSpare(this.getId("repairSpare.id"));
			} else {
				repairSpare = new RepairSpare();
			}
		}
		if (this.hasId("planProcFalg")) {
			this.planProcFlag = request.getParameter("planProcFlag");
		}
	}
	public String save() {
		boolean isNew = this.repairSpare.isNew();
		if(this.preYearFlag.equals("YEAR")){	
			repairSpare.setRepairPlanAndProcDetail(repairPlanOrProcDetail);
		}else{
			repairSpare.setPreRepairDetail(this.preRepairPlanDetail);
		}
		if (!StringUtils.isEmpty(request.getParameter("spare.id"))) {
			this.spare = this.spareManager.loasSpare(this.getId("spare.id"));
			repairSpare.setSpare(spare);
		}
//		if (!StringUtils.isEmpty(request.getParameter("oldPlanUsedNum"))) {
//			this.oldPlanUsedNum = request.getParameter("oldPlanUsedNum");
//		}
//		if (!StringUtils.isEmpty(request.getParameter("oldProcUsedNum")) && this.planProcFlag.equals(PreRepairModel.PROC.toString())) {
//			this.oldProcUsedNum = request.getParameter("oldProcUsedNum");
//		}
//		repairSpareManager.storeRepairSpare(preRepairPlanDetail, repairSpare,oldPlanUsedNum, oldProcUsedNum );
		repairSpareManager.storeRepairSpare(repairSpare);
		if (isNew) {
			this.addActionMessage(this.getText(
					"repairSpare.add.success",
					Arrays.asList(new Object[] { repairSpare.getSpare().getName() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText(
					"repairSpare.edit.success",
					Arrays.asList(new Object[] { repairSpare.getSpare().getName() })));
			return SUCCESS;
		}
	}

	public PreRepairPlanDetail getPreRepairPlanDetail() {
		return preRepairPlanDetail;
	}

	public void setPreRepairPlanDetail(PreRepairPlanDetail preRepairPlanDetail) {
		this.preRepairPlanDetail = preRepairPlanDetail;
	}

	public RepairSpare getRepairSpare() {
		return repairSpare;
	}

	public void setRepairSpare(RepairSpare repairSpare) {
		this.repairSpare = repairSpare;
	}

	public Spare getSpare() {
		return spare;
	}

	public void setSpare(Spare spare) {
		this.spare = spare;
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
	
}

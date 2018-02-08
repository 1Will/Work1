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
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlanDetail;
import com.yongjun.tdms.model.runmaintenance.repair.RepairItem;
import com.yongjun.tdms.model.year.repair.RepairPlanAndProcDetail;
import com.yongjun.tdms.service.runmaintenance.fault.FaultRepairManager;
import com.yongjun.tdms.service.runmaintenance.repair.PreRepairPlanDetailManager;
import com.yongjun.tdms.service.runmaintenance.repair.RepairItemManager;
import com.yongjun.tdms.service.year.repair.RepairPlanAndProcDetailManager;

/**
 * <p>Title:  ListRepairItemAction
 * <p>Description: 预防性维修详细panel页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: ListRepairItemAction.java 11224 2008-03-09 05:56:50Z zbzhang $
 */
public class ListRepairItemAction extends ValueListAction{
	private static final long serialVersionUID = 9009688975072260462L;
	private final PreRepairPlanDetailManager preRepairPlanDetailManager;
	private final RepairItemManager repairItemManager;
	private final RepairPlanAndProcDetailManager repairPlanAndProcDetailManager;
	private final FaultRepairManager faultRepairManager;
	
	private PreRepairPlanDetail preRepairPlanDetail;
	private RepairPlanAndProcDetail repairPlanOrProcDetail;
	private FaultRepair faultRepair;
	private List<RepairItem> repairItems;
	private String planProcFlag;                     //标识为计划，还是实施
	private String allProcExecPeople;                //维修详细列表中所有维修详细ID和所有实际执行人的值
	private String allRepairItemProcCompleteDate;    //维修详细列表中所有维修详细ID和所有实际完成日期的值
	private String allRepairItemComment;             //维修详细列表中所有维修详细ID和所有备注的值
	private String allRepairItemId;                  //维修详细列表中所有维修详细ID
	private String preYearFlag="PRE";						 //标识为预防性维修、大项修、故障维修
	
	public ListRepairItemAction(PreRepairPlanDetailManager preRepairPlanDetailManager,
			RepairItemManager repairItemManager,
			RepairPlanAndProcDetailManager repairPlanAndProcDetailManager,
			FaultRepairManager faultRepairManager){
		this.preRepairPlanDetailManager = preRepairPlanDetailManager;
		this.repairItemManager = repairItemManager;
		this.repairPlanAndProcDetailManager = repairPlanAndProcDetailManager;
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
		if(this.hasId("itemIds")) {
			this.repairItems = this.repairItemManager.loadAllRepairItems(this.getIds("itemIds"));
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
		if (this.isSave()) {
			return this.save();
		}
		return SUCCESS;
	}
	public String delete() {
		this.repairItemManager.deleteAllRepairItem(repairItems);
		// 计划明细维修部位根据维修明细统计时修改
		//this.repairItemManager.deleteAllRepairItem(preRepairPlanDetail,repairItems);
		return SUCCESS;
	}

	private boolean isSave() {
		return this.hasKey("save");
	}
	
	public String save() {
		if (!StringUtils.isEmpty(request.getParameter("allRepairItemId"))) {
			this.allRepairItemId = request.getParameter("allRepairItemId");
		}
		if (!StringUtils.isEmpty(request.getParameter("allProcExecPeople"))) {
			this.allProcExecPeople = request.getParameter("allProcExecPeople");
		}
		if (!StringUtils.isEmpty(request.getParameter("allRepairItemProcCompleteDate"))) {
			this.allRepairItemProcCompleteDate = request.getParameter("allRepairItemProcCompleteDate");
		}
		if (!StringUtils.isEmpty(request.getParameter("allRepairItemComment"))) {
			this.allRepairItemComment = request.getParameter("allRepairItemComment");
		}
		this.repairItemManager.storeRepairItem(allRepairItemId, allProcExecPeople,
				allRepairItemProcCompleteDate, allRepairItemComment);
		return SUCCESS;
	}
	public List<RepairItem> getRepairItems() {
		return repairItems;
	}

	public void setRepairItems(List<RepairItem> repairItems) {
		this.repairItems = repairItems;
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

	public RepairPlanAndProcDetail getRepairPlanOrProcDetail() {
		return repairPlanOrProcDetail;
	}

	public void setRepairPlanOrProcDetail(
			RepairPlanAndProcDetail repairPlanOrProcDetail) {
		this.repairPlanOrProcDetail = repairPlanOrProcDetail;
	}

	public String getPreYearFlag() {
		return preYearFlag;
	}

	public void setPreYearFlag(String preYearFlag) {
		this.preYearFlag = preYearFlag;
	}

	@Override
	protected String getAdapterName() {
		if(this.preYearFlag.equals("YEAR")){
			return "yearRepairItems";
		} else if (this.preYearFlag.equals("PRE")){
			return "preRepairItems";
		} else {
			return "faultRepairItems";
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

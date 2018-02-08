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
import com.yongjun.tdms.model.runmaintenance.repair.RepairTool;
import com.yongjun.tdms.model.year.repair.RepairPlanAndProcDetail;
import com.yongjun.tdms.service.runmaintenance.fault.FaultRepairManager;
import com.yongjun.tdms.service.runmaintenance.repair.PreRepairPlanDetailManager;
import com.yongjun.tdms.service.runmaintenance.repair.RepairToolManager;
import com.yongjun.tdms.service.year.repair.RepairPlanAndProcDetailManager;
/**
 * <p>Title: ListRepairToolAction
 * <p>Description: 维修工具页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: ListRepairToolAction.java 11224 2008-03-09 05:56:50Z zbzhang $
 */
public class ListRepairToolAction extends ValueListAction {
	private static final long serialVersionUID = 8682050313008405067L;
	private final PreRepairPlanDetailManager preRepairPlanDetailManager;
	private final RepairToolManager repairToolManager;
	private final RepairPlanAndProcDetailManager repairPlanAndProcDetailManager;
	private final FaultRepairManager faultRepairManager;
	
	private PreRepairPlanDetail preRepairPlanDetail;
	private RepairPlanAndProcDetail repairPlanOrProcDetail;
	private List<RepairTool> repairTools;
	private FaultRepair faultRepair;
	private String planProcFlag;                    //标识为是计划,还是实施
	private String allRepairToolId;                 //维修工具列表中维修工具ID所有值
	private String allRepairToolProcUseNum;         //为序工具列表中维修工具ID所有值和实际使用数量的值
	private String preYearFlag="PRE";						 //标识为预防性维修，还是大项修
	
	public ListRepairToolAction(PreRepairPlanDetailManager preRepairPlanDetailManager,
			RepairToolManager repairToolManager,
			RepairPlanAndProcDetailManager repairPlanAndProcDetailManager,
			FaultRepairManager faultRepairManager) {
		this.preRepairPlanDetailManager = preRepairPlanDetailManager;
		this.repairToolManager = repairToolManager;
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
		
		if (this.hasIds("repairToolIds")) {
			this.repairTools = this.repairToolManager.loadAllRepairTools(this.getIds("repairToolIds"));
		}
		if (this.hasId("planProcFalg")) {
			this.planProcFlag = request.getParameter("planProcFlag");
		}
		this.setFirst(false);
	}
	/**
	 * 判断如果点击删除按钮，则删除选中的维修工具集合
	 * @return SUCCESS
	 */
	public String execute() {
		if (this.isDelete()) {
			return delete();
		}
		if (this.isSave()) {
			return save();
		}
		return SUCCESS;
	}
	/**
	 * 删除选中的维修工具集合
	 * @return SUCCESS
	 */
	public String delete(){
		this.repairToolManager.deleteAllRepairTools(this.repairTools);
		return SUCCESS;
	}
	
	private boolean isSave() {
		return this.hasKey("save");
	}
	
	public String save() {
		if (!StringUtils.isEmpty(request.getParameter("allRepairToolId"))) {
			this.allRepairToolId = request.getParameter("allRepairToolId");
		}
		if (!StringUtils.isEmpty(request.getParameter("allRepairToolProcUseNum"))) {
			this.allRepairToolProcUseNum = request.getParameter("allRepairToolProcUseNum");
		}
		this.repairToolManager.storeRepairTool(allRepairToolId, allRepairToolProcUseNum);
		return SUCCESS;
		
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

	public List<RepairTool> getRepairTools() {
		return repairTools;
	}

	public void setRepairTools(List<RepairTool> repairTools) {
		this.repairTools = repairTools;
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

	@Override
	protected String getAdapterName() {
		if(this.preYearFlag.equals("YEAR")){
			return "yearRepairTools";
		} else if(this.preYearFlag.equals("PRE")){
			return "preRepairTools";
		} else {
			return "faultRepairTools";
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

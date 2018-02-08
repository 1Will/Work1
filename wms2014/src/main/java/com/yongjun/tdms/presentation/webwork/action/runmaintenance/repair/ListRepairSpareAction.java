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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.runmaintenance.fault.FaultRepair;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlanDetail;
import com.yongjun.tdms.model.runmaintenance.repair.RepairSpare;
import com.yongjun.tdms.model.year.repair.RepairPlanAndProcDetail;
import com.yongjun.tdms.service.runmaintenance.fault.FaultRepairManager;
import com.yongjun.tdms.service.runmaintenance.repair.PreRepairPlanDetailManager;
import com.yongjun.tdms.service.runmaintenance.repair.RepairSpareManager;
import com.yongjun.tdms.service.year.repair.RepairPlanAndProcDetailManager;

/**
 * <p>Title:  ListRepairSpareAction
 * <p>Description: 预防性维修计划备件panel页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: ListRepairSpareAction.java 11224 2008-03-09 05:56:50Z zbzhang $
 */
public class ListRepairSpareAction extends ValueListAction {
	private static final long serialVersionUID = -5844329063111346144L;

	private final Log log = LogFactory.getLog(getClass());

	private final PreRepairPlanDetailManager preRepairPlanDetailManager;
	private final RepairSpareManager repairSpareManager;
	private final RepairPlanAndProcDetailManager repairPlanAndProcDetailManager;
	private final FaultRepairManager faultRepairManager;

	private PreRepairPlanDetail preRepairPlanDetail;
	private RepairPlanAndProcDetail repairPlanOrProcDetail;
	private FaultRepair faultRepair;
	private List<RepairSpare> repairSpare;
	private String addSpareIds;
	private static final String ADD_SPARES = "addSpares";
	private String alterPlanUsedNum;//	更改计划使用数量
	private String planProcFlag;                    //标识为是计划,还是实施
	private String preYearFlag="PRE";						 //标识为预防性维修，还是大项修

	public ListRepairSpareAction(PreRepairPlanDetailManager preRepairPlanDetailManager,
			RepairSpareManager repairSpareManager,
			RepairPlanAndProcDetailManager repairPlanAndProcDetailManager,
			FaultRepairManager faultRepairManager) {
		this.preRepairPlanDetailManager = preRepairPlanDetailManager;
		this.repairSpareManager = repairSpareManager;
		this.repairPlanAndProcDetailManager = repairPlanAndProcDetailManager;
		this.faultRepairManager = faultRepairManager;
	}

	public void prepare() {
		if(this.hasId("preYearFlag")) {
			this.preYearFlag = request.getParameter("preYearFlag");
			if(this.preYearFlag.equals("YEAR")){						//在此多判断一次，以预防以后增加共用
				if(this.hasId("repairPlanOrProcDetail.id")) {
					this.repairPlanOrProcDetail =this.repairPlanAndProcDetailManager.loadRepairPlanAndProcDetail(this.getId("repairPlanOrProcDetail.id"));
				}
			} else if (this.preYearFlag.equals("PRE")){
				if(this.hasId("preRepairPlanDetail.id")) {
					this.preRepairPlanDetail =this.preRepairPlanDetailManager.loadPreRepairPlanDetail(this.getId("preRepairPlanDetail.id"));
				}
			} else{
				if (this.hasId("faultRepair.id")) {
					this.faultRepair = this.faultRepairManager.loadFaultRepair(this.getId("faultRepair.id"));
				}
			}
		}

		if (this.hasId("spareItemIds")) {
			this.repairSpare = this.repairSpareManager
					.loadRepairSpares(this.getIds("spareItemIds"));
		}
		if (null == this.addSpareIds) {
			if (!StringUtils.isEmpty(request.getParameter("addSpareIds"))) {
				this.addSpareIds = request.getParameter("addSpareIds");
				log.debug("spareIds " + addSpareIds);
			}
		}
		if (null == alterPlanUsedNum) {
			if (!StringUtils.isEmpty(request.getParameter("saveSpareIds"))) {	//saveSpareIds为页面提交的保存内容
				this.alterPlanUsedNum = request.getParameter("saveSpareIds");
			}
		}
		if (this.hasId("planProcFlag")) {
			this.planProcFlag = request.getParameter("planProcFlag");
		}
		this.setFirst(false);

	}

	public String execute() {
		if (this.isDelete()) {
			this.delete();
		}
		if (this.isAddSpares()) {
			this.saveAddSpares();
		}
		if(this.isSaveSpare()){
			this.saveSpares();
		}
		return SUCCESS;
	}

	/**
	 * 判断页面传来的addSpare变量是否有值，且值是否等于ADD_SPARES
	 * 
	 * @return boolean true 添加备件|false 不添加
	 */
	private boolean isAddSpares() {
		if (!StringUtils.isEmpty(request.getParameter("addSpares"))) {
			if (request.getParameter("addSpares").equals(ADD_SPARES)) {
				return true;
			}
		}
		return false;
	}

	private boolean isSaveSpare() {
		if(this.hasKey("save")){
			if (!StringUtils.isEmpty(request.getParameter("saveSpareIds"))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 保存新添加的备件
	 * 
	 */
	
	private String saveSpares(){
		this.repairSpareManager.storeRepairSpare(this.alterPlanUsedNum,this.planProcFlag);
		this.addActionMessage(this.getText("spare.installPlace.success"));
		return SUCCESS;
	}
	
	public void saveAddSpares() {
		if(this.preYearFlag.equals("YEAR")){	//保存大项修的维修备件
			this.repairSpareManager.storeRepairSpare(repairPlanOrProcDetail,
					this.addSpareIds);
		}else if(this.preYearFlag.equals("PRE")){       //保存预防性维修的维修备件                    
			this.repairSpareManager.storeRepairSpare(preRepairPlanDetail,
					this.addSpareIds);
		} else {                                       //保存故障维修的维修备件
			this.repairSpareManager.storeRepairSpare(faultRepair, this.addSpareIds);
		}
		this.addActionMessage(this.getText("spare.add.success"));
	}

	public void delete() {
		this.repairSpareManager.deleteAllRepairSpare(repairSpare);
//		this.repairSpareManager.deleteAllRepairSpare(repairSpare,preRepairPlanDetail);
		this.addActionMessage(this.getText("spare.delete.success"));
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

	public String getAlterPlanUsedNum() {
		return alterPlanUsedNum;
	}

	public void setAlterPlanUsedNum(String alterPlanUsedNum) {
		this.alterPlanUsedNum = alterPlanUsedNum;
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

	public List<RepairSpare> getRepairSpare() {
		return repairSpare;
	}

	public void setRepairSpare(List<RepairSpare> repairSpare) {
		this.repairSpare = repairSpare;
	}

	@Override
	protected String getAdapterName() {
		if(this.preYearFlag.equals("YEAR")){
			return "yearRepairSpares";
		} else if(this.preYearFlag.equals("PRE")) {
			return "preRepairSpares";
		} else {
			return "faultRepairSpares";
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

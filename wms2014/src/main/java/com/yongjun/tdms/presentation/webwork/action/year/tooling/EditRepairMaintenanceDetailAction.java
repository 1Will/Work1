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
package com.yongjun.tdms.presentation.webwork.action.year.tooling;

import java.util.Arrays;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.year.tooling.RepairMaintenanceDetail;
import com.yongjun.tdms.model.year.tooling.quarterPlan.QuarterPlan;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlan;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlanDetail;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlanDetailCategory;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.year.tooling.RepairMaintenanceDetailManager;
import com.yongjun.tdms.service.year.tooling.quarterPlan.QuarterPlanManager;
import com.yongjun.tdms.service.year.tooling.yearPlan.YearPlanDetailManager;
import com.yongjun.tdms.service.year.tooling.yearPlan.YearPlanManager;

/**
 * 
 * <p>Title: EditRepairMaintenanceDetailAction
 * <p>Description: 维修保养明细页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $
 */
public class EditRepairMaintenanceDetailAction extends PrepareAction {
	private static final long serialVersionUID = -2262824381523558000L;
	
	private final YearPlanDetailManager repairMaintenanceDetailManager;
	private final YearPlanManager yearPlanManager;
	private final DeviceCardManager deviceCardManager;
	private final QuarterPlanManager quarterPlanManager;
	
	//年度计划
	private YearPlan yearPlan;
	//维修保养明细
	private YearPlanDetail repairMaintenanceDetail;
	//工装
	private DeviceCard tooling;
	//季度计划
	private QuarterPlan quarterPlan;
    //年度计划与季度计划标识[YEAR_PLAN(年度计划)][QUARTER_PLAN(季度计划)]
	private String yearQuarterFlag = "YEAR_PLAN";

	public EditRepairMaintenanceDetailAction(YearPlanDetailManager repairMaintenanceDetailManager,
			YearPlanManager yearPlanManager,
			DeviceCardManager deviceCardManager,
			QuarterPlanManager quarterPlanManager) {
		this.repairMaintenanceDetailManager = repairMaintenanceDetailManager;
		this.yearPlanManager = yearPlanManager;
		this.deviceCardManager = deviceCardManager;
		this.quarterPlanManager = quarterPlanManager;
	}
	/**
	 * 为其他方法准备数据
	 */
	public void prepare() throws Exception {
		if (this.hasId("yearPlan.id")) {           //如果请求中包含"yearPlan.id",则根据"yearPlan.id"获取年度计划对象
			this.yearPlan = this.yearPlanManager.loadYearPlan(this.getId("yearPlan.id"));
		}
		if (null == repairMaintenanceDetail) {
			if (this.hasId("repairMaintenanceDetail.id")) {
		 	   //如果请求中有"repairMaintenanceDetail.id",则根据"repairMaintenanceDetail.id"获取维修保养明细
				this.repairMaintenanceDetail = this.repairMaintenanceDetailManager.loadYearPlanDetail(this.getId("repairMaintenanceDetail.id"));
			    if (!this.hasId("tooling.id")) {
			    	this.tooling = this.repairMaintenanceDetail.getTooling();
			    }
			} else {   //如果请求中没有"repairMaintenanceDetail.id",则创建维修保养明细
				this.repairMaintenanceDetail = new YearPlanDetail();
			}
		}
		if (this.hasId("quarterPlan.id")) {               //如果请求中包含"quarterPlan.id",则根据"quarterPlan.id"获取季度度计划对象
			this.quarterPlan = this.quarterPlanManager.loadQuarterPlan(this.getId("quarterPlan.id"));
		}
		if (this.hasId("yearQuarterFlag")) {          //根据请求中的"yearQuarterFlag"参数的值,来设置年度计划与季度计划标识
			this.yearQuarterFlag = request.getParameter("yearQuarterFlag");
		}

	}
	
	/**
	 * 如果点击保存按钮，保存维修保养明细
	 * @return
	 */
    public String save() {
    	boolean isNew = this.repairMaintenanceDetail.isNew();
    	
    	if (this.hasId("tooling.id")) {               //设置工装
    		this.tooling = this.deviceCardManager.loadDevice(this.getId("tooling.id"));
    		//设置工装id
    		this.repairMaintenanceDetail.setTooling(tooling);
    		//设置工装名称
    		this.repairMaintenanceDetail.setName(tooling.getName());
    		//设置工装图号
    		this.repairMaintenanceDetail.setGraphNo(tooling.getDeviceNo());
    		//关联单位
    		if(tooling.getCalUnit()!=null){
    			this.repairMaintenanceDetail.setCalUnit(tooling.getCalUnit());
    		}
    		//设置工装类别
    		if (null != tooling.getToolingType()) {
    			this.repairMaintenanceDetail.setToolingCategory(tooling.getToolingType());
    			this.repairMaintenanceDetail.setCategoryName(tooling.getToolingType().getValue());
    		}
    		//设置工装明细类别
    		if (null != tooling.getToolingTypeDetail()) {
    			this.repairMaintenanceDetail.setDetailCategoryName(tooling.getToolingTypeDetail().getName());
    		}
    		//设置工装型号
    		this.repairMaintenanceDetail.setModel(tooling.getModel());
    		//设置工装规格
    		this.repairMaintenanceDetail.setSpecification(tooling.getSpecification());
    		
    	}
		if (this.yearQuarterFlag.equals("YEAR_PLAN")) {     //如果是年度计划
			this.repairMaintenanceDetail.setYearPlan(yearPlan);    //设置维修保养明细关联的年度计划
		} 
//		else {                                           //如果是季度计划
//			repairMaintenanceDetail.setQuarterPlan(this.quarterPlan);  //设置维修保养明细关联的季度计划
//		}
		//设置明细类别[维修保养]
		this.repairMaintenanceDetail.setDetailType(YearPlanDetailCategory.REPAIR_MAINTENANCE);
		this.repairMaintenanceDetail.setUnitPrice(null);
		this.repairMaintenanceDetail.setNumber(null);
    	this.repairMaintenanceDetailManager.storeYearPlanDetail(this.repairMaintenanceDetail);
    	
		if (isNew) {
			this.addActionMessage(this.getText("repairMaintenanceDetail.add.success", Arrays
					.asList(new Object[] { repairMaintenanceDetail.getTooling().getName() })));

			return NEW;
		} else {
			this.addActionMessage(this.getText("repairMaintenanceDetail.edit.success", Arrays
					.asList(new Object[] {repairMaintenanceDetail.getTooling().getName() })));
			return SUCCESS;
		}
    }
    
	public YearPlan getYearPlan() {
		return yearPlan;
	}

	public void setYearPlan(YearPlan yearPlan) {
		this.yearPlan = yearPlan;
	}
	
	public DeviceCard getTooling() {
		return tooling;
	}
	
	public void setTooling(DeviceCard tooling) {
		this.tooling = tooling;
	}
	
	public QuarterPlan getQuarterPlan() {
		return quarterPlan;
	}
	
	public void setQuarterPlan(QuarterPlan quarterPlan) {
		this.quarterPlan = quarterPlan;
	}
	
	public String getYearQuarterFlag() {
		return yearQuarterFlag;
	}
	
	public void setYearQuarterFlag(String yearQuarterFlag) {
		this.yearQuarterFlag = yearQuarterFlag;
	}
	public YearPlanDetail getRepairMaintenanceDetail() {
		return repairMaintenanceDetail;
	}
	public void setRepairMaintenanceDetail(YearPlanDetail repairMaintenanceDetail) {
		this.repairMaintenanceDetail = repairMaintenanceDetail;
	}

}

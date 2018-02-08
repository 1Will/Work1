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
import java.util.List;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.year.tooling.TechAlterDetail;
import com.yongjun.tdms.model.year.tooling.quarterPlan.QuarterPlan;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlan;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlanDetail;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlanDetailCategory;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.base.codevalue.ToolingTypeManager;
import com.yongjun.tdms.service.year.tooling.TechAlterDetailManager;
import com.yongjun.tdms.service.year.tooling.quarterPlan.QuarterPlanManager;
import com.yongjun.tdms.service.year.tooling.yearPlan.YearPlanDetailManager;
import com.yongjun.tdms.service.year.tooling.yearPlan.YearPlanManager;

/**
 * <p>Title: EditTechAlterDetailAction
 * <p>Description:  技术改造明细页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public class EditTechAlterDetailAction extends PrepareAction {

	private static final long serialVersionUID = -113580903782170427L;
	
	private final YearPlanDetailManager techAlterDetailManager;
	private final YearPlanManager yearPlanManager;
	//private final ToolingTypeManager toolingTypeManager;
	private final DeviceCardManager deviceCardManager;
	private final QuarterPlanManager quarterPlanManager;
	
	//技术改造
	private YearPlanDetail techAlterDetail;
	//年度计划
	private YearPlan yearPlan;
	//工装
	private DeviceCard tooling;
	//季度计划
	private QuarterPlan quarterPlan;
    //年度计划与季度计划标识[YEAR_PLAN(年度计划)][QUARTER_PLAN(季度计划)]
	private String yearQuarterFlag = "YEAR_PLAN";

	public EditTechAlterDetailAction(YearPlanDetailManager techAlterDetailManager,
			YearPlanManager yearPlanManager,
			DeviceCardManager deviceCardManager,
			QuarterPlanManager quarterPlanManager) {
		this.techAlterDetailManager = techAlterDetailManager;
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
		if (null == techAlterDetail) {
			if (this.hasId("techAlterDetail.id")) {
		 	   //如果请求中有"repairMaintenanceDetail.id",则根据"repairMaintenanceDetail.id"获取维修保养明细
				this.techAlterDetail = this.techAlterDetailManager.loadYearPlanDetail(this.getId("techAlterDetail.id"));
			    if (!this.hasId("tooling.id")) {
			    	this.tooling = this.techAlterDetail.getTooling();
			    }
			} else {   //如果请求中没有"repairMaintenanceDetail.id",则创建维修保养明细
				this.techAlterDetail = new YearPlanDetail();
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
    	boolean isNew = this.techAlterDetail.isNew();
    	
//    	if (this.hasId("category.id")) {               //设置工装类别
//    		this.techAlterDetail.setToolingType(this.toolingTypeManager.loadToolingType(this.getId("category.id")));
//    	}
    	if (this.hasId("tooling.id")) {               //设置工装
    		this.tooling = this.deviceCardManager.loadDevice(this.getId("tooling.id"));
    		this.techAlterDetail.setTooling(tooling);
    		//拷贝工装图号
    		this.techAlterDetail.setGraphNo(tooling.getDeviceNo());
    		//拷贝工装名称
    		this.techAlterDetail.setName(tooling.getName());
    		if(tooling.getCalUnit()!=null){
    			techAlterDetail.setCalUnit(tooling.getCalUnit());
    		}
    		//拷贝工装类别
    		if (null != tooling.getToolingType()) {
    			this.techAlterDetail.setToolingCategory(tooling.getToolingType());
    			this.techAlterDetail.setCategoryName(tooling.getToolingType().getValue());
    		}
    		//设置工装明细类别
    		if (null != tooling.getToolingTypeDetail()) {
    			this.techAlterDetail.setDetailCategoryName(tooling.getToolingTypeDetail().getName());
    		}
    		//设置工装单位
    		if(tooling.getCalUnit()!=null){
    			techAlterDetail.setCalUnit(tooling.getCalUnit());
    		}
    		//拷贝工装型号
    		this.techAlterDetail.setModel(tooling.getModel());
    		//拷贝工装规格
    		this.techAlterDetail.setSpecification(tooling.getSpecification());
    		
    	}
		if (this.yearQuarterFlag.equals("YEAR_PLAN")) {     //如果是年度计划
			this.techAlterDetail.setYearPlan(yearPlan);    //设置技术改造明细关联的年度计划
		} 
//		else {                                           //如果是季度计划
//			techAlterDetail.setQuarterPlan(this.quarterPlan);  //设置技术改造明细关联的季度计划
//		}
		//设置明细类别[技术改造]
		this.techAlterDetail.setDetailType(YearPlanDetailCategory.TECH_ALTER);
		this.techAlterDetail.setUnitPrice(null);
		this.techAlterDetail.setNumber(null);
    	this.techAlterDetailManager.storeYearPlanDetail(this.techAlterDetail);
    	
   		if (isNew) {
			this.addActionMessage(this.getText("techAlterDetail.add.success", Arrays
					.asList(new Object[] {  })));

			return NEW;
		} else {
			this.addActionMessage(this.getText("techAlterDetail.edit.success", Arrays
					.asList(new Object[] { })));
			return SUCCESS;
		}
    }
    
	/**
	 * 获取工装类别集合
	 * @return List 工装类别集合
	 */
//	public List getCategorys() {
//		return this.toolingTypeManager.loadAllToolingTypes();
//	}
	public YearPlan getYearPlan() {
		return yearPlan;
	}

	public void setYearPlan(YearPlan yearPlan) {
		this.yearPlan = yearPlan;
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
	public DeviceCard getTooling() {
		return tooling;
	}
	public void setTooling(DeviceCard tooling) {
		this.tooling = tooling;
	}
	public YearPlanDetail getTechAlterDetail() {
		return techAlterDetail;
	}
	public void setTechAlterDetail(YearPlanDetail techAlterDetail) {
		this.techAlterDetail = techAlterDetail;
	}

}

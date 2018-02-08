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
package com.yongjun.tdms.presentation.webwork.action.year.device.runmaintainPlan;

import java.util.List;
import java.util.Map;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.year.device.runmaintainPlan.DailyRepair;
import com.yongjun.tdms.model.year.device.runmaintainPlan.RunmaintainPlanDetail;
import com.yongjun.tdms.service.year.device.runmaintainPlan.DailyRepairManager;
import com.yongjun.tdms.service.year.device.runmaintainPlan.RunmaintainPlanDetailManager;

/**
 * 
 * <p>Title: ListDailyRepairAction
 * <p>Description: 运维计划的日常维修页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public class ListDailyRepairAction extends ValueListAction {
	private static final long serialVersionUID = -2790076348087085769L;
	
	private final RunmaintainPlanDetailManager runmaintainPlanDetailManager;
	private final DailyRepairManager dailyRepairManager;
	
	//运维计划明细
	private RunmaintainPlanDetail runmaintainPlanDetail;
	//日常维修集合
	private List<DailyRepair> dailyRepairs;

	public ListDailyRepairAction(RunmaintainPlanDetailManager runmaintainPlanDetailManager,
			DailyRepairManager dailyRepairManager) {
		this.runmaintainPlanDetailManager = runmaintainPlanDetailManager;
		this.dailyRepairManager = dailyRepairManager;
	}
	
	/**
	 * 为其他方法执行准备数据
	 */
	public void prepare() {
		if (this.hasId("runmaintainPlanDetail.id")) {
			//如果请求中包含"runmaintainPlanDetail.id",则根据"runmaintainPlanDetail.id"获取运维计划明细对象
			this.runmaintainPlanDetail = this.runmaintainPlanDetailManager.loadRunmaintainPlanDetail(this.getId("runmaintainPlanDetail.id"));
		}
		if (this.hasIds("dailyRepairIds")) {
			//如果请求中包含"dailyRepairIds",则根据"dailyRepairIds"获取日常维修集合
			this.dailyRepairs = this.dailyRepairManager.loadAllDailyRepairs(this.getIds("dailyRepairIds"));
		}
		this.setFirst(false);
	}
	
	public String execute() {
		//如果页面点击的删除按钮,则执行删除操作
		if (this.isDelete()) {
			return delete();
		}
		return SUCCESS;
	}
	
	/**
	 * 删除页面选中的日常维修明细
	 * @return SUCCESS
	 */
	public String delete() {
		this.dailyRepairManager.deleteAllDailyRepairs(this.runmaintainPlanDetail,this.dailyRepairs);
		return SUCCESS;
	}
	
	/**
	 * 得到 <i>valueList</i>中配置的，查询hql的ID
	 */
	@Override
	protected String getAdapterName() {
		return "dailyRepairs";
	}

	/**
	 * 往查询日常维修的hql语句中设置运维计划明细的ID值
	 */
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		if (this.hasId("runmaintainPlanDetail.id")) {
			map.put("runmaintainPlanDetail.id",this.runmaintainPlanDetail.getId());
		}
		return map;
	}
	
	public RunmaintainPlanDetail getRunmaintainPlanDetail() {
		return runmaintainPlanDetail;
	}

	public void setRunmaintainPlanDetail(RunmaintainPlanDetail runmaintainPlanDetail) {
		this.runmaintainPlanDetail = runmaintainPlanDetail;
	}

}

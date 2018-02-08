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

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.year.device.runmaintainPlan.RunmaintainPlan;
import com.yongjun.tdms.model.year.device.runmaintainPlan.RunmaintainPlanDetail;
import com.yongjun.tdms.service.year.device.runmaintainPlan.RunmaintainPlanDetailManager;
import com.yongjun.tdms.service.year.device.runmaintainPlan.RunmaintainPlanManager;

/**
 * 
 * <p>Title: ListRunmaintainPlanDetailAction
 * <p>Description: 年度运维计划明细列表页面访问控制类类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $
 */
public class ListRunmaintainPlanDetailAction extends ValueListAction {
	private static final long serialVersionUID = 6168166638243456393L;
	
	private final RunmaintainPlanManager runmaintainPlanManager;
	private final RunmaintainPlanDetailManager runmaintainPlanDetailManager;

	//年度运维计划
	private RunmaintainPlan runmaintainPlan;
	//年度运维计划明细集合
	private List<RunmaintainPlanDetail> runmaintainPlanDetail;
    //用","分割的设备ID的字符串
	private String newDeviceIds;                                 
	
	public ListRunmaintainPlanDetailAction(RunmaintainPlanManager runmaintainPlanManager, 
			RunmaintainPlanDetailManager runmaintainPlanDetailManager) {
		this.runmaintainPlanManager = runmaintainPlanManager;
		this.runmaintainPlanDetailManager = runmaintainPlanDetailManager;
	}
	
	/**
	 * 为其他方法执行准备数据
	 */
	public void prepare() {
		if (this.hasId("runmaintainPlan.id")) {
			//如果请求中包含"runmaintainPlan.id",则根据"runmaintainPlan.id"获取设备年度运维计划对象
			this.runmaintainPlan = this.runmaintainPlanManager.loadRunmaintainPlan(this.getId("runmaintainPlan.id"));
		}
		if (this.hasIds("runmaintainPlanDetailIds")) {
			//如果请求中包含"runmaintainPlanDetailIds",则根据"runmaintainPlanDetailIds"获取设备年度运维计划明细集合   
			this.runmaintainPlanDetail = this.runmaintainPlanDetailManager.loadAllRunmaintainPlanDetails(this.getIds("runmaintainPlanDetailIds"));
		}
		if (null == this.newDeviceIds) {      //从请求中获取新添加的设备ID"addDeviceIds"
			if (!StringUtils.isEmpty(request.getParameter("addDeviceIds"))) {
				this.newDeviceIds = request.getParameter("addDeviceIds");
			}
		}
		this.setFirst(false);
	}
	
	public String execute() {
		//如果页面点击的删除按钮,则执行删除操作
		if (this.isDelete()) {
			return delete();
		}
		//如果页面点击新建按钮，则执行保存新添加的设备为运维计划明细
		if(this.isAddDevice()) {
			return saveAddDeviceForRunmaintainPlanDetail(this.runmaintainPlan, newDeviceIds);
		}
		return SUCCESS;
	}
	
	/**
	 * 删除页面选中的设备年度维护计划明细
	 * @return SUCCESS
	 */
	public String delete() {
		this.runmaintainPlanDetailManager.deleteAllRunmaintainPlanDetails(runmaintainPlan,this.runmaintainPlanDetail);
		return SUCCESS;
	}
	
	/**
	 * 判断页面传来的addDevice变量是否有值，且值是否等于addDevices
	 * 
	 * @return boolean true 添加新的设备 | false 不添加
	 */
	private boolean isAddDevice() {
		if (!StringUtils.isEmpty(request.getParameter("addDevice"))) {
			if (request.getParameter("addDevice").equals("addDevices"))
				return true;
		}
		return false;
	}
	
	/**
	 * 保存新添加的运维计划明细
	 * @param runmaintainPlan 运维计划
	 * @param addDeviceIds 设备ID的字符串
	 * @return SUCCESS
	 */
	public String saveAddDeviceForRunmaintainPlanDetail(RunmaintainPlan runmaintainPlan, 
			String addDeviceIds) {
		this.runmaintainPlanDetailManager.storeRunmaintainPlanDetail(this.runmaintainPlan,newDeviceIds);
		return SUCCESS;
	}
	
	/**
	 * 得到 <i>valueList</i>中配置的，查询hql的ID
	 */
	@Override
	protected String getAdapterName() {
		return "deviceRunmaintainPlanDetails";
	}

	/**
	 * 往查询运维计划明细的hql语句中设置运维计划的ID值
	 */
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		if (this.hasId("runmaintainPlan.id")) {
			map.put("runmaintainPlan.id",this.runmaintainPlan.getId());
		}
		return map;
	}
	public RunmaintainPlan getRunmaintainPlan() {
		return runmaintainPlan;
	}

	public void setRunmaintainPlan(RunmaintainPlan runmaintainPlan) {
		this.runmaintainPlan = runmaintainPlan;
	}

}

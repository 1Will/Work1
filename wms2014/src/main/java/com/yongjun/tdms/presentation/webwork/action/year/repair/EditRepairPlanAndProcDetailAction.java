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
package com.yongjun.tdms.presentation.webwork.action.year.repair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.util.DateUtil;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairDetailResult;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairModel;
import com.yongjun.tdms.model.year.repair.RepairPlanAndProc;
import com.yongjun.tdms.model.year.repair.RepairPlanAndProcDetail;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.year.repair.RepairPlanAndProcDetailManager;
import com.yongjun.tdms.service.year.repair.RepairPlanAndProcManager;
/**
 * <p>Title: EditRepairPlanAndProcDetailItem
 * <p>Description: 大项修计划明细页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: EditRepairPlanAndProcDetailAction.java 11198 2008-03-05 09:17:12Z zbzhang $
 */
public class EditRepairPlanAndProcDetailAction extends PrepareAction {
	private static final long serialVersionUID = -6714661013891174791L;
	private final RepairPlanAndProcDetailManager repairPlanAndProcDetailManager;
	private final RepairPlanAndProcManager repairPlanAndProcManager;
	private final DeviceCardManager deviceCardManager;
	private final DepartmentManager departmentManager;
	private final UserManager userManager;
	private final CodeValueManager codeValueManager;
	private RepairPlanAndProcDetail repairPlanOrProcDetail;
	private RepairPlanAndProc repairPlanOrProc;
	private DeviceCard asset;
	private String planProcFlag;                     //标识为计划，还是实施
	private String toolingDevFlag;                   //标识为工装，还是设备
	
	public EditRepairPlanAndProcDetailAction(RepairPlanAndProcDetailManager repairPlanAndProcDetailManager,
			RepairPlanAndProcManager repairPlanAndProcManager,
			DeviceCardManager deviceCardManager,
			DepartmentManager departmentManager,
			UserManager userManager,
			CodeValueManager codeValueManager) {
		this.repairPlanAndProcDetailManager = repairPlanAndProcDetailManager;
		this.repairPlanAndProcManager = repairPlanAndProcManager;
		this.deviceCardManager = deviceCardManager;
		this.departmentManager = departmentManager;
		this.userManager = userManager;
		this.codeValueManager = codeValueManager;
	}
	
	/**
	 * 根据页面传入的大项修计划明细ID,获取大项修计划明细;
	 * 根据页面传入的设备ID,获取设备
	 * 根据页面传入的大项修计划ID,获取大项修计划
	 */
	public void prepare() throws Exception {
		if (null == repairPlanOrProcDetail) {
			if (this.hasId("repairPlanOrProcDetail.id")) {
				this.repairPlanOrProcDetail = this.repairPlanAndProcDetailManager.loadRepairPlanAndProcDetail(this.getId("repairPlanOrProcDetail.id"));
				if (!this.hasId("device.id") && !this.hasId("tooling.id")) {
			    	this.asset = this.repairPlanOrProcDetail.getAsset();
			    	this.repairPlanOrProc = this.repairPlanOrProcDetail.getPlan();
			    }
			} else {
				this.repairPlanOrProcDetail = new RepairPlanAndProcDetail();
			}
		}
		if (this.hasId("repairPlanOrProc.id")) {
			this.repairPlanOrProc = this.repairPlanAndProcManager.loadRepairPlanOrProc(this.getId("repairPlanOrProc.id"));
		}
		if (this.hasId("planProcFlag")) {
			this.planProcFlag = request.getParameter("planProcFlag");
		}
		if (this.hasId("toolingDevFlag")) {
			this.toolingDevFlag = request.getParameter("toolingDevFlag");
		}
	}
	
	/**
	 *  保存大项修计划明细 
	 * @return SUCCESS或NEW
	 */
	public String save() {
		//保存大项修实施
		if (this.planProcFlag.equals(PreRepairModel.PROC.toString())) {
			/*if (!StringUtils.isEmpty(request.getParameter("department.id"))) {    //设置使用部门
				Department dept = this.departmentManager.loadDepartment(this.getId("department.id"));
				repairPlanOrProcDetail.setDepartment(dept);      //设置部门id
				repairPlanOrProcDetail.setUseDept(dept.getName()); //设置部门名称
			}*/
			if (!StringUtils.isEmpty(request.getParameter("department.id"))) {    //设置承修部门
			    this.repairPlanOrProc.setDepartment(this.departmentManager.loadDepartment(this.getId("department.id")));
				//repairPlanOrProcDetail.setDepartment(dept);      //设置部门id
				//repairPlanOrProcDetail.setRepairDepart(dept.getName()); //设置部门名称
			}
			if (!StringUtils.isEmpty(request.getParameter("procRepairDate"))) {     //设置计划维修日期
				repairPlanOrProcDetail.setProcRepairDate(DateUtil.toDate(request.getParameter("procRepairDate"),"yyyy-MM"));
			} else {
				repairPlanOrProcDetail.setProcRepairDate(null);
			}
			if (!StringUtils.isEmpty(request.getParameter("procResult"))) {
				if (request.getParameter("procResult").equals(PreRepairDetailResult.UNFINISHED.toString())) {
					repairPlanOrProcDetail.setProcResult(PreRepairDetailResult.UNFINISHED);
				} else if (request.getParameter("procResult").equals(PreRepairDetailResult.FINISHED.toString())) {
					repairPlanOrProcDetail.setProcResult(PreRepairDetailResult.FINISHED);
				} else if (request.getParameter("procResult").equals(PreRepairDetailResult.CANCEL.toString())) {
					repairPlanOrProcDetail.setProcResult(PreRepairDetailResult.CANCEL);
				} else {
					repairPlanOrProcDetail.setProcResult(PreRepairDetailResult.SHIFT);
				}
			}
			this.repairPlanAndProcDetailManager.storeRepairProcDetail(repairPlanOrProcDetail);
			this.addActionMessage(this.getText("repairProcDetail.edit.success", Arrays
					.asList(new Object[] { repairPlanOrProcDetail.getAsset().getName() })));
			return SUCCESS;
		}
		repairPlanOrProcDetail.setPlan(repairPlanOrProc);                  //设置大项修计划详细中关联的计划
		if (this.toolingDevFlag.equals(SysModel.DEVICE.toString())) {
			if (!StringUtils.isEmpty(request.getParameter("device.id"))) {
				 //设置设备关联的设备
				repairPlanOrProcDetail.setAsset(this.deviceCardManager.loadDevice(this.getId("device.id")));
			}
		} else {
			    //设置关联的工装
			if (!StringUtils.isEmpty(request.getParameter("tooling.id"))) {
				repairPlanOrProcDetail.setAsset(this.deviceCardManager.loadDevice(this.getId("tooling.id")));
			}
		}
		
		if (!StringUtils.isEmpty(request.getParameter("department.id"))) {       //设置承修单位
			repairPlanOrProcDetail.setDepartment(this.departmentManager.loadDepartment(this.getId("department.id")));
		}
		
		if (!StringUtils.isEmpty(request.getParameter("category.id"))) {      //设置种类
			repairPlanOrProcDetail.setCategory(this.codeValueManager.loadCodeValue(this.getId("category.id")));
		}
		//设置是否外协
		if (!StringUtils.isEmpty(request.getParameter("externalHelpFlag"))) {          
			repairPlanOrProcDetail.setExternalHelpFlag(Boolean.valueOf(request.getParameter("externalHelpFlag")).booleanValue());
		} else {
			repairPlanOrProcDetail.setExternalHelpFlag(false);
		}
		//设置机械标识
		if (!StringUtils.isEmpty(request.getParameter("machineFlag"))) {
			repairPlanOrProcDetail.setMachineFlag(true);         //是机械
		} else {
			repairPlanOrProcDetail.setMachineFlag(false);        //不是机械
		}
		//设置电器标识
		if (!StringUtils.isEmpty(request.getParameter("electricalFlag"))) {
			repairPlanOrProcDetail.setElectricalFlag(true);      //是电器
		} else {
			repairPlanOrProcDetail.setElectricalFlag(false);     //不是电器
		}
		if (!StringUtils.isEmpty(request.getParameter("planRepairDate"))) {     //设置计划维修日期
			repairPlanOrProcDetail.setPlanRepairDate(DateUtil.toDate(request.getParameter("planRepairDate"),"yyyy-MM"));
		} else {
			repairPlanOrProcDetail.setPlanRepairDate(null);
		}
		boolean isNew = this.repairPlanOrProcDetail.isNew();
		//保存大项修计划明细
		this.repairPlanAndProcDetailManager.storeRepairPlanDetail(repairPlanOrProcDetail);
		if (isNew) {
			this.addActionMessage(this.getText("repairPlanDetail.add.success", Arrays
					.asList(new Object[] { repairPlanOrProcDetail.getAsset().getName() })));

			return NEW;
		} else {       
				this.addActionMessage(this.getText("repairPlanDetail.edit.success", Arrays
						.asList(new Object[] { repairPlanOrProcDetail.getAsset().getName() })));
				return SUCCESS;
			}
	}

	public RepairPlanAndProcDetail getRepairPlanOrProcDetail() {
		return repairPlanOrProcDetail;
	}

	public void setRepairPlanOrProcDetail(
			RepairPlanAndProcDetail repairPlanOrProcDetail) {
		this.repairPlanOrProcDetail = repairPlanOrProcDetail;
	}

	public DeviceCard getDevice() {
		return asset;
	}
	
    public DeviceCard getTooling() {
    	return asset;
    }
	
    /**
     * 获取所有部门集合
     * @return List  部门集合
     */
	public List getDepartments() {
		return departmentManager.createSelectDepartments(this
				.getText(""));
	}

	/**
	 * 获取所有维修级别的集合
	 * @return List 维修级别集合
	 */
	public List getRepairLevels() {
		return this.codeValueManager.LoadAllValuesByCode(CodeConstants.REPAIR_LEVEL);
	}

	/**
	 * 获取所有的大项修种类
	 * @return  List 大项修种类集合
	 */
	public List getCategorys() {
		return this.codeValueManager.createSelectCodeValues(this.getText(""), CodeConstants.YEAR_REPAIR_CATEGORY);
	}
	
	/**
	 * 获取所有的大项修技术等级
	 * @return List 大项修技术等级集合
	 */
	public List getTechnicalLevels() {
		return this.codeValueManager.createSelectCodeValues(this.getText(""), CodeConstants.YEAR_TECHNICAL_LEVEL);
	}
	
	public List<LabelValue> getProcResults() {
		LabelValue[] arrays = this.wrapEnum(PreRepairDetailResult.class);
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

	public String getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

	public RepairPlanAndProc getRepairPlanOrProc() {
		return repairPlanOrProc;
	}

	public void setRepairPlanOrProc(RepairPlanAndProc repairPlanOrProc) {
		this.repairPlanOrProc = repairPlanOrProc;
	}	
	
}

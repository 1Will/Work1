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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.tooling.record;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.runmaintenance.tooling.record.DemarcatePlan;
import com.yongjun.tdms.model.runmaintenance.tooling.record.DemarcatePlanDetail;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.tooling.record.DemarcatePlanDetailManager;
import com.yongjun.tdms.service.runmaintenance.tooling.record.DemarcatePlanManager;

/**
 * @author qs
 * @version $Id: EditToolingDemarcatePlanAction.java 10514 2008-01-20 04:01:01Z qsun $
 */
public class EditToolingDemarcatePlanAction extends ValueListAction {
	private static final long serialVersionUID = 2786818920821623317L;
	private final Log log = LogFactory.getLog(getClass());;
	private final DemarcatePlanManager demarcatePlanManager;
	private final DepartmentManager departmentManager;
	private final DeviceCardManager deviceCardManager;
	private final UserManager userManager;
	private final DemarcatePlanDetailManager demarcatePlanDetailManager;
	private DemarcatePlan toolingDemarcatePlan;
	private String newDemarcate;
	private List<DeviceCard> toolingDemarcatePlanDetail;
	private List<DemarcatePlanDetail> demarcatePlanDetails;
	private List demarcateRuleIdList;
	private String allToolingDemarcateManager;                      //标定计划详细中负责人的值
	private String allThisDemarcateDateTm;                          //标定计划详细中本次标定时间的值
	private String allDemarcateDetailComment;                       //标定计划详细中备注的值
	private String allDemarcateResult;                              //标定计划详细中标定结果的值
	private String addOrUpdateDetail;                                     
	private static final String ADD_DETAIL = "ADD_DETAIL";        //添加标定计划详细标识
	private static final String UPDATE_DETAIL = "UPDATE_DETAIL";  //更新标定计划详细标示

	public String getNewDemarcate() {
		return newDemarcate;
	}

	public void setNewDemarcate(String newDemarcate) {
		this.newDemarcate = newDemarcate;
	}

	public EditToolingDemarcatePlanAction(DemarcatePlanManager demarcatePlanManager,
			DepartmentManager departmentManager,
			UserManager userManager,
			DeviceCardManager deviceCardManager,
			DemarcatePlanDetailManager demarcatePlanDetailManager) {
		this.demarcatePlanManager = demarcatePlanManager;
		this.departmentManager = departmentManager;
		this.userManager = userManager;
		this.deviceCardManager = deviceCardManager;
		this.demarcatePlanDetailManager = demarcatePlanDetailManager;
	}

	public void prepare() throws Exception {
		if (null == this.toolingDemarcatePlan) {
			if (this.hasId("demarcatePlan.id")) {
				this.toolingDemarcatePlan = this.demarcatePlanManager
						.loadDemarcatePlan(this.getId("demarcatePlan.id"));
				this.setFirst(false);
			} else {
				this.toolingDemarcatePlan = new DemarcatePlan();
			}
		}
		if (this.hasId("toolingDemarcatePlan.id")) {
			this.toolingDemarcatePlan = this.demarcatePlanManager.loadDemarcatePlan(this.getId("toolingDemarcatePlan.id"));
		}
		if (null == this.addOrUpdateDetail) {
			if (this.hasId("addOrUpdateDetail")) {
				this.addOrUpdateDetail = request.getParameter("addOrUpdateDetail");
			}
		}
		if (this.hasId("demarcatePlanIds")) {
			this.demarcatePlanDetails = this.demarcatePlanDetailManager.loadAllDemarcatePlanDetails(this.getIds("demarcatePlanIds"));
		}
		
	}	

	public String execute() {
		if (this.isSave()) {
			return save();
		} else if(ADD_DETAIL.equals(this.addOrUpdateDetail)) {
			if (this.hasId("newDemarcateRuleIds")) {
				log.debug("==================================== ADD_DETAIL");
				this.demarcatePlanDetailManager.storeDemarcatePlanDetail(request.getParameter("newDemarcateRuleIds"), toolingDemarcatePlan);
				this.addActionMessage(this.getText("planDetail.add.success"));
			} 
		}
		if (this.isDelete()) {
			 delete();
		     this.demarcatePlanManager.updateDemarcatePlanStatus(this.getId("toolingDemarcatePlan.id"));
		}
		return SUCCESS;
	}
	
	public boolean isSave() {
		return hasKey("save");
	}
	
	public String delete() {
		this.demarcatePlanDetailManager.deleteAllDemarcatePlanDetail(this.getId("toolingDemarcatePlan.id"),demarcatePlanDetails);
		this.addActionMessage(this.getText("planDetail.delete.success"));
		return SUCCESS;
	}
	
	public String save() {
		boolean isNew = this.toolingDemarcatePlan.isNew();
    	if (!StringUtils.isEmpty(request.getParameter("department.id"))) {
    		toolingDemarcatePlan.setDepartment(this.departmentManager.loadDepartment(this.getId("department.id")));
    	}
    	if (!StringUtils.isEmpty(request.getParameter("assessor.id"))) {
    		toolingDemarcatePlan.setAssessor(this.userManager.loadUser(this.getId("assessor.id")));
    	}
    	if (!StringUtils.isEmpty(request.getParameter("organizer.id"))) {
    		toolingDemarcatePlan.setOrganizer(this.userManager.loadUser(this.getId("organizer.id")));
    	}
    	if (!StringUtils.isEmpty(request.getParameter("ratifier.id"))) {
    		toolingDemarcatePlan.setRatifier(this.userManager.loadUser(this.getId("ratifier.id")));
    	}
    	if (isNew) {
    		this.demarcatePlanManager.storeDemarcatePlan(toolingDemarcatePlan);	
    	} else {
    		if (UPDATE_DETAIL.equals(this.addOrUpdateDetail)) {
        		if(!StringUtils.isEmpty(request.getParameter("allToolingDemarcateManager"))) {
        			allToolingDemarcateManager = request.getParameter("allToolingDemarcateManager");
        			log.debug("allToolingDemarcateManager=" + allToolingDemarcateManager);
        		}
        		if (!StringUtils.isEmpty(request.getParameter("allThisDemarcateDateTm"))) {
        			allThisDemarcateDateTm = request.getParameter("allThisDemarcateDateTm");
        			log.debug("allThisDemarcateDateTm=" + allThisDemarcateDateTm);
        		}
        		if (!StringUtils.isEmpty(request.getParameter("allDemarcateDetailComment"))) {
        			allDemarcateDetailComment = request.getParameter("allDemarcateDetailComment");
        			log.debug("allDemarcateDetailComment=" + allDemarcateDetailComment);
        		}
        		this.demarcatePlanDetailManager.storeDemarcatePlamDetail(toolingDemarcatePlan, allToolingDemarcateManager, allThisDemarcateDateTm,allDemarcateDetailComment);
        		if (!StringUtils.isEmpty(request.getParameter("enterDemarcateResult"))){
        			if (!StringUtils.isEmpty(request.getParameter("allDemarcateResult"))) {
        				allDemarcateResult = request.getParameter("allDemarcateResult");
        				log.debug("allDemarcateResult value" + allDemarcateResult);
        			}
        			this.demarcatePlanDetailManager.storeDemarcatePlamDetailResult(this.getId("toolingDemarcatePlan.id"), allDemarcateResult);
        		}
        		this.addActionMessage(this.getText("planDetail.edit.success"));
    		} else {
    			this.demarcatePlanManager.storeDemarcatePlan(toolingDemarcatePlan);	
    			this.addActionMessage(this.getText("toolingDemarcatePlan.edit.success", Arrays
    					.asList(new Object[] { toolingDemarcatePlan.getPlanName() })));
    		}
    	}
		
		if (isNew) {
			this.addActionMessage(this.getText("toolingDemarcatePlan.add.success", Arrays
					.asList(new Object[] { toolingDemarcatePlan.getPlanName() })));
			return NEW;
		}

	    return SUCCESS;
	}
	
	public List getDepartments() {
		return departmentManager.loadAllDepartments();
	}

	public DemarcatePlan getToolingDemarcatePlan() {
		return toolingDemarcatePlan;
	}

	public void setToolingDemarcatePlan(DemarcatePlan toolingDemarcatePlan) {
		this.toolingDemarcatePlan = toolingDemarcatePlan;
	}

	@Override
	protected String getAdapterName() {
		return "demarcatePlanDetails";
	}

	public List<DeviceCard> getToolingDemarcatePlanDetail() {
		return toolingDemarcatePlanDetail;
	}

	public void setToolingDemarcatePlanDetail(
			List<DeviceCard> toolingDemarcatePlanDetail) {
		this.toolingDemarcatePlanDetail = toolingDemarcatePlanDetail;
	}
	
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
        if (this.hasId("demarcatePlan.id")){
			map.put("demarcatePlan.id", this.getId("demarcatePlan.id"));
		}
		return map;
	}

}

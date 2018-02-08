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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.maintenance;

/**
 * <p>Title: ListMaintenanceRuleAction
 * <p>Description: 保养标准列表访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: ListCheckPointRuleAction.java 11229 2008-03-09 07:47:35Z wzou $
 */
import java.util.List;
import java.util.Map;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.runmaintenance.maintenance.MaintenanceRule;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.runmaintenance.maintenance.MaintenanceRuleManager;

public class ListMaintenanceRuleAction extends ValueListAction {

	private static final long serialVersionUID = 6380661404529225395L;
	private final DeviceCardManager deviceCardManager;
	private final MaintenanceRuleManager maintenanceRuleManager;
	
	private DeviceCard device;
	private List<MaintenanceRule> maintenanceRules;
	
	public ListMaintenanceRuleAction(DeviceCardManager deviceCardManager,
			MaintenanceRuleManager maintenanceRuleManager) {
		this.deviceCardManager = deviceCardManager;
		this.maintenanceRuleManager = maintenanceRuleManager;
	}

	public void prepare() {
		if (this.hasId("device.id")) {
			this.device = this.deviceCardManager.loadDevice(this.getId("device.id"));
		}
		if (this.maintenanceRules == null && this.hasIds("maintenanceRuleIds")) {
			this.maintenanceRules = this.maintenanceRuleManager.loadAllmaintenanceRule(this
					.getIds("maintenanceRuleIds"));
		} 
		this.setFirst(false);
	}
	
	public String execute() throws Exception {
		if (this.isDelete()) {
			delete();
		}
		return SUCCESS;
	}
	
	public String delete() {
		try {
			this.maintenanceRuleManager.deleteAllMaintenanceRule(this.maintenanceRules);
			this.addActionMessage(this.getText("maintenanceRules.delete.success"));
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionMessage(this.getText("maintenanceRules.delete.error"));
			return ERROR;
		}

		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		map.put("device.id",this.getId("device.id"));	
		return map;
	}
	
	@Override
	protected String getAdapterName() {
		return "maintenanceRules";
	}

	public DeviceCard getDevice() {
		return device;
	}

	public void setDevice(DeviceCard device) {
		this.device = device;
	}

	public List<MaintenanceRule> getMaintenanceRules() {
		return maintenanceRules;
	}

	public void setMaintenanceRules(List<MaintenanceRule> maintenanceRules) {
		this.maintenanceRules = maintenanceRules;
	}

}

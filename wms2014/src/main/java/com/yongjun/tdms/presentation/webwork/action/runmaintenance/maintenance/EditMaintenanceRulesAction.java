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

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointRule;
import com.yongjun.tdms.model.runmaintenance.maintenance.MaintenanceRule;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.runmaintenance.maintenance.MaintenanceRuleManager;
/**
 * <p>Title: EditMaintenanceRulesAction
 * <p>Description: 保养标准访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: $
 */
public class EditMaintenanceRulesAction extends PrepareAction {
	private static final long serialVersionUID = 8423343667644837813L;
	
	private final MaintenanceRuleManager maintenanceRuleManager;
	private final DeviceCardManager deviceCardManager;
	private final CodeValueManager codeValueManager;
	
	private MaintenanceRule maintenanceRule;
	private DeviceCard device;
	

	public EditMaintenanceRulesAction(MaintenanceRuleManager maintenaceRuleManager,
			DeviceCardManager deviceCardManager,
			CodeValueManager codeValueManager) {
		this.maintenanceRuleManager = maintenaceRuleManager;
		this.deviceCardManager = deviceCardManager;
		this.codeValueManager = codeValueManager;
	}
	
	public void prepare() throws Exception {
		if (this.maintenanceRule == null) {
			if (this.hasId("maintenanceRule.id")) {
				this.maintenanceRule = this.maintenanceRuleManager.loadMaintenanceRule(this
						.getId("maintenanceRule.id"));
			} else {
				this.maintenanceRule = new MaintenanceRule();
			}
		}
		if (this.hasId("device.id")) {
			this.device = this.deviceCardManager.loadDevice(this.getId("device.id"));
		}
	}
	
	public String save(){
		boolean isNew = this.maintenanceRule.isNew();
		if (!StringUtils.isEmpty(request.getParameter("maintenanceType.id"))) {
			this.maintenanceRule.setMaintenanceType(this.codeValueManager.loadCodeValue(this.getId("maintenanceType.id")));
		}
		this.maintenanceRule.setDevice(device);
		this.maintenanceRuleManager.storeMaintenanceRule(maintenanceRule);
		
		if (isNew) {
			this.addActionMessage(this.getText("maintenanceRule.add.success"));

			return NEW;
		} else {       
				this.addActionMessage(this.getText("maintenanceRule.edit.success"));
				return SUCCESS;
		}
	}
	
	public DeviceCard getDevice() {
		return device;
	}

	public void setDevice(DeviceCard device) {
		this.device = device;
	}

	public MaintenanceRule getMaintenanceRule() {
		return maintenanceRule;
	}

	public void setMaintenanceRule(MaintenanceRule maintenanceRule) {
		this.maintenanceRule = maintenanceRule;
	}
	
	public List getMaintenanceTypes() {
		return codeValueManager.LoadAllValuesByCode(CodeConstants.MAINTENANCE_CATEGORY);
	}
}

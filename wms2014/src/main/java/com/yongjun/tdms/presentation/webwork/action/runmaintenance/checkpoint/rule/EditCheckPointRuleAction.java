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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.checkpoint.rule;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointRule;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.runmaintenance.checkpoint.CheckPointRuleManager;

/**
 * @author wzou
 * @version $Id: EditCheckPointRuleAction.java 11271 2008-03-11 12:52:15Z wzou $
 */
public class EditCheckPointRuleAction extends PrepareAction {
	private static final long serialVersionUID = -233247328122373368L;

	private final CheckPointRuleManager checkPointRuleManager;
	private final DeviceCardManager deviceCardManager;
	
	private CheckPointRule checkPointRule;
	private DeviceCard toolingDev;

	public EditCheckPointRuleAction(CheckPointRuleManager checkPointRuleManager,
			DeviceCardManager deviceCardManager) {
		this.checkPointRuleManager = checkPointRuleManager;
		this.deviceCardManager = deviceCardManager;
	}

	public void prepare() throws Exception {
		if (this.checkPointRule == null) {
			if (this.hasId("checkPointRule.id")) {
				this.checkPointRule = this.checkPointRuleManager.loadRule(this
						.getId("checkPointRule.id"));
			} else {
				this.checkPointRule = new CheckPointRule();
			}
		}
		if (this.hasId("toolingDev.id")) {
			this.toolingDev = this.deviceCardManager.loadDevice(this.getId("toolingDev.id"));
		}
	}

	public String save(){
		boolean isNew = this.checkPointRule.isNew();
		
		this.checkPointRule.setDevice(toolingDev);
		this.checkPointRuleManager.storeRule(checkPointRule);
		
		if (isNew) {
			this.addActionMessage(this.getText("checkPointRule.add.success"));

			return NEW;
		} else {       
				this.addActionMessage(this.getText("checkPointRule.edit.success"));
				return SUCCESS;
		}
	}

	public CheckPointRule getCheckPointRule() {
		return checkPointRule;
	}

	public void setCheckPointRule(CheckPointRule checkPointRule) {
		this.checkPointRule = checkPointRule;
	}

	public DeviceCard getToolingDev() {
		return toolingDev;
	}

	public void setToolingDev(DeviceCard toolingDev) {
		this.toolingDev = toolingDev;
	}
	
}

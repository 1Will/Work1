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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.device.lubrication;

import java.util.List;
import java.util.Map;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.runmaintenance.lubrication.LubricationRule;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairModel;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.runmaintenance.lubrication.LubricationRuleManager;

/**
 * <p>Title: ListLubricationRuleAction
 * <p>Description: 娑︽粦鏍囧噯鍒楄〃璁块棶鎺у埗绫�</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author mwei@yj-technology.com
 * @version $Id: ListLubricationRuleAction.java 11088 2008-02-26 01:42:04Z zbzhang $
 */
public class ListLubricationRuleAction extends ValueListAction {
	private static final long serialVersionUID = -2453812732542410990L;

	private final DeviceCardManager deviceCardManager;
	private final LubricationRuleManager lubricationRuleManager;

	private DeviceCard device;
	private List<LubricationRule> lubricationRules;

	public ListLubricationRuleAction(DeviceCardManager deviceCardManager,
			LubricationRuleManager lubricationRuleManager) {
		this.deviceCardManager = deviceCardManager;
		this.lubricationRuleManager = lubricationRuleManager;
	}

	public void prepare() {
		if (this.hasId("device.id")) {
			this.device = this.deviceCardManager.loadDevice(this
					.getId("device.id"));
		} 
		
		if (this.lubricationRules == null && this.hasIds("lubricationRuleIds")) {
			this.lubricationRules = this.lubricationRuleManager
					.loadAllLubricationRule(this.getIds("lubricationRuleIds"));
		}
		this.setFirst(false);

	}

	public String execute() throws Exception {
		if(this.isDelete()){
			return delete();
		}
		return SUCCESS;
	}

	@Override
	protected String getAdapterName() {
		return "lubricationRules";
	}
	
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		map.put("device.id",this.getId("device.id"));	
		return map;
	}
	
	private String delete() {
		try{
			lubricationRuleManager.deleteAllLubricationRule(lubricationRules);
			this.addActionMessage(this.getText("lubrication.delete.success"));
		}catch(Exception ex){
			ex.printStackTrace();
			this.addActionMessage(this.getText("lubrication.delete.failure"));
		}
		return SUCCESS;
	}

	public DeviceCard getDevice() {
		return device;
	}

	public void setDevice(DeviceCard device) {
		this.device = device;
	}

	public List<LubricationRule> getLubricationRules() {
		return lubricationRules;
	}

	public void setLubricationRules(List<LubricationRule> lubricationRules) {
		this.lubricationRules = lubricationRules;
	}

}

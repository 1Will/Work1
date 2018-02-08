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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.repair;

import java.util.List;
import java.util.Map;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairRule;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.runmaintenance.repair.PreRepairRuleManager;

/**
 * <p>Title: ListPreRepairRuleAction
 * <p>Description: 预防性维修标准页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: ListPreRepairRuleAction.java 11235 2008-03-09 09:32:00Z wzou $
 */
public class ListPreRepairRuleAction extends ValueListAction {
	private static final long serialVersionUID = -3835975748865529572L;
	private final PreRepairRuleManager preRepairRuleManager;
	private final DeviceCardManager deviceCardManager;
	private DeviceCard asset;
	private List<PreRepairRule> preRepairRules;
	
	public ListPreRepairRuleAction(PreRepairRuleManager preRepairRuleManager,
			DeviceCardManager deviceCardManager) {
		this.preRepairRuleManager = preRepairRuleManager;
		this.deviceCardManager = deviceCardManager;
	}

	public void prepare() throws Exception {
		if (null == asset) {
			if (this.hasId("device.id")) {
				this.asset = this.deviceCardManager.loadDevice(this.getId("device.id"));
			}
		}
		if (null == preRepairRules) {
			if (this.hasId("preRepairRuleIds")) {
				this.preRepairRules = this.preRepairRuleManager.loadAllPreRepairRules(this.getIds("preRepairRuleIds"));
			}
		}
		this.setFirst(false);
	}

	/**
	 * 判断如果点击删除按钮，则删除选中的预防性维修标准集合
	 * @return SUCCESS
	 */
	public String execute() {
		if (this.isDelete()) {
			return delete();
		}
		return SUCCESS;
	}
	
	/**
	 * 删除选中的预防性维修标准集合
	 * @return SUCCESS
	 */
	public String delete() {
		this.preRepairRuleManager.deleteAllPreRepairRules(this.preRepairRules);
		this.addActionMessage(this.getText("preRepairRule.delete.success"));
		return SUCCESS;
	}
	
	@Override
	protected String getAdapterName() {
		return "preRepairRules";
	}
	
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		map.put("device.id",this.getId("device.id"));	
		return map;
	}
	
	public DeviceCard getAsset() {
		return asset;
	}

	public void setAsset(DeviceCard asset) {
		this.asset = asset;
	}

	public List<PreRepairRule> getPreRepairRules() {
		return preRepairRules;
	}

	public void setPreRepairRules(List<PreRepairRule> preRepairRules) {
		this.preRepairRules = preRepairRules;
	}

}

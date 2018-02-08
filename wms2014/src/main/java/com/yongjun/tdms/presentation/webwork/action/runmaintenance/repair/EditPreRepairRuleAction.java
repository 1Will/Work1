package com.yongjun.tdms.presentation.webwork.action.runmaintenance.repair;

import java.util.Arrays;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairRule;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.runmaintenance.repair.PreRepairRuleManager;

public class EditPreRepairRuleAction extends PrepareAction {
	private static final long serialVersionUID = 6980350028709930919L;
	private final PreRepairRuleManager preRepairRuleManager;
	private final DeviceCardManager deviceCardManager;
	private PreRepairRule preRepairRule;
	private DeviceCard asset;
	
	public EditPreRepairRuleAction(PreRepairRuleManager preRepairRuleManager,
			DeviceCardManager deviceCardManager) {
		this.preRepairRuleManager = preRepairRuleManager;
		this.deviceCardManager = deviceCardManager;
	}
	
	public void prepare() throws Exception {
		if (null == preRepairRule) {
			if (this.hasId("preRepairRule.id")) {
				this.preRepairRule = this.preRepairRuleManager.loadPreRepairRule(this.getId("preRepairRule.id"));
			} else {
				this.preRepairRule = new PreRepairRule();
			}
		}
		if (this.hasId("device.id")) {
			this.asset = this.deviceCardManager.loadDevice(this.getId("device.id"));
		}
	}

	public String save() {
		boolean isNew = this.preRepairRule.isNew();
		
		preRepairRule.setAsset(asset);
		this.preRepairRuleManager.storePreRepairRule(preRepairRule);
		
		if (isNew) {
			this.addActionMessage(this.getText("preRepairRule.add.success", Arrays
					.asList(new Object[] { preRepairRule.getPosition() })));

			return NEW;
		} else {			
			this.addActionMessage(this.getText("preRepairRule.edit.success", Arrays
					.asList(new Object[] { preRepairRule.getPosition() })));
			return SUCCESS;
		}
	}
	public DeviceCard getAsset() {
		return asset;
	}

	public void setAsset(DeviceCard asset) {
		this.asset = asset;
	}

	public PreRepairRule getPreRepairRule() {
		return preRepairRule;
	}

	public void setPreRepairRule(PreRepairRule preRepairRule) {
		this.preRepairRule = preRepairRule;
	}

}

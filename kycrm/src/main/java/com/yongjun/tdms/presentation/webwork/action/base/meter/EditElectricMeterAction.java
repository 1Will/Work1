package com.yongjun.tdms.presentation.webwork.action.base.meter;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.base.meter.ElectricMeter;
import com.yongjun.tdms.service.base.meter.ElectricMeterManager;

public class EditElectricMeterAction extends PrepareAction {

	private static final long serialVersionUID = -6353207655478426422L;
	private final ElectricMeterManager electricMeterManager;

	private ElectricMeter electricMeter;

	public EditElectricMeterAction(ElectricMeterManager electricMeterManager) {
		this.electricMeterManager = electricMeterManager;
	}

	public void prepare() throws Exception {
		if (hasId("electricMeter.id")) {
			this.electricMeter = this.electricMeterManager.loadElectricMeter(getId("electricMeter.id"));
		} else {
			this.electricMeter = new ElectricMeter();
		}
	}

	public String save() {
		boolean isNew =this.electricMeter.isNew();
		try {
			this.electricMeterManager.storeElectricMeter(this.electricMeter);
		} catch (Exception e) {
			e.printStackTrace();
			if(isNew){
				addActionMessage(getText("electricMeter.add.error"));
			}else {
				addActionMessage(getText("electricMeter.edit.error"));
			}
			return ERROR;
		}
		if(isNew){
			addActionMessage(getText("electricMeter.add.success"));
		}else {
			addActionMessage(getText("electricMeter.edit.success"));
		}
		return SUCCESS;
	}

	public ElectricMeter getElectricMeter() {
		return electricMeter;
	}

	public void setElectricMeter(ElectricMeter electricMeter) {
		this.electricMeter = electricMeter;
	}

}

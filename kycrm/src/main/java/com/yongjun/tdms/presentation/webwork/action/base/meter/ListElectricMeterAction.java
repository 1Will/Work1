package com.yongjun.tdms.presentation.webwork.action.base.meter;

import java.util.Map;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.base.meter.ElectricMeter;
import com.yongjun.tdms.service.base.meter.ElectricMeterManager;

public class ListElectricMeterAction extends ValueListAction {
	private static final long serialVersionUID = -7991194195515604965L;

	private final ElectricMeterManager electricMeterManager;
	private ElectricMeter electricMeter;
	
	public ListElectricMeterAction(ElectricMeterManager electricMeterManager) {
		this.electricMeterManager = electricMeterManager;
	}
	
	public void prepare() throws Exception {
		if (hasId("electricMeter.id")) {
			this.electricMeter = this.electricMeterManager.loadElectricMeter(getId("electricMeter.id"));
		} else {
			this.electricMeter = new ElectricMeter();
		}
	}

	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		return map;
	}

	protected String getAdapterName() {
		return "electricMeterHQL";
	}

	public String execute() {
		return "success";
	}

	public ElectricMeter getElectricMeter() {
		return electricMeter;
	}

	public void setElectricMeter(ElectricMeter electricMeter) {
		this.electricMeter = electricMeter;
	}
	
}

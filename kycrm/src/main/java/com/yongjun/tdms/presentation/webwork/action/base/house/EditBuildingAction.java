package com.yongjun.tdms.presentation.webwork.action.base.house;

import java.util.ArrayList;
import java.util.List;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.base.house.Building;
import com.yongjun.tdms.model.base.house.Floor;
import com.yongjun.tdms.model.base.house.House;
import com.yongjun.tdms.model.base.meter.ElectricMeter;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.house.BuildingManager;
import com.yongjun.tdms.service.base.house.FloorManager;
import com.yongjun.tdms.service.base.house.HouseManager;
import com.yongjun.tdms.service.base.meter.ElectricMeterManager;

public class EditBuildingAction extends PrepareAction {

	private static final long serialVersionUID = -6353207655478426422L;
	private final BuildingManager buildingManager;
	private final FloorManager floorManager;
	private final HouseManager houseManager;
	private final CodeValueManager codeValueManager;
	private final ElectricMeterManager electricMeterManager;

	private Building building;
	private Floor floor;
	private House house;

	public EditBuildingAction(BuildingManager buildingManager, FloorManager floorManager, HouseManager houseManager,
			CodeValueManager codeValueManager,ElectricMeterManager electricMeterManager) {
		this.buildingManager = buildingManager;
		this.floorManager = floorManager;
		this.houseManager = houseManager;
		this.codeValueManager = codeValueManager;
		this.electricMeterManager = electricMeterManager;
	}

	public void prepare() throws Exception {
		if (hasId("building.id")) {
			this.building = this.buildingManager.loadBuilding(getId("building.id"));
		} else {
			this.building = new Building();
		}

		if (hasId("floor.id")) {
			this.floor = this.floorManager.loadFloor(getId("floor.id"));
		}

		if (hasId("house.id")) {
			this.house = this.houseManager.loadHouse(getId("house.id"));
		}
	}

	public String save() {
		boolean isNew = this.building.isNew();
		try {
			if (hasId("waterModel.id")) {
				CodeValue waterModel = this.codeValueManager.loadCodeValue(getId("waterModel.id"));
				this.building.setWaterModel(waterModel);
			}
			if(hasId("aeMeter.id")){
				this.building.setAeMeter(electricMeterManager.loadElectricMeter(getId("aeMeter.id")));
			}
			
			if(hasId("beMeter.id")){
				this.building.setBeMeter(electricMeterManager.loadElectricMeter(getId("beMeter.id")));
			}
			
			this.buildingManager.storeBuilding(this.building);
		} catch (Exception e) {
			e.printStackTrace();
			if(isNew){
				addActionMessage(getText("building.add.error"));
			}else {
				addActionMessage(getText("building.edit.error"));
			}
			return ERROR;
		}
		if(isNew){
			addActionMessage(getText("building.add.success"));
		}else {
			addActionMessage(getText("building.edit.success"));
		}
		return SUCCESS;
	}

	public List<CodeValue> getAllWaterModel() {
		try {
			List<CodeValue> codes = new ArrayList<CodeValue>();
			List<CodeValue> one = this.codeValueManager.loadByKey("code", String.valueOf("225"));
			if ((null != one) && (one.size() > 0)) {
				List<CodeValue> list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());
				if ((null != list) && (list.size() > 0)) {
					codes.addAll(list);
				}
			}
			return codes;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<CodeValue>();
		}
	}
	
	public List<ElectricMeter> getAllElectricMeter(){
		List<ElectricMeter> meters = electricMeterManager.loadAllElectricMeter();
		if(meters!=null && meters.size()>0){
			meters.add(0, new ElectricMeter());
			return meters;
		}else {
			return new ArrayList<ElectricMeter>();
		}
	}

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public Floor getFloor() {
		return floor;
	}

	public void setFloor(Floor floor) {
		this.floor = floor;
	}

	public House getHouse() {
		return house;
	}

	public void setHouse(House house) {
		this.house = house;
	}

}

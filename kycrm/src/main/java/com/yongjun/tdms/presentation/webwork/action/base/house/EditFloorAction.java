package com.yongjun.tdms.presentation.webwork.action.base.house;

import java.util.ArrayList;
import java.util.List;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.base.house.Building;
import com.yongjun.tdms.model.base.house.Floor;
import com.yongjun.tdms.model.base.house.House;
import com.yongjun.tdms.model.base.meter.ElectricMeter;
import com.yongjun.tdms.service.base.house.BuildingManager;
import com.yongjun.tdms.service.base.house.FloorManager;
import com.yongjun.tdms.service.base.house.HouseManager;
import com.yongjun.tdms.service.base.meter.ElectricMeterManager;

public class EditFloorAction extends PrepareAction {

	private static final long serialVersionUID = -6353207655478426422L;
	private final BuildingManager buildingManager;
	private final FloorManager floorManager;
	private final HouseManager houseManager;
	private final ElectricMeterManager electricMeterManager;
	
	private Building building;
	private Floor floor;
	private House house;
	
	public EditFloorAction (BuildingManager buildingManager,FloorManager floorManager,HouseManager houseManager,ElectricMeterManager electricMeterManager) {
		this.buildingManager = buildingManager;
		this.floorManager = floorManager;
		this.houseManager = houseManager;
		this.electricMeterManager = electricMeterManager;
	}
	
	
	public void prepare() throws Exception {
		if (hasId("floor.id")) {
			this.floor = this.floorManager.loadFloor(getId("floor.id"));
		}else {
			this.floor = new Floor();
		}
		
		if (hasId("building.id")) {
			this.building = this.buildingManager.loadBuilding(getId("building.id"));
		}
		
		if (hasId("house.id")) {
			this.house = this.houseManager.loadHouse(getId("house.id"));
		}
	}
	
	public String save() {
		boolean isNew = this.floor.isNew();
		try{
			this.floor.setBuilding(this.building);
			if(hasId("aeMeter.id")){
				this.floor.setAeMeter(electricMeterManager.loadElectricMeter(getId("aeMeter.id")));
			}
			
			if(hasId("beMeter.id")){
				this.floor.setBeMeter(electricMeterManager.loadElectricMeter(getId("beMeter.id")));
			}
			this.floorManager.storeFloor(this.floor);
		}catch(Exception e){
			e.printStackTrace();
			if(isNew){
				addActionMessage(getText("floor.add.error"));
			}else {
				addActionMessage(getText("floor.edit.success"));
			}
			return ERROR;
		}
		if(isNew){
			addActionMessage(getText("floor.add.success"));
		}else {
			addActionMessage(getText("floor.edit.success"));
		}
		return SUCCESS;
	}

	
	public List<Building> getAllBuilding(){
		List<Building> buildings = this.buildingManager.loadAllBuilding();
		if(buildings!=null && buildings.size()>0){
			return buildings;
		}else {
			return new ArrayList<Building>();
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

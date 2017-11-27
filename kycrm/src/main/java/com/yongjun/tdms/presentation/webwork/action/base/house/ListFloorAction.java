package com.yongjun.tdms.presentation.webwork.action.base.house;

import java.util.Map;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.base.house.Building;
import com.yongjun.tdms.model.base.house.Floor;
import com.yongjun.tdms.model.base.house.House;
import com.yongjun.tdms.service.base.house.BuildingManager;
import com.yongjun.tdms.service.base.house.FloorManager;
import com.yongjun.tdms.service.base.house.HouseManager;

public class ListFloorAction extends ValueListAction {
	private static final long serialVersionUID = -7991194195515604965L;
	private final BuildingManager buildingManager;
	private final FloorManager floorManager;
	private final HouseManager houseManager;
	
	private Building building;
	private Floor floor;
	private House house;

	public ListFloorAction (BuildingManager buildingManager,FloorManager floorManager,HouseManager houseManager) {
		this.buildingManager = buildingManager;
		this.floorManager = floorManager;
		this.houseManager = houseManager;
	}
	
	public void prepare() throws Exception {
		if (hasId("house.id")) {
			this.house = this.houseManager.loadHouse(getId("house.id"));
		}
		
		if (hasId("building.id")) {
			this.building = this.buildingManager.loadBuilding(getId("building.id"));
		}
		
		if (hasId("floor.id")) {
			this.floor = this.floorManager.loadFloor(getId("floor.id"));
		}
	}

	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		return map;
	}

	protected String getAdapterName() {
		return "floorHQL";
	}

	public String execute() {
		return "success";
	}

	public Building getBuilding() {
		return building;
	}

	public Floor getFloor() {
		return floor;
	}

	public House getHouse() {
		return house;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public void setFloor(Floor floor) {
		this.floor = floor;
	}

	public void setHouse(House house) {
		this.house = house;
	}

}

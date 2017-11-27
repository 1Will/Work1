package com.yongjun.tdms.presentation.webwork.action.base.house;

import java.util.ArrayList;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.base.house.Building;
import com.yongjun.tdms.model.base.house.Floor;
import com.yongjun.tdms.model.base.house.House;
import com.yongjun.tdms.model.base.meter.ElectricMeter;
import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
import com.yongjun.tdms.service.base.house.BuildingManager;
import com.yongjun.tdms.service.base.house.FloorManager;
import com.yongjun.tdms.service.base.house.HouseManager;
import com.yongjun.tdms.service.base.meter.ElectricMeterManager;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class EditHouseAction extends PrepareAction {

	private static final long serialVersionUID = -6353207655478426422L;
	private final BuildingManager buildingManager;
	private final FloorManager floorManager;
	private final HouseManager houseManager;
	private final ElectricMeterManager electricMeterManager;
	private final CodeValueManager codeValueManager;
	private final CustomerInfoManager customerInfoManager;
	
	private Building building;
	private Floor floor;
	private House house;
	
	public EditHouseAction (BuildingManager buildingManager,FloorManager floorManager,HouseManager houseManager,ElectricMeterManager electricMeterManager,
			CodeValueManager codeValueManager,CustomerInfoManager customerInfoManager) {
		this.buildingManager = buildingManager;
		this.floorManager = floorManager;
		this.houseManager = houseManager;
		this.electricMeterManager = electricMeterManager;
		this.codeValueManager = codeValueManager;
		this.customerInfoManager = customerInfoManager;
	}
	
	
	public void prepare() throws Exception {
		if (hasId("house.id")) {
			this.house = this.houseManager.loadHouse(getId("house.id"));
		}else {
			this.house = new House();
		}
		
		if (hasId("building.id")) {
			this.building = this.buildingManager.loadBuilding(getId("building.id"));
		}
		
		if (hasId("floor.id")) {
			this.floor = this.floorManager.loadFloor(getId("floor.id"));
		}
	}
	
	public String save() {
		boolean isNew = this.house.isNew();
		try{
			if(!hasId("house.height")){
				this.house.setHeight(null);
			}
			this.house.setBuilding(this.building);
			this.house.setFloor(this.floor);
			if(hasId("aeMeter.id")){
				this.house.setAeMeter(electricMeterManager.loadElectricMeter(getId("aeMeter.id")));
			}
			if(hasId("beMeter.id")){
				this.house.setBeMeter(electricMeterManager.loadElectricMeter(getId("beMeter.id")));
			}
			if(hasId("customerInfo.id")){
				this.house.setCustomerInfo(this.customerInfoManager.loadCustomerInfo(getId("customerInfo.id")));
			}
			if(hasId("eType.id")){
				this.house.seteType(this.codeValueManager.loadCodeValue((getId("eType.id"))));
			}
			if(hasId("category.id")){
				this.house.setCategory(this.codeValueManager.loadCodeValue((getId("category.id"))));
			}
			if(hasId("state.id")){
				this.house.setState(this.codeValueManager.loadCodeValue((getId("state.id"))));
			}
			if(hasId("orientation.id")){
				this.house.setOrientation(this.codeValueManager.loadCodeValue((getId("orientation.id"))));
			}
			if(hasId("renovation.id")){
				this.house.setRenovation(this.codeValueManager.loadCodeValue((getId("renovation.id"))));
			}
			if(hasId("source.id")){
				this.house.setSource(this.codeValueManager.loadCodeValue((getId("source.id"))));
			}
			this.houseManager.storeHouse(this.house);
		}catch(Exception e){
			e.printStackTrace();
			if(isNew){
				addActionMessage(getText("house.add.error"));
			}else {
				addActionMessage(getText("house.edit.error"));
			}
			return ERROR;
		}
		if(isNew){
			addActionMessage(getText("house.add.success"));
		}else {
			addActionMessage(getText("house.edit.success"));
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
	
	public List<Floor> getAllFloor(){
		List<Floor> floors = this.floorManager.loadAllFloor();
		if(floors!=null && floors.size()>0){
			return floors;
		}else {
			return new ArrayList<Floor>();
		}
	}
	
	//页面dwr联动
	public List<Floor> loadFloor(Long buildingId){
		List<Floor> floors = new ArrayList<Floor>();
		try {
			floors = this.floorManager.loadByKey("building.id", buildingId);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return floors;
	}
	
	public List<Float> loadRate(Long buildingId) {
		Building building = this.buildingManager.loadBuilding(buildingId);
		List<Float> rate = new ArrayList<Float>();
		if(building.getAeMeter()!=null){
			rate.add(building.getAeMeter().getRate());
		}else {
			rate.add(0F);
		}
		if(building.getBeMeter()!=null){
			rate.add(building.getBeMeter().getRate());
		}else {
			rate.add(0F);
		}
		return rate;
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
	
	public List getAllCategory() {
		try {
			CodeValue codeValue = (CodeValue) this.codeValueManager.loadByKey("code", "229").get(0);
			List list = this.codeValueManager.loadByKey("parentCV", codeValue.getId());
			if (list != null) {
				CodeValue cv = new CodeValue();
				list.add(0, cv);
				return list;
			}
			return new ArrayList();
		} catch (DaoException e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}
	
	public List getAllSource() {
		try {
			CodeValue codeValue = (CodeValue) this.codeValueManager.loadByKey("code", "230").get(0);
			List list = this.codeValueManager.loadByKey("parentCV", codeValue.getId());
			if (list != null) {
				CodeValue cv = new CodeValue();
				list.add(0, cv);
				return list;
			}
			return new ArrayList();
		} catch (DaoException e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}
	
	public List getAllState() {
		try {
			CodeValue codeValue = (CodeValue) this.codeValueManager.loadByKey("code", "231").get(0);
			List list = this.codeValueManager.loadByKey("parentCV", codeValue.getId());
			if (list != null) {
				CodeValue cv = new CodeValue();
				list.add(0, cv);
				return list;
			}
			return new ArrayList();
		} catch (DaoException e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}
	
	public List getAllOrientation() {
		try {
			CodeValue codeValue = (CodeValue) this.codeValueManager.loadByKey("code", "232").get(0);
			List list = this.codeValueManager.loadByKey("parentCV", codeValue.getId());
			if (list != null) {
				CodeValue cv = new CodeValue();
				list.add(0, cv);
				return list;
			}
			return new ArrayList();
		} catch (DaoException e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}
	
	public List getAllRenovation() {
		try {
			CodeValue codeValue = (CodeValue) this.codeValueManager.loadByKey("code", "233").get(0);
			List list = this.codeValueManager.loadByKey("parentCV", codeValue.getId());
			if (list != null) {
				CodeValue cv = new CodeValue();
				list.add(0, cv);
				return list;
			}
			return new ArrayList();
		} catch (DaoException e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}
	
	public List getAllEType() {
		try {
			CodeValue codeValue = (CodeValue) this.codeValueManager.loadByKey("code", "234").get(0);
			List list = this.codeValueManager.loadByKey("parentCV", codeValue.getId());
			if (list != null) {
				CodeValue cv = new CodeValue();
				list.add(0, cv);
				return list;
			}
			return new ArrayList();
		} catch (DaoException e) {
			e.printStackTrace();
			return new ArrayList();
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

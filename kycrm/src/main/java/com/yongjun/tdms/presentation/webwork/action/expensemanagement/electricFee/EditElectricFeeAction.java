package com.yongjun.tdms.presentation.webwork.action.expensemanagement.electricFee;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.base.house.Building;
import com.yongjun.tdms.model.base.house.Floor;
import com.yongjun.tdms.model.base.house.House;
import com.yongjun.tdms.model.customercontract.contractmanagement.houseList.HouseList;
import com.yongjun.tdms.model.expensemanagement.electricFee.ElectricFee;
import com.yongjun.tdms.model.expensemanagement.electricFee.ElectricFloorFee;
import com.yongjun.tdms.model.expensemanagement.electricFee.ElectricHouseFee;
import com.yongjun.tdms.service.base.house.BuildingManager;
import com.yongjun.tdms.service.base.house.FloorManager;
import com.yongjun.tdms.service.base.house.HouseManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.houseList.HouseListManager;
import com.yongjun.tdms.service.expensemanagement.electricFee.ElectricFeeManager;
import com.yongjun.tdms.service.expensemanagement.electricFee.ElectricFloorFeeManager;
import com.yongjun.tdms.service.expensemanagement.electricFee.ElectricHouseFeeManager;
import com.yongjun.tdms.util.date.DateUtil;

public class EditElectricFeeAction extends PrepareAction {
	private static final long serialVersionUID = 1L;
	private final BuildingManager buildingManager;
	private final FloorManager floorManager;
	private final HouseManager houseManager;
	private final ElectricFeeManager electricFeeManager;
	private final CodeValueManager codeValueManager;
	private final ElectricFloorFeeManager electricFloorFeeManager;
	private final ElectricHouseFeeManager electricHouseFeeManager;
	private final HouseListManager houseListManager;

	private ElectricFee electricFee;
	private Building building;
	private Floor floor;
	public DecimalFormat format = new DecimalFormat("0.00");

	public EditElectricFeeAction(BuildingManager buildingManager, FloorManager floorManager, HouseManager houseManager,
			ElectricFeeManager electricFeeManager, CodeValueManager codeValueManager,
			ElectricFloorFeeManager electricFloorFeeManager, ElectricHouseFeeManager electricHouseFeeManager,
			HouseListManager houseListManager) {
		this.buildingManager = buildingManager;
		this.floorManager = floorManager;
		this.houseManager = houseManager;
		this.electricFeeManager = electricFeeManager;
		this.codeValueManager = codeValueManager;
		this.electricFloorFeeManager = electricFloorFeeManager;
		this.electricHouseFeeManager = electricHouseFeeManager;
		this.houseListManager = houseListManager;
	}

	public void prepare() throws Exception {
		if (hasId("electricFee.id")) {
			this.electricFee = this.electricFeeManager.loadElectricFee(getId("electricFee.id"));
		} else {
			this.electricFee = new ElectricFee();
		}
		if (hasId("building.id")) {
			this.building = this.buildingManager.loadBuilding(getId("building.id"));
		}
		if (hasId("floor.id")) {
			this.floor = floorManager.loadFloor(getId("floor.id"));
		}
	}

	public String save() {
		boolean isNew = this.electricFee.isNew();
		try {
			if (hasId("building.id")) {
				this.electricFee.setBuilding(this.building);
			}
			this.electricFeeManager.storeElectricFee(this.electricFee);
			// 开始添加楼层的电费
			if (isNew && hasId("building.id")) {
				List<Floor> floors = this.floorManager.loadByKey("building.id", this.building.getId());
				if (floors != null && floors.size() > 0) {
					for (int i = 0; i < floors.size(); i++) {
						if (floors.get(i).isHasElectricMeter()) {
							ElectricFloorFee electricFloorFee = new ElectricFloorFee();
							electricFloorFee.setFloor(floors.get(i));
							electricFloorFee.setStarTime(this.electricFee.getStarTime());
							electricFloorFee.setEndTime(this.electricFee.getEndTime());
							electricFloorFee.setElectricFee(this.electricFee);
							electricFloorFee.setOutLine("");
							if(floors.get(i).getAedisplay()!= null){
								electricFloorFee.setLastElectricA(floors.get(i).getAedisplay());
							}else {
								electricFloorFee.setLastElectricA(0);
							}
							
							if(floors.get(i).getBedisplay()!= null){
								electricFloorFee.setLastElectricB(floors.get(i).getBedisplay());
							}else {
								electricFloorFee.setLastElectricB(0);
							}
							this.electricFloorFeeManager.storeElectricFloorFee(electricFloorFee);
						}
						String keyName[] = { "building.id", "floor.id" };
						Object keyValue[] = { this.building.getId(), floors.get(i).getId() };
						List<House> houses = this.houseManager.loadByKeyArray(keyName, keyValue);
						if (houses != null && houses.size() > 0) {
							for (int j = 0; j < houses.size(); j++) {
								if (houses.get(j).getHasElectricMeter()) {
									String[] kn = { "house.id", "isuse" };
									Object[] kv = { houses.get(j).getId(), true };
									List<HouseList> houseLists = this.houseListManager.loadByKeyArray(kn, kv);
									if (houseLists != null && houseLists.size() > 0 && "06603".equals(houseLists.get(0).getContractManagement().getState().getCode())) {
										ElectricHouseFee electricHouseFee = new ElectricHouseFee();
										// 设置房间的开始时间
										if (houseLists.get(0).getStartTime().getTime() >= this.electricFee .getStarTime().getTime() && houseLists.get(0).getStartTime().getTime() < this.electricFee.getEndTime().getTime()) {
											electricHouseFee.setStarTime(houseLists.get(0).getStartTime());
										} else {
//											if (houseLists.get(0).getStartTime().getTime() > this.electricFee.getEndTime().getTime()) {
//												continue;
//											} else {
												electricHouseFee.setStarTime(this.electricFee.getStarTime());
//											}
										}
										// 设置房间的结束时间
										if (houseLists.get(0).getEndTime().getTime() <= this.electricFee.getEndTime().getTime()&& houseLists.get(0).getEndTime().getTime() > this.electricFee.getStarTime().getTime()) {
											electricHouseFee.setEndTime(houseLists.get(0).getEndTime());
										} else {
//											if (houseLists.get(0).getEndTime().getTime() < this.electricFee.getStarTime().getTime()) {
//												continue;
//											} else {
												electricHouseFee.setEndTime(this.electricFee.getEndTime());
//											}
										}
										int day = DateUtil.daysBetween(electricHouseFee.getStarTime(), electricHouseFee.getEndTime());
										electricHouseFee.setDays(day);
										electricHouseFee.setHouse(houses.get(j));
										electricHouseFee.setElectricFee(this.electricFee);
										electricHouseFee.setOutLine("");
										if(houses.get(j).getAedisplay()!= null){
											electricHouseFee.setLastElectricA(houses.get(j).getAedisplay());
										}else {
											electricHouseFee.setLastElectricA(0);
										}
										
										if(houses.get(j).getBedisplay()!= null){
											electricHouseFee.setLastElectricB(houses.get(j).getBedisplay());
										}else {
											electricHouseFee.setLastElectricB(0);
										}
										electricHouseFee.setCustomerInfo(houseLists.get(0).getContractManagement().getCustomerInfo());
										this.electricHouseFeeManager.storeElectricHouseFee(electricHouseFee);
									}else {
										// 空闲房间,直接创建 不参与公摊,用day=0标识
										ElectricHouseFee electricHouseFee = new ElectricHouseFee();
										electricHouseFee.setStarTime(this.electricFee.getStarTime());
										electricHouseFee.setEndTime(this.electricFee.getEndTime());
										electricHouseFee.setDays(0);
										electricHouseFee.setHouse(houses.get(j));
										electricHouseFee.setElectricFee(this.electricFee);
										electricHouseFee.setOutLine("");
										if(houses.get(j).getAedisplay()!= null){
											electricHouseFee.setLastElectricA(houses.get(j).getAedisplay());
										}else {
											electricHouseFee.setLastElectricA(0);
										}
										if(houses.get(j).getBedisplay()!= null){
											electricHouseFee.setLastElectricB(houses.get(j).getBedisplay());
										}else {
											electricHouseFee.setLastElectricB(0);
										}
										this.electricHouseFeeManager.storeElectricHouseFee(electricHouseFee);
									}
								}
							}
						}
					}
				}
			}

			if (isNew) {
				addActionMessage(getText("electricFee.add.success"));
				return NEW;
			}
			addActionMessage(getText("electricFee.edit.success"));
			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
			addActionMessage(getText("electricFee.edit.error"));
			return ERROR;
		}
	}

	/**
	 * 计算电费
	 * 
	 * @param electricFeeId
	 * @return
	 */
	public String calculate(Long electricFeeId) {
		ElectricFee electricFee = electricFeeManager.loadElectricFee(electricFeeId);
		try {
			List<ElectricHouseFee> electricHouseFees = electricHouseFeeManager.loadByKey("electricFee.id", electricFeeId);
			if(electricHouseFees!=null && electricHouseFees.size()>0){
				for (int i = 0; i < electricHouseFees.size(); i++) {
					double efee = 0D;
					double eshare = 0D;
					ElectricHouseFee ehf = electricHouseFees.get(i);
					//居民电价
					if("23401".equals(ehf.getHouse().geteType().getCode())){
						efee = ehf.getSumElectric() * ehf.getHouse().getBuilding().getElectricCivilPrice();
					}
					//办公电价
					if("23402".equals(ehf.getHouse().geteType().getCode())){
						efee = ehf.getSumElectric() * ehf.getHouse().getBuilding().getElectricIndustryPrice();
					}
					//公摊
					if(ehf.getHouse().getHasEShare()){
						int sumDay = DateUtil.daysBetween(electricFee.getStarTime(), electricFee.getEndTime());
						int days =ehf.getDays();
						double sharePrice = ehf.getHouse().getBuilding().getElectricSharePrice();
						double square = ehf.getHouse().getSquare();
						eshare = days*sharePrice*electricFee.getMonth()*square/sumDay;
					}
					ehf.setShareFee(Double.valueOf(format.format(eshare)));
					ehf.setSumFee(Double.valueOf(format.format(efee+eshare)));
					electricHouseFeeManager.storeElectricHouseFee(ehf);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}

	public List<Building> getAllBuilding() {
		List<Building> buildings = new ArrayList<Building>();
		buildings.add(new Building());
		buildings.addAll(this.buildingManager.loadAllBuilding());
		if (buildings != null && buildings.size() > 0) {
			return buildings;
		} else {
			return new ArrayList<Building>();
		}
	}

	public ElectricFee getElectricFee() {
		return electricFee;
	}

	public Building getBuilding() {
		return building;
	}

	public Floor getFloor() {
		return floor;
	}

	public void setElectricFee(ElectricFee electricFee) {
		this.electricFee = electricFee;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public void setFloor(Floor floor) {
		this.floor = floor;
	}

}

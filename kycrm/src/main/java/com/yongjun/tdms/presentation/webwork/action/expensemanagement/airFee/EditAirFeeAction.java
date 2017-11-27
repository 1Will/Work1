package com.yongjun.tdms.presentation.webwork.action.expensemanagement.airFee;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.base.house.Building;
import com.yongjun.tdms.model.base.house.House;
import com.yongjun.tdms.model.customercontract.contractmanagement.houseList.HouseList;
import com.yongjun.tdms.model.expensemanagement.airFee.AirFee;
import com.yongjun.tdms.model.expensemanagement.airFee.AirHouseFee;
import com.yongjun.tdms.service.base.house.BuildingManager;
import com.yongjun.tdms.service.base.house.FloorManager;
import com.yongjun.tdms.service.base.house.HouseManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.houseList.HouseListManager;
import com.yongjun.tdms.service.expensemanagement.airFee.AirFeeManager;
import com.yongjun.tdms.service.expensemanagement.airFee.AirHouseFeeManager;
import com.yongjun.tdms.util.date.DateUtil;

public class EditAirFeeAction extends PrepareAction {
	private static final long serialVersionUID = 1L;
	private final BuildingManager buildingManager;
	private final FloorManager floorManager;
	private final HouseManager houseManager;
	private final AirFeeManager airFeeManager;
	private final CodeValueManager codeValueManager;
	private final HouseListManager houseListManager;
	private final AirHouseFeeManager airHouseFeeManager;

	private AirFee airFee;
	private House house;
	private Date starTime;
	private Date endTime;
	private Building building;
	public DecimalFormat format = new DecimalFormat("0.00");

	public EditAirFeeAction(BuildingManager buildingManager, FloorManager floorManager, HouseManager houseManager,
			AirFeeManager airFeeManager, CodeValueManager codeValueManager, HouseListManager houseListManager,
			AirHouseFeeManager airHouseFeeManager) {
		this.buildingManager = buildingManager;
		this.floorManager = floorManager;
		this.houseManager = houseManager;
		this.airFeeManager = airFeeManager;
		this.codeValueManager = codeValueManager;
		this.houseListManager = houseListManager;
		this.airHouseFeeManager = airHouseFeeManager;
	}

	public void prepare() throws Exception {
		if (hasId("airFee.id")) {
			this.airFee = this.airFeeManager.loadAirFee(getId("airFee.id"));
		} else {
			this.airFee = new AirFee();
		}
		if (hasId("house.id")) {
			this.house = this.houseManager.loadHouse(getId("house.id"));
		}
		if (hasId("building.id")) {
			this.building = this.buildingManager.loadBuilding(getId("building.id"));
		}
	}

	public String save() {
		boolean isNew = this.airFee.isNew();
		this.airFee.setStarTime(starTime);
		this.airFee.setEndTime(endTime);
		if (hasId("building.id")) {
			this.airFee.setBuilding(this.building);
		}
		try {
			this.airFeeManager.storeAirFee(this.airFee);
			if (isNew) {
				String[] keyName = { "building.id", "hasAirMeter" };
				Object[] keyValue = { this.building.getId(), 1 };
				List<House> houses = this.houseManager.loadByKeyArray(keyName, keyValue);
				if (houses != null && houses.size() > 0) {
					for (int i = 0; i < houses.size(); i++) {
						if (houses.get(i).getHasAirMeter()) {
							AirHouseFee ahf = new AirHouseFee();
							ahf.setAirFee(this.airFee);
							ahf.setHouse(houses.get(i));
							ahf.setFactDay(DateUtil.daysBetween(starTime, endTime));
							ahf.setOpenDays(this.airFee.getOpenDays());
							if(houses.get(i).getAirdisplay()!=null){
								ahf.setLastAir(houses.get(i).getAirdisplay());
							}else {
								ahf.setLastAir(0);
							}
							String[] kn = { "house.id", "isuse" };
							Object[] kv = { houses.get(i).getId(), true };
							List<HouseList> houseLists = this.houseListManager.loadByKeyArray(kn, kv);
							if (houseLists != null && houseLists.size() > 0 && "06603".equals(houseLists.get(0).getContractManagement().getState().getCode())) {
								// 设置房间的开始时间
								if (houseLists.get(0).getStartTime().getTime() >= this.starTime.getTime() && houseLists.get(0).getStartTime().getTime() < this.endTime.getTime()) {
									ahf.setStarTime(houseLists.get(0).getStartTime());
								} else {
									// if
									// (houseLists.get(0).getStartTime().getTime() >this.endTime().getTime()) {
									// continue;
									// } else {
									ahf.setStarTime(this.starTime);
									// }
								}
								// 设置房间的结束时间
								if (houseLists.get(0).getEndTime().getTime() <= this.endTime.getTime() && houseLists.get(0).getEndTime().getTime() > this.starTime.getTime()) {
									ahf.setEndTime(houseLists.get(0).getEndTime());
								} else {
									// if(houseLists.get(0).getEndTime().getTime()< this.starTime().getTime()) {
									// continue;
									// } else {
									ahf.setEndTime(this.endTime);
									// }
								}
								ahf.setUseDay(DateUtil.daysBetween(ahf.getStarTime(), ahf.getEndTime()));
								ahf.setOutLine("");
								ahf.setCustomerInfo(houseLists.get(0).getContractManagement().getCustomerInfo());
								this.airHouseFeeManager.storeAirHouseFee(ahf);
							} else {
								ahf.setStarTime(starTime);
								ahf.setEndTime(endTime);
								ahf.setUseDay(0);
								ahf.setOutLine("");
								this.airHouseFeeManager.storeAirHouseFee(ahf);
							}
						}
					}
				}
			}
			if (isNew) {
				addActionMessage(getText("airFee.add.success"));
				return NEW;
			}
			addActionMessage(getText("airFee.edit.success"));
			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
			addActionMessage(getText("airFee.edit.error"));
			return ERROR;
		}
	}

	
	public String calculate(Long electricFeeId) {
		AirFee airFee = this.airFeeManager.loadAirFee(electricFeeId);
		//开放月系数
		double kfyxs =Double.valueOf(airFee.getOpenDays())/22;
		try {
			List<AirHouseFee> airHouseFees = airHouseFeeManager.loadByKey("airFee.id", electricFeeId);
			if(airHouseFees!=null && airHouseFees.size()>0){
				for (int i = 0; i < airHouseFees.size(); i++) {
					AirHouseFee ahf = airHouseFees.get(i);
					double base = ahf.getHouse().getSquare() * ahf.getHouse().getBuilding().getAirBasePrice()*kfyxs*ahf.getUseDay()/ahf.getFactDay() ;
					double flow = ahf.getSumAir() * ahf.getHouse().getBuilding().getAirPrice();
					ahf.setSumFee(Double.valueOf(format.format(base+flow)));
					airHouseFeeManager.storeAirHouseFee(ahf);
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

	public AirFee getAirFee() {
		return airFee;
	}

	public House getHouse() {
		return house;
	}

	public Date getStarTime() {
		return starTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setAirFee(AirFee airFee) {
		this.airFee = airFee;
	}

	public void setHouse(House house) {
		this.house = house;
	}

	public void setStarTime(Date starTime) {
		this.starTime = starTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}
}

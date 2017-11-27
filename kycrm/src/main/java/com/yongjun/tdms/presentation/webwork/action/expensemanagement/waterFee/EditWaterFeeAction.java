package com.yongjun.tdms.presentation.webwork.action.expensemanagement.waterFee;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.base.house.Building;
import com.yongjun.tdms.model.base.house.Floor;
import com.yongjun.tdms.model.base.house.House;
import com.yongjun.tdms.model.customercontract.contractmanagement.houseList.HouseList;
import com.yongjun.tdms.model.expensemanagement.waterFee.WaterFee;
import com.yongjun.tdms.model.expensemanagement.waterFee.WaterFloorFee;
import com.yongjun.tdms.model.expensemanagement.waterFee.WaterHouseFee;
import com.yongjun.tdms.service.base.house.BuildingManager;
import com.yongjun.tdms.service.base.house.FloorManager;
import com.yongjun.tdms.service.base.house.HouseManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.houseList.HouseListManager;
import com.yongjun.tdms.service.expensemanagement.waterFee.WaterFeeManager;
import com.yongjun.tdms.service.expensemanagement.waterFee.WaterFloorFeeManager;
import com.yongjun.tdms.service.expensemanagement.waterFee.WaterHouseFeeManager;
import com.yongjun.tdms.util.date.DateUtil;

public class EditWaterFeeAction extends PrepareAction {
	private static final long serialVersionUID = 1L;
	private final BuildingManager buildingManager;
	private final FloorManager floorManager;
	private final HouseManager houseManager;
	private final WaterFeeManager waterFeeManager;
	private final CodeValueManager codeValueManager;
	private final WaterFloorFeeManager waterFloorFeeManager;
	private final WaterHouseFeeManager waterHouseFeeManager;
	private final HouseListManager houseListManager;

	private WaterFee waterFee;
	private Building building;
	private Floor floor;
	public DecimalFormat format = new DecimalFormat("0.00");

	public EditWaterFeeAction(BuildingManager buildingManager, FloorManager floorManager, HouseManager houseManager,
			WaterFeeManager waterFeeManager, CodeValueManager codeValueManager,
			WaterFloorFeeManager waterFloorFeeManager, WaterHouseFeeManager waterHouseFeeManager,
			HouseListManager houseListManager) {
		this.buildingManager = buildingManager;
		this.floorManager = floorManager;
		this.houseManager = houseManager;
		this.waterFeeManager = waterFeeManager;
		this.codeValueManager = codeValueManager;
		this.waterFloorFeeManager = waterFloorFeeManager;
		this.waterHouseFeeManager = waterHouseFeeManager;
		this.houseListManager = houseListManager;
	}

	public void prepare() throws Exception {
		if (hasId("waterFee.id")) {
			this.waterFee = this.waterFeeManager.loadWaterFee(getId("waterFee.id"));
		} else {
			this.waterFee = new WaterFee();
		}
		if (hasId("building.id")) {
			this.building = this.buildingManager.loadBuilding(getId("building.id"));
		}
		if (hasId("floor.id")) {
			this.floor = floorManager.loadFloor(getId("floor.id"));
		}
	}

	public String save() {
		boolean isNew = this.waterFee.isNew();
		try {
			if (hasId("building.id")) {
				this.waterFee.setBuilding(this.building);
			}
			this.waterFeeManager.storeWaterFee(this.waterFee);

			// 开始添加楼层的水费
			if (isNew && hasId("building.id")) {
				if ("22502".equals(this.building.getWaterModel().getCode())) {// 如果是一层一表
					List<Floor> floors = this.floorManager.loadByKey("building.id", this.building.getId());
					if (floors != null && floors.size() > 0) {
						for (int i = 0; i < floors.size(); i++) {
							if (floors.get(i).isHasWaterMeter()) {
								WaterFloorFee waterFloorFee = new WaterFloorFee();
								waterFloorFee.setFloor(floors.get(i));
								waterFloorFee.setStarTime(this.waterFee.getStarTime());
								waterFloorFee.setEndTime(this.waterFee.getEndTime());
								waterFloorFee.setWaterFee(this.waterFee);
								waterFloorFee.setOutLine("");
								if(floors.get(i).getWaterdisplay()!= null){
									waterFloorFee.setLastWater(floors.get(i).getWaterdisplay());
								}else {
									waterFloorFee.setLastWater(0);
								}
								this.waterFloorFeeManager.storeWaterFloorFee(waterFloorFee);
							}
						}
					}
				}
				// 开始添加对应房间的水费
				if ("22501".equals(this.building.getWaterModel().getCode())) {// 如果是一户一表
					List<House> houses = this.houseManager.loadByKey("building.id", this.building.getId());
					createWaterHouseFee(houses, this.waterFee);
				}
			}

			if (isNew) {
				addActionMessage(getText("waterFee.add.success"));
				return NEW;
			}
			addActionMessage(getText("waterFee.edit.success"));
			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
			addActionMessage(getText("waterFee.edit.error"));
			return ERROR;
		}
	}

	// 创建房间水费
	public Double createWaterHouseFee(List<House> houses, WaterFee waterFee) throws DaoException, ParseException {
		Double sd = 0D;// 天数*面积
		if (houses != null && houses.size() > 0) {
			for (int j = 0; j < houses.size(); j++) {
				// if (houses.get(j).isHasWaterMeter()) {
				String[] keyName = { "house.id", "isuse" };
				Object[] keyValue = { houses.get(j).getId(), true };
				List<HouseList> houseLists = this.houseListManager.loadByKeyArray(keyName, keyValue);
				//WaterHouseFee没有就新建，有则修改
				String houseKeyName[] = { "waterFee.id", "house.id" };
				Object houseKeyValue[] = { waterFee.getId(), houses.get(j).getId() };
				List<WaterHouseFee> waterHouseFees = this.waterHouseFeeManager.loadByKeyArray(houseKeyName,houseKeyValue);
				WaterHouseFee whf = null;
				if(waterHouseFees!=null && waterHouseFees.size()>0){
					whf = waterHouseFees.get(0);
				}else {
					whf = new WaterHouseFee();
				}
				// 非空闲房间
				if (houseLists != null && houseLists.size() > 0 && "06603".equals(houseLists.get(0).getContractManagement().getState().getCode())) {
					// 设置房间的开始时间
					if (houseLists.get(0).getStartTime().getTime() >= waterFee.getStarTime().getTime()
							&& houseLists.get(0).getStartTime().getTime() < waterFee.getEndTime().getTime()) {
						whf.setStarTime(houseLists.get(0).getStartTime());
					} else {
//						if (houseLists.get(0).getStartTime().getTime() > waterFee.getEndTime().getTime()) {
//							continue;
//						} else {
							whf.setStarTime(waterFee.getStarTime());
//						}
					}
					// 设置房间的结束时间
					if (houseLists.get(0).getEndTime().getTime() <= waterFee.getEndTime().getTime()
							&& houseLists.get(0).getEndTime().getTime() > waterFee.getStarTime().getTime()) {
						whf.setEndTime(houseLists.get(0).getEndTime());
					} else {
//						if (houseLists.get(0).getEndTime().getTime() < waterFee.getStarTime().getTime()) {
//							continue;
//						} else {
							whf.setEndTime(waterFee.getEndTime());
//						}
					}
					int day = DateUtil.daysBetween(whf.getStarTime(), whf.getEndTime());
					sd += day * houses.get(j).getSquare();
					whf.setDays(day);
					whf.setHouse(houses.get(j));
					whf.setWaterFee(waterFee);
					whf.setOutLine("");
					if(houses.get(j).getWaterdisplay()!=null){
						whf.setLastWater(houses.get(j).getWaterdisplay());
					}else {
						whf.setLastWater(0);
					}
					whf.setCustomerInfo(houseLists.get(0).getContractManagement().getCustomerInfo());
					this.waterHouseFeeManager.storeWaterHouseFee(whf);
				} else {
					// 空闲房间,直接创建 不参与公摊,用day=0标识
					whf.setStarTime(waterFee.getStarTime());
					whf.setEndTime(waterFee.getEndTime());
					whf.setDays(0);
					whf.setHouse(houses.get(j));
					whf.setWaterFee(waterFee);
					whf.setOutLine("");
					if(houses.get(j).getWaterdisplay()!=null){
						whf.setLastWater(houses.get(j).getWaterdisplay());
					}else {
						whf.setLastWater(0);
					}
					this.waterHouseFeeManager.storeWaterHouseFee(whf);
				}
			}
			// }
		}
		return sd;
	}

	/**
	 * 计算水电费
	 * 
	 * @param waterFeeId
	 * @return
	 */
	public String calculate(Long waterFeeId) {
		WaterFee waterFee = this.waterFeeManager.loadWaterFee(waterFeeId);
		try {
			if ("22501".equals(waterFee.getBuilding().getWaterModel().getCode())) {// 如果是一户一表
				List<WaterHouseFee> waterHouseFees = this.waterHouseFeeManager.loadByKey("waterFee.id",waterFee.getId());
				Double water = 0D;
				Double sd = 0D;// 天数*面积
				if (waterHouseFees != null && waterHouseFees.size() > 0) {
					for (int i = 0; i < waterHouseFees.size(); i++) {
						water += waterHouseFees.get(i).getThisWater() - waterHouseFees.get(i).getLastWater();
						sd += waterHouseFees.get(i).getHouse().getSquare() * waterHouseFees.get(i).getDays();
					}
				}
				// 计算每单位公摊
				Double perShare = (waterFee.getSumWater() - water) / sd;
				// 循环修改房间公摊数、钱
				if (waterHouseFees != null && waterHouseFees.size() > 0) {
					for (int i = 0; i < waterHouseFees.size(); i++) {
						WaterHouseFee wf = waterHouseFees.get(i);
						wf.setShareWater(Double.valueOf(format.format(wf.getDays() * wf.getHouse().getSquare()* perShare)));
						wf.setSumWater(Double.valueOf(format.format(wf.getShareWater() + wf.getThisWater()- wf.getLastWater())));
						wf.setSumFee(Double.valueOf(format.format(wf.getSumWater()* wf.getHouse().getBuilding().getWaterPrice())));
						waterHouseFeeManager.storeWaterHouseFee(wf);
					}
				}
			}
			
			if ("22502".equals(waterFee.getBuilding().getWaterModel().getCode())) {// 如果是一层一表
				List<WaterFloorFee> waterFloorFees = this.waterFloorFeeManager.loadByKey("waterFee.id",waterFee.getId());
				if (waterFloorFees != null && waterFloorFees.size() > 0) {
					// 按楼层循环公摊
					for (int i = 0; i < waterFloorFees.size(); i++) {
						List<House> houses = this.houseManager.loadByKey("floor.id", waterFloorFees.get(i).getFloor().getId());
						// 添加房子水费，并算公摊
						if (houses != null && houses.size() > 0) {
							double sd = createWaterHouseFee(houses, waterFee);
							Double square = 0D;
							for (int j = 0; j < houses.size(); j++) {
								square += houses.get(j).getSquare();
							}
								Double perShare =0D;
							if(sd!=0){
								perShare = waterFloorFees.get(i).getSumWater() / sd;
							}
							String keyName[] = { "waterFee.id", "house.floor.id" };
							Object keyValue[] = { waterFeeId, waterFloorFees.get(i).getFloor().getId() };
							List<WaterHouseFee> waterHouseFees = this.waterHouseFeeManager.loadByKeyArray(keyName,keyValue);
							if (waterHouseFees != null && waterHouseFees.size() > 0) {
								for (int m = 0; m < waterHouseFees.size(); m++) {
									WaterHouseFee wf = waterHouseFees.get(m);
									wf.setShareWater(Double.valueOf(format.format(wf.getDays() * wf.getHouse().getSquare() * perShare)));
									wf.setSumWater(Double.valueOf(format.format(wf.getShareWater() + wf.getThisWater() - wf.getLastWater())));
									wf.setSumFee(Double.valueOf(format.format(wf.getSumWater() * wf.getHouse().getBuilding().getWaterPrice())));
									waterHouseFeeManager.storeWaterHouseFee(wf);
								}
							}
						}
					}
				}
			}
			if ("22503".equals(waterFee.getBuilding().getWaterModel().getCode())) {// 如果是一楼一表
				List<House> houses = this.houseManager.loadByKey("building.id", waterFee.getBuilding().getId());
				if (houses != null && houses.size() > 0) {
					double sd = createWaterHouseFee(houses, waterFee);
					Double square = 0D;
					for (int j = 0; j < houses.size(); j++) {
						square += houses.get(j).getSquare();
					}
					Double perShare = null;
					if(sd != 0){
						perShare = waterFee.getSumWater() / sd;
					}else {
						perShare = 0D;
					}
					List<WaterHouseFee> waterHouseFees = this.waterHouseFeeManager.loadByKey("waterFee.id", waterFeeId);
					if (waterHouseFees != null && waterHouseFees.size() > 0) {
						for (int m = 0; m < waterHouseFees.size(); m++) {
							WaterHouseFee wf = waterHouseFees.get(m);
							wf.setShareWater(Double.valueOf(format.format(wf.getDays() * wf.getHouse().getSquare() * perShare)));
							wf.setSumWater(Double.valueOf(format.format(wf.getShareWater() + wf.getThisWater() - wf.getLastWater())));
							wf.setSumFee(Double.valueOf(format.format(wf.getSumWater() * wf.getHouse().getBuilding().getWaterPrice())));
							waterHouseFeeManager.storeWaterHouseFee(wf);
						}
					}
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

	public WaterFee getWaterFee() {
		return waterFee;
	}

	public Building getBuilding() {
		return building;
	}

	public Floor getFloor() {
		return floor;
	}

	public void setWaterFee(WaterFee waterFee) {
		this.waterFee = waterFee;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public void setFloor(Floor floor) {
		this.floor = floor;
	}

}

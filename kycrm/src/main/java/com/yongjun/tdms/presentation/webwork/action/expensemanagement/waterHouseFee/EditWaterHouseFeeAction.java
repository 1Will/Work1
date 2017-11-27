package com.yongjun.tdms.presentation.webwork.action.expensemanagement.waterHouseFee;

import java.util.ArrayList;
import java.util.List;

import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.base.house.Building;
import com.yongjun.tdms.model.base.house.House;
import com.yongjun.tdms.model.expensemanagement.waterFee.WaterFee;
import com.yongjun.tdms.model.expensemanagement.waterFee.WaterHouseFee;
import com.yongjun.tdms.service.base.house.BuildingManager;
import com.yongjun.tdms.service.base.house.FloorManager;
import com.yongjun.tdms.service.base.house.HouseManager;
import com.yongjun.tdms.service.expensemanagement.waterFee.WaterFeeManager;
import com.yongjun.tdms.service.expensemanagement.waterFee.WaterHouseFeeManager;

public class EditWaterHouseFeeAction extends PrepareAction {
	private static final long serialVersionUID = 1L;
	private final BuildingManager buildingManager;
	private final FloorManager floorManager;
	private final HouseManager houseManager;
	private final WaterFeeManager waterFeeManager;
	private final WaterHouseFeeManager waterHouseFeeManager;
	private final CodeValueManager codeValueManager;

	private WaterHouseFee waterHouseFee;
	private Building building;
	private House house;
	private WaterFee waterFee;
	private String pagingPage;

	public EditWaterHouseFeeAction(BuildingManager buildingManager, FloorManager floorManager,
			HouseManager houseManager, WaterFeeManager waterFeeManager, CodeValueManager codeValueManager,
			WaterHouseFeeManager waterHouseFeeManager) {
		this.buildingManager = buildingManager;
		this.floorManager = floorManager;
		this.houseManager = houseManager;
		this.waterFeeManager = waterFeeManager;
		this.codeValueManager = codeValueManager;
		this.waterHouseFeeManager = waterHouseFeeManager;
	}

	public void prepare() throws Exception {
		if (hasId("waterHouseFee.id")) {
			this.waterHouseFee = this.waterHouseFeeManager.loadWaterHouseFee(getId("waterHouseFee.id"));
		} else {
			this.waterHouseFee = new WaterHouseFee();
		}
		if (hasId("building.id")) {
			this.building = this.buildingManager.loadBuilding(getId("building.id"));
		}
		if (hasId("house.id")) {
			this.house = houseManager.loadHouse(getId("house.id"));
		}
		if (hasId("waterFee.id")) {
			this.waterFee = waterFeeManager.loadWaterFee(getId("waterFee.id"));
		}
		if(hasId("pagingPage")){
			this.pagingPage = request.getParameter("pagingPage");
		}
	}

	public String save() {
		if (hasId("saveAll")) {
			String ids[] = request.getParameterValues("ids");
			for(int i =0;i<ids.length;i++){
				WaterHouseFee whf = waterHouseFeeManager.loadWaterHouseFee(Long.parseLong(ids[i]));
				whf.setLastWater(Double.parseDouble(request.getParameter("lastWater"+ids[i])));
				whf.setThisWater(Double.parseDouble(request.getParameter("thisWater"+ids[i])));
				whf.setSumWater(Double.parseDouble(request.getParameter("sumWater"+ids[i])));
				whf.setSumFee(Double.parseDouble(request.getParameter("sumFee"+ids[i])));
				whf.setOutLine(request.getParameter("outLine"+ids[i]));
				waterHouseFeeManager.storeWaterHouseFee(whf);
				House house = whf.getHouse();
				house.setWaterdisplay(whf.getThisWater());
				houseManager.storeHouse(house);
			}
			return "all";
		} else {
			boolean isNew = this.waterHouseFee.isNew();
			try {
				if (hasId("building.id")) {
					this.waterHouseFee.setHouse(this.house);
				}
				this.waterHouseFeeManager.storeWaterHouseFee(this.waterHouseFee);
				// 开始添加楼层的水电费
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

	public WaterHouseFee getWaterHouseFee() {
		return waterHouseFee;
	}

	public Building getBuilding() {
		return building;
	}

	public House getHouse() {
		return house;
	}

	public void setWaterHouseFee(WaterHouseFee waterHouseFee) {
		this.waterHouseFee = waterHouseFee;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public void setHouse(House house) {
		this.house = house;
	}

	public WaterFee getWaterFee() {
		return waterFee;
	}

	public void setWaterFee(WaterFee waterFee) {
		this.waterFee = waterFee;
	}

	public String getPagingPage() {
		return pagingPage;
	}

	public void setPagingPage(String pagingPage) {
		this.pagingPage = pagingPage;
	}

}

package com.yongjun.tdms.presentation.webwork.action.expensemanagement.waterFloorFee;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.base.house.Building;
import com.yongjun.tdms.model.base.house.Floor;
import com.yongjun.tdms.model.expensemanagement.waterFee.WaterFee;
import com.yongjun.tdms.model.expensemanagement.waterFee.WaterFloorFee;
import com.yongjun.tdms.service.base.house.BuildingManager;
import com.yongjun.tdms.service.base.house.FloorManager;
import com.yongjun.tdms.service.base.house.HouseManager;
import com.yongjun.tdms.service.expensemanagement.waterFee.WaterFeeManager;
import com.yongjun.tdms.service.expensemanagement.waterFee.WaterFloorFeeManager;

public class EditWaterFloorFeeAction extends PrepareAction {
	private static final long serialVersionUID = 1L;
	private final BuildingManager buildingManager;
	private final FloorManager floorManager;
	private final HouseManager houseManager;
	private final WaterFeeManager waterFeeManager;
	private final WaterFloorFeeManager waterFloorFeeManager;
	private final CodeValueManager codeValueManager;

	private WaterFloorFee waterFloorFee;
	private Building building;
	private Floor floor;
	private WaterFee waterFee;
	private String pagingPage;

	public EditWaterFloorFeeAction(BuildingManager buildingManager, FloorManager floorManager,
			HouseManager houseManager, WaterFeeManager waterFeeManager, CodeValueManager codeValueManager,
			WaterFloorFeeManager waterFloorFeeManager) {
		this.buildingManager = buildingManager;
		this.floorManager = floorManager;
		this.houseManager = houseManager;
		this.waterFeeManager = waterFeeManager;
		this.codeValueManager = codeValueManager;
		this.waterFloorFeeManager = waterFloorFeeManager;
	}

	public void prepare() throws Exception {
		if (hasId("waterFloorFee.id")) {
			this.waterFloorFee = this.waterFloorFeeManager.loadWaterFloorFee(getId("waterFloorFee.id"));
		} else {
			this.waterFloorFee = new WaterFloorFee();
		}
		if (hasId("building.id")) {
			this.building = this.buildingManager.loadBuilding(getId("building.id"));
		}
		if (hasId("floor.id")) {
			this.floor = floorManager.loadFloor(getId("floor.id"));
		}
		if (hasId("waterFee.id")) {
			this.waterFee = waterFeeManager.loadWaterFee(getId("waterFee.id"));
		}
		if(hasId("pagingPage")){
			this.pagingPage = request.getParameter("pagingPage");
		}
	}

	public String save() {
		try {
			SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
			if (hasId("saveAll")) {
				String ids[] = request.getParameterValues("ids");
				for (int i = 0; i < ids.length; i++) {
					WaterFloorFee wff = waterFloorFeeManager.loadWaterFloorFee(Long.parseLong(ids[i]));
					wff.setLastWater(Double.parseDouble(request.getParameter("lastWater" + ids[i])));
					wff.setThisWater(Double.parseDouble(request.getParameter("thisWater" + ids[i])));
					wff.setSumWater(Double.parseDouble(request.getParameter("sumWater" + ids[i])));
					wff.setOutLine(request.getParameter("outLine"+ids[i]));
//					wff.setStarTime(sFormat.parse(request.getParameter("starTime" + ids[i])));
//					wff.setEndTime(sFormat.parse(request.getParameter("endTime" + ids[i])));
					waterFloorFeeManager.storeWaterFloorFee(wff);
					Floor floor = wff.getFloor();
					floor.setWaterdisplay(wff.getThisWater());
					floorManager.storeFloor(floor);
				}
				return "all";
			} else {
				boolean isNew = this.waterFloorFee.isNew();
				if (hasId("building.id")) {
					this.waterFloorFee.setFloor(this.floor);
				}
				this.waterFloorFeeManager.storeWaterFloorFee(this.waterFloorFee);
				if (isNew) {
					addActionMessage(getText("waterFee.add.success"));
					return NEW;
				}
				addActionMessage(getText("waterFee.edit.success"));
				return SUCCESS;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			addActionMessage(getText("waterFee.edit.error"));
			return ERROR;
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

	public WaterFloorFee getWaterFloorFee() {
		return waterFloorFee;
	}

	public Building getBuilding() {
		return building;
	}

	public Floor getFloor() {
		return floor;
	}

	public void setWaterFloorFee(WaterFloorFee waterFloorFee) {
		this.waterFloorFee = waterFloorFee;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public void setFloor(Floor floor) {
		this.floor = floor;
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

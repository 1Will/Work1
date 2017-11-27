package com.yongjun.tdms.presentation.webwork.action.expensemanagement.electricFloorFee;

import java.util.ArrayList;
import java.util.List;

import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.base.house.Building;
import com.yongjun.tdms.model.base.house.Floor;
import com.yongjun.tdms.model.base.house.House;
import com.yongjun.tdms.model.expensemanagement.electricFee.ElectricFee;
import com.yongjun.tdms.model.expensemanagement.electricFee.ElectricFloorFee;
import com.yongjun.tdms.service.base.house.BuildingManager;
import com.yongjun.tdms.service.base.house.FloorManager;
import com.yongjun.tdms.service.base.house.HouseManager;
import com.yongjun.tdms.service.expensemanagement.electricFee.ElectricFeeManager;
import com.yongjun.tdms.service.expensemanagement.electricFee.ElectricFloorFeeManager;

public class EditElectricFloorFeeAction extends PrepareAction {
	private static final long serialVersionUID = 1L;
	private final BuildingManager buildingManager;
	private final FloorManager floorManager;
	private final HouseManager houseManager;
	private final ElectricFeeManager electricFeeManager;
	private final ElectricFloorFeeManager electricFloorFeeManager;
	private final CodeValueManager codeValueManager;

	private ElectricFloorFee electricFloorFee;
	private Building building;
	private Floor floor;
	private ElectricFee electricFee;
	private String pagingPage;

	public EditElectricFloorFeeAction(BuildingManager buildingManager, FloorManager floorManager,
			HouseManager houseManager, ElectricFeeManager electricFeeManager, CodeValueManager codeValueManager,
			ElectricFloorFeeManager electricFloorFeeManager) {
		this.buildingManager = buildingManager;
		this.floorManager = floorManager;
		this.houseManager = houseManager;
		this.electricFeeManager = electricFeeManager;
		this.codeValueManager = codeValueManager;
		this.electricFloorFeeManager = electricFloorFeeManager;
	}

	public void prepare() throws Exception {
		if (hasId("electricFloorFee.id")) {
			this.electricFloorFee = this.electricFloorFeeManager.loadElectricFloorFee(getId("electricFloorFee.id"));
		} else {
			this.electricFloorFee = new ElectricFloorFee();
		}
		if (hasId("building.id")) {
			this.building = this.buildingManager.loadBuilding(getId("building.id"));
		}
		if (hasId("floor.id")) {
			this.floor = floorManager.loadFloor(getId("floor.id"));
		}
		if (hasId("electricFee.id")) {
			this.electricFee = electricFeeManager.loadElectricFee(getId("electricFee.id"));
		}
		if(hasId("pagingPage")){
			this.pagingPage = request.getParameter("pagingPage");
		}
	}

	public String save() {
		if (hasId("saveAll")) {
			String ids[] = request.getParameterValues("ids");
			for (int i = 0; i < ids.length; i++) {
				ElectricFloorFee wff = electricFloorFeeManager.loadElectricFloorFee(Long.parseLong(ids[i]));
				wff.setLastElectricA(Double.parseDouble(request.getParameter("alastElectric" + ids[i])));
				wff.setThisElectricA(Double.parseDouble(request.getParameter("athisElectric" + ids[i])));
				wff.setSumElectricA(Double.parseDouble(request.getParameter("asumElectric" + ids[i])));
				wff.setLastElectricB(Double.parseDouble(request.getParameter("blastElectric" + ids[i])));
				wff.setThisElectricB(Double.parseDouble(request.getParameter("bthisElectric" + ids[i])));
				wff.setSumElectricB(Double.parseDouble(request.getParameter("bsumElectric" + ids[i])));
				wff.setSumElectric(Double.parseDouble(request.getParameter("sumElectric" + ids[i])));
				wff.setOutLine(request.getParameter("outLine"+ids[i]));
				electricFloorFeeManager.storeElectricFloorFee(wff);
				Floor floor = wff.getFloor();
				floor.setAedisplay(wff.getThisElectricA());
				floor.setBedisplay(wff.getThisElectricB());
				floorManager.storeFloor(floor);
			}
			return "all";
		} else {
			boolean isNew = this.electricFloorFee.isNew();
			try {
				if (hasId("building.id")) {
					this.electricFloorFee.setFloor(this.floor);
				}
				this.electricFloorFeeManager.storeElectricFloorFee(this.electricFloorFee);
				// 开始添加楼层的水电费
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

	public ElectricFloorFee getElectricFloorFee() {
		return electricFloorFee;
	}

	public Building getBuilding() {
		return building;
	}

	public Floor getFloor() {
		return floor;
	}

	public void setElectricFloorFee(ElectricFloorFee electricFloorFee) {
		this.electricFloorFee = electricFloorFee;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public void setFloor(Floor floor) {
		this.floor = floor;
	}

	public ElectricFee getElectricFee() {
		return electricFee;
	}

	public void setElectricFee(ElectricFee electricFee) {
		this.electricFee = electricFee;
	}

	public String getPagingPage() {
		return pagingPage;
	}

	public void setPagingPage(String pagingPage) {
		this.pagingPage = pagingPage;
	}

}

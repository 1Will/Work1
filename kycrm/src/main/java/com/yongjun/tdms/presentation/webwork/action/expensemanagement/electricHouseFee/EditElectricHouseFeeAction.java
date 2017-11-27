package com.yongjun.tdms.presentation.webwork.action.expensemanagement.electricHouseFee;

import java.util.ArrayList;
import java.util.List;

import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.base.house.Building;
import com.yongjun.tdms.model.base.house.Floor;
import com.yongjun.tdms.model.base.house.House;
import com.yongjun.tdms.model.expensemanagement.electricFee.ElectricFee;
import com.yongjun.tdms.model.expensemanagement.electricFee.ElectricHouseFee;
import com.yongjun.tdms.service.base.house.BuildingManager;
import com.yongjun.tdms.service.base.house.FloorManager;
import com.yongjun.tdms.service.base.house.HouseManager;
import com.yongjun.tdms.service.expensemanagement.electricFee.ElectricFeeManager;
import com.yongjun.tdms.service.expensemanagement.electricFee.ElectricHouseFeeManager;

public class EditElectricHouseFeeAction extends PrepareAction {
	private static final long serialVersionUID = 1L;
	private final BuildingManager buildingManager;
	private final FloorManager floorManager;
	private final HouseManager houseManager;
	private final ElectricFeeManager electricFeeManager;
	private final ElectricHouseFeeManager electricHouseFeeManager;
	private final CodeValueManager codeValueManager;

	private ElectricHouseFee electricHouseFee;
	private Building building;
	private House house;
	private ElectricFee electricFee;
	private String pagingPage;

	public EditElectricHouseFeeAction(BuildingManager buildingManager, FloorManager floorManager,
			HouseManager houseManager, ElectricFeeManager electricFeeManager, CodeValueManager codeValueManager,
			ElectricHouseFeeManager electricHouseFeeManager) {
		this.buildingManager = buildingManager;
		this.floorManager = floorManager;
		this.houseManager = houseManager;
		this.electricFeeManager = electricFeeManager;
		this.codeValueManager = codeValueManager;
		this.electricHouseFeeManager = electricHouseFeeManager;
	}

	public void prepare() throws Exception {
		if (hasId("electricHouseFee.id")) {
			this.electricHouseFee = this.electricHouseFeeManager.loadElectricHouseFee(getId("electricHouseFee.id"));
		} else {
			this.electricHouseFee = new ElectricHouseFee();
		}
		if (hasId("building.id")) {
			this.building = this.buildingManager.loadBuilding(getId("building.id"));
		}
		if (hasId("house.id")) {
			this.house = houseManager.loadHouse(getId("house.id"));
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
				ElectricHouseFee whf = electricHouseFeeManager.loadElectricHouseFee(Long.parseLong(ids[i]));
				whf.setLastElectricA(Double.parseDouble(request.getParameter("alastElectric" + ids[i])));
				whf.setThisElectricA(Double.parseDouble(request.getParameter("athisElectric" + ids[i])));
				whf.setSumElectricA(Double.parseDouble(request.getParameter("asumElectric" + ids[i])));
				whf.setLastElectricB(Double.parseDouble(request.getParameter("blastElectric" + ids[i])));
				whf.setThisElectricB(Double.parseDouble(request.getParameter("bthisElectric" + ids[i])));
				whf.setSumElectricB(Double.parseDouble(request.getParameter("bsumElectric" + ids[i])));
				whf.setSumElectric(Double.parseDouble(request.getParameter("sumElectric" + ids[i])));
				whf.setOutLine(request.getParameter("outLine"+ids[i]));
				electricHouseFeeManager.storeElectricHouseFee(whf);
				House house = whf.getHouse();
				house.setAedisplay(whf.getThisElectricA());
				house.setBedisplay(whf.getThisElectricB());
				houseManager.storeHouse(house);
			}
			return "all";
		} else {
			boolean isNew = this.electricHouseFee.isNew();
			try {
				if (hasId("building.id")) {
					this.electricHouseFee.setHouse(this.house);
				}
				this.electricHouseFeeManager.storeElectricHouseFee(this.electricHouseFee);
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

	public ElectricHouseFee getElectricHouseFee() {
		return electricHouseFee;
	}

	public Building getBuilding() {
		return building;
	}

	public House getHouse() {
		return house;
	}

	public void setElectricHouseFee(ElectricHouseFee electricHouseFee) {
		this.electricHouseFee = electricHouseFee;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public void setHouse(House house) {
		this.house = house;
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

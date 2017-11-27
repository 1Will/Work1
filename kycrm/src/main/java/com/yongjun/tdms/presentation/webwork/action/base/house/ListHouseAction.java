package com.yongjun.tdms.presentation.webwork.action.base.house;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.base.house.Building;
import com.yongjun.tdms.model.base.house.Floor;
import com.yongjun.tdms.model.base.house.House;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.model.customercontract.contractmanagement.houseList.HouseList;
import com.yongjun.tdms.service.base.house.BuildingManager;
import com.yongjun.tdms.service.base.house.FloorManager;
import com.yongjun.tdms.service.base.house.HouseManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.houseList.HouseListManager;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ListHouseAction extends ValueListAction {
	private static final long serialVersionUID = -7991194195515604965L;

	private final BuildingManager buildingManager;
	private final FloorManager floorManager;
	private final HouseManager houseManager;
	private final HouseListManager houseListManager;
	private final CodeValueManager codeValueManager;
	private final ContractManagementManager contractManagementManager;

	private Building building;
	private Floor floor;
	private House house;
	private List<House> houses;

	public ListHouseAction(BuildingManager buildingManager, FloorManager floorManager, HouseManager houseManager,
			HouseListManager houseListManager, CodeValueManager codeValueManager,
			ContractManagementManager contractManagementManager) {
		this.buildingManager = buildingManager;
		this.floorManager = floorManager;
		this.houseManager = houseManager;
		this.houseListManager = houseListManager;
		this.codeValueManager = codeValueManager;
		this.contractManagementManager = contractManagementManager;
	}

	public void prepare() throws Exception {
		if (hasId("house.id")) {
			this.house = this.houseManager.loadHouse(getId("house.id"));
		}

		if (hasIds("houseIds")) {
			this.houses = houseManager.loadAllHouse(getIds("houseIds"));
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
		try {
			if (hasId("getRest")) {
				List<HouseList> houseLists = houseListManager.loadHouseList();
				List<House> houses = new ArrayList<House>();
				if (houseLists != null && houseLists.size() > 0) {
					for (int i = 0; i < houseLists.size(); i++) {
						if (!"23101".equals(houseLists.get(i).getHouse().getState().getCode())) {
							houses.add(houseLists.get(i).getHouse());
						}
					}
				} else {
					House h = new House();
					h.setId(0L);
					houses.add(h);
				}
				map.put("restHouse", houses);
			}

			
			if (hasId("customerInfo.id")) {
				List<House> houses = new ArrayList<House>();
				List<ContractManagement> cList = this.contractManagementManager.loadByKey("customerInfo.id",
						getId("customerInfo.id"));
				if (cList != null && cList.size() > 0) {
					for (int i = 0; i < cList.size(); i++) {
						// 正在使用的房子
						String key[] = { "contractManagement.id", "isuse" };
						Object value[] = { cList.get(i).getId(), true };
						List<HouseList> houseLists = houseListManager.loadByKeyArray(key, value);
						if (houseLists != null && houseLists.size() > 0) {
							for (int j = 0; j < houseLists.size(); j++) {
								houses.add(houseLists.get(j).getHouse());
							}
						}
					}
				}
				if (houses.size() == 0) {
					House h = new House();
					h.setId(0L);
					houses.add(h);
				}
				map.put("selectHouse", houses);

			}
			
			
			if (hasId("contractManagement.id")) {
				String key[] = { "contractManagement.id", "isuse" };
				Object value[] = { getId("contractManagement.id"), true };
				List<HouseList> houseLists = houseListManager.loadByKeyArray(key, value);
				List<House> houses = new ArrayList<House>();
				if (houseLists != null && houseLists.size() > 0) {
					for (int j = 0; j < houseLists.size(); j++) {
						houses.add(houseLists.get(j).getHouse());
					}
				}
				if (houses.size() == 0) {
					House h = new House();
					h.setId(0L);
					houses.add(h);
				}
				map.put("selectHouse", houses);
			}
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return map;
	}

	protected String getAdapterName() {
		return "houseHQL";
	}

	public String execute() {
		if (isDelete()) {
			delete();
		}
		return "success";
	}

	private String delete() {
		try {
			this.houseManager.deleteAllHouse(houses);
			addActionMessage(getText("houses.delete.success"));
			return "success";
		} catch (RuntimeException e) {
			addActionMessage(getText("houses.delete.error"));
		}
		return "error";
	}

	public List getAllState() {
		try {
			CodeValue codeValue = (CodeValue) this.codeValueManager.loadByKey("code", "231").get(0);
			List list = this.codeValueManager.loadByKey("parentCV", codeValue.getId());
			if (list != null) {
				CodeValue cv = new CodeValue();
				cv.setName("所有");
				list.add(0, cv);
				return list;
			}
			return new ArrayList();
		} catch (DaoException e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}

	public List getAllCategory() {
		try {
			CodeValue codeValue = (CodeValue) this.codeValueManager.loadByKey("code", "229").get(0);
			List list = this.codeValueManager.loadByKey("parentCV", codeValue.getId());
			if (list != null) {
				CodeValue cv = new CodeValue();
				cv.setName("所有");
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

	public List<House> getHouses() {
		return houses;
	}

	public void setHouses(List<House> houses) {
		this.houses = houses;
	}

}

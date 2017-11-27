package com.yongjun.tdms.presentation.webwork.action.expensemanagement.airHouseFee;

import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.base.house.House;
import com.yongjun.tdms.model.expensemanagement.airFee.AirFee;
import com.yongjun.tdms.model.expensemanagement.airFee.AirHouseFee;
import com.yongjun.tdms.service.base.house.BuildingManager;
import com.yongjun.tdms.service.base.house.FloorManager;
import com.yongjun.tdms.service.base.house.HouseManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.houseList.HouseListManager;
import com.yongjun.tdms.service.expensemanagement.airFee.AirFeeManager;
import com.yongjun.tdms.service.expensemanagement.airFee.AirHouseFeeManager;

public class EditAirHouseFeeAction extends PrepareAction {
	private static final long serialVersionUID = 1L;
	private final BuildingManager buildingManager;
	private final FloorManager floorManager;
	private final HouseManager houseManager;
	private final AirHouseFeeManager airHouseFeeManager;
	private final CodeValueManager codeValueManager;
	private final HouseListManager houseListManager;
	private final AirFeeManager airFeeManager;

	private AirFee airFee;
	private AirHouseFee airHouseFee;

	public EditAirHouseFeeAction(BuildingManager buildingManager, FloorManager floorManager, HouseManager houseManager,
			AirHouseFeeManager airHouseFeeManager, CodeValueManager codeValueManager, HouseListManager houseListManager,
			AirFeeManager airFeeManager) {
		this.buildingManager = buildingManager;
		this.floorManager = floorManager;
		this.houseManager = houseManager;
		this.airHouseFeeManager = airHouseFeeManager;
		this.codeValueManager = codeValueManager;
		this.houseListManager = houseListManager;
		this.airFeeManager = airFeeManager;
	}

	public void prepare() throws Exception {
		if(hasId("airFee.id")){
			this.airFee = airFeeManager.loadAirFee(getId("airFee.id"));
		} 
	}

	public String save() {
		try {
			String ids[] = request.getParameterValues("ids");
			if (ids != null && ids.length >= 0) {
				for (int i = 0; i < ids.length; i++) {
					AirHouseFee ahFee = airHouseFeeManager.loadAirHouseFee(Long.parseLong(ids[i]));
					ahFee.setLastAir(Double.parseDouble(request.getParameter("lastAir" + ids[i])));
					ahFee.setThisAir(Double.parseDouble(request.getParameter("thisAir" + ids[i])));
					ahFee.setSumAir(Double.parseDouble(request.getParameter("sumAir" + ids[i])));
					ahFee.setOutLine((request.getParameter("outLine" + ids[i])));
					airHouseFeeManager.storeAirHouseFee(ahFee);
					House house = ahFee.getHouse();
					house.setAirdisplay(ahFee.getThisAir());
					houseManager.storeHouse(house);
				}
			}
			addActionMessage(getText("airHouseFee.edit.success"));
			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
			addActionMessage(getText("airHouseFee.edit.error"));
			return ERROR;
		}
	}

	public AirHouseFee getAirHouseFee() {
		return airHouseFee;
	}

	public void setAirHouseFee(AirHouseFee airHouseFee) {
		this.airHouseFee = airHouseFee;
	}

	public AirFee getAirFee() {
		return airFee;
	}

	public void setAirFee(AirFee airFee) {
		this.airFee = airFee;
	}
}

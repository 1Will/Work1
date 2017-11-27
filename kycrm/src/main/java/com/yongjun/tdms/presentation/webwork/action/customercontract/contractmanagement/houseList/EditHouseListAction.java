package com.yongjun.tdms.presentation.webwork.action.customercontract.contractmanagement.houseList;

import java.text.DecimalFormat;

import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.base.house.House;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.model.customercontract.contractmanagement.houseList.HouseList;
import com.yongjun.tdms.service.base.house.HouseManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.houseList.HouseListManager;

public class EditHouseListAction extends PrepareAction {
	private static final long serialVersionUID = 1L;
	private final HouseListManager houseListManager;
	private final ContractManagementManager contractManagementManager;
	private final HouseManager houseManager;
	private final CodeValueManager codeValueManager;
	private HouseList houseList;
	private ContractManagement contractManagement;
	public DecimalFormat format = new DecimalFormat("0.00");

	public EditHouseListAction(HouseListManager houseListManager, ContractManagementManager contractManagementManager,
			HouseManager houseManager, CodeValueManager codeValueManager) {
		this.houseListManager = houseListManager;
		this.contractManagementManager = contractManagementManager;
		this.houseManager = houseManager;
		this.codeValueManager = codeValueManager;
	}

	public void prepare() throws Exception {
		if (hasId("houseList.id")) {
			this.houseList = this.houseListManager.loadHouseList(getId("houseList.id"));
			this.contractManagement = this.houseList.getContractManagement();
		} else {
			this.houseList = new HouseList();
			this.contractManagement = this.contractManagementManager
					.loadContractManagement(getId("contractManagement.id"));
			this.houseList.setContractManagement(this.contractManagement);
			this.houseList.setStartTime(this.contractManagement.getStartTime());
		}
	}

	public String save() throws Exception {
		boolean isNew = this.houseList.isNew();
		if (hasId("house.id")) {
			House house = this.houseManager.loadHouse(getId("house.id"));
			this.houseList.setHouse(house);
		}
		try {
			if("06603".equals(contractManagement.getState().getCode())){
				this.houseList.setIsuse(true);//合同在执行中，本记录有效
			}else {
				this.houseList.setIsuse(false);
			}
			this.houseListManager.storeHouseList(this.houseList);
			//修改合同金额
			ContractManagement cm = this.houseList.getContractManagement();
			cm.setContractMoney(Double.valueOf(format.format(this.houseListManager.getSum(cm.getId()))));
			//修改合同面积
			Double square=cm.getSquare()+this.houseList.getHouse().getSquare();
			cm.setSquare(Double.valueOf(format.format(this.houseListManager.getAllSquare(cm.getId()))));
			this.contractManagementManager.storeContractManagement(cm);
			//修改房子状态为在用
			House house = houseList.getHouse();
			house.setState(codeValueManager.loadByKey("code", "23102").get(0));
			houseManager.storeHouse(house);
			if (isNew) {
				addActionMessage(getText("houseList.add.success"));
				return NEW;
			}
			addActionMessage(getText("houseList.edit.success"));
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			if (isNew) {
				addActionMessage(getText("houseList.add.error"));
				return ERROR;
			}
			addActionMessage(getText("houseList.edit.error"));
			return ERROR;
		}
	}

	public HouseList getHouseList() {
		return houseList;
	}

	public ContractManagement getContractManagement() {
		return contractManagement;
	}

	public void setHouseList(HouseList houseList) {
		this.houseList = houseList;
	}

	public void setContractManagement(ContractManagement contractManagement) {
		this.contractManagement = contractManagement;
	}

}

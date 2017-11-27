package com.yongjun.tdms.presentation.webwork.action.customercontract.contractmanagement.houseListBack;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.base.house.House;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.model.customercontract.contractmanagement.houseListBack.HouseListBack;
import com.yongjun.tdms.model.customercontract.plan.ReturnPlan;
import com.yongjun.tdms.service.base.house.HouseManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.houseListBack.HouseListBackManager;
import com.yongjun.tdms.service.customercontract.plan.ReturnPlanManager;

public class EditHouseListBackAction extends PrepareAction {
	private static final long serialVersionUID = 1L;
	private final HouseListBackManager houseListBackManager;
	private final ContractManagementManager contractManagementManager;
	private final HouseManager houseManager;
	private final CodeValueManager codeValueManager;
	private HouseListBack houseListBack;
	private ContractManagement contractManagement;
	private final ReturnPlanManager returnPlanManager;
	private ReturnPlan returnPlan;
	public EditHouseListBackAction(HouseListBackManager houseListBackManager, ContractManagementManager contractManagementManager,
			HouseManager houseManager, CodeValueManager codeValueManager,ReturnPlanManager returnPlanManager) {
		this.houseListBackManager = houseListBackManager;
		this.contractManagementManager = contractManagementManager;
		this.houseManager = houseManager;
		this.codeValueManager = codeValueManager;
		this.returnPlanManager= returnPlanManager;
	}

	public void prepare() throws Exception {
		if (hasId("houseListBack.id")) {
			this.houseListBack = this.houseListBackManager.loadHouseListBack(getId("houseListBack.id"));
			this.contractManagement = this.houseListBack.getContractManagement();
		} else {
			this.houseListBack = new HouseListBack();
			this.contractManagement = this.contractManagementManager.loadContractManagement(Long.parseLong(request.getParameter("contractManagement.id")));
			this.houseListBack.setContractManagement(this.contractManagement);
		}
	}

	public String save() throws Exception {
		boolean isNew = this.houseListBack.isNew();
		if (hasId("house.id")) {
			House house = this.houseManager.loadHouse(getId("house.id"));
			this.houseListBack.setHouse(house);
		}
		if(hasId("state.id")){
			this.houseListBack.setState(this.codeValueManager.loadCodeValue((getId("state.id"))));
		}
		if(hasId("backType.id")){
			this.houseListBack.setBackType(this.codeValueManager.loadCodeValue((getId("backType.id"))));
		}
		try {
//			if("06603".equals(contractManagement.getState().getCode())){
//				this.houseList.setIsuse(true);//合同在执行中，本记录有效
//			}else {
//				this.houseList.setIsuse(false);
//			}
			if(isNew){
				List <HouseListBack>houseListBacks =houseListBackManager.loadByKey("contractManagement", this.contractManagement.getId());
				if(houseListBacks!=null&&houseListBacks.size()>0){
					long houseId=Long.parseLong(request.getParameter("house.id"));
					for(HouseListBack h:houseListBacks){
						if((long)h.getHouse().getId()==houseId){
							this.houseListBack.setId(h.getId());
							addActionMessage(getText("houseList.add.again"));
							return ERROR;
						}
					}
				}
			}
			
			this.houseListBackManager.storeHouseListBack(this.houseListBack);
			//修改合同金额
			ContractManagement cm = this.houseListBack.getContractManagement();
			DecimalFormat format = new DecimalFormat("0.00");
			cm.setBackMoney(new Double(format.format(this.houseListBackManager.getSum(cm.getId()))));
			this.contractManagementManager.storeContractManagement(cm);
			//修改合同收款计划
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

	
	
	
	public List getAllState() {
		try {
			CodeValue codeValue = (CodeValue) this.codeValueManager.loadByKey("code", "235").get(0);
			List list = this.codeValueManager.loadByKey("parentCV", codeValue.getId());
			if (list != null) {
				CodeValue cv = new CodeValue();
				list.add(0, cv);
				return list;
			}
			return new ArrayList();
		} catch (DaoException e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}
	public List getAllBackType() {
		try {
			CodeValue codeValue = (CodeValue) this.codeValueManager.loadByKey("code", "236").get(0);
			List list = this.codeValueManager.loadByKey("parentCV", codeValue.getId());
			if (list != null) {
				CodeValue cv = new CodeValue();
				list.add(0, cv);
				return list;
			}
			return new ArrayList();
		} catch (DaoException e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}
	
	
	public HouseListBack getHouseListBack() {
		return houseListBack;
	}

	public void setHouseListBack(HouseListBack houseListBack) {
		this.houseListBack = houseListBack;
	}

	public ContractManagement getContractManagement() {
		return contractManagement;
	}

	public void setContractManagement(ContractManagement contractManagement) {
		this.contractManagement = contractManagement;
	}

	public ReturnPlan getReturnPlan() {
		return returnPlan;
	}

	public void setReturnPlan(ReturnPlan returnPlan) {
		this.returnPlan = returnPlan;
	}
	
}


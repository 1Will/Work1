package com.yongjun.tdms.presentation.webwork.action.customercontract.contractmanagement.houseListBack;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;

import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.base.house.House;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.model.customercontract.contractmanagement.houseList.HouseList;
import com.yongjun.tdms.model.customercontract.contractmanagement.houseListBack.HouseListBack;
import com.yongjun.tdms.model.customercontract.plan.ReturnPlan;
import com.yongjun.tdms.service.base.house.HouseManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.houseList.HouseListManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.houseListBack.HouseListBackManager;
import com.yongjun.tdms.service.customercontract.plan.ReturnPlanManager;

public class ListHouseListBackAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private final HouseListBackManager houseListBackManager;
	private final ContractManagementManager contractManagementManager;
	private final HouseManager houseManager;
	private final CodeValueManager codeValueManager;
	private final ReturnPlanManager returnPlanManager;
	private List<HouseListBack> houseListBacks;
	private ContractManagement contractManagement;

	public ListHouseListBackAction(HouseListBackManager houseListBackManager, ContractManagementManager contractManagementManager,HouseManager houseManager,
			CodeValueManager codeValueManager,ReturnPlanManager returnPlanManager) {
		this.houseListBackManager = houseListBackManager;
		this.contractManagementManager = contractManagementManager;
		this.houseManager = houseManager;
		this.codeValueManager = codeValueManager;
		this.returnPlanManager=returnPlanManager;
	}

	public void prepare() throws Exception {
		if ((null == this.houseListBacks) && (hasIds("houseListBackIds"))) {
			this.houseListBacks = this.houseListBackManager.loadAllHouseListBack(getIds("houseListBackIds"));
		}
	}

	protected String getAdapterName() {
		return "houseLisBacktHQL";
	}

	@SuppressWarnings("rawtypes")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		return map;
	}

	public String execute() throws Exception {
		if (isDelete()) {
//			String flag="flag";
//			Cookie cookie = new Cookie("listHouseFlag", flag);  
//			System.out.println(cookie);  
//	        cookie.setMaxAge(24 * 60 * 60);  
//	        response.addCookie(cookie);  
			delete();
		}
		return "success";
	}

	public String delete() {
		try {
			for (int i = 0; i < houseListBacks.size(); i++) {
				//修改合同金额
				ContractManagement cm = houseListBacks.get(i).getContractManagement();
				DecimalFormat format = new DecimalFormat("0.00");
				cm.setBackMoney(new Double(format.format(cm.getBackMoney()-houseListBacks.get(i).getBackRent())));
				this.contractManagementManager.storeContractManagement(cm);
// 				House house = houseListBacks.get(i).getHouse();
//				house.setState(codeValueManager.loadByKey("code", "23101").get(0));
//                double contractMoney = this.contractManagement.getContractMoney()-houseLists.get(i).getSum();	
//			    double square=this.contractManagement.getSquare()-house.getSquare(); 	
//			    this.contractManagement.setContractMoney(contractMoney);
//			    this.contractManagement.setSquare(square);
//			    contractManagementManager.storeContractManagement(this.contractManagement);
//			    houseManager.storeHouse(house);
			}
			this.houseListBackManager.deleteAllHouseListBack(this.houseListBacks);
			addActionMessage(getText("houseList.delete.success"));
			return "success";
		} catch (Exception e) {
			addActionMessage(getText("houseList.delete.error"));
		}
		return "error";
	}

	public List<HouseListBack> getHouseListBacks() {
		return houseListBacks;
	}

	public void setHouseListBacks(List<HouseListBack> houseListBacks) {
		this.houseListBacks = houseListBacks;
	}

	public ContractManagement getContractManagement() {
		return contractManagement;
	}

	public void setContractManagement(ContractManagement contractManagement) {
		this.contractManagement = contractManagement;
	}

}

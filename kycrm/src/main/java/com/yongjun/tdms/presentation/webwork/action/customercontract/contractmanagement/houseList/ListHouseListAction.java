package com.yongjun.tdms.presentation.webwork.action.customercontract.contractmanagement.houseList;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;

import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.base.house.House;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.model.customercontract.contractmanagement.houseList.HouseList;
import com.yongjun.tdms.model.customercontract.plan.ReturnPlan;
import com.yongjun.tdms.service.base.house.HouseManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.houseList.HouseListManager;
import com.yongjun.tdms.service.customercontract.plan.ReturnPlanManager;

public class ListHouseListAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private final HouseListManager houseListManager;
	private final ContractManagementManager contractManagementManager;
	private final HouseManager houseManager;
	private final CodeValueManager codeValueManager;
	private final ReturnPlanManager returnPlanManager;
	private List<HouseList> houseLists;
	private ContractManagement contractManagement;
	private ReturnPlan returnPlan;
	private String returnflag;
    private String popWindowFlag;
	public DecimalFormat format = new DecimalFormat("0.00");
	
	public ListHouseListAction(HouseListManager houseListManager, ContractManagementManager contractManagementManager,HouseManager houseManager,
			CodeValueManager codeValueManager,ReturnPlanManager returnPlanManager) {
		this.houseListManager = houseListManager;
		this.contractManagementManager = contractManagementManager;
		this.houseManager = houseManager;
		this.codeValueManager = codeValueManager;
		this.returnPlanManager=returnPlanManager;
	}

	public void prepare() throws Exception {
		if ((null == this.houseLists) && (hasIds("houseListIds"))) {
			this.houseLists = this.houseListManager.loadAllHouseList(getIds("houseListIds"));
		}
        if(request.getParameter("popWindowFlag")!=null&&request.getParameter("popWindowFlag").length()>0){
        	this.popWindowFlag=request.getParameter("popWindowFlag");
        }
		if (hasId("contractManagement.id")) {
			this.contractManagement = contractManagementManager.loadContractManagement(getId("contractManagement.id"));
			if(returnPlanManager.loadByKey("contractManagement",contractManagement)!=null&&returnPlanManager.loadByKey("contractManagement",contractManagement).size()>0 ){
				this.returnPlan= returnPlanManager.loadByKey("contractManagement",contractManagement).get(0);
				if(returnPlan!=null){
					this.returnflag="returnflag";
				}
			}
		}
		
	}

	protected String getAdapterName() {
		return "houseListHQL";
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		if(hasId("houseCode")){
			String code[] = request.getParameter("houseCode").split(",");
			List<String> houseCode = new ArrayList<String>();
			for (int i = 0; i < code.length; i++) {
				houseCode.add(code[i]);
			}
			if(houseCode.size()==0){
				houseCode.add("hehe");
			}
			map.put("houseCode", houseCode);
		}
		return map;
	}

	public String execute() throws Exception {
		if (isDelete()) {
			String flag="flag";
			Cookie cookie = new Cookie("listHouseFlag", flag);  
			System.out.println(cookie);  
	        cookie.setMaxAge(24 * 60 * 60);  
	        response.addCookie(cookie);  
			delete();
		}
		return "success";
	}

	public String delete() {
		try {
			for (int i = 0; i < houseLists.size(); i++) {
 				House house = houseLists.get(i).getHouse();
				house.setState(codeValueManager.loadByKey("code", "23101").get(0));
                double contractMoney =Double.valueOf(format.format(this.contractManagement.getContractMoney()-houseLists.get(i).getSum()));	
			    double square=Double.valueOf(format.format(this.contractManagement.getSquare()-house.getSquare())); 	
			    this.contractManagement.setContractMoney(contractMoney);
			    this.contractManagement.setSquare(square);
			    contractManagementManager.storeContractManagement(this.contractManagement);
			    houseManager.storeHouse(house);
			}
			this.houseListManager.deleteAllHouseList(this.houseLists);
			addActionMessage(getText("houseList.delete.success"));
			return "success";
		} catch (Exception e) {
			addActionMessage(getText("houseList.delete.error"));
		}
		return "error";
	}

	public List<HouseList> getHouseLists() {
		return houseLists;
	}

	public ContractManagement getContractManagement() {
		return contractManagement;
	}

	public void setHouseLists(List<HouseList> houseLists) {
		this.houseLists = houseLists;
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

	public String getReturnflag() {
		return returnflag;
	}

	public void setReturnflag(String returnflag) {
		this.returnflag = returnflag;
	}

	public String getPopWindowFlag() {
		return popWindowFlag;
	}

	public void setPopWindowFlag(String popWindowFlag) {
		this.popWindowFlag = popWindowFlag;
	}

}

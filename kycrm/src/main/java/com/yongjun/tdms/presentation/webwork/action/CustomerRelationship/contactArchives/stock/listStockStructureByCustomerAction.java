package com.yongjun.tdms.presentation.webwork.action.CustomerRelationship.contactArchives.stock;

import java.util.List;
import java.util.Map;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.CustomerRelationship.financialInformation.FinancialInformation;
import com.yongjun.tdms.model.CustomerRelationship.stock.StockStructure;
import com.yongjun.tdms.service.CustomerRelationship.stockStructure.StockStructureManager;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class listStockStructureByCustomerAction extends ValueListAction{

	private static final long serialVersionUID = 1L;
	private final StockStructureManager stockStructureManager;
	private List<StockStructure> cas;
	private Long customerId;
	
	public listStockStructureByCustomerAction(StockStructureManager stockStructureManager){
		this.stockStructureManager=stockStructureManager;
		
	}

	public void prepare()throws Exception{
		if(hasIds("stockStructureIds")){
			this.cas=this.stockStructureManager.loadAllStockStructure(getIds("stockStructureIds"));
			
		}
		if (hasId("customerInfo.id")) {
			this.customerId = getId("customerInfo.id");
			setFirst(false);
		}
	}
	@Override
	protected String getAdapterName() {
		
		return "stockStructureHQL";
	}
	
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();

		return map;
	}

	public String execute() throws Exception {
		if (isDisabled()) {
			disabled();
		}
		if (isEnable()) {
			enabled();
		}
		if (isDelete()) {
			delete();
		}
		return "success";
	}
	
	public String delete() {
		try {
			this.stockStructureManager.deleteAllStockStructure(this.cas);
			addActionMessage(getText("stockStructure.delete.success"));
			return "success";
		} catch (RuntimeException e) {
			addActionMessage(getText("stockStructure.disabled.error"));
		}
		return "error";
	}
	
	//失效
	private String disabled() {
		try {
			this.stockStructureManager.disabledAllStockStructure(this.cas);
			addActionMessage(getText("stockStructur.disabled.success"));
			return "success";
		} catch (RuntimeException e) {
			addActionMessage(getText("stockStructur.disabled.error"));
		}
		return "error";
	}
	
	//还原（激活）
	private String enabled() {
		try {
			this.stockStructureManager.enabledAllStockStructure(this.cas);
			addActionMessage(getText("stockStructure.enabled.success"));
			return "success";
		} catch (RuntimeException e) {
			e.printStackTrace();
			addActionMessage(getText("stockStructure.enabled.error"));
		}
		return "error";
	}
	public List<StockStructure> getCas() {
		return this.cas;
	}

	public void setCas(List<StockStructure> cas) {
		this.cas = cas;
	}


	public Long getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	

}

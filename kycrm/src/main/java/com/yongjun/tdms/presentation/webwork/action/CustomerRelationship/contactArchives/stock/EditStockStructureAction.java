package com.yongjun.tdms.presentation.webwork.action.CustomerRelationship.contactArchives.stock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.CustomerRelationship.financialInformation.FinancialInformation;
import com.yongjun.tdms.model.CustomerRelationship.stock.StockStructure;
import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
import com.yongjun.tdms.service.CustomerRelationship.financialInformation.FinancialInformationManager;
import com.yongjun.tdms.service.CustomerRelationship.stockStructure.StockStructureManager;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class EditStockStructureAction extends PrepareAction {
	private static final long serialVersionUID = 1L;
	private final CodeValueManager codeValueManager;
	private final  StockStructureManager  stockStructureManager;
	private final CustomerInfoManager customerInfoManager;
	private StockStructure stockStructure;
	private long customerInfoId;
	private CustomerInfo customer;

	
	
	
	public EditStockStructureAction(StockStructureManager stockStructureManager,
			CustomerInfoManager customerInfoManager,CodeValueManager codeValueManager) {
		this.stockStructureManager = stockStructureManager;
		this.customerInfoManager = customerInfoManager;
		this.codeValueManager =codeValueManager;
	}
	public void prepare() throws Exception {
		if (null == this.stockStructure) {
			if (hasId("stockStructure.id")) {
				this.stockStructure = this.stockStructureManager.loadStockStructure(getId("stockStructure.id"));

			} else {
				this.stockStructure = new StockStructure();
			}
		}

		if (hasId("customerInfo.id")) {
			this.customerInfoId = getId("customerInfo.id");
		} 

		
	}
	//出资方式列表
			public List<CodeValue> getAllContributive() {
				try {
					List<CodeValue> codes = new ArrayList<CodeValue>();
					List<CodeValue> one = this.codeValueManager.loadByKey("code", String.valueOf("218"));
					if ((null != one) && (one.size() > 0)) {
						List<CodeValue> list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());
						if ((null != list) && (list.size() > 0)) {
							codes.addAll(list);
						}
					}
					return codes;
				} catch (Exception e) {
					e.printStackTrace();
					return new ArrayList();
				}
			}
	//所有权列表
			public List<CodeValue> getAllOwnership() {
				try {
					List<CodeValue> codes = new ArrayList<CodeValue>();
					List<CodeValue> one = this.codeValueManager.loadByKey("code", String.valueOf("227"));
					if ((null != one) && (one.size() > 0)) {
						List<CodeValue> list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());
						if ((null != list) && (list.size() > 0)) {
							codes.addAll(list);
						}
					}
					return codes;
				} catch (Exception e) {
					e.printStackTrace();
					return new ArrayList();
				}
			}
	
	
	public String save() {
		boolean isNew = this.stockStructure.isNew();
		
			if (hasId("customerInfo.id")&&isNew) {
				this.customer = this.customerInfoManager.loadCustomerInfo(getId("customerInfo.id"));
				this.stockStructure.setCustomerName(this.customer);
			} 
		try {
			if (!StringUtils.isEmpty("stockStructure.ownership")) {
				String bs = this.request.getParameter("stockStructure.ownership");
				this.stockStructure.setOwnership(codeValueManager.loadByKey("id", Long.parseLong(bs)).get(0));
			}
//			if (!StringUtils.isEmpty("contributive.id")) {
//				String bs= this.request.getParameter("stockStructure.contributive");
//				this.stockStructure.setContributive(codeValueManager.loadByKey("id", Long.parseLong(bs)).get(0));
//			}
			if(this.hasId("contributive.id")){
				this.stockStructure.setContributive(codeValueManager.loadByKey("id",this.getId("contributive.id")).get(0));
				
				
			}
			this.stockStructureManager.storeStockStructure(this.stockStructure);
			
		} catch (Exception e) {
			e.printStackTrace();
			if (isNew) {
			addActionMessage(getText("new.stockStructure.error",Arrays.asList(new Object[] { this.stockStructure.getStockholderName() })));
			return "error";
			}
			addActionMessage(getText("alert.stockStructure.error",Arrays.asList(new Object[] { this.stockStructure.getStockholderName() })));
			return "error";
		}
		
	
		
		if (isNew) {
			addActionMessage(getText("stockStructure.add.success",
					Arrays.asList(new Object[] { this.stockStructure.getCustomerName()})));
			return "new";
		}else {
			addActionMessage(getText("stockStructure.update.success",
					Arrays.asList(new Object[] { this.stockStructure.getCustomerName()})));
			return "new";
		}
	}

	
	

	public long getCustomerInfoId() {
		return customerInfoId;
	}

	public void setCustomerInfoId(long customerInfoId) {
		this.customerInfoId = customerInfoId;
	}

	public StockStructure getStockStructure() {
		return stockStructure;
	}

	public void setStockStructure(StockStructure stockStructure) {
		this.stockStructure = stockStructure;
	}

	public CustomerInfo getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerInfo customer) {
		this.customer = customer;
	}

	public CustomerInfoManager getCustomerInfoManager() {
		return customerInfoManager;
	}



	public StockStructureManager getStockStructureManager() {
		return stockStructureManager;
	}

	


}

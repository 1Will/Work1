  /*
 * Copyright (c) 2001-2010 YongJun Technology Pte.,Ltd. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of YongJun
 * Technology Pte.,Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with YongJun.
 * 
 * YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 */
package com.yongjun.tdms.presentation.webwork.action.prophase.factory;

import java.util.List;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.prophase.supplier.Supplier;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.country.CountryManager;
import com.yongjun.tdms.service.prophase.supplier.SupplierManager;

/**
 * <p>Title: com.yongjun.tdms.presentation.webwork.action.prophase.business.ListFactoryAction.java</p>
 * <p>Description: the ListFactoryAction class</p>
 * <p>Copyright: Copyright (c) 2010 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * <p>@author jyang@yj-technology.com</p>
 * <p>@version $ Id:ListFactoryAction.java 2010-10-7 jyang $</p>
 */
public class ListFactoryAction extends ValueListAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 366256636773651686L;
	private final SupplierManager supplierManager;
	private final CodeValueManager codeValueManager;
	private final CountryManager countryManager;
	private List<Supplier> factory;
	
	public ListFactoryAction(SupplierManager supplierManager,CodeValueManager codeValueManager,CountryManager countryManager){
		this.supplierManager = supplierManager;
		this.codeValueManager = codeValueManager;
		this.countryManager = countryManager;
	}
	
	public void prepare(){
		if (null == this.factory && this.hasIds("factoryIds")) {
			this.factory = this.supplierManager.loadAllSuppliers(this
					.getIds("factoryIds"));
		}
	}
	
	@Override
	protected String getAdapterName() {
		return "factoryList";
	}

	public String execute(){
		if (this.isDisabled()) {
			return disabled();
		}
		if (this.isEnable()) {
			return this.enabled();
		}

		return SUCCESS;
	}
	
	public String disabled() {
		this.supplierManager.disabledAllSuppliers(factory);
		this.addActionMessage(this.getText("factory.disabled.success"));
		return SUCCESS;
	}
	
	public String enabled() {
		this.supplierManager.enabledAllSuppliers(factory);
		this.addActionMessage(this.getText("factory.enabled.success"));
		return SUCCESS;
	}
	
	//厂家类别
	public List getFactoryType(){
		return codeValueManager.createSelectCodeValues(this
				.getText("select.option.all"), CodeConstants.SUPPLIER_TYPE);
	}
	
	//国家
	public List getFactoryCountry(){
		return countryManager.loadAllCountries();
	}
}

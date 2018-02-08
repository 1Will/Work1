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

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.sequence.service.SequenceManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.prophase.supplier.Supplier;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.country.CountryManager;
import com.yongjun.tdms.service.prophase.supplier.SupplierManager;

/**
 * <p>Title: com.yongjun.tdms.presentation.webwork.action.prophase.business.EditFactoryAction.java</p>
 * <p>Description: the EditFactoryAction class</p>
 * <p>Copyright: Copyright (c) 2010 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * <p>@author jyang@yj-technology.com</p>
 * <p>@version $ Id:EditFactoryAction.java 2010-10-9 jyang $</p>
 */
public class EditFactoryAction extends PrepareAction{

	private static final long serialVersionUID = 2312958186100997865L;
	private final SupplierManager supplierManager;
	private final CodeValueManager codeValueManager;
	private final CountryManager countryManager;
	private final SequenceManager sequenceManager;
	private Supplier factory;   //生产厂家对象
    private String requestSourceType;   //页面请求来源，type:PopupWin|Win;popupWin表示弹出页面；win表示非弹出页面
	
	public EditFactoryAction(SupplierManager supplierManager,CodeValueManager codeValueManager,CountryManager countryManager,SequenceManager sequenceManager){
		this.supplierManager = supplierManager;
		this.codeValueManager = codeValueManager;
		this.countryManager = countryManager;
		this.sequenceManager = sequenceManager;
	}
	
	public void prepare() throws Exception {
		if(null == factory){
			if(this.hasId("factory.id")){
				this.factory = this.supplierManager.loadSupplier(this
						.getId("factory.id"));
			}else{
				factory = new Supplier();
			}
		}
		if (!StringUtils.isEmpty(request.getParameter("companyType.id"))) {//获得公司性质
			factory.setCompanyType(this.codeValueManager.loadCodeValue(this
					.getId("companyType.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("supplierType.id"))) {//获得生产厂家类别
			factory.setSupplierType(this.codeValueManager.loadCodeValue(this
					.getId("supplierType.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("tradeType.id"))) {//获得行业类别
			factory.setTradeType(this.codeValueManager.loadCodeValue(this
					.getId("tradeType.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("country.id"))) {//获得国家对象
			factory.setCountry(this.countryManager.loadCountry(this
					.getId("country.id")));
		}
	}

	public String save(){
		boolean isNew = this.factory.isNew();
		factory.setCategory("FACTORY"); //标识类别是“生产厂家”
		if(isNew){
			String supplierNo = (String) sequenceManager.generate("-");
			factory.setSupplierNo(supplierNo);
		}
		this.supplierManager.storeSupplier(factory);
		if (isNew) {
			this.addActionMessage(this.getText("factory.add.success", Arrays
					.asList(new Object[] { factory.getName() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText("factory.edit.success", Arrays
					.asList(new Object[] { factory.getName() })));
			return SUCCESS;
		}
	}
	
	//公司性质
	public List getCompanyType(){
		return codeValueManager.createSelectCodeValues(this
				.getText("select.option.all"), CodeConstants.COMPANY_TYPE);
	}
	
	//厂家类别
	public List getFactoryType(){
		return codeValueManager.createSelectCodeValues(this
				.getText("select.option.all"), CodeConstants.SUPPLIER_TYPE);
	}
	
	//行业
	public List getFactoryTrade(){
		return codeValueManager.createSelectCodeValues(this
				.getText("select.option.all"), CodeConstants.TRADE_TYPE);
	}
	
	//国家
	public List getFactoryCountry(){
		return countryManager.loadAllCountries();
	}

	public Supplier getFactory() {
		return factory;
	}

	public void setFactory(Supplier factory) {
		this.factory = factory;
	}

	public String getRequestSourceType() {
		return requestSourceType;
	}

	public void setRequestSourceType(String requestSourceType) {
		this.requestSourceType = requestSourceType;
	}
	
	
}

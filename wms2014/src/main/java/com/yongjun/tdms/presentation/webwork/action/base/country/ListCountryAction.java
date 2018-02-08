/*
 * Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All Rights Reserved.
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
package com.yongjun.tdms.presentation.webwork.action.base.country;

import java.util.List;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.base.country.Country;
import com.yongjun.tdms.service.base.country.CountryManager;

/**
 * @author qs
 * @version $Id: ListCountryAction.java 8881 2007-12-02 03:05:28Z qsun $
 */
public class ListCountryAction extends ValueListAction {
	private static final long serialVersionUID = -8808626930458362305L;
	private final CountryManager countryManager;
	private List<Country> countries;
	
	public ListCountryAction(CountryManager countryManager) {
		this.countryManager = countryManager;
	}
	/*
	 *获取页面参数<b>countryIds</b>,如果存在，就根据ID获取国家
	 */
	public void prepare() throws Exception {
		if(null == this.countries && this.hasId("countryIds")){
			this.countries = this.countryManager.loadAllCountry(this.getIds("countryIds"));
		}
	}
	/*
	 * 页面执行，如果选择有效 就调用disabled函数
	 * 如果选择无效 就调用enabled函数
	 */
	public String execute() throws Exception {
		if(this.isDisabled()){
			this.disabled();
		}
		if(this.isEnable()){
			this.enabled();
		}
		return SUCCESS;
	}
	/*
	 * 选择的国家显示有效
	 */
	private String disabled(){
		this.countryManager.disableAllFiliales(this.countries);
		this.addActionMessage(this.getText("country.disabled.success"));
		return SUCCESS;
	}
	/*
	 * 选择的国家显示无效
	 */
	private String enabled(){
		this.countryManager.enabledAllFiliales(this.countries);
		this.addActionMessage(this.getText("country.enabled.success"));
		return SUCCESS;
	}
	/**
	 * 得到 <i>valueList</i>中配置的，查询hql的ID
	 */
	@Override
	protected String getAdapterName() {
		return "countries";
	}

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}
	
	
}

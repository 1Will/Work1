package com.yongjun.tdms.presentation.webwork.action.base.country;

import java.util.Arrays;


import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.exception.ErroSaveException;
import com.yongjun.tdms.model.base.country.Country;
import com.yongjun.tdms.service.base.country.CountryManager;

/**
 * <p>
 * Title: EditFilialeAction
 * <p>
 * Description: 国家页面访问控制类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2001 yj-technology
 * </p>
 * <p>
 * Company: www.yj-technology.com
 * </p>
 * 
 * @author mfzhang@yj-technology.com
 * @version
 */
public class EditCountryAction extends PrepareAction {
	private static final long serialVersionUID = 5647231872461125882L;
	private final CountryManager countryManager; 
	private Country country;
	
	public EditCountryAction(CountryManager countryManager){
		this.countryManager = countryManager;
	}

	/*
	 * 初始化，获取参数<b>country.id</b>，如果存在就获取这个实体，如果不存在就，就新建一个实体
	 */
	public void prepare() throws Exception {
		if(null == country){
			if(this.hasId("country.id")){
				this.country = this.countryManager.loadCountry(this.getId("country.id"));
			}else{
				this.country = new Country();
/**	隐藏域解决值丢失的方法，URL中文乱码，未采用			
				//获得文本框中输入的值
				if(null == request.getParameter("country.codeHidden")){
					this.country = new Country();
					country.setCode(request.getParameter("country.code"));
					country.setName(request.getParameter("country.name"));
				}else{
					//获得隐藏域中的值
					this.country = new Country();
					country.setCode(request.getParameter("country.codeHidden"));
					country.setName(request.getParameter("country.nameHidden"));
				}
*/				
			}
		}
	}
	
	public String save(){
		boolean isNew = this.country.isNew();
		try {
			try{
				this.countryManager.storeCountry(this.country);
			}catch(Exception e){
				throw new ErroSaveException();
			}
		} catch (ErroSaveException e) {
			//e.printStackTrace();
//			this.addActionMessage("<font color=red>输入的国别"+this.country.getName()+"已经存在</font>");
			this.addActionMessage(this.getText("country.code.exists",Arrays.
					asList(new Object[]{country.getCode()})));
			return ERROR;
		}
		if(isNew){
			this.addActionMessage(this.getText("country.add.success",Arrays.
					asList(new Object[]{country.getName()})));
			return NEW;
		}else{
			this.addActionMessage(this.getText("country.edit.success", Arrays.
					asList(new Object[]{country.getName()})));
			return SUCCESS;
		}
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	
	
}

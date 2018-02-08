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
package com.yongjun.tdms.presentation.webwork.action.prophase.supplier;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.prophase.supplier.EScale;
import com.yongjun.tdms.model.prophase.supplier.Supplier;
import com.yongjun.tdms.model.prophase.supplier.SupplierExtInfo;
import com.yongjun.tdms.service.prophase.supplier.SupplierManager;

/**
 * @author qs
 * @version $Id: $
 */
public class EditSupplierExtInfoAction extends PrepareAction{
	private static final long serialVersionUID = -1394034987829025381L;
	private final Log log = LogFactory.getLog(getClass());
	private final SupplierManager supplierManager;
	
	private Supplier supplier;
	
	private SupplierExtInfo supplierExtInfo;
	
	public EditSupplierExtInfoAction(SupplierManager supplierManager){
		this.supplierManager=supplierManager;
	}
	
	public void prepare() throws Exception {
		if (null == this.supplier) {
			if (this.hasId("supplier.id")) {
				this.supplier = this.supplierManager.loadSupplier(this
						.getId("supplier.id"));
			} else {
				this.supplier = new Supplier();
			}
		}
			if(this.supplier.getSupplierExtInfo()!=null){
				this.supplierExtInfo=this.supplier.getSupplierExtInfo();
			}
			else{
				this.supplierExtInfo=new SupplierExtInfo();
			}
}
	
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public String save() {

//		String result =  request.getParameter("supplierExtInfo.peopleScale");
//		log.debug("scale result is : " + result);
//		if (!StringUtils.isEmpty(result)) {
//			log.debug("value of scale : " + EScale.valueOf(result));
//			supplierExtInfo.setPeopleScale(EScale.valueOf(result));

//		String result =  request.getParameter("supplierExtInfo.peopleScale");
//		log.debug("scale result is : " + result);
//		if (!StringUtils.isEmpty(result)) {
//			log.debug("value of scale : " + EScale.valueOf(result));
//			
//			//TODO 人员规模
//			//supplierExtInfo.setPeopleScale(EScale.valueOf(result));
//		}

		this.supplier.setSupplierExtInfo(supplierExtInfo);
		this.supplierManager.storeSupplier(supplier);
			this.addActionMessage(this.getText("supplier.edit.success", Arrays
					.asList(new Object[] { supplier.getName() })));
			return SUCCESS;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public SupplierExtInfo getSupplierExtInfo() {
		return supplierExtInfo;
	}

	public void setSupplierExtInfo(SupplierExtInfo supplierExtInfo) {
		this.supplierExtInfo = supplierExtInfo;
	}
	
	public LabelValue[] getPeopleScale(){
		LabelValue[] arrays =this.wrapEnum(EScale.class);
		return arrays;
	}
}
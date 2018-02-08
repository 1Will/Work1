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
package com.yongjun.tdms.presentation.webwork.action.base.product;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.base.product.Product;
import com.yongjun.tdms.service.base.product.ProductManager;

/**
 * @author qs
 * @version $Id: EditProductAction.java 10154 2008-01-06 09:11:08Z zbzhang $
 */
public class EditProductAction extends PrepareAction {
	private static final long serialVersionUID = 756319774440324844L;
	private final ProductManager productManager;
	private Product product;
	
	public EditProductAction(ProductManager productManager) {
		this.productManager = productManager;
	}
	
	public void prepare() throws Exception {
		if (null == product) {
			if (this.hasId("product.id")) {
				this.product = this.productManager.loadProduct(this.getId("product.id"));
			} else {
				this.product = new Product();
			}
		}
	}
	
	public String save() {
		String productNo = null;
		String name = null;
		//获取产品编码
		if(!StringUtils.isEmpty(request.getParameter("product.productNo"))){
			productNo = request.getParameter("product.productNo");
		}
		//获取产品名称
		if(!StringUtils.isEmpty(request.getParameter("product.name"))){
			name = request.getParameter("product.name");
		}
		
		boolean isNew = this.product .isNew();
		
		if (this.isDisable()) {
			this.product.setDisabled(true);
		}
		if (this.isEnabled()) {
			this.product.setDisabled(false);
		}
		if(isNew){
			if(checkProduct(productNo,name)){
				this.productManager.storeProduct(this.product);
			}else{
				if(null != this.productManager.getProductByProductNo(productNo)){
					this.addActionMessage(this.getText("product.productNo.exists",Arrays
						.asList(new Object[]{product.getProductNo()})));
				}
				if(null != this.productManager.getProductByName(name)){
					this.addActionMessage(this.getText("product.name.exists",Arrays
							.asList(new Object[]{product.getName()})));
				}
				return ERROR;
			}
		}else{
			this.productManager.storeProduct(this.product);
		}
		if (isNew) {
			this.addActionMessage(this.getText("product.add.success", Arrays
					.asList(new Object[] { product.getName() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText("product.edit.success", Arrays
					.asList(new Object[] { product.getName() })));
			return SUCCESS;
		}
	}
	/**
	 * 判断页面上输入的值是否重复
	 * @param ProductNo
	 * @param name
	 * @return true | false
	 */
	public boolean checkProduct(String productNo,String name){
		if(null != this.productManager.getProductByProductNo(productNo)){
			return false;
		}
		if(null != this.productManager.getProductByName(name)){
			return false;
		}
		return true;
	}
	
	/**
	 * 判断是否点击失效按钮
	 * @return true | false
	 */
	private boolean isDisable() {
		return this.hasKey("disabled");
	}
	/**
	 * 判断是否点击有效按钮
	 * @return　true | false
	 */
	private boolean isEnabled() {
		return this.hasKey("enabled");
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
}

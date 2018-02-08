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

import java.util.List;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.base.product.Product;
import com.yongjun.tdms.service.base.product.ProductManager;

/**
 * @author qs
 * @version $Id: ListProductAction.java 11319 2008-03-14 08:25:24Z wzou $
 */
public class ListProductAction extends ValueListAction {
	private static final long serialVersionUID = 4485315350163841243L;
	private final ProductManager productManager;
	private List<Product> products;
	private boolean includeDisabled;
	private Product product;
	public ListProductAction(ProductManager productManager) {
		this.productManager = productManager;
	}
	
	public void prepare() {
		System.out.println("onlyValid**************有效  " + request.getParameter("onlyValid"));
		System.out.println("onlyInvalid**************失效  " + request.getParameter("onlyInvalid"));
		if (this.hasId("productIds")) {
			this.products = this.productManager.loadAllProducts(this.getIds("productIds"));
		}
	}
//	
//	public String execute() {
//		if (this.isDisabled()) {
//			return this.disable();
//		}
//		if (this.isEnable()) {
//			return enabled();
//		}
//		
//		return SUCCESS;
//	}
	public String execute() {
		if (this.isDisabled()) {
			return disabled();
		}
		if (this.isEnabled()) {
			return this.enabled();
		}
		return SUCCESS;
	}
//	private String disable() {
//		this.productManager.disabledAllProducts(this.products);
//		this.addActionMessage(this.getText("products.disabled.success"));
//		return SUCCESS;
//	}
	private String disabled() {
		this.productManager.disabledAllProducts(this.products);
		this.addActionMessage(this.getText("products.disabled.success"));
		return SUCCESS;
	}
	public String enabled() {
		this.productManager.enabledAllProducts(products);
		this.addActionMessage(this.getText("enabled.products.success"));
		return SUCCESS;
	}
	/**
	 * 判断是否点击了失效按钮
	 * @return　true | false
	 */
	private boolean isEnabled() {
		return this.hasKey("enabled");
	}
	
	@Override
	protected String getAdapterName() {
		return "products";
	}
	public List<Product> getProducts() {
		return products;
	}
	public boolean isIncludeDisabled() {
		return includeDisabled;
	}
	public void setIncludeDisabled(boolean includeDisabled) {
		this.includeDisabled = includeDisabled;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}

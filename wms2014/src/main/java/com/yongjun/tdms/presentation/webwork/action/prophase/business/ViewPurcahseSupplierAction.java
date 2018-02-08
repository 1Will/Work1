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
package com.yongjun.tdms.presentation.webwork.action.prophase.business;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.prophase.supplier.Supplier;
import com.yongjun.tdms.service.prophase.supplier.SupplierManager;

/**
 * @author qs
 * @version $Id: ViewPurcahseSupplierAction.java 10912 2008-02-14 01:49:44Z qsun $
 */
@SuppressWarnings("serial")
public class ViewPurcahseSupplierAction extends PrepareAction {
	private Supplier supplier;
	private final SupplierManager supplierManager;
	
	public ViewPurcahseSupplierAction(SupplierManager supplierManager) {
		this.supplierManager = supplierManager;
	}
	
	public void prepare() throws Exception {
		if (null == supplier) {
			if (hasId("supplier.id")) {
				this.supplier = supplierManager.loadSupplier(getId("supplier.id"));
			} else {
				this.supplier = new Supplier();
			}
		}
	}

	public Supplier getSupplier() {
		return supplier;
	}
	
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
}

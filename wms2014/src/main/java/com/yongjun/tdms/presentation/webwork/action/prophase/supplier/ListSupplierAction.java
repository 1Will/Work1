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







import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.prophase.supplier.SupplierManager;
import com.yongjun.tdms.workflow.service.docstate.WfDocStateManager;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;

import com.yongjun.tdms.model.prophase.supplier.Supplier;

import java.util.List;

/**
 * @author qs
 * @version $Id: ListSupplierAction.java 11309 2008-03-13 05:51:55Z mwei $
 */
public class ListSupplierAction extends ValueListAction {
	private static final long serialVersionUID = -46962563495428767L;

	private final SupplierManager supplierManager;

	private final WfDocStateManager docStateManager;

	private final CodeValueManager codeValueManager;

	private List<Supplier> supplier;
	private String toolingDevFlag;

	public ListSupplierAction(SupplierManager supplierManager,
			WfDocStateManager docStateManager, CodeValueManager codeValueManager) {
		this.supplierManager = supplierManager;
		this.docStateManager = docStateManager;
		this.codeValueManager = codeValueManager;
	}

	public void prepare() {
		
		if (null == this.supplier && this.hasIds("supplierIds")) {
			this.supplier = this.supplierManager.loadAllSuppliers(this
					.getIds("supplierIds"));
		}
		if (this.hasId("toolingDevFlag")) {
			this.toolingDevFlag = request.getParameter("toolingDevFlag");
			
		}
		
	}

	public String execute() throws Exception {
		
		if (this.isDisabled()) {
			return disabled();
		}
		if (this.isEnabled()) {
			return this.enabled();
		}

		return SUCCESS;
	}
  
	public String disabled() {
	this.supplierManager.disabledAllSuppliers(supplier);
	this.addActionMessage(this.getText("supplier.disabled.success"));
	return SUCCESS;
   }

   public String enabled() {
	this.supplierManager.enabledAllSuppliers(supplier);
	this.addActionMessage(this.getText("supplier.enabled.success"));
	return SUCCESS;
} 
	
	@Override
	protected String getAdapterName() {
//		if (toolingDevFlag.equals(SysModel.DEVICE.toString())) {
//			return "deviceSuppliers";
//		} else {
//			return "toolingSuppliers";
//		}
		//return "toolingDev";
		if (("TOOLING").equals(request.getParameter("toolingDevFlag"))) {
			return "toolingSuppliers";
		}
		if (("DEVICE").equals(request.getParameter("toolingDevFlag"))) {
			return "deviceSuppliers";
		}
		return "toolingDev";
	}
	
	private boolean isEnabled() {
		return this.hasKey("enabled");
	}

//	public void delete() {
//		try {
//			this.supplierManager.deleteAllSuppliers(this.supplier);
//		} catch (Exception e) {
//			e.printStackTrace();
//			this.addActionMessage(this.getText("checkPointProc.delete.error"));
//		}
//		this.addActionMessage(this.getText("supplier.delete.success"));
//	}

	public List<Supplier> getSupplier() {
		return supplier;
	}

	public void setSupplier(List<Supplier> supplier) {
		this.supplier = supplier;
	}

	

	public List getSupplierCatory() {
		return codeValueManager.createSelectCodeValues(this
				.getText("supplier.all"), CodeConstants.SUPPLIER_TYPE);
	}

	public List getSupplierLevel() {
		return codeValueManager.createSelectCodeValues(this
				.getText("supplier.all"), CodeConstants.SUPPLIER_LEVEL);
	}



	public String getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

}

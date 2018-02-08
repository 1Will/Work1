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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.inventory;

import java.util.List;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.runmaintenance.inventory.InventoryBill;
import com.yongjun.tdms.service.runmaintenance.inventory.InventoryBillDetailManager;
import com.yongjun.tdms.service.runmaintenance.inventory.InventoryManager;

/**
 * @author qs
 * @version $Id: ListInventoryDetailAction.java 11086 2008-02-26 01:12:56Z mwei $
 */
@SuppressWarnings("serial")
public class ListInventoryDetailAction extends ValueListAction {
	
	private List inventoryBillDetails;

	private final InventoryBillDetailManager inventoryBillDetailManager;

	private InventoryBill inventoryBill;

	private final InventoryManager inventoryManager;
	
	private String toolingDevFlag;

	public ListInventoryDetailAction(InventoryManager inventoryManager,
			InventoryBillDetailManager inventoryBillDetailManager) {
		this.inventoryManager = inventoryManager;
		this.inventoryBillDetailManager = inventoryBillDetailManager;
		

	}
	
	public String execute() throws Exception {
		
		if (this.isDelete()) {
			delete();
		}
		return SUCCESS;
	}
	@SuppressWarnings("unchecked")
	private void delete() {
		
		this.inventoryBillDetailManager
				.deleteAllInventoryBillDetails(inventoryBillDetails);
		this.addActionMessage(this
				.getText("inventoryBillDetail.delete.success"));
	}
	public void prepare() {

		if (this.hasId("inventoryBill.id")) {

			this.inventoryBill = this.inventoryManager.loadInventoryBill(this
					.getId("inventoryBill.id"));
		}
		if (this.inventoryBillDetails == null
				&& this.hasIds("inventoryBillDetailIds")) {

			this.inventoryBillDetails = this.inventoryBillDetailManager
					.loadAllInventoryBillDetails(this
							.getIds("inventoryBillDetailIds"));
		}	

		if(this.hasId("toolingDevFlag")) {
			this.toolingDevFlag = request.getParameter("toolingDevFlag");
		}
	
		this.setFirst(false);
	}
	public InventoryBill getInventoryBill() {
		return inventoryBill;
	}

	public void setInventoryBill(InventoryBill inventoryBill) {
		this.inventoryBill = inventoryBill;
	}

	public List getInventoryBillDetails() {
		return inventoryBillDetails;
	}

	public void setInventoryBillDetails(List inventoryBillDetails) {
		this.inventoryBillDetails = inventoryBillDetails;
	}

	@Override
	protected String getAdapterName() {
		if(toolingDevFlag.equals(SysModel.DEVICE.toString())){
       	     return "deviceDetailinventory";
       }else{
         	return "toolingDetailinventory";	
       }
	}

	public String getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

}

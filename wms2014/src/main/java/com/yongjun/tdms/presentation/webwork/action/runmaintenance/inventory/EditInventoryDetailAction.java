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

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.webwork.action.PrepareAction;

import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.asset.device.DeviceType;
import com.yongjun.tdms.model.base.codevalue.ToolingType;
import com.yongjun.tdms.model.runmaintenance.inventory.InventoryBill;
import com.yongjun.tdms.model.runmaintenance.inventory.InventoryBillDetail;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.inventory.InventoryBillDetailManager;
import com.yongjun.tdms.service.runmaintenance.inventory.InventoryManager;

/**
 * @author qs
 * @version $Id: EditInventoryDetailAction.java 11086 2008-02-26 01:12:56Z mwei $
 */
public class EditInventoryDetailAction extends PrepareAction {
	private static final long serialVersionUID = -1106315629972702672L;

	private final InventoryBillDetailManager inventoryBillDetailManager;

	private final DepartmentManager departmentManager;

	private final DeviceCardManager deviceCardManager;

	private final InventoryManager inventoryManager;

	private InventoryBill inventoryBill;

	private DeviceCard device;
	
    private DeviceCard asset;
    
	private DeviceType devicetype;
	
	private ToolingType toolingtype;

	private String toolingDevFlag;
	
	private InventoryBillDetail inventoryBillDetail;

	public EditInventoryDetailAction(DepartmentManager departmentManager,
			InventoryBillDetailManager inventoryBillDetailManager,
			DeviceCardManager deviceCardManager,
			InventoryManager inventoryManager) {
		this.departmentManager = departmentManager;
		this.inventoryBillDetailManager = inventoryBillDetailManager;
		this.deviceCardManager = deviceCardManager;
		this.inventoryManager = inventoryManager;

	}
	public void prepare() throws Exception {
			if (null == inventoryBill) {
				if (this.hasId("inventoryBill.id")) {
					this.inventoryBill = this.inventoryManager.loadInventoryBill(this.getId("inventoryBill.id"));
				} 
			}
		
		if (null == inventoryBillDetail) {
					if (this.hasId("inventoryBillDetail.id")) {
						this.inventoryBillDetail = this.inventoryBillDetailManager.loadInventoryBillDetail(this.getId("inventoryBillDetail.id"));
						if (!this.hasId("tooling.id") && !this.hasId("device.id")) {
							this.device = inventoryBillDetail.getAsset();
							this.asset=inventoryBillDetail.getAsset();
						}
						this.devicetype = inventoryBillDetail.getAsset().getDeviceType();
						this.toolingtype=inventoryBillDetail.getAsset().getToolingType();
					} else {
						this.inventoryBillDetail = new InventoryBillDetail();
					}
				}
				
		if(this.hasId("toolingDevFlag")) {
			this.toolingDevFlag = request.getParameter("toolingDevFlag");
		  }
	}
	

	public String save() {
		boolean isNew = this.inventoryBillDetail.isNew();
		if (!StringUtils.isEmpty(request.getParameter("device.id"))) {
			inventoryBillDetail.setAsset(this.deviceCardManager.loadDevice(this
					.getId("device.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("tooling.id"))) {
			inventoryBillDetail.setAsset(this.deviceCardManager.loadDevice(this.getId("tooling.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("inventoryBill.id"))) {
			inventoryBillDetail.setInventory(this.inventoryManager
					.loadInventoryBill(this.getId("inventoryBill.id")));
		}
		inventoryBillDetail.setInventory(inventoryBill);
		this.inventoryBillDetailManager
				.storeInventoryBillDetail(inventoryBillDetail);

		if (isNew) {
			this.addActionMessage(this.getText("inventoryDetailBill.add.success",
					Arrays
							.asList(new Object[] { inventoryBillDetail
									.getAsset().getName() })));

			return NEW;
		} else {
			this.addActionMessage(this.getText("inventoryDetailBill.edit.success",
					Arrays
							.asList(new Object[] { inventoryBillDetail
									.getAsset().getName() })));
			return SUCCESS;
		}

	}

	public InventoryBillDetail getInventoryBillDetail() {
		return inventoryBillDetail;
	}

	public void setInventoryBillDetail(InventoryBillDetail inventoryBillDetail) {
		this.inventoryBillDetail = inventoryBillDetail;
	}

	public InventoryBill getInventoryBill() {
		return inventoryBill;
	}

	public void setInventoryBill(InventoryBill inventoryBill) {
		this.inventoryBill = inventoryBill;
	}

	public DeviceCard getDevice() {
		return device;
	}

	public DeviceType getDevicetype() {
		return devicetype;
	}

	public String getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

	public ToolingType getToolingtype() {
		return toolingtype;
	}

	public void setToolingtype(ToolingType toolingtype) {
		this.toolingtype = toolingtype;
	}

	public DeviceCard getAsset() {
		return asset;
	}

	public void setAsset(DeviceCard asset) {
		this.asset = asset;
	}
	public DeviceCard getTooling() {
		return asset;
	}

}

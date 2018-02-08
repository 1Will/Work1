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
package com.yongjun.tdms.presentation.webwork.action.asset.spare.warehouseInfo.warehouse;

import java.util.ArrayList;
import java.util.List;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.pluto.model.security.Warehouse;
import com.yongjun.pluto.service.security.WarehouseManager;

/**
 * <p>
 * Title:ListWarehouseAction
 * <p>
 * Description:ListWarehouseAction
 * </p>
 * <p>
 * Copyright: Copyright (c) 2001 yj-technology
 * </p>
 * <p>
 * Company: www.yj-technology.com
 * </p>
 * 
 * @author bxchen@yj-technology.com
 * @version
 */
public class ListWarehouseAction
extends ValueListAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final WarehouseManager warehouseManager;

	private List<Warehouse> warehouses;
	
	private boolean includeDisabled;

	public ListWarehouseAction(final WarehouseManager warehouseManager) {
		this.warehouseManager = warehouseManager;
	}

	@Override
	public void prepare() throws Exception {
		if (this.hasIds("warehouseIds")) {
			this.warehouses = this.warehouseManager.loadAllWarehouse(this
			.getIds("warehouseIds"));
		}
		else {
			this.warehouses = new ArrayList<Warehouse>();
		}
	}

	@Override
	protected String getAdapterName() {
		return "WarehouseHQL";
	}

	@Override
	public String execute() throws Exception {
		if (this.isDelete()) {
			return delete();
		}
		if (this.isDisabled()) {
			return disabled();
		}
		if (this.isEnable()) {
			return enabled();
		}
		return SUCCESS;
	}

	private String enabled() {
		try {
			this.warehouseManager.enabledAllWarehouse(this.warehouses);
			this.addActionMessage(this.getText("warehouse.enabled.success"));
			return SUCCESS;
		}
		catch (Exception e) {
			this.addActionMessage(this.getText("warehouse.enabled.error"));
			return ERROR;
		}
	}

	private String disabled() {
		try {
			this.warehouseManager.disabledAllWarehouse(this.warehouses);
			this.addActionMessage(this.getText("warehouse.disabled.success"));
			return SUCCESS;
		}
		catch (Exception e) {
			this.addActionMessage(this.getText("warehouse.disabled.error"));
			return ERROR;
		}
	}

	private String delete() {
		try {
			this.warehouseManager.deleteAllWarehouse(this.warehouses);
			this.addActionMessage(this.getText("warehouse.delete.success"));
			return SUCCESS;
		}
		catch (Exception e) {
			this.addActionMessage(this.getText("warehouse.delete.error"));
			return ERROR;
		}
	}

	public boolean isIncludeDisabled() {
		return includeDisabled;
	}

	public void setIncludeDisabled(boolean includeDisabled) {
		this.includeDisabled = includeDisabled;
	}

	public List<Warehouse> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(List<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}
	
}

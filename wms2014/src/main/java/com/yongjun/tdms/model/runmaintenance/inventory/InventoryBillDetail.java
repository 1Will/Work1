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
package com.yongjun.tdms.model.runmaintenance.inventory;

import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.asset.device.DeviceCard;

/**
 * <p>Title: InventoryBillDetail
 * <p>Description: 清查明细类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: InventoryBillDetail.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class InventoryBillDetail extends BaseInfoEntity {
	private static final long serialVersionUID = 8631418816422069940L;
	
	//清查结果
	private String inventoryResult;
	
	//处理结果
	private String handleResult;
	
	//关联的资产
	private DeviceCard asset;
	
	//关联的清查单
	private InventoryBill inventory;

	@Override
	public int hashCode() {
		return 0;
		
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public DeviceCard getAsset() {
		return asset;
	}

	public void setAsset(DeviceCard asset) {
		this.asset = asset;
	}

	public String getHandleResult() {
		return handleResult;
	}

	public void setHandleResult(String handleResult) {
		this.handleResult = handleResult;
	}

	public InventoryBill getInventory() {
		return inventory;
	}

	public void setInventory(InventoryBill inventory) {
		this.inventory = inventory;
	}

	public String getInventoryResult() {
		return inventoryResult;
	}

	public void setInventoryResult(String inventoryResult) {
		this.inventoryResult = inventoryResult;
	}

}

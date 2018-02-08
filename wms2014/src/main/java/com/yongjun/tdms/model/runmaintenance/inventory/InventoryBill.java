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

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.pluto.model.tracking.OrganizationTracking;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.pluto.model.security.Department;

/**
 * <p>Title: InventoryBill
 * <p>Description: 清查类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: InventoryBill.java 27787 2010-10-14 02:47:51Z zbzhang $
 */

public class InventoryBill extends BaseInfoEntity implements CreatorTracking,
CreatedTimeTracking, LastOperatorTracking, LastModifiedTimeTracking, OrganizationTracking{
	private static final long serialVersionUID = -1623649084112506886L;
	
	//清查编号
	private String billNo;
	
	//清查名称
	private String name;
	
	//清查日期
	private Date inventoryDate;
	
	//备注
	private String comment;
	
	//资产标识[设备][工装]
	private SysModel toolingDevFlag = SysModel.DEVICE;

	//清查人
	private User inventoryPeople;
	
	//部门
	private Department department;
	
	//清查单明细
	private Set inventoryBillDetail = new HashSet<InventoryBillDetail>();
	
	@Override
	public int hashCode() {
		return billNo.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof InventoryBill)) {return false;}
		
		InventoryBill inventoryBill = (InventoryBill)o;
		if (this.billNo.equals(inventoryBill.getBillNo())) {return true;}
		
		return false;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Date getInventoryDate() {
		return inventoryDate;
	}

	public void setInventoryDate(Date inventoryDate) {
		this.inventoryDate = inventoryDate;
	}

	public User getInventoryPeople() {
		return inventoryPeople;
	}

	public void setInventoryPeople(User inventoryPeople) {
		this.inventoryPeople = inventoryPeople;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SysModel getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(SysModel toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

	public Set getInventoryBillDetail() {
		return inventoryBillDetail;
	}

	public void setInventoryBillDetail(Set inventoryBillDetail) {
		this.inventoryBillDetail = inventoryBillDetail;
	}

}

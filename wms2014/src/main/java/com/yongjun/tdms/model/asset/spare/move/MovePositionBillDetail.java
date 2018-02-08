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
package com.yongjun.tdms.model.asset.spare.move;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.Warehouse;
import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.asset.spare.Location;
import com.yongjun.tdms.model.asset.spare.SpareLocation;
import com.yongjun.tdms.model.asset.spare.warehouseInfo.regional.Regional;
/**
 * <p>Title: MovePositionBillDetail
 * <p>Description: 移位单明细</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author xschen@yj-technology.com
 * @version $Id: MovePositionBillDetail.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class MovePositionBillDetail extends BaseInfoEntity implements CreatorTracking,
CreatedTimeTracking, LastOperatorTracking, LastModifiedTimeTracking{
	private static final long serialVersionUID = 1L;
    //备件编号
	private String spareNo;
    //备件名称
	private String spareName;
	//型号
	private String modelSpec;
    //移位前仓库
	private String beforeMoveWarehouse;
    //移位前库区
	private String beforeMoveRegional;
	//移位前库位号
	private String beforeMoveLocationCode;
	//移位前库存
	private Long beforeMoveStocks;
    //移位后仓库
	private String afterMoveWarehouse;
    //移位后库区
	private String afterMoveRegional;
	//移位后库位号
	private String afterMoveLocationCode;
	//数量
	private Long number;
	//状态
	private MovePositionBillDetailStatus movePositionDtlStatus=MovePositionBillDetailStatus.unMovePositionDtl;
	//移位单明细所关联的移位单
	private  MovePositionBill  movePositionBill;
	//移位单明细所关联的备件明细台帐
	private SpareLocation spareLocation;
	//备件的id
	private Long spareId;
	//标识移位后的仓库名是属于哪个仓库的
	private Warehouse warehouse;
	//标识移位后的库区名是属于哪个库区的
	private Regional regional;
	//标识移位后的库位号是属于哪个库位的
	private Location location;
	//部门
	private Department department;
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public String getAfterMoveLocationCode() {
		return afterMoveLocationCode;
	}

	public void setAfterMoveLocationCode(String afterMoveLocationCode) {
		this.afterMoveLocationCode = afterMoveLocationCode;
	}

	public String getAfterMoveRegional() {
		return afterMoveRegional;
	}

	public void setAfterMoveRegional(String afterMoveRegional) {
		this.afterMoveRegional = afterMoveRegional;
	}

	public String getAfterMoveWarehouse() {
		return afterMoveWarehouse;
	}

	public void setAfterMoveWarehouse(String afterMoveWarehouse) {
		this.afterMoveWarehouse = afterMoveWarehouse;
	}

	public String getBeforeMoveLocationCode() {
		return beforeMoveLocationCode;
	}

	public void setBeforeMoveLocationCode(String beforeMoveLocationCode) {
		this.beforeMoveLocationCode = beforeMoveLocationCode;
	}

	public String getBeforeMoveRegional() {
		return beforeMoveRegional;
	}

	public void setBeforeMoveRegional(String beforeMoveRegional) {
		this.beforeMoveRegional = beforeMoveRegional;
	}

	public Long getBeforeMoveStocks() {
		return beforeMoveStocks;
	}

	public void setBeforeMoveStocks(Long beforeMoveStocks) {
		this.beforeMoveStocks = beforeMoveStocks;
	}

	public String getBeforeMoveWarehouse() {
		return beforeMoveWarehouse;
	}

	public void setBeforeMoveWarehouse(String beforeMoveWarehouse) {
		this.beforeMoveWarehouse = beforeMoveWarehouse;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getModelSpec() {
		return modelSpec;
	}

	public void setModelSpec(String modelSpec) {
		this.modelSpec = modelSpec;
	}

	public MovePositionBill getMovePositionBill() {
		return movePositionBill;
	}

	public void setMovePositionBill(MovePositionBill movePositionBill) {
		this.movePositionBill = movePositionBill;
	}

	public MovePositionBillDetailStatus getMovePositionDtlStatus() {
		return movePositionDtlStatus;
	}

	public void setMovePositionDtlStatus(
			MovePositionBillDetailStatus movePositionDtlStatus) {
		this.movePositionDtlStatus = movePositionDtlStatus;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public Regional getRegional() {
		return regional;
	}

	public void setRegional(Regional regional) {
		this.regional = regional;
	}

	public Long getSpareId() {
		return spareId;
	}

	public void setSpareId(Long spareId) {
		this.spareId = spareId;
	}

	public SpareLocation getSpareLocation() {
		return spareLocation;
	}

	public void setSpareLocation(SpareLocation spareLocation) {
		this.spareLocation = spareLocation;
	}

	public String getSpareName() {
		return spareName;
	}

	public void setSpareName(String spareName) {
		this.spareName = spareName;
	}

	public String getSpareNo() {
		return spareNo;
	}

	public void setSpareNo(String spareNo) {
		this.spareNo = spareNo;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

}

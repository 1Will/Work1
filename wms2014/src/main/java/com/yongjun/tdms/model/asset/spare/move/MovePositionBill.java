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

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.security.Warehouse;
import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
/**
 * <p>Title: MovePositionBill
 * <p>Description: 移位单</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author xschen@yj-technology.com
 * @version $Id: MovePositionBill.java 32473 2011-09-27 10:16:59Z zbzhang $
 */
public class MovePositionBill extends BaseInfoEntity implements CreatorTracking,
CreatedTimeTracking, LastOperatorTracking, LastModifiedTimeTracking{
	private static final long serialVersionUID = 1L;
	//移位单编号
    private String billNo;
    //移位单名称
    private String billName;
    //移位时间
    private Date moveTime;
    //移位人
    private User user;
    //状态
    private MovePositionStatus moveStatus=MovePositionStatus.unMovePosition;
    //备注
    private String comment;
    //移位单所关联的移位单明细集合
    private Set<MovePositionBillDetail> movePositionBillDtls=new HashSet<MovePositionBillDetail>();
    
	/**
	 * 仓库级别
	 */
	private CodeValue storageGrade;
	/**
	 * 仓库
	 */
	private Warehouse warehouse;
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

	public String getBillName() {
		return billName;
	}

	public void setBillName(String billName) {
		this.billName = billName;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}


	public MovePositionStatus getMoveStatus() {
		return moveStatus;
	}

	public void setMoveStatus(MovePositionStatus moveStatus) {
		this.moveStatus = moveStatus;
	}

	public Date getMoveTime() {
		return moveTime;
	}

	public void setMoveTime(Date moveTime) {
		this.moveTime = moveTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<MovePositionBillDetail> getMovePositionBillDtls() {
		return movePositionBillDtls;
	}

	public void setMovePositionBillDtls(
			Set<MovePositionBillDetail> movePositionBillDtls) {
		this.movePositionBillDtls = movePositionBillDtls;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public CodeValue getStorageGrade() {
		return storageGrade;
	}

	public void setStorageGrade(CodeValue storageGrade) {
		this.storageGrade = storageGrade;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

}

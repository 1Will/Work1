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
package com.yongjun.tdms.model.runmaintenance.alteration;

import java.util.Date;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.asset.device.DeviceCard;

/**
 * <p>Title: Alteration
 * <p>Description: 资产变动类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: Alteration.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class Alteration extends BaseInfoEntity implements CreatorTracking,
CreatedTimeTracking, LastOperatorTracking, LastModifiedTimeTracking {
	private static final long serialVersionUID = -8165743926456257213L;
	private String sealedBillNo;				// 封存单编号
	private String sealedBillName;				// 封存单名称
	private DeviceCard asset;					// 资产
	private User sealedUsed;					// 封存人
	private Date sealedDateApp;					// 封存申请日期
	private String reason;						// 封存原因
	
	private boolean isSealed = false;			// 封存批准
	private User sealedUsedApr;					// 封存批准人
	private Date sealedDateTm=new Date();					// 封存日期
	private String sealedComment;				// 封存备注
	
	private boolean isUnSealed = false;			// 启封批准
	private User unSealedUsedApr;				// 启封批准人
	private Date unSealedDateTm=new Date();				// 启封日期
	private String unSealComment;				// 启封备注
	
	private SysModel toolingDevFlag;			//标示工装，设备
	
//	设备原值，针对设备封存的报表，没有hbm的数据库字段
	private Double origPrice;
	
	private UnSealedStatus status  = UnSealedStatus.REQUEST;		//封存单状态
	
	public Alteration() {
	}
	
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
	public DeviceCard getAsset() {
		return asset;
	}
	public void setAsset(DeviceCard asset) {
		this.asset = asset;
	}

	public boolean isIsSealed() {
		return isSealed;
	}

	public void setIsSealed(boolean isSealed) {
		this.isSealed = isSealed;
	}

	public boolean isIsUnSealed() {
		return isUnSealed;
	}

	public void setIsUnSealed(boolean isUnSealed) {
		this.isUnSealed = isUnSealed;
	}

	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getSealedBillName() {
		return sealedBillName;
	}
	public void setSealedBillName(String sealedBillName) {
		this.sealedBillName = sealedBillName;
	}
	public String getSealedBillNo() {
		return sealedBillNo;
	}
	public void setSealedBillNo(String sealedBillNo) {
		this.sealedBillNo = sealedBillNo;
	}
	public String getSealedComment() {
		return sealedComment;
	}
	public void setSealedComment(String sealedComment) {
		this.sealedComment = sealedComment;
	}
	public Date getSealedDateApp() {
		return sealedDateApp;
	}
	public void setSealedDateApp(Date sealedDateApp) {
		this.sealedDateApp = sealedDateApp;
	}
	public Date getSealedDateTm() {
		return sealedDateTm;
	}
	public void setSealedDateTm(Date sealedDateTm) {
		this.sealedDateTm = sealedDateTm;
	}
	
	public User getUnSealedUsedApr() {
		return unSealedUsedApr;
	}
	public void setUnSealedUsedApr(User unSealedUsedApr) {
		this.unSealedUsedApr = unSealedUsedApr;
	}
	public User getSealedUsed() {
		return sealedUsed;
	}
	public void setSealedUsed(User sealedUsed) {
		this.sealedUsed = sealedUsed;
	}
	public User getSealedUsedApr() {
		return sealedUsedApr;
	}
	public void setSealedUsedApr(User sealedUsedApr) {
		this.sealedUsedApr = sealedUsedApr;
	}
	public String getUnSealComment() {
		return unSealComment;
	}
	public void setUnSealComment(String unSealComment) {
		this.unSealComment = unSealComment;
	}
	public Date getUnSealedDateTm() {
		return unSealedDateTm;
	}
	public void setUnSealedDateTm(Date unSealedDateTm) {
		this.unSealedDateTm = unSealedDateTm;
	}
	public UnSealedStatus getStatus() {
		return status;
	}
	public void setStatus(UnSealedStatus status) {
		this.status = status;
	}
	public SysModel getToolingDevFlag() {
		return toolingDevFlag;
	}
	public void setToolingDevFlag(SysModel toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

	public Double getOrigPrice() {
		return origPrice;
	}

	public void setOrigPrice(Double origPrice) {
		this.origPrice = origPrice;
	}
	
}

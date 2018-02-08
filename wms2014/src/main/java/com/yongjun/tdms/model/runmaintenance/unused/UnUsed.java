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
package com.yongjun.tdms.model.runmaintenance.unused;

import java.util.Date;

import com.yongjun.pluto.model.security.User;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.asset.device.DeviceCard;

/**
 * @author qs
 * @version $Id: UnUsed.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class UnUsed extends BaseInfoEntity {
	private static final long serialVersionUID = 6756500257179972182L;
	// 闲置单号
	private String code;
	// 闲置单名称
	private String name;
	// 闲置日期
	private Date unUseDate;
	// 申请人
	private User applicant;
	// 闲置原因
	private String reason;
	//启用原因
	private String usedreason;
	// 备注
	private String comment;
	// 闲置批准
	private boolean isUnUsedAduit = false;
	// 闲置批准人
	private User unUsedApr;
	// 闲置批准日期
	private Date unUsedAprDate;
	// 闲置批准备注
	private String unUsedAprCmt;
	// 启用批准
	private boolean isUsedAduit = false;
	// 启用批准人
	private User usedApr;
	//批准意见
	private String devise;
	// 启用批准日期
	private Date usedAprDate;
	// 启用备注
	private String usedAprCmt;
	// 设备
	private DeviceCard asset;
	// 闲置单状态
	private UnUsedStatus status = UnUsedStatus.REQUEST;
	
//	资产标识[工装]|[设备]
	private SysModel toolingDevFlag=SysModel.DEVICE;
	
	public UnUsed() {
		
	}
	
	public UnUsedStatus getStatus() {
		return this.status;
	}
	
	public void setStatus(UnUsedStatus status) {
		this.status = status;
	}
	
	

	public DeviceCard getAsset() {
		return asset;
	}

	public void setAsset(DeviceCard asset) {
		this.asset = asset;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean getIsUnUsedAduit() {
		return isUnUsedAduit;
	}

	public void setIsUnUsedAduit(boolean isUnUsedAduit) {
		this.isUnUsedAduit = isUnUsedAduit;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	

	public String getUnUsedAprCmt() {
		return unUsedAprCmt;
	}

	public void setUnUsedAprCmt(String unUsedAprCmt) {
		this.unUsedAprCmt = unUsedAprCmt;
	}

	public Date getUnUsedAprDate() {
		return unUsedAprDate;
	}

	public void setUnUsedAprDate(Date unUsedAprDate) {
		this.unUsedAprDate = unUsedAprDate;
	}

	public Date getUnUseDate() {
		return unUseDate;
	}

	public void setUnUseDate(Date unUseDate) {
		this.unUseDate = unUseDate;
	}


	public String getUsedAprCmt() {
		return usedAprCmt;
	}

	public void setUsedAprCmt(String usedAprCmt) {
		this.usedAprCmt = usedAprCmt;
	}

	public Date getUsedAprDate() {
		return usedAprDate;
	}

	public void setUsedAprDate(Date usedAprDate) {
		this.usedAprDate = usedAprDate;
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

	public User getApplicant() {
		return applicant;
	}

	public void setApplicant(User applicant) {
		this.applicant = applicant;
	}

	public User getUnUsedApr() {
		return unUsedApr;
	}

	public void setUnUsedApr(User unUsedApr) {
		this.unUsedApr = unUsedApr;
	}

	public String getDevise() {
		return devise;
	}

	public void setDevise(String devise) {
		this.devise = devise;
	}

	public User getUsedApr() {
		return usedApr;
	}

	public void setUsedApr(User usedApr) {
		this.usedApr = usedApr;
	}

	public String getUsedreason() {
		return usedreason;
	}

	public void setUsedreason(String usedreason) {
		this.usedreason = usedreason;
	}

	public SysModel getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(SysModel toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

	
	public void getIsUnUsedAduit(boolean isUnUsedAduit) {
		this.isUnUsedAduit = isUnUsedAduit;
	}
	public void setUnUsedAduit(boolean isUnUsedAduit) {
		this.isUnUsedAduit = isUnUsedAduit;
	}

	public boolean getIsUsedAduit() {
		return isUsedAduit;
	}

	public void setIsUsedAduit(boolean isUsedAduit) {
		this.isUsedAduit = isUsedAduit;
	}

	

}

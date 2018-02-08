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
package com.yongjun.tdms.model.asset.spare;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.tdms.model.BaseInfoEntity;

/**
 * @author qs
 * @version $Id: SpareInOutBill.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class SpareInOutBill extends BaseInfoEntity implements CreatorTracking,
		CreatedTimeTracking, LastOperatorTracking, LastModifiedTimeTracking {
	private static final long serialVersionUID = 5708508542780732136L;
	// 出/入库单号
	private String billNo;
	// 出/入库单名称
	private String name;
	// 出/入库日期
	private Date inoutDateTm;
	// 出/入库人
	private String inoutPeople;
	//备注
	private String comment;
	private boolean inFlag = true; // 默认为入库
	private Boolean submit = false;//是否‘提交’标识
	private Set<SpareInOutHistory> spareInOutHistory = new HashSet<SpareInOutHistory>();
	
	public SpareInOutBill() {
		
	}

	public String getBillNo() {
		return billNo;
	}
	
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	
	public Date getInoutDateTm() {
		return inoutDateTm;
	}

	public void setInoutDateTm(Date inoutDateTm) {
		this.inoutDateTm = inoutDateTm;
	}

	public String getInoutPeople() {
		return inoutPeople;
	}

	public void setInoutPeople(String inoutPeople) {
		this.inoutPeople = inoutPeople;
	}

	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public boolean isInFlag() {
		return inFlag;
	}
	
	public void setInFlag(boolean inFlag) {
		this.inFlag = inFlag;
	}
	
	@Override
	public int hashCode() {
		// TODO 添加单据号作为hashcode
		return 0;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public Set<SpareInOutHistory> getSpareInOutHistory() {
		return spareInOutHistory;
	}

	public void setSpareInOutHistory(Set<SpareInOutHistory> spareInOutHistory) {
		this.spareInOutHistory = spareInOutHistory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getSubmit() {
		return submit;
	}

	public void setSubmit(Boolean submit) {
		this.submit = submit;
	}
}

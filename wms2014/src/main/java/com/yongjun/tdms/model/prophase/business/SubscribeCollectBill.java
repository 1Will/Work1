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
package com.yongjun.tdms.model.prophase.business;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.SysModel;

/**
 * <p>Title: SubscribeCollectBill
 * <p>Description: 申购汇总单类</p>
 * <p>Copyright: Copyright (c) 2010 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: SubscribeCollectBill.java 30990 2011-03-09 02:02:11Z zbzhang $
 */
public class SubscribeCollectBill extends BaseInfoEntity implements CreatorTracking,
CreatedTimeTracking, LastOperatorTracking, LastModifiedTimeTracking{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4223145011267328872L;

	//申购汇总单编码
	private String code;
	//申购汇总单名称
	private String name;
	//汇总部门 [默认当前登陆人的部门]
	private Department collectDept;
	//汇总人 [默认当前登陆人]
	private User collectPerson;
	//汇总日期 [默认当前日期]
	private Date collectDate = new Date();
	//状态 [新建|采购中|已采购|入库中|已入库]
	private SubscribeCollectBillTypeStatus billStatus = SubscribeCollectBillTypeStatus.NEW;
	//备注
	private String comment;
	//系统模块类型 [设备|工装 默认是设备]
	private SysModel toolingDevFlag = SysModel.DEVICE;
	//申购汇总类型 [设备|工装|备件 默认是备件]
	private SubscribeTypeStatus typeStatus = SubscribeTypeStatus.SPARE;
	
	
	//申购单所关联的申购明细集合 zzb 2011-03-01
	private Set<SubscribeDetail> subscribeDetails = new HashSet<SubscribeDetail>();
	//汇总单明细的数量 zzb 2011-03-01
	private Integer sumDetail;
    //汇总单明细的采购数量 zzb 2011-03-01
	private Integer purNum;
	//汇总单明细的取消数量 zzb 2011-03-01
	private Integer calNum;
	// 待确认数 zzb 2011-03-01
	private Integer conNum;
	//金额 zzb 2011-03-01
	private Double totalMoney;
	//入库项 zzb
	private Integer insNum;
	
	
	
	public Set<SubscribeDetail> getSubscribeDetails() {
		return subscribeDetails;
	}


	public void setSubscribeDetails(Set<SubscribeDetail> subscribeDetails) {
		this.subscribeDetails = subscribeDetails;
	}


	public Integer getSumDetail() {
		return sumDetail;
	}


	public void setSumDetail(Integer sumDetail) {
		this.sumDetail = sumDetail;
	}


	@Override
	public boolean equals(Object arg0) {
		return false;
	}


	@Override
	public int hashCode() {
		return 0;
	}


	public SubscribeCollectBillTypeStatus getBillStatus() {
		return billStatus;
	}


	public void setBillStatus(SubscribeCollectBillTypeStatus billStatus) {
		this.billStatus = billStatus;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public Date getCollectDate() {
		return collectDate;
	}


	public void setCollectDate(Date collectDate) {
		this.collectDate = collectDate;
	}


	public Department getCollectDept() {
		return collectDept;
	}


	public void setCollectDept(Department collectDept) {
		this.collectDept = collectDept;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public User getCollectPerson() {
		return collectPerson;
	}


	public void setCollectPerson(User collectPerson) {
		this.collectPerson = collectPerson;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public SysModel getToolingDevFlag() {
		return toolingDevFlag;
	}


	public void setToolingDevFlag(SysModel toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}


	public SubscribeTypeStatus getTypeStatus() {
		return typeStatus;
	}


	public void setTypeStatus(SubscribeTypeStatus typeStatus) {
		this.typeStatus = typeStatus;
	}


	public Integer getCalNum() {
		return calNum;
	}


	public void setCalNum(Integer calNum) {
		this.calNum = calNum;
	}


	public Integer getConNum() {
		return conNum;
	}


	public void setConNum(Integer conNum) {
		this.conNum = conNum;
	}


	public Integer getPurNum() {
		return purNum;
	}


	public void setPurNum(Integer purNum) {
		this.purNum = purNum;
	}


	public Double getTotalMoney() {
		return totalMoney;
	}


	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}


	public Integer getInsNum() {
		return insNum;
	}


	public void setInsNum(Integer insNum) {
		this.insNum = insNum;
	}

}

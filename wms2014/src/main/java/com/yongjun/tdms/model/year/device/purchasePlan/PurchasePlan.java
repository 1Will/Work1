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
package com.yongjun.tdms.model.year.device.purchasePlan;

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

/**
 * 
 * <p>Title: PurchasePlan
 * <p>Description: 设备年度采购计划类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: PurchasePlan.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class PurchasePlan extends BaseInfoEntity implements CreatorTracking,
CreatedTimeTracking, LastOperatorTracking, LastModifiedTimeTracking {
	private static final long serialVersionUID = 2632858517867365167L;

	//采购计划编号
	private String planNo;
	//采购计划名称
	private String name;
	//年度
	private String year;
	//计划总费用
	private Double planAllFee = 0.0;
	//编制日期 默认为当前日期
	private Date planCreatedDate = new Date();
	//编制人
	private User planCreator;
	//部门
	private Department department;
	//备注
	private String comment;
	//关联的采购计划明细
	private Set<PurchasePlanDetail> purchasePlanDetail = new HashSet<PurchasePlanDetail>();
	
	public PurchasePlan() {}
	
	@Override
	public int hashCode() {
		return this.getPlanNo().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {return true;}
		if (!(o instanceof PurchasePlan)) {
			return false;
		}
		PurchasePlan plan = (PurchasePlan)o;
		if (getPlanNo().equals(plan.getPlanNo())) {
			return false;
		}
		return false;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPlanAllFee() {
		return planAllFee;
	}

	public void setPlanAllFee(Double planAllFee) {
		this.planAllFee = planAllFee;
	}

	public Date getPlanCreatedDate() {
		return planCreatedDate;
	}

	public void setPlanCreatedDate(Date planCreatedDate) {
		this.planCreatedDate = planCreatedDate;
	}

	public User getPlanCreator() {
		return planCreator;
	}

	public void setPlanCreator(User planCreator) {
		this.planCreator = planCreator;
	}

	public String getPlanNo() {
		return planNo;
	}

	public void setPlanNo(String planNo) {
		this.planNo = planNo;
	}

	public Set<PurchasePlanDetail> getPurchasePlanDetail() {
		return purchasePlanDetail;
	}

	public void setPurchasePlanDetail(Set<PurchasePlanDetail> purchasePlanDetail) {
		this.purchasePlanDetail = purchasePlanDetail;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

}

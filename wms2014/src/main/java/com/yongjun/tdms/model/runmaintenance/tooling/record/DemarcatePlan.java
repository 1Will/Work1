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
package com.yongjun.tdms.model.runmaintenance.tooling.record;

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
import com.yongjun.pluto.model.security.Department;

/**
 * @author qs
 * @version $Id: DemarcatePlan.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class DemarcatePlan extends BaseInfoEntity implements CreatorTracking,
		CreatedTimeTracking, LastOperatorTracking, LastModifiedTimeTracking, OrganizationTracking {
	private static final long serialVersionUID = 4204946687368448476L;
	private String planNo;                        //标定计划编号
	private String planName;                     //标定计划名称
	private String comment;                      //备注
	private Date planStartTime;                  //计划标定日期
    private DemarcateStatus planStatus;          //计划单状态 
	private Department department;                //部门
	private User assessor;                        //编制人
	private User organizer;                       //审核
	private User ratifier;                        //批准人
	private Set<DemarcatePlanDetail> demarcateDetails = new HashSet<DemarcatePlanDetail>();

	@Override
	public int hashCode() {
		return planNo.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof DemarcatePlan)) {
			return false;
		}
		DemarcatePlan demarcatePlan = (DemarcatePlan) o;
		if (this.planNo.equals(demarcatePlan.getPlanNo())) {
			return true;
		}
		return false;
	}

	public User getAssessor() {
		return assessor;
	}

	public void setAssessor(User assessor) {
		this.assessor = assessor;
	}

	public Set<DemarcatePlanDetail> getDemarcateDetails() {
		return demarcateDetails;
	}

	public void setDemarcateDetails(Set<DemarcatePlanDetail> demarcateDetails) {
		this.demarcateDetails = demarcateDetails;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public User getOrganizer() {
		return organizer;
	}

	public void setOrganizer(User organizer) {
		this.organizer = organizer;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getPlanNo() {
		return planNo;
	}

	public void setPlanNo(String planNo) {
		this.planNo = planNo;
	}
	
	public User getRatifier() {
		return ratifier;
	}

	public void setRatifier(User ratifier) {
		this.ratifier = ratifier;
	}

	public DemarcateStatus getPlanStatus() {
		return planStatus;
	}

	public void setPlanStatus(DemarcateStatus planStatus) {
		this.planStatus = planStatus;
	}

	public Date getPlanStartTime() {
		return planStartTime;
	}

	public void setPlanStartTime(Date planStartTime) {
		this.planStartTime = planStartTime;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}

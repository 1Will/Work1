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
package com.yongjun.tdms.model.runmaintenance.intactness;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.pluto.model.security.Department;

/**
 * @author qs
 * @version $Id: Intactness.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class Intactness extends BaseInfoEntity {
	private static final long serialVersionUID = 5071668400469043570L;
	// 鉴定单编号
	private String code;
	// 鉴定单名称
	private String name;
	// 鉴定人
	private String examiner;
	// 鉴定日期
	private Date examDate;
	// 备注
	private String comment;
	// 部门
	private Department department;   
	// 鉴定的明细项目
	private Set<IntactnessDetail> intactnessDetails = new HashSet<IntactnessDetail>();
	
	public Intactness() {
		
	}
	
	public Set<IntactnessDetail> getIntactnessDetails() {
		return intactnessDetails;
	}

	public void setIntactnessDetails(Set<IntactnessDetail> intactnessDetails) {
		this.intactnessDetails = intactnessDetails;
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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getExaminer() {
		return examiner;
	}

	public void setExaminer(String examiner) {
		this.examiner = examiner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return code.hashCode();
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

}

/*
 * Copyright (c) 2001-2009 YongJun Technology Pte.,Ltd. All Rights Reserved.
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
package com.yongjun.tdms.model.employee;

import java.util.Date;

import com.yongjun.pluto.model.base.institution.Institution;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.base.area.Area;
import com.yongjun.tdms.model.codevalue.CodeValue;

/**
 * <p>
 * Title: Employee Model
 * <p>
 * Description: Employee Model
 * </p>
 * <p>
 * Copyright: Copyright (c) 2001 yj-technology
 * </p>
 * <p>
 * Company: www.yj-technology.com
 * </p>
 * 
 * @author xmZhang@yj-technology.com
 * @version
 */

public class Employee extends BaseInfoEntity implements CreatedTimeTracking,
		CreatorTracking, LastOperatorTracking {

	private static final long serialVersionUID = -6413172449974648625L;

	// 员工工号(非空)
	private String employeeNo;

	// 员工姓名(非空)
	private String name;

	// 员工档案编号
	private String archiveCode;

	// 公司(非空)
	private Institution inst;

	// 部门(非空)
	private Department dept;

	// 职务(非空)
	//private String duty;
	private CodeValue duty;
	
	// 工作方式
	private CodeValue workingMode;

	// 工作地点
	private String workingPlace;

	// 性别(非空)
	private boolean sex;

	// 身份证号(非空)
	private String identityCard;

	// 员工所关联的籍贯
	private Area nationality;

	// 员工所关联的政治面貌
	private CodeValue politics;

	// 电话
	private String telephone;

	// 手机(非空)
	private String cellphone;

	// E-MAIL
	private String email;

	// 是否销售人员
	private boolean isSale;

	// 是否系统用户
	private boolean isSysUser;

	// 用户名
	private String userName;

	// 出生日期
	private Date birthday;

	// 入职日期
	private Date staffTime;

	// 转正日期
	private Date officiallyTime;

	// 婚姻状况
	//private String isMarried;
	private CodeValue isMarried;
	
	// 状态
	private CodeValue status;

	@Override
	public boolean equals(Object o) {
//		if (o == this) {
//			return true;
//		}
//		if (!(o instanceof Employee)) {
//			return false;
//		}
		return false;
	}

	public String getArchiveCode() {
		return archiveCode;
	}

	public Date getBirthday() {
		return birthday;
	}

	public String getCellphone() {
		return cellphone;
	}

	public Department getDept() {
		return dept;
	}

	/*public String getDuty() {
		return duty;
	}*/

	public String getEmail() {
		return email;
	}

	public String getEmployeeNo() {
		return employeeNo;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public Institution getInst() {
		return inst;
	}

	public CodeValue getIsMarried() {
		return isMarried;
	}

	public boolean getIsSale() {
		return isSale;
	}

	public boolean getIsSysUser() {
		return isSysUser;
	}

	public String getName() {
		return name;
	}

	public Area getNationality() {
		return nationality;
	}

	public Date getOfficiallyTime() {
		return officiallyTime;
	}

	public CodeValue getPolitics() {
		return politics;
	}

	public Date getStaffTime() {
		return staffTime;
	}

	public CodeValue getStatus() {
		return status;
	}

	public String getTelephone() {
		return telephone;
	}

	public String getUserName() {
		return userName;
	}

	public CodeValue getWorkingMode() {
		return workingMode;
	}

	public String getWorkingPlace() {
		return workingPlace;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isSex() {
		return sex;
	}

	public void setArchiveCode(String archiveCode) {
		this.archiveCode = archiveCode;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	/*public void setDuty(String duty) {
		this.duty = duty;
	}*/

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public void setInst(Institution inst) {
		this.inst = inst;
	}

	public void setIsMarried(CodeValue isMarried) {
		this.isMarried = isMarried;
	}

	public void setIsSale(boolean isSale) {
		this.isSale = isSale;
	}

	public void setIsSysUser(boolean isSysUser) {
		this.isSysUser = isSysUser;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNationality(Area nationality) {
		this.nationality = nationality;
	}

	public void setOfficiallyTime(Date officiallyTime) {
		this.officiallyTime = officiallyTime;
	}

	public void setPolitics(CodeValue politics) {
		this.politics = politics;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public void setStaffTime(Date staffTime) {
		this.staffTime = staffTime;
	}

	public void setStatus(CodeValue status) {
		this.status = status;
	}
	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setWorkingMode(CodeValue workingMode) {
		this.workingMode = workingMode;
	}

	public void setWorkingPlace(String workingPlace) {
		this.workingPlace = workingPlace;
	}

	public CodeValue getDuty() {
		return duty;
	}

	public void setDuty(CodeValue duty) {
		this.duty = duty;
	}


}

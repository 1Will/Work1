package com.yongjun.tdms.model.personnelFiles;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.base.institution.Institution;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.tdms.model.base.area.Area;
import com.yongjun.tdms.model.base.duty.Duty;
import java.util.Date;

public class PersonnelFiles extends BaseInfoEntity
		implements CreatedTimeTracking, CreatorTracking, LastOperatorTracking {
	private static final long serialVersionUID = 1L;
	private String code;
	private String name;
	private String fileNo;
	private Institution inst;
	private Department dept;
	private Duty duty;
	private boolean sex = false;
	private String idNumber;
	private Date birthday;
	private CodeValue marriage;
	private Area birthplace;
	private CodeValue national;
	private CodeValue politice;
	private CodeValue education;
	private String homeTel;
	private String mobile;
	private String telphone;
	private String email;
	private Date entryDate;
	private Date regularizedDate;
	private Date separationDate;
	private String address;
	private CodeValue workway;
	private CodeValue status;
	private PersonnelFiles superiorLeader;
	private CodeValue businessType;

	public boolean equals(Object arg0) {
		if (arg0 == this) {
			return true;
		}
		if (!(arg0 instanceof PersonnelFiles)) {
			return false;
		}
		return false;
	}

	public int hashCode() {
		return 0;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Area getBirthplace() {
		return this.birthplace;
	}

	public void setBirthplace(Area birthplace) {
		this.birthplace = birthplace;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Duty getDuty() {
		return this.duty;
	}

	public void setDuty(Duty duty) {
		this.duty = duty;
	}

	public CodeValue getEducation() {
		return this.education;
	}

	public void setEducation(CodeValue education) {
		this.education = education;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getEntryDate() {
		return this.entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public String getFileNo() {
		return this.fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	public String getHomeTel() {
		return this.homeTel;
	}

	public void setHomeTel(String homeTel) {
		this.homeTel = homeTel;
	}

	public String getIdNumber() {
		return this.idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public Department getDept() {
		return this.dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public Institution getInst() {
		return this.inst;
	}

	public void setInst(Institution inst) {
		this.inst = inst;
	}

	public CodeValue getMarriage() {
		return this.marriage;
	}

	public void setMarriage(CodeValue marriage) {
		this.marriage = marriage;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CodeValue getNational() {
		return this.national;
	}

	public void setNational(CodeValue national) {
		this.national = national;
	}

	public CodeValue getPolitice() {
		return this.politice;
	}

	public void setPolitice(CodeValue politice) {
		this.politice = politice;
	}

	public Date getRegularizedDate() {
		return this.regularizedDate;
	}

	public void setRegularizedDate(Date regularizedDate) {
		this.regularizedDate = regularizedDate;
	}

	public Date getSeparationDate() {
		return this.separationDate;
	}

	public void setSeparationDate(Date separationDate) {
		this.separationDate = separationDate;
	}

	public boolean isSex() {
		return this.sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public CodeValue getStatus() {
		return this.status;
	}

	public void setStatus(CodeValue status) {
		this.status = status;
	}

	public String getTelphone() {
		return this.telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public CodeValue getWorkway() {
		return this.workway;
	}

	public void setWorkway(CodeValue workway) {
		this.workway = workway;
	}

	public PersonnelFiles getSuperiorLeader() {
		return superiorLeader;
	}

	public void setSuperiorLeader(PersonnelFiles superiorLeader) {
		this.superiorLeader = superiorLeader;
	}

	public CodeValue getBusinessType() {
		return businessType;
	}

	public void setBusinessType(CodeValue businessType) {
		this.businessType = businessType;
	}

}

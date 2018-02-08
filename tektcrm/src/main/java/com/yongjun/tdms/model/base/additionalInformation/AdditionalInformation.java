package com.yongjun.tdms.model.base.additionalInformation;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.pluto.model.tracking.OrganizationTracking;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;

public class AdditionalInformation extends BaseInfoEntity implements CreatedTimeTracking, CreatorTracking,
LastOperatorTracking, LastModifiedTimeTracking, OrganizationTracking{
	
	private static final long serialVersionUID = 1L;
	private Department department;
	private PersonnelFiles leader;//部门负责人
	private PersonnelFiles manageLeader;//公司业务分管领导
	private PersonnelFiles hrLeader;//公司分管人力资源领导

	
	
	
	
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public PersonnelFiles getLeader() {
		return leader;
	}

	public void setLeader(PersonnelFiles leader) {
		this.leader = leader;
	}

	public PersonnelFiles getManageLeader() {
		return manageLeader;
	}

	public void setManageLeader(PersonnelFiles manageLeader) {
		this.manageLeader = manageLeader;
	}

	public PersonnelFiles getHrLeader() {
		return hrLeader;
	}

	public void setHrLeader(PersonnelFiles hrLeader) {
		this.hrLeader = hrLeader;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
	      return true;
			   }
	    if (!(obj instanceof AdditionalInformation)) {
          return false;
	    }
	    AdditionalInformation o = (AdditionalInformation)obj;
	     
	     if (!o.getId().equals(getId())) {
	           return false;
	        }
	        return true;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return  getId().hashCode();
	}

}

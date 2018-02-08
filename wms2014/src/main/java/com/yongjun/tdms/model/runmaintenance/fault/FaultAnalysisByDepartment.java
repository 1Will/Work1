/**
 * 
 */
package com.yongjun.tdms.model.runmaintenance.fault;

import com.yongjun.pluto.model.security.Department;

/**
 * @author Administrator
 *
 */
public class FaultAnalysisByDepartment extends BaseFaultAnalysis {
	
	private Department department;
	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}

/**
 * 
 */
package com.yongjun.tdms.model.runmaintenance.fault;

import com.yongjun.tdms.model.base.codevalue.CodeValue;

/**
 * @author Administrator
 *
 */
public class FaultAnalysisByFaultCatorgy extends BaseFaultAnalysis {

	private CodeValue faultCatorgy;
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

	public CodeValue getFaultCatorgy() {
		return faultCatorgy;
	}

	public void setFaultCatorgy(CodeValue faultCatorgy) {
		this.faultCatorgy = faultCatorgy;
	}

}

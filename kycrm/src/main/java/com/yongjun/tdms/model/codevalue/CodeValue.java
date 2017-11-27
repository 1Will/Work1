/**
 * 
 */
package com.yongjun.tdms.model.codevalue;

import java.util.HashSet;
import java.util.Set;

import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.tdms.model.BaseInfoEntity;

/**
 * @author Administrator
 *
 */
public class CodeValue extends BaseInfoEntity implements
CreatedTimeTracking ,CreatorTracking,LastOperatorTracking{

	private static final long serialVersionUID = 1L;
	private String code;
	private String name;
	private CodeValue parentCV;
	private Set<CodeValue> childCV = new HashSet<CodeValue>();
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<CodeValue> getChildCV() {
		return childCV;
	}

	public void setChildCV(Set<CodeValue> childCV) {
		this.childCV = childCV;
	}

	public CodeValue getParentCV() {
		return parentCV;
	}

	public void setParentCV(CodeValue parentCV) {
		this.parentCV = parentCV;
	}

}

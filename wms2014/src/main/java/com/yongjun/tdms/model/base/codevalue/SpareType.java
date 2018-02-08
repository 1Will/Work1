/**
 * 
 */
package com.yongjun.tdms.model.base.codevalue;

import java.util.HashSet;
import java.util.Set;

import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.SysModel;

/**
 *<p>Title:SpareType.java
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author yangli@yj-technology.com
 * @version 
 */
public class SpareType extends BaseInfoEntity implements LastModifiedTimeTracking,
LastOperatorTracking, CreatedTimeTracking, CreatorTracking {
    private static final long serialVersionUID = 1L;
	//代码
	private String code;
	//名称
	private String name;
	//编码
	private String realCode;
	//类别
	private SysModel toolingDevFlag;
	//关联明细
	private Set<SpareDetailType> spareDetailType = new  HashSet<SpareDetailType>();
	
	@Override
	public boolean equals(Object arg0) {
		return false;
	}

	@Override
	public int hashCode() {
		return 0;
	}

	public String getCode() {
    	return code;
    }

	public void setCode(String code) {
    	this.code = code;
    }

	public SysModel getToolingDevFlag() {
    	return toolingDevFlag;
    }

	public void setToolingDevFlag(SysModel toolingDevFlag) {
    	this.toolingDevFlag = toolingDevFlag;
    }

	public String getName() {
    	return name;
    }

	public void setName(String name) {
    	this.name = name;
    }

	public String getRealCode() {
    	return realCode;
    }

	public void setRealCode(String realCode) {
    	this.realCode = realCode;
    }

	public Set<SpareDetailType> getSpareDetailType() {
    	return spareDetailType;
    }

	public void setSpareDetailType(Set<SpareDetailType> spareDetailType) {
    	this.spareDetailType = spareDetailType;
    }

}

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
package com.yongjun.tdms.model.base.codevalue;

import java.util.HashSet;
import java.util.Set;

import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.tdms.model.BaseInfoEntity;

/**
 * <p>Title: CodeValue
 * <p>Description: 系统代码类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author qs@yj-technology.com
 * @version $Id: CodeValue.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class CodeValue extends BaseInfoEntity implements LastModifiedTimeTracking,
		LastOperatorTracking, CreatedTimeTracking, CreatorTracking {
	private static final long serialVersionUID = -7382213500560698497L;
	private String code;					// 代码
	private String value;					// 类型或者值
	private boolean readOnly;		// 是否为只读
	/**
	 * realCode和referCode同Code的区别在于，它是可以被程序引用的，内容同static定义的字段一致
	 * 备件分类区别TOOLING和DEVICE字段
	 * 
	 */
	private String realCode;	
	private String referCode;        //编码
	private String comment;			// 备注
	private CodeValue masterCode;	// 如果为类型，则这个域为null，否则为类型实体
	private Set<CodeValue> values = new HashSet<CodeValue>();	// 如果为类型，这个集合包含了该类型下的具体值
	//private Set<SpareDetailType> spareDetailType = new  HashSet<SpareDetailType>();
	
	public CodeValue() {
		
	}
	
	public String getRealCode() {
		return realCode;
	}
	
	public void setRealCode(String realCode) {
		this.realCode = realCode;
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

	public CodeValue getMasterCode() {
		return masterCode;
	}

	public void setMasterCode(CodeValue masterCode) {
		this.masterCode = masterCode;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public Set<CodeValue> getValues() {
		return values;
	}

	public void setValues(Set<CodeValue> values) {
		this.values = values;
	}

	@Override
	public int hashCode() {
		return code.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof CodeValue)) {
			return false;
		}
		CodeValue c = (CodeValue)o;
		if (getId().equals(c.getId())) {
			return true;
		}
		return false;
	}

	public String getReferCode() {
		return referCode;
	}

	public void setReferCode(String referCode) {
		this.referCode = referCode;
	}

//	public Set<SpareDetailType> getSpareDetailType() {
//		return spareDetailType;
//	}
//
//	public void setSpareDetailType(Set<SpareDetailType> spareDetailType) {
//		this.spareDetailType = spareDetailType;
//	}
}

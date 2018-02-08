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
package com.yongjun.tdms.model.base.org;

import com.yongjun.tdms.model.BaseInfoEntity;

/**
 * @author qs
 * @version $Id: WorkGroup.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
@Deprecated
public class WorkGroup extends BaseInfoEntity {
	private static final long serialVersionUID = 5830606323676813015L;
	private String code;
	private String name;
	
	public WorkGroup() {
		
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
	
	@Override
	public int hashCode() {
		return code.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof WorkGroup)) { return false; }
		WorkGroup w = (WorkGroup)o;
		if (!(code.equals(w.getCode()))) { return false; }
		return true;
	}

}

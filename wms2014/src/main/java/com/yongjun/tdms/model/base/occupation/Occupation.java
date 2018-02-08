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
package com.yongjun.tdms.model.base.occupation;

import com.yongjun.tdms.model.BaseInfoEntity;

/**
 * @author qs
 * @version $Id: Occupation.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class Occupation extends BaseInfoEntity {
	private static final long serialVersionUID = 5807912306439347720L;
	// 工种编码
	private String code;
	// 工种名称
	private String name;
	// 工种单位时间内费用（小时计）
	private Double fee;
	
	public Occupation() {
		
	}
	
	public String getCode() {
		return code;
	}
	


	public void setCode(String code) {
		this.code = code;
	}
	


	public Double getFee() {
		return fee;
	}
	


	public void setFee(Double fee) {
		this.fee = fee;
	}
	


	public String getName() {
		return name;
	}
	


	public void setName(String name) {
		this.name = name;
	}
	


	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}

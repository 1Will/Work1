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
package com.yongjun.tdms.model.runmaintenance.intactness;

import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.asset.device.DeviceCard;

/**
 * @author qs
 * @version $Id: IntactnessDetail.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class IntactnessDetail extends BaseInfoEntity {
	private static final long serialVersionUID = 1120264872724725717L;
	// 鉴定结果，默认只记录不完好的
	private IntactnessResultType result = IntactnessResultType.UNINTACT;
	// 备注
	private String comment;
	// 鉴定的设备
	private DeviceCard device;
	
	private Intactness intactness;
	
	public IntactnessDetail() {
		
	}
	
	public Intactness getIntactness() {
		return intactness;
	}

	public void setIntactness(Intactness intactness) {
		this.intactness = intactness;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public DeviceCard getDevice() {
		return device;
	}

	public void setDevice(DeviceCard device) {
		this.device = device;
	}

	public IntactnessResultType getResult() {
		return result;
	}

	public void setResult(IntactnessResultType result) {
		this.result = result;
	}

	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}

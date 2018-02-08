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
package com.yongjun.tdms.model.runmaintenance.checkpoint;

import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.asset.device.DeviceCard;

/**
 * <p>Title: CheckPointReportDetail
 * <p>Description: 点检报告明细类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: CheckPointReportDetail.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class CheckPointReportDetail extends BaseInfoEntity{
	private static final long serialVersionUID = 1113002135423194181L;
	private CheckPointReport checkPointReport;	//关联的
	private DeviceCard device;              	//关联的设备
	private Float runTime;						//运行台时
	private Float maintenanceTime ;				//保养台时
	private Float faultTime ;					//故障台时
	private String comment;                 	//备注
	private Long productTotalOutput;			//产品总产量
	private Long inferiorProductOutput;		//次产品产量
	
	public CheckPointReport getCheckPointReport() {
		return checkPointReport;
	}

	public void setCheckPointReport(CheckPointReport checkPointReport) {
		this.checkPointReport = checkPointReport;
	}

	public DeviceCard getDevice() {
		return device;
	}

	public void setDevice(DeviceCard device) {
		this.device = device;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Float getFaultTime() {
		return faultTime;
	}

	public void setFaultTime(Float faultTime) {
		this.faultTime = faultTime;
	}

	public Float getMaintenanceTime() {
		return maintenanceTime;
	}

	public void setMaintenanceTime(Float maintenanceTime) {
		this.maintenanceTime = maintenanceTime;
	}

	public Float getRunTime() {
		return runTime;
	}

	public void setRunTime(Float runTime) {
		this.runTime = runTime;
	}

	/**
	 * @return the inferiorProductOutput
	 */
	public Long getInferiorProductOutput() {
		return inferiorProductOutput;
	}

	/**
	 * @param inferiorProductOutput the inferiorProductOutput to set
	 */
	public void setInferiorProductOutput(Long inferiorProductOutput) {
		this.inferiorProductOutput = inferiorProductOutput;
	}

	/**
	 * @return the productTotalOutput
	 */
	public Long getProductTotalOutput() {
		return productTotalOutput;
	}

	/**
	 * @param productTotalOutput the productTotalOutput to set
	 */
	public void setProductTotalOutput(Long productTotalOutput) {
		this.productTotalOutput = productTotalOutput;
	}

}

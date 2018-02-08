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
package com.yongjun.tdms.model.runmaintenance.accident;

import java.util.Date;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.asset.device.DeviceCard;

/**
 * <p>Title: AccidentBill
 * <p>Description: 事故单类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: AccidentBill.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class AccidentBill extends BaseInfoEntity implements CreatorTracking,
CreatedTimeTracking, LastOperatorTracking, LastModifiedTimeTracking {
	private static final long serialVersionUID = -8211325785557397974L;
	private String billNo;                     //事故单编号
	private String billName;                   //事故单名称
	private Date accidentOccurDateTm;          //事故发生时间
	private String accidentDetailContent;      //事故详细
	private String accidentSolution;           //解决方案
	private DeviceCard toolingDev;             //[工装]|[设备]
	private User manager;                     //负责人
	private SysModel toolingDevFlag; // 标示为设备或工装

	public String getAccidentDetailContent() {
		return accidentDetailContent;
	}

	public void setAccidentDetailContent(String accidentDetailContent) {
		this.accidentDetailContent = accidentDetailContent;
	}

	public Date getAccidentOccurDateTm() {
		return accidentOccurDateTm;
	}

	public void setAccidentOccurDateTm(Date accidentOccurDateTm) {
		this.accidentOccurDateTm = accidentOccurDateTm;
	}

	public String getAccidentSolution() {
		return accidentSolution;
	}

	public void setAccidentSolution(String accidentSolution) {
		this.accidentSolution = accidentSolution;
	}

	public String getBillName() {
		return billName;
	}

	public void setBillName(String billName) {
		this.billName = billName;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

	public DeviceCard getToolingDev() {
		return toolingDev;
	}

	public void setToolingDev(DeviceCard toolingDev) {
		this.toolingDev = toolingDev;
	}

	@Override
	public int hashCode() {
		return this.billNo.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof AccidentBill)) {return false;}
		
		AccidentBill accidentBill = (AccidentBill)o;
		if (this.billNo.equals(accidentBill.getBillNo())) {
			return true;
		}
			
		return false;
	}

	public SysModel getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(SysModel toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}
	
}

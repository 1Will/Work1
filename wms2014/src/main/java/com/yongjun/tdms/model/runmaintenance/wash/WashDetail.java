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
package com.yongjun.tdms.model.runmaintenance.wash;

import java.util.Date;

import com.yongjun.pluto.model.security.User;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.base.product.Product;
import com.yongjun.tdms.model.runmaintenance.maintenance.MaintenanceResultType;

/**
 * <p>Title: WashDetail
 * <p>Description: 清洗明细类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: WashDetail.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class WashDetail extends BaseInfoEntity {
	private static final long serialVersionUID = -3404573418604674056L;

	//计划清洗日期
	private Date planWashDate;
	
	//实际清洗日期
	private Date procWashDate;
	
	//备注
	private String comment;
	
	//清洗结果
	private WashDetailResult washResult=WashDetailResult.UNFINISHED;
	
	//负责人
	private User dutyPeople;
	
	//监督人
	private User supervisePeople;
	
	//产品型号
	private Product productModel;
	
	//工装
	private DeviceCard tooling;
	
	//关联的计划
	private Wash plan;
	
	//关联的实施
	private Wash proc;
	
	@Override
	public int hashCode() {
		return this.getId().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof WashDetail)) {return false;}
		
		WashDetail detail = (WashDetail)o;
		if (this.getId().equals(detail.getId())) {return true;}
		return false;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public User getDutyPeople() {
		return dutyPeople;
	}

	public void setDutyPeople(User dutyPeople) {
		this.dutyPeople = dutyPeople;
	}

	public Wash getPlan() {
		return plan;
	}

	public void setPlan(Wash plan) {
		this.plan = plan;
	}
	public Date getPlanWashDate() {
		return planWashDate;
	}

	public void setPlanWashDate(Date planWashDate) {
		this.planWashDate = planWashDate;
	}

	public Wash getProc() {
		return proc;
	}

	public void setProc(Wash proc) {
		this.proc = proc;
	}

	public Date getProcWashDate() {
		return procWashDate;
	}

	public void setProcWashDate(Date procWashDate) {
		this.procWashDate = procWashDate;
	}

	public Product getProductModel() {
		return productModel;
	}

	public void setProductModel(Product productModel) {
		this.productModel = productModel;
	}

	public User getSupervisePeople() {
		return supervisePeople;
	}

	public void setSupervisePeople(User supervisePeople) {
		this.supervisePeople = supervisePeople;
	}

	public DeviceCard getTooling() {
		return tooling;
	}

	public void setTooling(DeviceCard tooling) {
		this.tooling = tooling;
	}

	public WashDetailResult getWashResult() {
		return washResult;
	}

	public void setWashResult(WashDetailResult washResult) {
		this.washResult = washResult;
	}
	public String getwashResultTxt() {
		return this.washResult == WashDetailResult.UNFINISHED ? "UNFINISHED" : "FINISHED";
	}
}

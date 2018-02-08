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
package com.yongjun.tdms.model.runmaintenance.repair;

import java.util.Date;

import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.asset.device.DeviceCard;

/**
 * <p>Title: PreRepairRule
 * <p>Description: 预防性维修标准类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: PreRepairRule.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class PreRepairRule extends BaseInfoEntity {
	private static final long serialVersionUID = -7852063465130312106L;
	// 维修部位
	private String position;
	
	// 维修内容
	private String content;
	
	// 上次维修日期
	private Date lastRepairDate;
	
	// 维修运行台时阀值（单位：时）,工装的件数
	private Integer maxRunHour;
	
	//	预防性维修累积时间
	private Double preRepairTime = 0.0;
	
	// 设备或者工装
	private DeviceCard asset;
	
	public PreRepairRule() {
		
	}
	
	public DeviceCard getAsset() {
		return asset;
	}

	public void setAsset(DeviceCard asset) {
		this.asset = asset;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getLastRepairDate() {
		return lastRepairDate;
	}

	public void setLastRepairDate(Date lastRepairDate) {
		this.lastRepairDate = lastRepairDate;
	}

	public Integer getMaxRunHour() {
		return maxRunHour;
	}

	public void setMaxRunHour(Integer maxRunHour) {
		this.maxRunHour = maxRunHour;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
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

	public Double getPreRepairTime() {
		return preRepairTime;
	}

	public void setPreRepairTime(Double preRepairTime) {
		this.preRepairTime = preRepairTime;
	}

}

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
package com.yongjun.tdms.model.runmaintenance.feedback;

import java.util.Date;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.pluto.model.tracking.OrganizationTracking;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.pluto.model.security.Department;

/**
 * <p>Title: Feedback
 * <p>Description: 反馈单类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: Feedback.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class Feedback extends BaseInfoEntity implements CreatorTracking,
		CreatedTimeTracking, LastOperatorTracking, LastModifiedTimeTracking,
		OrganizationTracking {
	private static final long serialVersionUID = 1L;

	private String feedbackNo;

	private String feedbackName;

	private DeviceCard device;

	private Department department; // 反馈部门

	private Date feedbaceDatetime; // 反馈日期

	private User manager; // 反馈人

	private String content; // 反馈内容

	@Override
	public int hashCode() {
		return this.feedbackNo.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Feedback)) {
			return false;
		}
		Feedback f = (Feedback) o;
		if (this.feedbackNo.equals(f.getFeedbackNo())) {
			return true;
		}
		return false;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public DeviceCard getDevice() {
		return device;
	}

	public void setDevice(DeviceCard device) {
		this.device = device;
	}

	public Date getFeedbaceDatetime() {
		return feedbaceDatetime;
	}

	public void setFeedbaceDatetime(Date feedbaceDatetime) {
		this.feedbaceDatetime = feedbaceDatetime;
	}

	public String getFeedbackNo() {
		return feedbackNo;
	}

	public void setFeedbackNo(String feedbackNo) {
		this.feedbackNo = feedbackNo;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

	public String getFeedbackName() {
		return feedbackName;
	}

	public void setFeedbackName(String feedbackName) {
		this.feedbackName = feedbackName;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
}

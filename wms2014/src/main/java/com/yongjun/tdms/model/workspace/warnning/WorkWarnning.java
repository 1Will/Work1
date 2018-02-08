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
package com.yongjun.tdms.model.workspace.warnning;

import java.util.Date;

import com.yongjun.pluto.model.security.User;
import com.yongjun.tdms.model.BaseInfoEntity;

/**
 * <p>Title: WorkWarnning
 * <p>Description: 我的提醒类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: WorkWarnning.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class WorkWarnning extends BaseInfoEntity {
	private static final long serialVersionUID = 448275123928744135L;
	
	//提醒类型
	private String type;
	//提醒内容
	private String content;
	//提醒日期
	private Date warnningDate;
	//是否已读, 默认为未读
	private boolean readFlag = false;
	//提醒人
	private User warnedPeople;
	
	public WorkWarnning() {
	}
	
	@Override
	public int hashCode() {
		return this.getId().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {return true;}
		if (!(o instanceof WorkWarnning)) {
			return false;
		}
		return false;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public User getWarnedPeople() {
		return warnedPeople;
	}

	public void setWarnedPeople(User warnedPeople) {
		this.warnedPeople = warnedPeople;
	}

	public Date getWarnningDate() {
		return warnningDate;
	}

	public void setWarnningDate(Date warnningDate) {
		this.warnningDate = warnningDate;
	}

	public boolean isReadFlag() {
		return readFlag;
	}

	public void setReadFlag(boolean readFlag) {
		this.readFlag = readFlag;
	}


}

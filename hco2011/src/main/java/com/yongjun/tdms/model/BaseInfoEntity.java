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
package com.yongjun.tdms.model;

import java.util.Date;
import com.yongjun.pluto.model.VersionalEntity;
import com.yongjun.pluto.model.security.Organization;

public abstract class BaseInfoEntity extends VersionalEntity {
    private boolean disabled = false;				// 是否失效
    private String creator;									// 创建人
    private Date createdTime = new Date();	// 创建日期时间
    private String lastOperator;						// 更新人
    private Date lastModifiedTime =new Date();	// 更新日期时间
    private Organization organization;						// 记录所属组织
    
    public boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getCreator() {
    	return this.creator;
    }
    
    public void setCreator(String creator) {
    	this.creator = creator;
    }
    
    public Date getCreatedTime() {
    	return this.createdTime;
    }
    
    public void setCreatedTime(Date createdTime) {
    	this.createdTime = createdTime;
    }
    
    public String getLastOperator() {
        return lastOperator;
    }

    public void setLastOperator(String lastOperator) {
        this.lastOperator = lastOperator;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }
    
    public Organization getOrganization() {
    	return organization;
    }
    
    public void setOrganization(Organization organization) {
    	this.organization = organization;
    }
}


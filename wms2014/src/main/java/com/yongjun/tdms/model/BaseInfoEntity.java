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
import com.yongjun.tdms.workflow.model.job.Job;

/**
 * <p>Title: BaseInfoEntity
 * <p>Description: 所有Entity的基类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author qs@yj-technology.com
 * @version $Id: BaseInfoEntity.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public abstract class BaseInfoEntity extends VersionalEntity {
    private boolean disabled = false;				// 是否失效
    private String creator;									// 创建人
    private Date createdTime = new Date();	// 创建日期时间
    private String lastOperator;						// 更新人
    private Date lastModifiedTime =new Date();	// 更新日期时间
    private Job job;														// 工作流实体
    private Organization organization;						// 记录所属组织
    
    
    public BaseInfoEntity(){}
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
    
    public Job getJob() {
    	return job;
    }
    
    public void setJob(Job job) {
    	this.job = job;
    }
    
    public Organization getOrganization() {
    	return organization;
    }
    
    public void setOrganization(Organization organization) {
    	this.organization = organization;
    }
}


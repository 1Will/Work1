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
package com.yongjun.tdms.service.base.document.pojo;

import java.util.Collection;
import java.util.List;
import java.util.Properties;


import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.tdms.dao.base.document.ApplicationDocDao;
import com.yongjun.tdms.model.base.document.ApplicationDoc;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlanDetail;
import com.yongjun.tdms.service.base.document.ApplicationDocManager;

/**
 * @author qs
 * @version $Id: DefaultApplicationDocManager.java 11152 2008-02-28 13:05:20Z zbzhang $
 */
public class DefaultApplicationDocManager extends BaseManager implements ApplicationDocManager{
	private final ApplicationDocDao applicationDocDao;
	private final UserManager userManager;
	
	protected  Properties systemParameterConfiguration;
	
	
	public DefaultApplicationDocManager(ApplicationDocDao applicationDocDao,UserManager userManager){
		this.applicationDocDao = applicationDocDao;
		this.userManager = userManager;
	}
	
	public ApplicationDoc loadApplicationDoc(Long applicationDocId) {
		return applicationDocDao.loadApplicationDoc(applicationDocId);
	}

	public List<ApplicationDoc> loadAllApplicationDocs(Long[] applicationDocIds) {
		return applicationDocDao.loadAllApplicationDocs(applicationDocIds);
	}

	public List<ApplicationDoc> loadAllApplicationDocs() {
		return applicationDocDao.loadAllApplicationDocs();
	}

	public void storeApplicationDoc(ApplicationDoc applicationDoc) {
		applicationDoc.setCreator(this.userManager.getUser().getName());
		applicationDocDao.storeApplicationDoc(applicationDoc);
	}

	public void deleteApplicationDoc(ApplicationDoc applicationDoc) {
		applicationDocDao.deleteApplicationDoc(applicationDoc);
	}

	public void deleteAllApplicationDocs(Collection<ApplicationDoc> applicationDocs) {
		applicationDocDao.deleteAllApplicationDocs(applicationDocs);
	}

	public void resetRepairDoc(PreRepairPlanDetail newDetail, PreRepairPlanDetail oldDetail) {
			for (ApplicationDoc oldRepairDoc : oldDetail.getRepairDoc()) {
				ApplicationDoc newRepairDoc = new ApplicationDoc();         //新的维修技术文档
				newRepairDoc.setPreRepairPlanDetail(newDetail);             //设置新的维修技术文档所属的维修明细
				copyRepairDoc(newRepairDoc, oldRepairDoc);                  //拷贝老的维修技术文档到新的维修技术文档
				this.applicationDocDao.storeApplicationDoc(newRepairDoc);   
				newDetail.getRepairDoc().add(newRepairDoc);
			}
	}
    //	拷贝老的维修技术文档到新的维修技术文档  
	private void copyRepairDoc(ApplicationDoc newRepairDoc, ApplicationDoc oldRepairDoc) {
	//	newRepairDoc.setFileNo(oldRepairDoc.getFileName());              //拷贝原文件编号
		newRepairDoc.setFileName(oldRepairDoc.getFileName());            //拷贝原文件名称      
		newRepairDoc.setName(oldRepairDoc.getName());                    //拷贝文件名称
		newRepairDoc.setDescription(oldRepairDoc.getDescription());      //拷贝文件描述
		newRepairDoc.setPosition(oldRepairDoc.getPosition());            //拷贝文件路径
	}

	public Integer getNumberOfManualDoc() {
		return this.applicationDocDao.getNumberOfManualDoc();
	}

	public Properties getSystemParameterConfiguration() {
		return systemParameterConfiguration;
	}

	public void setSystemParameterConfiguration(
			Properties systemParameterConfiguration) {
		this.systemParameterConfiguration = systemParameterConfiguration;
	}

	public boolean isMostNumberForTheManualDoc() {
		String manualDocNumber = this.systemParameterConfiguration.getProperty("help_manual_number");
		if (manualDocNumber != null) {
			//实际已经存在的文件个数
			Integer number = this.getNumberOfManualDoc();
			//如果已经存在的文件个数大于配置的文件个数，则返回true
			if (number.compareTo(Integer.valueOf(manualDocNumber)) >= 0) {
				return true;
			}
		}
		return false;
	}

	public Integer getMostUploadNumberForManualDoc() {
		Integer number = 0;
		String manualDocNumber = this.systemParameterConfiguration.getProperty("help_manual_number");
		if (null != manualDocNumber) {
			number = Integer.valueOf(manualDocNumber);
		}
		return number;
	}

	public List<ApplicationDoc> getAllManualDoc() {
		return this.applicationDocDao.getAllManualDoc();
	}
	
	
	
}

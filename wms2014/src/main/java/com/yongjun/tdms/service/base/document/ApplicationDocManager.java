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
package com.yongjun.tdms.service.base.document;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.base.document.ApplicationDoc;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlanDetail;

/**
 * <p>Title: ApplicationDocManager
 * <p>Description: 技术资料文档上传业务接口定义类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: ApplicationDocManager.java 9931 2007-12-29 02:26:21Z wzou $
 */
@Transactional(readOnly = true)
public interface ApplicationDocManager {
	/**
	 * 根据传入的技术文档对象ID，导出该技术文档对象
	 * 
	 * @param applicationDocId	技术文档对象ID
	 * @return
	 */
	@Transactional
	public ApplicationDoc loadApplicationDoc(Long applicationDocId);
	
	/**
     * 传入技术文档对象ID集合，获取集合到数据库
     * 
     * @param applicationDocIds	技术文档对象ID集合
     * @return List				技术文档对象集合
     */
	public List<ApplicationDoc> loadAllApplicationDocs(Long [] applicationDocIds);
	
	/**
	 * 直接load技术文档对象集合
     * @return List				技术文档对象集合
     */
	public List<ApplicationDoc> loadAllApplicationDocs();			//工装变更使用到！
	
	/**
	 * 根据传入的技术文档对象，保存该技术文档对象
	 * 
	 * @param applicationDoc	技术文档对象
	 * @return
	 */
	@Transactional
	public void storeApplicationDoc(ApplicationDoc applicationDoc);

	/**
	 * 根据传入的技术文档对象，删除该技术文档对象
	 * 
	 * @param applicationDoc	技术文档对象
	 * @return
	 */
	@Transactional
	public void deleteApplicationDoc(ApplicationDoc applicationDoc);
	
	/**
	 * 根据传入的技术文档对象ID集合，删除集合中的技术文档对象
	 * 
	 * @param applicationDocs	技术文档对象ID集合
	 * @return
	 */
	@Transactional
	public void deleteAllApplicationDocs(Collection<ApplicationDoc> applicationDocs);
	
	@Transactional
	void resetRepairDoc(PreRepairPlanDetail newDetail, PreRepairPlanDetail oldDetail);
	
	/**
	 * 获取手册类型文件的个数
	 * @return
	 */
	public Integer getNumberOfManualDoc();
	
	/**
	 * 判断手册上传的个数是否已经达到最大值
	 * @return true | false
	 */
	public boolean isMostNumberForTheManualDoc();
	
	/**
	 * 获取手册最大的上传数量
	 * @return Integer  手册最大的上传数量
	 */
	public Integer getMostUploadNumberForManualDoc();
	
	/**
	 * 获取所有的手册对象
	 * @return List
	 */
	public List<ApplicationDoc> getAllManualDoc();
	
}

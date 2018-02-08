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
package com.yongjun.tdms.service.runmaintenance.feedback;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.runmaintenance.feedback.Feedback;

/**
 * <p>Title: FeedbackManager
 * <p>Description: 设备反馈详细业务接口定义类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: FeedbackManager.java 10070 2008-01-03 07:22:57Z wzou $
 */
@Transactional(readOnly=true)
public interface FeedbackManager {
	/**
     * 设备反馈单ID集合，获取集合到数据库
     * 
     * @param feedbackIds		设备反馈单ID集合
     * @return List				设备反馈单集合
     */
	List<Feedback> loadAllFeedbacks(Long[] feedbackIds);
	
	/**
	 * 根据传入的设备反馈单集合，删除集合中的设备反馈单
	 * 
	 * @param feedbacks	设备反馈单集合
	 * @return
	 */
	@Transactional
	void deleteAllFeedback(List feedbacks);
	
	/**
	 * 根据传入的设备反馈单ID，导出该设备反馈单对象
	 * 
	 * @param feedbackId	设备反馈单ID
	 * @return
	 */
	public Feedback loadFeedback(Long feedbackId);
	
	/**
	 * 根据传入的设备反馈单对象，保存该设备反馈单对象
	 * 
	 * @param feedback	设备反馈单对象
	 * @return
	 */
	@Transactional
	void storeFeedback(Feedback feedback);

}

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
package com.yongjun.tdms.service.runmaintenance.feedback.pojo;

import java.util.List;

import com.yongjun.pluto.sequence.service.SequenceManager;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.runmaintenance.feedback.FeedbackDao;
import com.yongjun.tdms.model.runmaintenance.feedback.Feedback;
import com.yongjun.tdms.service.runmaintenance.feedback.FeedbackManager;

public class DefaultFeedbackManager extends BaseManager implements FeedbackManager{
	private final FeedbackDao feedbackDao;
	private final SequenceManager sequenceManager;
	
	public DefaultFeedbackManager(
			FeedbackDao feedbackDao,
			SequenceManager sequenceManager){
		this.feedbackDao = feedbackDao;
		this.sequenceManager = sequenceManager;
	}

	public List<Feedback> loadAllFeedbacks(Long[] feedbackIds) {
		return this.feedbackDao.loadAllFeedbacks(feedbackIds);
	}

	public void deleteAllFeedback(List feedbacks) {
		this.feedbackDao.deleteAllFeedback(feedbacks);
	}

	public Feedback loadFeedback(Long feedbackId) {
		return this.feedbackDao.loadFeedback(feedbackId);
	}

	public void storeFeedback(Feedback feedback) {
		if(feedback.isNew()){
			String feedbackNo = (String)sequenceManager.generate("-");
			feedback.setFeedbackNo(feedbackNo);
		}
		this.feedbackDao.storeFeedback(feedback);
	}
}

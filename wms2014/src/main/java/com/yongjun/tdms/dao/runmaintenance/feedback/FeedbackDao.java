package com.yongjun.tdms.dao.runmaintenance.feedback;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.runmaintenance.feedback.Feedback;

public interface FeedbackDao {
	List<Feedback> loadAllFeedbacks(Long[] feedbackIds);
	
	@Transactional
	void deleteAllFeedback(List feedbacks);
	
	public Feedback loadFeedback(Long feedbackId);
	
	@Transactional
	void storeFeedback(Feedback feedback);
}

package com.yongjun.tdms.dao.runmaintenance.feedback.hibernate;

import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.runmaintenance.feedback.FeedbackDao;
import com.yongjun.tdms.model.runmaintenance.feedback.Feedback;

public class HibernateFeedback extends BaseHibernateDao implements FeedbackDao{

	public List<Feedback> loadAllFeedbacks(Long[] feedbackIds) {
		return this.loadAll(Feedback.class,feedbackIds);
	}

	@SuppressWarnings("unchecked")
	public void deleteAllFeedback(List feedbacks) {
		this.deleteAll(feedbacks);
	}

	public Feedback loadFeedback(Long feedbackId) {
		return this.load(Feedback.class,feedbackId);
	}

	public void storeFeedback(Feedback feedback) {
		this.store(feedback);
	}

}

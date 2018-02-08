package com.yongjun.tdms.presentation.webwork.action.runmaintenance.feedback;

import java.util.List;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.runmaintenance.feedback.Feedback;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.feedback.FeedbackManager;

public class ListFeedbackAction extends ValueListAction{
	private static final long serialVersionUID = 1L;
	private final FeedbackManager feedbackManager;
	private DepartmentManager departmentManager;
	
	private List<Feedback> feedbacks;

	public ListFeedbackAction(
			FeedbackManager feedbackManager,
			DepartmentManager departmentManager){
		this.feedbackManager = feedbackManager;
		this.departmentManager = departmentManager;
	}
	
	public void prepare() throws Exception{
		if(this.feedbacks == null && this.hasIds("feedbackIds")){
			this.feedbacks =  this.feedbackManager.loadAllFeedbacks(
					this.getIds("feedbackIds"));
		}
	}
	
	public String execute() throws Exception {
		if (this.isDelete()) {
			delete();
		}
		return SUCCESS;
	}
	
	public String delete() {
		try {
			this.feedbackManager.deleteAllFeedback(this.feedbacks);
		} catch (Exception e) {
			this.addActionMessage(this.getText("feedback.delete.error"));
			return ERROR;
		}

		this.addActionMessage(this.getText("feedback.delete.success"));
		return SUCCESS;
	}
	@Override
	protected String getAdapterName() {
		return "feedbacks";
	}
	
	public List getDepartments() {
		return departmentManager.createSelectDepartments(this
				.getText("select.option.all"));
	}
}

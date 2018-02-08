package com.yongjun.tdms.presentation.webwork.action.runmaintenance.feedback;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.tdms.model.runmaintenance.feedback.Feedback;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.feedback.FeedbackManager;

public class EditFeedbackAction extends PrepareAction {
	private static final long serialVersionUID = 4750567567139636882L;
	private final FeedbackManager feedbackManager;
	private final DeviceCardManager deviceCardManager;
	private final DepartmentManager departmentManager;
	private Feedback feedback;
	private DeviceCard device;
	private Department department;
    private User manager;
    
    public EditFeedbackAction(FeedbackManager feedbackManager,
    		DeviceCardManager deviceCardManager,
    		DepartmentManager departmentManager){
    	this.feedbackManager =feedbackManager;
    	this.deviceCardManager = deviceCardManager;
    	this.departmentManager = departmentManager;
    }
    
    public void prepare() throws Exception{
    	if (null == this.feedback) {
    		if (this.hasId("feedback.id")) {
    			this.feedback = this.feedbackManager.loadFeedback(this.getId("feedback.id"));
    			this.device = feedback.getDevice();
    		} else {
    			this.feedback = new Feedback();
    		}
    	}
    }
    
    public String save() {
    	if (!StringUtils.isEmpty(request.getParameter("device.id"))) {
			this.device = this.deviceCardManager.loadDevice(this.getId("device.id"));
			feedback.setDevice(device);
		}
    	boolean isNew = this.feedback.isNew();
    	feedback.setDevice(device);
    	feedback.setDepartment(department);
    	feedback.setManager(manager);
    	
    	this.feedbackManager.storeFeedback(feedback);
    	if (isNew) {
			this.addActionMessage(this.getText("feedback.add.success",
					Arrays.asList(new Object[] { feedback.getFeedbackName() })));

			return NEW;
		} else {
			this.addActionMessage(this.getText("feedback.edit.success",
					Arrays.asList(new Object[] { feedback.getFeedbackName() })));
			return SUCCESS;
		}
    	
    }
    
    public List getDepartments() {
		return this.departmentManager.loadAllDepartments();
	}
    
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public DeviceCard getDevice() {
		return device;
	}

	public void setDevice(DeviceCard device) {
		this.device = device;
	}

	public Feedback getFeedback() {
		return feedback;
	}

	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}
}

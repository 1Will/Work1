package com.yongjun.tdms.presentation.webwork.action.workReport.daily.reply;

import java.util.Date;

import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.workReport.daily.Reply;
import com.yongjun.tdms.service.workReport.daily.DailyManager;
import com.yongjun.tdms.service.workReport.daily.ReplyManager;

public class EditReplyAction extends PrepareAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final ReplyManager replyManager;
	private final DailyManager dailyManager;
	private final UserManager userManager;
	private String dailyId;
	private String content;

	public EditReplyAction(ReplyManager replyManager, DailyManager dailyManager, UserManager userManager) {
		this.replyManager = replyManager;
		this.dailyManager = dailyManager;
		this.userManager = userManager;
	}

	public void prepare() throws Exception {
		if (request.getParameter("daily.id") != null) {
			this.dailyId = request.getParameter("daily.id");
		}
		if (request.getParameter("daily.content") != null) {
			this.content = request.getParameter("daily.content");
		}
	}

	public String save() {
		Reply reply = new Reply();
		reply.setReplyDate(new Date());
		reply.setDaily(dailyManager.loadDaily(Long.parseLong(dailyId)));
		reply.setContent(content);
		reply.setUser(userManager.getUser());
		reply.setUserName(userManager.getUser().getName());
		replyManager.storeReply(reply);
		return "success";
	}

	public String getDailyId() {
		return dailyId;
	}

	public void setDailyId(String dailyId) {
		this.dailyId = dailyId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	

}

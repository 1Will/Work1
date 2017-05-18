package com.yongjun.tdms.presentation.webwork.action.workReport.daily.reply;

import java.util.Date;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.workReport.daily.ReplyDaily;
import com.yongjun.tdms.service.workReport.daily.DailyManager;
import com.yongjun.tdms.service.workReport.daily.ReplyDailyManager;

public class EditReplyDailyAction extends PrepareAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final ReplyDailyManager replyDailyManager;
	private final DailyManager dailyManager;
	private final UserManager userManager;
	private String dailyId;
	private String content;

	public EditReplyDailyAction(ReplyDailyManager replyDailyManager, DailyManager dailyManager, UserManager userManager) {
		this.replyDailyManager = replyDailyManager;
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
		ReplyDaily replyDaily = new ReplyDaily();
		replyDaily.setReplyDate(new Date());
		replyDaily.setDaily(dailyManager.loadDaily(Long.parseLong(dailyId)));
		replyDaily.setContent(content);
		replyDaily.setUser(userManager.getUser());
		replyDaily.setUserName(userManager.getUser().getName());
		replyDailyManager.storeReplyDaily(replyDaily);
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

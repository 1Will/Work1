package com.yongjun.tdms.presentation.webwork.action.notice;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.presentation.webwork.FileTransportAction;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.file.FileTransportManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.tdms.model.notice.ReceviceNotice;
import com.yongjun.tdms.model.notice.SendNotice;
import com.yongjun.tdms.model.notice.SendStatus;
import com.yongjun.tdms.service.notice.ReceviceNoticeManager;
import com.yongjun.tdms.service.notice.SendNoticeManager;

public class EditNoticeAction extends FileTransportAction {
	private static final long serialVersionUID = 1L;
	private final SendNoticeManager sendNoticeManager;
	private final FileTransportManager fileTransportManager;
	private final UserManager userManager;
	private final ReceviceNoticeManager receviceNoticeManager;
	private final CodeValueManager codeValueManager;
	private List<User> users;
	private SendNotice sendNotice;
	private String saveOrSendFlag;
	private String cancelFlag;
	private String sendFlag;
	Set<User> userSended = new HashSet();
	private String usersJson;

	public EditNoticeAction(SendNoticeManager sendNoticeManager, FileTransportManager fileTransportManager,
			UserManager userManager, ReceviceNoticeManager receviceNoticeManager, CodeValueManager codeValueManager) {
		this.sendNoticeManager = sendNoticeManager;
		this.fileTransportManager = fileTransportManager;
		this.userManager = userManager;
		this.receviceNoticeManager = receviceNoticeManager;
		this.codeValueManager = codeValueManager;
	}

	public void prepare() throws Exception {
		if (this.sendNotice == null) {
			if (hasId("sendNotice.id")) {
				this.sendNotice = this.sendNoticeManager.loadSendNotice(getId("sendNotice.id"));
				usersJson = getUsersJson();
			} else {
				this.sendNotice = new SendNotice();
			}
		}
		if (!StringUtils.isEmpty(this.request.getParameter("sendFlag")))
			this.sendFlag = "send";
	}

	public String save() throws Exception {
		boolean isNew = this.sendNotice.isNew();
		if (hasKey("cancel")) {
			this.cancelFlag = "cancel";
			Set rNotice = this.sendNotice.getReceviceNotices();
			this.sendNotice.getReceviceNotices().removeAll(rNotice);
			this.receviceNoticeManager.deleteReceviceNotices(rNotice);
			this.sendNotice.setSendStatus(SendStatus.UNSEND);

			User user = getLoginUser();
			this.sendNotice.setSendUser(user);
			this.sendNoticeManager.storeSendNotice(this.sendNotice);
			addActionMessage(getText("noticeSend.cancel.success",
					Arrays.asList(new Object[] { this.sendNotice.getTitle() })));

			return "success";
		}

		if ((null != getFile()) && (getFile().exists())) {
			String location = this.fileTransportManager.upload(this.request, getFile(), "origFileName");
			String orgFileName = this.request.getParameter("origFileName");
			this.sendNotice.setFileName(orgFileName);
			this.sendNotice.setPosition(location);
		}

		if (!StringUtils.isEmpty(this.request.getParameter("loginUser.id"))) {
			this.sendNotice.setNoticeUser(this.userManager.loadUser(getId("loginUser.id")));
		}

		if (hasKey("save")) {
			if (hasId("noticeType.id")) {
				this.sendNotice.setNoticeType(this.codeValueManager.loadCodeValue(getId("noticeType.id")));
			}
			this.sendNotice.setSendStatus(SendStatus.UNSEND);
			this.saveOrSendFlag = "save";
		} else if (hasKey("send")) {
			this.sendNotice.setSendStatus(SendStatus.SENDED);
			this.sendNotice.setNoticeType(this.codeValueManager.loadCodeValue(getId("noticeType.id")));
			this.saveOrSendFlag = "send";
		}

		if (hasIds("availableUserIds")) {
			String availableUserIds = this.request.getParameter("availableUserIds");
			String[] availableUserId = availableUserIds.split(",");
			for (int i = 0; i < availableUserId.length; i++) {
				if ((availableUserId[i].indexOf("_user") == -1) && (availableUserId[i].indexOf("_group") == -1)
						&& (availableUserId[i].indexOf("_dept") == -1) && (availableUserId[i] != null)
						&& (!availableUserId[i].isEmpty())) {
					int tmp436_434 = i;
					String[] tmp436_433 = availableUserId;
					tmp436_433[tmp436_434] = (tmp436_433[tmp436_434] + "_user");
				}
			}
			if ((availableUserId != null) && (!availableUserId[0].isEmpty())) {
				this.sendNoticeManager.joinUsersForSend(availableUserId, this.sendNotice, this.userSended);
			}

		}

		User u = getLoginUser();
		System.out.println(u.getName());
		this.sendNotice.setSendUser(u);
		this.sendNoticeManager.storeSendNotice(this.sendNotice);

		if (hasKey("send")) {
			this.users = this.userManager.loadAllUsers();

			for (User user : this.userSended) {
				ReceviceNotice reNotice = new ReceviceNotice();
				reNotice.setTitle(this.sendNotice.getTitle());
				reNotice.setContent(this.sendNotice.getContent());
				reNotice.setNoticeType(this.codeValueManager.loadCodeValue(getId("noticeType.id")));
				if (this.sendNotice.getFileName() != null) {
					reNotice.setFileName(this.sendNotice.getFileName());
				}
				reNotice.setName(this.sendNotice.getName());
				reNotice.setNoticeUser(this.sendNotice.getNoticeUser());
				reNotice.setPosition(this.sendNotice.getPosition());
				reNotice.setReceviceUser(user);
				reNotice.setValidityDate(this.sendNotice.getValidityDate());
				reNotice.setSendNotice(this.sendNotice);
				this.receviceNoticeManager.storeReceviceNotice(reNotice);
			}

			addActionMessage(getText("noticeSend.send.success",
					Arrays.asList(new Object[] { this.sendNotice.getTitle() })));

			return "success";
		}

		if (isNew) {
			addActionMessage(getText("noticeSend.add.success",
					Arrays.asList(new Object[] { this.sendNotice.getTitle() })));

			return "new";
		}
		addActionMessage(getText("noticeSend.edit.success", Arrays.asList(new Object[] { this.sendNotice.getTitle() })));

		return "success";
	}

	public List<CodeValue> getAllNoticeTypes() {
		List list = null;
		list = this.codeValueManager.loadAllChildByParentCode("090");

		return list;
	}

	public SendNotice getSendNotice() {
		return this.sendNotice;
	}

	public void setSendNotice(SendNotice sendNotice) {
		this.sendNotice = sendNotice;
	}

	public User getLoginUser() {
		return this.userManager.getUser();
	}

	public String getSaveOrSendFlag() {
		return this.saveOrSendFlag;
	}

	public void setSaveOrSendFlag(String saveOrSendFlag) {
		this.saveOrSendFlag = saveOrSendFlag;
	}

	public String getCancelFlag() {
		return this.cancelFlag;
	}

	public void setCancelFlag(String cancelFlag) {
		this.cancelFlag = cancelFlag;
	}

	public String getSendFlag() {
		return this.sendFlag;
	}

	public void setSendFlag(String sendFlag) {
		this.sendFlag = sendFlag;
	}

	public String getUsersJson() {
		StringBuilder json = new StringBuilder();  
        json.append("[");  
        if (sendNotice.getUsers() != null && sendNotice.getUsers().size() > 0) {  
            for (User u : sendNotice.getUsers()) {  
            	json.append("{id:'");  
                json.append(u.getId());  
                json.append("',name:'");  
                json.append(u.getName());  
                json.append("'},");  
            }  
            json.setCharAt(json.length() - 1, ']');
        } else {  
            json.append("]");  
        }  
        return json.toString();  
	}

	public void setUsersJson(String usersJson) {
		this.usersJson = usersJson;
	}
}

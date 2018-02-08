package com.yongjun.tdms.model.notice;
import com.yongjun.pluto.model.security.User;
import com.yongjun.tdms.model.BaseInfoEntity;

public class Notice extends BaseInfoEntity{

	private static final long serialVersionUID = 1L;
	//通知人
    private User noticeUser;
    //标题
    private String title;
    //内容
    private String content;
    //是否接收,默认为未接收
    private Receive receiveStatus;
    //是否发送  默认为未发送
    private SendStatus send;
    //已读 未读 默认为未读
    private Read readStatus;
    
    public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getNoticeUser() {
		return noticeUser;
	}

	public void setNoticeUser(User noticeUser) {
		this.noticeUser = noticeUser;
	}

	public Receive getReceiveStatus() {
		return receiveStatus;
	}

	public void setReceiveStatus(Receive receiveStatus) {
		this.receiveStatus = receiveStatus;
	}

	public SendStatus getSend() {
		return send;
	}

	public void setSend(SendStatus send) {
		this.send = send;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Read getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(Read readStatus) {
		this.readStatus = readStatus;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}

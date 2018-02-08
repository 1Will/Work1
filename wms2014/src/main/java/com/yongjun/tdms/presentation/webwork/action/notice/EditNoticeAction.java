package com.yongjun.tdms.presentation.webwork.action.notice;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;



import com.yongjun.pluto.dao.notice.ReceviceNoticeDao;
import com.yongjun.pluto.model.notice.ReadStatus;
import com.yongjun.pluto.model.notice.ReceviceNotice;
import com.yongjun.pluto.model.notice.SendNotice;
import com.yongjun.pluto.model.notice.SendStatus;
import com.yongjun.pluto.model.security.User;

import com.yongjun.pluto.presentation.webwork.FileTransportAction;
import com.yongjun.pluto.service.file.FileTransportManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.tdms.service.notice.ReceviceNoticeManager;
import com.yongjun.tdms.service.notice.SendNoticeManager;


public class EditNoticeAction extends FileTransportAction{
	private static final long serialVersionUID = 1L;
	private final SendNoticeManager sendNoticeManager;
 	private final FileTransportManager fileTransportManager;
 	private final UserManager userManager;
 	private final ReceviceNoticeManager receviceNoticeManager;
 	private final ReceviceNoticeDao receviceNoticeDao;
 	private List <User> users;
	private SendNotice sendNotice;
	private String saveOrSendFlag;      //发送或保存标识
	private String cancelFlag;          //取消发送的标识
	private String sendFlag;            //标识是否从查询页面传来的发送标识  
	

   public EditNoticeAction(SendNoticeManager sendNoticeManager,
		   FileTransportManager fileTransportManager,UserManager userManager,
		   ReceviceNoticeManager receviceNoticeManager, ReceviceNoticeDao receviceNoticeDao){
	   this.sendNoticeManager=sendNoticeManager;
	   this.fileTransportManager=fileTransportManager;
	   this.userManager=userManager;
	   this.receviceNoticeManager=receviceNoticeManager;
	   this.receviceNoticeDao=receviceNoticeDao;
   }
	public void prepare() throws Exception {
		if(sendNotice==null){
			if (this.hasId("sendNotice.id")){
				sendNotice = sendNoticeManager.loadSendNotice(this.getId("sendNotice.id"));
	
			} else {
				this.sendNotice = new SendNotice();
			}
		}
		if (!StringUtils.isEmpty(request.getParameter("sendFlag"))){
			this.sendFlag="send";
		
		}
	}
	public String save() throws Exception {
		boolean isNew = this.sendNotice.isNew();
		if(this.hasKey("cancel")){
			this.cancelFlag="cancel";
			Set<ReceviceNotice> rNotice= sendNotice.getReceviceNotices();
			 sendNotice.getReceviceNotices().removeAll(rNotice);
			 receviceNoticeManager.deleteReceviceNotices(rNotice);
			 sendNotice.setSendStatus(SendStatus.UNSEND);
			 this.sendNoticeManager.storeSendNotice(sendNotice);
			this.addActionMessage(this.getText("noticeSend.cancel.success", Arrays
						.asList(new Object[] { sendNotice.getTitle() })));
			 return SUCCESS;
		}
		/**
		 * 如果文件不为空，且存在，则上传到服务上
		 */
		if (null != this.getFile()&& this.getFile().exists()) {
			String location = fileTransportManager.upload(request,getFile(),"origFileName");
			String orgFileName = request.getParameter("origFileName");
			sendNotice.setFileName(orgFileName);
			sendNotice.setPosition(location);
			//sendNotice.setName(orgFileName);
		}
		//设置通知人
		if (!StringUtils.isEmpty(request.getParameter("loginUser.id"))) {
			sendNotice.setNoticeUser(this.userManager.loadUser(this
					.getId("loginUser.id")));
			
		}
		//如果点击"保存"按钮，则置通知状态为"未发送"，如果点击"发送"按钮，则置通知状态为"已发送"
		if(this.hasKey("save")){
			sendNotice.setSendStatus(SendStatus.UNSEND);
			this.saveOrSendFlag = "save";
		}else if(this.hasKey("send")){
			sendNotice.setSendStatus(SendStatus.SENDED);
			this.saveOrSendFlag = "send";
		}
		

        //获得需要通知的用户
//		if (this.hasIds("availableUserIds")) {
//    		String availableUserIds = request.getParameter("availableUserIds");
//    		String [] availableUserId = availableUserIds.split(",");
//    		this.sendNoticeManager.joinUsersForSend(availableUserId, sendNotice);
//    	}
   
		
		  this.sendNoticeManager.storeSendNotice(sendNotice);
		//点击发送触发的函数，向发送通知人发送通知
		if (this.hasKey("send")){
//			发送通知给所有用户
			 users=this.userManager.loadAllUsers();
			 //如果是当前用户  则不发送
//			 User currentUser = this.userManager.getUser();
//			 if(currentUser!=null){
//					users.remove(currentUser);
//			 }
			 for(User user:users){   
				ReceviceNotice reNotice = new ReceviceNotice();
				reNotice.setTitle(sendNotice.getTitle());     //通知标题
				reNotice.setContent(sendNotice.getContent());  //通知内容
				if(sendNotice.getFileName()!=null){
				  reNotice.setFileName(sendNotice.getFileName());  //文件名
				}
				reNotice.setName(sendNotice.getName());
				reNotice.setNoticeUser(sendNotice.getNoticeUser());      //发送人
				reNotice.setPosition(sendNotice.getPosition());     //文件存放的路径
				reNotice.setReceviceUser(user);    //接收人
				reNotice.setValidityDate(sendNotice.getValidityDate());   //设置通知有效时间
				reNotice.setSendNotice(sendNotice);                      //设置发送通知
				receviceNoticeManager.storeReceviceNotice(reNotice);
				
			}
			
			
			this.addActionMessage(this.getText("noticeSend.send.success", Arrays
					.asList(new Object[] { sendNotice.getTitle() })));
			return SUCCESS;
		}
		
		if (isNew) {
			this.addActionMessage(this.getText("noticeSend.add.success", Arrays
					.asList(new Object[] { sendNotice.getTitle() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText("noticeSend.edit.success", Arrays
					.asList(new Object[] { sendNotice.getTitle() })));
			return SUCCESS;
		}
	}
	
//	//通过通知的标题和内容分别获得发通知的一个对象和收通知的一个集合
//	public String getTitleAndContent(){
//		System.out.println("==========================="+sendNotice.getReceviceNotices().size());
//		receviceNoticeManager.deleteReceviceNotices(sendNotice.getReceviceNotices());
//		this.sendNoticeManager.storeSendNotice(sendNotice);
//		return SUCCESS;
//	}
	public SendNotice getSendNotice() {
		return sendNotice;
	}
	public void setSendNotice(SendNotice sendNotice) {
		this.sendNotice = sendNotice;
	}
	public User getLoginUser() {
		return this.userManager.getUser();
	}
	public String getSaveOrSendFlag() {
		return saveOrSendFlag;
	}
	public void setSaveOrSendFlag(String saveOrSendFlag) {
		this.saveOrSendFlag = saveOrSendFlag;
	}
	public String getCancelFlag() {
		return cancelFlag;
	}
	public void setCancelFlag(String cancelFlag) {
		this.cancelFlag = cancelFlag;
	}
	public String getSendFlag() {
		return sendFlag;
	}
	public void setSendFlag(String sendFlag) {
		this.sendFlag = sendFlag;
	}
}

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
package com.yongjun.tdms.presentation.webwork.action.notice;

import com.yongjun.pluto.model.notice.ReadStatus;
import com.yongjun.pluto.model.notice.ReceviceNotice;
import com.yongjun.pluto.service.file.FileTransportManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.service.notice.ReceviceNoticeManager;

/**
 * <p>Title: EditReceviceNoticeAction
 * <p>Description: 接收通知维护页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public class EditReceviceNoticeAction extends PrepareAction {

	private static final long serialVersionUID = -8342978203395583399L;
	
	private final ReceviceNoticeManager receviceNoticeManager;
	private final FileTransportManager fileTransportManager;
	
	private ReceviceNotice receviceNotice;
	
	public EditReceviceNoticeAction(ReceviceNoticeManager receviceNoticeManager,
			FileTransportManager fileTransportManager) {
		this.receviceNoticeManager = receviceNoticeManager;
		this.fileTransportManager = fileTransportManager;
	}
	
	public void prepare() throws Exception {
		if (this.hasId("receviceNotice.id")) {
			this.receviceNotice = this.receviceNoticeManager.loadReceviceNotice(this.getId("receviceNotice.id"));
			//置通知的状态为已读
			receviceNotice.setReadStatus(ReadStatus.READED);
			this.receviceNoticeManager.storeReceviceNotice(receviceNotice);
		}
		if (this.hasId("downloadDoc.id")) {
			this.receviceNotice = this.receviceNoticeManager.loadReceviceNotice(this.getId("downloadDoc.id"));
		}
	}
	public String download(){
		fileTransportManager.download(request, response, receviceNotice.getFileName(), receviceNotice.getPosition());
		return null;
	}
	
	public ReceviceNotice getReceviceNotice() {
		return receviceNotice;
	}

	public void setReceviceNotice(ReceviceNotice receviceNotice) {
		this.receviceNotice = receviceNotice;
	}

}

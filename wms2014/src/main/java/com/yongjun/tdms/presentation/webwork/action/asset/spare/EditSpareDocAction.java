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
package com.yongjun.tdms.presentation.webwork.action.asset.spare;

import java.util.Arrays;

import com.yongjun.pluto.presentation.webwork.FileTransportAction;
import com.yongjun.pluto.service.file.FileTransportManager;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.model.base.document.ApplicationDoc;
import com.yongjun.tdms.service.asset.spare.SpareManager;
import com.yongjun.tdms.service.base.document.ApplicationDocManager;

/**
 * <p>Title: EditSpareDocAction
 * <p>Description: 备件资料访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: EditSpareDocAction.java 10070 2008-01-03 07:22:57Z wzou $
 */
public class EditSpareDocAction extends FileTransportAction {
	private static final long serialVersionUID = -2706192037102232098L;
	private final ApplicationDocManager applicationDocManager;
	private final SpareManager spareManager;
	private final FileTransportManager fileTransportManager;
	private ApplicationDoc spareDoc;
	private Spare spare;
	
	public EditSpareDocAction(ApplicationDocManager applicationDocManager,
			SpareManager spareManager,
			FileTransportManager fileTransportManager) {
		this.applicationDocManager = applicationDocManager;
		this.spareManager = spareManager;
		this.fileTransportManager = fileTransportManager;
	}
	
	/**
	 * 获取参数<b>spare.id</b>，如果存在就获取这个备件资料实体，
	 * 如果不存在，就新建一个备件实体
	 */
	public void prepare() throws Exception {
		if (this.hasId("spare.id")) {
			this.spare = this.spareManager.loasSpare(this.getId("spare.id"));
		}
		if (null == this.spareDoc) {
			if (this.hasId("doc.id")) {
				this.spareDoc = this.applicationDocManager.loadApplicationDoc(this.getId("doc.id"));
			} else {
				spareDoc = new ApplicationDoc();
			}
		}
	}
	
	public String execute() {
		if (this.isDelete()) {
			delete();
		} 
		return SUCCESS;
	}
	
	/**
	 * 保存一个备件资料
	 * @return String SUCCESS 或者 NEW
	 */
	public String save() throws Exception {
		
		boolean isNew = this.spareDoc.isNew();
		
		String location = fileTransportManager.upload(request, getFile(), "origFileName");
		
		String orgFileName = request.getParameter("origFileName");
		spareDoc.setFileName(orgFileName);
		spareDoc.setPosition(location);
		spareDoc.setSpare(spare);
		
		this.applicationDocManager.storeApplicationDoc(spareDoc);
		
		if (isNew) {
			this.addActionMessage(this.getText("spareDoc.add.success", Arrays
					.asList(new Object[] { spareDoc.getName() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText("spareDoc.edit.success", Arrays
					.asList(new Object[] { spareDoc.getName() })));
			return SUCCESS;
		}
	}
	
	/**
	 * 修改一个备件资料
	 * @return String SUCCESS 或者 NEW
	 */
	public String modify() {
		ApplicationDoc doc = new ApplicationDoc();
		
		if (this.hasId("spareDoc.id")) {
			doc = this.applicationDocManager.loadApplicationDoc(this.getId("spareDoc.id"));
		}
		doc.setName(spareDoc.getName());
		doc.setDescription(spareDoc.getDescription());
		this.applicationDocManager.storeApplicationDoc(doc);
		this.addActionMessage(this.getText("spareDoc.edit.success", Arrays
				.asList(new Object[] { spareDoc.getName() })));
		
		return SUCCESS;
	}
	
	//TODO: 删除文件
	public void delete() {
		this.applicationDocManager.deleteApplicationDoc(this.spareDoc);
	}
	
	public ApplicationDoc getSpareDoc() {
		return spareDoc;
	}

	public void setSpareDoc(ApplicationDoc spareDoc) {
		this.spareDoc = spareDoc;
	}

	public Spare getSpare() {
		return spare;
	}

	public void setSpare(Spare spare) {
		this.spare = spare;
	}
	
}

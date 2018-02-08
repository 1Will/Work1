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
package com.yongjun.tdms.presentation.webwork.action.base.helpManual;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.presentation.webwork.FileTransportAction;
import com.yongjun.pluto.service.file.FileTransportManager;
import com.yongjun.tdms.model.base.document.ApplicationDoc;
import com.yongjun.tdms.model.base.document.ApplicationDocType;
import com.yongjun.tdms.service.base.document.ApplicationDocManager;

/**
 * <p>Title: EditManualAction
 * <p>Description: 手册维护页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public class EditManualAction extends FileTransportAction {

	private static final long serialVersionUID = -877987215782827292L;

	private final ApplicationDocManager applicationDocManager;
	private final FileTransportManager fileTransportManager;
	//手册
	private ApplicationDoc manual;

	public EditManualAction(ApplicationDocManager applicationDocManager,
			FileTransportManager fileTransportManager) {
		this.applicationDocManager = applicationDocManager;
		this.fileTransportManager = fileTransportManager;
	}
	
	/**
	 * 为执行save()方法准备数据
	 */
	public void prepare() throws Exception {
		if (null == this.manual) {
			if (this.hasId("manual.id")) {      //如果请求中包含manual.id,则根据manual.id获取手册对象
				this.manual = this.applicationDocManager
						.loadApplicationDoc(this.getId("manual.id"));
			} else {                           //如果请求中不包含manual.id,则创建手册对象
				this.manual = new ApplicationDoc();
			}
		}

	}
	/**
	 * 上传文件,或修改文件信息
	 * @return NEW | SUCCESS
	 * @throws Exception
	 */
	public String save() throws Exception {
		boolean isNew = this.manual.isNew();
		
		if (isNew || this.getFile() != null) {
			if (!isNew) {        //如果不是新建,则删除原来服务器上的文件
				this.fileTransportManager.delete(request, manual.getPosition());
			}
			String location = fileTransportManager.upload(request, getFile(),
			"origFileName");
			manual.setPosition(location);                 //设置文件在服务器上的GUUID
			String orgFileName = request.getParameter("origFileName");
			manual.setFileName(orgFileName);              //设置原文件名
			manual.setFileNo(location);                  //设置默认的技术文件编号
		}

		manual.setDocFlag(ApplicationDocType.HELP_MANUAL_DOC);    //设置该上传文档为手册类型标识

		this.applicationDocManager.storeApplicationDoc(manual);

		if (isNew) {
			if (!this.isMostNumberForTheManualDoc()) {
				this.addActionMessage(this.getText("manual.add.success", Arrays
						.asList(new Object[] { manual.getName() })));
			}  else {                   //文件个数已经为最大数
				this.addActionMessage(this.getText("manual.most.number", Arrays
						.asList(new Object[] { manual.getName(),this.applicationDocManager.getMostUploadNumberForManualDoc() })));
			}
			return NEW;
		} else {
			this.addActionMessage(this.getText("manual.edit.success", Arrays
					.asList(new Object[] { manual.getName() })));
			return SUCCESS;
		}
	}
	
	public ApplicationDoc getManual() {
		return manual;
	}
	
	public void setManual(ApplicationDoc manual) {
		this.manual = manual;
	}

	/**
	 * 获取手册类型文件的个数
	 * @return
	 */
	public Integer getNumberOfManualDoc() {
		return this.applicationDocManager.getNumberOfManualDoc();
	}
	
	/**
	 * 判断文件个数是否为最大数，true 表示已经最大
	 * @return
	 */
	public boolean isMostNumberForTheManualDoc() {
		return this.applicationDocManager.isMostNumberForTheManualDoc();
	}
}

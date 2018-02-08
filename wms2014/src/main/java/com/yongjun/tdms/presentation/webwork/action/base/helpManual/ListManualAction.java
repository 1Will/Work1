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

import java.util.Iterator;
import java.util.List;

import com.yongjun.pluto.service.file.FileTransportManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.base.document.ApplicationDoc;
import com.yongjun.tdms.service.base.document.ApplicationDocManager;

/**
 * <p>Title: ListManualAction
 * <p>Description: 手册的查询页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public class ListManualAction extends ValueListAction {
	private static final long serialVersionUID = 1028590432151252029L;
	
	private final ApplicationDocManager applicationDocManager;
	private final FileTransportManager fileTransportManager;
	
	//手册对象
	private ApplicationDoc manual;
	//手册集合
	private List<ApplicationDoc> helpManuals;
	
	public ListManualAction(ApplicationDocManager applicationDocManager,
			FileTransportManager fileTransportManager) {
		this.applicationDocManager = applicationDocManager;
		this.fileTransportManager = fileTransportManager;
	}
	
	/**
	 * 为执行excute()执行准备数据
	 */
	public void prepare() {
		if (this.hasId("manual.id")) {              //如果请求中包含manual.id,则根据manual.id获取手册对象
			this.manual = this.applicationDocManager.loadApplicationDoc(this.getId("manual.id"));
			this.download();
		}
		if (this.hasIds("helpManualIds")) {             //如果请求中包含helpManualIds,则根据helpManualIds获取手册集合对象
			this.helpManuals = this.applicationDocManager.loadAllApplicationDocs(this.getIds("helpManualIds"));
		}
	}
	
	public String execute() {
		if (this.isDelete()) {
			delete();
		}
		return SUCCESS;
	}
	/**
	 * 删除手册文件，以及数据库中关联的记录
	 *
	 */
	public void delete() {
		for (Iterator iter = helpManuals.iterator(); iter.hasNext();) {
			ApplicationDoc doc = (ApplicationDoc)iter.next();
            //删除服务器目录上的文件
			this.fileTransportManager.delete(request, doc.getPosition());
			//删除数据库中的文件关联的记录
			this.applicationDocManager.deleteApplicationDoc(doc);
		}
		this.addActionMessage(this.getText("manual.delete.success"));
	}
	
	/**
	 *　下载手册
	 * @return
	 */
	public String download() {
		this.fileTransportManager.download(request, response, manual.getFileName(), manual.getPosition());
		return null;
	}
	
	/**
	 * 得到 <i>valueList</i>中配置的，查询hql的ID
	 */
	@Override
	protected String getAdapterName() {
		return "helpManuals";
	}

	public ApplicationDoc getManual() {
		return manual;
	}

	public void setManual(ApplicationDoc manual) {
		this.manual = manual;
	}
	
	/**
	 * 判断文件个数是否为最大数，true 表示已经最大
	 * @return
	 */
	public boolean isMostNumberForTheManualDoc() {
		return this.applicationDocManager.isMostNumberForTheManualDoc();
	}
}

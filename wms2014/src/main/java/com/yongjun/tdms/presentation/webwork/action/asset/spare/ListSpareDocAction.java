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

import java.util.Iterator;
import java.util.List;

import com.yongjun.pluto.presentation.webwork.FileTransportAction;
import com.yongjun.pluto.service.file.FileTransportManager;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.model.base.document.ApplicationDoc;
import com.yongjun.tdms.service.asset.spare.SpareManager;
import com.yongjun.tdms.service.base.document.ApplicationDocManager;

/**
 * <p>Title: ListSpareDocAction
 * <p>Description: 备件资料访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: ListSpareDocAction.java 9887 2007-12-28 03:11:36Z qsun $
 */
public class ListSpareDocAction extends FileTransportAction {
	private static final long serialVersionUID = -5005536686720555656L;
	private final SpareManager spareManager;
	private final ApplicationDocManager applicationDocManager;
	private final FileTransportManager fileTransportManager;
	private Spare spare;
	private List<ApplicationDoc> spareDocs;
	private ApplicationDoc doc;
	
	public ListSpareDocAction(SpareManager spareManager,
			ApplicationDocManager applicationDocManager,
			FileTransportManager fileTransportManager) {
		this.spareManager = spareManager;
		this.applicationDocManager = applicationDocManager;
		this.fileTransportManager = fileTransportManager;
	}
	
	/**
	 * 获取页面参数 <b>spareDocIds</b> ,如果得到，根据ID获取设备技术单
	 */
	public void prepare() throws Exception {
		if (this.hasId("spare.id")) {
			this.spare = this.spareManager.loasSpare(this.getId("spare.id"));
		}
		if (this.hasId("spareDocIds")) {
			this.spareDocs = this.applicationDocManager.loadAllApplicationDocs(this.getIds("spareDocIds"));
		}
		if (this.hasId("doc.id")) {
			doc = this.applicationDocManager.loadApplicationDoc(this.getId("doc.id"));
			this.download();
		}
		
		if (isDelete()) {
			this.delete();
		}
	}
	
	/**
	 * 页面执行，如果选择了删除就调用 <b>delete</b> 函数处理
	 * 
	 * @return	String SUCCESS
	 */
    private void delete() {
    	for (Iterator iter = spareDocs.iterator(); iter.hasNext(); ) {
    		ApplicationDoc doc = (ApplicationDoc)iter.next(); 
    		this.fileTransportManager.delete(request, doc.getPosition());
    	}
    	this.spare.getSpareDoc().removeAll(this.spareDocs);
    	this.spareManager.storeSpare(spare);    	
        this.addActionMessage(this.getText("spareDoc.delete.success"));
    }
    
	public String download(){
		fileTransportManager.download(request, response, doc.getFileName(), doc.getPosition());
		return null;
	}

	public Spare getSpare() {
		return spare;
	}

	public void setSpare(Spare spare) {
		this.spare = spare;
	}
	
	
}

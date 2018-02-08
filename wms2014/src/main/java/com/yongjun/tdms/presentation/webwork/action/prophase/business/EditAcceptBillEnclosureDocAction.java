package com.yongjun.tdms.presentation.webwork.action.prophase.business;

import java.util.Arrays;
import java.util.List;

import com.yongjun.pluto.presentation.webwork.FileTransportAction;
import com.yongjun.pluto.service.file.FileTransportManager;
import com.yongjun.tdms.model.base.document.ApplicationDoc;
import com.yongjun.tdms.model.prophase.business.AcceptBill;

import com.yongjun.tdms.service.base.document.ApplicationDocManager;
import com.yongjun.tdms.service.prophase.business.AcceptBillManager;


public class EditAcceptBillEnclosureDocAction extends FileTransportAction{
	private static final long serialVersionUID = 1L;

    private final ApplicationDocManager applicationDocManager;
	
	private final FileTransportManager fileTransportManager;
	
	private final AcceptBillManager acceptBillManager;
	
    private ApplicationDoc acceptBillEnclosureDoc;
	
	private AcceptBill acceptBill ;
	
	private List<ApplicationDoc> acceptBillEnclosureDocs;
	
	public EditAcceptBillEnclosureDocAction( ApplicationDocManager applicationDocManager,FileTransportManager fileTransportManager,
			AcceptBillManager acceptBillManager){
		this.applicationDocManager=applicationDocManager;
		this.fileTransportManager=fileTransportManager;
		this.acceptBillManager=acceptBillManager;
	}
	public void prepare() throws Exception {
		if (this.hasId("acceptBill.id")) {
			this.acceptBill = this.acceptBillManager.loadAcceptBill(this.getId("acceptBill.id"));
		}
		
		if (null == this.acceptBillEnclosureDoc) {
			if (this.hasId("acceptBillEnclosureDoc.id")) {
				this.acceptBillEnclosureDoc = this.applicationDocManager.loadApplicationDoc(this.getId("acceptBillEnclosureDoc.id"));
			} else {
				this.acceptBillEnclosureDoc = new ApplicationDoc();
			}
		}
		
	}
	public String save() throws Exception {
		boolean isNew = this.acceptBillEnclosureDoc.isNew();
		
		String location = fileTransportManager.upload(request, getFile(), "origFileName");
		
		String orgFileName = request.getParameter("origFileName");
		acceptBillEnclosureDoc.setFileName(orgFileName);
		acceptBillEnclosureDoc.setPosition(location);
		acceptBillEnclosureDoc.setAcceptBill(acceptBill);
		acceptBillEnclosureDoc.setFileNo(location); 
		
		
		this.applicationDocManager.storeApplicationDoc(acceptBillEnclosureDoc);
		
		if (isNew) {
			this.addActionMessage(this.getText("toolingChangeDoc.add.success", Arrays
					.asList(new Object[] { acceptBillEnclosureDoc.getName() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText("toolingChangeDoc.edit.success", Arrays
					.asList(new Object[] { acceptBillEnclosureDoc.getName() })));
			return SUCCESS;
		}
	}
	public String modify() {
		ApplicationDoc doc = new ApplicationDoc();
		
		if (this.hasId("purchaseEnclosureDoc.id")) {
			doc = this.applicationDocManager.loadApplicationDoc(this.getId("purchaseEnclosureDoc.id"));
		}
		doc.setName(acceptBillEnclosureDoc.getName());
		doc.setDescription(acceptBillEnclosureDoc.getDescription());
		doc.setFileNo(acceptBillEnclosureDoc.getFileNo());
		this.applicationDocManager.storeApplicationDoc(doc);
		this.addActionMessage(this.getText("toolingChangeDoc.edit.success", Arrays
				.asList(new Object[] { acceptBillEnclosureDoc.getName() })));
		
		return SUCCESS;
	}
	public List<ApplicationDoc> getAcceptBillEnclosureDocs() {
		return acceptBillEnclosureDocs;
	}
	public void setAcceptBillEnclosureDocs(
			List<ApplicationDoc> acceptBillEnclosureDocs) {
		this.acceptBillEnclosureDocs = acceptBillEnclosureDocs;
	}
	public AcceptBill getAcceptBill() {
		return acceptBill;
	}
	public void setAcceptBill(AcceptBill acceptBill) {
		this.acceptBill = acceptBill;
	}
	public ApplicationDoc getAcceptBillEnclosureDoc() {
		return acceptBillEnclosureDoc;
	}
	public void setAcceptBillEnclosureDoc(ApplicationDoc acceptBillEnclosureDoc) {
		this.acceptBillEnclosureDoc = acceptBillEnclosureDoc;
	}
	/**
	 * 用来判断是更新还是上传新的文件
	 * @return
	 */
	public String getFirst() {
		return request.getParameter("first");
	}

}

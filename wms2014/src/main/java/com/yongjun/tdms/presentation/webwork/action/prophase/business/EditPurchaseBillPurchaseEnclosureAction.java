package com.yongjun.tdms.presentation.webwork.action.prophase.business;

import java.util.Arrays;
import java.util.List;

import com.yongjun.pluto.presentation.webwork.FileTransportAction;
import com.yongjun.pluto.service.file.FileTransportManager;
import com.yongjun.tdms.model.base.document.ApplicationDoc;
import com.yongjun.tdms.model.prophase.business.PurchaseBill;
import com.yongjun.tdms.service.base.document.ApplicationDocManager;
import com.yongjun.tdms.service.prophase.business.PurchaseBillManager;

public class EditPurchaseBillPurchaseEnclosureAction extends FileTransportAction{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private final ApplicationDocManager applicationDocManager;
	private final FileTransportManager fileTransportManager;
	private final PurchaseBillManager purchaseBillManager;
	
    private ApplicationDoc purchaseEnclosureDoc;
	private PurchaseBill purchaseBill ;
	private List<ApplicationDoc> purchaseEnclosureDocs;
	
	public EditPurchaseBillPurchaseEnclosureAction( ApplicationDocManager applicationDocManager,FileTransportManager fileTransportManager,
			PurchaseBillManager purchaseBillManager){
		this.applicationDocManager=applicationDocManager;
		this.fileTransportManager=fileTransportManager;
		this.purchaseBillManager=purchaseBillManager;
	}
	public void prepare() throws Exception {
		if (this.hasId("purchaseBill.id")) {
			this.purchaseBill = this.purchaseBillManager.loadPurchaseBill(this.getId("purchaseBill.id"));
		}
		
		if (null == this.purchaseEnclosureDoc) {
			if (this.hasId("purchaseEnclosureDoc.id")) {
				this.purchaseEnclosureDoc = this.applicationDocManager.loadApplicationDoc(this.getId("purchaseEnclosureDoc.id"));
			} else {
				this.purchaseEnclosureDoc = new ApplicationDoc();
			}
		}
		
	}
	public String save() throws Exception {
		boolean isNew = this.purchaseEnclosureDoc.isNew();
		
		String location = fileTransportManager.upload(request, getFile(), "origFileName");
		
		String orgFileName = request.getParameter("origFileName");
		purchaseEnclosureDoc.setFileName(orgFileName);
		purchaseEnclosureDoc.setPosition(location);
		purchaseEnclosureDoc.setPurchaseBill(purchaseBill);
		purchaseEnclosureDoc.setFileNo(location);
		
		this.applicationDocManager.storeApplicationDoc(purchaseEnclosureDoc);
		
		if (isNew) {
			this.addActionMessage(this.getText("toolingChangeDoc.add.success", Arrays
					.asList(new Object[] { purchaseEnclosureDoc.getName() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText("toolingChangeDoc.edit.success", Arrays
					.asList(new Object[] { purchaseEnclosureDoc.getName() })));
			return SUCCESS;
		}
	}
	public String modify() {
		ApplicationDoc doc = new ApplicationDoc();
		
		if (this.hasId("purchaseEnclosureDoc.id")) {
			doc = this.applicationDocManager.loadApplicationDoc(this.getId("purchaseEnclosureDoc.id"));
		}
		doc.setName(purchaseEnclosureDoc.getName());
		doc.setDescription(purchaseEnclosureDoc.getDescription());
		doc.setFileNo(purchaseEnclosureDoc.getFileNo());
		
		this.applicationDocManager.storeApplicationDoc(doc);
		this.addActionMessage(this.getText("toolingChangeDoc.edit.success", Arrays
				.asList(new Object[] { purchaseEnclosureDoc.getName() })));
		
		return SUCCESS;
	}

	public PurchaseBill getPurchaseBill() {
		return purchaseBill;
	}


	public void setPurchaseBill(PurchaseBill purchaseBill) {
		this.purchaseBill = purchaseBill;
	}


	public List<ApplicationDoc> getPurchaseEnclosureDocs() {
		return purchaseEnclosureDocs;
	}


	public void setPurchaseEnclosureDocs(List<ApplicationDoc> purchaseEnclosureDocs) {
		this.purchaseEnclosureDocs = purchaseEnclosureDocs;
	}


	public ApplicationDoc getPurchaseEnclosureDoc() {
		return purchaseEnclosureDoc;
	}


	public void setPurchaseEnclosureDoc(ApplicationDoc purchaseEnclosureDoc) {
		this.purchaseEnclosureDoc = purchaseEnclosureDoc;
	}
	
	/**
	 * 用来判断是更新还是上传新的文件
	 * @return
	 */
	public String getFirst() {
		return request.getParameter("first");
	}
}

package com.yongjun.tdms.presentation.webwork.action.prophase.business;
        

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yongjun.pluto.service.file.FileTransportManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.base.document.ApplicationDoc;
import com.yongjun.tdms.model.prophase.business.PurchaseBill;
import com.yongjun.tdms.service.base.document.ApplicationDocManager;
import com.yongjun.tdms.service.prophase.business.PurchaseBillManager;

public class ListPurchaseBillDocPurchaseEnclosureAction extends ValueListAction {

	private static final long serialVersionUID = -1104105155991390145L;
	private final ApplicationDocManager applicationDocManager;
	
	private final FileTransportManager fileTransportManager;
	
	private final PurchaseBillManager purchaseBillManager;
	
	private ApplicationDoc purchaseEnclosureDoc;
	
	private PurchaseBill purchaseBill ;
	
	private List<ApplicationDoc> purchaseEnclosureDocs;
	
	private final Log log = LogFactory.getLog(getClass());
	public  ListPurchaseBillDocPurchaseEnclosureAction(ApplicationDocManager applicationDocManager,FileTransportManager fileTransportManager
			,PurchaseBillManager purchaseBillManager){
		this.applicationDocManager=applicationDocManager;
		this.fileTransportManager=fileTransportManager;
		this.purchaseBillManager=purchaseBillManager;
	}
	public void prepare() throws Exception {
	
			if (this.hasId("purchaseBill.id")) {
				this.purchaseBill = this.purchaseBillManager
						.loadPurchaseBill(this.getId("purchaseBill.id"));
				log.debug("device.doc size is "
						+ purchaseBill.getChangeDoc().size());
			}
			if (this.hasId("purchaseEnclosureIds")) {
				this.purchaseEnclosureDocs = this.applicationDocManager
						.loadAllApplicationDocs(this.getIds("purchaseEnclosureIds"));
			}
			if (this.hasId("doc.id")) {
				purchaseEnclosureDoc = this.applicationDocManager
						.loadApplicationDoc(this.getId("doc.id"));
				this.download();
			}

       this.setFirst(false);
	}
	public String execute() {
		//如果点击删除按钮，则执行删除操作
		if (isDelete()) {
			this.delete();
		}
		return SUCCESS;
	}
	private void delete() {
		for (Iterator iter = purchaseEnclosureDocs.iterator(); iter.hasNext();) {
			ApplicationDoc doc = (ApplicationDoc) iter.next();
			this.fileTransportManager.delete(request, doc.getPosition());
		}
		this.purchaseBill.getChangeDoc().removeAll(purchaseEnclosureDocs);
		this.purchaseBillManager.storePurchaseBill(purchaseBill);
		this.addActionMessage(this.getText("toolingChangeDoc.delete.success"));

	}
	public String download() {
		fileTransportManager.download(request, response, purchaseEnclosureDoc
				.getFileName(), purchaseEnclosureDoc.getPosition());
		return null;
	}
	public ApplicationDoc getPurchaseEnclosureDoc() {
		return purchaseEnclosureDoc;
	}
	public void setPurchaseEnclosureDoc(ApplicationDoc purchaseEnclosureDoc) {
		this.purchaseEnclosureDoc = purchaseEnclosureDoc;
	}
	public PurchaseBill getPurchaseBill() {
		return purchaseBill;
	}
	public void setPurchaseBill(PurchaseBill purchaseBill) {
		this.purchaseBill = purchaseBill;
	}
	@Override
	protected String getAdapterName() {
		
		return "purchaseBillDoc";
	}
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		if(this.hasId("purchaseBill.id")) {
			map.put("purchaseBill.id",this.getId("purchaseBill.id"));
		}
		return map;
	}
	
	
}

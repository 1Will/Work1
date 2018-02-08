package com.yongjun.tdms.presentation.webwork.action.runmaintenance.usualcheck;

import java.util.Arrays;
import java.util.List;

import com.yongjun.pluto.presentation.webwork.FileTransportAction;
import com.yongjun.pluto.service.file.FileTransportManager;
import com.yongjun.tdms.model.base.document.ApplicationDoc;
import com.yongjun.tdms.model.runmaintenance.usualcheck.Check;
import com.yongjun.tdms.service.base.document.ApplicationDocManager;
import com.yongjun.tdms.service.runmaintenance.usualcheck.CheckManager;
public class EditUsualCheckTechnicalMaterialAction extends FileTransportAction{

    private static final long serialVersionUID = 1L;
    private final ApplicationDocManager applicationDocManager;
	private final FileTransportManager fileTransportManager;
	private final CheckManager checkManager;
    private ApplicationDoc checkTechnicalMaterialDoc;
	
	private Check check ;
	private List<ApplicationDoc> checkTechnicalMaterialDocs;
	public EditUsualCheckTechnicalMaterialAction( ApplicationDocManager applicationDocManager,FileTransportManager fileTransportManager,
			CheckManager checkManager){
		this.applicationDocManager=applicationDocManager;
		this.fileTransportManager=fileTransportManager;
		this.checkManager=checkManager;
	}
	public void prepare() throws Exception {
		if (this.hasId("check.id")) {
			this.check = this.checkManager.loadCheck(this.getId("check.id"));
		}
		if (null == this.checkTechnicalMaterialDoc) {
			if (this.hasId("checkTechnicalMaterialDoc.id")) {
				this.checkTechnicalMaterialDoc = this.applicationDocManager.loadApplicationDoc(this.getId("checkTechnicalMaterialDoc.id"));
			} else {
				this.checkTechnicalMaterialDoc = new ApplicationDoc();
			}
		}
	}
	public String save() throws Exception {
		boolean isNew = this.checkTechnicalMaterialDoc.isNew();
		String location = fileTransportManager.upload(request, getFile(), "origFileName");  
		String orgFileName = request.getParameter("origFileName");
		checkTechnicalMaterialDoc.setFileName(orgFileName);
		checkTechnicalMaterialDoc.setPosition(location);
		checkTechnicalMaterialDoc.setCheck(check);
		checkTechnicalMaterialDoc.setFileNo(location);
		this.applicationDocManager.storeApplicationDoc(checkTechnicalMaterialDoc);
		if (isNew) {
			this.addActionMessage(this.getText("usaualCheckDoc.add.success", Arrays
					.asList(new Object[] { checkTechnicalMaterialDoc.getName() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText("usaualCheckDoc.edit.success", Arrays
					.asList(new Object[] { checkTechnicalMaterialDoc.getName() })));
			return SUCCESS;
		}
	}
	public String modify() {
		ApplicationDoc doc = new ApplicationDoc();
		
		if (this.hasId("checkTechnicalMaterialDoc.id")) {
			doc = this.applicationDocManager.loadApplicationDoc(this.getId("checkTechnicalMaterialDoc.id"));
		}
		doc.setName(checkTechnicalMaterialDoc.getName());
		doc.setDescription(checkTechnicalMaterialDoc.getDescription());
		doc.setFileNo(checkTechnicalMaterialDoc.getFileNo());
		
		this.applicationDocManager.storeApplicationDoc(doc);
		this.addActionMessage(this.getText("toolingChangeDoc.edit.success", Arrays
				.asList(new Object[] { checkTechnicalMaterialDoc.getName() })));
		
		return SUCCESS;
	}

	/**
	 * 用来判断是更新还是上传新的文件
	 * @return
	 */
	public String getFirst() {
		return request.getParameter("first");
	}
	public ApplicationDoc getCheckTechnicalMaterialDoc() {
		return checkTechnicalMaterialDoc;
	}
	public void setCheckTechnicalMaterialDoc(ApplicationDoc checkTechnicalMaterialDoc) {
		this.checkTechnicalMaterialDoc = checkTechnicalMaterialDoc;
	}
	public List<ApplicationDoc> getCheckTechnicalMaterialDocs() {
		return checkTechnicalMaterialDocs;
	}
	public void setCheckTechnicalMaterialDocs(List<ApplicationDoc> checkTechnicalMaterialDocs) {
		this.checkTechnicalMaterialDocs = checkTechnicalMaterialDocs;
	}
	public Check getCheck() {
		return check;
	}
	public void setCheck(Check check) {
		this.check = check;
	}

}

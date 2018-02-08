package com.yongjun.tdms.presentation.webwork.action.runmaintenance.usualcheck;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yongjun.pluto.service.file.FileTransportManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.base.document.ApplicationDoc;
import com.yongjun.tdms.model.runmaintenance.usualcheck.Check;
import com.yongjun.tdms.service.base.document.ApplicationDocManager;
import com.yongjun.tdms.service.runmaintenance.usualcheck.CheckManager;
public class ListUsualCheckTechnicalMaterialAction extends ValueListAction{

	private static final long serialVersionUID = 1L;
	private final Log log = LogFactory.getLog(getClass());
	private final ApplicationDocManager applicationDocManager;
	private final FileTransportManager fileTransportManager;
	private final CheckManager checkManager;
	private ApplicationDoc checkTechnicalMaterialDoc;
	
	private Check check ;
	private List<ApplicationDoc> checkTechnicalMaterialDocs;
	public  ListUsualCheckTechnicalMaterialAction(ApplicationDocManager applicationDocManager,FileTransportManager fileTransportManager
			,CheckManager checkManager){
		this.applicationDocManager=applicationDocManager;
		this.fileTransportManager=fileTransportManager;
		this.checkManager=checkManager;
	}
	public void prepare() throws Exception {
	
			if (this.hasId("check.id")) {
				this.check = this.checkManager
						.loadCheck(this.getId("check.id"));
				log.debug("device.doc size is "
						+ check.getChangeDoc().size());
			}
			if (this.hasId("technicalMaterialIds")) {
				this.checkTechnicalMaterialDocs = this.applicationDocManager
						.loadAllApplicationDocs(this.getIds("technicalMaterialIds"));
			}
			if (this.hasId("doc.id")) {
				checkTechnicalMaterialDoc = this.applicationDocManager
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
		for (Iterator iter = checkTechnicalMaterialDocs.iterator(); iter.hasNext();) {
			ApplicationDoc doc = (ApplicationDoc) iter.next();
			this.fileTransportManager.delete(request, doc.getPosition());
		}
		this.check.getChangeDoc().removeAll(checkTechnicalMaterialDocs);
		this.checkManager.storeCheck(check);
		this.addActionMessage(this.getText("toolingChangeDoc.delete.success"));

	}
	public String download() {
		fileTransportManager.download(request, response, checkTechnicalMaterialDoc
				.getFileName(), checkTechnicalMaterialDoc.getPosition());
		return null;
	}

	@Override
	protected String getAdapterName() {
		
		return "usualCheckDoc";
	}
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		if(this.hasId("check.id")) {
			map.put("check.id",this.getId("check.id"));
		}
		return map;
	}
	public Check getCheck() {
		return check;
	}
	public void setCheck(Check check) {
		this.check = check;
	}
	public ApplicationDoc getCheckTechnicalMaterialDoc() {
		return checkTechnicalMaterialDoc;
	}
	public void setCheckTechnicalMaterialDoc(
			ApplicationDoc checkTechnicalMaterialDoc) {
		this.checkTechnicalMaterialDoc = checkTechnicalMaterialDoc;
	}
	public List<ApplicationDoc> getCheckTechnicalMaterialDocs() {
		return checkTechnicalMaterialDocs;
	}
	public void setCheckTechnicalMaterialDocs(
			List<ApplicationDoc> checkTechnicalMaterialDocs) {
		this.checkTechnicalMaterialDocs = checkTechnicalMaterialDocs;
	}
}

package com.yongjun.tdms.presentation.webwork.action.prophase.business;	
    import java.util.Iterator;
	import java.util.List;
import java.util.Map;

	import org.apache.commons.logging.Log;
	import org.apache.commons.logging.LogFactory;
	import com.yongjun.pluto.service.file.FileTransportManager;
	import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
	import com.yongjun.tdms.model.base.document.ApplicationDoc;
    import com.yongjun.tdms.model.prophase.business.AcceptBill;
	import com.yongjun.tdms.service.base.document.ApplicationDocManager;
import com.yongjun.tdms.service.prophase.business.AcceptBillManager;
	public class ListacceptBillEnclosureFileAction extends ValueListAction {
		private static final long serialVersionUID = -1104105155991390145L;
		private final ApplicationDocManager applicationDocManager;
		private final Log log = LogFactory.getLog(getClass());
		private final FileTransportManager fileTransportManager;
		private final AcceptBillManager acceptBillManager;
		private ApplicationDoc acceptEnclosureDoc;//上传文件辅助类
		private AcceptBill acceptBill ;//验收单对象
		private List<ApplicationDoc> acceptBillDocs;//获得上传文件的内容
		public  ListacceptBillEnclosureFileAction(ApplicationDocManager applicationDocManager,FileTransportManager fileTransportManager
				,AcceptBillManager acceptBillManager){
			this.applicationDocManager=applicationDocManager;
			this.fileTransportManager=fileTransportManager;
			this.acceptBillManager=acceptBillManager;
		}
		public void prepare() throws Exception {
		
				if (this.hasId("acceptBill.id")) {//获得上传的验收单文件
					this.acceptBill = this.acceptBillManager
							.loadAcceptBill(this.getId("acceptBill.id"));
					log.debug("device.doc size is "
							+ acceptBill.getChangeDoc().size());
				}
				if (this.hasId("acceptBillEnclosureIds")) {//获得上传文件的集合
					this.acceptBillDocs = this.applicationDocManager
							.loadAllApplicationDocs(this.getIds("acceptBillEnclosureIds"));
				}
				if (this.hasId("doc.id")) {//装载全部的上传的的文件
					acceptEnclosureDoc = this.applicationDocManager
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
		private void delete() {//删除所有的上传文件
			for (Iterator iter = acceptBillDocs.iterator(); iter.hasNext();) {
				ApplicationDoc doc = (ApplicationDoc) iter.next();
				this.fileTransportManager.delete(request, doc.getPosition());
			}
			this.acceptBill.getChangeDoc().removeAll(acceptBillDocs);
			this.acceptBillManager.storeAcceptBill(acceptBill);
			this.addActionMessage(this.getText("toolingChangeDoc.delete.success"));

		}
		public String download() {//下载文件
			fileTransportManager.download(request, response, acceptEnclosureDoc
					.getFileName(), acceptEnclosureDoc.getPosition());
			return null;
		}
	
		@Override
		protected String getAdapterName() {
			
			return "enclosureDoc";
		}
		public ApplicationDoc getAcceptEnclosureDoc() {
			return acceptEnclosureDoc;
		}
		public void setAcceptEnclosureDoc(ApplicationDoc acceptEnclosureDoc) {
			this.acceptEnclosureDoc = acceptEnclosureDoc;
		}
		public AcceptBill getAcceptBill() {
			return acceptBill;
		}
		public void setAcceptBill(AcceptBill acceptBill) {
			this.acceptBill = acceptBill;
		}
		public List<ApplicationDoc> getAcceptBillDocs() {
			return acceptBillDocs;
		}
		public void setAcceptBillDocs(List<ApplicationDoc> acceptBillDocs) {
			this.acceptBillDocs = acceptBillDocs;
		}
		
		@SuppressWarnings("unchecked")
		protected Map getRequestParameterMap() {
			Map map = super.getRequestParameterMap();
			if(this.hasId("acceptBill.id")) {
				map.put("acceptBill.id",this.getId("acceptBill.id"));
			}
			return map;
		}

	}



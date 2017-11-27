package com.yongjun.tdms.presentation.webwork.action.CustomerRelationship.customerNews;

import java.util.Iterator;
import java.util.List;

import com.yongjun.pluto.model.DomainModel;
import com.yongjun.pluto.service.file.FileTransportManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.CustomerRelationship.customerNews.CustomerNews;
import com.yongjun.tdms.service.CustomerRelationship.customerNews.CustomerNewsManager;

public class ListCustomerNewsAction extends ValueListAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final CustomerNewsManager customerNewsManager;
	private final FileTransportManager fileTransportManager;
	private List<CustomerNews>customerNewses;
	 private Long customerInfoId;
	 private CustomerNews customerNews;
	 public List<CustomerNews> getCustomerNews() {
		return customerNewses;
	}
	
	
	 public ListCustomerNewsAction(CustomerNewsManager customerNewsManager,
			FileTransportManager fileTransportManager) {
		this.customerNewsManager = customerNewsManager;
		this.fileTransportManager = fileTransportManager;
	}
	 public void prepare() throws Exception {
		 customerNewses=null;
			if (hasId("customerInfo.id")) {
				this.customerInfoId = getId("customerInfo.id");
			}
			if(hasId("customerNewsIds")){
				customerNewses=customerNewsManager.loadAllCustomerNews(getIds("customerNewsIds"));
	    	}
			if (hasId("customerNews.id")) {
				this.customerNews= this.customerNewsManager.loadCustomerNews(getId("customerNews.id"));
				download();
			}
		}
	 public String execute() throws Exception {
			if (isDelete()) {
				delete();
			}
			return "success";
		}

		private void delete() {
			String strDoc = "";
			for (Iterator<CustomerNews> iter = this.customerNewses.iterator(); iter.hasNext();) {
				CustomerNews doc = (CustomerNews) iter.next();
				strDoc = strDoc + doc.getPictureName() + ",";
				this.fileTransportManager.delete(this.request, doc.getPosition());
				this.customerNewsManager.deleteCustomerNews(doc);
			}
			Integer index = null;
			index = Integer.valueOf(strDoc.lastIndexOf(","));
			strDoc = strDoc.substring(0, index.intValue());
			getLogger().logStore((DomainModel) this.customerNewses.get(0), "(名称为:[" + strDoc + "]的产品)被删除",
					"applicationDoc");

			addActionMessage(getText("applicationDocs.delete.success"));
		}

		protected String getAdapterName() {
			return "customerNewsHQL";
		}

		public String download() {
			this.fileTransportManager.download(this.request, this.response, this.customerNews.getPictureName(),
					this.customerNews.getPosition());
			return null;
		}
		
		public Long getCustomerInfoId() {
			return customerInfoId;
		}
		public void setCustomerInfoId(Long customerInfoId) {
			this.customerInfoId = customerInfoId;
		}


		public List<CustomerNews> getCustomerNewses() {
			return customerNewses;
		}


		public void setCustomerNewses(List<CustomerNews> customerNewses) {
			this.customerNewses = customerNewses;
		}


		public void setCustomerNews(CustomerNews customerNews) {
			this.customerNews = customerNews;
		}


}

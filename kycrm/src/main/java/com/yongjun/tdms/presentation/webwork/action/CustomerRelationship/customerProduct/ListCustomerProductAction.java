package com.yongjun.tdms.presentation.webwork.action.CustomerRelationship.customerProduct;

import java.util.Iterator;
import java.util.List;

import com.yongjun.pluto.model.DomainModel;
import com.yongjun.pluto.service.file.FileTransportManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.CustomerRelationship.customerProduct.CustomerProduct;
import com.yongjun.tdms.service.CustomerRelationship.customerProduct.CustomerProductManager;

public class ListCustomerProductAction extends ValueListAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final CustomerProductManager customerProductManager;
	private final FileTransportManager fileTransportManager;
	private List<CustomerProduct>customerProducts;
	 private Long customerInfoId;
	 private CustomerProduct customerProduct;
	 public List<CustomerProduct> getCustomerProducts() {
		return customerProducts;
	}
	
	
	 public ListCustomerProductAction(CustomerProductManager customerProductManager,
			FileTransportManager fileTransportManager) {
		this.customerProductManager = customerProductManager;
		this.fileTransportManager = fileTransportManager;
	}
	 public void prepare() throws Exception {
		 customerProducts=null;
			if (hasId("customerInfo.id")) {
				this.customerInfoId = getId("customerInfo.id");
			}
			if(hasId("customerProductIds")){
				customerProducts=customerProductManager.loadAllCustomerProduct(getIds("customerProductIds"));
	    	}
			if (hasId("customerProduct.id")) {
				this.customerProduct= this.customerProductManager.loadCustomerProduct(getId("customerProduct.id"));
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
			for (Iterator<CustomerProduct> iter = this.customerProducts.iterator(); iter.hasNext();) {
				CustomerProduct doc = (CustomerProduct) iter.next();
				strDoc = strDoc + doc.getPictureName() + ",";
				this.fileTransportManager.delete(this.request, doc.getPosition());
				this.customerProductManager.deleteCustomerProduct(doc);
			}
			Integer index = null;
			index = Integer.valueOf(strDoc.lastIndexOf(","));
			strDoc = strDoc.substring(0, index.intValue());
			getLogger().logStore((DomainModel) this.customerProducts.get(0), "(名称为:[" + strDoc + "]的产品)被删除",
					"applicationDoc");

			addActionMessage(getText("applicationDocs.delete.success"));
		}

		protected String getAdapterName() {
			return "customerProductHQL";
		}

		public String download() {
			this.fileTransportManager.download(this.request, this.response, this.customerProduct.getPictureName(),
					this.customerProduct.getPosition());
			return null;
		}
		
		public void setCustomerProducts(List<CustomerProduct> customerProducts) {
			this.customerProducts = customerProducts;
		}
		public Long getCustomerInfoId() {
			return customerInfoId;
		}
		public void setCustomerInfoId(Long customerInfoId) {
			this.customerInfoId = customerInfoId;
		}
		public CustomerProduct getCustomerProduct() {
			return customerProduct;
		}
		public void setCustomerProduct(CustomerProduct customerProduct) {
			this.customerProduct = customerProduct;
		}


}

package com.yongjun.tdms.presentation.webwork.action.base.products;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.base.event.EventNew;
import com.yongjun.tdms.model.base.event.EventType;
import com.yongjun.tdms.model.base.products.Products;
import com.yongjun.tdms.model.base.produttype.ProductType;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.supplier.Supplier;
import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
import com.yongjun.tdms.service.base.event.EventNewManager;
import com.yongjun.tdms.service.base.event.EventTypeManager;
import com.yongjun.tdms.service.base.products.ProductsManager;
import com.yongjun.tdms.service.base.producttype.ProductTypeManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.supplier.SupplierManager;
import com.yongjun.tdms.util.personnelFilesToUser.PersonnelFilesToUserManager;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class EditProductsAction extends PrepareAction {
	private static final long serialVersionUID = -6722017437417848485L;
	private final ProductsManager productsManager;
	private final ProductTypeManager productTypeManager;
	private final SupplierManager supplierManager;
	private final CodeValueManager codeValueManager;
	private final EventNewManager eventNewManager;
	private final EventTypeManager eventTypeManager;
	private final PersonnelFilesToUserManager personnelFilesToUserManager;
	private final UserManager userManager;
	private final CustomerInfoManager customerInfoManager;
    private final PersonnelFilesManager personnelFilesManager;
	
	private Products products;
	private ProductType pt;
	private Supplier supplier;
	private CustomerInfo customerInfo;
	private String backFlag;
    private PersonnelFiles personnelFiles;
	public EditProductsAction(ProductsManager productsManager, ProductTypeManager productTypeManager,
			SupplierManager supplierManager, CodeValueManager codeValueManager, EventNewManager eventNewManager,
			EventTypeManager eventTypeManager, PersonnelFilesToUserManager personnelFilesToUserManager,
			UserManager userManager,CustomerInfoManager customerInfoManager,PersonnelFilesManager personnelFilesManager) {
		this.productsManager = productsManager;
		this.productTypeManager = productTypeManager;
		this.supplierManager = supplierManager;
		this.codeValueManager = codeValueManager;
		this.eventNewManager = eventNewManager;
		this.eventTypeManager = eventTypeManager;
		this.personnelFilesToUserManager = personnelFilesToUserManager;
		this.userManager = userManager;
		this.customerInfoManager = customerInfoManager;
		this.personnelFilesManager=personnelFilesManager;
	}

	public void prepare() throws Exception {
		if ((null != this.userManager.getUser().getCode())&& (!"".equals(this.userManager.getUser().getCode()))){
			this.personnelFiles = ((PersonnelFiles) this.personnelFilesManager.loadByKey("code",this.userManager.getUser().getCode()).get(0));
		}
		if (this.products == null)
			if (hasId("products.id")) {
				this.products = this.productsManager.loadProducts(getId("products.id"));
				this.pt = this.products.getPt();
				this.supplier = this.products.getSupplier();
			} else {
				this.products = new Products();
			}
		if (this.request.getParameter("backFlag") != null) {
			this.backFlag = this.request.getParameter("backFlag");
		}
	}

	public String delete() {
		this.productsManager.deleteProducts(this.products);
		addActionMessage(getText("products.invalid.success", Arrays.asList(new Object[] { this.products.getName() })));
		return "success";
	}

	public String save() {
		boolean isNew = this.products.isNew();
		String submit = null;
		this.products.setIsSaved(this.request.getParameter("isSaved"));
		try {
			if (isNew) {
				if (null == this.productsManager.loadByKey("code", this.products.getCode())) {
					saver();
					this.productsManager.storeProducts(this.products);
					addActionMessage(getText("products.add.success",
							Arrays.asList(new Object[] { this.products.getCode() })));
					return "new";
				}
				saver();
				addActionMessage(getText("products.add.exist", Arrays.asList(new Object[] { this.products.getCode() })));
				return "error";
			}

			saver();
			this.productsManager.storeProducts(this.products);

			// 添加事件
			if ("1".equals(this.request.getParameter("isSaved"))) {
				EventType eventType = null;
				List<EventType> eventTypes = this.eventTypeManager.loadByKey("code", "10010");
				if (eventTypes != null && eventTypes.size() > 0) {
					eventType = eventTypes.get(0);
				} else {
					logger.info("eventTypes不存在！");
				}
				EventNew event = new EventNew();
				event.setEffectflag("E");
				event.setEventType(eventType);
				event.setName(eventType.getName());
				event.setUserId(this.userManager.getUser().getId() + "");
				Map<String, String> map = new HashMap();
				String pids ="";
				CodeValue code = this.products.getBusinessType();
				if(code == null){
					pids = this.personnelFilesToUserManager.loadUserIdToStrByEnable();
				}else {
					pids = this.personnelFilesToUserManager.loadUserIdToStrByType(code.getCode());
				}
				
				map.put("users", pids);
				map.put("productsId", this.products.getId() + "");
				map.put("name", new SimpleDateFormat("yyyy-MM-dd").format(this.products.getCreatedTime())+","+this.products.getCreator()+"提交了产品:"+this.products.getName());
				map.put("url", "productsManager/editProducts.html?popWindowFlag=popWindowFlag&products.id="+this.products.getId());
				String moreinfo = JSONObject.fromObject(map).toString();
				event.setMoreinfo(moreinfo);
				eventNewManager.storeEventNew(event);
				submit = "submit";
			}

			if ("submit".equals(submit)) {
				addActionMessage(getText("products.submit.success",
						Arrays.asList(new Object[] { this.products.getCode() })));
			} else {
				addActionMessage(getText("products.edit.success",
						Arrays.asList(new Object[] { this.products.getCode() })));
			}
			return "success";
		} catch (Exception e) {
			if (isNew)
				addActionMessage(getText("products.add.exist", Arrays.asList(new Object[] { this.products.getCode() })));
			else {
				addActionMessage(getText("products.edit.exist", Arrays.asList(new Object[] { this.products.getCode() })));
			}
			e.printStackTrace();
		}
		return "error";
	}

	private void saver() {
		if (hasId("pt.id")) {
			this.pt = this.productTypeManager.loadProductType(getId("pt.id"));
			this.products.setPt(this.pt);
		}

		if (hasId("product_source_ID.id")) {
			this.products.setProduct_source_ID(this.codeValueManager.loadCodeValue(Long.valueOf(this.request
					.getParameter("product_source_ID.id"))));

			this.products.setProductSource(this.codeValueManager.loadCodeValue(
					Long.valueOf(this.request.getParameter("product_source_ID.id"))).getName());
		}

		if (hasId("supplier.id")) {
			this.supplier = this.supplierManager.loadSupplier(getId("supplier.id"));
			this.products.setSupplier(this.supplier);
		} else {
			this.products.setSupplier(null);
		}
		if (hasId("businessType.id")) {
			    this.products.setBusinessType(this.codeValueManager.loadCodeValue(getId("businessType.id")));
			    }
		
		if (hasId("customerInfo.id")) {
			this.customerInfo = this.customerInfoManager.loadCustomerInfo(getId("customerInfo.id"));
			this.products.setCustomerInfo(this.customerInfo);
		} else {
			this.products.setCustomerInfo(null);
		}

		if (hasId("products.isNoMain")){
			this.products.setIsNoMain(Boolean.parseBoolean(this.request.getParameter("products.isNoMain")));
		}
		this.products.setCreator(userManager.getUser().getName());
	}

	public List getAllProductSource() {
		try {
			CodeValue codeValue = (CodeValue) this.codeValueManager.loadByKey("code", "013").get(0);

			List list = this.codeValueManager.loadByKey("parentCV", codeValue.getId());

			if (list != null) {
				CodeValue cv = new CodeValue();
				cv.setName(getText(""));
				cv.setId(Long.valueOf(-1L));
				list.add(0, cv);
				return list;
			}
			return new ArrayList();
		} catch (DaoException e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}
       public List<CodeValue> getAllBusinessType() {
					try {
						List companyNatures = new ArrayList();
			String[] keys={"code","name"};
			String[] values={"210","客户属性"};
			List one =this.codeValueManager.loadByKeyArray(keys, values);// this.codeValueManager.loadByKey("code", Long.valueOf("210"));
			
			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());
				if ((null != list) && (list.size() > 0)) {
					companyNatures.addAll(list);
				}
			}
			if((null!=companyNatures) && companyNatures.size()>0){
				Iterator<CodeValue> it = companyNatures.iterator();  
	            while(it.hasNext()){        
	            	CodeValue temp = it.next();    
	                if(temp.getName().equals("军民品")){  
	                    it.remove();  
	                }   
	            }  
			}
			return companyNatures;
			} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList();
			}
			}

	public List getAllSupplier() {
		List suppliers = this.supplierManager.loadAllSupplier();
		Supplier sup = new Supplier();
		sup.setName("");
		sup.setId(Long.valueOf(-1L));
		suppliers.add(0, sup);
		return suppliers;
	}

	public List getAllType() {
		return this.productTypeManager.getAllProductTypeByNull(getText(""));
	}

	public Products getProducts() {
		return this.products;
	}

	public void setProducts(Products products) {
		this.products = products;
	}

	public String getBackFlag() {
		return backFlag;
	}

	public void setBackFlag(String backFlag) {
		this.backFlag = backFlag;
	}

	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}

	public PersonnelFiles getPersonnelFiles() {
		return personnelFiles;
	}

	public void setPersonnelFiles(PersonnelFiles personnelFiles) {
		this.personnelFiles = personnelFiles;
	}

}

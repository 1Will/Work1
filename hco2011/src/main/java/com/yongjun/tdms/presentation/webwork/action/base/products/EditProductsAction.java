package com.yongjun.tdms.presentation.webwork.action.base.products;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.base.event.EventNew;
import com.yongjun.tdms.model.base.event.EventType;
import com.yongjun.tdms.model.base.products.Products;
import com.yongjun.tdms.model.base.produttype.ProductType;
import com.yongjun.tdms.model.supplier.Supplier;
import com.yongjun.tdms.service.base.event.EventNewManager;
import com.yongjun.tdms.service.base.event.EventTypeManager;
import com.yongjun.tdms.service.base.products.ProductsManager;
import com.yongjun.tdms.service.base.producttype.ProductTypeManager;
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

	private Products products;
	private ProductType pt;
	private Supplier supplier;
	private String backFlag;

	public EditProductsAction(ProductsManager productsManager, ProductTypeManager productTypeManager,
			SupplierManager supplierManager, CodeValueManager codeValueManager, EventNewManager eventNewManager,
			EventTypeManager eventTypeManager, PersonnelFilesToUserManager personnelFilesToUserManager,
			UserManager userManager) {
		this.productsManager = productsManager;
		this.productTypeManager = productTypeManager;
		this.supplierManager = supplierManager;
		this.codeValueManager = codeValueManager;
		this.eventNewManager = eventNewManager;
		this.eventTypeManager = eventTypeManager;
		this.personnelFilesToUserManager = personnelFilesToUserManager;
		this.userManager = userManager;
	}

	public void prepare() throws Exception {
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
					eventType = new EventType();
					eventType.setId(11L);
				}
				EventNew event = new EventNew();
				event.setEffectflag("E");
				event.setEventType(eventType);
				event.setName("新增产品");
				event.setUserId(this.userManager.getUser().getId() + "");
				Map<String, String> map = new HashMap();
				String pids = this.personnelFilesToUserManager.loadUserIdToStrByEnable();
				map.put("users", pids);
				map.put("productsId", this.products.getId() + "");
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
		if (!StringUtils.isEmpty(this.request.getParameter("pt.id"))) {
			this.pt = this.productTypeManager.loadProductType(getId("pt.id"));
			this.products.setPt(this.pt);
		}

		if (!StringUtils.isEmpty(this.request.getParameter("product_source_ID.id"))) {
			this.products.setProduct_source_ID(this.codeValueManager.loadCodeValue(Long.valueOf(this.request
					.getParameter("product_source_ID.id"))));

			this.products.setProductSource(this.codeValueManager.loadCodeValue(
					Long.valueOf(this.request.getParameter("product_source_ID.id"))).getName());
		}

		if (!StringUtils.isEmpty(this.request.getParameter("supplier.id"))) {
			this.supplier = this.supplierManager.loadSupplier(getId("supplier.id"));
			this.products.setSupplier(this.supplier);
		} else {
			this.products.setSupplier(null);
		}

		if (Integer.parseInt(this.request.getParameter("isNoM")) == 0)
			this.products.setIsNoMain(false);
		else
			this.products.setIsNoMain(true);
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

}

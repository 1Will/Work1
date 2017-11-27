package com.yongjun.tdms.presentation.webwork.action.base.products;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.base.products.Products;
import com.yongjun.tdms.model.base.produttype.ProductType;
import com.yongjun.tdms.model.project.projectInfoProduct.ProjectInfoProduct;
import com.yongjun.tdms.model.supplier.Supplier;
import com.yongjun.tdms.service.base.products.ProductsManager;
import com.yongjun.tdms.service.base.producttype.ProductTypeManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.project.projectInfoProduct.ProjectInfoProductManager;
import com.yongjun.tdms.service.supplier.SupplierManager;

public class ListProductsAction extends ValueListAction {
	private static final long serialVersionUID = 5616960016127750986L;
	private final ProductsManager productsManager;
	private final ProductTypeManager productTypeManager;
	private final SupplierManager supplierManager;
	private final CodeValueManager codeValueManager;
	private final ProjectInfoProductManager projectInfoProductManager;
	private List<Products> productses;
	private String productCheckBox;
	private UserManager userManager;
	private PersonnelFilesManager personnelFilesManager;

	public ListProductsAction(ProductsManager productsManager, ProductTypeManager productTypeManager,
			SupplierManager supplierManager, CodeValueManager codeValueManager,
			ProjectInfoProductManager projectInfoProductManager, UserManager userManager,
			PersonnelFilesManager personnelFilesManager) {
		this.productsManager = productsManager;
		this.productTypeManager = productTypeManager;
		this.supplierManager = supplierManager;
		this.codeValueManager = codeValueManager;
		this.projectInfoProductManager = projectInfoProductManager;
		this.userManager = userManager;
		this.personnelFilesManager = personnelFilesManager;
	}

	public String execute() throws Exception {
		if (isDelete()) {
			delete();
		}
		if (isDisabled()) {
			disabled();
		}
		if (isEnable()) {
			enabled();
		}
		return "success";
	}

	public void prepare() throws Exception {
		if ((this.productses == null) && (hasIds("productsIds")))
			this.productses = this.productsManager.loadAllProducts(getIds("productsIds"));
		if (this.request.getParameter("productCheckBox") != null) {
			this.productCheckBox = this.request.getParameter("productCheckBox");
		}
	}

	public String disabled() {
		try {
			this.productsManager.disabledAllProducts(this.productses);
			addActionMessage(getText("products.disabled.success"));
			return "success";
		} catch (RuntimeException e) {
			addActionMessage(getText("products.disabled.failer"));
		}
		return "error";
	}

	public String enabled() {
		try {
			this.productsManager.enabledAllProducts(this.productses);
			addActionMessage(getText("products.enabled.success"));
			return "success";
		} catch (RuntimeException e) {
			addActionMessage(getText("products.enabled.failer"));
		}
		return "error";
	}

	public List getAllProductSource() {
		List productSources = new ArrayList();
		try {
			CodeValue productSource = (CodeValue) this.codeValueManager.loadByKey("code", "013").get(0);
			productSources = this.codeValueManager.loadByKey("parentCV.id", productSource.getId());
			if (productSources != null) {
				CodeValue cv = new CodeValue();
				cv.setId(Long.valueOf(-1L));
				cv.setName(getText("select.option.all"));
				productSources.add(0, cv);
				return productSources;
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
		sup.setName(getText("select.all.option"));
		sup.setId(Long.valueOf(-1L));
		suppliers.add(0, sup);
		return suppliers;
	}

	public List getAllType() {
		return this.productTypeManager.getAllProductTypeByNull(getText("select.all.option"));
	}

	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();

		// PersonnelFiles personnelFiles =null;
		// try {
		// List<PersonnelFiles>
		// tempList=this.personnelFilesManager.loadByKey("code",
		// this.userManager.getUser().getCode());
		// if(tempList!=null&&tempList.size()>0){
		// personnelFiles = tempList.get(0);
		// if(personnelFiles.getBusinessType()!=null){
		// if(personnelFiles.getBusinessType().getName().equals("军品")||personnelFiles.getBusinessType().getName().equals("民品")){
		// map.put("businessType",
		// "%"+personnelFiles.getBusinessType().getName()+"%");
		// }
		//
		// }
		// }
		// } catch (DaoException e) {
		// e.printStackTrace();
		// }
		if (hasIds("projectInfoId")) {
			long projectInfoId = Long.parseLong(request.getParameter("projectInfoId"));
			List<ProjectInfoProduct> ppList = null;
			try {
				ppList = projectInfoProductManager.loadByKey("projectInfo", projectInfoId);
			} catch (DaoException e) {
				e.printStackTrace();
			}
			if (ppList != null) {
				List ppIds = new ArrayList();
				for (int i = 0; i < ppList.size(); i++) {
					ppIds.add(ppList.get(i).getProducts().getId());
				}
				map.put("ppIds", ppIds);
			}
		}
		List<Long> ptidList = null;
		if (hasId("pt.id")) {
			ptidList = new ArrayList<Long>();
			ptidList.add(getId("pt.id"));
			try {
				List<ProductType> list = this.productTypeManager.loadByKey("parentPT.id", getId("pt.id"));
				if (list != null && list.size() > 0) {
					for (ProductType cv : list) {
						ptidList.add(cv.getId());
					}
				}
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (ptidList != null) {
			map.put("ptidList", ptidList);
		}
		return map;
	}

	protected String getAdapterName() {
		return "products";
	}

	private String delete() {
		try {
			this.productsManager.deleteAllProducts(this.productses);
			addActionMessage(getText("productses.delete.success"));
			return "success";
		} catch (RuntimeException e) {
			addActionMessage(getText("products.delete.failer"));
		}
		return "error";
	}

	public List getProductses() {
		return this.productses;
	}

	public void setProductses(List<Products> productses) {
		this.productses = productses;
	}

	public String getProductCheckBox() {
		return productCheckBox;
	}

	public void setProductCheckBox(String productCheckBox) {
		this.productCheckBox = productCheckBox;
	}

}

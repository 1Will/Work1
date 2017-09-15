/*     */ package com.yongjun.tdms.presentation.webwork.action.base.products;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.base.products.Products;
import com.yongjun.tdms.model.base.produttype.ProductType;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.project.projectInfoProduct.ProjectInfoProduct;
/*     */ import com.yongjun.tdms.model.supplier.Supplier;
/*     */ import com.yongjun.tdms.service.base.products.ProductsManager;
/*     */ import com.yongjun.tdms.service.base.producttype.ProductTypeManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.project.projectInfoProduct.ProjectInfoProductManager;
/*     */ import com.yongjun.tdms.service.supplier.SupplierManager;

/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
import java.util.Map;
/*     */ 
/*     */ public class ListProductsAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 5616960016127750986L;
/*     */   private final ProductsManager productsManager;
/*     */   private final ProductTypeManager productTypeManager;
/*     */   private final SupplierManager supplierManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private final ProjectInfoProductManager projectInfoProductManager;
/*     */   private List<Products> productses;
            private String productCheckBox;
            private UserManager userManager;
            private PersonnelFilesManager personnelFilesManager;
/*     */ 
/*     */   public ListProductsAction(ProductsManager productsManager, ProductTypeManager productTypeManager, SupplierManager supplierManager, CodeValueManager codeValueManager,ProjectInfoProductManager projectInfoProductManager,UserManager userManager,PersonnelFilesManager personnelFilesManager)
/*     */   {
/*  35 */     this.productsManager = productsManager;
/*  36 */     this.productTypeManager = productTypeManager;
/*  37 */     this.supplierManager = supplierManager;
/*  38 */     this.codeValueManager = codeValueManager;
			  this.projectInfoProductManager=projectInfoProductManager;
			  this.userManager =userManager;
			  this.personnelFilesManager = personnelFilesManager;
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/*  44 */     if (isDelete()) {
/*  45 */       delete();
/*     */     }
/*  47 */     if (isDisabled()) {
/*  48 */       disabled();
/*     */     }
/*  50 */     if (isEnable()) {
/*  51 */       enabled();
/*     */     }
/*  53 */     return "success";
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  60 */     if ((this.productses == null) && (hasIds("productsIds")))
/*  61 */       this.productses = this.productsManager.loadAllProducts(getIds("productsIds"));
              if(this.request.getParameter("productCheckBox")!=null){
            	  this.productCheckBox =this.request.getParameter("productCheckBox");
              }
/*     */   }
/*     */ 
/*     */   public String disabled()
/*     */   {
/*     */     try
/*     */     {
/*  69 */       this.productsManager.disabledAllProducts(this.productses);
/*  70 */       addActionMessage(getText("products.disabled.success"));
/*  71 */       return "success";
/*     */     } catch (RuntimeException e) {
/*  73 */       addActionMessage(getText("products.disabled.failer"));
/*  74 */     }return "error";
/*     */   }
/*     */ 
/*     */   public String enabled()
/*     */   {
/*     */     try
/*     */     {
/*  82 */       this.productsManager.enabledAllProducts(this.productses);
/*  83 */       addActionMessage(getText("products.enabled.success"));
/*  84 */       return "success";
/*     */     } catch (RuntimeException e) {
/*  86 */       addActionMessage(getText("products.enabled.failer"));
/*  87 */     }return "error";
/*     */   }
/*     */ 
/*     */   public List getAllProductSource()
/*     */   {
/*  94 */     List productSources = new ArrayList();
/*     */     try {
/*  96 */       CodeValue productSource = (CodeValue)this.codeValueManager.loadByKey("code", "013").get(0);
/*  97 */       productSources = this.codeValueManager.loadByKey("parentCV.id", productSource.getId());
/*  98 */       if (productSources != null) {
/*  99 */         CodeValue cv = new CodeValue();
/* 100 */         cv.setId(Long.valueOf(-1L));
/* 101 */         cv.setName(getText("select.option.all"));
/* 102 */         productSources.add(0, cv);
/* 103 */         return productSources;
/*     */       }
/* 105 */       return new ArrayList();
/*     */     }
/*     */     catch (DaoException e)
/*     */     {
/* 109 */       e.printStackTrace();
/* 110 */       return new ArrayList();
/*     */     }
/*     */   }
/*     */ 
/*     */   public List getAllSupplier()
/*     */   {
/* 119 */     List suppliers = this.supplierManager.loadAllSupplier();
/* 120 */     Supplier sup = new Supplier();
/* 121 */     sup.setName(getText("select.all.option"));
/* 122 */     sup.setId(Long.valueOf(-1L));
/* 123 */     suppliers.add(0, sup);
/* 124 */     return suppliers;
/*     */   }
/*     */ 
/*     */   public List getAllType()
/*     */   {
/* 131 */     return this.productTypeManager.getAllProductTypeByNull(getText("select.all.option"));
/*     */   }

			protected Map getRequestParameterMap()
/*     */   {
/* 121 */     Map map = super.getRequestParameterMap();

				PersonnelFiles personnelFiles =null;
					try {
						List<PersonnelFiles>  tempList=this.personnelFilesManager.loadByKey("code", this.userManager.getUser().getCode());
						if(tempList!=null&&tempList.size()>0){
							personnelFiles  = tempList.get(0);
							if(personnelFiles.getBusinessType()!=null){
								if(personnelFiles.getBusinessType().getName().equals("军品")||personnelFiles.getBusinessType().getName().equals("民品")){
									map.put("businessType", "%"+personnelFiles.getBusinessType().getName()+"%");
								}
							
							}
						}
					} catch (DaoException e) {
	// TODO Auto-generated catch block
						e.printStackTrace();
						}
			  if(hasIds("projectInfoId")){
				 long projectInfoId =Long.parseLong(request.getParameter("projectInfoId"));
				 List<ProjectInfoProduct> ppList =null;
				 try {
					 ppList =projectInfoProductManager.loadByKey("projectInfo", projectInfoId);
				} catch (DaoException e) {
					e.printStackTrace();
				}
				 if(ppList!=null){
					 List ppIds=new ArrayList();
					for(int i=0;i<ppList.size();i++){
						ppIds.add(ppList.get(i).getProducts().getId());
					}
					map.put("ppIds", ppIds);
				}
			  }
			  List<Long> ptidList =null;
			  if(hasId("pt.id")){
				  ptidList = new ArrayList<Long>();
				  ptidList.add(getId("pt.id"));
				  try {
					List<ProductType> list = this.productTypeManager.loadByKey("parentPT.id", getId("pt.id"));
					if(list!=null&&list.size()>0){
						for(ProductType cv:list){
							ptidList.add(cv.getId());
						}
					}
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
			  if(ptidList!=null){
				  map.put("ptidList", ptidList);
			  }
			  return map;
			}
/*     */ 
/*     */   protected String getAdapterName() {
/* 135 */     return "products";
/*     */   }
/*     */ 
/*     */   private String delete()
/*     */   {
/*     */     try
/*     */     {
/* 143 */       this.productsManager.deleteAllProducts(this.productses);
/* 144 */       addActionMessage(getText("productses.delete.success"));
/* 145 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 147 */       addActionMessage(getText("products.delete.failer"));
/* 148 */     }return "error";
/*     */   }
/*     */ 
/*     */   public List getProductses() {
/* 152 */     return this.productses;
/*     */   }
/*     */ 
/*     */   public void setProductses(List<Products> productses) {
/* 156 */     this.productses = productses;
/*     */   }
			public String getProductCheckBox() {
				return productCheckBox;
			}
			public void setProductCheckBox(String productCheckBox) {
				this.productCheckBox = productCheckBox;
			}

/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.base.products.ListProductsAction
 * JD-Core Version:    0.6.2
 */
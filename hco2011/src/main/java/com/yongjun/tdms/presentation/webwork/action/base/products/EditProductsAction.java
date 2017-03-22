/*     */ package com.yongjun.tdms.presentation.webwork.action.base.products;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.base.products.Products;
/*     */ import com.yongjun.tdms.model.base.produttype.ProductType;
/*     */ import com.yongjun.tdms.model.supplier.Supplier;
/*     */ import com.yongjun.tdms.service.base.products.ProductsManager;
/*     */ import com.yongjun.tdms.service.base.producttype.ProductTypeManager;
/*     */ import com.yongjun.tdms.service.supplier.SupplierManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class EditProductsAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = -6722017437417848485L;
/*     */   private final ProductsManager productsManager;
/*     */   private final ProductTypeManager productTypeManager;
/*     */   private final SupplierManager supplierManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private Products products;
/*     */   private ProductType pt;
/*     */   private Supplier supplier;
            private String backFlag;
/*     */ 
/*     */   public EditProductsAction(ProductsManager productsManager, ProductTypeManager productTypeManager, SupplierManager supplierManager, CodeValueManager codeValueManager)
/*     */   {
/*  51 */     this.productsManager = productsManager;
/*  52 */     this.productTypeManager = productTypeManager;
/*  53 */     this.supplierManager = supplierManager;
/*  54 */     this.codeValueManager = codeValueManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  62 */     if (this.products == null)
/*  63 */       if (hasId("products.id")) {
/*  64 */         this.products = this.productsManager.loadProducts(getId("products.id"));
/*  65 */         this.pt = this.products.getPt();
/*  66 */         this.supplier = this.products.getSupplier();
/*     */       } else {
/*  68 */         this.products = new Products();
/*     */       }
               if(this.request.getParameter("backFlag")!=null){
              this.backFlag =backFlag;
               }
/*     */   }
/*     */ 
/*     */   public String delete()
/*     */   {
/*  78 */     this.productsManager.deleteProducts(this.products);
/*  79 */     addActionMessage(getText("products.invalid.success", Arrays.asList(new Object[] { this.products.getName() })));
/*  80 */     return "success";
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/*  89 */     boolean isNew = this.products.isNew();
/*     */     try
/*     */     {
/*  92 */       if (isNew) {
/*  93 */         if (null == this.productsManager.loadByKey("code", this.products.getCode())) {
/*  94 */           saver();
/*  95 */           this.productsManager.storeProducts(this.products);
/*  96 */           addActionMessage(getText("products.add.success", Arrays.asList(new Object[] { this.products.getCode() })));
/*  97 */           return "new";
/*     */         }
/*  99 */         saver();
/* 100 */         addActionMessage(getText("products.add.exist", Arrays.asList(new Object[] { this.products.getCode() })));
/* 101 */         return "error";
/*     */       }
/*     */ 
/* 104 */       saver();
/* 105 */       this.productsManager.storeProducts(this.products);
/* 106 */       addActionMessage(getText("products.edit.success", Arrays.asList(new Object[] { this.products.getCode() })));
/* 107 */       return "success";
/*     */     }
/*     */     catch (Exception e) {
/* 110 */       if (isNew)
/* 111 */         addActionMessage(getText("products.add.exist", Arrays.asList(new Object[] { this.products.getCode() })));
/*     */       else {
/* 113 */         addActionMessage(getText("products.edit.exist", Arrays.asList(new Object[] { this.products.getCode() })));
/*     */       }
/* 115 */       e.printStackTrace();
/* 116 */     }return "error";
/*     */   }
/*     */ 
/*     */   private void saver()
/*     */   {
/* 126 */     if (!StringUtils.isEmpty(this.request.getParameter("pt.id"))) {
/* 127 */       this.pt = this.productTypeManager.loadProductType(getId("pt.id"));
/* 128 */       this.products.setPt(this.pt);
/*     */     }
/*     */ 
/* 131 */     if (!StringUtils.isEmpty(this.request.getParameter("product_source_ID.id"))) {
/* 132 */       this.products.setProduct_source_ID(this.codeValueManager.loadCodeValue(Long.valueOf(this.request.getParameter("product_source_ID.id"))));
/*     */ 
/* 134 */       this.products.setProductSource(this.codeValueManager.loadCodeValue(Long.valueOf(this.request.getParameter("product_source_ID.id"))).getName());
/*     */     }
/*     */ 
/* 138 */     if (!StringUtils.isEmpty(this.request.getParameter("supplier.id"))) {
/* 139 */       this.supplier = this.supplierManager.loadSupplier(getId("supplier.id"));
/* 140 */       this.products.setSupplier(this.supplier);
/*     */     } else {
/* 142 */       this.products.setSupplier(null);
/*     */     }
/*     */ 
/* 145 */     if (Integer.parseInt(this.request.getParameter("isNoM")) == 0)
/* 146 */       this.products.setIsNoMain(false);
/*     */     else
/* 148 */       this.products.setIsNoMain(true);
/*     */   }
/*     */ 
/*     */   public List getAllProductSource()
/*     */   {
/*     */     try
/*     */     {
/* 159 */       CodeValue codeValue = (CodeValue)this.codeValueManager.loadByKey("code", "013").get(0);
/*     */ 
/* 161 */       List list = this.codeValueManager.loadByKey("parentCV", codeValue.getId());
/*     */ 
/* 163 */       if (list != null) {
/* 164 */         CodeValue cv = new CodeValue();
/* 165 */         cv.setName(getText(""));
/* 166 */         cv.setId(Long.valueOf(-1L));
/* 167 */         list.add(0, cv);
/* 168 */         return list;
/*     */       }
/* 170 */       return new ArrayList();
/*     */     }
/*     */     catch (DaoException e)
/*     */     {
/* 174 */       e.printStackTrace();
/* 175 */       return new ArrayList();
/*     */     }
/*     */   }
/*     */ 
/*     */   public List getAllSupplier()
/*     */   {
/* 184 */     List suppliers = this.supplierManager.loadAllSupplier();
/* 185 */     Supplier sup = new Supplier();
/* 186 */     sup.setName("");
/* 187 */     sup.setId(Long.valueOf(-1L));
/* 188 */     suppliers.add(0, sup);
/* 189 */     return suppliers;
/*     */   }
/*     */ 
/*     */   public List getAllType()
/*     */   {
/* 196 */     return this.productTypeManager.getAllProductTypeByNull(getText(""));
/*     */   }
/*     */ 
/*     */   public Products getProducts() {
/* 200 */     return this.products;
/*     */   }
/*     */ 
/*     */   public void setProducts(Products products) {
/* 204 */     this.products = products;
/*     */   }
			public String getBackFlag() {
				return backFlag;
			}
			public void setBackFlag(String backFlag) {
				this.backFlag = backFlag;
			}

/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.base.products.EditProductsAction
 * JD-Core Version:    0.6.2
 */
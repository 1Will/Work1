/*     */ package com.yongjun.tdms.presentation.webwork.action.customercontract.contractmanagement.productlist;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.base.products.Products;
/*     */ import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
/*     */ import com.yongjun.tdms.model.customercontract.contractmanagement.productlist.ProductList;
/*     */ import com.yongjun.tdms.service.base.products.ProductsManager;
/*     */ import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;
/*     */ import com.yongjun.tdms.service.customercontract.contractmanagement.productlist.ProductListManager;
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class EditProductListAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final ProductListManager productListManager;
/*     */   private final ContractManagementManager contractManagementManager;
/*     */   private final ProductsManager productsManager;
/*     */   private final CodeValueManager codeValueManager;
/*  67 */   private ProductList productList = null;
/*     */   private ContractManagement contractManagement;
/*     */   private Products product;
/*     */   private CodeValue unit;
/*     */   private long contractManagementid;
/*     */ 
/*     */   public EditProductListAction(ProductListManager productListManager, ContractManagementManager contractManagementManager, CodeValueManager codeValueManager, ProductsManager productsManager)
/*     */   {
/*  95 */     this.productListManager = productListManager;
/*  96 */     this.contractManagementManager = contractManagementManager;
/*  97 */     this.codeValueManager = codeValueManager;
/*  98 */     this.productsManager = productsManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/* 107 */     if (hasId("productList.id")) {
/* 108 */       this.productList = this.productListManager.loadProductList(getId("productList.id"));
/*     */ 
/* 110 */       this.contractManagement = this.productList.getContractManagement();
/* 111 */       this.contractManagementid = this.contractManagement.getId().longValue();
/*     */     } else {
/* 113 */       this.productList = new ProductList();
/* 114 */       this.contractManagementid = Long.valueOf(this.request.getParameter("contractManagementid")).longValue();
/* 115 */       this.contractManagement = this.contractManagementManager.loadContractManagement(Long.valueOf(this.contractManagementid));
/*     */ 
/* 117 */       this.productList.setContractManagement(this.contractManagement);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String execute() throws Exception {
/* 122 */     return super.execute();
/*     */   }
/*     */ 
/*     */   public String save()
/*     */     throws Exception, DaoException
/*     */   {
/* 131 */     boolean isNew = this.productList.isNew();
/*     */ 
/* 133 */     if (hasId("product.id")) {
/* 134 */       this.product = this.productsManager.loadProducts(getId("product.id"));
/*     */     }
/*     */ 
/* 137 */     this.productList.setProduct(this.product);
/*     */ 
/* 139 */     if (hasId("unit.id")) {
/* 140 */       this.unit = this.codeValueManager.loadCodeValue(getId("unit.id"));
/*     */     }
/*     */ 
/* 143 */     this.productList.setUnit(this.unit);
/*     */ 
/* 146 */     this.productListManager.storeProductList(this.productList);
/*     */ 
/* 148 */     ContractManagement cm = this.contractManagementManager.loadContractManagement(this.productList.getContractManagement().getId());
/* 149 */     DecimalFormat format = new DecimalFormat("0.00");
/* 150 */     cm.setContractMoney(new Double(format.format(this.productListManager.getSumTotalPrice(cm.getId().longValue()))).doubleValue());
/*     */ 
/* 152 */     this.contractManagementManager.storeContractManagement(cm);
/*     */     try
/*     */     {
/* 156 */       if (isNew) {
/* 157 */         addActionMessage(getText("productList.add.success"));
/* 158 */         return "new";
/*     */       }
/* 160 */       addActionMessage(getText("productList.edit.success"));
/* 161 */       return "success";
/*     */     }
/*     */     catch (Exception ex)
/*     */     {
/* 165 */       ex.printStackTrace();
/* 166 */       if (isNew) {
/* 167 */         addActionMessage(getText("productList.add.error"));
/* 168 */         return "new";
/*     */       }
/* 170 */       addActionMessage(getText("productList.edit.error"));
/* 171 */     }return "success";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllUnit()
/*     */   {
/* 182 */     List codes = null;
/*     */     try {
/* 184 */       codes = new ArrayList();
/* 185 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("068"));
/*     */ 
/* 187 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 189 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 191 */         if ((null != list) && (list.size() > 0)) {
/* 192 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 195 */       return codes;
/*     */     } catch (DaoException e) {
/* 197 */       e.printStackTrace();
/*     */     }
/* 199 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<Products> getAllProduct()
/*     */   {
/* 206 */     List pros = new ArrayList();
/* 207 */     List<Products> proTemps = this.productsManager.getAllProductsByNull("所有");
/* 208 */     for (Products p : proTemps) {
/* 209 */       if (!p.getDisabled()) {
/* 210 */         pros.add(p);
/*     */       }
/*     */     }
/* 213 */     return pros;
/*     */   }
/*     */ 
/*     */   public ProductList getProductList()
/*     */   {
/* 221 */     return this.productList;
/*     */   }
/*     */ 
/*     */   public void setProductList(ProductList productList)
/*     */   {
/* 228 */     this.productList = productList;
/*     */   }
/*     */ 
/*     */   public long getContractManagementid()
/*     */   {
/* 236 */     return this.contractManagementid;
/*     */   }
/*     */ 
/*     */   public void setContractManagementid(long contractManagementid)
/*     */   {
/* 243 */     this.contractManagementid = contractManagementid;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.customercontract.contractmanagement.productlist.EditProductListAction
 * JD-Core Version:    0.6.2
 */
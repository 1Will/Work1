/*     */ package com.yongjun.tdms.presentation.webwork.action.base.productsType;
/*     */ 
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.base.produttype.ProductType;
/*     */ import com.yongjun.tdms.service.base.producttype.ProductTypeManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class EditProductsTypeAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final ProductTypeManager productTypeManager;
/*     */   private ProductType productType;
/*     */   private ProductType parentPT;
/*     */ 
/*     */   public EditProductsTypeAction(ProductTypeManager productTypeManager)
/*     */   {
/*  27 */     this.productTypeManager = productTypeManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  33 */     if (null == this.productType)
/*  34 */       if (hasId("productType.id")) {
/*  35 */         this.productType = this.productTypeManager.loadProductType(getId("productType.id"));
/*  36 */         this.parentPT = this.productType.getParentPT();
/*     */       } else {
/*  38 */         this.productType = new ProductType();
/*     */       }
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/*  47 */     boolean isNew = this.productType.isNew();
/*     */ 
/*  51 */     if (!StringUtils.isEmpty(this.request.getParameter("productType.parentPT")))
/*  52 */       if (this.request.getParameter("productType.parentPT").equals("0"))
/*     */       {
/*  54 */         this.productType.setStep(Integer.valueOf(0));
/*     */       } else {
/*  56 */         ProductType parentPT = this.productTypeManager.loadProductType(Long.valueOf(this.request.getParameter("productType.parentPT")));
/*     */ 
/*  58 */         this.productType.setStep(Integer.valueOf(parentPT.getStep().intValue() + 1));
/*  59 */         this.productType.setParentPT(parentPT);
/*     */       }
/*     */     try
/*     */     {
/*  63 */       if (isNew) {
/*  64 */         if (null == this.productTypeManager.loadByKey("code", this.productType.getCode())) {
/*  65 */           this.productTypeManager.storeProductType(this.productType);
/*  66 */           addActionMessage(getText("productType.add.success", Arrays.asList(new Object[] { this.productType.getCode() })));
/*  67 */           return "new";
/*     */         }
/*  69 */         addActionMessage(getText("productType.add.exist", Arrays.asList(new Object[] { this.productType.getCode() })));
/*  70 */         return "error";
/*     */       }
/*     */ 
/*  73 */       this.productTypeManager.storeProductType(this.productType);
/*  74 */       addActionMessage(getText("productType.edit.success", Arrays.asList(new Object[] { this.productType.getCode() })));
/*  75 */       return "success";
/*     */     }
/*     */     catch (Exception e) {
/*  78 */       if (isNew)
/*  79 */         addActionMessage(getText("productType.add.exist", Arrays.asList(new Object[] { this.productType.getCode() })));
/*     */       else {
/*  81 */         addActionMessage(getText("productType.edit.exist", Arrays.asList(new Object[] { this.productType.getCode() })));
/*     */       }
/*  83 */       e.printStackTrace();
/*  84 */     }return "error";
/*     */   }
/*     */ 
/*     */   public List getAllParentPT()
/*     */   {
/*  94 */     List<ProductType> pts = this.productTypeManager.createSelectProductTypes(getText("select.option.non"));
/*  95 */     if (this.productType.isNew())
/*     */     {
/*  97 */       if ((null != this.productType) && (!this.productType.isNew())) {
/*  98 */         pts.remove(this.productType);
/*     */       }
/*     */ 
/* 101 */       List list = new ArrayList();
/* 102 */       for (ProductType pt : pts) {
/* 103 */         if (!pt.getDisabled()) {
/* 104 */           list.add(pt);
/*     */         }
/*     */       }
/* 107 */       return list;
/*     */     }
/*     */ 
/* 110 */     List list = new ArrayList();
/* 111 */     for (ProductType pt : pts) {
/* 112 */       if (!pt.getDisabled()) {
/* 113 */         list.add(pt);
/*     */       }
/*     */     }
/* 116 */     return this.productTypeManager.getPT(this.productType, list, getText("select.option.non"));
/*     */   }
/*     */ 
/*     */   public ProductType getParentPT()
/*     */   {
/* 122 */     return this.parentPT;
/*     */   }
/*     */   public void setParentPT(ProductType parentPT) {
/* 125 */     this.parentPT = parentPT;
/*     */   }
/*     */   public ProductType getProductType() {
/* 128 */     return this.productType;
/*     */   }
/*     */ 
/*     */   public void setProductType(ProductType productType) {
/* 132 */     this.productType = productType;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.base.productsType.EditProductsTypeAction
 * JD-Core Version:    0.6.2
 */
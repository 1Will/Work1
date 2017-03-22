/*     */ package com.yongjun.tdms.presentation.webwork.action.base.productsType;
/*     */ 
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.base.produttype.ProductType;
/*     */ import com.yongjun.tdms.service.base.producttype.ProductTypeManager;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ListProductsTypeAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final ProductTypeManager productTypeManager;
/*     */   private List<ProductType> pts;
/*     */ 
/*     */   public ListProductsTypeAction(ProductTypeManager productTypeManager)
/*     */   {
/*  21 */     this.productTypeManager = productTypeManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  27 */     if ((null == this.pts) && (hasIds("ptIds")))
/*  28 */       this.pts = this.productTypeManager.loadAllProductType(getIds("ptIds"));
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/*  36 */     if (isDisabled()) {
/*  37 */       disabled();
/*     */     }
/*  39 */     if (isEnable()) {
/*  40 */       enabled();
/*     */     }
/*  42 */     if (isDelete()) {
/*  43 */       delete();
/*     */     }
/*  45 */     return "success";
/*     */   }
/*     */ 
/*     */   private String disabled()
/*     */   {
/*     */     try
/*     */     {
/*  53 */       this.productTypeManager.disabledAllProductType(this.pts);
/*     */     } catch (Exception e) {
/*  55 */       addActionMessage(getText("productType.disabled.error"));
/*  56 */       return "error";
/*     */     }
/*  58 */     addActionMessage(getText("productType.disabled.success"));
/*  59 */     return "success";
/*     */   }
/*     */ 
/*     */   private String enabled()
/*     */   {
/*     */     try
/*     */     {
/*  67 */       this.productTypeManager.enabledAllProductType(this.pts);
/*     */     } catch (Exception e) {
/*  69 */       addActionMessage(getText("productType.enabled.error"));
/*  70 */       return "error";
/*     */     }
/*  72 */     addActionMessage(getText("productType.enabled.success"));
/*  73 */     return "success";
/*     */   }
/*     */ 
/*     */   private String delete()
/*     */   {
/*     */     try
/*     */     {
/*  81 */       this.productTypeManager.deleteAllProductType(this.pts);
/*     */     } catch (Exception e) {
/*  83 */       addActionMessage(getText("productType.delete.error"));
/*  84 */       return "error";
/*     */     }
/*  86 */     addActionMessage(getText("productType.delete.success"));
/*  87 */     return "success";
/*     */   }
/*     */ 
/*     */   protected String getAdapterName() {
/*  91 */     return "pts";
/*     */   }
/*     */ 
/*     */   public List<ProductType> getPts() {
/*  95 */     return this.pts;
/*     */   }
/*     */ 
/*     */   public void setPts(List<ProductType> pts) {
/*  99 */     this.pts = pts;
/*     */   }
/*     */ 
/*     */   public List getAllParentPT()
/*     */   {
/* 110 */     List pts = this.productTypeManager.createSelectProductTypes(getText("select.option.non"));
/* 111 */     ProductType pt = new ProductType();
/* 112 */     pt.setId(Long.valueOf(-1L));
/* 113 */     pt.setName(getText("select.option.all"));
/* 114 */     pts.add(0, pt);
/* 115 */     return pts;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.base.productsType.ListProductsTypeAction
 * JD-Core Version:    0.6.2
 */
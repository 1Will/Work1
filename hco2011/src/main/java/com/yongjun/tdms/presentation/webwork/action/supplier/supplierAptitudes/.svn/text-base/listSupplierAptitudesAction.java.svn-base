/*     */ package com.yongjun.tdms.presentation.webwork.action.supplier.supplierAptitudes;
/*     */ 
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.supplier.Supplier;
/*     */ import com.yongjun.tdms.model.supplier.supplierAptitudes.SupplierAptitudes;
/*     */ import com.yongjun.tdms.service.supplier.SupplierManager;
/*     */ import com.yongjun.tdms.service.supplier.supplierAptitudes.SupplierAptitudesManager;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class listSupplierAptitudesAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = -7952479992571055935L;
/*     */   private Supplier supplier;
/*     */   private List<SupplierAptitudes> supplierAptitudeses;
/*     */   private final SupplierAptitudesManager supplierAptitudesManager;
/*     */   private final SupplierManager supplierManager;
/*     */ 
/*     */   public listSupplierAptitudesAction(SupplierAptitudesManager supplierAptitudesManager, SupplierManager supplierManager)
/*     */   {
/*  59 */     this.supplierAptitudesManager = supplierAptitudesManager;
/*  60 */     this.supplierManager = supplierManager;
/*     */   }
/*     */ 
/*     */   private void delete() {
/*  64 */     this.supplierAptitudesManager.deleteAllSupplierAptitudes(this.supplierAptitudeses);
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/*  70 */     if (isDelete()) {
/*  71 */       delete();
/*     */     }
/*  73 */     return "success";
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  78 */     return "supplierAptitudesHQL";
/*     */   }
/*     */ 
/*     */   protected Map getRequestParameterMap()
/*     */   {
/*  83 */     Map map = super.getRequestParameterMap();
/*  84 */     if ((null != this.request.getParameter("supplier.id")) && (this.request.getParameter("supplier.id") != ""))
/*     */     {
/*  86 */       map.put("sex", Long.valueOf(this.request.getParameter("supplier.id")));
/*     */     }
/*  88 */     return map;
/*     */   }
/*     */ 
/*     */   public Supplier getSupplier() {
/*  92 */     return this.supplier;
/*     */   }
/*     */ 
/*     */   public List<SupplierAptitudes> getSupplierAptitudeses() {
/*  96 */     return this.supplierAptitudeses;
/*     */   }
/*     */ 
/*     */   public void prepare() throws Exception
/*     */   {
/* 101 */     if (hasIds("supplierAptitudesIds")) {
/* 102 */       this.supplierAptitudeses = this.supplierAptitudesManager.loadAllSupplierAptitudes(getIds("supplierAptitudesIds"));
/*     */     }
/*     */ 
/* 106 */     if ((this.supplier == null) && (this.request.getParameter("supplier.id") != null))
/*     */     {
/* 108 */       this.supplier = this.supplierManager.loadSupplier(Long.valueOf(this.request.getParameter("supplier.id")));
/*     */     }
/*     */ 
/* 111 */     if (this.request.getParameter("supplier.id") != null)
/* 112 */       setFirst(false);
/*     */   }
/*     */ 
/*     */   public void setSupplier(Supplier supplier)
/*     */   {
/* 117 */     this.supplier = supplier;
/*     */   }
/*     */ 
/*     */   public void setSupplierAptitudeses(List<SupplierAptitudes> supplierAptitudeses)
/*     */   {
/* 122 */     this.supplierAptitudeses = supplierAptitudeses;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.supplier.supplierAptitudes.listSupplierAptitudesAction
 * JD-Core Version:    0.6.2
 */
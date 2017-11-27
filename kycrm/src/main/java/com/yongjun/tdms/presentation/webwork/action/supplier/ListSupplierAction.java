/*     */ package com.yongjun.tdms.presentation.webwork.action.supplier;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.supplier.Supplier;
/*     */ import com.yongjun.tdms.service.supplier.SupplierManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ListSupplierAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 1555028259278711499L;
/*     */   private final SupplierManager supplierManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private List<Supplier> supplier;
/*     */ 
/*     */   public ListSupplierAction(SupplierManager supplierManager, CodeValueManager codeValueManager)
/*     */   {
/*  57 */     this.supplierManager = supplierManager;
/*  58 */     this.codeValueManager = codeValueManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  65 */     if ((null == this.supplier) && (hasIds("supplierIds")))
/*  66 */       this.supplier = this.supplierManager.loadAllSupplier(getIds("supplierIds"));
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/*  72 */     if (isDisabled()) {
/*  73 */       disable();
/*     */     }
/*  75 */     if (isEnable()) {
/*  76 */       enabled();
/*     */     }
/*  78 */     if (isDelete()) {
/*  79 */       delete();
/*     */     }
/*  81 */     return "success";
/*     */   }
/*     */ 
/*     */   public List getAllSupplierType()
/*     */   {
/*     */     try
/*     */     {
/*  89 */       List list1 = this.codeValueManager.loadByKey("code", "008");
/*  90 */       if (list1 == null) {
/*  91 */         return new ArrayList();
/*     */       }
/*  93 */       List list = this.codeValueManager.loadByKey("parentCV", ((CodeValue)list1.get(0)).getId());
/*     */ 
/*  95 */       if (list == null) {
/*  96 */         return new ArrayList();
/*     */       }
/*  98 */       CodeValue cv = new CodeValue();
/*  99 */       cv.setId(Long.valueOf(0L));
/* 100 */       cv.setName(getText("select.option.all"));
/* 101 */       list.add(0, cv);
/* 102 */       return list;
/*     */     }
/*     */     catch (DaoException e)
/*     */     {
/* 106 */       e.printStackTrace();
/* 107 */     }return new ArrayList();
/*     */   }
/*     */ 
/*     */   public List getAllTradeType()
/*     */   {
/*     */     try
/*     */     {
/* 116 */       List list = this.codeValueManager.loadByKey("parentCV", ((CodeValue)this.codeValueManager.loadByKey("code", "002").get(0)).getId());
/*     */ 
/* 119 */       CodeValue cv = new CodeValue();
/* 120 */       cv.setId(Long.valueOf(0L));
/* 121 */       cv.setName(getText("select.option.all"));
/* 122 */       list.add(0, cv);
/* 123 */       return list;
/*     */     } catch (DaoException e) {
/* 125 */       e.printStackTrace();
/* 126 */     }return null;
/*     */   }
/*     */ 
/*     */   public List getAllCompanyType()
/*     */   {
/*     */     try
/*     */     {
/* 135 */       List list = this.codeValueManager.loadByKey("parentCV", ((CodeValue)this.codeValueManager.loadByKey("code", "003").get(0)).getId());
/*     */ 
/* 138 */       CodeValue cv = new CodeValue();
/* 139 */       cv.setId(Long.valueOf(0L));
/* 140 */       cv.setName(getText("select.option.all"));
/* 141 */       list.add(0, cv);
/* 142 */       return list;
/*     */     } catch (DaoException e) {
/* 144 */       e.printStackTrace();
/* 145 */     }return null;
/*     */   }
/*     */ 
/*     */   private String disable()
/*     */   {
/*     */     try
/*     */     {
/* 155 */       boolean flag = this.supplierManager.disabledAllSupplier(this.supplier);
/* 156 */       if (flag)
/* 157 */         addActionMessage(getText("disabled.supplier.success"));
/*     */       else
/* 159 */         addActionMessage(getText("disabled.supplier.error"));
/*     */     }
/*     */     catch (Exception e) {
/* 162 */       e.printStackTrace();
/* 163 */       return "error";
/*     */     }
/* 165 */     return "success";
/*     */   }
/*     */ 
/*     */   private String enabled() {
/*     */     try {
/* 170 */       this.supplierManager.enabledAllSupplier(this.supplier);
/*     */     } catch (Exception e) {
/* 172 */       addActionMessage(getText("enabled.supplier.error"));
/* 173 */       e.printStackTrace();
/* 174 */       return "error";
/*     */     }
/* 176 */     addActionMessage(getText("enabled.supplier.success"));
/* 177 */     return "success";
/*     */   }
/*     */ 
/*     */   private String delete()
/*     */   {
/*     */     try
/*     */     {
/* 186 */       this.supplierManager.deleteAllSuppliers(this.supplier);
/*     */     } catch (Exception e) {
/* 188 */       addActionMessage(getText("supplier.delete.error"));
/* 189 */       return "error";
/*     */     }
/* 191 */     addActionMessage(getText("supplier.delete.success"));
/* 192 */     return "success";
/*     */   }
/*     */   protected String getAdapterName() {
/* 195 */     return "suppliers";
/*     */   }
/*     */ 
/*     */   public List<Supplier> getSupplier() {
/* 199 */     return this.supplier;
/*     */   }
/*     */ 
/*     */   public void setSupplier(List<Supplier> supplier) {
/* 203 */     this.supplier = supplier;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.supplier.ListSupplierAction
 * JD-Core Version:    0.6.2
 */
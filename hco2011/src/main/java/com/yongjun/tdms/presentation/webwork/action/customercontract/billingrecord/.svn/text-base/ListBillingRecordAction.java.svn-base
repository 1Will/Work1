/*     */ package com.yongjun.tdms.presentation.webwork.action.customercontract.billingrecord;
/*     */ 
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.customercontract.billingrecord.BillingRecord;
/*     */ import com.yongjun.tdms.service.customercontract.billingrecord.BillingRecordManager;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ListBillingRecordAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 675686785046421962L;
/*     */   private final BillingRecordManager billingRecordManager;
/*     */   private List<BillingRecord> billingRecords;
/*     */ 
/*     */   public ListBillingRecordAction(BillingRecordManager billingRecordManager)
/*     */   {
/*  36 */     this.billingRecordManager = billingRecordManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  44 */     if (hasIds("billingRecordIds"))
/*  45 */       this.billingRecords = this.billingRecordManager.loadAllBillingRecord(getIds("billingRecordIds"));
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  55 */     return "billingRecords";
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/*  66 */     if (isDisabled()) {
/*  67 */       disabled();
/*     */     }
/*  69 */     if (isEnable()) {
/*  70 */       enabled();
/*     */     }
/*  72 */     if (isDelete()) {
/*  73 */       delete();
/*     */     }
/*  75 */     return "success";
/*     */   }
/*     */ 
/*     */   public String delete()
/*     */   {
/*     */     try
/*     */     {
/*  85 */       this.billingRecordManager.deleteAllBillingRecord(this.billingRecords);
/*  86 */       addActionMessage(getText("billingRecord.delete.success"));
/*  87 */       return "success";
/*     */     } catch (RuntimeException e) {
/*  89 */       addActionMessage(getText("billingRecord.delete.error"));
/*  90 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String disabled()
/*     */   {
/*     */     try
/*     */     {
/* 100 */       this.billingRecordManager.disabledAllBillingRecord(this.billingRecords);
/* 101 */       addActionMessage(getText("billingRecord.disabled.success"));
/* 102 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 104 */       addActionMessage(getText("billingRecord.disabled.error"));
/* 105 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String enabled()
/*     */   {
/*     */     try
/*     */     {
/* 115 */       this.billingRecordManager.enabledAllBillingRecord(this.billingRecords);
/* 116 */       addActionMessage(getText("billingRecord.enabled.success"));
/* 117 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 119 */       e.printStackTrace();
/* 120 */       addActionMessage(getText("billingRecord.enabled.error"));
/* 121 */     }return "error";
/*     */   }
/*     */ 
/*     */   public List<BillingRecord> getBillingRecords()
/*     */   {
/* 131 */     return this.billingRecords;
/*     */   }
/*     */ 
/*     */   public void setBillingRecords(List<BillingRecord> billingRecords)
/*     */   {
/* 140 */     this.billingRecords = billingRecords;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.customercontract.billingrecord.ListBillingRecordAction
 * JD-Core Version:    0.6.2
 */
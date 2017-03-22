/*     */ package com.yongjun.tdms.presentation.webwork.action.CustomerRelationship.additionalInfo;
/*     */ 
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.additionalInfo.CusAdditionalInfo;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
/*     */ import com.yongjun.tdms.service.CustomerRelationship.additionalInfo.CusAdditionalInfoManager;
/*     */ import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Arrays;
/*     */ 
/*     */ public class EditAdditionalInfoAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final CusAdditionalInfoManager cusAdditionalInfoManager;
/*     */   private final CustomerInfoManager customerInfoManager;
/*     */   private CusAdditionalInfo cusAdditionalInfo;
/*     */   private CustomerInfo customerInfo;
/*     */ 
/*     */   public EditAdditionalInfoAction(CusAdditionalInfoManager cusAdditionalInfoManager, CustomerInfoManager customerInfoManager)
/*     */   {
/*  56 */     this.cusAdditionalInfoManager = cusAdditionalInfoManager;
/*  57 */     this.customerInfoManager = customerInfoManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  66 */     if (hasId("customerInfo.id")) {
/*  67 */       this.customerInfo = this.customerInfoManager.loadCustomerInfo(getId("customerInfo.id"));
/*     */     }
/*     */ 
/*  70 */     if ((null != this.customerInfo) && (null != this.customerInfo.getAdditional())) {
/*  71 */       this.cusAdditionalInfo = this.customerInfo.getAdditional();
/*     */     } else {
/*  73 */       this.cusAdditionalInfo = new CusAdditionalInfo();
/*  74 */       this.cusAdditionalInfo.setCi(this.customerInfo);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/*  84 */     boolean isNew = this.cusAdditionalInfo.isNew();
/*  85 */     System.out.println(isNew);
/*     */     try {
/*  87 */       this.cusAdditionalInfoManager.storeCusAdditionalInfo(this.cusAdditionalInfo);
/*  88 */       if (null != this.customerInfo) {
/*  89 */         this.customerInfo.setAdditional(this.cusAdditionalInfo);
/*  90 */         this.customerInfoManager.storeCustomerInfo(this.customerInfo);
/*     */       }
/*     */     } catch (Exception e) {
/*  93 */       e.printStackTrace();
/*  94 */       if (isNew) {
/*  95 */         addActionMessage(getText("additionalInfo.add.error", Arrays.asList(new Object[] { this.cusAdditionalInfo.getLicenseNumber() })));
/*     */ 
/*  97 */         return "error";
/*     */       }
/*  99 */       addActionMessage(getText("additionalInfo.edit.error", Arrays.asList(new Object[] { this.cusAdditionalInfo.getLicenseNumber() })));
/*     */ 
/* 101 */       return "error";
/*     */     }
/*     */ 
/* 104 */     if (isNew) {
/* 105 */       addActionMessage(getText("additionalInfo.add.success", Arrays.asList(new Object[] { this.cusAdditionalInfo.getLicenseNumber() })));
/*     */ 
/* 107 */       return "new";
/*     */     }
/* 109 */     addActionMessage(getText("additionalInfo.edit.success", Arrays.asList(new Object[] { this.cusAdditionalInfo.getLicenseNumber() })));
/*     */ 
/* 111 */     return "success";
/*     */   }
/*     */ 
/*     */   public CusAdditionalInfo getCusAdditionalInfo() {
/* 115 */     return this.cusAdditionalInfo;
/*     */   }
/*     */   public void setCusAdditionalInfo(CusAdditionalInfo cusAdditionalInfo) {
/* 118 */     this.cusAdditionalInfo = cusAdditionalInfo;
/*     */   }
/*     */   public CustomerInfo getCustomerInfo() {
/* 121 */     return this.customerInfo;
/*     */   }
/*     */   public void setCustomerInfo(CustomerInfo customerInfo) {
/* 124 */     this.customerInfo = customerInfo;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.CustomerRelationship.additionalInfo.EditAdditionalInfoAction
 * JD-Core Version:    0.6.2
 */
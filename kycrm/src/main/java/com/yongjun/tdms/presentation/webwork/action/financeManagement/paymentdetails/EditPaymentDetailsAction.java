/*     */ package com.yongjun.tdms.presentation.webwork.action.financeManagement.paymentdetails;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.financeManagement.paymentdetails.PaymentDetails;
/*     */ import com.yongjun.tdms.model.financeManagement.paymentorder.Paymentorder;
/*     */ import com.yongjun.tdms.service.financeManagement.paymentdetails.PaymentDetailsManager;
/*     */ import com.yongjun.tdms.service.financeManagement.paymentorder.PaymentorderManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class EditPaymentDetailsAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final PaymentDetailsManager paymentDetailsManager;
/*     */   private final PaymentorderManager paymentorderManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private PaymentDetails paymentDetails;
/*     */   private Paymentorder paymentorder;
/*     */   private CodeValue paymentMode;
/*     */   private CodeValue stipple;
/*     */   private CodeValue moneyType;
/*     */   private long paymentorderid;
/*     */ 
/*     */   public EditPaymentDetailsAction(PaymentDetailsManager paymentDetailsManager, PaymentorderManager paymentorderManager, CodeValueManager codeValueManager)
/*     */   {
/*  90 */     this.paymentorderManager = paymentorderManager;
/*  91 */     this.paymentDetailsManager = paymentDetailsManager;
/*  92 */     this.codeValueManager = codeValueManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/* 100 */     if (null == this.paymentDetails) {
/* 101 */       if (hasId("paymentDetails.id")) {
/* 102 */         this.paymentDetails = this.paymentDetailsManager.loadPaymentDetails(getId("paymentDetails.id"));
/*     */       }
/*     */       else {
/* 105 */         this.paymentDetails = new PaymentDetails();
/*     */       }
/*     */     }
/* 108 */     if (null == this.paymentorder) {
/* 109 */       if (hasId("paymentorderid")) {
/* 110 */         this.paymentorder = this.paymentorderManager.loadPaymentorder(getId("paymentorderid"));
/*     */       }
/*     */       else {
/* 113 */         this.paymentorder = null;
/*     */       }
/*     */     }
/* 116 */     if (null == this.paymentMode) {
/* 117 */       if (hasId("paymentMode.id")) {
/* 118 */         this.paymentMode = this.codeValueManager.loadCodeValue(getId("paymentMode.id"));
/*     */       }
/*     */       else {
/* 121 */         this.paymentMode = null;
/*     */       }
/*     */     }
/*     */ 
/* 125 */     if (null == this.stipple) {
/* 126 */       if (hasId("stipple.id")) {
/* 127 */         this.stipple = this.codeValueManager.loadCodeValue(getId("stipple.id"));
/*     */       }
/*     */       else {
/* 130 */         this.stipple = null;

/*     */       }
/*     */     }
/*     */ 
/* 134 */     if (null == this.moneyType)
/* 135 */       if (hasId("moneyType.id")) {
/* 136 */         this.moneyType = this.codeValueManager.loadCodeValue(getId("moneyType.id"));
/*     */       }
/*     */       else
/* 139 */         this.moneyType = null;
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/* 149 */     boolean isNew = this.paymentDetails.isNew();
/*     */     try {
/* 151 */       this.paymentDetails.setMoneyType(this.moneyType);
/* 152 */       this.paymentDetails.setPaymentMode(this.paymentMode);
/* 153 */       this.paymentDetails.setPaymentorder(this.paymentorder);
/* 154 */       this.paymentDetails.setStipple(this.stipple);
/* 155 */       this.paymentDetailsManager.storePaymentDetails(this.paymentDetails);
/* 156 */       if (isNew) {
/* 157 */         addActionMessage(getText("paymentDetails.add.success"));
/* 158 */         return "new";
/*     */       }
/* 160 */       addActionMessage(getText("paymentDetails.edit.success"));
/* 161 */       return "success";
/*     */     }
/*     */     catch (Exception ex)
/*     */     {
/* 165 */       ex.printStackTrace();
/* 166 */       if (isNew) {
/* 167 */         addActionMessage(getText("paymentDetails.add.error"));
/* 168 */         return "new";
/*     */       }
/* 170 */       addActionMessage(getText("paymentDetails.edit.error"));
/* 171 */     }return "success";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllPaymentMode()
/*     */   {
/* 181 */     List codes = null;
/*     */     try {
/* 183 */       codes = new ArrayList();
/* 184 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("046"));
/*     */ 
/* 186 */       if ((null != one) && (one.size() > 0)) {
/* 187 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 189 */         if ((null != list) && (list.size() > 0)) {
/* 190 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 193 */       return codes;
/*     */     } catch (DaoException e) {
/* 195 */       e.printStackTrace();
/*     */     }
/* 197 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllStipple()
/*     */   {
/* 204 */     List codes = null;
/*     */     try {
/* 206 */       codes = new ArrayList();
/* 207 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("074"));
/*     */ 
/* 209 */       if ((null != one) && (one.size() > 0)) {
/* 210 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 212 */         if ((null != list) && (list.size() > 0)) {
/* 213 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 216 */       return codes;
/*     */     } catch (DaoException e) {
/* 218 */       e.printStackTrace();
/*     */     }
/* 220 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllMoneyType()
/*     */   {
/* 227 */     List codes = null;
/*     */     try {
/* 229 */       codes = new ArrayList();
/* 230 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("067"));
/*     */ 
/* 232 */       if ((null != one) && (one.size() > 0)) {
/* 233 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 235 */         if ((null != list) && (list.size() > 0)) {
/* 236 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 239 */       return codes;
/*     */     } catch (DaoException e) {
/* 241 */       e.printStackTrace();
/*     */     }
/* 243 */     return codes;
/*     */   }
/*     */ 
/*     */   public long getPaymentorderid()
/*     */   {
/* 251 */     return this.paymentorderid;
/*     */   }
/*     */ 
/*     */   public void setPaymentorderid(long paymentorderid)
/*     */   {
/* 258 */     this.paymentorderid = paymentorderid;
/*     */   }
/*     */ 
/*     */   public PaymentDetails getPaymentDetails()
/*     */   {
/* 266 */     return this.paymentDetails;
/*     */   }
/*     */ 
/*     */   public void setPaymentDetails(PaymentDetails paymentDetails)
/*     */   {
/* 273 */     this.paymentDetails = paymentDetails;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.financeManagement.paymentdetails.EditPaymentDetailsAction
 * JD-Core Version:    0.6.2
 */
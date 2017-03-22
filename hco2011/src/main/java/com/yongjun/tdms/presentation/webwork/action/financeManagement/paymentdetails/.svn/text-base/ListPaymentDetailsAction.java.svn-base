/*     */ package com.yongjun.tdms.presentation.webwork.action.financeManagement.paymentdetails;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.financeManagement.paymentdetails.PaymentDetails;
/*     */ import com.yongjun.tdms.service.financeManagement.paymentdetails.PaymentDetailsManager;
/*     */ import com.yongjun.tdms.service.financeManagement.paymentorder.PaymentorderManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ListPaymentDetailsAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final PaymentDetailsManager paymentDetailsManager;
/*     */   private final PaymentorderManager paymentorderManager;
/*     */   private final CodeValueManager codeValueManager;
/*  59 */   private List<PaymentDetails> paymentDetailses = null;
/*     */ 
/*     */   public ListPaymentDetailsAction(PaymentDetailsManager paymentDetailsManager, PaymentorderManager paymentorderManager, CodeValueManager codeValueManager)
/*     */   {
/*  70 */     this.paymentDetailsManager = paymentDetailsManager;
/*  71 */     this.paymentorderManager = paymentorderManager;
/*  72 */     this.codeValueManager = codeValueManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  82 */     if ((null == this.paymentDetailses) && 
/*  83 */       (hasIds("paymentDetailsIds")))
/*  84 */       this.paymentDetailses = this.paymentDetailsManager.loadAllPaymentDetails(getIds("paymentDetailsIds"));
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  96 */     return "paymentDetailsHQL";
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/* 107 */     if (isDisabled()) {
/* 108 */       disabled();
/*     */     }
/* 110 */     if (isEnable()) {
/* 111 */       enabled();
/*     */     }
/* 113 */     if (isDelete()) {
/* 114 */       delete();
/*     */     }
/* 116 */     return "success";
/*     */   }
/*     */ 
/*     */   public String delete()
/*     */   {
/*     */     try
/*     */     {
/* 126 */       this.paymentDetailsManager.deleteAllPaymentDetails(this.paymentDetailses);
/* 127 */       addActionMessage(getText("paymentDetails.delete.success"));
/* 128 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 130 */       addActionMessage(getText("paymentDetails.delete.error"));
/* 131 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String disabled()
/*     */   {
/*     */     try
/*     */     {
/* 142 */       this.paymentDetailsManager.disabledAllPaymentDetails(this.paymentDetailses);
/* 143 */       addActionMessage(getText("paymentDetails.disabled.success"));
/* 144 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 146 */       addActionMessage(getText("paymentDetails.disabled.error"));
/* 147 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String enabled()
/*     */   {
/*     */     try
/*     */     {
/* 157 */       this.paymentDetailsManager.enabledAllPaymentDetails(this.paymentDetailses);
/* 158 */       addActionMessage(getText("paymentDetails.enabled.success"));
/* 159 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 161 */       e.printStackTrace();
/* 162 */       addActionMessage(getText("paymentDetails.enabled.error"));
/* 163 */     }return "error";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllPaymentMode()
/*     */   {
/* 173 */     List codes = null;
/*     */     try {
/* 175 */       codes = new ArrayList();
/* 176 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("046"));
/*     */ 
/* 178 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 180 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 182 */         if ((null != list) && (list.size() > 0)) {
/* 183 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 186 */       CodeValue cv = new CodeValue();
/* 187 */       cv.setId(null);
/* 188 */       cv.setName(getText("所有"));
/* 189 */       codes.add(0, cv);
/* 190 */       return codes;
/*     */     } catch (DaoException e) {
/* 192 */       e.printStackTrace();
/*     */ 
/* 194 */       CodeValue cv = new CodeValue();
/* 195 */       cv.setId(null);
/* 196 */       cv.setName(getText("所有"));
/* 197 */       codes.add(0, cv);
/* 198 */     }return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllStipple()
/*     */   {
/* 205 */     List codes = null;
/*     */     try {
/* 207 */       codes = new ArrayList();
/* 208 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("074"));
/*     */ 
/* 210 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 212 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 214 */         if ((null != list) && (list.size() > 0)) {
/* 215 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 218 */       CodeValue cv = new CodeValue();
/* 219 */       cv.setId(null);
/* 220 */       cv.setName(getText("所有"));
/* 221 */       codes.add(0, cv);
/* 222 */       return codes;
/*     */     } catch (DaoException e) {
/* 224 */       e.printStackTrace();
/*     */ 
/* 226 */       CodeValue cv = new CodeValue();
/* 227 */       cv.setId(null);
/* 228 */       cv.setName(getText("所有"));
/* 229 */       codes.add(0, cv);
/* 230 */     }return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllMoneyType()
/*     */   {
/* 237 */     List codes = null;
/*     */     try {
/* 239 */       codes = new ArrayList();
/* 240 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("067"));
/*     */ 
/* 242 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 244 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 246 */         if ((null != list) && (list.size() > 0)) {
/* 247 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 250 */       CodeValue cv = new CodeValue();
/* 251 */       cv.setId(null);
/* 252 */       cv.setName(getText("所有"));
/* 253 */       codes.add(0, cv);
/* 254 */       return codes;
/*     */     } catch (DaoException e) {
/* 256 */       e.printStackTrace();
/*     */ 
/* 258 */       CodeValue cv = new CodeValue();
/* 259 */       cv.setId(null);
/* 260 */       cv.setName(getText("所有"));
/* 261 */       codes.add(0, cv);
/* 262 */     }return codes;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.financeManagement.paymentdetails.ListPaymentDetailsAction
 * JD-Core Version:    0.6.2
 */
/*     */ package com.yongjun.tdms.presentation.webwork.action.financeManagement.paymentorder;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.financeManagement.paymentorder.Paymentorder;
/*     */ import com.yongjun.tdms.service.financeManagement.paymentorder.PaymentorderManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ListPaymentorderAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final PaymentorderManager paymentorderManager;
/*     */   private final CodeValueManager codeValueManager;
/*  59 */   private List<Paymentorder> paymentorders = null;
/*     */ 
/*     */   public ListPaymentorderAction(PaymentorderManager paymentorderManager, CodeValueManager codeValueManager)
/*     */   {
/*  68 */     this.paymentorderManager = paymentorderManager;
/*  69 */     this.codeValueManager = codeValueManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  79 */     if ((null == this.paymentorders) && 
/*  80 */       (hasIds("paymentorderIds")))
/*  81 */       this.paymentorders = this.paymentorderManager.loadAllPaymentorder(getIds("paymentorderIds"));
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  93 */     return "paymentorderHQL";
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/* 104 */     if (isDisabled()) {
/* 105 */       disabled();
/*     */     }
/* 107 */     if (isEnable()) {
/* 108 */       enabled();
/*     */     }
/* 110 */     if (isDelete()) {
/* 111 */       delete();
/*     */     }
/* 113 */     return "success";
/*     */   }
/*     */ 
/*     */   public String delete()
/*     */   {
/*     */     try
/*     */     {
/* 123 */       this.paymentorderManager.deleteAllPaymentorder(this.paymentorders);
/* 124 */       addActionMessage(getText("paymentorder.delete.success"));
/* 125 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 127 */       addActionMessage(getText("paymentorder.delete.error"));
/* 128 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String disabled()
/*     */   {
/*     */     try
/*     */     {
/* 139 */       this.paymentorderManager.disabledAllPaymentorder(this.paymentorders);
/* 140 */       addActionMessage(getText("paymentorder.disabled.success"));
/* 141 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 143 */       addActionMessage(getText("paymentorder.disabled.error"));
/* 144 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String enabled()
/*     */   {
/*     */     try
/*     */     {
/* 154 */       this.paymentorderManager.enabledAllPaymentorder(this.paymentorders);
/* 155 */       addActionMessage(getText("paymentorder.enabled.success"));
/* 156 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 158 */       e.printStackTrace();
/* 159 */       addActionMessage(getText("paymentorder.enabled.error"));
/* 160 */     }return "error";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllProduceType()
/*     */   {
/* 168 */     List codes = null;
/*     */     try {
/* 170 */       codes = new ArrayList();
/* 171 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("072"));
/*     */ 
/* 173 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 175 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 177 */         if ((null != list) && (list.size() > 0)) {
/* 178 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 181 */       CodeValue cv = new CodeValue();
/* 182 */       cv.setId(null);
/* 183 */       cv.setName(getText("所有"));
/* 184 */       codes.add(0, cv);
/* 185 */       return codes;
/*     */     } catch (DaoException e) {
/* 187 */       e.printStackTrace();
/*     */ 
/* 189 */       CodeValue cv = new CodeValue();
/* 190 */       cv.setId(null);
/* 191 */       cv.setName(getText("所有"));
/* 192 */       codes.add(0, cv);
/* 193 */     }return codes;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.financeManagement.paymentorder.ListPaymentorderAction
 * JD-Core Version:    0.6.2
 */
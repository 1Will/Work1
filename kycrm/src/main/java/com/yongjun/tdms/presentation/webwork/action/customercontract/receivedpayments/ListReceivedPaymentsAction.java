/*     */ package com.yongjun.tdms.presentation.webwork.action.customercontract.receivedpayments;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.customercontract.receivedpayments.ReceivedPayments;
/*     */ import com.yongjun.tdms.service.customercontract.receivedpayments.ReceivedPaymentsManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ListReceivedPaymentsAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 675686785046421962L;
/*     */   private final ReceivedPaymentsManager receivedPaymentsManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private List<ReceivedPayments> receivedPaymentses;
/*     */ 
/*     */   public ListReceivedPaymentsAction(ReceivedPaymentsManager receivedPaymentsManager, CodeValueManager codeValueManager)
/*     */   {
/*  47 */     this.receivedPaymentsManager = receivedPaymentsManager;
/*  48 */     this.codeValueManager = codeValueManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  56 */     if (hasIds("receivedPaymentsIds"))
/*  57 */       this.receivedPaymentses = this.receivedPaymentsManager.loadAllReceivedPayments(getIds("receivedPaymentsIds"));
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  67 */     return "receivedPaymentses";
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/*  78 */     if (isDisabled()) {
/*  79 */       disabled();
/*     */     }
/*  81 */     if (isEnable()) {
/*  82 */       enabled();
/*     */     }
/*  84 */     if (isDelete()) {
/*  85 */       delete();
/*     */     }
/*  87 */     return "success";
/*     */   }
/*     */ 
/*     */   public String delete()
/*     */   {
/*     */     try
/*     */     {
/*  97 */       this.receivedPaymentsManager.deleteAllReceivedPayments(this.receivedPaymentses);
/*  98 */       addActionMessage(getText("receivedPayments.delete.success"));
/*  99 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 101 */       addActionMessage(getText("receivedPayments.delete.error"));
/* 102 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String disabled()
/*     */   {
/*     */     try
/*     */     {
/* 112 */       this.receivedPaymentsManager.disabledAllReceivedPayments(this.receivedPaymentses);
/* 113 */       addActionMessage(getText("receivedPayments.disabled.success"));
/* 114 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 116 */       addActionMessage(getText("receivedPayments.disabled.error"));
/* 117 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String enabled()
/*     */   {
/*     */     try
/*     */     {
/* 127 */       this.receivedPaymentsManager.enabledAllReceivedPayments(this.receivedPaymentses);
/* 128 */       addActionMessage(getText("receivedPayments.enabled.success"));
/* 129 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 131 */       e.printStackTrace();
/* 132 */       addActionMessage(getText("receivedPayments.enabled.error"));
/* 133 */     }return "error";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllPayment()
/*     */   {
/* 141 */     List codes = null;
/*     */     try {
/* 143 */       codes = new ArrayList();
/* 144 */       List one = this.codeValueManager.loadByKey("code", Long.valueOf("065"));
/*     */ 
/* 146 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 148 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 150 */         if ((null != list) && (list.size() > 0)) {
/* 151 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 154 */       CodeValue cv = new CodeValue();
/* 155 */       cv.setId(Long.valueOf(-1L));
/* 156 */       cv.setName(getText("select.option.all"));
/* 157 */       codes.add(0, cv);
/* 158 */       return codes;
/*     */     } catch (DaoException e) {
/* 160 */       e.printStackTrace();
/*     */     }
/* 162 */     return codes;
/*     */   }
/*     */ 
/*     */   protected Map getRequestParameterMap()
/*     */   {
/* 171 */     Map map = super.getRequestParameterMap();
/* 172 */     if (hasId("isOrNot")) {
/* 173 */       if (getId("isOrNot").equals(Long.valueOf("2")))
/* 174 */         map.put("cbv", null);
/*     */       else {
/* 176 */         map.put("cbv", Long.valueOf(getId("isOrNot").longValue()));
/*     */       }
/*     */     }
/* 179 */     return map;
/*     */   }
/*     */ 
/*     */   public List<ReceivedPayments> getReceivedPaymentses()
/*     */   {
/* 188 */     return this.receivedPaymentses;
/*     */   }
/*     */ 
/*     */   public void setReceivedPaymentses(List<ReceivedPayments> receivedPaymentses)
/*     */   {
/* 197 */     this.receivedPaymentses = receivedPaymentses;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.customercontract.receivedpayments.ListReceivedPaymentsAction
 * JD-Core Version:    0.6.2
 */
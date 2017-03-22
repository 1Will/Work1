/*     */ package com.yongjun.tdms.presentation.webwork.action.customerservicemanagement.customerservice;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.customerservicemanagement.customerservice.CustomerService;
/*     */ import com.yongjun.tdms.service.customerservicemanagement.customerservice.CustomerServiceManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ListCustomerServiceAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final CustomerServiceManager customerServiceManager;
/*     */   private final CodeValueManager codeValueManager;
/*  54 */   private List<CustomerService> customerServices = null;
/*     */ 
/*     */   public ListCustomerServiceAction(CustomerServiceManager customerServiceManager, CodeValueManager codeValueManager)
/*     */   {
/*  63 */     this.customerServiceManager = customerServiceManager;
/*  64 */     this.codeValueManager = codeValueManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  74 */     if ((null == this.customerServices) && 
/*  75 */       (hasIds("customerServiceIds")))
/*  76 */       this.customerServices = this.customerServiceManager.loadAllCustomerService(getIds("customerServiceIds"));
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  88 */     return "customerServiceHQL";
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/*  99 */     if (isDisabled()) {
/* 100 */       disabled();
/*     */     }
/* 102 */     if (isEnable()) {
/* 103 */       enabled();
/*     */     }
/* 105 */     if (isDelete()) {
/* 106 */       delete();
/*     */     }
/* 108 */     return "success";
/*     */   }
/*     */ 
/*     */   public String delete()
/*     */   {
/*     */     try
/*     */     {
/* 118 */       this.customerServiceManager.deleteAllCustomerService(this.customerServices);
/* 119 */       addActionMessage(getText("customerService.delete.success"));
/* 120 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 122 */       addActionMessage(getText("customerService.delete.error"));
/* 123 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String disabled()
/*     */   {
/*     */     try
/*     */     {
/* 134 */       this.customerServiceManager.disabledAllCustomerService(this.customerServices);
/* 135 */       addActionMessage(getText("customerService.disabled.success"));
/* 136 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 138 */       addActionMessage(getText("customerService.disabled.error"));
/* 139 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String enabled()
/*     */   {
/*     */     try
/*     */     {
/* 149 */       this.customerServiceManager.enabledAllCustomerService(this.customerServices);
/* 150 */       addActionMessage(getText("customerService.enabled.success"));
/* 151 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 153 */       e.printStackTrace();
/* 154 */       addActionMessage(getText("customerService.enabled.error"));
/* 155 */     }return "error";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllServiceType()
/*     */   {
/* 164 */     List codes = null;
/*     */     try {
/* 166 */       codes = new ArrayList();
/* 167 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("055"));
/*     */ 
/* 169 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 171 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 173 */         if ((null != list) && (list.size() > 0)) {
/* 174 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 177 */       CodeValue cv = new CodeValue();
/* 178 */       cv.setId(null);
/* 179 */       cv.setName(getText("所有"));
/* 180 */       codes.add(0, cv);
/* 181 */       return codes;
/*     */     } catch (DaoException e) {
/* 183 */       e.printStackTrace();
/*     */ 
/* 185 */       CodeValue cv = new CodeValue();
/* 186 */       cv.setId(null);
/* 187 */       cv.setName(getText("所有"));
/* 188 */       codes.add(0, cv);
/* 189 */     }return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllServiceWay()
/*     */   {
/* 197 */     List codes = null;
/*     */     try {
/* 199 */       codes = new ArrayList();
/* 200 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("056"));
/*     */ 
/* 202 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 204 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 206 */         if ((null != list) && (list.size() > 0)) {
/* 207 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 210 */       CodeValue cv = new CodeValue();
/* 211 */       cv.setId(null);
/* 212 */       cv.setName(getText("所有"));
/* 213 */       codes.add(0, cv);
/* 214 */       return codes;
/*     */     } catch (DaoException e) {
/* 216 */       e.printStackTrace();
/*     */ 
/* 218 */       CodeValue cv = new CodeValue();
/* 219 */       cv.setId(null);
/* 220 */       cv.setName(getText("所有"));
/* 221 */       codes.add(0, cv);
/* 222 */     }return codes;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.customerservicemanagement.customerservice.ListCustomerServiceAction
 * JD-Core Version:    0.6.2
 */
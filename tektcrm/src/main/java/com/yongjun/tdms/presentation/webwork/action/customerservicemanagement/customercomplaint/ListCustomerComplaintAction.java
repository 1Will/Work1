/*     */ package com.yongjun.tdms.presentation.webwork.action.customerservicemanagement.customercomplaint;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.customerservicemanagement.customercomplaint.CustomerComplaint;
/*     */ import com.yongjun.tdms.service.customerservicemanagement.customercomplaint.CustomerComplaintManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ListCustomerComplaintAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final CustomerComplaintManager customerComplaintManager;
/*     */   private final CodeValueManager codeValueManager;
/*  54 */   private List<CustomerComplaint> customerComplaints = null;
/*     */ 
/*     */   public ListCustomerComplaintAction(CustomerComplaintManager customerComplaintManager, CodeValueManager codeValueManager)
/*     */   {
/*  62 */     this.customerComplaintManager = customerComplaintManager;
/*  63 */     this.codeValueManager = codeValueManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  73 */     if ((null == this.customerComplaints) && 
/*  74 */       (hasIds("customerComplaintIds")))
/*  75 */       this.customerComplaints = this.customerComplaintManager.loadAllCustomerComplaint(getIds("customerComplaintIds"));
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  87 */     return "customerComplaintHQL";
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/*  98 */     if (isDisabled()) {
/*  99 */       disabled();
/*     */     }
/* 101 */     if (isEnable()) {
/* 102 */       enabled();
/*     */     }
/* 104 */     if (isDelete()) {
/* 105 */       delete();
/*     */     }
/* 107 */     return "success";
/*     */   }
/*     */ 
/*     */   public String delete()
/*     */   {
/*     */     try
/*     */     {
/* 117 */       this.customerComplaintManager.deleteAllCustomerComplaint(this.customerComplaints);
/* 118 */       addActionMessage(getText("customerComplaint.delete.success"));
/* 119 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 121 */       addActionMessage(getText("customerComplaint.delete.error"));
/* 122 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String disabled()
/*     */   {
/*     */     try
/*     */     {
/* 133 */       this.customerComplaintManager.disabledAllCustomerComplaint(this.customerComplaints);
/* 134 */       addActionMessage(getText("customerComplaint.disabled.success"));
/* 135 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 137 */       addActionMessage(getText("customerComplaint.disabled.error"));
/* 138 */     }return "error";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllComplaintType()
/*     */   {
/* 146 */     List codes = null;
/*     */     try {
/* 148 */       codes = new ArrayList();
/* 149 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("058"));
/*     */ 
/* 151 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 153 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 155 */         if ((null != list) && (list.size() > 0)) {
/* 156 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 159 */       CodeValue cv = new CodeValue();
/* 160 */       cv.setId(null);
/* 161 */       cv.setName(getText("所有"));
/* 162 */       codes.add(0, cv);
/* 163 */       return codes;
/*     */     } catch (DaoException e) {
/* 165 */       e.printStackTrace();
/*     */ 
/* 167 */       CodeValue cv = new CodeValue();
/* 168 */       cv.setId(null);
/* 169 */       cv.setName(getText("所有"));
/* 170 */       codes.add(0, cv);
/* 171 */     }return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllUrgencyDegree()
/*     */   {
/* 179 */     List codes = null;
/*     */     try {
/* 181 */       codes = new ArrayList();
/* 182 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("059"));
/*     */ 
/* 184 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 186 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 188 */         if ((null != list) && (list.size() > 0)) {
/* 189 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 192 */       CodeValue cv = new CodeValue();
/* 193 */       cv.setId(null);
/* 194 */       cv.setName(getText("所有"));
/* 195 */       codes.add(0, cv);
/* 196 */       return codes;
/*     */     } catch (DaoException e) {
/* 198 */       e.printStackTrace();
/*     */ 
/* 200 */       CodeValue cv = new CodeValue();
/* 201 */       cv.setId(null);
/* 202 */       cv.setName(getText("所有"));
/* 203 */       codes.add(0, cv);
/* 204 */     }return codes;
/*     */   }
/*     */ 
/*     */   public List<CustomerComplaint> getCustomerComplaints()
/*     */   {
/* 212 */     return this.customerComplaints;
/*     */   }
/*     */ 
/*     */   public void setCustomerComplaints(List<CustomerComplaint> customerComplaints)
/*     */   {
/* 220 */     this.customerComplaints = customerComplaints;
/*     */   }
/*     */ 
/*     */   private String enabled()
/*     */   {
/*     */     try
/*     */     {
/* 229 */       this.customerComplaintManager.enabledAllCustomerComplaint(this.customerComplaints);
/* 230 */       addActionMessage(getText("customerComplaint.enabled.success"));
/* 231 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 233 */       e.printStackTrace();
/* 234 */       addActionMessage(getText("customerComplaint.enabled.error"));
/* 235 */     }return "error";
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.customerservicemanagement.customercomplaint.ListCustomerComplaintAction
 * JD-Core Version:    0.6.2
 */
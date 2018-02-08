/*     */ package com.yongjun.tdms.presentation.webwork.action.COM.contractCustomer;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.COM.contractCustomer.ContractCustomer;
/*     */ import com.yongjun.tdms.service.COM.contractCustomer.ContractCustomerManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ListContractCustomerAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 675686785046421962L;
/*     */   private final ContractCustomerManager contractCustomerManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private List<ContractCustomer> contractCustomers;
/*     */ 
/*     */   public ListContractCustomerAction(ContractCustomerManager contractCustomerManager, CodeValueManager codeValueManager)
/*     */   {
/*  47 */     this.contractCustomerManager = contractCustomerManager;
/*  48 */     this.codeValueManager = codeValueManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  56 */     if (hasIds("contractCustomerIds"))
/*  57 */       this.contractCustomers = this.contractCustomerManager.loadAllContractCustomer(getIds("contractCustomerIds"));
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  67 */     return "contractCustomers";
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
/*  97 */       this.contractCustomerManager.deleteAllContractCustomer(this.contractCustomers);
/*  98 */       addActionMessage(getText("contractCustomer.delete.success"));
/*  99 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 101 */       addActionMessage(getText("contractCustomer.delete.error"));
/* 102 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String disabled()
/*     */   {
/*     */     try
/*     */     {
/* 112 */       this.contractCustomerManager.disabledAllContractCustomers(this.contractCustomers);
/* 113 */       addActionMessage(getText("contractCustomer.disabled.success"));
/* 114 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 116 */       addActionMessage(getText("contractCustomer.disabled.error"));
/* 117 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String enabled()
/*     */   {
/*     */     try
/*     */     {
/* 127 */       this.contractCustomerManager.enabledAllContractCustomers(this.contractCustomers);
/* 128 */       addActionMessage(getText("contractCustomer.enabled.success"));
/* 129 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 131 */       e.printStackTrace();
/* 132 */       addActionMessage(getText("contractCustomer.enabled.error"));
/* 133 */     }return "error";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllPayments()
/*     */   {
/* 141 */     List codes = null;
/*     */     try {
/* 143 */       codes = new ArrayList();
/* 144 */       List one = this.codeValueManager.loadByKey("code", "046");
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
/*     */   public List<CodeValue> getAllTypes()
/*     */   {
/* 169 */     List codes = null;
/*     */     try {
/* 171 */       codes = new ArrayList();
/* 172 */       List one = this.codeValueManager.loadByKey("code", "060");
/*     */ 
/* 174 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 176 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 178 */         if ((null != list) && (list.size() > 0)) {
/* 179 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 182 */       CodeValue cv = new CodeValue();
/* 183 */       cv.setId(Long.valueOf(-1L));
/* 184 */       cv.setName(getText("select.option.all"));
/* 185 */       codes.add(0, cv);
/* 186 */       return codes;
/*     */     } catch (DaoException e) {
/* 188 */       e.printStackTrace();
/*     */     }
/* 190 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllStatus()
/*     */   {
/* 198 */     List codes = null;
/*     */     try {
/* 200 */       codes = new ArrayList();
/* 201 */       List one = this.codeValueManager.loadByKey("code", "082001");
/*     */ 
/* 203 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 205 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 207 */         if ((null != list) && (list.size() > 0)) {
/* 208 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 211 */       CodeValue cv = new CodeValue();
/* 212 */       cv.setId(Long.valueOf(-1L));
/* 213 */       cv.setName(getText("select.option.all"));
/* 214 */       codes.add(0, cv);
/* 215 */       return codes;
/*     */     } catch (DaoException e) {
/* 217 */       e.printStackTrace();
/*     */     }
/* 219 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<ContractCustomer> getContractCustomers()
/*     */   {
/* 228 */     return this.contractCustomers;
/*     */   }
/*     */ 
/*     */   public void setContractCustomers(List<ContractCustomer> contractCustomers)
/*     */   {
/* 237 */     this.contractCustomers = contractCustomers;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.COM.contractCustomer.ListContractCustomerAction
 * JD-Core Version:    0.6.2
 */
/*     */ package com.yongjun.tdms.presentation.webwork.action.expensemanagement.expenseuse;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.security.Department;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
/*     */ import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
/*     */ import com.yongjun.tdms.model.expensemanagement.expenseapply.ExpenseApply;
/*     */ import com.yongjun.tdms.model.expensemanagement.expenseuse.ExpenseUse;
/*     */ import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager;
/*     */ import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
/*     */ import com.yongjun.tdms.service.base.org.DepartmentManager;
/*     */ import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;
/*     */ import com.yongjun.tdms.service.expensemanagement.expenseapply.ExpenseApplyManager;
/*     */ import com.yongjun.tdms.service.expensemanagement.expenseuse.ExpenseUseManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class EditExpenseUseAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final ExpenseUseManager expenseUseManager;
/*     */   private final ExpenseApplyManager expenseApplyManager;
/*     */   private final CustomerInfoManager customerInfoManager;
/*     */   private final ContactArchivesManager contactArchivesManager;
/*     */   private final ContractManagementManager contractManagementManager;
/*     */   private final DepartmentManager departmentManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private ExpenseUse expenseUse;
/*     */   private ExpenseApply expenseApply;
/*     */   private CustomerInfo customerInfo;
/*     */   private ContactArchives linkman;
/*     */   private ContractManagement contractManagement;
/*     */ 
/*     */   public EditExpenseUseAction(ExpenseUseManager expenseUseManager, ExpenseApplyManager expenseApplyManager, CustomerInfoManager customerInfoManager, ContactArchivesManager contactArchivesManager, ContractManagementManager contractManagementManager, DepartmentManager departmentManager, CodeValueManager codeValueManager)
/*     */   {
/* 118 */     this.expenseUseManager = expenseUseManager;
/* 119 */     this.expenseApplyManager = expenseApplyManager;
/* 120 */     this.customerInfoManager = customerInfoManager;
/* 121 */     this.contactArchivesManager = contactArchivesManager;
/* 122 */     this.contractManagementManager = contractManagementManager;
/* 123 */     this.departmentManager = departmentManager;
/* 124 */     this.codeValueManager = codeValueManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/* 132 */     if (null == this.expenseUse) {
/* 133 */       if (hasId("expenseUse.id")) {
/* 134 */         this.expenseUse = this.expenseUseManager.loadExpenseUse(getId("expenseUse.id"));
/*     */       }
/*     */       else {
/* 137 */         this.expenseUse = new ExpenseUse();
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 142 */     if (null == this.expenseApply) {
/* 143 */       if (hasId("expenseApply.id")) {
/* 144 */         this.expenseApply = this.expenseApplyManager.loadExpenseApply(getId("expenseApply.id"));
/*     */       }
/*     */       else {
/* 147 */         this.expenseApply = null;
/*     */       }
/*     */     }
/*     */ 
/* 151 */     if (null == this.customerInfo) {
/* 152 */       if (hasId("customerInfo.id")) {
/* 153 */         this.customerInfo = this.customerInfoManager.loadCustomerInfo(getId("customerInfo.id"));
/*     */       }
/*     */       else {
/* 156 */         this.customerInfo = null;
/*     */       }
/*     */     }
/*     */ 
/* 160 */     if (null == this.linkman) {
/* 161 */       if (hasId("linkman.id")) {
/* 162 */         this.linkman = this.contactArchivesManager.loadContactArchives(getId("linkman.id"));
/*     */       }
/*     */       else {
/* 165 */         this.linkman = null;
/*     */       }
/*     */     }
/* 168 */     if (null == this.contractManagement)
/* 169 */       if (hasId("contractManagement.id")) {
/* 170 */         this.contractManagement = this.contractManagementManager.loadContractManagement(getId("contractManagement.id"));
/*     */       }
/*     */       else
/* 173 */         this.contractManagement = null;
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/* 183 */     boolean isNew = this.expenseUse.isNew();
/*     */     try {
/* 185 */       this.expenseUse.setContractManagement(this.contractManagement);
/* 186 */       this.expenseUse.setCustomerInfo(this.customerInfo);
/* 187 */       this.expenseUse.setExpenseApply(this.expenseApply);
/* 188 */       this.expenseApply.setResidualMoney(this.expenseApply.getApplyMoney() - this.expenseUse.getUsedMoney());
/* 189 */       this.expenseApplyManager.storeExpenseApply(this.expenseApply);
/*     */ 
/* 191 */       this.expenseUse.setLinkman(this.linkman);
/* 192 */       this.expenseUseManager.storeExpenseUse(this.expenseUse);
/* 193 */       if (isNew) {
/* 194 */         addActionMessage(getText("expenseUse.add.success"));
/* 195 */         return "new";
/*     */       }
/* 197 */       addActionMessage(getText("expenseUse.edit.success"));
/* 198 */       return "success";
/*     */     }
/*     */     catch (Exception ex)
/*     */     {
/* 202 */       ex.printStackTrace();
/* 203 */       if (isNew) {
/* 204 */         addActionMessage(getText("expenseUse.add.error"));
/* 205 */         return "new";
/*     */       }
/* 207 */       addActionMessage(getText("expenseUse.edit.error"));
/* 208 */     }return "success";
/*     */   }
/*     */ 
/*     */   public List<Department> getAllDepartment()
/*     */   {
/* 218 */     List depts = this.departmentManager.loadAllDepartments();
/* 219 */     return depts;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllExpenseType()
/*     */   {
/* 226 */     List codes = null;
/*     */     try {
/* 228 */       codes = new ArrayList();
/* 229 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("069"));
/*     */ 
/* 231 */       if ((null != one) && (one.size() > 0)) {
/* 232 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 234 */         if ((null != list) && (list.size() > 0)) {
/* 235 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 238 */       return codes;
/*     */     } catch (DaoException e) {
/* 240 */       e.printStackTrace();
/*     */     }
/* 242 */     return codes;
/*     */   }
/*     */ 
/*     */   public ExpenseUse getExpenseUse()
/*     */   {
/* 250 */     return this.expenseUse;
/*     */   }
/*     */ 
/*     */   public void setExpenseUse(ExpenseUse expenseUse)
/*     */   {
/* 257 */     this.expenseUse = expenseUse;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.expensemanagement.expenseuse.EditExpenseUseAction
 * JD-Core Version:    0.6.2
 */
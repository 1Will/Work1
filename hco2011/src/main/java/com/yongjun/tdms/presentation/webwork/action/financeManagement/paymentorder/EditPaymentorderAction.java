/*     */ package com.yongjun.tdms.presentation.webwork.action.financeManagement.paymentorder;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.security.Department;
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.financeManagement.paymentorder.Paymentorder;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import com.yongjun.tdms.model.supplier.Supplier;
/*     */ import com.yongjun.tdms.service.base.org.DepartmentManager;
/*     */ import com.yongjun.tdms.service.financeManagement.paymentorder.PaymentorderManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
/*     */ import com.yongjun.tdms.service.supplier.SupplierManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class EditPaymentorderAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final PaymentorderManager paymentorderManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private final PersonnelFilesManager personnelFilesManager;
/*     */   private final DepartmentManager departmentManager;
/*     */   private final UserManager userManager;
/*     */   private final SupplierManager supplierManager;
/*  82 */   private Paymentorder paymentorder = null;
/*     */   private Supplier supplier;
/*  91 */   private PersonnelFiles paymentPersion = null;
/*     */   private Department department;
/*     */   private CodeValue produceType;
/*     */ 
/*     */   public EditPaymentorderAction(PaymentorderManager paymentorderManager, CodeValueManager codeValueManager, PersonnelFilesManager personnelFilesManager, DepartmentManager departmentManager, SupplierManager supplierManager, UserManager userManager)
/*     */   {
/* 115 */     this.paymentorderManager = paymentorderManager;
/* 116 */     this.personnelFilesManager = personnelFilesManager;
/* 117 */     this.codeValueManager = codeValueManager;
/* 118 */     this.supplierManager = supplierManager;
/* 119 */     this.departmentManager = departmentManager;
/* 120 */     this.userManager = userManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/* 129 */     if (null == this.paymentorder) {
/* 130 */       if (hasId("paymentorder.id")) {
/* 131 */         this.paymentorder = this.paymentorderManager.loadPaymentorder(getId("paymentorder.id"));
/*     */       }
/*     */       else {
/* 134 */         this.paymentorder = new Paymentorder();
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 139 */     if (null == this.department) {
/* 140 */       if (hasId("department.id")) {
/* 141 */         this.department = this.departmentManager.loadDepartment(getId("department.id"));
/*     */       }
/*     */       else {
/* 144 */         this.department = null;
/*     */       }
/*     */     }
/*     */ 
/* 148 */     if (null == this.paymentPersion) {
/* 149 */       if (hasId("paymentPersion.id")) {
/* 150 */         this.paymentPersion = this.personnelFilesManager.loadPersonnel(getId("paymentPersion.id"));
/*     */       }
/*     */       else {
/* 153 */         this.paymentPersion = null;
/*     */       }
/*     */     }
/*     */ 
/* 157 */     if (null == this.produceType) {
/* 158 */       if (hasId("produceType.id")) {
/* 159 */         this.produceType = this.codeValueManager.loadCodeValue(getId("produceType.id"));
/*     */       }
/*     */       else {
/* 162 */         this.produceType = null;
/*     */       }
/*     */     }
/*     */ 
/* 166 */     if (null == this.supplier)
/* 167 */       if (hasId("supplier.id")) {
/* 168 */         this.supplier = this.supplierManager.loadSupplier(getId("supplier.id"));
/*     */       }
/*     */       else
/* 171 */         this.supplier = null;
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/* 181 */     boolean isNew = this.paymentorder.isNew();
/* 182 */     this.paymentorder.setOrganization(this.userManager.getOrganization());
/* 183 */     if (isNew) {
/* 184 */       String code = autoCompleteCode();
/* 185 */       this.paymentorder.setCode(code);
/*     */     }
/*     */     try {
/* 188 */       this.paymentorder.setDepartment(this.department);
/* 189 */       this.paymentorder.setPaymentPersion(this.paymentPersion);
/* 190 */       this.paymentorder.setSupplier(this.supplier);
/* 191 */       this.paymentorder.setProduceType(this.produceType);
/* 192 */       this.paymentorderManager.storePaymentorder(this.paymentorder);
/* 193 */       if (isNew) {
/* 194 */         addActionMessage(getText("paymentorder.add.success"));
/* 195 */         return "new";
/*     */       }
/* 197 */       addActionMessage(getText("paymentorder.edit.success"));
/* 198 */       return "success";
/*     */     }
/*     */     catch (Exception ex)
/*     */     {
/* 202 */       ex.printStackTrace();
/* 203 */       if (isNew) {
/* 204 */         addActionMessage(getText("paymentorder.add.error"));
/* 205 */         return "new";
/*     */       }
/* 207 */       addActionMessage(getText("paymentorder.edit.error"));
/* 208 */     }return "success";
/*     */   }
/*     */ 
/*     */   public List<Department> getAllDepartment()
/*     */   {
/* 217 */     List depts = this.departmentManager.loadAllDepartments();
/* 218 */     return depts;
/*     */   }
/*     */ 
/*     */   public String autoCompleteCode()
/*     */   {
/* 226 */     String prefix = "FKD";
/* 227 */     String maxCode = this.paymentorderManager.getMaxPFCode(prefix);
/* 228 */     if (null != maxCode) {
/* 229 */       int num = Integer.parseInt(maxCode) + 1;
/* 230 */       if (num < 10)
/* 231 */         return prefix + "-00000" + num;
/* 232 */       if (num < 100)
/* 233 */         return prefix + "-0000" + num;
/* 234 */       if (num < 1000)
/* 235 */         return prefix + "-000" + num;
/* 236 */       if (num < 10000)
/* 237 */         return prefix + "-00" + num;
/* 238 */       if (num < 100000) {
/* 239 */         return prefix + "-0" + num;
/*     */       }
/* 241 */       return prefix + "-" + num;
/*     */     }
/*     */ 
/* 244 */     return prefix + "-000001";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllProduceType()
/*     */   {
/* 254 */     List codes = null;
/*     */     try {
/* 256 */       codes = new ArrayList();
/* 257 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("072"));
/*     */ 
/* 259 */       if ((null != one) && (one.size() > 0)) {
/* 260 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 262 */         if ((null != list) && (list.size() > 0)) {
/* 263 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 266 */       return codes;
/*     */     } catch (DaoException e) {
/* 268 */       e.printStackTrace();
/*     */     }
/* 270 */     return codes;
/*     */   }
/*     */ 
/*     */   public User getUser()
/*     */   {
/* 277 */     return this.userManager.getUser();
/*     */   }
/*     */ 
/*     */   public PersonnelFiles getPersonnelF()
/*     */     throws Exception
/*     */   {
/* 285 */     List pfs = this.personnelFilesManager.loadByKey("code", this.userManager.getUser().getCode());
/*     */ 
/* 287 */     if ((null != pfs) && (pfs.size() > 0)) {
/* 288 */       return (PersonnelFiles)pfs.get(0);
/*     */     }
/* 290 */     return null;
/*     */   }
/*     */ 
/*     */   public Paymentorder getPaymentorder()
/*     */   {
/* 298 */     return this.paymentorder;
/*     */   }
/*     */ 
/*     */   public void setPaymentorder(Paymentorder paymentorder)
/*     */   {
/* 305 */     this.paymentorder = paymentorder;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.financeManagement.paymentorder.EditPaymentorderAction
 * JD-Core Version:    0.6.2
 */
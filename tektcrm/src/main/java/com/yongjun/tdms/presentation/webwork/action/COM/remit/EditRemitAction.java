/*     */ package com.yongjun.tdms.presentation.webwork.action.COM.remit;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.COM.co.Co;
/*     */ import com.yongjun.tdms.model.COM.contractCustomer.ContractCustomer;
/*     */ import com.yongjun.tdms.model.COM.remit.Remit;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import com.yongjun.tdms.service.COM.co.CoManager;
/*     */ import com.yongjun.tdms.service.COM.contractCustomer.ContractCustomerManager;
/*     */ import com.yongjun.tdms.service.COM.remit.RemitManager;
/*     */ import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class EditRemitAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 612315316215110285L;
/*     */   private final RemitManager remitManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private final PersonnelFilesManager personnelFilesManager;
/*     */   private final CustomerInfoManager customerInfoManager;
/*     */   private final CoManager coManager;
/*     */   private ContractCustomerManager contractCustomerManager;
/*     */   private Remit remit;
/*     */ 
/*     */   public EditRemitAction(RemitManager remitManager, CodeValueManager codeValueManager, PersonnelFilesManager personnelFilesManager, CustomerInfoManager customerInfoManager, CoManager coManager, ContractCustomerManager contractCustomerManager)
/*     */   {
/*  79 */     this.remitManager = remitManager;
/*  80 */     this.codeValueManager = codeValueManager;
/*  81 */     this.personnelFilesManager = personnelFilesManager;
/*  82 */     this.customerInfoManager = customerInfoManager;
/*  83 */     this.coManager = coManager;
/*  84 */     this.contractCustomerManager = contractCustomerManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  92 */     if (hasId("remit.id"))
/*  93 */       this.remit = this.remitManager.loadRemit(getId("remit.id"));
/*     */     else
/*  95 */       this.remit = new Remit();
/*     */   }
/*     */ 
/*     */   public String save()
/*     */     throws Exception
/*     */   {
/* 105 */     boolean isNew = this.remit.isNew();
/*     */ 
/* 108 */     if (!StringUtils.isEmpty(this.request.getParameter("type.id"))) {
/* 109 */       this.remit.setType(this.codeValueManager.loadCodeValue(Long.valueOf(this.request.getParameter("type.id"))));
/*     */     }
/*     */ 
/* 114 */     if (!StringUtils.isEmpty(this.request.getParameter("billing.id"))) {
/* 115 */       this.remit.setBilling(this.codeValueManager.loadCodeValue(Long.valueOf(this.request.getParameter("billing.id"))));
/*     */     }
/*     */ 
/* 119 */     if (!StringUtils.isEmpty(this.request.getParameter("customer.id"))) {
/* 120 */       CustomerInfo customer = this.customerInfoManager.loadCustomerInfo(Long.valueOf(this.request.getParameter("customer.id")));
/*     */ 
/* 122 */       if (null != customer) {
/* 123 */         this.remit.setCustomerInfo(customer);
/*     */       }
/*     */     }
/*     */ 
/* 127 */     if (!StringUtils.isEmpty(this.request.getParameter("salesman.id"))) {
/* 128 */       PersonnelFiles salesman = this.personnelFilesManager.loadPersonnel(Long.valueOf(this.request.getParameter("salesman.id")));
/*     */ 
/* 130 */       if (null != salesman) {
/* 131 */         this.remit.setSaleman(salesman);
/*     */       }
/*     */     }
/*     */ 
/* 135 */     if (!StringUtils.isEmpty(this.request.getParameter("co.id"))) {
/* 136 */       Co co = this.coManager.loadCo(Long.valueOf(this.request.getParameter("co.id")));
/*     */ 
/* 138 */       if (null != co) {
/* 139 */         this.remit.setCo(co);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 144 */     if (!StringUtils.isEmpty(this.request.getParameter("cc.id"))) {
/* 145 */       ContractCustomer cc = this.contractCustomerManager.loadContractCustomer(Long.valueOf(this.request.getParameter("cc.id")));
/*     */ 
/* 147 */       if (null != cc) {
/* 148 */         this.remit.setCc(cc);
/*     */       }
/*     */     }
/* 151 */     if (isNew) {
/* 152 */       String code = autoCompleteCode();
/* 153 */       this.remit.setCode(code);
/* 154 */       this.remitManager.storeRemit(this.remit);
/* 155 */       addActionMessage(getText("remit.save.success"));
/* 156 */       return "new";
/*     */     }
/* 158 */     this.remitManager.storeRemit(this.remit);
/* 159 */     addActionMessage(getText("remit.edit.success"));
/* 160 */     return "success";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllTypes()
/*     */   {
/* 171 */     List codes = null;
/*     */     try {
/* 173 */       codes = new ArrayList();
/* 174 */       List one = this.codeValueManager.loadByKey("code", "083001");
/*     */ 
/* 176 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 178 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 180 */         if ((null != list) && (list.size() > 0)) {
/* 181 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 184 */       return codes;
/*     */     } catch (DaoException e) {
/* 186 */       e.printStackTrace();
/*     */     }
/* 188 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllBillings()
/*     */   {
/* 196 */     List codes = null;
/*     */     try {
/* 198 */       codes = new ArrayList();
/* 199 */       List one = this.codeValueManager.loadByKey("code", "084001");
/*     */ 
/* 201 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 203 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 205 */         if ((null != list) && (list.size() > 0)) {
/* 206 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 209 */       return codes;
/*     */     } catch (DaoException e) {
/* 211 */       e.printStackTrace();
/*     */     }
/* 213 */     return codes;
/*     */   }
/*     */ 
/*     */   public String autoCompleteCode()
/*     */   {
/* 222 */     String maxCode = this.remitManager.getMaxPFCode("THGL");
/* 223 */     if (null != maxCode) {
/* 224 */       int num = Integer.parseInt(maxCode) + 1;
/* 225 */       if (num < 10)
/* 226 */         return "THGL-000" + num;
/* 227 */       if (num < 100)
/* 228 */         return "THGL-00" + num;
/* 229 */       if (num < 1000) {
/* 230 */         return "THGL-0" + num;
/*     */       }
/* 232 */       return "THGL-" + num;
/*     */     }
/*     */ 
/* 235 */     return "THGL-0001";
/*     */   }
/*     */ 
/*     */   public Remit getRemit()
/*     */   {
/* 244 */     return this.remit;
/*     */   }
/*     */ 
/*     */   public void setRemit(Remit remit)
/*     */   {
/* 253 */     this.remit = remit;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.COM.remit.EditRemitAction
 * JD-Core Version:    0.6.2
 */
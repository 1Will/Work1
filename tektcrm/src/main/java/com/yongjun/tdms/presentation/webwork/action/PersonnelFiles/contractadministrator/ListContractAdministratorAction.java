/*     */ package com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.contractadministrator;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.personnelFiles.contractadministrator.ContractAdministrator;
/*     */ import com.yongjun.tdms.service.personnelFiles.contractadministrator.ContractAdministratorManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ListContractAdministratorAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = -7991194195515604965L;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private final ContractAdministratorManager contractAdministratorManager;
/*     */   private List<ContractAdministrator> contractAdministrators;
/*     */ 
/*     */   public ListContractAdministratorAction(CodeValueManager codeValueManager, ContractAdministratorManager contractAdministratorManager)
/*     */   {
/*  45 */     this.codeValueManager = codeValueManager;
/*  46 */     this.contractAdministratorManager = contractAdministratorManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  53 */     if (hasIds("contractAdministratorIds"))
/*  54 */       this.contractAdministrators = this.contractAdministratorManager.loadAllContractAdministrator(getIds("contractAdministratorIds"));
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  65 */     return "contractAdministrators";
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/*  77 */     if (isDisabled()) {
/*  78 */       disabled();
/*     */     }
/*  80 */     if (isEnable()) {
/*  81 */       enabled();
/*     */     }
/*  83 */     if (isDelete()) {
/*  84 */       delete();
/*     */     }
/*  86 */     return "success";
/*     */   }
/*     */ 
/*     */   public String delete()
/*     */   {
/*     */     try
/*     */     {
/*  94 */       this.contractAdministratorManager.deleteAllContractAdministrator(this.contractAdministrators);
/*  95 */       addActionMessage(getText("contractAdministrator.delete.success"));
/*     */     } catch (RuntimeException e) {
/*  97 */       addActionMessage(getText("contractAdministrator.delete.error"));
/*  98 */       return "error";
/*     */     }
/* 100 */     return "success";
/*     */   }
/*     */ 
/*     */   private String disabled()
/*     */   {
/*     */     try
/*     */     {
/* 108 */       this.contractAdministratorManager.disabledContractAdministrators(this.contractAdministrators);
/* 109 */       addActionMessage(getText("contractAdministrator.disabled.success"));
/* 110 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 112 */       addActionMessage(getText("contractAdministrator.disabled.error"));
/* 113 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String enabled()
/*     */   {
/*     */     try
/*     */     {
/* 123 */       this.contractAdministratorManager.enabledContractAdministrators(this.contractAdministrators);
/* 124 */       addActionMessage(getText("contractAdministrator.enabled.success"));
/* 125 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 127 */       e.printStackTrace();
/* 128 */       addActionMessage(getText("contractAdministrator.enabled.error"));
/* 129 */     }return "error";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllContractTypes()
/*     */   {
/* 138 */     List codes = null;
/*     */     try {
/* 140 */       codes = new ArrayList();
/* 141 */       List one = this.codeValueManager.loadByKey("code", "055555");
/*     */ 
/* 143 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 145 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 147 */         if ((null != list) && (list.size() > 0)) {
/* 148 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 151 */       CodeValue cv = new CodeValue();
/* 152 */       cv.setId(Long.valueOf(-1L));
/* 153 */       cv.setName(getText("select.option.all"));
/* 154 */       codes.add(0, cv);
/* 155 */       return codes;
/*     */     } catch (DaoException e) {
/* 157 */       e.printStackTrace();
/*     */     }
/* 159 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllBecomes()
/*     */   {
/* 166 */     List codes = null;
/*     */     try {
/* 168 */       codes = new ArrayList();
/* 169 */       List one = this.codeValueManager.loadByKey("code", "055554");
/*     */ 
/* 171 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 173 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 175 */         if ((null != list) && (list.size() > 0)) {
/* 176 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 179 */       CodeValue cv = new CodeValue();
/* 180 */       cv.setId(Long.valueOf(-1L));
/* 181 */       cv.setName(getText("select.option.all"));
/* 182 */       codes.add(0, cv);
/* 183 */       return codes;
/*     */     } catch (DaoException e) {
/* 185 */       e.printStackTrace();
/*     */     }
/* 187 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<ContractAdministrator> getContractAdministrators()
/*     */   {
/* 194 */     return this.contractAdministrators;
/*     */   }
/*     */ 
/*     */   public void setContractAdministrators(List<ContractAdministrator> contractAdministrators)
/*     */   {
/* 203 */     this.contractAdministrators = contractAdministrators;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.contractadministrator.ListContractAdministratorAction
 * JD-Core Version:    0.6.2
 */
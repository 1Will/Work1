/*     */ package com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.becomesmanager;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.personnelFiles.Becomes;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import com.yongjun.tdms.model.personnelFiles.contractadministrator.ContractAdministrator;
/*     */ import com.yongjun.tdms.service.personnelFiles.becomesmanager.BecomesManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.contractadministrator.ContractAdministratorManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ListBecomesAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = -6323953075714588419L;
/*     */   private final BecomesManager becomesManager;
/*     */   private final ContractAdministratorManager contractAdministratorManager;
/*     */   private final PersonnelFilesManager personnelFilesManager;
/*     */   private Becomes becomes;
/*     */ 
/*     */   public ListBecomesAction(BecomesManager becomesManager, ContractAdministratorManager contractAdministratorManager, PersonnelFilesManager personnelFilesManager)
/*     */   {
/*  54 */     this.becomesManager = becomesManager;
/*  55 */     this.contractAdministratorManager = contractAdministratorManager;
/*  56 */     this.personnelFilesManager = personnelFilesManager;
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  65 */     return "becomeses";
/*     */   }
/*     */ 
/*     */   public void saveBecomes()
/*     */   {
/*  72 */     List<PersonnelFiles> personnelFiles = this.personnelFilesManager.loadAllPersonnel();
/*  73 */     if (null != personnelFiles)
/*  74 */       for (PersonnelFiles p : personnelFiles)
/*     */         try {
/*  76 */           List<ContractAdministrator> contractAdministrators = this.contractAdministratorManager.loadByKey("personnelName", p.getId());
/*     */ 
/*  78 */           if (null != contractAdministrators)
/*  79 */             for (ContractAdministrator c : contractAdministrators) {
/*  80 */               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/*  81 */               Date now = new Date();
/*     */               try {
/*  83 */                 now = sdf.parse(sdf.format(now));
/*  84 */                 if (p.getRegularizedDate().before(now)) {
/*  85 */                   this.becomes.setCode(p.getCode());
/*  86 */                   this.becomes.setName(p.getName());
/*  87 */                   this.becomes.setPersonnelCode(p.getFileNo());
/*  88 */                   this.becomes.setInst(p.getInst());
/*  89 */                   this.becomes.setDept(p.getDept());
/*  90 */                   this.becomes.setDuty(p.getDuty());
/*  91 */                   this.becomes.setContractCode(c.getContractCode());
/*  92 */                   this.becomes.setBecomesDate(p.getRegularizedDate());
/*  93 */                   this.becomes.setMobilephone(p.getMobile());
/*  94 */                   this.becomes.setTelephone(p.getTelphone());
/*  95 */                   this.becomes.setStatus(p.getStatus());
/*  96 */                   this.becomesManager.storeBecomes(this.becomes);
/*     */                 }
/*     */               } catch (ParseException e) {
/*  99 */                 e.printStackTrace();
/*     */               }
/*     */             }
/*     */         }
/*     */         catch (DaoException e)
/*     */         {
/* 105 */           e.printStackTrace();
/*     */         }
/*     */   }
/*     */ 
/*     */   public Becomes getBecomes()
/*     */   {
/* 116 */     return this.becomes;
/*     */   }
/*     */ 
/*     */   public void setBecomes(Becomes becomes)
/*     */   {
/* 125 */     this.becomes = becomes;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.becomesmanager.ListBecomesAction
 * JD-Core Version:    0.6.2
 */
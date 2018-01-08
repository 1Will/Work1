/*     */ package com.yongjun.tdms.presentation.webwork.action.backvisit;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.stepInfo.StepInfo;
/*     */ import com.yongjun.tdms.model.backvisit.BackVisit;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager;
/*     */ import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
/*     */ import com.yongjun.tdms.service.backvisit.BackVisitManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class EditBackVisitStateAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private final BackVisitManager backVisitManager;
/*     */   private final PersonnelFilesManager personnelFilesManager;
/*     */   private final CustomerInfoManager customerInfoManager;
/*     */   private final ContactArchivesManager contactArchivesManager;
/*     */   private final UserManager userManager;
/*     */   private BackVisit backVisit;
/*     */   private StepInfo stepInfo;
/*     */   private PersonnelFiles personnelFiles;
/*     */ 
/*     */   public EditBackVisitStateAction(CodeValueManager codeValueManager, BackVisitManager backVisitManager, PersonnelFilesManager personnelFilesManager, CustomerInfoManager customerInfoManager, ContactArchivesManager contactArchivesManager, UserManager userManager)
/*     */   {
/*  40 */     this.codeValueManager = codeValueManager;
/*  41 */     this.backVisitManager = backVisitManager;
/*  42 */     this.personnelFilesManager = personnelFilesManager;
/*  43 */     this.customerInfoManager = customerInfoManager;
/*  44 */     this.contactArchivesManager = contactArchivesManager;
/*  45 */     this.userManager = userManager;
/*     */   }
/*     */   public BackVisit getBackVisit() {
/*  48 */     return this.backVisit;
/*     */   }
/*     */   public void setBackVisit(BackVisit backVisit) {
/*  51 */     this.backVisit = backVisit;
/*     */   }
/*     */   public void prepare() throws Exception {
/*  54 */     if (this.backVisit == null)
/*  55 */       if (hasId("backVisit.id")) {
/*  56 */         this.backVisit = this.backVisitManager.loadBackVisit(getId("backVisit.id"));
/*     */       } else {
/*  58 */         this.backVisit = new BackVisit();
/*  59 */         if (this.userManager.getUser().getCode() != null) {
/*  60 */           List pfs = this.personnelFilesManager.loadByKey("name", this.userManager.getUser().getName());
/*  61 */           if ((null != pfs) && (pfs.size() > 0))
/*     */           {
/*  64 */             this.personnelFiles = ((PersonnelFiles)pfs.get(0));
/*  65 */             this.backVisit.setEmployee(this.personnelFiles);
/*     */ 
/*  67 */             this.backVisit.setBackVisiter(this.personnelFiles.getName());
/*     */           }
/*     */         }
/*     */       }
/*     */   }
/*     */ 
/*     */   public String save() {
/*  74 */     boolean isNew = this.backVisit.isNew();

			  if(backVisit.getCustomerStated()==null){
				  if ("" != this.request.getParameter("customerStatingId")) {
					  /* 101 */       CodeValue cv = this.codeValueManager.loadCodeValue(Long.valueOf(Long.parseLong(this.request.getParameter("customerStatingId"))));
					  /* 102 */       this.backVisit.setCustomerStating(cv);
					  /*     */     }
			  }else{
				  this.backVisit.setCustomerStating(backVisit.getCustomerStated()); 
				  
			  }
			  
/* 104 */     if (hasId("customerStated.id")) {
/* 105 */       CodeValue cv = this.codeValueManager.loadCodeValue(getId("customerStated.id"));
/* 106 */       this.backVisit.setCustomerStated(cv);
/*     */     }
/* 108 */     if ("" != this.request.getParameter("changStateReason")) {
/* 109 */       this.backVisit.setChangStateReason(this.request.getParameter("changStateReason"));
/*     */     }

			
/* 112 */     if (hasId("customerStated.id")) {
				CodeValue cvState = this.codeValueManager.loadCodeValue(getId("customerStated.id"));
/* 114 */       CustomerInfo cif = this.backVisit.getCustomerInfo();
/*     */ 
                cif.setState(cvState);


/*     */ 
/* 121 */       this.backVisit.setCustomerInfo(cif);
/*     */     }
/* 124 */   
/* 143 */     if (isNew) {
/* 144 */       this.backVisitManager.storeBackVisit(this.backVisit);
/*     */ 
///* 146 */       if (("0" == this.request.getParameter("gradeChange")) || ("0".equals(this.request.getParameter("gradeChange")))) {
///* 147 */         CodeValue cve = this.codeValueManager.loadCodeValue(getId("customerSteped.id"));
///* 148 */         CustomerInfo cif = this.backVisit.getCustomerInfo();
///* 149 */         this.stepInfo = new StepInfo();
///* 150 */         this.stepInfo.setBackVisitId(this.backVisit);
///* 151 */         this.stepInfo.setCustomerId(cif);
///* 152 */         this.stepInfo.setCustomerSteping(this.codeValueManager.loadCodeValue(Long.valueOf(Long.parseLong(this.request.getParameter("customerStepingId")))));
///* 153 */         this.stepInfo.setCustomerSteped(cve);
///* 154 */         this.stepInfo.setChangeReason(this.request.getParameter("changeReason"));
///* 155 */         this.backVisitManager.storeStepInfo(this.stepInfo);
///*     */       }
/*     */     } else {
/* 158 */       this.backVisitManager.storeBackVisit(this.backVisit);
///* 159 */       if (("0" == this.request.getParameter("gradeChange")) || ("0".equals(this.request.getParameter("gradeChange")))) {
///* 160 */         CodeValue cve = this.codeValueManager.loadCodeValue(getId("customerSteped.id"));
///* 161 */         CustomerInfo cif = this.backVisit.getCustomerInfo();
///* 162 */         this.stepInfo = new StepInfo();
///* 163 */         this.stepInfo.setBackVisitId(this.backVisit);
///* 164 */         this.stepInfo.setCustomerId(cif);
///* 165 */         this.stepInfo.setCustomerSteping(this.codeValueManager.loadCodeValue(Long.valueOf(Long.parseLong(this.request.getParameter("customerStepingId")))));
///* 166 */         this.stepInfo.setCustomerSteped(cve);
///* 167 */         this.stepInfo.setChangeReason(this.request.getParameter("changeReason"));
///* 168 */         this.backVisitManager.storeStepInfo(this.stepInfo);
///*     */       }
/*     */     }
/*     */ 
/* 172 */     if (isNew) {
/* 173 */       addActionMessage(getText("backvisit.state.success", Arrays.asList(new Object[] { this.backVisit.getBackVisitType().getName() })));
/*     */ 
/* 175 */       return "success";
/*     */     }
/* 177 */     addActionMessage(getText("backvisit.state.success", Arrays.asList(new Object[] { this.backVisit.getBackVisitType().getName() })));
///*     */  if (hasId("from")) {
//			String s=request.getParameter("from");
//			if(s.equals("h")||s=="h"){
//				return "success1";
//			}
//	
//			}
/* 179 */     return "success";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllBackVisitWay() {
/* 183 */     List cust_types = new ArrayList();
/*     */     try {
/* 185 */       CodeValue custType = (CodeValue)this.codeValueManager.loadByKey("code", "007").get(0);
/* 186 */       cust_types = this.codeValueManager.loadByKey("parentCV.id", custType.getId());
/* 187 */       if (cust_types != null) {
/* 188 */         CodeValue cv = new CodeValue();
/* 189 */         cv.setId(Long.valueOf(-1L));
/* 190 */         cv.setName(getText(""));
/* 191 */         cust_types.add(0, cv);
/* 192 */         return cust_types;
/*     */       }
/* 194 */       return new ArrayList();
/*     */     }
/*     */     catch (DaoException e) {
/* 197 */       e.printStackTrace();
/* 198 */     }return new ArrayList();
/*     */   }
/*     */ 
/*     */   public UserManager getUserManager() {
/* 202 */     return this.userManager;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllSteps()
/*     */   {
/*     */     try
/*     */     {
/* 211 */       List codes = new ArrayList();
/* 212 */       List one = this.codeValueManager.loadByKey("code", "022");
/* 213 */       if ((null != one) && (one.size() > 0)) {
/* 214 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/* 215 */         if ((null != list) && (list.size() > 0)) {
/* 216 */           codes.addAll(list);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 223 */       return codes;
/*     */     } catch (DaoException e) {
/* 225 */       e.printStackTrace();
/* 226 */       return new ArrayList();
/*     */     }
/*     */   }
		public List<CodeValue> getAllStates()
/*     */   {
/*     */     try
/*     */     {
/* 211 */       List codes = new ArrayList();
/* 212 */       List one = this.codeValueManager.loadByKey("code", "200");
/* 213 */       if ((null != one) && (one.size() > 0)) {
/* 214 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/* 215 */         if ((null != list) && (list.size() > 0)) {
/* 216 */           codes.addAll(list);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 223 */       return codes;
/*     */     } catch (DaoException e) {
/* 225 */       e.printStackTrace();
/* 226 */       return new ArrayList();
/*     */     }
/*     */   }
/*     */ 
/*     */   public StepInfo getStepInfo()
/*     */   {
/* 235 */     return this.stepInfo;
/*     */   }
/*     */   public void setStepInfo(StepInfo stepInfo) {
/* 238 */     this.stepInfo = stepInfo;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllBackVisitType()
/*     */   {
/* 245 */     List cust_types = new ArrayList();
/*     */     try {
/* 247 */       CodeValue custType = (CodeValue)this.codeValueManager.loadByKey("code", "888").get(0);
/* 248 */       cust_types = this.codeValueManager.loadByKey("parentCV.id", custType.getId());
/* 249 */       if (cust_types != null) {
/* 250 */         CodeValue cv = new CodeValue();
/* 251 */         cv.setId(Long.valueOf(-1L));
/* 252 */         cv.setName(getText(""));
/* 253 */         cust_types.add(0, cv);
/* 254 */         return cust_types;
/*     */       }
/* 256 */       return new ArrayList();
/*     */     }
/*     */     catch (DaoException e) {
/* 259 */       e.printStackTrace();
/* 260 */     }return new ArrayList();
/*     */   }
/*     */ 
/*     */   public CodeValue getCustomerSteped()
/*     */   {
/* 265 */     return this.codeValueManager.loadCodeValue(getId("customerSteped.id"));
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.backvisit.EditBackVisitAction
 * JD-Core Version:    0.6.2
 */
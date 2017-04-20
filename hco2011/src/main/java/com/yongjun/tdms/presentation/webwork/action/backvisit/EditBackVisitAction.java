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
import com.yongjun.tdms.model.base.event.EventNew;
import com.yongjun.tdms.model.base.event.EventType;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.project.ProjectInfo;
/*     */ import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager;
/*     */ import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
/*     */ import com.yongjun.tdms.service.backvisit.BackVisitManager;
import com.yongjun.tdms.service.base.event.EventNewManager;
import com.yongjun.tdms.service.base.event.EventTypeManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.project.ProjectInfoManager;
import com.yongjun.tdms.util.personnelFilesToUser.PersonnelFilesToUserManager;

/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
import java.util.HashMap;
/*     */ import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;
/*     */ 
/*     */ public class EditBackVisitAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private final BackVisitManager backVisitManager;
/*     */   private final PersonnelFilesManager personnelFilesManager;
/*     */   private final CustomerInfoManager customerInfoManager;
/*     */   private final ContactArchivesManager contactArchivesManager;
            private final EventNewManager eventNewManager;
            private final EventTypeManager eventTypeManager;
/*     */   private final UserManager userManager;
            private final ProjectInfoManager projectInfoManager;
            private final PersonnelFilesToUserManager personnelFilesToUserManager;
/*     */   private BackVisit backVisit;
/*     */   private String fromType;
            private String openFlag;//如果存在就是从客户管理、联系人管理=项目管理中的弹出修改页面，控制点击返回是关闭 
public String getFromType() {
	return fromType;
}
public void setFromType(String fromType) {
	this.fromType = fromType;
}
/*     */   private StepInfo stepInfo;
/*     */   private PersonnelFiles personnelFiles;
/*     */ 
/*     */   public EditBackVisitAction(CodeValueManager codeValueManager, BackVisitManager backVisitManager, PersonnelFilesManager personnelFilesManager, CustomerInfoManager customerInfoManager, ContactArchivesManager contactArchivesManager, UserManager userManager,EventNewManager eventNewManager,ProjectInfoManager projectInfoManager,EventTypeManager eventTypeManager,PersonnelFilesToUserManager personnelFilesToUserManager)
/*     */   {
/*  40 */     this.codeValueManager = codeValueManager;
/*  41 */     this.backVisitManager = backVisitManager;
/*  42 */     this.personnelFilesManager = personnelFilesManager;
/*  43 */     this.customerInfoManager = customerInfoManager;
/*  44 */     this.contactArchivesManager = contactArchivesManager;
/*  45 */     this.userManager = userManager;
			  this.eventNewManager =eventNewManager;
			  this.projectInfoManager =projectInfoManager;
			  this.eventTypeManager =eventTypeManager;
			  this.personnelFilesToUserManager=personnelFilesToUserManager;
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
               if(this.request.getParameter("openFlag")!=null){
            	   this.openFlag =this.request.getParameter("openFlag");
               }
/*     */   }
/*     */ 
/*     */   public String save() {
/*  74 */     boolean isNew = this.backVisit.isNew();
			  if (request.getParameter("fromType") != null) {
				  fromType = request.getParameter("fromType");
			  }
/*     */ 
/*  76 */     if (hasId("customer.id")) {
/*  77 */       CustomerInfo ci = this.customerInfoManager.loadCustomerInfo(getId("customer.id"));
/*  78 */       this.backVisit.setCustomerName(ci.getName());
/*  79 */       this.backVisit.setCustomerInfo(ci);
				if(isNew){
					this.backVisit.setCustomerStating(ci.getState());  
					this.backVisit.setCustomerSteping(ci.getStep());
	  
				}
/*     */     }
/*     */ 
/*  82 */     if (hasId("backVisitType.id")) {
/*  83 */       CodeValue cv = this.codeValueManager.loadCodeValue(getId("backVisitType.id"));
/*  84 */       this.backVisit.setBackVisitType(cv);
/*     */     }
				if (hasId("projectInfo.id")) {
/*  83 */       ProjectInfo pInfo = this.projectInfoManager.loadProjectInfo(getId("projectInfo.id"));
/*  84 */       this.backVisit.setProjectInfo(pInfo);
                this.backVisit.setProjectName(pInfo.getName());
/*     */    	 }
/*  86 */     if (hasId("contactArchive.id")) {
/*  87 */       ContactArchives ca = this.contactArchivesManager.loadContactArchives(getId("contactArchive.id"));
/*  88 */       this.backVisit.setCaName(ca.getName());
/*  89 */       this.backVisit.setContactArchive(ca);
/*     */     }
/*  91 */     if (hasId("backVisiter.id")) {
/*  92 */       this.personnelFiles = this.personnelFilesManager.loadPersonnel(getId("backVisiter.id"));
/*  93 */       this.backVisit.setBackVisiter(this.personnelFiles.getName());
/*  94 */       this.backVisit.setEmployee(this.personnelFiles);
/*     */     }
/*  96 */     if (hasId("backVisitWay.id")) {
/*  97 */       CodeValue cv = this.codeValueManager.loadCodeValue(getId("backVisitWay.id"));
/*  98 */       this.backVisit.setBackVisitWay(cv);
/*     */     }
///* 100 */     if ("" != this.request.getParameter("customerStepingId")) {
///* 101 */       CodeValue cv = this.codeValueManager.loadCodeValue(Long.valueOf(Long.parseLong(this.request.getParameter("customerStepingId"))));
///* 102 */       this.backVisit.setCustomerSteping(cv);
///*     */     }
///* 104 */     if (hasId("customerSteped.id")) {
///* 105 */       CodeValue cv = this.codeValueManager.loadCodeValue(getId("customerSteped.id"));
///* 106 */       this.backVisit.setCustomerSteped(cv);
///*     */     }
///* 108 */     if ("" != this.request.getParameter("changeReason")) {
///* 109 */       this.backVisit.setChangReason(this.request.getParameter("changeReason"));
///*     */     }
//
//			  if ("" != this.request.getParameter("customerStatingId")) {
///* 101 */       CodeValue cv = this.codeValueManager.loadCodeValue(Long.valueOf(Long.parseLong(this.request.getParameter("customerStatingId"))));
///* 102 */       this.backVisit.setCustomerStating(cv);
///*     */     }
///* 104 */     if (hasId("customerStated.id")) {
///* 105 */       CodeValue cv = this.codeValueManager.loadCodeValue(getId("customerStated.id"));
///* 106 */       this.backVisit.setCustomerStated(cv);
///*     */     }
///* 108 */     if ("" != this.request.getParameter("changStateReason")) {
///* 109 */       this.backVisit.setChangStateReason(this.request.getParameter("changStateReason"));
///*     */     }

			  if ("" != this.request.getParameter("contactArchives")) {
/* 109 */       this.backVisit.setContactArchives(this.request.getParameter("contactArchives"));
/*     */     }
			  if ("" != this.request.getParameter("employees")) {
/* 109 */       this.backVisit.setEmployees(this.request.getParameter("employees"));
/*     */     }
			  
/*     */ 
///* 112 */     if (hasId("customerSteped.id")) {
///* 113 */       CodeValue cve = this.codeValueManager.loadCodeValue(getId("customerSteped.id"));
//				CodeValue cvState = this.codeValueManager.loadCodeValue(getId("customerStated.id"));
///* 114 */       CustomerInfo cif = this.backVisit.getCustomerInfo();
///*     */ 
///* 118 */       cif.setStep(cve);
//                cif.setState(cvState);
//
//
///*     */ 
///* 121 */       this.backVisit.setCustomerInfo(cif);
///*     */     }
			  if ("" != this.request.getParameter("isSaved")) {
				       this.backVisit.setIsSaved(this.request.getParameter("isSaved"));
			    }
/*     */ 
/* 124 */     if (isNew) {
/*     */       try
/*     */       {
/* 127 */         List<BackVisit> backVisits = this.backVisitManager.loadByKey("customerInfo", this.backVisit.getCustomerInfo());
/*     */         Long sum;
/* 129 */         if (null != backVisits) {
/* 130 */           sum = Long.valueOf(backVisits.size() + 1);
/* 131 */           this.backVisit.setBackVisitSum(sum);
/* 132 */           for (BackVisit b : backVisits) {
/* 133 */             b.setBackVisitSum(sum);
/* 134 */             this.backVisitManager.storeBackVisit(b);
/*     */           }
/*     */         } else {
/* 137 */           this.backVisit.setBackVisitSum(Long.valueOf(1L));
/*     */         }
/*     */       } catch (DaoException e) {
/* 140 */         e.printStackTrace();
/*     */       }
/*     */     }
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
			if ("" != this.request.getParameter("isSaved")&&this.request.getParameter("isSaved").endsWith("1")) {
				try {
					EventType eventType=null;
					List<EventType> eventTypes =this.eventTypeManager.loadByKey("code", "10001");
					if(eventTypes!=null&&eventTypes.size()>0){
						eventType=eventTypes.get(0);
					}else {
						  eventType = new EventType();
						    eventType.setId(2l);
					}
					 	EventNew event = new EventNew();
					    event.setEffectflag("E");
					    event.setEventType(eventType);
					    event.setName("回访提交");
					    event.setUserId(this.userManager.getUser().getId()+"");
					    Map<String, String> map = new HashMap();
					    if (this.backVisit.getProjectInfo() != null){
					    	map.put("users",this.personnelFilesToUserManager.loadUserIdToStrByProjectInfoId(this.backVisit.getProjectInfo().getId(), this.backVisit.getProjectInfo().getCreateUser()));
					    	
					    }else{
					    	
					    	map.put("users", this.personnelFilesToUserManager.loadUserIdToStrByEnable());
					    }
						   map.put("backVisitId", this.backVisit.getId()+"");
						   String moreinfo = JSONObject.fromObject(map).toString();
							event.setMoreinfo(moreinfo);
							eventNewManager.storeEventNew(event);

							addActionMessage(getText("backvisit.submit.success", Arrays.asList(new Object[] { this.backVisit.getBackVisitType().getName() })));
							return "success";
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
/*     */ 
/* 172 */     if (isNew) {
/* 173 */       addActionMessage(getText("backvisit.add.success", Arrays.asList(new Object[] { this.backVisit.getBackVisitType().getName() })));
/*     */ 
/* 175 */       return "success";
/*     */     }
/* 177 */     addActionMessage(getText("backvisit.edit.success", Arrays.asList(new Object[] { this.backVisit.getBackVisitType().getName() })));
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
/* 212 */       List one = this.codeValueManager.loadByKey("code", Long.valueOf("022"));
/* 213 */       if ((null != one) && (one.size() > 0)) {
/* 214 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(1)).getId());
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
/* 212 */       List one = this.codeValueManager.loadByKey("code", Long.valueOf("200"));
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
			public String getOpenFlag() {
				return openFlag;
			}
			public void setOpenFlag(String openFlag) {
				this.openFlag = openFlag;
			}
 
/*     */ 
///*     */   public CodeValue getCustomerSteped()
///*     */   {
///* 265 */     return this.codeValueManager.loadCodeValue(getId("customerSteped.id"));
///*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.backvisit.EditBackVisitAction
 * JD-Core Version:    0.6.2
 */
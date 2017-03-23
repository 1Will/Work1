/*     */ package com.yongjun.tdms.presentation.webwork.action.CustomerRelationship.contactArchives;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactToHistory;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
/*     */ import com.yongjun.tdms.model.base.area.Area;
import com.yongjun.tdms.model.project.ProjectInfo;
import com.yongjun.tdms.model.project.projectInfoContract.ProjectInfoContract;
/*     */ import com.yongjun.tdms.model.supplier.Supplier;
/*     */ import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager;
import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactToHistoryManager;
/*     */ import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
/*     */ import com.yongjun.tdms.service.base.area.AreaManager;
import com.yongjun.tdms.service.project.ProjectInfoManager;
import com.yongjun.tdms.service.project.projectInfoContract.ProjectInfoContractManager;
/*     */ import com.yongjun.tdms.service.supplier.SupplierManager;

/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;

/*     */ import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class EditContactArchivesAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final ContactArchivesManager contactArchivesManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private final CustomerInfoManager customerInfoManager;
            private final ContactToHistoryManager contactToHistoryManager;
            private final ProjectInfoContractManager projectInfoContractManager;
            private final UserManager userManager;
            private ProjectInfoManager projectInfoManager;
/*     */   private final AreaManager areaManager;
/*     */   private final SupplierManager supplierManager;
/*     */   private ContactArchives contactArchives;
/*     */   private String birthplace;
/*     */   private CodeValue nationality;
/*     */   private CustomerInfo customer;
/*     */   private CodeValue customerType;
/*     */   private CodeValue temperament;
/*     */   private CodeValue type;
/*     */   private boolean sex;
/*     */   private Supplier supplier;
            private ProjectInfo projectInfo;
            private String newContacts;

/*     */ 
/*     */   public EditContactArchivesAction(ContactArchivesManager contactArchivesManager, CodeValueManager codeValueManager, CustomerInfoManager customerInfoManager, AreaManager areaManager, SupplierManager supplierManager,ProjectInfoManager projectInfoManager,ContactToHistoryManager contactToHistoryManager,UserManager userManager,ProjectInfoContractManager projectInfoContractManager)
/*     */   {
/*  99 */     this.contactArchivesManager = contactArchivesManager;
/* 100 */     this.codeValueManager = codeValueManager;
/* 101 */     this.customerInfoManager = customerInfoManager;
/* 102 */     this.areaManager = areaManager;
/* 103 */     this.supplierManager = supplierManager;
              this.projectInfoManager=projectInfoManager;
              this.contactToHistoryManager=contactToHistoryManager;
              this.userManager =userManager;
              this.projectInfoContractManager =projectInfoContractManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/* 112 */     if (null == this.contactArchives) {
/* 113 */       if (hasId("contactArchives.id")) {
/* 114 */         this.contactArchives = this.contactArchivesManager.loadContactArchives(getId("contactArchives.id"));
/*     */ 
/* 116 */         this.sex = this.contactArchives.isSex();
/*     */       } else {
/* 118 */         this.contactArchives = new ContactArchives();
/*     */       }
/*     */     }
/*     */ 
/* 122 */     if (hasId("customer.id")) {
/* 123 */       this.customer = this.customerInfoManager.loadCustomerInfo(getId("customer.id"));
/* 124 */       this.contactArchives.setCustomerName(this.customer);
/* 125 */       this.contactArchives.setCustName(this.customer.getName());
/* 126 */       this.contactArchives.setIndustry(this.customer.getIndustry().getName());
/* 127 */       this.contactArchives.setCustomerType(this.customer.getCustomerType());
/* 128 */       this.contactArchives.setCustomerInfoCode(this.customer.getCode());
/*     */     } else if (this.contactArchives.getCustomerName() != null 
		&& this.contactArchives.getCustomerName().getId() != null) {
                   this.customer = this.contactArchives.getCustomerName();
			   }
/*     */ 
/* 137 */     if (hasId("supplier.id")) {
/* 138 */       this.supplier = this.supplierManager.loadSupplier(getId("supplier.id"));
/* 139 */       this.contactArchives.setSupplier(this.supplier);
/*     */     }
			  if (hasId("projectInfo.id")) {
/* 138 */       this.projectInfo = this.projectInfoManager.loadProjectInfo(getId("projectInfo.id"));
/* 139 */       this.contactArchives.setProjectInfo(this.projectInfo);
                this.contactArchives.setProName(this.projectInfo.getName());
/*     */     }
			  if (this.request.getParameter("newContacts")!=null) {
				  this.newContacts = this.request.getParameter("newContacts");
			  }
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/* 226 */     boolean isNew = this.contactArchives.isNew();
/*     */     try
/*     */     {
/* 234 */       if ((null != this.request.getParameter("contactArchivesSex")) && ("" != this.request.getParameter("contactArchivesSex"))) {
/* 235 */         String flag = this.request.getParameter("contactArchivesSex");
/* 236 */         if (flag.equals("0"))
/* 237 */           this.contactArchives.setSex(false);
/*     */         else {
/* 239 */           this.contactArchives.setSex(true);
/*     */         }
/*     */       }
/*     */ 
/* 243 */      /* if (hasId("birthplace.id")){
 244          this.birthplace = this.areaManager.loadArea(getId("birthplace.id")); 
            else {
 246          this.birthplace = null;
            }
 248        this.contactArchives.setBirthplace(this.birthplace);*/
			if (!StringUtils.isEmpty("contactArchives.birthplace")) {
				String bs=this.request.getParameter("contactArchives.birthplace");
				this.contactArchives.setBirthplace(bs);
			}
			if (!StringUtils.isEmpty("contactArchives.mobilePhone")) {
				String phon=this.request.getParameter("contactArchives.mobilePhone");				
				this.customer.setMobilePhone(phon);
			}

/* 250 */       if (hasId("leader.id")){
					ContactArchives leader =this.contactArchivesManager.loadContactArchives(getId("leader.id"));
					this.contactArchives.setLeader(leader);
/*     */      }
			
			
			
/* 250 */       if (hasId("nationality.id")){
///* 251 */         this.nationality = this.codeValueManager.loadCodeValue(getId("nationality.id"));
					nationality =new CodeValue();
					nationality.setId(getId("nationality.id"));
/*     */      } else {
/* 253 */         this.nationality = null;
/*     */       }
/* 255 */       this.contactArchives.setNationality(this.nationality);
/*     */ 
/* 262 */       if (hasId("temperament.id"))
/* 263 */         this.temperament = this.codeValueManager.loadCodeValue(getId("temperament.id"));
/*     */       else {
/* 265 */         this.temperament = null;
/*     */       }
/* 267 */       this.contactArchives.setTemperament(this.temperament);
/*     */ 
/* 273 */       if (hasId("type.id")) {
///* 274 */         this.type = this.codeValueManager.loadCodeValue(getId("type.id"));
					type =new CodeValue();
					type.setId(getId("type.id"));
/*     */       }
/*     */ 
/* 277 */       if (!StringUtils.isEmpty("contactArchives.comment")) {
/* 278 */         this.contactArchives.setComment(this.request.getParameter("contactArchives.comment"));
/* 279 */         this.contactArchives.setType(this.type);
/*     */       }
/*     */ 
/* 282 */       if (hasId("contactArchives.enterpriseSynopsis")) {
/* 283 */         this.contactArchives.setEnterpriseSynopsis(this.request.getParameter("contactArchives.enterpriseSynopsis"));
/*     */       }
				if (hasId("businessType.id")) {
					CodeValue cv = this.codeValueManager.loadCodeValue(getId("businessType.id"));
					this.contactArchives.setBusinessType(cv);
				}
               if(isNew){
            	   this.contactArchives.setCreator(this.userManager.getUser().getName());
               }
/*     */ 
/* 287 */       this.contactArchivesManager.storeContactArchives(this.contactArchives);

				ProjectInfoContract projectInfoContract =null;
				//先判断项目是否绑定了最新的联系人和其业务属性
					if(this.contactArchives.getProjectInfo()!=null&&this.contactArchives.getBusinessType()!=null){
	
						String[] arrayKey = {"projectInfo.id", "contactArchives.id"};
					Long [] arrayValue={this.contactArchives.getProjectInfo().getId(),this.contactArchives.getId()};
					//根据项目id和联系人id查询关系表中是否有记录， 有的话就修改，没有就增加
					List<ProjectInfoContract> list = this.projectInfoContractManager.loadByKeyArray(arrayKey, arrayValue);
					if(list!=null&&list.size()>0){
						projectInfoContract =list.get(0);
						projectInfoContract.setBusinessType(contactArchives.getBusinessType());
						projectInfoContract.setOutline(this.contactArchives.getOutline());
						 this.projectInfoContractManager.storeProjectInfoContract(projectInfoContract);
					}else {
						projectInfoContract= new ProjectInfoContract();
						projectInfoContract.setProjectInfo(this.contactArchives.getProjectInfo());
						projectInfoContract.setContactArchives(this.contactArchives);
						projectInfoContract.setBusinessType(this.contactArchives.getBusinessType());
						projectInfoContract.setOutline(this.contactArchives.getOutline());
						this.projectInfoContractManager.storeProjectInfoContract(projectInfoContract);
						
					}
		
		
	}


               if(isNew){
            	   ContactToHistory history = new ContactToHistory();
            	   history.setCreator(this.userManager.getUser().getName());
            	   history.setLastOperator(this.userManager.getUser().getName());
            	   history.setContactArchivesId(this.contactArchives);
            	   history.setConment("创建联系人");
            	   this.contactToHistoryManager.storeContactToHistory(history);
            	   
            	 
            	   
               }else {
            	   ContactToHistory history = new ContactToHistory();
//            	   history.setCreator(this.userManager.getUser().getName());
            	   history.setLastOperator(this.userManager.getUser().getName());
            	   history.setContactArchivesId(this.contactArchives);
            	   history.setConment("修改基本信息");
            	   this.contactToHistoryManager.storeContactToHistory(history);
            	   
               }

/*     */     }
/*     */     catch (Exception e) {
/* 290 */       e.printStackTrace();
/* 291 */       if (isNew) {
/* 292 */         addActionMessage(getText("contactArchives.add.error", Arrays.asList(new Object[] { this.contactArchives.getName() })));
/*     */         
/* 294 */         return "error";
/*     */       }
/* 296 */       addActionMessage(getText("contactArchives.edit.error", Arrays.asList(new Object[] { this.contactArchives.getName() })));
/*     */ 
/* 298 */       return "error";
/*     */     }
/*     */ 
/* 301 */     if (isNew) {
/* 302 */       addActionMessage(getText("contactArchives.add.success", Arrays.asList(new Object[] { this.contactArchives.getName() })));
/*     */ 
/* 304 */       return "new";
/*     */     }
/* 306 */     addActionMessage(getText("contactArchives.edit.success", Arrays.asList(new Object[] { this.contactArchives.getName() })));
/*     */ 
/* 308 */     return "success";
/*     */   }


		public String saveContactInformation()
/*     */   {
/* 226 */     boolean isNew = this.contactArchives.isNew();
/*     */     try
/*     */     {                                                                                                                          
			if (!StringUtils.isEmpty("contactArchives.mobilePhone")) {
				String phon=this.request.getParameter("contactArchives.mobilePhone");				
				this.customer.setMobilePhone(phon);
			}
			if (!StringUtils.isEmpty("contactArchives.chuanzhen")) {
				String cz=this.request.getParameter("contactArchives.chuanzhen");				
				this.customer.setChuanzhen(cz);
			}
			
			if (!StringUtils.isEmpty("contactArchives.chuanzhen")) {
				String cz=this.request.getParameter("contactArchives.chuanzhen");				
				this.customer.setChuanzhen(cz);
			}
			
			
			 this.contactArchivesManager.storeContactArchives(this.contactArchives);
			 
			 ContactToHistory history = new ContactToHistory();
//      	   history.setCreator(this.userManager.getUser().getName());
      	   history.setLastOperator(this.userManager.getUser().getName());
      	   history.setContactArchivesId(this.contactArchives);
      	   history.setConment("修改联系方式信息");
      	   this.contactToHistoryManager.storeContactToHistory(history);
/*     */     }
/*     */     catch (Exception e) {
/* 290 */       e.printStackTrace();
/* 291 */       if (isNew) {
/* 292 */         addActionMessage(getText("contactArchives.add.error", Arrays.asList(new Object[] { this.contactArchives.getName() })));
/*     */ 
/* 294 */         return "error";
/*     */       }
/* 296 */       addActionMessage(getText("contactArchives.edit.error", Arrays.asList(new Object[] { this.contactArchives.getName() })));
/*     */ 
/* 298 */       return "error";
/*     */     }
/*     */ 
/* 301 */     if (isNew) {
/* 302 */       addActionMessage(getText("contactArchives.add.success", Arrays.asList(new Object[] { this.contactArchives.getName() })));
/*     */ 
/* 304 */       return "new";
/*     */     }
/* 306 */     addActionMessage(getText("contactArchives.edit.success", Arrays.asList(new Object[] { this.contactArchives.getName() })));
/*     */ 
/* 308 */     return "success";
/*     */   }
		
		public String saveAdditional()
		/*     */   {
		/* 226 */     boolean isNew = this.contactArchives.isNew();
		/*     */     try
		/*     */     {
			
			if (hasId("nationality.id")){
				      CodeValue code = this.codeValueManager.loadCodeValue(getId("nationality.id"));
				      this.contactArchives.setNationality(code);
		      }
			
			
			if (hasId("temperament.id")){
			      CodeValue code = this.codeValueManager.loadCodeValue(getId("temperament.id"));
			      this.contactArchives.setTemperament(code);
	      }
			
			if (hasId("bloodType.id")){
			      CodeValue code = this.codeValueManager.loadCodeValue(getId("bloodType.id"));
			      this.contactArchives.setBloodType(code);
	      }
			
			if (hasId("constellation.id")){
			      CodeValue code = this.codeValueManager.loadCodeValue(getId("constellation.id"));
			      this.contactArchives.setConstellation(code);
	      }
			
			if (hasId("chineseZodiac.id")){
			      CodeValue code = this.codeValueManager.loadCodeValue(getId("chineseZodiac.id"));
			      this.contactArchives.setChineseZodiac(code);
	      }
			
			if (hasId("enneagram.id")){
			      CodeValue code = this.codeValueManager.loadCodeValue(getId("enneagram.id"));
			      this.contactArchives.setEnneagram(code);
	      }
			
			if (hasId("religiousBelief.id")){
			      CodeValue code = this.codeValueManager.loadCodeValue(getId("religiousBelief.id"));
			      this.contactArchives.setReligiousBelief(code);
	      }
			
			if (hasId("health.id")){
			      CodeValue code = this.codeValueManager.loadCodeValue(getId("health.id"));
			      this.contactArchives.setHealth(code);
	      }
			
			if (hasId("vision.id")){
			      CodeValue code = this.codeValueManager.loadCodeValue(getId("vision.id"));
			      this.contactArchives.setVision(code);
	      }
			
			
			if (hasId("maritalStatus.id")){
			      CodeValue code = this.codeValueManager.loadCodeValue(getId("maritalStatus.id"));
			      this.contactArchives.setMaritalStatus(code);
	      }
			
			if (hasId("education.id")){
			      CodeValue code = this.codeValueManager.loadCodeValue(getId("education.id"));
			      this.contactArchives.setEducation(code);
	      }
			
			if (hasId("politicalOutlook.id")){
			      CodeValue code = this.codeValueManager.loadCodeValue(getId("politicalOutlook.id"));
			      this.contactArchives.setPoliticalOutlook(code);
	      }
					
					
					
					 this.contactArchivesManager.storeContactArchives(this.contactArchives);
					 ContactToHistory history = new ContactToHistory();
//	            	   history.setCreator(this.userManager.getUser().getName());
	            	   history.setLastOperator(this.userManager.getUser().getName());
	            	   history.setContactArchivesId(this.contactArchives);
	            	   history.setConment("修改附加信息");
	            	   this.contactToHistoryManager.storeContactToHistory(history);
		/*     */     }
		/*     */     catch (Exception e) {
		/* 290 */       e.printStackTrace();
		/* 291 */       if (isNew) {
		/* 292 */         addActionMessage(getText("contactArchives.add.error", Arrays.asList(new Object[] { this.contactArchives.getName() })));
		/*     */ 
		/* 294 */         return "error";
		/*     */       }
		/* 296 */       addActionMessage(getText("contactArchives.edit.error", Arrays.asList(new Object[] { this.contactArchives.getName() })));
		/*     */ 
		/* 298 */       return "error";
		/*     */     }
		/*     */ 
		/* 301 */     if (isNew) {
		/* 302 */       addActionMessage(getText("contactArchives.add.success", Arrays.asList(new Object[] { this.contactArchives.getName() })));
		/*     */ 
		/* 304 */       return "new";
		/*     */     }
		/* 306 */     addActionMessage(getText("contactArchives.edit.success", Arrays.asList(new Object[] { this.contactArchives.getName() })));
		/*     */ 
		/* 308 */     return "success";
		/*     */   }
/*     */ //血型
		  			public List<CodeValue> getAllBlood()
		  /*     */   {
		  /*     */     try
		  /*     */     {
		  /* 340 */       List codes = new ArrayList();
		  /* 341 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("203"));
		  /* 342 */       if ((null != one) && (one.size() > 0)) {
		  /* 343 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
		  /* 344 */         if ((null != list) && (list.size() > 0)) {
		  /* 345 */           codes.addAll(list);
		  /*     */         }
		  /*     */       }
		  /* 348 */       return codes;
		  /*     */     } catch (Exception e) {
		  /* 350 */       e.printStackTrace();
		  /* 351 */       return new ArrayList();
		  /*     */     }
		  /*     */   }
		  			//生肖
		  			public List<CodeValue> getAllChineseZodiac()
		  		  /*     */   {
		  		  /*     */     try
		  		  /*     */     {
		  		  /* 340 */       List codes = new ArrayList();
		  		  /* 341 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("204"));
		  		  /* 342 */       if ((null != one) && (one.size() > 0)) {
		  		  /* 343 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
		  		  /* 344 */         if ((null != list) && (list.size() > 0)) {
		  		  /* 345 */           codes.addAll(list);
		  		  /*     */         }
		  		  /*     */       }
		  		  /* 348 */       return codes;
		  		  /*     */     } catch (Exception e) {
		  		  /* 350 */       e.printStackTrace();
		  		  /* 351 */       return new ArrayList();
		  		  /*     */     }
		  		  /*     */   }
		  			//星座
		  			public List<CodeValue> getAllConstellation()
			  		  /*     */   {
			  		  /*     */     try
			  		  /*     */     {
			  		  /* 340 */       List codes = new ArrayList();
			  		  /* 341 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("205"));
			  		  /* 342 */       if ((null != one) && (one.size() > 0)) {
			  		  /* 343 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
			  		  /* 344 */         if ((null != list) && (list.size() > 0)) {
			  		  /* 345 */           codes.addAll(list);
			  		  /*     */         }
			  		  /*     */       }
			  		  /* 348 */       return codes;
			  		  /*     */     } catch (Exception e) {
			  		  /* 350 */       e.printStackTrace();
			  		  /* 351 */       return new ArrayList();
			  		  /*     */     }
		  			}
		  			
		  		//星座
		  			public List<CodeValue> getAllEnneagram()
			  		  /*     */   {
			  		  /*     */     try
			  		  /*     */     {
			  		  /* 340 */       List codes = new ArrayList();
			  		  /* 341 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("206"));
			  		  /* 342 */       if ((null != one) && (one.size() > 0)) {
			  		  /* 343 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
			  		  /* 344 */         if ((null != list) && (list.size() > 0)) {
			  		  /* 345 */           codes.addAll(list);
			  		  /*     */         }
			  		  /*     */       }
			  		  /* 348 */       return codes;
			  		  /*     */     } catch (Exception e) {
			  		  /* 350 */       e.printStackTrace();
			  		  /* 351 */       return new ArrayList();
			  		  /*     */     }
		  			}
		  /*     */ 
		
/*     */   public List<CodeValue> getAllNationality()
/*     */   {
/*     */     try
/*     */     {
/* 340 */       List codes = new ArrayList();
/* 341 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("004"));
/* 342 */       if ((null != one) && (one.size() > 0)) {
/* 343 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/* 344 */         if ((null != list) && (list.size() > 0)) {
/* 345 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 348 */       return codes;
/*     */     } catch (Exception e) {
/* 350 */       e.printStackTrace();
/* 351 */       return new ArrayList();
/*     */     }
/*     */   }
/*     */ 
/*     */   public List<Area> getAllBirthplace()
/*     */   {
/*     */     try
/*     */     {
/* 362 */       List areas = new ArrayList();
/* 363 */       List list = this.areaManager.loadByKey("type", "province");
/* 364 */       if ((null != list) && (list.size() > 0)) {
/* 365 */         areas.addAll(list);
/*     */       }
/* 367 */       return areas;
/*     */     } catch (Exception e) {
/* 369 */       e.printStackTrace();
/* 370 */       return new ArrayList();
/*     */     }
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllTemperament()
/*     */   {
/*     */     try
/*     */     {
/* 381 */       List temperaments = new ArrayList();
/* 382 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("014"));
/* 383 */       if ((null != one) && (one.size() > 0)) {
/* 384 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/* 385 */         if ((null != list) && (list.size() > 0)) {
/* 386 */           temperaments.addAll(list);
/*     */         }
/*     */       }
/* 389 */       return temperaments;
/*     */     } catch (Exception e) {
/* 391 */       e.printStackTrace();
/* 392 */       return new ArrayList();
/*     */     }
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllTypes()
/*     */   {
/*     */     try
/*     */     {
/* 403 */       List types = new ArrayList();
/* 404 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("010"));
/* 405 */       if ((null != one) && (one.size() > 0)) {
/* 406 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/* 407 */         if ((null != list) && (list.size() > 0)) {
/* 408 */           types.addAll(list);
/*     */         }
/*     */       }
/* 411 */       return types;
/*     */     } catch (Exception e) {
/* 413 */       e.printStackTrace();
/* 414 */       return new ArrayList();
/*     */     }
/*     */   }
		public List<CodeValue> getAllMarriage()
/*     */   {
/*     */     try
/*     */     {
/* 331 */       String[] keyNames1 = { "code", "disabled" };
/* 332 */       Object[] keyValues1 = { "011", Boolean.valueOf(false) };
/* 333 */       List marriage = this.codeValueManager.loadByKeyArray(keyNames1, keyValues1);
/*     */ 
/* 335 */       if (marriage != null) {
/* 336 */         String[] keyNames2 = { "parentCV.id", "disabled" };
/* 337 */         Object[] keyValues2 = { ((CodeValue)marriage.get(0)).getId(), Boolean.valueOf(false) };
/* 338 */         List marriages = this.codeValueManager.loadByKeyArray(keyNames2, keyValues2);
/*     */ 
/* 340 */         if (marriages != null)
/* 341 */           return marriages;
/*     */       }
/*     */     }
/*     */     catch (DaoException e) {
/* 345 */       e.printStackTrace();
/* 346 */       return new ArrayList();
/*     */     }
/* 348 */     return new ArrayList();
/*     */   }
		 public List<CodeValue> getAllPolitice()
		 /*     */   {
		 /*     */     try
		 /*     */     {
		 /* 420 */       String[] keyNames1 = { "code", "disabled" };
		 /* 421 */       Object[] keyValues1 = { "005", Boolean.valueOf(false) };
		 /* 422 */       List politice = this.codeValueManager.loadByKeyArray(keyNames1, keyValues1);
		 /*     */ 
		 /* 424 */       if (politice != null) {
		 /* 425 */         String[] keyNames2 = { "parentCV.id", "disabled" };
		 /* 426 */         Object[] keyValues2 = { ((CodeValue)politice.get(0)).getId(), Boolean.valueOf(false) };
		 /* 427 */         List politices = this.codeValueManager.loadByKeyArray(keyNames2, keyValues2);
		 /*     */ 
		 /* 429 */         if (politices != null)
		 /* 430 */           return politices;
		 /*     */       }
		 /*     */     }
		 /*     */     catch (DaoException e) {
		 /* 434 */       e.printStackTrace();
		 /* 435 */       return new ArrayList();
		 /*     */     }
		 /* 437 */     return new ArrayList();
		 /*     */   }
		 public List<CodeValue> getAllEducation()
		 /*     */   {
		 /*     */     try
		 /*     */     {
		 /* 449 */       String[] keyNames1 = { "code", "disabled" };
		 /* 450 */       Object[] keyValues1 = { "017", Boolean.valueOf(false) };
		 /* 451 */       List education = this.codeValueManager.loadByKeyArray(keyNames1, keyValues1);
		 /*     */ 
		 /* 453 */       if (education != null) {
		 /* 454 */         String[] keyNames2 = { "parentCV.id", "disabled" };
		 /* 455 */         Object[] keyValues2 = { ((CodeValue)education.get(0)).getId(), Boolean.valueOf(false) };
		 /* 456 */         List educations = this.codeValueManager.loadByKeyArray(keyNames2, keyValues2);
		 /*     */ 
		 /* 458 */         if (educations != null)
		 /* 459 */           return educations;
		 /*     */       }
		 /*     */     }
		 /*     */     catch (DaoException e) {
		 /* 463 */       e.printStackTrace();
		 /* 464 */       return new ArrayList();
		 /*     */     }
		 /* 466 */     return new ArrayList();
		 /*     */   }
		 public List<CodeValue> getAllReligion()
		 /*     */   {
		 /* 125 */     List list = new ArrayList();
		 /*     */     try {
		 /* 127 */       String[] keyNames = { "code" };
		 /* 128 */       Object[] keyValues = { "019" };
		 /* 129 */       List cvs = this.codeValueManager.loadByKeyArray(keyNames, keyValues);
		 /*     */ 
		 /* 131 */       if ((null != cvs) && 
		 /* 132 */         (cvs.size() > 0)) {
		 /* 133 */         CodeValue cv = (CodeValue)cvs.get(0);
		 /* 134 */         keyNames = new String[] { "parentCV" };
		 /* 135 */         keyValues = new Object[] { cv.getId() };
		 /* 136 */         list = this.codeValueManager.loadByKeyArray(keyNames, keyValues);
		 /*     */       }
		 /*     */ 
		 /*     */     }
		 /*     */     catch (Exception e)
		 /*     */     {
		 /*     */     }
		 /*     */ 
		 /* 144 */     return list;
		 /*     */   }
		 public List<CodeValue> getAllBusinessType()
		 /*     */   {
		 /*     */     try
		 /*     */     {
		 /* 211 */       List codes = new ArrayList();
		 /* 212 */       List one = this.codeValueManager.loadByKey("code", Long.valueOf("207"));
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
		 /*     */   public List<CodeValue> getAllHealth()
		 /*     */   {
		 /* 155 */     List list = new ArrayList();
		 /*     */     try {
		 /* 157 */       String[] keyNames = { "code" };
		 /* 158 */       Object[] keyValues = { "018" };
		 /* 159 */       List cvs = this.codeValueManager.loadByKeyArray(keyNames, keyValues);
		 /*     */ 
		 /* 161 */       if ((null != cvs) && 
		 /* 162 */         (cvs.size() > 0)) {
		 /* 163 */         CodeValue cv = (CodeValue)cvs.get(0);
		 /* 164 */         keyNames = new String[] { "parentCV" };
		 /* 165 */         keyValues = new Object[] { cv.getId() };
		 /* 166 */         list = this.codeValueManager.loadByKeyArray(keyNames, keyValues);
		 /*     */       }
		 /*     */ 
		 /*     */     }
		 /*     */     catch (Exception e)
		 /*     */     {
		 /*     */     }
		 /*     */ 
		 /* 174 */     return list;
		 /*     */   }
/*     */ 
/*     */   public String getBirthplace()
/*     */   {
/* 423 */     return this.birthplace;
/*     */   }
/*     */ 
/*     */   public void setBirthplace(String birthplace)
/*     */   {
/* 430 */     this.birthplace = birthplace;
/*     */   }
/*     */ 
/*     */   public CodeValue getType()
/*     */   {
/* 437 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(CodeValue type)
/*     */   {
/* 444 */     this.type = type;
/*     */   }
/*     */ 
/*     */   public ContactArchives getContactArchives()
/*     */   {
/* 451 */     return this.contactArchives;
/*     */   }
/*     */ 
/*     */   public void setContactArchives(ContactArchives contactArchives)
/*     */   {
/* 458 */     this.contactArchives = contactArchives;
/*     */   }
/*     */ 
/*     */   public CodeValue getNationality()
/*     */   {
/* 465 */     return this.nationality;
/*     */   }
/*     */ 
/*     */   public void setNationality(CodeValue nationality)
/*     */   {
/* 472 */     this.nationality = nationality;
/*     */   }
/*     */ 
/*     */   public boolean isSex()
/*     */   {
/* 479 */     return this.sex;
/*     */   }
/*     */ 
/*     */   public void setSex(boolean sex)
/*     */   {
/* 486 */     this.sex = sex;
/*     */   }
/*     */ 
/*     */   public CodeValue getTemperament()
/*     */   {
/* 493 */     return this.temperament;
/*     */   }
/*     */ 
/*     */   public void setTemperament(CodeValue temperament)
/*     */   {
/* 500 */     this.temperament = temperament;
/*     */   }
/*     */ 
/*     */   public CodeValue getCustomerType()
/*     */   {
/* 507 */     return this.customerType;
/*     */   }
/*     */ 
/*     */   public void setCustomerType(CodeValue customerType)
/*     */   {
/* 514 */     this.customerType = customerType;
/*     */   }
/*     */ 
/*     */   public Supplier getSupplier() {
/* 518 */     return this.supplier;
/*     */   }
			public ProjectInfo getProjectInfo() {
			return projectInfo;
			}
			public void setProjectInfo(ProjectInfo projectInfo) {
				this.projectInfo = projectInfo;
			}
			public String getNewContacts() {
				return newContacts;
			}
			public void setNewContacts(String newContacts) {
				this.newContacts = newContacts;
			}

/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.CustomerRelationship.contactArchives.EditContactArchivesAction
 * JD-Core Version:    0.6.2
 */
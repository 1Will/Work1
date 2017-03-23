/*     */ package com.yongjun.tdms.model.project;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.User;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
/*     */ import com.yongjun.tdms.model.base.area.Area;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import com.yongjun.tdms.model.supplier.Supplier;

import java.util.Date;
/*     */ 
/*     */ public class ProjectInfo extends BaseInfoEntity
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String name;//项目名称
			private String code;//项目编号
/*     */   private CustomerInfo customer;//客户
			private ContactArchives contact;//项目联系人
			private PersonnelFiles controller;//项目负责人
			private String outline;//项目概要
/*     */   private CodeValue state;//项目状态
			private CodeValue businessType;//项目联系人业务属性
            private User createUser;
            private String conOutline;//联系人角色说明
/*     */ 
/*     */   public boolean equals(Object arg0)
/*     */   {
/*  94 */     if (arg0 == this) {
/*  95 */       return true;
/*     */     }
/*  97 */     if (!(arg0 instanceof ProjectInfo)) {
/*  98 */       return false;
/*     */     }
/*     */ 
/* 101 */     ProjectInfo contact = (ProjectInfo)arg0;
/*     */ 
/* 103 */     if (!contact.getId().equals(getId())) {
/* 104 */       return false;
/*     */     }
/* 106 */     return true;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 111 */     return 0;
/*     */   }
/*     */ 
/*     */  
/*     */ 
/*     */   public String getName()
/*     */   {
/* 370 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 377 */     this.name = name;
/*     */   }
			public String getCode() {
				return code;
			}
			public void setCode(String code) {
				this.code = code;
			}
			public CustomerInfo getCustomer() {
				return customer;
			}
			public void setCustomer(CustomerInfo customer) {
				this.customer = customer;
			}
			public ContactArchives getContact() {
				return contact;
			}
			public void setContact(ContactArchives contact) {
				this.contact = contact;
			}
			public PersonnelFiles getController() {
				return controller;
			}
			public void setController(PersonnelFiles controller) {
				this.controller = controller;
			}
			public String getOutline() {
				return outline;
			}
			public void setOutline(String outline) {
				this.outline = outline;
			}
			public CodeValue getState() {
				return state;
			}
			public void setState(CodeValue state) {
				this.state = state;
			}
			public User getCreateUser() {
				return createUser;
			}
			public void setCreateUser(User createUser) {
				this.createUser = createUser;
			}
			public CodeValue getBusinessType() {
				return businessType;
			}
			public void setBusinessType(CodeValue businessType) {
				this.businessType = businessType;
			}
			public String getConOutline() {
				return conOutline;
			}
			public void setConOutline(String conOutline) {
				this.conOutline = conOutline;
			}
			
			
			
			
            
/*     */ 
/*     */   
			

/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives
 * JD-Core Version:    0.6.2
 */
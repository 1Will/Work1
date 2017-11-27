/*     */ package com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.relation;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.security.Organization;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.personnelFiles.SocialRelations;
import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.relation.SocialRelationsManager;

/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;

/*     */ import javax.servlet.http.HttpServletRequest;

/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class EditSocialRelationAction extends PrepareAction
/*     */ {
/*     */   private final SocialRelationsManager socialRelationManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private final UserManager userManager;
/*     */   private final PersonnelFilesManager personnelFilesManager;
            private final ContactArchivesManager contactArchivesManager;
/*     */   private SocialRelations socialRelation;
/*     */   private Long personnelFileId;
            private Long contactArchivesId;
/*     */ 
/*     */   public EditSocialRelationAction(SocialRelationsManager socialRelationManager, CodeValueManager codeValueManager, UserManager userManager, PersonnelFilesManager personnelFilesManager,ContactArchivesManager contactArchivesManager)
/*     */   {
/*  76 */     this.socialRelationManager = socialRelationManager;
/*  77 */     this.codeValueManager = codeValueManager;
/*  78 */     this.userManager = userManager;
/*  79 */     this.personnelFilesManager = personnelFilesManager;
              this.contactArchivesManager=contactArchivesManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  86 */     if ((this.socialRelation == null) && (hasId("socialRelation.id"))) {
/*  87 */       this.socialRelation = this.socialRelationManager.loadSocialRelations(getId("socialRelation.id"));
/*     */     }
/*     */     else
/*  90 */       this.socialRelation = new SocialRelations();
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/* 101 */     boolean isNew = this.socialRelation.isNew();
/*     */ 
/* 103 */     if (hasId("socialRelation.politice")) {
/* 104 */       this.socialRelation.setPolitice(this.codeValueManager.loadCodeValue(getId("socialRelation.politice")));
/*     */     }
/*     */ 
/* 108 */     this.socialRelation.setOrganization(this.userManager.getOrganization());
/*     */   if (hasId("pf.id")) {
			this.socialRelation.setPf(this.personnelFilesManager.loadPersonnel(Long.valueOf(this.request.getParameter("pf.id"))));
			}
			if (hasId("cr.id")) {
				this.socialRelation.setCr(this.contactArchivesManager.loadContactArchives(Long.valueOf(this.request.getParameter("cr.id"))));
			}
/* 110 */     
/*     */     try
/*     */     {
/* 114 */       this.socialRelationManager.storeSocialRelations(this.socialRelation);
/*     */ 
/* 116 */       if (isNew) {
/* 117 */         addActionMessage(getText("socialRelation.add.success", Arrays.asList(new Object[] { this.socialRelation.getName() })));
/*     */ 
/* 121 */         return "new";
/*     */       }
/*     */ 
/* 124 */       addActionMessage(getText("socialRelation.edit.success", Arrays.asList(new Object[] { this.socialRelation.getName() })));
/*     */ 
/* 128 */       return "success";
/*     */     }
/*     */     catch (Exception e) {
/* 131 */       e.printStackTrace();
/* 132 */       addActionMessage(getText("socialRelation.add.error", Arrays.asList(new Object[] { this.socialRelation.getName() })));
/*     */     }
/*     */ 
/* 136 */     return "error";
/*     */   }
/*     */ 
/*     */   public Long getPersonnelFileId()
/*     */   {
/* 147 */     if (!StringUtils.isEmpty(this.request.getParameter("pf.id"))) {
/* 148 */       return Long.valueOf(this.request.getParameter("pf.id"));
/*     */     }
/* 150 */     return null;
/*     */   }

			public Long getContactArchivesId() {
				if (!StringUtils.isEmpty(this.request.getParameter("cr.id"))) {
					     return Long.valueOf(this.request.getParameter("cr.id"));
					   }
					     return null;
			}
/*     */ 
/*     */   public SocialRelations getSocialRelation()
/*     */   {
/* 158 */     return this.socialRelation;
/*     */   }
/*     */ 
/*     */   public void setSocialRelation(SocialRelations socialRelation)
/*     */   {
/* 166 */     this.socialRelation = socialRelation;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllPolitice()
/*     */   {
/*     */     try
/*     */     {
/* 177 */       Long orgId = this.userManager.getOrganization().getId();
/* 178 */       String[] keyNames1 = { "code", "disabled" };
/* 179 */       Object[] keyValues1 = { "005", Boolean.valueOf(false) };
/* 180 */       List politice = this.codeValueManager.loadByKeyArray(keyNames1, keyValues1);
/*     */ 
/* 182 */       if (politice != null) {
/* 183 */         String[] keyNames2 = { "parentCV.id", "disabled" };
/* 184 */         Object[] keyValues2 = { ((CodeValue)politice.get(0)).getId(), Boolean.valueOf(false) };
/* 185 */         List politices = this.codeValueManager.loadByKeyArray(keyNames2, keyValues2);
/*     */ 
/* 187 */         if (politices != null)
/* 188 */           return politices;
/*     */       }
/*     */     }
/*     */     catch (DaoException e) {
/* 192 */       e.printStackTrace();
/* 193 */       return new ArrayList();
/*     */     }
/* 195 */     return new ArrayList();
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.relation.EditSocialRelationAction
 * JD-Core Version:    0.6.2
 */
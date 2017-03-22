/*     */ package com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.relation;
/*     */ 
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.personnelFiles.SocialRelations;
/*     */ import com.yongjun.tdms.service.personnelFiles.relation.SocialRelationsManager;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class ListSocialRelationAction extends ValueListAction
/*     */ {
/*     */   private final SocialRelationsManager socialRelationsManager;
/*     */   private List<SocialRelations> socialRelations;
/*     */   private Long personnelFileId;
            private Long contactArchivesId;
/*     */ 
/*     */   public ListSocialRelationAction(SocialRelationsManager socialRelationManager)
/*     */   {
/*  51 */     this.socialRelationsManager = socialRelationManager;
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  56 */     return "socialRelations";
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  65 */     if (hasIds("socialRelationIds")) {
/*  66 */       this.socialRelations = this.socialRelationsManager.loadAllSocialRelations(getIds("socialRelationIds"));
/*     */     }
/*  68 */     setFirst(false);
/*     */   }
/*     */ 
/*     */   private String delete()
/*     */   {
/*  78 */     this.socialRelationsManager.deleteAllSocialRelations(this.socialRelations);
/*     */ 
/*  80 */     return "success";
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/*  88 */     if (isDelete()) {
/*  89 */       return delete();
/*     */     }
/*  91 */     return "success";
/*     */   }
/*     */ 
/*     */   public Long getPersonnelFileId()
/*     */   {
/* 101 */     if (!StringUtils.isEmpty(this.request.getParameter("pf.id"))) {
/* 102 */       return Long.valueOf(this.request.getParameter("pf.id"));
/*     */     }
/* 104 */     return null;
/*     */   }
			public Long getContactArchivesId() {
				if (!StringUtils.isEmpty(this.request.getParameter("cr.id"))) {
					     return Long.valueOf(this.request.getParameter("cr.id"));
				    }
					    return null;
			}

/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.relation.ListSocialRelationAction
 * JD-Core Version:    0.6.2
 */
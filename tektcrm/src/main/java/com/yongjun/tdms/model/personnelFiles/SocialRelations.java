/*     */ package com.yongjun.tdms.model.personnelFiles;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
/*     */ import com.yongjun.pluto.model.tracking.CreatorTracking;
/*     */ import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;

/*     */ import java.util.Date;
/*     */ 
/*     */ public class SocialRelations extends BaseInfoEntity
/*     */   implements CreatedTimeTracking, CreatorTracking, LastOperatorTracking
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String name;
/*     */   private String relations;
/*     */   private Date birth;
/*     */   private String telphone;
/*     */   private CodeValue politice;
/*     */   private String literacy;
/*     */   private String job;
/*     */   private String inst;
/*     */   private String comment;
/*     */   private PersonnelFiles pf;
            private ContactArchives cr;
/*     */ 
/*     */   public boolean equals(Object arg0)
/*     */   {
/*  53 */     if (arg0 == this) {
/*  54 */       return true;
/*     */     }
/*  56 */     if (!(arg0 instanceof SocialRelations)) {
/*  57 */       return false;
/*     */     }
/*  59 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/*  65 */     return 0;
/*     */   }
/*     */ 
/*     */   public Date getBirth()
/*     */   {
/*  72 */     return this.birth;
/*     */   }
/*     */ 
/*     */   public void setBirth(Date birth)
/*     */   {
/*  79 */     this.birth = birth;
/*     */   }
/*     */ 
/*     */   public String getComment()
/*     */   {
/*  86 */     return this.comment;
/*     */   }
/*     */ 
/*     */   public void setComment(String comment)
/*     */   {
/*  93 */     this.comment = comment;
/*     */   }
/*     */ 
/*     */   public String getJob()
/*     */   {
/* 100 */     return this.job;
/*     */   }
/*     */ 
/*     */   public void setJob(String job)
/*     */   {
/* 107 */     this.job = job;
/*     */   }
/*     */ 
/*     */   public String getInst()
/*     */   {
/* 114 */     return this.inst;
/*     */   }
/*     */ 
/*     */   public void setInst(String inst)
/*     */   {
/* 121 */     this.inst = inst;
/*     */   }
/*     */ 
/*     */   public String getLiteracy()
/*     */   {
/* 128 */     return this.literacy;
/*     */   }
/*     */ 
/*     */   public void setLiteracy(String literacy)
/*     */   {
/* 135 */     this.literacy = literacy;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 142 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 149 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public PersonnelFiles getPf()
/*     */   {
/* 156 */     return this.pf;
/*     */   }
/*     */ 
/*     */   public void setPf(PersonnelFiles pf)
/*     */   {
/* 163 */     this.pf = pf;
/*     */   }
/*     */ 
/*     */   public CodeValue getPolitice()
/*     */   {
/* 170 */     return this.politice;
/*     */   }
/*     */ 
/*     */   public void setPolitice(CodeValue politice)
/*     */   {
/* 177 */     this.politice = politice;
/*     */   }
/*     */ 
/*     */   public String getRelations()
/*     */   {
/* 184 */     return this.relations;
/*     */   }
/*     */ 
/*     */   public void setRelations(String relations)
/*     */   {
/* 191 */     this.relations = relations;
/*     */   }
/*     */ 
/*     */   public String getTelphone()
/*     */   {
/* 198 */     return this.telphone;
/*     */   }
/*     */ 
/*     */   public void setTelphone(String telphone)
/*     */   {
/* 205 */     this.telphone = telphone;
/*     */   }
			public ContactArchives getCr() {
				return cr;
			}
			public void setCr(ContactArchives cr) {
				this.cr = cr;
			}
           
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.personnelFiles.SocialRelations
 * JD-Core Version:    0.6.2
 */
/*    */ package com.yongjun.tdms.model.base.area;
/*    */ 
/*    */ import com.yongjun.pluto.model.BaseInfoEntity;
/*    */ import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
/*    */ import com.yongjun.pluto.model.tracking.CreatorTracking;
/*    */ import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
/*    */ import com.yongjun.pluto.model.tracking.LastOperatorTracking;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class Area extends BaseInfoEntity
/*    */   implements CreatedTimeTracking, CreatorTracking, LastOperatorTracking, LastModifiedTimeTracking
/*    */ {
/*    */   private String code;
/*    */   private String name;
/*    */   private String type;
/*    */   private Area parentArea;
/* 30 */   private Set<Area> childArea = new HashSet();
/*    */ 
/*    */   public boolean equals(Object obj) {
/* 33 */     if (obj == this) {
/* 34 */       return true;
/*    */     }
/* 36 */     if (!(obj instanceof Area)) {
/* 37 */       return false;
/*    */     }
/* 39 */     Area o = (Area)obj;
/*    */ 
/* 41 */     if (!o.getId().equals(getId())) {
/* 42 */       return false;
/*    */     }
/* 44 */     return true;
/*    */   }
/*    */ 
/*    */   public int hashCode()
/*    */   {
/* 49 */     return getId().hashCode();
/*    */   }
/*    */ 
/*    */   public Set<Area> getChildArea() {
/* 53 */     return this.childArea;
/*    */   }
/*    */ 
/*    */   public String getCode() {
/* 57 */     return this.code;
/*    */   }
/*    */ 
/*    */   public String getName() {
/* 61 */     return this.name;
/*    */   }
/*    */ 
/*    */   public Area getParentArea() {
/* 65 */     return this.parentArea;
/*    */   }
/*    */ 
/*    */   public String getType() {
/* 69 */     return this.type;
/*    */   }
/*    */ 
/*    */   public void setChildArea(Set<Area> childArea) {
/* 73 */     this.childArea = childArea;
/*    */   }
/*    */ 
/*    */   public void setCode(String code) {
/* 77 */     this.code = code;
/*    */   }
/*    */ 
/*    */   public void setName(String name) {
/* 81 */     this.name = name;
/*    */   }
/*    */ 
/*    */   public void setParentArea(Area parentArea) {
/* 85 */     this.parentArea = parentArea;
/*    */   }
/*    */ 
/*    */   public void setType(String type) {
/* 89 */     this.type = type;
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.base.area.Area
 * JD-Core Version:    0.6.2
 */
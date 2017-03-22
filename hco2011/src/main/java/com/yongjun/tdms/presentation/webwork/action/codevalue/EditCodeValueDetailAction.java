/*     */ package com.yongjun.tdms.presentation.webwork.action.codevalue;
/*     */ 
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ 
/*     */ public class EditCodeValueDetailAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 2383086852839142040L;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private CodeValue type;
/*     */   private CodeValue codeValueDetail;
/*     */   private List<CodeValue> types;
/*     */ 
/*     */   public EditCodeValueDetailAction(CodeValueManager codeValueManager)
/*     */   {
/*  39 */     this.codeValueManager = codeValueManager;
/*     */   }
/*     */ 
/*     */   public void prepare() throws Exception {
/*  43 */     if (null == this.type) {
/*  44 */       if (hasId("type.id"))
/*  45 */         this.type = this.codeValueManager.loadCodeValue(getId("type.id"));
/*     */       else {
/*  47 */         this.type = new CodeValue();
/*     */       }
/*     */     }
/*  50 */     if (null == this.codeValueDetail)
/*  51 */       if (hasId("codeValueDetail.id"))
/*  52 */         this.codeValueDetail = this.codeValueManager.loadCodeValue(getId("codeValueDetail.id"));
/*     */       else
/*  54 */         this.codeValueDetail = new CodeValue();
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/*  71 */     boolean isNew = this.codeValueDetail.isNew();
/*  72 */     List<CodeValue> allTypes = new ArrayList();
/*  73 */     int code = 0; int max = 0;
/*     */ 
/*  75 */     if (isNew) {
/*  76 */       this.types = this.codeValueManager.loadAllCodeValue();
/*  77 */       for (CodeValue t : this.types) {
/*  78 */         if ((t.getParentCV() != null) && 
/*  79 */           (t.getParentCV().getId() == this.type.getId())) {
/*  80 */           allTypes.add(t);
/*     */         }
/*     */       }
/*     */ 
/*  84 */       for (CodeValue type_ : allTypes)
/*     */       {
/*  86 */         int index = this.type.getCode().length();
/*  87 */         code = Integer.valueOf(type_.getCode().substring(index, type_.getCode().length())).intValue();
/*  88 */         if (code > max) {
/*  89 */           max = code;
/*     */         }
/*     */       }
/*  92 */       String s_code = this.type.getCode() + Integer.toString(max + 1);
/*  93 */       this.codeValueDetail.setParentCV(this.type);
/*  94 */       this.codeValueDetail.setCode(s_code);
/*     */     }
/*     */ 
/* 110 */     this.codeValueManager.storeCodeValue(this.codeValueDetail);
/*     */ 
/* 112 */     if (isNew) {
/* 113 */       addActionMessage(getText("codeValueDetail.add.success", Arrays.asList(new Object[] { this.codeValueDetail.getName() })));
/*     */ 
/* 115 */       return "new";
/*     */     }
/* 117 */     addActionMessage(getText("codeValueDetail.edit.success", Arrays.asList(new Object[] { this.codeValueDetail.getName() })));
/*     */ 
/* 119 */     return "success";
/*     */   }
/*     */ 
/*     */   public CodeValue getCodeValueDetail()
/*     */   {
/* 139 */     return this.codeValueDetail;
/*     */   }
/*     */ 
/*     */   public void setCodeValueDetail(CodeValue codeValueDetail) {
/* 143 */     this.codeValueDetail = codeValueDetail;
/*     */   }
/*     */ 
/*     */   public CodeValue getType() {
/* 147 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(CodeValue type) {
/* 151 */     this.type = type;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getTypes() {
/* 155 */     return this.types;
/*     */   }
/*     */ 
/*     */   public void setTypes(List<CodeValue> types) {
/* 159 */     this.types = types;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.codevalue.EditCodeValueDetailAction
 * JD-Core Version:    0.6.2
 */
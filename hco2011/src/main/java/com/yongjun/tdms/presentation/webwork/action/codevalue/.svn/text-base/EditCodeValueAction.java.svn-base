/*     */ package com.yongjun.tdms.presentation.webwork.action.codevalue;
/*     */ 
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class EditCodeValueAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = -4592806141801642844L;
/*     */   private CodeValue type;
/*     */   private List<CodeValue> codeValueDetails;
/*     */   private List<CodeValue> Details;
/*     */   private List<CodeValue> types;
/*     */   private final CodeValueManager codeValueManager;
/*     */ 
/*     */   public EditCodeValueAction(CodeValueManager codeValueManager)
/*     */   {
/*  43 */     this.codeValueManager = codeValueManager;
/*     */   }
/*     */ 
/*     */   public void prepare() throws Exception
/*     */   {
/*  48 */     if ((this.codeValueDetails == null) && (hasIds("codeValueDetailIds"))) {
/*  49 */       this.codeValueDetails = this.codeValueManager.loadAllCodeValue(getIds("codeValueDetailIds"));
/*     */     }
/*     */ 
/*  52 */     if (null == this.type)
/*  53 */       if (hasId("type.id")) {
/*  54 */         this.type = this.codeValueManager.loadCodeValue(getId("type.id"));
/*  55 */         this.Details = new ArrayList(this.type.getChildCV());
/*     */ 
/*  57 */         this.request.setAttribute("valueList", this.Details);
/*     */       } else {
/*  59 */         this.type = new CodeValue();
/*     */       }
/*     */   }
/*     */ 
/*     */   public String delete()
/*     */   {
/*     */     try
/*     */     {
/*  69 */       this.codeValueManager.deleteAllCodeValue(this.codeValueDetails);
/*     */     } catch (Exception e) {
/*  71 */       e.printStackTrace();
/*  72 */       addActionMessage(getText("codeValueDetails.delete.error"));
/*  73 */       return "error";
/*     */     }
/*     */ 
/*  76 */     addActionMessage(getText("codeValueDetails.delete.success"));
/*  77 */     return "success";
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/*  84 */     if (isDelete()) {
/*  85 */       delete();
/*  86 */       return "success";
/*     */     }
/*  88 */     boolean isNew = this.type.isNew();
/*     */ 
/* 110 */     this.codeValueManager.storeCodeValue(this.type);
/* 111 */     if (isNew) {
/* 112 */       addActionMessage(getText("codeValue.add.success", Arrays.asList(new Object[] { this.type.getName() })));
/*     */ 
/* 114 */       return "new";
/*     */     }
/* 116 */     addActionMessage(getText("codeValue.edit.success", Arrays.asList(new Object[] { this.type.getName() })));
/*     */ 
/* 118 */     return "success";
/*     */   }
/*     */ 
/*     */   public CodeValue getType()
/*     */   {
/* 126 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(CodeValue type) {
/* 130 */     this.type = type;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getDetails()
/*     */   {
/* 135 */     return this.Details;
/*     */   }
/*     */ 
/*     */   public void setDetails(List<CodeValue> details) {
/* 139 */     this.Details = details;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.codevalue.EditCodeValueAction
 * JD-Core Version:    0.6.2
 */
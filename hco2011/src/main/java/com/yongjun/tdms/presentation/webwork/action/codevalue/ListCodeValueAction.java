/*    */ package com.yongjun.tdms.presentation.webwork.action.codevalue;
/*    */ 
/*    */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*    */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*    */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ListCodeValueAction extends ValueListAction
/*    */ {
/*    */   private static final long serialVersionUID = -5309787188031323179L;
/*    */   private final CodeValueManager codeValueManager;
/*    */   private List<CodeValue> codeValueDetails;
/*    */ 
/*    */   public ListCodeValueAction(CodeValueManager codeValueManager)
/*    */   {
/* 35 */     this.codeValueManager = codeValueManager;
/*    */   }
/*    */ 
/*    */   public void prepare()
/*    */   {
/* 42 */     if (hasIds("codeValueIds"))
/* 43 */       this.codeValueDetails = this.codeValueManager.loadAllCodeValue(getIds("codeValueIds"));
/*    */   }
/*    */ 
/*    */   public String execute()
/*    */   {
/* 48 */     if (isDisabled()) {
/* 49 */       return disabled();
/*    */     }
/* 51 */     if (isEnable()) {
/* 52 */       return enabled();
/*    */     }
/* 54 */     return "success";
/*    */   }
/*    */ 
/*    */   public String disabled() {
/* 58 */     this.codeValueManager.disabledAllCodeValues(this.codeValueDetails);
/* 59 */     addActionMessage(getText("disabled.codeValueDetails.success"));
/* 60 */     return "success";
/*    */   }
/*    */ 
/*    */   public String enabled() {
/* 64 */     this.codeValueManager.enabledAllChecks(this.codeValueDetails);
/* 65 */     addActionMessage(getText("enabled.codeValueDetails.success"));
/* 66 */     return "success";
/*    */   }
/*    */ 
/*    */   protected String getAdapterName()
/*    */   {
/* 71 */     return "codes";
/*    */   }
/*    */ 
/*    */   public List<CodeValue> getCodeValueDetails() {
/* 75 */     return this.codeValueDetails;
/*    */   }
/*    */ 
/*    */   public void setCodeValueDetails(List<CodeValue> codeValueDetails) {
/* 79 */     this.codeValueDetails = codeValueDetails;
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.codevalue.ListCodeValueAction
 * JD-Core Version:    0.6.2
 */
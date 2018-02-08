/*    */ package com.yongjun.tdms.presentation.webwork.tag;
/*    */ 
/*    */ import com.opensymphony.webwork.views.jsp.ui.HiddenTag;
/*    */ import com.opensymphony.xwork.util.OgnlValueStack;
/*    */ 
/*    */ public class OrgIdHiddenTag extends HiddenTag
/*    */ {
/*    */   private static final long serialVersionUID = -6994917502749890443L;
/*    */   public static final String TEMPLATE = "datepicker";
/*    */ 
/*    */   public void evaluateExtraParams(OgnlValueStack ognlValueStack)
/*    */   {
/* 40 */     super.evaluateExtraParams(ognlValueStack);
/*    */ 
/* 42 */     if (this.id == null)
/* 43 */       addParameter("id", findString(this.nameAttr));
/*    */   }
/*    */ 
/*    */   protected String getDefaultTemplate()
/*    */   {
/* 63 */     return "datepicker";
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.tag.OrgIdHiddenTag
 * JD-Core Version:    0.6.2
 */
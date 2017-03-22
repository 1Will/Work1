/*    */ package com.yongjun.tdms.presentation.webwork.action.base.products;
/*    */ 
/*    */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*    */ import java.util.Map;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ 
/*    */ public class ListSupplierProducts extends ValueListAction
/*    */ {
/*    */   private static final long serialVersionUID = 5616960016127750986L;
/*    */ 
/*    */   public void prepare()
/*    */     throws Exception
/*    */   {
/* 18 */     setFirst(false);
/*    */   }
/*    */ 
/*    */   protected Map getRequestParameterMap()
/*    */   {
/* 25 */     Map map = super.getRequestParameterMap();
/* 26 */     if ((null != this.request.getParameter("supplier.id")) && ("" != this.request.getParameter("supplier.id"))) {
/* 27 */       map.put("supplier.id", Long.valueOf(this.request.getParameter("supplier.id")));
/*    */     }
/* 29 */     return map;
/*    */   }
/*    */ 
/*    */   protected String getAdapterName() {
/* 33 */     return "supplierProducts";
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.base.products.ListSupplierProducts
 * JD-Core Version:    0.6.2
 */
/*     */ package com.yongjun.tdms.presentation.webwork.action.workspace.data;
/*     */ 
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.workspace.data.Data;
/*     */ import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.workspace.data.DataManager;

import java.text.SimpleDateFormat;
import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ListDataAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 675686785046421962L;
/*     */   private final DataManager dataManager;
/*     */   private final DepartmentManager departmentManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private List<Data> lbs;
/*     */ 
/*     */   public ListDataAction(DataManager dataManager, DepartmentManager departmentManager, CodeValueManager codeValueManager)
/*     */   {
/*  57 */     this.dataManager = dataManager;
/*  58 */     this.departmentManager = departmentManager;
/*  59 */     this.codeValueManager = codeValueManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  67 */     if (hasIds("dataIds"))
/*  68 */       this.lbs = this.dataManager.loadAllData(getIds("dataIds"));
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  77 */     return "datas";
/*     */   }
/*     */ 
/*     */   protected Map getRequestParameterMap()
/*     */   {
/*  86 */      Map map = super.getRequestParameterMap();
/*  87 */     User u = this.userManager.getUser();
/*  88 */     if (null != u) {
/*  89 */       map.put("personnelFiles.code", u.getCode());
/*     */     }
              if(request.getParameter("year")==null||request.getParameter("year").equals("")){
            	  SimpleDateFormat sf  = new SimpleDateFormat("yyyy年");
            	  String year = sf.format(new Date());
            	  map.put("year", "%"+year+"%");
              } 
/*  91 */     return map;
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/* 111 */     return "success";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.workspace.leaveBill.ListLeaveBillAction
 * JD-Core Version:    0.6.2
 */
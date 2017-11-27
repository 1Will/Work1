/*     */ package com.yongjun.tdms.presentation.webwork.action.workspace.ontheroadBill;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.security.Department;
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.workspace.ontheroadBill.OnTheRoadBill;
/*     */ import com.yongjun.tdms.service.base.org.DepartmentManager;
/*     */ import com.yongjun.tdms.service.workspace.ontheroadBill.OnTheRoadBillManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ListOnTheRoadBillAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 675686785046421962L;
/*     */   private final OnTheRoadBillManager onTheRoadBillManager;
/*     */   private final DepartmentManager departmentManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private List<OnTheRoadBill> otrbs;
/*     */ 
/*     */   public ListOnTheRoadBillAction(OnTheRoadBillManager onTheRoadBillManager, DepartmentManager departmentManager, CodeValueManager codeValueManager)
/*     */   {
/*  57 */     this.onTheRoadBillManager = onTheRoadBillManager;
/*  58 */     this.departmentManager = departmentManager;
/*  59 */     this.codeValueManager = codeValueManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  67 */     if (hasIds("onTheRoadBillIds"))
/*  68 */       this.otrbs = this.onTheRoadBillManager.loadAllOnTheRoadBill(getIds("onTheRoadBillIds"));
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  78 */     return "onTheRoadBills";
/*     */   }
/*     */ 
/*     */   protected Map getRequestParameterMap()
/*     */   {
/*  88 */     Map map = super.getRequestParameterMap();
/*  89 */     User u = this.userManager.getUser();
/*  90 */     if (null != u) {
/*  91 */       map.put("onTheRoadBill.applyPerson", u.getName());
/*     */     }
/*  93 */     return map;
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/* 105 */     if (isDisabled()) {
/* 106 */       disabled();
/*     */     }
/* 108 */     if (isEnable()) {
/* 109 */       enabled();
/*     */     }
/* 111 */     if (isDelete()) {
/* 112 */       delete();
/*     */     }
/* 114 */     return "success";
/*     */   }
/*     */ 
/*     */   public String delete()
/*     */   {
/*     */     try
/*     */     {
/* 123 */       this.onTheRoadBillManager.deleteAllOnTheRoadBill(this.otrbs);
/* 124 */       addActionMessage(getText("onTheRoadBill.delete.success"));
/* 125 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 127 */       addActionMessage(getText("onTheRoadBill.delete.error"));
/* 128 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String disabled()
/*     */   {
/*     */     try
/*     */     {
/* 139 */       this.onTheRoadBillManager.disabledAllOnTheRoadBills(this.otrbs);
/* 140 */       addActionMessage(getText("onTheRoadBill.disabled.success"));
/* 141 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 143 */       addActionMessage(getText("onTheRoadBill.disabled.error"));
/* 144 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String enabled()
/*     */   {
/*     */     try
/*     */     {
/* 154 */       this.onTheRoadBillManager.enabledAllOnTheRoadBills(this.otrbs);
/* 155 */       addActionMessage(getText("onTheRoadBill.enabled.success"));
/* 156 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 158 */       e.printStackTrace();
/* 159 */       addActionMessage(getText("onTheRoadBill.enabled.error"));
/* 160 */     }return "error";
/*     */   }
/*     */ 
/*     */   public List<OnTheRoadBill> getOtrbs()
/*     */   {
/* 169 */     return this.otrbs;
/*     */   }
/*     */ 
/*     */   public void setOtrbs(List<OnTheRoadBill> otrbs)
/*     */   {
/* 176 */     this.otrbs = otrbs;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllStatus()
/*     */   {
/* 183 */     List codes = null;
/*     */     try {
/* 185 */       codes = new ArrayList();
/* 186 */       List one = this.codeValueManager.loadByKey("code", "020");
/*     */ 
/* 188 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 190 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 192 */         if ((null != list) && (list.size() > 0)) {
/* 193 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 196 */       CodeValue cv = new CodeValue();
/* 197 */       cv.setId(Long.valueOf(-1L));
/* 198 */       cv.setName(getText("select.option.all"));
/* 199 */       codes.add(0, cv);
/* 200 */       return codes;
/*     */     } catch (DaoException e) {
/* 202 */       e.printStackTrace();
/*     */     }
/* 204 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<Department> getAllDepts()
/*     */   {
/* 212 */     List depts = this.departmentManager.loadAllDepartments();
/* 213 */     Department d = new Department();
/* 214 */     d.setId(Long.valueOf(-1L));
/* 215 */     d.setName(getText("select.option.all"));
/* 216 */     depts.add(0, d);
/* 217 */     return depts;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.workspace.ontheroadBill.ListOnTheRoadBillAction
 * JD-Core Version:    0.6.2
 */
/*     */ package com.yongjun.tdms.presentation.webwork.action.workspace.overTimeBill;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.security.Department;
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.workspace.overTimeBill.OverTimeBill;
/*     */ import com.yongjun.tdms.service.base.org.DepartmentManager;
/*     */ import com.yongjun.tdms.service.workspace.overTimeBill.OverTimeBillManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ListOverTimeBillAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 675686785046421962L;
/*     */   private final OverTimeBillManager overTimeBillManager;
/*     */   private final DepartmentManager departmentManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private List<OverTimeBill> otbs;
/*     */ 
/*     */   public ListOverTimeBillAction(OverTimeBillManager overTimeBillManager, DepartmentManager departmentManager, CodeValueManager codeValueManager)
/*     */   {
/*  56 */     this.overTimeBillManager = overTimeBillManager;
/*  57 */     this.departmentManager = departmentManager;
/*  58 */     this.codeValueManager = codeValueManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  65 */     if (hasIds("overTimeBillIds"))
/*  66 */       this.otbs = this.overTimeBillManager.loadAllOverTimeBill(getIds("overTimeBillIds"));
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  75 */     return "overTimeBills";
/*     */   }
/*     */ 
/*     */   protected Map getRequestParameterMap()
/*     */   {
/*  83 */     Map map = super.getRequestParameterMap();
///*  84 */     User u = this.userManager.getUser();
///*  85 */     if (null != u) {
///*  86 */       map.put("overTimeBill.applyPerson", u.getName());
///*     */     }
/*  88 */     return map;
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/* 102 */     if (isDisabled()) {
/* 103 */       disabled();
/*     */     }
/* 105 */     if (isEnable()) {
/* 106 */       enabled();
/*     */     }
/* 108 */     if (isDelete()) {
/* 109 */       delete();
/*     */     }
/* 111 */     return "success";
/*     */   }
/*     */ 
/*     */   public String delete()
/*     */   {
/*     */     try
/*     */     {
/* 121 */       this.overTimeBillManager.deleteAllOverTimeBill(this.otbs);
/* 122 */       addActionMessage(getText("overTimeBill.delete.success"));
/* 123 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 125 */       addActionMessage(getText("overTimeBill.delete.error"));
/* 126 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String disabled()
/*     */   {
/*     */     try
/*     */     {
/* 137 */       this.overTimeBillManager.disabledAllOverTimeBills(this.otbs);
/* 138 */       addActionMessage(getText("overTimeBill.disabled.success"));
/* 139 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 141 */       addActionMessage(getText("overTimeBill.disabled.error"));
/* 142 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String enabled()
/*     */   {
/*     */     try
/*     */     {
/* 152 */       this.overTimeBillManager.enabledAllOverTimeBills(this.otbs);
/* 153 */       addActionMessage(getText("overTimeBill.enabled.success"));
/* 154 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 156 */       e.printStackTrace();
/* 157 */       addActionMessage(getText("overTimeBill.enabled.error"));
/* 158 */     }return "error";
/*     */   }
/*     */ 
/*     */   public List<OverTimeBill> getOtbs()
/*     */   {
/* 167 */     return this.otbs;
/*     */   }
/*     */ 
/*     */   public void setOtbs(List<OverTimeBill> otbs)
/*     */   {
/* 175 */     this.otbs = otbs;
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
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.workspace.overTimeBill.ListOverTimeBillAction
 * JD-Core Version:    0.6.2
 */
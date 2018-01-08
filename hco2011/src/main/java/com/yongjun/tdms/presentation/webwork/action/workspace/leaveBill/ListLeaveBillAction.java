/*     */ package com.yongjun.tdms.presentation.webwork.action.workspace.leaveBill;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;

/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.security.Department;
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.workspace.leaveBill.LeaveBill;
/*     */ import com.yongjun.tdms.service.base.org.DepartmentManager;
/*     */ import com.yongjun.tdms.service.workspace.leaveBill.LeaveBillManager;
/*     */ 
/*     */ public class ListLeaveBillAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 675686785046421962L;
/*     */   private final LeaveBillManager leaveBillManager;
/*     */   private final DepartmentManager departmentManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private List<LeaveBill> lbs;
/*     */ 
/*     */   public ListLeaveBillAction(LeaveBillManager leaveBillManager, DepartmentManager departmentManager, CodeValueManager codeValueManager)
/*     */   {
/*  57 */     this.leaveBillManager = leaveBillManager;
/*  58 */     this.departmentManager = departmentManager;
/*  59 */     this.codeValueManager = codeValueManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  67 */     if (hasIds("leaveBillIds"))
/*  68 */       this.lbs = this.leaveBillManager.loadAllLeaveBill(getIds("leaveBillIds"));
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  77 */     return "leaveBills";
/*     */   }
/*     */ 
/*     */   protected Map getRequestParameterMap()
/*     */   {
/*  86 */     Map map = super.getRequestParameterMap();
/*  87 */     User u = this.userManager.getUser();
/*  88 */     if (null != u) {
/*  89 */       map.put("leaveBill.applyPerson", u.getName());
/*     */     }
/*  91 */     return map;
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
/* 121 */       this.leaveBillManager.deleteAllLeaveBill(this.lbs);
/* 122 */       addActionMessage(getText("leaveBill.delete.success"));
/* 123 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 125 */       addActionMessage(getText("leaveBill.delete.error"));
/* 126 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String disabled()
/*     */   {
/*     */     try
/*     */     {
/* 136 */       this.leaveBillManager.disabledAllLeaveBills(this.lbs);
/* 137 */       addActionMessage(getText("leaveBill.disabled.success"));
/* 138 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 140 */       addActionMessage(getText("leaveBill.disabled.error"));
/* 141 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String enabled()
/*     */   {
/*     */     try
/*     */     {
/* 151 */       this.leaveBillManager.enabledAllLeaveBills(this.lbs);
/* 152 */       addActionMessage(getText("leaveBill.enabled.success"));
/* 153 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 155 */       e.printStackTrace();
/* 156 */       addActionMessage(getText("leaveBill.enabled.error"));
/* 157 */     }return "error";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllStatus()
/*     */   {
/* 165 */     List codes = null;
/*     */     try {
/* 167 */       codes = new ArrayList();
/* 168 */       List one = this.codeValueManager.loadByKey("code", "020");
/*     */ 
/* 170 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 172 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 174 */         if ((null != list) && (list.size() > 0)) {
/* 175 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 178 */       CodeValue cv = new CodeValue();
/* 179 */       cv.setId(Long.valueOf(-1L));
/* 180 */       cv.setName(getText("select.option.all"));
/* 181 */       codes.add(0, cv);
/* 182 */       return codes;
/*     */     } catch (DaoException e) {
/* 184 */       e.printStackTrace();
/*     */     }
/* 186 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllTypes()
/*     */   {
/* 193 */     List codes = null;
/*     */     try {
/* 195 */       codes = new ArrayList();
/* 196 */       List one = this.codeValueManager.loadByKey("code", "030");
/*     */ 
/* 198 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 200 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 202 */         if ((null != list) && (list.size() > 0)) {
/* 203 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 206 */       CodeValue cv = new CodeValue();
/* 207 */       cv.setId(Long.valueOf(-1L));
/* 208 */       cv.setName(getText("select.option.all"));
/* 209 */       codes.add(0, cv);
/* 210 */       return codes;
/*     */     } catch (DaoException e) {
/* 212 */       e.printStackTrace();
/*     */     }
/* 214 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<Department> getAllDepts()
/*     */   {
/* 221 */     List depts = this.departmentManager.loadAllDepartments();
/* 222 */     Department d = new Department();
/* 223 */     d.setId(Long.valueOf(-1L));
/* 224 */     d.setName(getText("select.option.all"));
/* 225 */     depts.add(0, d);
/* 226 */     return depts;
/*     */   }
/*     */ 
/*     */   public List<LeaveBill> getLbs()
/*     */   {
/* 234 */     return this.lbs;
/*     */   }
/*     */ 
/*     */   public void setLbs(List<LeaveBill> lbs)
/*     */   {
/* 242 */     this.lbs = lbs;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.workspace.leaveBill.ListLeaveBillAction
 * JD-Core Version:    0.6.2
 */
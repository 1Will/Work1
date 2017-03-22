/*     */ package com.yongjun.tdms.presentation.webwork.action.baseInfoManagement.organizationManagement.institutionManagement;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.base.institution.Institution;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.service.base.institution.InstitutionManager;
/*     */ import com.yongjun.tdms.service.base.org.DepartmentManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class EditInstitutionAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final InstitutionManager institutionManager;
/*     */   private final DepartmentManager departmentManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private Institution institution;
/*     */   private Institution parentInst;
/*     */   private List<Institution> parentInsts;
/*     */   private String root;
/*     */   private Long parentId;
/*     */ 
/*     */   public EditInstitutionAction(InstitutionManager institutionManager, DepartmentManager departmentManager, CodeValueManager codeValueManager)
/*     */   {
/*  68 */     this.institutionManager = institutionManager;
/*  69 */     this.departmentManager = departmentManager;
/*  70 */     this.codeValueManager = codeValueManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  78 */     if (null == this.institution) {
/*  79 */       if (hasId("institution.id"))
/*  80 */         this.institution = this.institutionManager.loadInstitution(getId("institution.id"));
/*     */       else {
/*  82 */         this.institution = new Institution();
/*     */       }
/*     */     }
/*     */ 
/*  86 */     if (hasId("parentid")) {
/*  87 */       this.parentId = Long.valueOf(this.request.getParameter("parentid"));
/*     */     }
/*     */ 
/*  90 */     this.root = this.request.getParameter("root");
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/*  98 */     if (isDelete()) {
/*  99 */       return delete();
/*     */     }
/*     */ 
/* 102 */     boolean isNew = this.institution.isNew();
/*     */ 
/* 106 */     if (this.parentId == null)
/*     */     {
/* 108 */       this.institution.setStep(0);
/* 109 */       this.institution.setParentInst(null);
/*     */     } else {
/* 111 */       this.parentInst = this.institutionManager.loadInstitution(this.parentId);
/*     */ 
/* 113 */       this.institution.setStep(this.parentInst.getStep() + 1);
/* 114 */       this.institution.setParentInst(this.parentInst);
/*     */     }
/*     */ 
/* 118 */     if (hasId("industry.id")) {
/* 119 */       CodeValue cv = this.codeValueManager.loadCodeValue(getId("industry.id"));
/*     */ 
/* 121 */       this.institution.setIndustry(cv);
/*     */     }
/*     */ 
/* 124 */     if (hasId("nature.id")) {
/* 125 */       CodeValue cv = this.codeValueManager.loadCodeValue(getId("nature.id"));
/*     */ 
/* 127 */       this.institution.setNature(cv);
/*     */     }
/*     */     try
/*     */     {
/* 131 */       if (isNew)
/*     */       {
/* 133 */         if (null == this.institutionManager.loadByKey("code", this.institution.getCode())) {
/* 134 */           this.institutionManager.storeInstitution(this.institution);
/*     */         } else {
/* 136 */           addActionMessage(getText("institution.add.exist", Arrays.asList(new Object[] { this.institution.getCode() })));
/*     */ 
/* 138 */           return "error";
/*     */         }
/*     */       }
/* 141 */       else this.institutionManager.storeInstitution(this.institution); 
/*     */     }
/*     */     catch (DaoException e)
/*     */     {
/* 144 */       e.printStackTrace();
/* 145 */       addActionMessage(getText("institution.add.exist", Arrays.asList(new Object[] { this.institution.getCode() })));
/*     */ 
/* 149 */       return "error";
/*     */     }
/*     */ 
/* 152 */     if (isNew) {
/* 153 */       addActionMessage(getText("institution.add.success", Arrays.asList(new Object[] { this.institution.getCode() })));
/*     */ 
/* 157 */       return "new";
/*     */     }
/* 159 */     addActionMessage(getText("institution.edit.success", Arrays.asList(new Object[] { this.institution.getCode() })));
/*     */ 
/* 163 */     return "success";
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/* 173 */     return "success";
/*     */   }
/*     */ 
/*     */   private String delete() {
/* 177 */     Institution iii = new Institution();
/* 178 */     if (hasId("institution.id"))
/* 179 */       iii = this.institutionManager.loadInstitution(getId("institution.id"));
/* 180 */     else if (hasId("parentid")) {
/* 181 */       iii = this.institutionManager.loadInstitution(getId("parentid"));
/*     */     }
/* 183 */     List instList = new ArrayList();
/* 184 */     instList.addAll(iii.getChildInst());
/* 185 */     if (instList.size() > 0) {
/* 186 */       for (int i = 0; i < instList.size(); i++)
/*     */         try {
/* 188 */           List deptList = new ArrayList();
/* 189 */           deptList = this.departmentManager.loadByKey("inst.id", ((Institution)instList.get(i)).getId());
/* 190 */           if ((null != deptList) && (deptList.size() > 0))
/* 191 */             this.departmentManager.deleteAllDepartments(deptList);
/*     */         }
/*     */         catch (DaoException e) {
/* 194 */           e.printStackTrace();
/* 195 */           addActionMessage(getText("institution.delete.error"));
/* 196 */           return "error";
/*     */         }
/*     */     }
/*     */     try
/*     */     {
/* 201 */       if (instList.size() > 0) {
/* 202 */         this.institutionManager.deleteAllInstitution(instList);
/*     */       }
/* 204 */       List depts = new ArrayList();
/* 205 */       depts = this.departmentManager.loadByKey("inst.id", iii.getId());
/*     */ 
/* 207 */       if ((null != depts) && (depts.size() > 0)) {
/* 208 */         this.departmentManager.deleteAllDepartments(depts);
/*     */       }
/* 210 */       this.institutionManager.deleteInstitution(iii);
/* 211 */       this.institution = null;
/*     */     } catch (Exception e) {
/* 213 */       e.printStackTrace();
/* 214 */       addActionMessage(getText("institution.delete.error"));
/* 215 */       return "error";
/*     */     }
/* 217 */     addActionMessage(getText("institution.delete.success"));
/* 218 */     return "deleteSuccess";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllIndustry()
/*     */   {
/*     */     try
/*     */     {
/* 227 */       List arr = this.codeValueManager.loadByKey("code", "002");
/* 228 */       CodeValue industry = null;
/* 229 */       if (null != arr) {
/* 230 */         industry = (CodeValue)arr.get(0);
/* 231 */         List industrys = this.codeValueManager.loadByKey("parentCV", industry.getId());
/* 232 */         if (null != industrys) {
/* 233 */           return industrys;
/*     */         }
/*     */       }
/* 236 */       return new ArrayList();
/*     */     } catch (DaoException e) {
/* 238 */       e.printStackTrace();
/* 239 */     }return new ArrayList();
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllNature()
/*     */   {
/*     */     try
/*     */     {
/* 249 */       List arr = this.codeValueManager.loadByKey("code", "003");
/* 250 */       CodeValue industry = null;
/* 251 */       if (null != arr) {
/* 252 */         industry = (CodeValue)arr.get(0);
/* 253 */         List industrys = this.codeValueManager.loadByKey("parentCV", industry.getId());
/* 254 */         if (null != industrys) {
/* 255 */           return industrys;
/*     */         }
/*     */       }
/* 258 */       return new ArrayList();
/*     */     } catch (DaoException e) {
/* 260 */       e.printStackTrace();
/* 261 */     }return new ArrayList();
/*     */   }
/*     */ 
/*     */   public Institution getInstitution()
/*     */   {
/* 270 */     return this.institution;
/*     */   }
/*     */ 
/*     */   public void setInstitution(Institution institution)
/*     */   {
/* 277 */     this.institution = institution;
/*     */   }
/*     */ 
/*     */   public Institution getParentInst()
/*     */   {
/* 284 */     return this.parentInst;
/*     */   }
/*     */ 
/*     */   public void setParentInst(Institution parentInst)
/*     */   {
/* 291 */     this.parentInst = parentInst;
/*     */   }
/*     */ 
/*     */   public List<Institution> getInstitutions()
/*     */   {
/* 298 */     return this.parentInsts;
/*     */   }
/*     */ 
/*     */   public void setInstitutions(List<Institution> parentInsts)
/*     */   {
/* 305 */     this.parentInsts = parentInsts;
/*     */   }
/*     */ 
/*     */   public Long getParentId() {
/* 309 */     return this.parentId;
/*     */   }
/*     */ 
/*     */   public void setParentId(Long parentId) {
/* 313 */     this.parentId = parentId;
/*     */   }
/*     */ 
/*     */   public String getRoot() {
/* 317 */     return this.root;
/*     */   }
/*     */ 
/*     */   public void setRoot(String root) {
/* 321 */     this.root = root;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.baseInfoManagement.organizationManagement.institutionManagement.EditInstitutionAction
 * JD-Core Version:    0.6.2
 */
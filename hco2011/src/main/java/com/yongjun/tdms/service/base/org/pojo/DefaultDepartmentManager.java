/*     */ package com.yongjun.tdms.service.base.org.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.dao.security.UserDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.base.institution.Institution;
/*     */ import com.yongjun.pluto.model.security.Department;
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.tdms.dao.base.org.DepartmentDao;
/*     */ import com.yongjun.tdms.service.base.org.DepartmentManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class DefaultDepartmentManager extends BaseManager
/*     */   implements DepartmentManager
/*     */ {
/*     */   private final DepartmentDao departmentDao;
/*     */   private final UserDao userDao;
/*     */   private final UserManager userManager;
/*     */ 
/*     */   public DefaultDepartmentManager(DepartmentDao departmentDao, UserDao userDao, UserManager userManager)
/*     */   {
/*  52 */     this.departmentDao = departmentDao;
/*  53 */     this.userDao = userDao;
/*  54 */     this.userManager = userManager;
/*     */   }
/*     */ 
/*     */   public void storeDepartment(Department department) {
/*  58 */     if (department.getChildDepts().size() > 0)
/*     */     {
/*  60 */       storeDepartmentEditSTEP(department);
/*     */     }
/*  62 */     this.departmentDao.storeDepartment(department);
/*     */   }
/*     */ 
/*     */   public void storeDepartmentEditSTEP(Department dept)
/*     */   {
/*  70 */     for (Department dept1 : dept.getChildDepts()) {
/*  71 */       dept1.setStep(dept1.getParentDept().getStep() + 1);
/*  72 */       this.departmentDao.storeDepartment(dept1);
/*  73 */       if (dept1.getChildDepts().size() > 0)
/*  74 */         storeDepartmentEditSTEP(dept1);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Department loadDepartment(Long departmentId) {
/*  79 */     return this.departmentDao.loadDepartment(departmentId);
/*     */   }
/*     */ 
/*     */   public void deleteDepartment(Department department) {
/*  83 */     this.departmentDao.deleteDepartment(department);
/*     */   }
/*     */ 
/*     */   public void deleteAllDepartments(Collection<Department> departmentIds)
/*     */   {
/*  88 */     this.departmentDao.deleteAllDepartments(departmentIds);
/*     */   }
/*     */ 
/*     */   public List<Department> loadAllDepartments(Long[] departmentIds)
/*     */   {
/*  93 */     return this.departmentDao.loadAllDepartment(departmentIds);
/*     */   }
/*     */ 
/*     */   public List<Department> loadAllDepartments() {
/*  97 */     return this.departmentDao.loadAllDepartments();
/*     */   }
/*     */ 
/*     */   public List createSelectDepartments(String name)
/*     */   {
/* 105 */     List list = new ArrayList();
/*     */ 
/* 107 */     List<Department> rootDept = this.departmentDao.getDeptsByStep(0);
/* 108 */     for (Department dept : rootDept) {
/* 109 */       List allDeptOfRootDept = getDeptsOfAfterTraversal(dept);
/* 110 */       for (int i = allDeptOfRootDept.size() - 1; i >= 0; i--) {
/* 111 */         list.add(allDeptOfRootDept.get(i));
/*     */       }
/*     */     }
/* 114 */     Department dept = new Department();
/* 115 */     dept.setId(Long.valueOf(-1L));
/* 116 */     dept.setName(name);
/* 117 */     list.add(0, dept);
/* 118 */     return list;
/*     */   }
/*     */ 
/*     */   public List getParentChildRelationDept() {
/* 122 */     List list = new ArrayList();
/*     */ 
/* 124 */     List<Department> rootDept = this.departmentDao.getDeptsByStep(0);
/* 125 */     for (Department dept : rootDept) {
/* 126 */       List allDeptOfRootDept = getDeptsOfAfterTraversal(dept);
/* 127 */       for (int i = allDeptOfRootDept.size() - 1; i >= 0; i--) {
/* 128 */         list.add(allDeptOfRootDept.get(i));
/*     */       }
/*     */     }
/* 131 */     return list;
/*     */   }
/*     */   public List createSelectParentGroups(String name) {
/* 134 */     List list = this.departmentDao.loadAllDepartments();
/* 135 */     Department dept = new Department();
/* 136 */     dept.setId(Long.valueOf(0L));
/* 137 */     dept.setName(name);
/* 138 */     list.add(0, dept);
/* 139 */     return list;
/*     */   }
/*     */   public int getDeptDepth(Department dept) {
/* 142 */     int maxDepth = 0;
/*     */ 
/* 144 */     Department parentDept = dept.getParentDept();
/* 145 */     List treeDept = getDeptsOfAfterTraversal(dept);
/*     */ 
/* 147 */     for (int i = treeDept.size() - 1; i >= 0; i--) {
/* 148 */       int tmpDepth = 1;
/* 149 */       Department tempParentDept = ((Department)treeDept.get(i)).getParentDept();
/* 150 */       while (!tempParentDept.equals(parentDept)) {
/* 151 */         tempParentDept = tempParentDept.getParentDept();
/* 152 */         tmpDepth++;
/*     */       }
/* 154 */       if (maxDepth < tmpDepth) {
/* 155 */         maxDepth = tmpDepth;
/*     */       }
/*     */     }
/* 158 */     return maxDepth;
/*     */   }
/*     */ 
/*     */   public int getDeptChildNum(Department dept) {
/* 162 */     int childNum = 0;
/* 163 */     if (0 == dept.getChildDepts().size()) {
/* 164 */       return 1;
/*     */     }
/* 166 */     for (Department d : dept.getChildDepts()) {
/* 167 */       childNum += getDeptChildNum(d);
/*     */     }
/* 169 */     return childNum;
/*     */   }
/*     */ 
/*     */   public List<Department> getDeptsOfAfterTraversal(Department dept) {
/* 173 */     List container = new ArrayList();
/* 174 */     traversalDepts(dept, container);
/* 175 */     return container;
/*     */   }
/*     */ 
/*     */   protected void traversalDepts(Department dept, List container)
/*     */   {
/* 184 */     if (0 == dept.getChildDepts().size()) {
/* 185 */       container.add(dept);
/* 186 */       return;
/*     */     }
/* 188 */     for (Department d : dept.getChildDepts()) {
/* 189 */       traversalDepts(d, container);
/*     */     }
/* 191 */     container.add(dept);
/*     */   }
/*     */ 
/*     */   public List<Integer> getDeptStepAfterGroupingByStep()
/*     */   {
/* 196 */     return this.departmentDao.getDeptStepAfterGroupingByStep();
/*     */   }
/*     */ 
/*     */   public List<Department> getDeptsByStep(int step) {
/* 200 */     return this.departmentDao.getDeptsByStep(step);
/*     */   }
/*     */ 
/*     */   public List<Department> getDeptsByParentDept(Long parentId) {
/* 204 */     return this.departmentDao.getDeptsByParentDept(parentId);
/*     */   }
/*     */ 
/*     */   public List<Department> getAllChilds(Department dept) {
/* 208 */     List container = new ArrayList();
/* 209 */     traversalDepts(dept, container);
/* 210 */     container.remove(dept);
/* 211 */     return container;
/*     */   }
/*     */ 
/*     */   public void storeDepartment(Department department, boolean changParentDept) {
/* 215 */     if (department.isNew()) {
/* 216 */       setStepOfDept(department);
/*     */     }
/* 218 */     else if (changParentDept) {
/* 219 */       int orgStep = department.getStep();
/* 220 */       setStepOfDept(department);
/* 221 */       int stepDiff = department.getStep() - orgStep;
/* 222 */       updateStepOfChildDepts(stepDiff, getAllChilds(department));
/*     */     }
/* 224 */     this.departmentDao.storeDepartment(department);
/*     */   }
/*     */ 
/*     */   protected void setStepOfDept(Department department)
/*     */   {
/* 234 */     if (null != department.getParentDept())
/* 235 */       department.setStep(department.getParentDept().getStep() + 1);
/*     */     else
/* 237 */       department.setStep(0);
/*     */   }
/*     */ 
/*     */   public void updateStepOfChildDepts(int stepDiff, List<Department> depts)
/*     */   {
/* 247 */     for (Department dept : depts) {
/* 248 */       dept.setStep(dept.getStep() + stepDiff);
/* 249 */       this.departmentDao.storeDepartment(dept);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void joinUsersForDepartment(String[] userIds, Department department)
/*     */   {
/* 258 */     for (int i = 0; i < userIds.length; i++) {
/* 259 */       User user = this.userDao.loadUser(Long.valueOf(userIds[i]));
/* 260 */       department.getUsers().add(user);
/*     */     }
/* 262 */     this.departmentDao.storeDepartment(department);
/*     */   }
/*     */ 
/*     */   public List displaySortUser(Long departmentId)
/*     */   {
/* 274 */     return this.departmentDao.displaySortUser(departmentId);
/*     */   }
/*     */ 
/*     */   public void disabledAllDepartment(Collection<Department> departments)
/*     */   {
/* 287 */     for (Department dept : departments) {
/* 288 */       dept.setDisabled(true);
/* 289 */       this.departmentDao.storeDepartment(dept);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledAllDepartment(Collection<Department> departments)
/*     */   {
/* 298 */     for (Department dept : departments) {
/* 299 */       dept.setDisabled(false);
/* 300 */       this.departmentDao.storeDepartment(dept);
/*     */     }
/*     */   }
/*     */ 
/*     */   public List<Department> InstitutionSelectDept(String instId, boolean isNew) {
/* 305 */     List list = this.departmentDao.InstitutionSelectDept(Long.valueOf(instId), isNew);
///* 306 */     Department dept = new Department();
///* 307 */     list.add(0, dept);
/* 308 */     return list;
/*     */   }
/*     */ 
/*     */   public List<Department> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/* 320 */     return this.departmentDao.loadByKey(keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public int getDeptSteps()
/*     */   {
/* 330 */     return this.departmentDao.getDeptSteps();
/*     */   }
/*     */ 
/*     */   public List<Department> getDepartments(boolean isNew, String name, Long id) {
/* 334 */     if (isNew) {
/* 335 */       List<Department> list = createSelectDepartments("");
/* 336 */       List enableDept = new ArrayList();
/*     */ 
/* 338 */       for (Department dept : list) {
/* 339 */         if (!dept.getDisabled()) {
/* 340 */           enableDept.add(dept);
/*     */         }
/*     */       }
/* 343 */       return enableDept;
/*     */     }
/* 345 */     List list = loadAllDepartments();
/*     */ 
/* 347 */     Department dept = new Department();
/* 348 */     dept.setId(Long.valueOf(id.longValue()));
/* 349 */     dept.setName(name);
/* 350 */     list.add(0, dept);
/* 351 */     return list;
/*     */   }
/*     */ 
/*     */   public List<Department> getDepartments()
/*     */   {
/* 356 */     List<Department> list = createSelectDepartments("");
/* 357 */     List enableDept = new ArrayList();
/*     */ 
/* 359 */     for (Department dept : list) {
/* 360 */       if (!dept.getDisabled()) {
/* 361 */         enableDept.add(dept);
/*     */       }
/*     */     }
/* 364 */     return enableDept;
/*     */   }
/*     */ 
/*     */   public List<Department> getDeptsByUserId(Long userId, String flag)
/*     */     throws Exception
/*     */   {
/* 379 */     return this.departmentDao.getDeptsByUserId(userId, flag);
/*     */   }
/*     */ 
/*     */   public List<Department> getDeptsByInstIdInUser(Long instId, String flag)
/*     */     throws Exception
/*     */   {
/* 394 */     Long userId = this.userManager.getUser().getId();
/* 395 */     List depts = getDeptsByUserId(userId, flag);
/* 396 */     if (instId.longValue() == -1L) {
/* 397 */       return null;
/*     */     }
/* 399 */     List deptlist = new ArrayList();
/* 400 */     for (int i = 0; i < depts.size(); i++) {
/* 401 */       if (((Department)depts.get(i)).getInst().getId().equals(instId)) {
/* 402 */         deptlist.add(depts.get(i));
/*     */       }
/*     */     }
/* 405 */     return deptlist;
/*     */   }
/*     */ 
/*     */   public List<Department> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 411 */     return null;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.base.org.pojo.DefaultDepartmentManager
 * JD-Core Version:    0.6.2
 */
/*     */ package com.yongjun.tdms.dao.base.org.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.base.institution.Institution;
/*     */ import com.yongjun.pluto.model.security.Department;
/*     */ import com.yongjun.tdms.dao.base.org.DepartmentDao;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.ResultSetMetaData;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import org.hibernate.HibernateException;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
/*     */ import org.hibernate.Transaction;
/*     */ import org.springframework.orm.hibernate3.HibernateCallback;
/*     */ import org.springframework.orm.hibernate3.HibernateTemplate;
/*     */ 
/*     */ public class HibernateDepartment extends BaseHibernateDao
/*     */   implements DepartmentDao
/*     */ {
/*     */   public void storeDepartment(Department department)
/*     */   {
/*  53 */     store(department);
/*  54 */     getHibernateTemplate().flush();
/*     */   }
/*     */ 
/*     */   public Department loadDepartment(Long departmentId) {
/*  58 */     return (Department)load(Department.class, departmentId);
/*     */   }
/*     */ 
/*     */   public void deleteDepartment(Department department) {
/*  62 */     delete(department);
/*     */   }
/*     */ 
/*     */   public void deleteAllDepartments(Collection<Department> departmentIds) {
/*  66 */     deleteAll(departmentIds);
/*     */   }
/*     */ 
/*     */   public List<Department> loadAllDepartment(Long[] departmentIds) {
/*  70 */     return loadAll(Department.class, departmentIds);
/*     */   }
/*     */ 
/*     */   public List<Department> loadAllDepartments()
/*     */   {
/*  78 */     return (List)getHibernateTemplate().execute(new HibernateCallback()
/*     */     {
/*     */       public Object doInHibernate(Session session) throws HibernateException, SQLException
/*     */       {
/*  82 */         return session.getNamedQuery("LoadAllDepartmentsBySortIdx").list();
/*     */       }
/*     */     });
/*     */   }
/*     */ 
/*     */   public List<Integer> getDeptStepAfterGroupingByStep()
/*     */   {
/*  89 */     return (List)getHibernateTemplate().execute(new HibernateCallback() {
/*     */       public Object doInHibernate(Session session) throws HibernateException {
/*  91 */         return session.getNamedQuery("getDeptStepAfterGroupingByStep").list();
/*     */       }
/*     */     });
/*     */   }
/*     */ 
/*     */   public List<Department> getDeptsByStep(final int step)
/*     */   {
/*  99 */     return (List)getHibernateTemplate().execute(new HibernateCallback()
/*     */     {
/*     */       public Object doInHibernate(Session session) throws HibernateException {
/* 102 */         return session.getNamedQuery("getDeptsByStep").setParameter("step", Integer.valueOf(step)).list();
/*     */       }
/*     */     });
/*     */   }
/*     */ 
/*     */   public List<Department> getDeptsByParentDept(final Long parentId)
/*     */   {
/* 111 */     return (List)getHibernateTemplate().execute(new HibernateCallback()
/*     */     {
/*     */       public Object doInHibernate(Session session) throws HibernateException {
/* 114 */         return session.getNamedQuery("getDeptsByParentDept").setParameter("parentId", parentId).list();
/*     */       }
/*     */     });
/*     */   }
/*     */ 
/*     */   public List displaySortUser(Long departmentId)
/*     */   {
/* 256 */     Transaction tx = null;
/* 257 */     Connection conn = null;
/* 258 */     PreparedStatement resultList = null;
/* 259 */     ResultSet result = null;
/* 260 */     Session session = getSession();
/* 261 */     List list = new ArrayList();
/*     */     try {
/* 263 */       tx = session.beginTransaction();
/* 264 */       conn = session.connection();
/* 265 */       String resultSet = "select *from t_department_user where DEPARTMENT_ID=" + departmentId;
/* 266 */       resultList = conn.prepareStatement(resultSet);
/* 267 */       result = resultList.executeQuery();
/* 268 */       while (result.next())
/*     */       {
/* 270 */         for (int i = 2; i < result.getMetaData().getColumnCount() + 1; i += 3)
/*     */         {
/* 272 */           list.add(Long.valueOf(result.getLong("USER_ID")));
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/*     */     }
/*     */     finally {
/* 279 */       releaseSession(session);
/*     */     }
/* 281 */     return list;
/*     */   }
/*     */ 
/*     */   public List<Department> InstitutionSelectDept(Long instId, boolean isNew)
/*     */   {
/* 291 */     String hql = null;
/* 292 */     if (isNew)
/* 293 */       hql = "select dept.id,dept.name from Department as dept where dept.inst.id = " + instId;
/*     */     else {
/* 295 */       hql = "select dept.id,dept.name from Department as dept where dept.disabled = false and dept.inst.id = " + instId;
/*     */     }
/* 297 */     Session session = getSession();
/* 298 */     List list = new ArrayList();
/* 299 */     list = session.createQuery(hql).list();
/* 300 */     return list;
/*     */   }
/*     */ 
/*     */   public List<Department> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/* 312 */     return loadByKey(Department.class, keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public int getDeptSteps()
/*     */   {
/* 322 */     int count = ((Integer)getSession().getNamedQuery("GetDeptSteps").uniqueResult()).intValue();
/* 323 */     return count + 1;
/*     */   }
/*     */ 
/*     */   public List<Department> getDeptsByUserId(Long userId, String flag)
/*     */     throws Exception
/*     */   {
/* 338 */     List depts = getDeptByUserId(userId);
/*     */ 
/* 340 */     String sql = "";
/* 341 */     if ("search".equals(flag))
/* 342 */       sql = "select * from t_department as dept where dept.id in (select du.department_id from t_department_user as du where du.user_id = " + userId + ")";
/* 343 */     else if ("edit".equals(flag)) {
/* 344 */       sql = "select * from t_department as dept where dept.disabled=0 and dept.id in (select du.department_id from t_department_user as du where du.user_id = " + userId + ")";
/*     */     }
/* 346 */     Session session = getSession();
/* 347 */     ResultSet rs = null;
/*     */     try {
/* 349 */       rs = session.connection().createStatement().executeQuery(sql);
/*     */       Department d;
/* 350 */       if (rs != null) {
/* 351 */         while (rs.next()) {
/* 352 */           d = new Department();
/* 353 */           d.setId(Long.valueOf(rs.getLong("id")));
/* 354 */           d.setName(rs.getString("name"));
/* 355 */           Institution inst = new Institution();
/* 356 */           inst.setId(Long.valueOf(rs.getLong("inst_id")));
/* 357 */           d.setInst(inst);
/* 358 */           depts.add(d);
/*     */         }
/*     */       }
/* 361 */       return depts;
/*     */     } finally {
/* 363 */       if (rs != null) {
/* 364 */         rs.close();
/*     */       }
/* 366 */       releaseSession(session);
/*     */     }
/*     */   }
/*     */ 
/*     */   public List<Department> getDeptByUserId(Long userId)
/*     */     throws Exception
/*     */   {
/* 379 */     String sql = "select * from t_department as dept where dept.id = (select u.department_id from t_users as u where u.id =" + userId + ")";
/* 380 */     Session session = getSession();
/* 381 */     ResultSet rs = null;
/*     */     try
/*     */     {
/* 384 */       rs = session.connection().createStatement().executeQuery(sql);
/* 385 */       List depts = new ArrayList();
/*     */       Department d;
/* 386 */       if (rs != null) {
/* 387 */         while (rs.next()) {
/* 388 */           d = new Department();
/* 389 */           d.setId(Long.valueOf(rs.getLong("id")));
/* 390 */           d.setName(rs.getString("name"));
/* 391 */           Institution inst = new Institution();
/* 392 */           inst.setId(Long.valueOf(rs.getLong("inst_id")));
/* 393 */           d.setInst(inst);
/* 394 */           depts.add(d);
/*     */         }
/*     */       }
/* 397 */       return depts;
/*     */     } finally {
/* 399 */       if (rs != null) {
/* 400 */         rs.close();
/*     */       }
/* 402 */       releaseSession(session);
/*     */     }
/*     */   }
/*     */ 
/*     */   public List<Department> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
/* 407 */     return loadByKeyArray(Department.class, keyNames, keyNames);
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.base.org.hibernate.HibernateDepartment
 * JD-Core Version:    0.6.2
 */
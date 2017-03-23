/*    */ package com.yongjun.tdms.dao.project.projectInfoPersonnels.hibernate;
/*    */ 
/*    */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*    */ import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.project.ProjectInfoDao;
import com.yongjun.tdms.dao.project.projectInfoPersonnels.ProjectInfoPersonnelDao;
import com.yongjun.tdms.model.project.ProjectInfo;
import com.yongjun.tdms.model.project.projectInfoPersonnels.ProjectInfoPersonnels;

import java.sql.SQLException;
/*    */ import java.util.Collection;
/*    */ import java.util.List;

import org.aspectj.apache.bcel.generic.Select;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
/*    */ 
/*    */ public class HibernateProjectInfoPersonnels extends BaseHibernateDao
/*    */   implements ProjectInfoPersonnelDao
/*    */ {
	 			public void deleteAllProjectInfoPersonnels(Collection<ProjectInfoPersonnels> ProjectInfoPersonnelss)
	 /*    */   {
	 /* 22 */     deleteAll(ProjectInfoPersonnelss);
	 /*    */   }
	 /*    */ 
	 /*    */   public void deleteProjectInfoPersonnels(ProjectInfoPersonnels ProjectInfoPersonnels) {
	 /* 26 */     delete(ProjectInfoPersonnels);
	 /*    */   }
	 /*    */ 
	 /*    */   public List<ProjectInfoPersonnels> loadAllProjectInfoPersonnels(Long[] ProjectInfoPersonnelsIds) {
	 /* 30 */     return loadAll(ProjectInfoPersonnels.class, ProjectInfoPersonnelsIds);
	 /*    */   }
	 /*    */ 
	 /*    */   public List<ProjectInfoPersonnels> loadAllProjectInfoPersonnelss() {
	 /* 34 */     return loadAll(ProjectInfoPersonnels.class);
	 /*    */   }
	 /*    */ 
	 /*    */   public ProjectInfoPersonnels loadProjectInfoPersonnels(Long ProjectInfoPersonnelsId) {
	 /* 38 */     return (ProjectInfoPersonnels)load(ProjectInfoPersonnels.class, ProjectInfoPersonnelsId);
	 /*    */   }
	 /*    */ 
	 /*    */   public List<ProjectInfoPersonnels> loadByKey(String keyName, Object keyValue) throws DaoException {
	 /* 42 */     return loadByKey(ProjectInfoPersonnels.class, keyName, keyValue);
	 /*    */   }
	 /*    */ 
	 /*    */   public List<ProjectInfoPersonnels> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
	 /* 46 */     return loadByKeyArray(ProjectInfoPersonnels.class, keyNames, keyValues);
	 /*    */   }
	 /*    */ 
	 /*    */   public void storeProjectInfoPersonnels(ProjectInfoPersonnels ProjectInfoPersonnels) {
	 /* 50 */     store(ProjectInfoPersonnels);
	 /*    */   }
	            public List<String> loadPersonnelsCodeByProjectInfoId(final Long projectInfoId){
	            	return  this.getHibernateTemplate().executeFind(new HibernateCallback() {
						public Object doInHibernate(Session session) throws HibernateException, SQLException {
							String hql = "select pp.proPerson.code from com.yongjun.tdms.model.project.projectInfoPersonnels.ProjectInfoPersonnels pp where pp.projectInfo.id="+projectInfoId;
							Query query = session.createQuery(hql);
							return query.list();
						}
					});
	            }
	            public List<String> loadPersonnelsCodeByEnable(){
	            	return  this.getHibernateTemplate().executeFind(new HibernateCallback() {
						public Object doInHibernate(Session session) throws HibernateException, SQLException {
							String hql = "select pp.code from com.yongjun.tdms.model.personnelFiles.PersonnelFiles pp where pp.disabled=0";
							Query query = session.createQuery(hql);
							return query.list();
						}
					});
	            }
/*    */   
/*    */ }


/*    */ package com.yongjun.tdms.dao.project.hibernate;
/*    */ 
/*    */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*    */ import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.project.ProjectInfoDao;
import com.yongjun.tdms.model.project.ProjectInfo;

/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ 
/*    */ public class HibernateProjectInfo extends BaseHibernateDao
/*    */   implements ProjectInfoDao
/*    */ {
	 			public void deleteAllProjectInfo(Collection<ProjectInfo> ProjectInfos)
	 /*    */   {
	 /* 22 */     deleteAll(ProjectInfos);
	 /*    */   }
	 /*    */ 
	 /*    */   public void deleteProjectInfo(ProjectInfo ProjectInfo) {
	 /* 26 */     delete(ProjectInfo);
	 /*    */   }
	 /*    */ 
	 /*    */   public List<ProjectInfo> loadAllProjectInfo(Long[] ProjectInfoIds) {
	 /* 30 */     return loadAll(ProjectInfo.class, ProjectInfoIds);
	 /*    */   }
	 /*    */ 
	 /*    */   public List<ProjectInfo> loadAllProjectInfos() {
	 /* 34 */     return loadAll(ProjectInfo.class);
	 /*    */   }
	 /*    */ 
	 /*    */   public ProjectInfo loadProjectInfo(Long ProjectInfoId) {
	 /* 38 */     return (ProjectInfo)load(ProjectInfo.class, ProjectInfoId);
	 /*    */   }
	 /*    */ 
	 /*    */   public List<ProjectInfo> loadByKey(String keyName, Object keyValue) throws DaoException {
	 /* 42 */     return loadByKey(ProjectInfo.class, keyName, keyValue);
	 /*    */   }
	 /*    */ 
	 /*    */   public List<ProjectInfo> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
	 /* 46 */     return loadByKeyArray(ProjectInfo.class, keyNames, keyValues);
	 /*    */   }
	 /*    */ 
	 /*    */   public void storeProjectInfo(ProjectInfo ProjectInfo) {
	 /* 50 */     store(ProjectInfo);
	 /*    */   }
	 			public List<ProjectInfo> loadByDate(String date,String name){
	 				 String hql = "from ProjectInfo p where convert(varchar,p.createdTime,120) like '"+date+"%' and p.creator = '" + name + "'";
	 				 return getSession().createQuery(hql).list();
	 			}
/*    */   
/*    */ }


/*    */ package com.yongjun.tdms.dao.project.projectInfoPlan.hibernate;
/*    */ 
/*    */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*    */ import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.project.projectInfoPlan.ProjectInfoPlanDao;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.project.projectInfoPlan.ProjectInfoPlan;

import java.util.ArrayList;
/*    */ import java.util.Collection;
import java.util.HashMap;
/*    */ import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
/*    */ 
/*    */ public class HibernateProjectInfoPlan extends BaseHibernateDao
/*    */   implements ProjectInfoPlanDao
/*    */ {
	 			public void deleteAllProjectInfoPlan(Collection<ProjectInfoPlan> ProjectInfoPlans)
	 /*    */   {
	 /* 22 */     deleteAll(ProjectInfoPlans);
	 /*    */   }
	 /*    */ 
	 /*    */   public void deleteProjectInfoPlan(ProjectInfoPlan ProjectInfoPlan) {
	 /* 26 */     delete(ProjectInfoPlan);
	 /*    */   }
	 /*    */ 
	 /*    */   public List<ProjectInfoPlan> loadAllProjectInfoPlan(Long[] ProjectInfoPlanIds) {
	 /* 30 */     return loadAll(ProjectInfoPlan.class, ProjectInfoPlanIds);
	 /*    */   }
	 /*    */ 
	 /*    */   public List<ProjectInfoPlan> loadAllProjectInfoPlans() {
	 /* 34 */     return loadAll(ProjectInfoPlan.class);
	 /*    */   }
	 /*    */ 
	 /*    */   public ProjectInfoPlan loadProjectInfoPlan(Long ProjectInfoPlanId) {
	 /* 38 */     return (ProjectInfoPlan)load(ProjectInfoPlan.class, ProjectInfoPlanId);
	 /*    */   }
	 /*    */ 
	 /*    */   public List<ProjectInfoPlan> loadByKey(String keyName, Object keyValue) throws DaoException {
	 /* 42 */     return loadByKey(ProjectInfoPlan.class, keyName, keyValue);
	 /*    */   }
	 /*    */ 
	 /*    */   public List<ProjectInfoPlan> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
	 /* 46 */     return loadByKeyArray(ProjectInfoPlan.class, keyNames, keyValues);
	 /*    */   }
	 /*    */ 
	 /*    */   public void storeProjectInfoPlan(ProjectInfoPlan ProjectInfoPlan) {
	 /* 50 */     store(ProjectInfoPlan);
	 /*    */   }
	 			public List loadForMyTeam(HashMap map) {
	 				
		 /*  54 */     Session session = getSession();
		 /*     */     try {
			           StringBuffer sb = new StringBuffer();
			           sb.append("from ProjectInfoPlan p where 1=1 ");
			           if(map.get("code")!=null){
			        	   sb.append(" and p.personnelFiles.code ='"+map.get("code")+"'");
			           }
			           if(map.get("state")!=null){
			        	   sb.append(" and p.planState.code in ("+map.get("state")+")");
			           }
			           sb.append(" order by p.endDate desc");
		 /*  57 */       Query query = session.createQuery(sb.toString());
		 /*  58 */       List list = query.list();
		                
		 /*  59 */       return list;
		 /*     */     } finally {
		 /*  61 */       releaseSession(session);
		 /*     */     }
		 /*     */   }
/*    */   
/*    */ }


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
	 			public List loadByTeam(String code) {
	 				
		 /*  54 */     Session session = getSession();
		 /*  55 */     String hql = "select p.personnelFiles.id ,p.personnelFiles.name ,count(*)  from ProjectInfoPlan p where p.personnelFiles.superiorLeader.code='"+code+"' group by p.personnelFiles.id,p.personnelFiles.name ";
		 /*     */     try {
		 /*  57 */       Query query = session.createQuery(hql);
		 /*  58 */       List list = query.list();
		                 List<Map> mapList = new ArrayList<Map>();
		                 if(list!=null&&list.size()>0){
		                	 for(int i=0;i<list.size();i++){
		                		 Object[] obj =( Object[])list.get(i);
		                		 HashMap map =new HashMap();
		                		 map.put("pid", obj[0]);
		                		 map.put("pname", obj[1]);
		                		 map.put("num", obj[2]);
		                		 mapList.add(map);
		                	 }
		                 }
		                
		 /*  59 */       return mapList;
		 /*     */     } finally {
		 /*  61 */       releaseSession(session);
		 /*     */     }
		 /*     */   }
/*    */   
/*    */ }


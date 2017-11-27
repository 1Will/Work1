/*    */ package com.yongjun.tdms.dao.workspace.data.hibernate;
/*    */ 
/*    */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*    */ import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.workspace.data.DataDao;
import com.yongjun.tdms.model.workspace.data.Data;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
/*    */ 
/*    */ public class HibernateDataDao extends BaseHibernateDao
/*    */   implements DataDao
/*    */ {
/*    */ 
/*    */   public List<Data> loadAllData(Long[] lbIds) {
/* 25 */     return loadAll(Data.class, lbIds);
/*    */   }
/*    */ 
/*    */   public List<Data> loadAllData() {
/* 29 */     return loadAll(Data.class);
/*    */   }
/*    */ 
/*    */   public List<Data> loadByKey(String keyName, Object keyValue) throws DaoException
/*    */   {
/* 34 */     return loadByKey(Data.class, keyName, keyValue);
/*    */   }
/*    */ 
/*    */   public List<Data> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException
/*    */   {
/* 39 */     return loadByKeyArray(Data.class, keyNames, keyValues);
/*    */   }
/*    */ 
/*    */   public Data loadData(Long lbId) {
/* 43 */     return (Data)get(Data.class, lbId);
/*    */   }
/*    */ 
/*    */
			public void storeData(Data data) {
				store(data);
	
			}
			public HashMap getDataMap(String staDate,String endDate){
				HashMap map =new HashMap();
				Session session =getSession();
				String hqlCount ="select count(*) from ContractManagement where ciemdinghTime >= CAST('"+staDate+"' AS DATETIME )  AND ciemdinghTime <= CAST('"+endDate+"' AS DATETIME )  ";
				List countList =session.createQuery(hqlCount).list();
				if ((null != countList) && (countList.size() > 0)) {
					Integer object  =  (Integer) countList.get(0);
				map.put("count",object);
				}
				String hqlMoney ="select isnull(sum(c.contractMoney),0) from ContractManagement c where c.ciemdinghTime >= CAST('"+staDate+"' AS DATETIME )  AND ciemdinghTime <= CAST('"+endDate+"' AS DATETIME )  ";
				List moneytList =session.createQuery(hqlMoney).list();
				if ((null != moneytList) && (moneytList.size() > 0)) {
					Double object  =  (Double) moneytList.get(0);
				map.put("money", object);
				}
				return map;
				
			}
			public Object loadAllDataByYear(HashMap map) {
				Session session =getSession();
				StringBuffer sf = new StringBuffer();
				sf.append("select isnull(sum( d.contractManagementMoney ),0),isnull(sum( d.contractManagementNum ),0),isnull(sum( d.financialManagementNum ),0), ")
				   .append("isnull(sum( d.financialManagementMoney ),0),isnull(sum( d.billingRecordNum ),0),isnull(sum( d.billingRecordMoney ),0)")
				   .append(" from Data d where 1=1");
				if(map.get("year")!=null){
					sf.append(" and d.year like '%"+map.get("year")+"%'");
				}
				if(map.get("code")!=null){
					sf.append(" and d.personnelFiles.code ='"+map.get("code")+"'");
				}
				Query query = session.createQuery(sf.toString());
				return query.uniqueResult();
			}
			
			public List<Data> loadAllDataByTeam(HashMap map){
				StringBuffer sf = new StringBuffer();
				sf.append(" from Data  d   where 1=1  ");
				if (map.get("personnelFilesId")!=null&&!map.get("personnelFilesId").equals("")) {
					sf.append(" and  d.personnelFiles.id in ("+(String)map.get("personnelFilesId")+")");
				}
				if (map.get("sfMonth")!=null&&!map.get("sfMonth").equals("")) {
					sf.append(" and  d.month  = '"+(String)map.get("sfMonth")+"'");
				}
				sf.append(" order by d.id desc ");
			 return 	this.getHibernateTemplate().find(sf.toString());
			}
		}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.workspace.leaveBill.hibernate.HibernateLeaveBillDao
 * JD-Core Version:    0.6.2
 */
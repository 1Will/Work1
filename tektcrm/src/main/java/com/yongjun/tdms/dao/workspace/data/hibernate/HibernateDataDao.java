 package com.yongjun.tdms.dao.workspace.data.hibernate;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.workspace.data.DataDao;
import com.yongjun.tdms.model.workspace.data.Data;
@SuppressWarnings("unchecked")
 public class HibernateDataDao extends BaseHibernateDao
   implements DataDao
 {
 
   public List<Data> loadAllData(Long[] lbIds) {
     return loadAll(Data.class, lbIds);
   }
 
   public List<Data> loadAllData() {
     return loadAll(Data.class);
   }
 
   public List<Data> loadByKey(String keyName, Object keyValue) throws DaoException
   {
     return loadByKey(Data.class, keyName, keyValue);
   }
 
   public List<Data> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException
   {
     return loadByKeyArray(Data.class, keyNames, keyValues);
   }
 
   public Data loadData(Long lbId) {
     return (Data)get(Data.class, lbId);
   }
 

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
			public List<Object[]> loadAllDataStatisticMonthByContion() {
				return this.getHibernateTemplate().executeFind(new HibernateCallback() {

					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						StringBuffer sql = new StringBuffer();
						sql.append("SELECT temp.[month] mm, SUM(temp.contractManagementNum) cm  FROM(")
						.append("select [month],contractManagementNum from t_data ) temp")
						.append("GROUP BY temp.[month] ORDER BY [month]");
						return session.createSQLQuery(sql.toString()).addScalar("mm", Hibernate.STRING).addScalar("cm", Hibernate.STRING)
								.list();
					}
					
				});
			}
			public List<Object[]> loadAllDataStatisticDayByContion() {
				// TODO Auto-generated method stub
				return null;
			}
			//根据条件查询年份数据
			public List<Object[]> loadAllMyDataYearByContion(String timeStart,String timeEnd,String businessType ,String classification,String flag) {
				// TODO Auto-generated method stub
				final String ts = timeStart;
				final String te = timeEnd;
				final String b = businessType;
				final String f = flag;
				final String i = classification;
				return this.getHibernateTemplate().executeFind(new HibernateCallback() {

					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						StringBuffer sql = new StringBuffer();
						sql.append("select b.ayear ay,isnull(sum(b.cnum), 0) cn,isnull(sum(b.cumoney), 0) cu,isnull(sum(b.fnum), 0) fn,")
						.append("isnull(sum(b.fmoney), 0) fm,isnull(sum(b.bnum), 0) bn,isnull(sum(b.bmoney), 0) bm")
						.append(" from(select a.year ayear,a.month  amonth,contractManagementNum   cnum,contractManagementMoney cumoney,financialManagementNum  fnum,")
						.append("financialManagementMoney fmoney,billingRecordNum   bnum,billingRecordMoney bmoney,")
						.append("p.NAME  pname, p.businessType  ptype,d.NAME dname,d.id")
						.append(" from t_data a, t_personnelFiles p, t_department d")
						.append(" where a.personnelFiles_id = p.Id")
						.append(" and p.DEPT_ID = d.id");
						if(i.equals("-1")&& b.equals("-1")){
						   
							} else {
								if(!b.equals("-1") && i.equals("-1")){
									sql.append(" and p.businessType = "+b);
								}else if(b.equals("-1") && !i.equals("-1")){
									sql	.append(" and d.id = "+i);
								}else{
									sql.append(" and p.businessType = "+b);
									sql	.append(" and d.id = "+i);
								}
							}
								
						
						sql.append(") b");
						if(!ts.equals("") &&!te.equals("")){
						      sql.append(" where '"+ts+"' <=b.ayear and '"+te+"'>=b.ayear  group by b.ayear ORDER BY b.ayear DESC");
						}else{
							if(!ts.equals("") && te.equals("")){
							sql.append(" where b.ayear like '%"+ts+"%'  group by b.ayear ORDER BY b.ayear DESC");
							}else if(ts.equals("") && !te.equals("")){
								sql.append(" where b.ayear like '%"+te+"%'  group by b.ayear ORDER BY b.ayear DESC");
							}else{
								sql.append(" where b.ayear like '%%'  group by b.ayear ORDER BY b.ayear DESC");
							}
						}
						return  session.createSQLQuery(sql.toString()).addScalar("ay",Hibernate.STRING).addScalar("cn",Hibernate.LONG)
								.addScalar("cu",Hibernate.DOUBLE).addScalar("fn",Hibernate.LONG).addScalar("fm",Hibernate.DOUBLE)
								.addScalar("bn",Hibernate.LONG).addScalar("bm",Hibernate.DOUBLE).list();
					}
					
				});
			}
			//根据条件查询月份数据
			public List<Object[]> loadAllMyDataMonthByContion(String timeStart,String timeEnd,String businessType ,String classification,String flag ) {
				final String ts = timeStart;
				final String te = timeEnd;
				final String b = businessType;
				final String f = flag;
				final String i = classification;
				// TODO Auto-generated method stub
				return this.getHibernateTemplate().executeFind(new HibernateCallback() {

					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						StringBuffer sql = new StringBuffer();
						sql.append("select b.amonth am,isnull(sum(b.cnum), 0) cn,isnull(sum(b.cumoney), 0) cu,isnull(sum(b.fnum), 0) fn,")
						.append("isnull(sum(b.fmoney), 0) fm,isnull(sum(b.bnum), 0) bn,isnull(sum(b.bmoney), 0) bm")
						.append(" from (select a.year ayear,a.month  amonth,contractManagementNum   cnum,contractManagementMoney cumoney,financialManagementNum  fnum,")
						.append("financialManagementMoney fmoney,billingRecordNum   bnum,billingRecordMoney bmoney,")
						.append("p.NAME  pname, p.businessType  ptype,d.NAME dname,d.id")
						.append(" from  t_data a,t_personnelFiles p,t_department d")
						.append(" where  a.personnelFiles_id = p.Id")
						.append(" and  p.DEPT_ID = d.id");
						if(i.equals("-1")&& b.equals("-1")){
							   
						} else {
							if(!b.equals("-1") && i.equals("-1")){
								sql.append(" and p.businessType = "+b);
							}else if(b.equals("-1") && !i.equals("-1")){
								sql	.append(" and d.id = "+i);
							}else{
								sql.append(" and p.businessType = "+b);
								sql	.append(" and d.id = "+i);
							}
						}
							sql.append(") b");
						if(!ts.equals("") &&!te.equals("")){
						      sql.append(" where b.amonth >='"+ts+"' and b.amonth<='"+te+"'  group by b.amonth ORDER BY b.amonth DESC");
						}else{
							if(!ts.equals("") && te.equals("")){
							sql.append(" where b.amonth like '%"+ts+"%'  group by b.amonth ORDER BY b.amonth DESC");
							}else if(ts.equals("") && !te.equals("")){
								sql.append(" where b.amonth like '%"+te+"%'  group by b.amonth ORDER BY b.amonth DESC");
							}else{
								sql.append(" where b.amonth like '%%'  group by b.amonth ORDER BY b.amonth DESC");
							}
						}
						return  session.createSQLQuery(sql.toString()).addScalar("am",Hibernate.STRING).addScalar("cn",Hibernate.LONG)
								.addScalar("cu",Hibernate.DOUBLE).addScalar("fn",Hibernate.LONG).addScalar("fm",Hibernate.DOUBLE)
								.addScalar("bn",Hibernate.LONG).addScalar("bm",Hibernate.DOUBLE).list();

					}
					
					
				});
			}
			//查询所有年份数据
			public List<Object[]> loadAllMyDataYearByAllContion(String timeStart,String timeEnd,String businessType ,String classification,String flag) {
				// TODO Auto-generated method stub
				//final String m = select;
				final String b = businessType;
				final String f = flag;
				final String i = classification;
				return this.getHibernateTemplate().executeFind(new HibernateCallback() {

					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						StringBuffer sql = new StringBuffer();
						sql.append("select b.ayear ay,isnull(sum(b.cnum), 0) cn,isnull(sum(b.cumoney), 0) cu,isnull(sum(b.fnum), 0) fn,"
						+"isnull(sum(b.fmoney), 0) fm,isnull(sum(b.bnum), 0) bn,isnull(sum(b.bmoney), 0) bm"
						+" from(select a.year ayear,a.month  amonth,contractManagementNum   cnum,contractManagementMoney cumoney,financialManagementNum  fnum,"
						+"financialManagementMoney fmoney,billingRecordNum   bnum,billingRecordMoney bmoney,"
						+"p.NAME  pname, p.businessType  ptype,d.NAME dname,d.id"
						+" from t_data a, t_personnelFiles p, t_department d"
						+" where a.personnelFiles_id = p.Id"
						+" and p.DEPT_ID = d.id"
						+") b"
						+" where b.ayear like '%%'  group by b.ayear ORDER BY b.ayear DESC");
						
						return  session.createSQLQuery(sql.toString()).addScalar("ay",Hibernate.STRING).addScalar("cn",Hibernate.LONG)
								.addScalar("cu",Hibernate.DOUBLE).addScalar("fn",Hibernate.LONG).addScalar("fm",Hibernate.DOUBLE)
								.addScalar("bn",Hibernate.LONG).addScalar("bm",Hibernate.DOUBLE).list();
					}
					
				});
			}
			//查询所有月份数据
			public List<Object[]> loadAllMyDataMonthByAllContion(String timeStart,String timeEnd,String businessType ,String classification,String flag) {
				//final String m = select;
				final String b = businessType;
				final String f = flag;
				final String i = classification;
				// TODO Auto-generated method stub
				return this.getHibernateTemplate().executeFind(new HibernateCallback() {

					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						StringBuffer sql = new StringBuffer();
						sql.append("select b.amonth am,isnull(sum(b.cnum), 0) cn,isnull(sum(b.cumoney), 0) cu,isnull(sum(b.fnum), 0) fn,"
						+"isnull(sum(b.fmoney), 0) fm,isnull(sum(b.bnum), 0) bn,isnull(sum(b.bmoney), 0) bm"
						+" from (select a.year ayear,a.month  amonth,contractManagementNum   cnum,contractManagementMoney cumoney,financialManagementNum  fnum,"
						+"financialManagementMoney fmoney,billingRecordNum   bnum,billingRecordMoney bmoney,"
						+"p.NAME  pname, p.businessType  ptype,d.NAME dname,d.id"
						+" from  t_data a,t_personnelFiles p,t_department d"
						+" where  a.personnelFiles_id = p.Id"
						+" and  p.DEPT_ID = d.id"
						+") b"
						+" where  b.amonth like '%%'  group by b.amonth ORDER BY b.amonth DESC");
						return  session.createSQLQuery(sql.toString()).addScalar("am",Hibernate.STRING).addScalar("cn",Hibernate.LONG)
								.addScalar("cu",Hibernate.DOUBLE).addScalar("fn",Hibernate.LONG).addScalar("fm",Hibernate.DOUBLE)
								.addScalar("bn",Hibernate.LONG).addScalar("bm",Hibernate.DOUBLE).list();

					}
					
					
				});
			}
			
			//查询某一年的所有数据饼形图展示图
			/*public List<Object[]> loadAllMyDataByBusinessType(String timeStart,String timeEnd,String flag) {
				// TODO Auto-generated method stub
				final String ts = timeStart;
				final String te = timeEnd;
				final String f = flag;
				
				return this.getHibernateTemplate().executeFind(new HibernateCallback() {
					
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						
						StringBuffer sql = new StringBuffer();
						sql.append("select b.ayear am, isnull(sum(b.cnum), 0) cn,isnull(sum(b.cumoney), 0) cu,isnull(sum(b.fnum), 0) fn,"
						+ "isnull(sum(b.fmoney), 0) fm,isnull(sum(b.bnum), 0) bn,isnull(sum(b.bmoney), 0) bm,b.ptype pt"
						+" from (select a.year ayear,a.month  amonth,contractManagementNum   cnum,contractManagementMoney cumoney,financialManagementNum  fnum,"
						+"financialManagementMoney fmoney,billingRecordNum   bnum,billingRecordMoney bmoney,"
						+"p.NAME  pname, p.businessType  ptype,d.NAME dname,d.id"
						+" from  t_data a,t_personnelFiles p,t_department d"
						+" where  a.personnelFiles_id = p.Id"
						+" and  p.DEPT_ID = d.id"
						+") b where  b.ayear like '%"+ts+"%'  group by b.ayear ,b.ptype ORDER BY b.ayear DESC");
						return  session.createSQLQuery(sql.toString()).addScalar("am",Hibernate.STRING).addScalar("cn",Hibernate.LONG)
								.addScalar("cu",Hibernate.DOUBLE).addScalar("fn",Hibernate.LONG).addScalar("fm",Hibernate.DOUBLE)
								.addScalar("bn",Hibernate.LONG).addScalar("bm",Hibernate.DOUBLE).addScalar("pt",Hibernate.STRING).list();

					}
				});
			}*/
        
			/*public List<Object[]> loadAllMyDataByMilitary(String startTime, String endTime,String flag){
				final String ts = startTime;
				final String f = flag;
				
				return this.getHibernateTemplate().executeFind(new HibernateCallback() {
					
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						
						StringBuffer sql = new StringBuffer();
						sql.append("select b.ayear ay, isnull(sum(b.cnum), 0) cn,isnull(sum(b.cumoney), 0) cu,isnull(sum(b.fnum), 0) fn,"
						+ "isnull(sum(b.fmoney), 0) fm,isnull(sum(b.bnum), 0) bn,isnull(sum(b.bmoney), 0) bm,b.dname dn"
						+" from (select a.year ayear,a.month  amonth,contractManagementNum   cnum,contractManagementMoney cumoney,financialManagementNum  fnum,"
						+"financialManagementMoney fmoney,billingRecordNum   bnum,billingRecordMoney bmoney,"
						+"p.NAME  pname, p.businessType  ptype,d.NAME dname,d.id"
						+" from  t_data a,t_personnelFiles p,t_department d"
						+" where  a.personnelFiles_id = p.Id"
						+" and  p.DEPT_ID = d.id  and p.businessType=537"
						+") b where  b.ayear like '%"+ts+"%'  group by b.ayear ,b.dname ORDER BY b.ayear DESC");
						return  session.createSQLQuery(sql.toString()).addScalar("ay",Hibernate.STRING).addScalar("cn",Hibernate.LONG)
								.addScalar("cu",Hibernate.DOUBLE).addScalar("fn",Hibernate.LONG).addScalar("fm",Hibernate.DOUBLE)
								.addScalar("bn",Hibernate.LONG).addScalar("bm",Hibernate.DOUBLE).addScalar("dn",Hibernate.STRING).list();

					}
				});
			}

			public List<Object[]> loadAllMyDataByCivilian(String startTime, String endTime,String flag) {
				final String ts = startTime;
				final String f = flag;
				
				return this.getHibernateTemplate().executeFind(new HibernateCallback() {
					
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						
						StringBuffer sql = new StringBuffer();
						sql.append("select b.ayear am, isnull(sum(b.cnum), 0) cn,isnull(sum(b.cumoney), 0) cu,isnull(sum(b.fnum), 0) fn,"
						+ "isnull(sum(b.fmoney), 0) fm,isnull(sum(b.bnum), 0) bn,isnull(sum(b.bmoney), 0) bm,b.dname dn"
						+" from (select a.year ayear,a.month  amonth,contractManagementNum   cnum,contractManagementMoney cumoney,financialManagementNum  fnum,"
						+"financialManagementMoney fmoney,billingRecordNum   bnum,billingRecordMoney bmoney,"
						+"p.NAME  pname, p.businessType  ptype,d.NAME dname,d.id"
						+" from  t_data a,t_personnelFiles p,t_department d"
						+" where  a.personnelFiles_id = p.Id"
						+" and  p.DEPT_ID = d.id and p.businessType=538"
						+") b where  b.ayear like '%"+ts+"%'  group by b.ayear ,b.dname  ORDER BY b.ayear  DESC");
						return  session.createSQLQuery(sql.toString()).addScalar("am",Hibernate.STRING).addScalar("cn",Hibernate.LONG)
								.addScalar("cu",Hibernate.DOUBLE).addScalar("fn",Hibernate.LONG).addScalar("fm",Hibernate.DOUBLE)
								.addScalar("bn",Hibernate.LONG).addScalar("bm",Hibernate.DOUBLE).addScalar("dn",Hibernate.STRING).list();

					}
				});
			}
*/
			
			//月度部门金额查询
			public List<Object[]> listStatisticalBusinessTypes(String date,String businessType, String classification) {
				
				final String d = date;
				final String b= businessType;
				final String i = classification;
				return this.getHibernateTemplate().executeFind(new HibernateCallback() {

					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						StringBuffer sql = new StringBuffer();
						sql.append("select b.amonth am, isnull(sum(b.cnum), 0) cn,isnull(sum(b.cumoney), 0) cu,isnull(sum(b.fnum), 0) fn,"
								+ "isnull(sum(b.fmoney), 0) fm,isnull(sum(b.bnum), 0) bn,isnull(sum(b.bmoney), 0) bm,b.dname dn"
								+" from (select a.year ayear,a.month  amonth,contractManagementNum   cnum,contractManagementMoney cumoney,financialManagementNum  fnum,"
								+"financialManagementMoney fmoney,billingRecordNum   bnum,billingRecordMoney bmoney,"
								+"p.NAME  pname, p.businessType  ptype,d.NAME dname,d.id"
								+" from  t_data a,t_personnelFiles p,t_department d"
								+" where  a.personnelFiles_id = p.Id"
								+" and  p.DEPT_ID = d.id ");
								if(i.equals("-1")&& b.equals("-1")){
									   
								} else {
									if(!b.equals("-1") && i.equals("-1")){
										sql.append(" and p.businessType = "+b);
									}else if(b.equals("-1") && !i.equals("-1")){
										sql	.append(" and d.id = "+i);
									}else{
										sql.append(" and p.businessType = "+b);
										sql	.append(" and d.id = "+i);
									}
								}
								sql.append(") b where  b.amonth like '%"+d+"%'  group by b.amonth ,b.dname  ORDER BY b.amonth  DESC");
						return  session.createSQLQuery(sql.toString()).addScalar("am",Hibernate.STRING).addScalar("cn",Hibernate.LONG)
								.addScalar("cu",Hibernate.DOUBLE).addScalar("fn",Hibernate.LONG).addScalar("fm",Hibernate.DOUBLE)
								.addScalar("bn",Hibernate.LONG).addScalar("bm",Hibernate.DOUBLE).addScalar("dn",Hibernate.STRING).list();
					}
					
				});
			}
           //year查询部门
			public List<Object[]> listYearStatisticalBusinessTypes(String dates, String businessType, String classification) {
				final String d = dates;
				final String b= businessType;
				final String i= classification;
				return this.getHibernateTemplate().executeFind(new HibernateCallback() {

					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						StringBuffer sql = new StringBuffer();
						sql.append("select b.ayear ay, isnull(sum(b.cnum), 0) cn,isnull(sum(b.cumoney), 0) cu,isnull(sum(b.fnum), 0) fn,"
								+ "isnull(sum(b.fmoney), 0) fm,isnull(sum(b.bnum), 0) bn,isnull(sum(b.bmoney), 0) bm,b.dname dn"
								+" from (select a.year ayear,a.month  amonth,contractManagementNum   cnum,contractManagementMoney cumoney,financialManagementNum  fnum,"
								+"financialManagementMoney fmoney,billingRecordNum   bnum,billingRecordMoney bmoney,"
								+"p.NAME  pname, p.businessType  ptype,d.NAME dname,d.id"
								+" from  t_data a,t_personnelFiles p,t_department d"
								+" where  a.personnelFiles_id = p.Id"
								+" and  p.DEPT_ID = d.id ");
								if(i.equals("-1")&& b.equals("-1")){
									   
								} else {
									if(!b.equals("-1") && i.equals("-1")){
										sql.append(" and p.businessType = "+b);
									}else if(b.equals("-1") && !i.equals("-1")){
										sql	.append(" and d.id = "+i);
									}else{
										sql.append(" and p.businessType = "+b);
										sql	.append(" and d.id = "+i);
									}
								}
								sql	.append(") b where  b.ayear like '%"+d+"%'  group by b.ayear ,b.dname  ORDER BY b.ayear  DESC");
						
						return  session.createSQLQuery(sql.toString()).addScalar("ay",Hibernate.STRING).addScalar("cn",Hibernate.LONG)
								.addScalar("cu",Hibernate.DOUBLE).addScalar("fn",Hibernate.LONG).addScalar("fm",Hibernate.DOUBLE)
								.addScalar("bn",Hibernate.LONG).addScalar("bm",Hibernate.DOUBLE).addScalar("dn",Hibernate.STRING).list();
					}
					
				});
			}
			//month查询部门
			public List<Object[]> listMonthSumStatisticalBusinessTypes(String startTime, String endTime,String businessType ,String classification,String flag) {
				final String ts = startTime;
				final String te = endTime;
				final String b = businessType;
				final String f = flag;
				final String i = classification;
				return this.getHibernateTemplate().executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						StringBuffer sql = new StringBuffer();
						sql.append("select  isnull(sum(b.cnum), 0) cn,isnull(sum(b.cumoney), 0) cu,isnull(sum(b.fnum), 0) fn,"
								+ "isnull(sum(b.fmoney), 0) fm,isnull(sum(b.bnum), 0) bn,isnull(sum(b.bmoney), 0) bm"
								+" from (select a.year ayear,a.month  amonth,contractManagementNum   cnum,contractManagementMoney cumoney,financialManagementNum  fnum,"
								+"financialManagementMoney fmoney,billingRecordNum   bnum,billingRecordMoney bmoney,"
								+"p.NAME  pname, p.businessType  ptype,d.NAME dname,d.id"
								+" from  t_data a,t_personnelFiles p,t_department d"
								+" where  a.personnelFiles_id = p.Id"
								+" and  p.DEPT_ID = d.id ");
								if(i.equals("-1") && b.equals("-1")){
									
								}else{
									if(!b.equals("-1") && i.equals("-1")){
										sql.append(" and p.businessType = "+b);
									}else if(b.equals("-1") && !i.equals("-1")){
										sql	.append(" and d.id = "+i);
									}else{
										sql.append(" and p.businessType = "+b);
										sql	.append(" and d.id = "+i);
									}
								}
								if(!ts.equals("") &&!te.equals("")){
									sql.append(") b where b.amonth >='"+ts+"' and b.amonth<='"+te+"' ");
								}else{
									sql.append(") b");
								}
						return  session.createSQLQuery(sql.toString()).addScalar("cn",Hibernate.LONG).addScalar("cu",Hibernate.DOUBLE)
								.addScalar("fn",Hibernate.LONG).addScalar("fm",Hibernate.DOUBLE).addScalar("bn",Hibernate.LONG)
								.addScalar("bm",Hibernate.DOUBLE).list();
					}
					
				});
			}
         //任意时间查询部门
			public List<Object[]> listSumStatisticalBusinessTypes(String startTime, String endTime, String businessType, String classification) {
				final String ts = startTime;
				final String te = endTime;
				final String b = businessType;
				final String i = classification;
				return this.getHibernateTemplate().executeFind(new HibernateCallback() {

					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						StringBuffer sql = new StringBuffer();
						sql.append("select b.dname dn, isnull(sum(b.cnum), 0) cn,isnull(sum(b.cumoney), 0) cu,isnull(sum(b.fnum), 0) fn,"
								+ "isnull(sum(b.fmoney), 0) fm,isnull(sum(b.bnum), 0) bn,isnull(sum(b.bmoney), 0) bm"
								+" from (select a.year ayear,a.month  amonth,contractManagementNum   cnum,contractManagementMoney cumoney,financialManagementNum  fnum,"
								+"financialManagementMoney fmoney,billingRecordNum   bnum,billingRecordMoney bmoney,"
								+"p.NAME  pname, p.businessType  ptype,d.NAME dname,d.id"
								+" from  t_data a,t_personnelFiles p,t_department d"
								+" where  a.personnelFiles_id = p.Id"
								+" and  p.DEPT_ID = d.id ");
								if(i.equals("-1")&& b.equals("-1")){
									   
								} else {
									if(!b.equals("-1") && i.equals("-1")){
										sql.append(" and p.businessType = "+b);
									}else if(b.equals("-1") && !i.equals("-1")){
										sql	.append(" and d.id = "+i);
									}else{
										sql.append(" and p.businessType = "+b);
										sql	.append(" and d.id = "+i);
									}
								}
								if(!ts.equals("") &&!te.equals("")){
									sql.append(") b where b.amonth >='"+ts+"' and b.amonth<='"+te+"'   GROUP BY  b.dname  ORDER BY b.dname" );
								}else{
									sql.append(") b  GROUP BY  b.dname  ORDER BY b.dname");
								}
						return  session.createSQLQuery(sql.toString()).addScalar("dn",Hibernate.STRING).addScalar("cn",Hibernate.LONG)
								.addScalar("cu",Hibernate.DOUBLE).addScalar("fn",Hibernate.LONG).addScalar("fm",Hibernate.DOUBLE)
								.addScalar("bn",Hibernate.LONG).addScalar("bm",Hibernate.DOUBLE).list();
					}
					
				});
			}
		}


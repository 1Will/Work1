/*
 * Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of YongJun
 * Technology Pte.,Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with YongJun.
 * 
 * YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 */
package com.yongjun.tdms.dao.asset.spare.hibernate;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.security.User;
import com.yongjun.tdms.dao.asset.spare.SpareDao;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.model.asset.spare.SpareLocation;

/**
 * @author qs
 * @version $Id: HibernateSpare.java 9881 2007-12-28 02:45:28Z qsun $
 */
public class HibernateSpare extends BaseHibernateDao implements SpareDao {
	public List<Spare> loadAllSpares(Long[] spareIds) {
		return this.loadAll(Spare.class, spareIds);
	}
	
	public List<Spare> loadAllSpares(){
		return this.loadAll(Spare.class);
	}

	public Spare loasSpare(Long spareId) {
		return this.load(Spare.class, spareId);
	}

	public void storeSpare(Spare spare) {
		this.store(spare);
	}

	public void deleteSpare(Spare spare) {
		this.delete(spare);
	}

	public void deleteAllSpare(Collection<Spare> spares) {
		this.deleteAll(spares);
	}
	
	public List Query(String[] queryInfo){
//		System.out.println("\n\n\n\nqueryInfo[0]=="+queryInfo[0]+"\nqueryInfo[1]=="+queryInfo[1]+
//                "\nqueryInfo[2]=="+queryInfo[2]+"\nqueryInfo[3]=="+queryInfo[3]+
//                "\nqueryInfo[4]=="+queryInfo[4]+"\nqueryInfo[5]=="+queryInfo[5]+
//                "\nqueryInfo[6]=="+queryInfo[6]);
		List result=null;
		try {
			String hql;
			if(queryInfo[6].equals("TOOLINGDEVICE")){
				hql = " from Spare as spare where 1=1 ";
			}else{
				hql = " from Spare as spare where 1=1 AND spare.toolingDevFlag = '"+queryInfo[6]+"'" ;
			}
			
			if (queryInfo[0] != "") {
				hql += " and spare.spareNo like :spareNo ";
			}
			if (queryInfo[1]!= ""){
				hql += " and spare.name like :name ";
			}
			if (queryInfo[2]!= ""){
				hql += " and spare.enName like :enName ";
			}
			if (queryInfo[3]!= ""){
				hql += " and spare.modelSpecs like :modelSpecs ";
			}
			if (queryInfo[4]!= ""){
				hql += " and spare.department.name =:depart_Name ";
			}
			if (queryInfo[5]!= ""){
				hql += " and spare.category.value =:category ";
			}
			Query query = getSession().createQuery(hql);
			if (queryInfo[0] != "") {
				query.setParameter("spareNo", '%'+queryInfo[0]+'%');
			}
			if(queryInfo[1] != ""){
				query.setParameter("name",'%'+queryInfo[1]+'%');
			}
			if(queryInfo[2] != ""){
				query.setParameter("enName",'%'+queryInfo[2]+'%');
			}
			if(queryInfo[3] != ""){
				query.setParameter("modelSpecs",'%'+queryInfo[3]+'%');
			}
			if(queryInfo[4] != ""){
				query.setParameter("depart_Name",queryInfo[4]);
			}
			if(queryInfo[5] != ""){
				query.setParameter("category",queryInfo[5]);
			}
			
			result = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public String getMaxSpareNoBySpareCode(final String code) {
		return (String) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetMaxSpareNoBySpareCode")
								.setParameter("spareCode", code)
								.uniqueResult();
					}
				});
	}

	public Integer getSpareNumBySpareNo(final String spareNo) {
		return (Integer) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetSpareNumBySpareNo")
								.setParameter("spareNo", spareNo.trim())
								.uniqueResult();
					}
				});
	}
	@SuppressWarnings("unchecked")
	public List<Spare> getSpareCollectionByModelSpares(final String modelSpare) {
		return (List<Spare>) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("getSpareCollentionByModelNo").setParameter("modelSpare",modelSpare).list();
					}
				});
	}
	@SuppressWarnings("unchecked")
	public List getSpareCollectionBySpecification(final String specification) {
		return (List<Spare>) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("getSpareCollentionByspecification").setParameter("specification",specification).list();
					}
				});
	}
	@SuppressWarnings("unchecked")
	public List getSpareCollentionByModel(final String modelSpare) {
		return (List<Spare>) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("getSpareCollentionByModel").setParameter("modelSpare",modelSpare).list();
					}
				});
	}
	@SuppressWarnings("unchecked")
	public List getSpareCollentionBySpec(final String specification) {
		return (List<Spare>) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("getSpareCollentionBySpe").setParameter("specification",specification).list();
					}
				});
	}
	
	public Integer getSpareNumByModelAndName(final String name, final String modelSpare) {
		return (Integer) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("getSpareNumByModelAndName")
								.setParameter("modelSpare", modelSpare.trim())
								.setParameter("name", name.trim())
								.uniqueResult();
					}
				});
	}
	
	public List<Spare> loadByKey(String keyName, Object keyValue) throws DaoException {
		return this.loadByKey(Spare.class, keyName, keyValue);
	}
	
	public Spare loadByPamrameter(final String name, final String modelSpecs,final String orderNo,final String factoryName
			) {
		Spare s = (Spare) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("loadByPamrameter")
								.setParameter("name", name.trim())
								.setParameter("modelSpecs", modelSpecs.trim())
								.setParameter("orderNo", orderNo.trim())
								.setParameter("factoryName", factoryName.trim())
								.uniqueResult();
					}
				});
		//System.out.println("$$$"+s.getName());
		return s;
	}
	
	
	public Spare loadByNameAndModel(final String name, final String modelSpecs) {
		Spare s = (Spare) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						List<Spare> tempList = session.getNamedQuery("loadByNameAndModel")
								.setParameter("name", name.trim())
								.setParameter("modelSpecs", modelSpecs.trim()).list();
						if(tempList!=null&&tempList.size()>0){
							return tempList.get(0);
						}else{
							return null;
						}
					}
				});
		return s;
	}
	
	public Spare loadByFirst() {
		Spare s = (Spare) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						String sql = " from Spare s where s.oldSpare ='0' ";
						Query query = session.createQuery(sql);
						query.setFirstResult(0);
						query.setMaxResults(1);
						List list = query.list();
						if(list!=null&&list.size()>0){
							return list.get(0);
						}
						return null;
					}
				});
		//System.out.println("$$$"+s.getName());
		return s;
	}
	
	public Spare loadBySpareNo(final String spareNo) {
		Spare s = (Spare) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("getSpareBySpareNoNew")
								.setParameter("spareNo", spareNo.trim())
								.uniqueResult();
					}
				});
		//System.out.println("$$$"+s.getName());
		return s;
	}
	@SuppressWarnings("unchecked")
	public List<Spare> loadByKeyArry(String[] value) throws DaoException {
		
		String hql = "from Spare s where 1=1 ";
		
		if(null !=value[0]){
			hql += " AND s.name= '"+value[0]+"'";
		}
		if(null !=value[1]){
			hql += " AND  s.modelSpecs= '"+value[1]+"'";
		}
		
		if("".equals(value[2])|| null == value[2]){
			hql += " AND (s.orderNo is null or s.orderNo ='')";
		}else {
			hql += " AND s.orderNo = '"+value[2]+"'";
		}
		
		if("".equals(value[3]) || null == value[3]){
			hql += " AND s.factory is null";
		}else{
			hql += " AND s.factory.id ="+value[3];
		}
		if(null !=value[4]){
			hql += " AND  s.category.id= "+value[4];
		}
//		if(null !=value[5]){
//			hql += " AND  s.spareDetailType.id= "+value[5];
//		}
		Query query = getSession().createQuery(hql);
		return query.list();
	}
	
	
	/**
	 * 更新备件的总库存
	 * @param spareLocation
	 */
	@SuppressWarnings("unchecked")
	public void updateSpareStocks(SpareLocation spareLocation){
		Spare spare = spareLocation.getSpare();
		Long id= spare.getId();
		//String hql = "update Spare s set s.stocks=(select sum(sl.stocks) from SpareLocation sl where sl.spare="+id+") where s.id="+id;
		//System.out.println("hql= "+hql);
		//Query query = getSession().createQuery(hql);
		 
		//System.out.println("----query.executeUpdate()--"+query.executeUpdate());
		String hql = "select sum(sl.stocks) from SpareLocation sl where sl.spare="+id;
		Query query = getSession().createQuery(hql);
		List list = query.list();
		if(null != list && list.size()>0){
			Long stocks = (Long)query.list().get(0);
			spare.setStocks(stocks);
			this.store(spare);
		}
	 }
	
	
}

package com.yongjun.tdms.dao.runmaintenance.unused.hibernate;

import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.runmaintenance.unused.unusedDao;
import com.yongjun.tdms.model.runmaintenance.unused.UnUsed;

/**
 * <p>
 * Title: HibernateInventoryBill
 * <p>
 * Description:闲置单数据访问实现类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2001 yj-technology
 * </p>
 * <p>
 * Company: www.yj-technology.com
 * </p>
 * 
 * @author zbzhang@yj-technology.com
 * @version $Id: $
 * @see com.yongjun.tdms.dao.runmaintenance.inventory.InventoryBillDao
 */

public class HibernateUnused extends BaseHibernateDao implements unusedDao {

	public UnUsed loadUnUsed(Long UnUsedId) {

		return this.load(UnUsed.class, UnUsedId);
	}

	public List<UnUsed> loadUnUseds(Long[] UnUsedIds) {

		return this.loadAll(UnUsed.class, UnUsedIds);
	}

	public List<UnUsed> loadUnUseds() {

		return this.loadAll(UnUsed.class);
	}

	public void storeUnUsed(UnUsed unUsed) {
		this.store(unUsed);
	}

	public void deleteUnUsed(UnUsed unUsed) {
		this.delete(unUsed);

	}

	public void deleteAllUnUseds(Collection<UnUsed> UnUseds) {
		this.deleteAllUnUseds(UnUseds);

	}
	public List Query(String[] queryInfo) throws HibernateException {
		List result=null;
		Transaction tx = null; 
		Session session = getSession();
		try {
			String hql = " from UnUsed as unused where 1=1 AND unused.toolingDevFlag = '"+queryInfo[11] + "'"
			            + " AND unused.disabled=" + queryInfo[10];
			// hql = " from Unused as unused where 1=1 AND unused.disabled='"+queryInfo[10]+"'";
			if (queryInfo[0] != "") {
				hql += " and unused.code like :code ";
			}
			if (queryInfo[1]!= ""){
				hql += " and unused.name like :name ";
			}
			if (queryInfo[2]!= ""){
				hql += " and unused.asset.deviceNo like :asset.deviceNo ";
			}
			if (queryInfo[3]!= ""&&queryInfo[3].equals("")){
				hql += " and unused.asset.department.id = :asset.department.id ";
			}
			if (queryInfo[4]!= ""){
				hql += " and unused.asset.name like :asset.name ";
			}
			if (queryInfo[5]!= ""){
				hql += " and unused.unUseDate_start =:unUseDate_start";
			}
			if (queryInfo[6]!= ""){
				hql += " and unused.unUseDate_end =:unUseDate_end";
			}
			if (queryInfo[7]!= ""){
				hql += " and unused.usedAprDate_start =:usedAprDate_start";
			}
			if (queryInfo[8]!= ""){
				hql += " and unused.usedAprDate_end =:usedAprDate_end";
			}
			if (queryInfo[9]!= ""){
				hql += " and unused.status =:status";
			}
			tx = session.beginTransaction();
			Query query = session.createQuery(hql);
			if (queryInfo[0] != "") {
				query.setParameter("code", '%'+queryInfo[0]+'%');
			}
			if(queryInfo[1] != ""){
				query.setParameter("name",'%'+queryInfo[1]+'%');
			}
			if(queryInfo[2] != ""){
				query.setParameter("asset.deviceNo",'%'+queryInfo[2]+'%');
			}
			if(queryInfo[3] != ""){
				query.setParameter("asset.department.id",'%'+queryInfo[3]+'%');
			}
			if(queryInfo[4] != ""){
				query.setParameter("asset.name",'%'+queryInfo[4]+'%');
			}
			if(queryInfo[5] != ""){
				query.setParameter("unUseDate_start",queryInfo[5]);
			}
			if(queryInfo[6] != ""){
				query.setParameter("unUseDate_end",queryInfo[6]);
			}
			if(queryInfo[7] != ""){
				query.setParameter("usedAprDate_start",queryInfo[7]);
			}
			if(queryInfo[8] != ""){
				query.setParameter("unUseDate_end",queryInfo[8]);
			}
			if(queryInfo[9] != ""){
				query.setParameter("status",queryInfo[9]);
			}
			//System.out.println("query.getQueryString()"+query.getQueryString());
			result = query.list();
			tx.commit();
			return result;
			//System.out.println("query.getQueryString()size"+result.size());
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		} finally {
        	releaseSession(session);
        }
		
	}
}

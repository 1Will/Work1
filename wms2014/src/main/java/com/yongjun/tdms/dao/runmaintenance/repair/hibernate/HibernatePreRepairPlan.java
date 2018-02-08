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
package com.yongjun.tdms.dao.runmaintenance.repair.hibernate;

import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.runmaintenance.repair.PreRepairPlanDao;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlan;

/**
 * <p>Title: HibernatePreRepairPlan
 * <p>Description: 预防性维修计划数据库访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: HibernatePreRepairPlan.java 103 2008-01-10 06:37:07Z wzou $
 */
public class HibernatePreRepairPlan extends BaseHibernateDao implements PreRepairPlanDao {


	private static final String preRepairPlanViewDao = null;

	public List<PreRepairPlan> loadAllPreRepairPlans(Long[] preRepairPlanIds) {
		return this.loadAll(PreRepairPlan.class,preRepairPlanIds);
	}

	@SuppressWarnings("unchecked")
	public void deleteAllPreRepairPlan(Collection<PreRepairPlan> preRepairPlans) {
		this.deleteAll(preRepairPlans);
	}

	public void storePreRepairPlan(PreRepairPlan preRepairPlan) {
		this.store(preRepairPlan);
	}

	public PreRepairPlan loadPreRepairPlan(Long preRepairPlanId) {
		return this.load(PreRepairPlan.class,preRepairPlanId);
	}

	public void deletePreRepairPlan(PreRepairPlan preRepairPlan) {
		this.delete(preRepairPlan);
	}

	public List Query(String[] queryInfo) throws HibernateException {
		List result=null;
		Transaction tx = null; 
		Session session = getSession();
		try{
			String hql = " from PreRepairPlan as preRepairPlan where 1=1 ";
			if (queryInfo[0] != "") {
				hql += " and preRepairPlan.planNo like :planNo ";
			}
			if (queryInfo[1] != ""){
				hql += " and preRepairPlan.name like :planName";
			}
			if (queryInfo[2] != "") {
				hql += " and preRepairPlan.department.id =:department.id ";
			}
			tx = session.beginTransaction();
			Query query = session.createQuery(hql);
			
			if (queryInfo[0] != "") {
				query.setParameter("planNo", '%'+queryInfo[0]+'%');
			}
			if (queryInfo[1] != "") {
				query.setParameter("planName", '%'+queryInfo[1]+'%');
			}
			if(queryInfo[2] != ""){
				query.setParameter("department.id",queryInfo[2]);
			}
			result = query.list();
			tx.commit();
			return result;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		} finally {
        	releaseSession(session);
        }
	}
}
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
package com.yongjun.tdms.dao.runmaintenance.lubrication.hibernate;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.util.DateUtil;
import com.yongjun.tdms.dao.runmaintenance.lubrication.LubricationPlanDao;
import com.yongjun.tdms.model.runmaintenance.lubrication.LubricationPlan;

/**
 * <p>Title: HibernateLubricationPlan
 * <p>Description: 润滑计划数据库访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: HibernateLubricationPlan.java 10842 2008-02-01 02:23:10Z qsun $
 */
public class HibernateLubricationPlan extends BaseHibernateDao implements
		LubricationPlanDao {
	public void storeLubricationPlan(LubricationPlan lubricationPlan){
		this.store(lubricationPlan);
	}
	
	public LubricationPlan loadLubricationPlan(Long id){
		return this.load(LubricationPlan.class,id);
	}
	
	public void deleteAllLubricationPlan(List<LubricationPlan> list){
		this.deleteAll(list);
	}
	
	public List<LubricationPlan> loadAllLubricationPlan(Long[] lubricationPlanIds){
		return this.loadAll(LubricationPlan.class,lubricationPlanIds);
	}
	
	public List<LubricationPlan> loadAllLubricationPlan(){
		return this.loadAll(LubricationPlan.class);
	}
	
	public List<LubricationPlan> loadAllLubricationPlansByDevice(final LubricationPlan lubricationPlan){
//		return (List<LubricationPlan>) this.getHibernateTemplate().execute(
//				new HibernateCallback() {
//					public Object doInHibernate(Session session)
//					       throws HibernateException, SQLException {
//						return session.getNamedQuery("LoadAllLubricationPlansByDeviceID")
//						.setParameter("deviceId", lubricationPlan.get).list();
//					}
//				});
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<LubricationPlan> loadAllMatchOptionLubricationPlans(Map searchOption) {
		List<LubricationPlan> list = null;
		StringBuffer hql = new StringBuffer("from LubricationPlan lubricationPlan where (1=1) ");
		if (searchOption.containsKey("device.deviceNo")) {
			hql.append("and lubricationPlan.device.deviceNo like :device.deviceNo ");
		}
		if (searchOption.containsKey("device.name")) {
			hql.append("and lubricationPlan.device.name like :device.name  ");
		}
		if (searchOption.containsKey("departmentId")) {
			hql.append("and lubricationPlan.device.department.id = :departmentId  ");
		}
		if (searchOption.containsKey("estimateExecDate_start")) {
			hql.append("and lubricationPlan.estimateExecDate >= :estimateExecDate_start  ");
		}
		if (searchOption.containsKey("estimateExecDate_end")) {
			hql.append("and lubricationPlan.estimateExecDate <= :estimateExecDate_end  ");
		}
		try {
			Query queryLubricationPlan = this.getSession().createQuery(hql.toString());
			if (searchOption.containsKey("device.deviceNo")) {
				queryLubricationPlan.setParameter("device.deviceNo",'%' + searchOption.get("device.deviceNo").toString()+'%');
			}
			if (searchOption.containsKey("device.name")) {
				queryLubricationPlan.setParameter("device.name",'%' + searchOption.get("device.name").toString() + '%');
			}
			if (searchOption.containsKey("departmentId")) {
				queryLubricationPlan.setParameter("departmentId",Long.valueOf(searchOption.get("departmentId").toString()));
			}
			if (searchOption.containsKey("estimateExecDate_start")) {
				queryLubricationPlan.setParameter("estimateExecDate_start",DateUtil.toDate(searchOption.get("estimateExecDate_start").toString(),"yyyy-MM-dd"));
			}
			if (searchOption.containsKey("estimateExecDate_end")) {
				queryLubricationPlan.setParameter("estimateExecDate_end",DateUtil.toDate(searchOption.get("estimateExecDate_end").toString(),"yyyy-MM-dd"));
			}
			list = (List<LubricationPlan>)queryLubricationPlan.list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
		
	}
}

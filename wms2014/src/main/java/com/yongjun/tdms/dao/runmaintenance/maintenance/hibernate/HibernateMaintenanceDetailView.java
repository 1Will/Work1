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
package com.yongjun.tdms.dao.runmaintenance.maintenance.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.runmaintenance.maintenance.MaintenanceDetailViewDao;
import com.yongjun.tdms.model.runmaintenance.maintenance.MaintenanceDetailView;

/**
 * <p>Title: HibernateMaintenanceDetailView
 * <p>Description: 保养计划汇总查询页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author smzhu@yj-technology.com
 * @version $
 */
public class HibernateMaintenanceDetailView extends BaseHibernateDao implements MaintenanceDetailViewDao{
	public List<MaintenanceDetailView> loadAllMaintenanceDetailView(String[] array) throws HibernateException{
		List<MaintenanceDetailView> result=null;
		Transaction tx = null; 
		Session session = getSession();
        try{
        	String hql="from MaintenanceDetailView as detialView where 1=1";
        	if (array[0] != "" && !array[0].equals("")) {
        		 hql = hql + " AND detialView.month =:month";
        	}
        	if (array[1] != "" && !array[1].equals("")) {
        		 hql = hql + " AND detialView.deptName =:department.name" ;
        	}
        	tx = session.beginTransaction();
            Query query = session.createQuery(hql);
            if (array[0] != "" && !array[0].equals("")) {
			query.setParameter("month", array[0].trim());
			}
			if(array[1] != "" && !array[1].equals("")){
				query.setParameter("department.name",array[1].trim());
			}
			//System.out.println(hql);
	      result=query.list(); 
	      tx.commit();
	      return result;
        } catch(HibernateException e){
        	e.printStackTrace();
        	return null;
        } finally {
        	releaseSession(session);
        }
		

	}
}

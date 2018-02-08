package com.yongjun.tdms.dao.runmaintenance.repair.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.runmaintenance.repair.PreRepairPlanViewDao;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlanView;
/**
 * <p>Title: HibernateMaintenanceDetailView
 * <p>Description: 预防性维修计划汇总查询页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author smzhu@yj-technology.com
 * @version $
 */
public class HibernateRepairPlanView extends BaseHibernateDao implements PreRepairPlanViewDao{

	/* (non-Javadoc)
	 * @see com.yongjun.tdms.dao.runmaintenance.repair.PreRepairPlanViewDao#loadAllPreRepairPlanView(java.lang.String[])
	 */
	public List<PreRepairPlanView> loadAllPreRepairPlanView(String[] array) throws HibernateException{
		List<PreRepairPlanView> result=null;
		try{
        	String hql="from PreRepairPlanView as detialView where 1=1";
        	if (array[0] != "" && !array[0].equals("")) {
        		 hql = hql + " AND detialView.beginDate >=:beginDateTime_start";
        	}
        	if (array[1] != "" && !array[1].equals("")) {
       		 hql = hql + " AND detialView.beginDate <=:beginDateTime_end";
       	}
        	if (array[2] != "" && !array[2].equals("")) {
        		 hql = hql + " AND detialView.deptName =:department.name" ;
        	}
        	if (array[3] != "" && !array[3].equals("")){
        		hql = hql + " AND detialView.tooling_dev_flag = :toolingDevFlag";
        	}
            Query query = getSession().createQuery(hql);
            if (array[0] != "" && !array[0].equals("")) {
            	query.setParameter("beginDateTime_start", array[0].trim());
			}
            if (array[1] != "" && !array[1].equals("")) {
    			query.setParameter("beginDateTime_end", array[1].trim());
    			}
			if(array[2] != "" && !array[2].equals("")){
				query.setParameter("department.name",array[2].trim());
			}
			if(array[3] != "" && !array[3].equals("")){
				query.setParameter("toolingDevFlag",array[3].trim());
			}
	      result=query.list();
        } catch(HibernateException e){
        	e.printStackTrace();
        }
		return result;
	}

	
}

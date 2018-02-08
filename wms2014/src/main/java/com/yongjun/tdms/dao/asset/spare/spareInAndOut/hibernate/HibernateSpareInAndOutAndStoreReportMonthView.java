package com.yongjun.tdms.dao.asset.spare.spareInAndOut.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.asset.spare.spareInAndOut.SpareInAndOutAndStoreReportMonthViewDao;
import com.yongjun.tdms.model.asset.spare.spareInAndOut.SpareInAndOutStoreMonthReportView;
/**
 * <p>Title: SpareInAndOutDao
 * <p>Description:</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author yli@yj-technology.com
 * @version $Id: SpareInAndOutDao.java 2009-3-1 下午03:09:01Z yli$
 * @see com.yongjun.tdms.dao.asset.spare.spareInAndOut
 */
public class HibernateSpareInAndOutAndStoreReportMonthView extends BaseHibernateDao implements SpareInAndOutAndStoreReportMonthViewDao{
	/**
	 * 根据页面传来的参数,获得备件收发存报表的月报表的集合
	 * @param array
	 * @return
	 * @throws HibernateException
	 */
	@SuppressWarnings("unchecked")
	public List<SpareInAndOutStoreMonthReportView> getSpareInAndOutAndStoreMonthViewCollention(String[] array) throws HibernateException {
		List<SpareInAndOutStoreMonthReportView> result = null;
		Transaction tx = null; 
		Session session = getSession();
		try {
			String hql = "from SpareInAndOutStoreMonthReportView as monthReport where 1=1";
			if(array[0] != "" && !array[0].equals("")){
				hql = hql + " AND monthReport.spareNo like :spareNo";
			}
			if(array[1] != "" && !array[1].equals("")){
				hql = hql + " AND monthReport.spareName like :spareName";
			}
			if(array[2] != "" && !array[2].equals("")){
				hql = hql + " AND monthReport.modelSpecs like :modelSpecs";
			}
			if(array[3] != "" && !array[3].equals("")){
				hql = hql + " AND monthReport.warehouse = :warehouse";
			}
			if(array[4] != "" && !array[4].equals("")){
				hql = hql + " AND monthReport.regional = :regional";
			}
			if(array[5] != "" && !array[5].equals("")){
				hql = hql + " AND monthReport.locationCode like :locationCode";
			}
			if(array[6] != "" && !array[6].equals("")){
				hql = hql + " AND monthReport.month = :month";
			}
			if(array[7] != "" && !array[7].equals("")){
				hql = hql + " AND monthReport.toolingDevFlag = :toolingDevFlag";
			}
			if(array[8] != "" && !array[8].equals("")&&array[8].equals("true")){
				hql = hql + " AND monthReport.inStocks>0 or monthReport.outStocks>0";
			}
			hql = hql + " order by monthReport.toolingDevFlag desc";
			tx = session.beginTransaction();
			Query query = session.createQuery(hql);
			if(array[0] != "" && !array[0].equals("")){
				query.setParameter("spareNo", "%"+array[0].trim()+"%");
			}
			if(array[1] != "" && !array[1].equals("")){
				query.setParameter("spareName",  "%"+array[1].trim()+"%");
			}
			if(array[2] != "" && !array[2].equals("")){
				query.setParameter("modelSpecs",  "%"+array[2].trim()+"%");
			}
			if(array[3] != "" && !array[3].equals("")){
				query.setParameter("warehouse",  Long.valueOf(array[3]));
			}
			if(array[4] != "" && !array[4].equals("")){
				query.setParameter("regional",  Long.valueOf(array[4]));
			}
			if(array[5] != "" && !array[5].equals("")){
				query.setParameter("locationCode",  "%"+array[5].trim()+"%");
			}
			if(array[6] != "" && !array[6].equals("")){
				query.setParameter("month", array[6].trim());
			}
			if(array[7] != "" && !array[7].equals("")){
				query.setParameter("toolingDevFlag", array[7].trim());
			}
			result=query.list(); 
			tx.commit();
			return result;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}finally{
			releaseSession(session);
		}
	}

}

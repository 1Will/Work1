package com.yongjun.tdms.dao.asset.spare.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.asset.spare.SpareDetailViewDao;
import com.yongjun.tdms.model.asset.spare.SpareDetailView;

public class HibernateSpareDetailView extends BaseHibernateDao implements
		SpareDetailViewDao {
	/**
	 * 根据页面传入的参数,从备件明细视图中获得备件台帐明细的集合 
	 * @param array
	 * @return
	 * @throws HibernateException
	 */
	@SuppressWarnings("unchecked")
	public List<SpareDetailView> loadSpareDetailAccount(String[] array)
			throws HibernateException {
		Transaction tx = null;
		List<SpareDetailView> result = null;
		Session session = getSession();
		try {
			String hql = "from SpareDetailView as reportView  where 1=1";

			if (array[0] != "" && !array[0].equals("")) {
				hql = hql + " AND reportView.spareNo like :spareNo";
			}
			if (array[1] != "" && !array[1].equals("")) {
				hql = hql + " AND reportView.spareName like :spareName";

			}
			if (array[2] != "" && !array[2].equals("")&&array[2] != null) {
				hql = hql + " AND reportView.spareEnName like :spareEnName";
			}
			if (array[3] != "" && !array[3].equals("")) {
				hql = hql + " AND reportView.modelSpecs like :modelSpecs";

			}

			if (array[4] != "" && !array[4].equals("")) {
				hql = hql + " AND reportView.locationCode like :locationCode";

			}

			if (array[5] != "" && !array[5].equals("")) {
				hql = hql + " AND reportView.dept =:depart_Name";
			}

			if (array[6] != "" && !array[6].equals("")) {
				hql = hql + " AND reportView.category =:category";
			}

			if (array[7] != "" && !array[7].equals("")) {
				hql = hql + " AND reportView.detailType =:detailType";
			}


			if (array[8] != "" && !array[8].equals("")&&array[8].equals("true")) {
				hql = hql + " AND reportView.stocks >0";
			}
            hql=hql+" order by reportView.toolingDevFlag";
			tx = session.beginTransaction();
			Query query = session.createQuery(hql);

			if (array[0] != "") {
				query.setParameter("spareNo", '%'+array[0].trim()+'%');
			}
			if (array[1] != "") {
				query.setParameter("spareName", '%'+array[1].trim()+'%');
			}
			if (array[2] != "") {
				query.setParameter("spareEnName", '%'+array[2].trim()+'%');
			}
			if (array[3] != "") {
				query.setParameter("modelSpecs", '%'+array[3].trim()+'%');
			}
			if (array[4] != "") {
				query.setParameter("locationCode", '%'+array[4].trim()+'%');
			}
			if (array[5] != "") {
				query.setParameter("depart_Name", array[5].trim());
			}
			if (array[6] != "") {
				query.setParameter("category", array[6].trim());
			}
			if (array[7] != "") {
				query.setParameter("detailType", array[7].trim());
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
	
	/**
	 * 根据页面传入的参数,从备件明细视图中获得备件台帐明细的集合 zzb
	 * @param array
	 * @return
	 * @throws HibernateException
	 */
	@SuppressWarnings("unchecked")
	public List<SpareDetailView> loadSpareLocation(String[] array) throws HibernateException {

		Transaction tx = null;
		List<SpareDetailView> result = null;
		Session session = getSession();
		try {
			String hql = "from SpareDetailView as reportView  where 1=1";

			if (array[0] != "" && !array[0].equals("")) {
				hql = hql + " AND reportView.spareNo like :spareNo";
			}
			if (array[1] != "" && !array[1].equals("")) {
				hql = hql + " AND reportView.spareName like :spareName";

			}
			if (array[2] != "" && !array[2].equals("")) {
				hql = hql + " AND reportView.modelSpecs like :modelSpecs";

			}

			if (array[3] != "" && !array[3].equals("")) {
				hql = hql + " AND reportView.locationCode like :locationCode";

			}

			if (array[4] != "" && !array[4].equals("")) {
				hql = hql + " AND reportView.dept =:depart_Name";
			}

			if (array[5] != "" && !array[5].equals("")) {
				hql = hql + " AND reportView.category =:category";
			}
			if (array[6] != "" && !array[6].equals("")) {
				hql = hql + " AND reportView.warehouse =:warehouseId";
			}
			if (array[7] != "" && !array[7].equals("")) {
				hql = hql + " AND reportView.regional =:regionalId";
			}
			if (array[8] != "" && !array[8].equals("")&&array[8].equals("true")) {
				hql = hql + " AND reportView.stocks >0";
			}
			
//			if (array[9] != "" && !array[9].equals("")) {
//				hql = hql + " AND reportView.warehouse in("+array[9]+")";
//			}
			
            hql=hql+" order by reportView.toolingDevFlag";
			tx = session.beginTransaction();
			Query query = session.createQuery(hql);

			if (array[0] != "") {
				query.setParameter("spareNo", '%'+array[0].trim()+'%');
			}
			if (array[1] != "") {
				query.setParameter("spareName", '%'+array[1].trim()+'%');
			}
			if (array[2] != "") {
				query.setParameter("modelSpecs", '%'+array[2].trim()+'%');
			}
			if (array[3] != "") {
				query.setParameter("locationCode", '%'+array[3].trim()+'%');
			}
			if (array[4] != "") {
				query.setParameter("depart_Name", array[4].trim());
			}
			if (array[5] != "") {
				query.setParameter("category", array[5].trim());
			}
			if (array[6] != "") {
				query.setParameter("warehouseId", array[6].trim());
			}			
			if (array[7] != "") {
				query.setParameter("regionalId", array[7].trim());
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

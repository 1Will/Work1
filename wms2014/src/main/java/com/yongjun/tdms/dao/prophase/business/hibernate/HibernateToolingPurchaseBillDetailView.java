package com.yongjun.tdms.dao.prophase.business.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.prophase.business.ToolingPurchaseBillDetailViewDao;
import com.yongjun.tdms.model.prophase.business.ToolingPurchaseBillDetailView;

public class HibernateToolingPurchaseBillDetailView extends BaseHibernateDao implements
		ToolingPurchaseBillDetailViewDao {
	@SuppressWarnings("unchecked")
	public List<ToolingPurchaseBillDetailView> loadToolingPurchaseBillContractDetail(final Long purchaseBillId) {
		return (List<ToolingPurchaseBillDetailView>) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetTOOLINGPURCHASECONTRACT")
								.setParameter("purchaseBillId", purchaseBillId).list();
					}
				});
	}

	public List<ToolingPurchaseBillDetailView> loadToolingPurchaseBillContractDetail(List ids) throws HibernateException {
		List<ToolingPurchaseBillDetailView> result=null;
		Transaction tx = null; 		
		Session session = getSession();
		String id=ids.toString().substring(1, ids.toString().length()-1);
        try{
        	String hql="from ToolingPurchaseBillDetailView as detialView where 1=1 ";
        	if (id != null && !id.equals("")) {
				hql = hql + " AND detialView.id in ("+id+")";
			}
        	hql=hql+"ORDER BY id DESC";
        	tx = session.beginTransaction();
            Query query = getSession().createQuery(hql);
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

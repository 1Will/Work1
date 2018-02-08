package com.yongjun.tdms.dao.asset.spare.outWareHouse.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.asset.spare.outWareHouse.SpareOutBillDetailViewDao;
import com.yongjun.tdms.model.asset.spare.outWareHouse.SpareOutBillDetailView;
public class HibernateSpareOutBillDetailViewDao extends BaseHibernateDao implements SpareOutBillDetailViewDao{
	@SuppressWarnings("unchecked")
	public List<SpareOutBillDetailView> loadSpareOutBillDetail(final Long outWareHouseBillId) {
		return (List<SpareOutBillDetailView>) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetOutBillDetailByOutWareHouseId")
								.setParameter("outWareHouseBillId", outWareHouseBillId).list();
					}
				});
	}
	public List<SpareOutBillDetailView> loadSpareOutBillDetail(String[] array) throws HibernateException {
		List<SpareOutBillDetailView> result = null;
		Transaction tx = null; 
		Session session = getSession();
		try {
			String hql = "from SpareOutBillDetailView as detailView where 1=1";
			if(array[0] != "" && !array[0].equals("")){
				hql = hql + " AND detailView.outBillCode like :spareOutBillNo";
			}
			if(array[1] != "" && !array[1].equals("")){
				hql = hql + " AND detailView.outBillName like :spareOutBillName";
			}
			if(array[2] != "" && !array[2].equals("")){
				hql = hql + " AND detailView.borrower like :borrower";
			}
			if(array[3] != "" && !array[3].equals("")){
				hql = hql + " AND detailView.outBillPerson like :outPeople";
			}
			if(array[4] != "" && !array[4].equals("")){
				hql = hql + " AND detailView.deptName = :dept_name";
			}
			if(array[5] != "" && !array[5].equals("")){
				hql = hql + " AND detailView.outBillDate >= :outDate_start";
			}
			if(array[6] != "" && !array[6].equals("")){
				hql = hql + " AND detailView.outBillDate <= :outDate_end";
			}
			if(array[7] != "" && !array[7].equals("")){
				hql = hql + " AND detailView.warehouse = :outWareHouseId";
			}
			if(array[9] != "" && !array[9].equals("")){
				hql = hql + " AND detailView.oldSpare = :oldSpare";
			}
			if(array[10] != "" && !array[10].equals("")){
				hql = hql + " AND detailView.outType = :outType";
			}
			System.out.println(hql);
			tx = session.beginTransaction();
			Query query = session.createQuery(hql);
			if(array[0] != "" && !array[0].equals("")){
				query.setParameter("spareOutBillNo", "%"+array[0].trim()+"%");
			}
			if(array[1] != "" && !array[1].equals("")){
				query.setParameter("spareOutBillName", "%"+array[1].trim()+"%");
			}
			if(array[2] != "" && !array[2].equals("")){
				query.setParameter("borrower", "%"+array[2].trim()+"%");
			}
			if(array[3] != "" && !array[3].equals("")){
				query.setParameter("outPeople", "%"+array[3].trim()+"%");
			}
			if(array[4] != "" && !array[4].equals("")){
				query.setParameter("dept_name", array[4].trim());
			}
			if(array[5] != "" && !array[5].equals("")){
				query.setParameter("outDate_start", array[5].trim());
			}
			if(array[6] != "" && !array[6].equals("")){
				query.setParameter("outDate_end", array[6].trim());
			}
			if(array[7] != "" && !array[7].equals("")){
				query.setParameter("outWareHouseId", array[7]);
			}
			if(array[9] != "" && !array[9].equals("")){
				query.setParameter("oldSpare", array[9]);
			}
			if(array[10] != "" && !array[10].equals("")){
				query.setParameter("outType", array[10]);
			}
			result=query.list(); 
			tx.commit();
			return result;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			releaseSession(session);
		}
	}
}

package com.yongjun.tdms.dao.prophase.business.hibernate;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.prophase.business.PurchaseBillDetailDao;
import com.yongjun.tdms.model.prophase.business.PurchaseBillDetail;
import com.yongjun.tdms.model.prophase.business.SubscribeDetail;

public class HibernatePurchaseBillDetail extends BaseHibernateDao implements PurchaseBillDetailDao{

	public PurchaseBillDetail loadPurchaseBillDetail(Long purchaseBillDetailId) {
		
		return this.load(PurchaseBillDetail.class,purchaseBillDetailId);
	}

	public List<PurchaseBillDetail> loadPurchaseBillDetails(Long[] PurchaseBillDetailIds) {
		
		return this.loadAll(PurchaseBillDetail.class,PurchaseBillDetailIds);
	}

	public void storePurchaseBillDetail(PurchaseBillDetail purchaseBillDetail) {
		this.store(purchaseBillDetail);
		
	}

	public void deletePurchaseBillDetail(PurchaseBillDetail purchaseBillDetail) {
		this.delete(purchaseBillDetail);
		
	}

	public void deleteAllPurchaseBillDetails(Collection<PurchaseBillDetail> PurchaseBillDetails) {
		this.deleteAll(PurchaseBillDetails);
		
	}
	@SuppressWarnings("unchecked")
	public List loadAllPurchaseBillDtlBySubDtlId(final Long id) {
		return (List<PurchaseBillDetail>) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("LoadPurchaseBillDetalBySubscribeDtlId")
								.setParameter("id", id).list();
					}
				});
	}

	public Double getPurchaseDetailTotalByPurchaseId(final Long id) {
		return (Double) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("getAllPurchaseDetailTotalPriceByPurchaseId")
								.setParameter("purchaseId", id)
								.uniqueResult();
					}
				});
	}

	public List<PurchaseBillDetail> loadByKey(String key, Object value) throws DaoException {		
		return super.loadByKey(PurchaseBillDetail.class, key, value);
	}

	
	public List<PurchaseBillDetail> loadByKeyArry(String[] keys, Object[] values) throws DaoException{
		return super.loadByKeyArray(PurchaseBillDetail.class, keys, values);
	}

	/**
	 * 根据采购明细ID集合获取对应的申购明细ID集合
	 * @param purchaseBill
	 */
	public String updSubDetailIds(String PurchaseBillIds){
		StringBuffer result= new StringBuffer();
		String sql = "select Subscribe_Detail_id as detailId from t_purchasebill_detail where ID in("+PurchaseBillIds+")";
		Session session = this.getSession();
		Connection conn = session.connection();
		Transaction ts = session.beginTransaction();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs =stmt.executeQuery(sql);
			while(rs.next()){
				result.append(rs.getLong("detailId")+",");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ts.commit();
		return result.toString();
	}
	
	/**
	 * 根据传入的采购单明细ID集合，更新申购单、汇总单的采购项
	 * 
	 * @param PurchaseBills 采购单明细ID集合
	 * @return
	 */
	public void updStatus(final String PurchaseBillIds) {
		this.getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session){
				try{
					Connection conn = session.connection();
					String sql = "{call updPurch(?)}";
					CallableStatement stmt = conn.prepareCall(sql);
                    stmt.setString(1, PurchaseBillIds);
                    stmt.execute(); 
				}catch(Exception e){
				}
				return null;
			}
		});
		
	}

}

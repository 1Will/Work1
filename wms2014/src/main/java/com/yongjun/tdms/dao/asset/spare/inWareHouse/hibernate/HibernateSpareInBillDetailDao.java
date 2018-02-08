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
package com.yongjun.tdms.dao.asset.spare.inWareHouse.hibernate;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.asset.spare.inWareHouse.SpareInBillDetailDao;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBillDetail;

/**
 * <p>Title: HibernateSpareInBillDetailDao
 * <p>Description: 备件入库明细实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $
 */
public class HibernateSpareInBillDetailDao extends BaseHibernateDao implements
		SpareInBillDetailDao {

	public void storeSpareInBillDtl(SpareInBillDetail spareInBillDtl) {
		this.store(spareInBillDtl);
	}

	public SpareInBillDetail loadSpareInBillDtl(Long spareInBillDtlId) {
		return this.load(SpareInBillDetail.class,spareInBillDtlId);
	}

	public List<SpareInBillDetail> loadAllSpareInBillDtl(Long[] spareInBillDtlIds) {
		return this.loadAll(SpareInBillDetail.class,spareInBillDtlIds);
	}

	public List<SpareInBillDetail> loadAllSpareInBillDtl() {
		return this.loadAll(SpareInBillDetail.class);
	}
	
	public void deleteSpareInBillDtl(SpareInBillDetail spareInBillDtl) {
		this.delete(spareInBillDtl);
	}

	public void deleteAllSpateInBillDtl(List<SpareInBillDetail> list) {
		this.deleteAll(list);
	}
	/*
	 * 根据部门的id,库位的id,备件的id从备件入库明细中获取符合条件的最顶层记录
	 */
	public SpareInBillDetail getTop1SpareInDetailByDeptAndLocationAndSpare(Long deptId, Long locationId, Long spareId){
		SpareInBillDetail spareInBillDetail = null;
		try {
			String hql = "FROM 	SpareInBillDetail spareInBillDetail WHERE spareInBillDetail.department.id = :deptId " +
            "AND spareInBillDetail.location.id = :locationId AND spareInBillDetail.spare.id = :spareId "
            ;
			Query query = getSession().createQuery(hql);
			query.setFirstResult(1);
			query.setMaxResults(2);
			query.setParameter("deptId",deptId)
				.setParameter("locationId",locationId)
				.setParameter("spareId",spareId);
			spareInBillDetail = (SpareInBillDetail)query.uniqueResult();
			
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		return spareInBillDetail;
	}
	
	/**
	 * 根据入库明细ID集合获取对应的采购明细ID集合
	 * @param SpareInBillDtlIds
	 */
	public String updPurchaseBillIds(String SpareInBillDtlIds){
		StringBuffer result= new StringBuffer();
		String sql = "select PO_DETAIL_ID as detailId from t_spareIn_bill_detail where ID in("+SpareInBillDtlIds+")";
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
	 * 根据传入的入库单明细ID集合，更新申购单、汇总单、采购单的入库项
	 * 
	 * @param SpareInBillDtlIds 入库单明细ID集合
	 * @return
	 */
	public void updStatus(final String SpareInBillDtlIds){
		this.getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session){
				try{
					Connection conn = session.connection();
					String sql = "{call updInbill(?)}";
					CallableStatement stmt = conn.prepareCall(sql);
                    stmt.setString(1, SpareInBillDtlIds);
                    stmt.execute(); 
				}catch(Exception e){
				}
				return null;
			}
		});
	}
}

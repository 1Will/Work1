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
package com.yongjun.tdms.dao.prophase.business.hibernate;


import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.prophase.business.PurchaseBillDao;
import com.yongjun.tdms.model.prophase.business.PurchaseBill;


/**
 * @author qs
 * @version $Id: HibernatePurchaseBill.java 11279 2008-03-12 01:12:13Z mwei $
 */
public class HibernatePurchaseBill extends BaseHibernateDao implements
		PurchaseBillDao {

	public PurchaseBill loadPurchaseBill(Long purchasId) {
	
		return this.load(PurchaseBill.class,purchasId);
	}

	public List<PurchaseBill> loadPurchaseBills(Long[] PurchaseBillIds) {
		
		return this.loadAll(PurchaseBill.class,PurchaseBillIds);
	}

	public void storePurchaseBill(PurchaseBill purchaseBill) {
	    
         this.store(purchaseBill);	
	}

	public void deletePurchaseBill(PurchaseBill purchaseBill) {
		this.delete(purchaseBill);
		
	}

	public void deleteAllPurchaseBills(Collection<PurchaseBill> PurchaseBills) {
		  this.deleteAll(PurchaseBills);
		
	}

	public void storeTotalPrice(PurchaseBill purchaseBill) {
		this.store(purchaseBill);
		
	}

	public String GetMaxBillNoByBillCode(final String billCode) {
		final String sql = "SELECT MAX(STUFF(purchaseBill.BILL_NO,9,3,'')) as billNo FROM t_purchase_bill as purchaseBill WHERE purchaseBill.BILL_NO LIKE ?";
		return (String) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.createSQLQuery(sql).addScalar("billNo",Hibernate.STRING).setString(0, billCode).uniqueResult();
//						return session.getNamedQuery("GetMaxBillNoByBillCode")
//								.setParameter("billCode", billCode)
//								.uniqueResult();
					}
				});
	}


//
//	public PurchaseBill loadPurchaseBillByBillNo(final String billNo) {
//		return (PurchaseBill) this.getHibernateTemplate().execute(
//				new HibernateCallback() {
//					public Object doInHibernate(Session session)
//							throws HibernateException, SQLException {
//						return session.getNamedQuery("LoadPurchaseBillByBillNo")
//								.setParameter("billNo", billNo).uniqueResult();
//					}
//				});}
	

}

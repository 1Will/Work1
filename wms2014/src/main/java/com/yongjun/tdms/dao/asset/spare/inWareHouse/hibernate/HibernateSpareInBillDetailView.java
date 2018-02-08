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

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.asset.spare.inWareHouse.SpareInBillDetailViewDao;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBillDetailView;
/**
 * 
 * <p>Title:HibernateSpareInBillDetailView
 * <p>Description:</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author yli@yj-technology.com
 * @version $Id: HibernateSpareInBillDetailView.java 2008-11-25 17:14:22 yli$
 */
public class HibernateSpareInBillDetailView extends BaseHibernateDao implements
	SpareInBillDetailViewDao {
	/**
	 * 根据入库单ID在视图类中查询出属于该入库单的所有入库明细记录，并打印报表
	 */
	@SuppressWarnings("unchecked")
	public List<SpareInBillDetailView> loadSpareInBillDetail(final Long spareInBillId) {
		return (List<SpareInBillDetailView>) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetSpareInBillDetailBySpareInBillId")
								.setParameter("spareInBillId", spareInBillId).list();
					}
	    });
		
	}
	/**
	 * 报表汇总打印
	 */
	public List<SpareInBillDetailView> loadSpareInBillDetailView(String[] array) throws HibernateException {
		List<SpareInBillDetailView> result = null;
		Transaction tx = null; 
		Session session = getSession();
		try {
			String hql = "from SpareInBillDetailView as detailView where 1=1";
			if(array[0] != "" && !array[0].equals("")){
				hql = hql + " AND detailView.spareinbill_code like :spareInBillNo";
			}
			if(array[1] != "" && !array[1].equals("")){
				hql = hql + " AND detailView.spareinbill_name like :spareInBillName";
			}
			if(array[2] != "" && !array[2].equals("")){
				hql = hql + " AND detailView.inpeople like :inPeopleName";
			}
			if(array[3] != "" && !array[3].equals("")){
				hql = hql + " AND detailView.checkpeople like :checkPeopleName";
			}
			if(array[4] != "" && !array[4].equals("")){
				hql = hql + " AND detailView.supplier_name like :supplierName";
			}
			if(array[5] != "" && !array[5].equals("")){
				hql = hql + " AND detailView.spareinbill_indate >= :inDate_start";
			}
			if(array[6] != "" && !array[6].equals("")){
				hql = hql + " AND detailView.spareinbill_indate <= :inDate_end";
			}
			tx = session.beginTransaction();
			Query query = session.createQuery(hql);
			if(array[0] != "" && !array[0].equals("")){
				query.setParameter("spareInBillNo", "%"+array[0].trim()+"%");
			}
			if(array[1] != "" && !array[1].equals("")){
				query.setParameter("spareInBillName",  "%"+array[1].trim()+"%");
			}
			if(array[2] != "" && !array[2].equals("")){
				query.setParameter("inPeopleName", "%"+array[2].trim()+"%");
			}
			if(array[3] != "" && !array[3].equals("")){
				query.setParameter("checkPeopleName", "%"+array[3].trim()+"%");
			}
			if(array[4] != "" && !array[4].equals("")){
				query.setParameter("supplierName","%"+array[4].trim()+"%");
			}
			if(array[5] != "" && !array[5].equals("")){
				query.setParameter("inDate_start", array[5].trim());
			}
			if(array[6] != "" && !array[6].equals("")){
				query.setParameter("inDate_end", array[6].trim());
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

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

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.prophase.business.SubscribeDao;
import com.yongjun.tdms.dao.prophase.business.SubscribeDetailViewDao;
import com.yongjun.tdms.model.prophase.business.Subscribe;
import com.yongjun.tdms.model.prophase.business.SubscribeDetail;
import com.yongjun.tdms.model.prophase.business.SubscribeDetailView;
import com.yongjun.tdms.service.prophase.business.SubscribeCollectBillManager;

/**
 * <p>Title:HibernateSubscribeDetailView
 * <p>Description:</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author yli@yj-technology.com
 * @version $Id: HibernateSubscribeDetailView.java 2008-12-15 13:39:41 yli$
 */
public class HibernateSubscribeDetailView extends BaseHibernateDao implements SubscribeDetailViewDao {

	/**
	 * 根据采购单ID在视图类中查询出属于该采购单的所有采购明细记录，并打印报表
	 */
	@SuppressWarnings("unchecked")
	public List<SubscribeDetailView> loadSubscribeDetailView(List id,String flag) {
		List<SubscribeDetailView> result = null;
		Transaction tx = null;
		Session session = getSession();	
		String ids=id.toString().substring(1, id.toString().length()-1);
		try {
			String hql="";
			if(flag.equals("sd")){
				hql = "from SubscribeDetailView as sd where 1=1";					
			}
			if(flag.equals("scbd")){
				hql = "from SubscribeDetailView as sd where 1=1 AND PUR_STOCK=0";	
			}
			if (ids != null && !ids.equals("")) {
				hql = hql + " AND sd.id in ("+ids+")";
			}else{
				hql = hql + " AND sd.id=0";
			}
			hql = hql + " order by sd.departmentId,sd.id desc";
			tx = session.beginTransaction();
			Query query = getSession().createQuery(hql);		
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

	public List<SubscribeDetailView> loadSubscribeDetailViewByBillIds(String billIds) {
		List<SubscribeDetailView> result = null;
		Transaction tx = null;
		Session session = getSession();	
		String ids=billIds.substring(0, billIds.length()-1);
		//System.out.println("ids: " + ids);
		try {
			String hql="";
			hql = "from SubscribeDetailView as sd where 1=1";	
			if (ids != null && !ids.equals("")) {
				hql = hql + " AND sd.purchaseBillId in ("+ids+")";
			}else{
				hql = hql+"  ";
			}
			hql = hql + " order by sd.departmentId,sd.id desc";
			tx = session.beginTransaction();
			Query query = getSession().createQuery(hql);		
			result = query.list();
			tx.commit();
			return result;
		}catch (HibernateException e) {
			e.printStackTrace();
			return null;
		} finally {
			releaseSession(session);
		}
	}

}

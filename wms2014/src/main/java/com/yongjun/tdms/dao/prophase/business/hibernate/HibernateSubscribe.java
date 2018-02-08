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
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.prophase.business.SubscribeDao;
import com.yongjun.tdms.model.asset.spare.warehouseInfo.regional.Regional;
import com.yongjun.tdms.model.prophase.business.Subscribe;
import com.yongjun.tdms.model.prophase.business.SubscribeCollectBill;
import com.yongjun.tdms.model.prophase.business.SubscribeDetail;

/**
 * @author qs
 * @version $Id: HibernateSubscribe.java 11237 2008-03-09 10:36:42Z mwei $
 */
public class HibernateSubscribe extends BaseHibernateDao implements
		SubscribeDao {

	public Subscribe loadSubscribe(Long id) {
		return load(Subscribe.class, id);
	}

	public void storeSubscribe(Subscribe subscribe) {
		store(subscribe);
	}

	public List<Subscribe> loadAllSubscribes(Long[] ids) {
		return loadAll(Subscribe.class, ids);
	}
	
	public void deleteAllSubscribes(List<Subscribe> subscribes) {
		deleteAll(subscribes);
	}

	public List<SubscribeDetail> loadAllSubscribeDetails(Long[] ids) {
		return loadAll(SubscribeDetail.class, ids);
	}

	public void deleteAllSubscribeDetails(List<SubscribeDetail> subscribeDetails) {
		deleteAll(subscribeDetails);
	}

	public SubscribeDetail loadSubscribeDetail(Long id) {
		return load(SubscribeDetail.class, id);
	}

	public void storeSubscribeDetail(SubscribeDetail subscribeDtl) {
		store(subscribeDtl);
	}

	public List Query(String[] queryInfo) throws HibernateException {
		List result=null;
//		try {
//			String hql = " from Subscribe as subscribe where 1=1 AND subscribe.toolingDevFlag = '"+queryInfo[7]+"'" ;
//			 hql = " from Subscribe as subscribe where 1=1 AND subscribe.disabled='"+queryInfo[6]+"'";
//			if (queryInfo[0] != "") {
//				hql += " and subscribe.billNo like :billNo ";
//			}
//			if (queryInfo[1]!= ""){
//				hql += " and subscribe.name like :name ";
//			}
//			if (queryInfo[2]!= ""){
//				hql += " and subscribe.subscriber like :subscriber.name ";
//			}
//			if (queryInfo[3]!= ""){
//				hql += " and subscribe.department.id like :department.id ";
//			}
//			if (queryInfo[4]!= ""){
//				hql += " and subscribe.subscribeDate =:subscribeDate_start";
//			}
//			if (queryInfo[5]!= ""){
//				hql += " and subscribe.subscribeDate =:subscribeDate_end";
//			}
//			Query query = getSession().createQuery(hql);
//			
//			if (queryInfo[0] != "") {
//				query.setParameter("billNo", '%'+queryInfo[0]+'%');
//			}
//			if(queryInfo[1] != ""){
//				query.setParameter("name",'%'+queryInfo[1]+'%');
//			}
//			if(queryInfo[2] != ""){
//				query.setParameter("buyingPerson.name",'%'+queryInfo[2]+'%');
//			}
//			if(queryInfo[3] != ""){
//				query.setParameter("department.id",'%'+queryInfo[3]+'%');
//			}
//			if(queryInfo[4] != ""){
//				query.setParameter("subscribeDate",queryInfo[4]);
//			}
//			if(queryInfo[5] != ""){
//				query.setParameter("totalPrice",queryInfo[5]);
//			}
//			if(queryInfo[6] != ""){
//				query.setParameter("reason",queryInfo[6]);
//			}
//			result = query.list();
//		} catch (HibernateException e) {
//			e.printStackTrace();
//		}
		return result;
	}
	
	/**
	 * 该方法暂时没有用到(主要作用是：更具传入的采购单id和采购单明细类型来查找明细记录)
	 */
	
	@SuppressWarnings("unchecked")
	public List<SubscribeDetail> loadAllSubscribeDetailByDetailType(final Long id,final String detailType) {
		return (List<SubscribeDetail>) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetAllValuesByDetailType")
								.setParameter("id", id)
								.setParameter("detailType",detailType).list();
					}
				});
	}

	public void storeToolingAndDevice(SubscribeDetail dtl) {
		this.store(dtl);
		
	}

	public Double getAllSubscribeDetailTotalPriceBySubscribeIdAndDetailType(final Long subscribeId) {
		return (Double) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("getAllSubscribeDetailTotalPriceBySubscribeIdAndDetailType")
								.setParameter("subscribeId", subscribeId)
								.uniqueResult();
					}
				});
	}

	public void storeToolingPurchase(Subscribe subscribe) {
		this.store(subscribe);
		
	}

	public List<SubscribeDetail> loadByKey(String key, Object value) throws DaoException {		
		return super.loadByKey(SubscribeDetail.class, key, value);
	}
	
	
	
	public List<Regional> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException{		
		return super.loadByKeyArray(Regional.class, keyNames, keyValues);
	}
	
	/**
	 * 根据非主键集合找明细
	 * @param arrys
	 * @param values
	 * @return
	 * @throws DaoException 
	 */
	public List<SubscribeDetail>loadDetailBykeyArry(String[] arrys,Object[] values) throws DaoException{
		return this.loadByKeyArray(SubscribeDetail.class, arrys, values);
	}
	
	/**
	 * 取得总金额
	 * @return
	 */
	public Double getSumPrice(SubscribeCollectBill bill){
		String hql="select sum(detail.totalPrice) from SubscribeDetail detail where detail.subscribeCollectBill="+bill.getId();
		hql = hql+"and detail.status<>'NOTPURCHASEED'";
		Session session= this.getSession();
		Query query = session.createQuery(hql);
		List list = query.list();
		if(null !=list && list.size()>0){
			return (Double) list.get(0);
		}
		return  0.0;
		
	}
	
	/**
	 * 取得采购数量 包括入库
	 * @param bill
	 * @return
	 */
	public Integer getPurNum(SubscribeCollectBill bill){
		String hql="select count(*) from SubscribeDetail detail where detail.subscribeCollectBill="+bill.getId();
		hql = hql+"and detail.status<>'NOTPURCHASEED' and detail.status<>'NEW' and detail.status<>'COLLECTED' ";
		Session session= this.getSession();
		Query query = session.createQuery(hql);
		List list = query.list();
		if(null !=list && list.size()>0){
			return (Integer) list.get(0);
		}
		return 0;
	}
}

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
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.asset.spare.inWareHouse.SpareInBillDao;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBill;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBillDetail;
import com.yongjun.tdms.model.base.codevalue.CodeValue;

/**
 * <p>Title: HibernateSpareInBillDao
 * <p>Description: 备件入库数据库访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @see com.yongjun.tdms.dao.asset.spare.inWareHouse.SpareInBillDao
 * @version $Id:$
 */
public class HibernateSpareInBillDao extends BaseHibernateDao implements
		SpareInBillDao {

	public void storeSpareInBill(SpareInBill spareInBill) {
		this.store(spareInBill);
	}

	public SpareInBill loadSpareInBill(Long spareInBillId) {
		return this.load(SpareInBill.class,spareInBillId);
	}

	public List<SpareInBill> loadAllSpareInBill(Long[] spareInBillIds) {
		return this.loadAll(SpareInBill.class,spareInBillIds);
	}

	public List<SpareInBill> loadAllSpareInBill() {
		return this.loadAll(SpareInBill.class);
	}
	
	public void deleteSpareInBill(SpareInBill spareInBill) {
		this.delete(spareInBill);
	}

	public void deleteAllSpareInBill(List<SpareInBill> list) {
		this.deleteAll(list);
	}

	/**
	 * 在获得总计时，将仓库加入到查询条件中
	 * hjia 2010-5-21
	 */
	public Double showTotalPrice(String[] array) {
		Session session = getSession();
		Double result = 0.0;
		try {		
			String hql = "SELECT sum(spareInBill.totalPrice) FROM SpareInBill as spareInBill where 1=1 "
					+ " AND spareInBill.oldSpare is null ";
			if(array[0]!=""&&!array[0].equals("")){
				hql = hql + " AND spareInBill.code LIKE '%"+array[0].trim()+"%'";
			}
			if(array[1]!=""&&!array[1].equals("")){
				hql = hql + " AND spareInBill.name LIKE '%"+array[1].trim()+"%'";
			}
			if(array[2]!=""&&!array[2].equals("")){
				hql = hql + " AND spareInBill.inPeople.name LIKE '%"+array[2].trim()+"%'";
			}
			if(array[3]!=""&&!array[3].equals("")){
				hql = hql + " AND spareInBill.checkPeople.name LIKE '%"+array[3].trim()+"%'";
			}
			if(array[4]!=""&&!array[4].equals("")){
				hql = hql + " AND spareInBill.supplier.name LIKE  '%"+array[4].trim()+"%'";
			}
			if(array[5]!=""&&!array[5].equals("")){
				hql = hql + " AND spareInBill.inDate >= '"+array[5].trim()+"'";
			}
			if(array[6]!=""&&!array[6].equals("")){
				hql = hql + " AND spareInBill.inDate <= '"+array[6].trim()+"'";
			}
			if(array[7]!=""&&!array[7].equals("")&&!array[7].startsWith("(")){
				hql = hql + " AND spareInBill.warehouse.id = "+array[7].trim();
			}
			if(array[7]!=""&&!array[7].equals("")&&array[7].startsWith("(")){
				hql = hql + " AND spareInBill.warehouse.id in "+array[7].trim();
			}
			if(array[8]!=""&&!array[8].equals("")){
				hql = hql + " AND spareInBill.disabled=true";
			}
			if(array[9]!=""&&!array[9].equals("")){
				hql = hql + " AND spareInBill.disabled=false";
			}
			if(array[10]!=""&&!array[10].equals("")){
				hql = hql + " AND spareInBill.status = '"+array[10].trim()+"'";
			}
			if(array[11]!=""&&!array[11].equals("")){
				hql = hql + " AND spareInBill.storageGrade.id ='"+array[11].trim()+"'";
			}
			//System.out.println("hql=="+hql);
			Query query = session.createQuery(hql);
			result = (Double)query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}finally{
			releaseSession(session);
		}
		return result;
	}
	
	/**
	 * 在获得总计时，将仓库加入到查询条件中
	 * hjia 2010-5-21
	 */
	public Double showOldTotalPrice(String[] array) {
		Session session = getSession();
		Double result = 0.0;
		try {		
			String hql = "SELECT sum(spareInBill.totalPrice) FROM SpareInBill as spareInBill where 1=1 "
					+ " AND spareInBill.oldSpare = '0' ";
			if(array[0]!=""&&!array[0].equals("")){
				hql = hql + " AND spareInBill.code LIKE '%"+array[0].trim()+"%'";
			}
			if(array[1]!=""&&!array[1].equals("")){
				hql = hql + " AND spareInBill.name LIKE '%"+array[1].trim()+"%'";
			}
			if(array[2]!=""&&!array[2].equals("")){
				hql = hql + " AND spareInBill.inPeople.name LIKE '%"+array[2].trim()+"%'";
			}
			if(array[3]!=""&&!array[3].equals("")){
				hql = hql + " AND spareInBill.checkPeople.name LIKE '%"+array[3].trim()+"%'";
			}
			if(array[4]!=""&&!array[4].equals("")){
				hql = hql + " AND spareInBill.supplier.name LIKE  '%"+array[4].trim()+"%'";
			}
			if(array[5]!=""&&!array[5].equals("")){
				hql = hql + " AND spareInBill.inDate >= '"+array[5].trim()+"'";
			}
			if(array[6]!=""&&!array[6].equals("")){
				hql = hql + " AND spareInBill.inDate <= '"+array[6].trim()+"'";
			}
			if(array[7]!=""&&!array[7].equals("")&&!array[7].startsWith("(")){
				hql = hql + " AND spareInBill.warehouse.id = "+array[7].trim();
			}
			if(array[7]!=""&&!array[7].equals("")&&array[7].startsWith("(")){
				hql = hql + " AND spareInBill.warehouse.id in "+array[7].trim();
			}
			if(array[8]!=""&&!array[8].equals("")){
				hql = hql + " AND spareInBill.disabled=true";
			}
			if(array[9]!=""&&!array[9].equals("")){
				hql = hql + " AND spareInBill.disabled=false";
			}
			if(array[10]!=""&&!array[10].equals("")){
				hql = hql + " AND spareInBill.status = '"+array[10].trim()+"'";
			}
			if(array[11]!=""&&!array[11].equals("")){
				hql = hql + " AND spareInBill.storageGrade.id ='"+array[11].trim()+"'";
			}
			//System.out.println("hql=="+hql);
			Query query = session.createQuery(hql);
			result = (Double)query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}finally{
			releaseSession(session);
		}
		return result;
	}

	public List<SpareInBill> loadByKey(String key, Object value)throws DaoException {
		// TODO Auto-generated method stub
		return loadByKey(SpareInBill.class,key,value);
	}

	public SpareInBill loadSpareInBillBySpareOutBillId(final Long spareOutBillId) {
		return (SpareInBill) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetSpareInBillBySpareOutBillId")
								.setParameter("sobid", spareOutBillId).uniqueResult();
					}
				});
	}

	public void storeSpareInBillDetail(SpareInBillDetail spareInBill) {
		// TODO Auto-generated method stub
		this.store(spareInBill);
		
	}

}

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
package com.yongjun.tdms.dao.asset.spare.outWareHouse.hibernate;

import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.asset.spare.outWareHouse.SpareOutBillDao;
import com.yongjun.tdms.model.asset.spare.outWareHouse.SpareOutBill;
import com.yongjun.tdms.model.asset.spare.outWareHouse.SpareOutBillDetail;

/**
 * <p>Title: HibernateSpareOutBillDao
 * <p>Description: 备件出库数据访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @see com.yongjun.tdms.dao.asset.spare.outWareHouse.SpareOutBillDao
 * @version $Id:$
 */
public class HibernateSpareOutBillDao extends BaseHibernateDao implements
		SpareOutBillDao {

	public List<SpareOutBill> loadAllSpareOutBill(Long[] spareOutBillIds) {
		return this.loadAll(SpareOutBill.class,spareOutBillIds);
	}

	public SpareOutBill loadSpareOutBill(Long spareOutBillId) {
		return this.load(SpareOutBill.class,spareOutBillId);
	}

	public void storeSpareOutBill(SpareOutBill spareOutBill) {
		this.store(spareOutBill);
	}

	public void deleteSpareOutBill(SpareOutBill spareOutBill) {
		this.delete(spareOutBill);
	}

	public void deleteAllSpareOutBill(Collection<SpareOutBill> spareOutBill) {
		this.deleteAll(spareOutBill);
	}
	
	  /**
     * 查询列表下所有金额
     * @param param
     * @return
     */
	public Double showOutTotalPrice(String[] param){
		Session session = getSession();
		Double result = 0.0;
		String hql = "SELECT sum(spareOutBill.totalPrice) FROM SpareOutBill as spareOutBill where 1=1 "
				+ " AND spareOutBill.oldSpare is null";
		if(!"".equals(param[0])){
			hql = hql + " AND spareOutBill.code LIKE '%"+param[0].trim()+"%'";
		}
		if(!"".equals(param[1])){
			hql = hql + " AND spareOutBill.name LIKE '%"+param[1].trim()+"%'";
		}
		if(!"".equals(param[2])){
			hql = hql + " AND spareOutBill.department.id = "+param[2].trim();
		}
		if(!"".equals(param[3])){
			hql = hql + " AND spareOutBill.borrower LIKE '%"+param[3].trim()+"%'";
		}
		if(!"".equals(param[4])){
			hql = hql + " AND spareOutBill.outPeople.name LIKE '%"+param[4].trim()+"%'";
		}
		if(!"".equals(param[5])){
			hql = hql + " AND spareOutBill.outDate >= '"+param[5].trim()+"'";
		}
		if(!"".equals(param[6])){
			hql = hql + " AND spareOutBill.outDate <= '"+param[6].trim()+"'";
		}
		if(!"".equals(param[7])){
			hql = hql + " AND spareOutBill.storageGrade.id = "+param[7].trim();
		}
//		if(!"".equals(param[8])){
//			hql = hql + " AND spareOutBill.warehouse.id = "+param[8].trim();
//		}
		if(!"".equals(param[8])){
			hql = hql + " AND spareOutBill.warehouse.id in ( "+param[8].trim()+" )";
		}
//		if(null != list && list.size()>0){
//			hql = hql +" AND spareOutBill.warehouse.id in  "+ary;
//		}
		
		if(!"".equals(param[9])){
			hql = hql + " AND spareOutBill.inWarehouse.id = "+param[9].trim();
		}
		if(!"".equals(param[10])){
			hql = hql + " AND spareOutBill.disabled = true";
		}
		if(!"".equals(param[11])){
			hql = hql + " AND spareOutBill.disabled = false";
		}
		if(!"".equals(param[12])){
			hql = hql + " AND spareOutBill.status = '"+param[12]+"'";
		}
		System.out.println("hql=="+hql);
		
		Query query = session.createQuery(hql);
		result = (Double)query.uniqueResult();
		return result;
	}

	/**
     * 查询列表下所有金额
     * @param param
     * @return
     */
	public Double showOldOutTotalPrice(String[] param){
		Session session = getSession();
		Double result = 0.0;
		String hql = "SELECT sum(spareOutBill.totalPrice) FROM SpareOutBill as spareOutBill where 1=1 "
				+ " AND spareOutBill.outType = '0' ";
		if(!"".equals(param[0])){
			hql = hql + " AND spareOutBill.code LIKE '%"+param[0].trim()+"%'";
		}
		if(!"".equals(param[1])){
			hql = hql + " AND spareOutBill.name LIKE '%"+param[1].trim()+"%'";
		}
		if(!"".equals(param[2])){
			hql = hql + " AND spareOutBill.department.id = "+param[2].trim();
		}
		if(!"".equals(param[3])){
			hql = hql + " AND spareOutBill.borrower LIKE '%"+param[3].trim()+"%'";
		}
		if(!"".equals(param[4])){
			hql = hql + " AND spareOutBill.outPeople.name LIKE '%"+param[4].trim()+"%'";
		}
		if(!"".equals(param[5])){
			hql = hql + " AND spareOutBill.outDate >= '"+param[5].trim()+"'";
		}
		if(!"".equals(param[6])){
			hql = hql + " AND spareOutBill.outDate <= '"+param[6].trim()+"'";
		}
		if(!"".equals(param[7])){
			hql = hql + " AND spareOutBill.storageGrade.id = "+param[7].trim();
		}
//		if(!"".equals(param[8])){
//			hql = hql + " AND spareOutBill.warehouse.id = "+param[8].trim();
//		}
		if(!"".equals(param[8])){
			hql = hql + " AND spareOutBill.warehouse.id in ( "+param[8].trim()+" )";
		}
//		if(null != list && list.size()>0){
//			hql = hql +" AND spareOutBill.warehouse.id in  "+ary;
//		}
		
		if(!"".equals(param[9])){
			hql = hql + " AND spareOutBill.inWarehouse.id = "+param[9].trim();
		}
		if(!"".equals(param[10])){
			hql = hql + " AND spareOutBill.disabled = true";
		}
		if(!"".equals(param[11])){
			hql = hql + " AND spareOutBill.disabled = false";
		}
		if(!"".equals(param[12])){
			hql = hql + " AND spareOutBill.status = '"+param[12]+"'";
		}
		System.out.println("hql=="+hql);
		
		Query query = session.createQuery(hql);
		result = (Double)query.uniqueResult();
		return result;
	}
	
	/**
     * 查询列表下所有金额
     * @param param
     * @return
     */
	public Double showOldScrapTotalPrice(String[] param){
		Session session = getSession();
		Double result = 0.0;
		String hql = "SELECT sum(spareOutBill.totalPrice) FROM SpareOutBill as spareOutBill where 1=1 "
				+ " AND spareOutBill.outType = '1' ";
		if(!"".equals(param[0])){
			hql = hql + " AND spareOutBill.code LIKE '%"+param[0].trim()+"%'";
		}
		if(!"".equals(param[1])){
			hql = hql + " AND spareOutBill.name LIKE '%"+param[1].trim()+"%'";
		}
		if(!"".equals(param[2])){
			hql = hql + " AND spareOutBill.department.id = "+param[2].trim();
		}
		if(!"".equals(param[3])){
			hql = hql + " AND spareOutBill.borrower LIKE '%"+param[3].trim()+"%'";
		}
		if(!"".equals(param[4])){
			hql = hql + " AND spareOutBill.outPeople.name LIKE '%"+param[4].trim()+"%'";
		}
		if(!"".equals(param[5])){
			hql = hql + " AND spareOutBill.outDate >= '"+param[5].trim()+"'";
		}
		if(!"".equals(param[6])){
			hql = hql + " AND spareOutBill.outDate <= '"+param[6].trim()+"'";
		}
		if(!"".equals(param[7])){
			hql = hql + " AND spareOutBill.storageGrade.id = "+param[7].trim();
		}
//		if(!"".equals(param[8])){
//			hql = hql + " AND spareOutBill.warehouse.id = "+param[8].trim();
//		}
		if(!"".equals(param[8])){
			hql = hql + " AND spareOutBill.warehouse.id in ( "+param[8].trim()+" )";
		}
//		if(null != list && list.size()>0){
//			hql = hql +" AND spareOutBill.warehouse.id in  "+ary;
//		}
		
		if(!"".equals(param[9])){
			hql = hql + " AND spareOutBill.inWarehouse.id = "+param[9].trim();
		}
		if(!"".equals(param[10])){
			hql = hql + " AND spareOutBill.disabled = true";
		}
		if(!"".equals(param[11])){
			hql = hql + " AND spareOutBill.disabled = false";
		}
		if(!"".equals(param[12])){
			hql = hql + " AND spareOutBill.status = '"+param[12]+"'";
		}
		System.out.println("hql=="+hql);
		
		Query query = session.createQuery(hql);
		result = (Double)query.uniqueResult();
		return result;
	}
	
	/**
     * 查询列表下所有金额
     * @param param
     * @return
     */
	public Double showOldMaintainTotalPrice(String[] param){
		Session session = getSession();
		Double result = 0.0;
		String hql = "SELECT sum(spareOutBill.totalPrice) FROM SpareOutBill as spareOutBill where 1=1 "
				+ " AND spareOutBill.outType = '2' ";
		if(!"".equals(param[0])){
			hql = hql + " AND spareOutBill.code LIKE '%"+param[0].trim()+"%'";
		}
		if(!"".equals(param[1])){
			hql = hql + " AND spareOutBill.name LIKE '%"+param[1].trim()+"%'";
		}
		if(!"".equals(param[2])){
			hql = hql + " AND spareOutBill.department.id = "+param[2].trim();
		}
		if(!"".equals(param[3])){
			hql = hql + " AND spareOutBill.borrower LIKE '%"+param[3].trim()+"%'";
		}
		if(!"".equals(param[4])){
			hql = hql + " AND spareOutBill.outPeople.name LIKE '%"+param[4].trim()+"%'";
		}
		if(!"".equals(param[5])){
			hql = hql + " AND spareOutBill.outDate >= '"+param[5].trim()+"'";
		}
		if(!"".equals(param[6])){
			hql = hql + " AND spareOutBill.outDate <= '"+param[6].trim()+"'";
		}
		if(!"".equals(param[7])){
			hql = hql + " AND spareOutBill.storageGrade.id = "+param[7].trim();
		}
//		if(!"".equals(param[8])){
//			hql = hql + " AND spareOutBill.warehouse.id = "+param[8].trim();
//		}
		if(!"".equals(param[8])){
			hql = hql + " AND spareOutBill.warehouse.id in ( "+param[8].trim()+" )";
		}
//		if(null != list && list.size()>0){
//			hql = hql +" AND spareOutBill.warehouse.id in  "+ary;
//		}
		
		if(!"".equals(param[9])){
			hql = hql + " AND spareOutBill.inWarehouse.id = "+param[9].trim();
		}
		if(!"".equals(param[10])){
			hql = hql + " AND spareOutBill.disabled = true";
		}
		if(!"".equals(param[11])){
			hql = hql + " AND spareOutBill.disabled = false";
		}
		if(!"".equals(param[12])){
			hql = hql + " AND spareOutBill.status = '"+param[12]+"'";
		}
		System.out.println("hql=="+hql);
		
		Query query = session.createQuery(hql);
		result = (Double)query.uniqueResult();
		return result;
	}

	public List<SpareOutBillDetail> loadAllSpareOutBill(long spareOutBillId) {
		// TODO Auto-generated method stub
		Session session = getSession();
		String hql = "from SpareOutBillDetail s where s.spareOutBill.id = "+spareOutBillId;
		Query query = session.createQuery(hql);
		List<SpareOutBillDetail> details = query.list();
		
		return details;
	}
	public List<SpareOutBillDetail> loadAllSpareOutBill(long spareOutBillId,String outDetailids) {
		// TODO Auto-generated method stub
		Session session = getSession();
		String hql = "from SpareOutBillDetail s where s.spareOutBill.id = "+spareOutBillId ;
		if(outDetailids!=null&&!outDetailids.equals("")){
		hql+=" and s.id not in ("+outDetailids+")"	;
		}
		Query query = session.createQuery(hql);
		List<SpareOutBillDetail> details = query.list();
		
		return details;
	}
}

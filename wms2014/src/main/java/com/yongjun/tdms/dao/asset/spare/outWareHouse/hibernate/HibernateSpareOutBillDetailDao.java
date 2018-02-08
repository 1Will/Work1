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

import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.asset.spare.outWareHouse.SpareOutBillDetailDao;
import com.yongjun.tdms.model.asset.spare.SpareLocation;
import com.yongjun.tdms.model.asset.spare.outWareHouse.SpareOutBillDetail;

/**
 * <p>Title: HibernateSpareOutBillDetailDao
 * <p>Description: 备件出库明细数据访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @see com.yongjun.tdms.dao.asset.spare.outWareHouse.SpareOutBillDetailDao
 * @version $
 */
public class HibernateSpareOutBillDetailDao extends BaseHibernateDao implements
		SpareOutBillDetailDao {

	public List<SpareOutBillDetail> loadAllSpareOutBillDetail(Long[] spareOutBillDetailIds) {
		return this.loadAll(SpareOutBillDetail.class,spareOutBillDetailIds);
	}

	public SpareOutBillDetail loadSpareOutBillDetail(Long spareOutBillDetailId) {
		return this.load(SpareOutBillDetail.class,spareOutBillDetailId);
	}

	public void storeSpareOutBillDetail(SpareOutBillDetail spareOutBillDetail) {
		this.store(spareOutBillDetail);
	}

	public void deleteSpareOutBillDetail(SpareOutBillDetail spareOutBillDetail) {
		this.delete(spareOutBillDetail);
	}

	public void deleteAllSpareOutBillDetail(Collection<SpareOutBillDetail> spareOutBillDetails) {
		this.deleteAll(spareOutBillDetails);
	}

	public List<Integer> loadSpareLocations(String warehouseid) {
		// TODO Auto-generated method stub
		String hql =" select t.id sid from t_spare_location t , t_spare  s where (s.OLD_SPARE='0'  and t.spareId=s.id)  AND (s.disableStocks > 0 ) and (t.warehouseId="+warehouseid+") ";
		
		Query query = this.getSession().createSQLQuery(hql).addScalar("sid",Hibernate.INTEGER);
		return query.list();
	}

	public Integer loadSpareOutBillDetailCount(String outBillId) {
		// TODO Auto-generated method stub
	  String hql =" select count(*) from SpareOutBillDetail s where s.spareOutBill.id="+ outBillId;
	  Query query = this.getSession().createQuery(hql);
	  List list = query.list();
	  
	  if(list!=null&&list.size()>0){
		  return (Integer)list.get(0);
	  }else{
		  return 0;
	  }
	  
	}

}

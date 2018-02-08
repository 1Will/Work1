package com.yongjun.tdms.dao.asset.spare.outWareHouse;

import java.util.List;

import org.hibernate.HibernateException;

import com.yongjun.tdms.model.asset.spare.outWareHouse.SpareOutBillDetailView;
public interface SpareOutBillDetailViewDao {
	List<SpareOutBillDetailView> loadSpareOutBillDetail(Long outWareBillId);
	List<SpareOutBillDetailView> loadSpareOutBillDetail(String[] array)throws HibernateException;
	  
}

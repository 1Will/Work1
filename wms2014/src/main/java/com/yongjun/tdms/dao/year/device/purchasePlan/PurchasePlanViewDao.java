package com.yongjun.tdms.dao.year.device.purchasePlan;

import java.util.List;

import org.hibernate.HibernateException;

import com.yongjun.tdms.model.year.device.purchasePlan.PurchasePlanView;

public interface PurchasePlanViewDao {

	public List<PurchasePlanView> loadAllPurchasePlanView(String[] array) throws HibernateException;
}

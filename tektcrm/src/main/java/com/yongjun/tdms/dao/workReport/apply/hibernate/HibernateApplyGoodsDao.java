package com.yongjun.tdms.dao.workReport.apply.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.workReport.apply.ApplyGoodsDao;
import com.yongjun.tdms.model.workReport.apply.ApplyGoods;

public class HibernateApplyGoodsDao extends BaseHibernateDao  implements ApplyGoodsDao{

	public void storeApplyGoods(ApplyGoods applyGoods) {
		store(applyGoods);
	}

	public void deleteApplyGoods(ApplyGoods applyGoods) {
		delete(applyGoods);
	}

	public void deleteApplyGoods(Collection<ApplyGoods> paramCollection) {
		deleteAll(paramCollection);
	}

	public List<ApplyGoods> loadAllApplyGoods(Long[] paramArrayOfLong) {
		return loadAll(ApplyGoods.class, paramArrayOfLong);
	}

	public ApplyGoods loadApplyGoods(Long paramLong) {
		return load(ApplyGoods.class, paramLong);
	}

	public List<ApplyGoods> loadAllApplyGoods() {
		return loadAll(ApplyGoods.class);
	}

	public List<ApplyGoods> loadByKey(String paramString, Object paramObject) throws DaoException {
		return loadByKey(ApplyGoods.class, paramString, paramObject);
	}

	public List<ApplyGoods> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
			throws DaoException {
		return loadByKeyArray(ApplyGoods.class, paramArrayOfString, paramArrayOfObject);
	}

}

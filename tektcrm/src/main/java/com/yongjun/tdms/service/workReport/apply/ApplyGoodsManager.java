package com.yongjun.tdms.service.workReport.apply;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.workReport.apply.ApplyGoods;

public abstract interface ApplyGoodsManager {
	 public abstract void storeApplyGoods(ApplyGoods applyGoods);

	  public abstract void deleteApplyGoods(ApplyGoods applyGoods);

	  public abstract void deleteApplyGoods(Collection<ApplyGoods> paramCollection);

	  public abstract List<ApplyGoods> loadAllApplyGoods(Long[] paramArrayOfLong);

	  public abstract ApplyGoods loadApplyGoods(Long paramLong);

	  public abstract List<ApplyGoods> loadAllApplyGoods();

	  public abstract List<ApplyGoods> loadByKey(String paramString, Object paramObject)
	    throws DaoException;

	  public abstract List<ApplyGoods> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
	    throws DaoException;

	public abstract void disabledAllApplyGoods(List<ApplyGoods> goods);

	public abstract void enabledAllApplyGoods(List<ApplyGoods> goods);
}

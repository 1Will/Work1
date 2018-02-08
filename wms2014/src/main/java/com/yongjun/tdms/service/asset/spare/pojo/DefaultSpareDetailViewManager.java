package com.yongjun.tdms.service.asset.spare.pojo;

import java.util.List;

import org.hibernate.HibernateException;

import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.asset.spare.SpareDetailViewDao;
import com.yongjun.tdms.model.asset.spare.SpareDetailView;
import com.yongjun.tdms.service.asset.spare.SpareDetailViewManager;

public class DefaultSpareDetailViewManager extends BaseManager implements SpareDetailViewManager {
   private final SpareDetailViewDao spareDetailViewDao;
   public DefaultSpareDetailViewManager(SpareDetailViewDao spareDetailViewDao){
	   this.spareDetailViewDao=spareDetailViewDao;
   }
	/**
	 * 根据页面传入的参数,从备件明细视图中获得备件台帐明细的集合 
	 * @param array
	 * @return
	 * @throws HibernateException
	 */
	public List<SpareDetailView> loadSpareDetailAccount(String[] array) throws HibernateException {
		return spareDetailViewDao.loadSpareDetailAccount(array);
	}
	/**
	 * 根据页面传入的参数,从备件明细视图中获得备件台帐明细的集合 zzb
	 * @param array
	 * @return
	 * @throws HibernateException
	 */
	public List<SpareDetailView> loadSpareLocation(String[] array) throws HibernateException {
		return spareDetailViewDao.loadSpareLocation(array);
	}

}

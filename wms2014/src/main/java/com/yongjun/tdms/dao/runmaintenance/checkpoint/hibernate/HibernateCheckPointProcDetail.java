package com.yongjun.tdms.dao.runmaintenance.checkpoint.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.runmaintenance.checkpoint.CheckPointProcDetailDao;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointProcDetail;

/**
 * @author qs
 * @version $Id: $
 */
public class HibernateCheckPointProcDetail extends BaseHibernateDao implements CheckPointProcDetailDao{


    public void storeCheckPointProcDetail(CheckPointProcDetail checkPointProcDetail){
    	this.store(checkPointProcDetail);
    }
	
	public CheckPointProcDetail loadCheckPointProcDetail(Long checkPointProcDetailIds){
		return this.load(CheckPointProcDetail.class,checkPointProcDetailIds);
	}
	
	public void deleteCheckPointProcDetail(CheckPointProcDetail checkPointProcDetail){
		this.delete(checkPointProcDetail);
	}
	
	public void deleteAllCheckPointProcDetail(Collection<CheckPointProcDetail> checkPointProcDetailIds){
	    this.deleteAll(checkPointProcDetailIds);	
	}
	
	public List<CheckPointProcDetail> loadAllCheckPointProcDetail(Long [] checkPointProcDetailIds){
		return this.loadAll(CheckPointProcDetail.class,checkPointProcDetailIds);
	}
}

package com.yongjun.tdms.dao.runmaintenance.checkpoint.hibernate;

import java.util.Collection;
import java.util.List;
import com.yongjun.tdms.dao.runmaintenance.checkpoint.CheckPointProcDao;
import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointProc;

public class HibernateCheckPointProc extends BaseHibernateDao implements CheckPointProcDao{

	    public void storeCheckPointProc(CheckPointProc checkPointProc){
		    this.store(checkPointProc);
	     }
		
		public CheckPointProc loadCheckPointProc(Long checkPointProcIds){
			return this.load(CheckPointProc.class,checkPointProcIds);
		}
		
		public void deleteCheckPointProc(CheckPointProc checkPointProc){
			this.delete(checkPointProc);
		}
		
		public void deleteAllCheckPointProc(Collection<CheckPointProc> checkPointProcIds){
			this.deleteAll(checkPointProcIds);
		}
		
		public List<CheckPointProc> loadAllCheckPointProc(Long [] checkPointProcIds){
			return this.loadAll(CheckPointProc.class,checkPointProcIds);
		}
}

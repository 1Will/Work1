package com.yongjun.tdms.dao.runmaintenance.checkpoint;

import java.util.Collection;
import java.util.List;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointProc;


public interface CheckPointProcDao { 
	
	    public void storeCheckPointProc(CheckPointProc checkPointProc);
		
		public CheckPointProc loadCheckPointProc(Long checkPointProcIds);
		
		public void deleteCheckPointProc(CheckPointProc checkPointProc);
		
		public void deleteAllCheckPointProc(Collection<CheckPointProc> checkPointProcIds);
		
		public List<CheckPointProc> loadAllCheckPointProc(Long [] checkPointProcIds);
}

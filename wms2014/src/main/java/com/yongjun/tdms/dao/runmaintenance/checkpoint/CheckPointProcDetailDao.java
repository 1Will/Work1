package com.yongjun.tdms.dao.runmaintenance.checkpoint;

import java.util.Collection;
import java.util.List;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointProcDetail;

public interface CheckPointProcDetailDao {

	    public void storeCheckPointProcDetail(CheckPointProcDetail checkPointProcDetail);
		
		public CheckPointProcDetail loadCheckPointProcDetail(Long checkPointProcDetailIds);
		
		public void deleteCheckPointProcDetail(CheckPointProcDetail checkPointProcDetail);
		
		public void deleteAllCheckPointProcDetail(Collection<CheckPointProcDetail> checkPointProcDetailIds);
		
		public List<CheckPointProcDetail> loadAllCheckPointProcDetail(Long [] checkPointProcDetailIds);
}

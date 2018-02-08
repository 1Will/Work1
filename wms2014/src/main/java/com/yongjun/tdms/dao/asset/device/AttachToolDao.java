package com.yongjun.tdms.dao.asset.device;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.model.asset.device.AttachTool;

/**
 * @author qs
 * @version $Id: AttachToolDao.java 7451 2007-09-19 01:08:48Z qsun $
 */
public interface AttachToolDao {
	public void storeAttachTool(AttachTool attachTool);
	
	public AttachTool loadAttachTool(Long attachToolId);
	
	public List<AttachTool> loadAllAttachTool(Long [] attachToolIds);
	
	public void deleteAttachTool(AttachTool attachTool);
	
	public void deleteAllAttachTool(Collection <AttachTool> attachTools);
}

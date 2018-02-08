package com.yongjun.tdms.service.asset.device;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.asset.device.AttachTool;

/**
 * @author qs
 * @version $Id: $
 */
@Transactional(readOnly = true)
public interface AttachToolManager {
	@Transactional
	public void storeAttachTool(AttachTool attachTool);
	
	public AttachTool loadAttachTool(Long attachToolId);
	
	public List<AttachTool> loadAllAttachTool(Long [] attachToolIds);
	
	@Transactional
	public void deleteAttachTool(AttachTool attachTool);
	
	@Transactional
	public void deleteAllAttachTool(Collection<AttachTool> attachTools);

}

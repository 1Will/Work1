package com.yongjun.tdms.service.asset.device.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.dao.asset.device.AttachToolDao;
import com.yongjun.tdms.model.asset.device.AttachTool;
import com.yongjun.tdms.service.asset.device.AttachToolManager;

/**
 * @author qs
 * @version $Id: DefaultAttachToolManager.java 7478 2007-09-19 06:50:14Z zbzhang $
 */
public class DefaultAttachToolManager implements AttachToolManager {
	private final AttachToolDao attachToolDao;
	
	public DefaultAttachToolManager(AttachToolDao attachToolDao) {
		this.attachToolDao = attachToolDao;
	}

	public void storeAttachTool(AttachTool attachTool) {

		/*
		Long maxSerialNo = attachToolDao.getMaxSerialNo(attachTool.getDevice());
		if (attachTool.isNew()) {			
			attachTool.setSerialNo(maxSerialNo+1L);
		}

		*/
		this.attachToolDao.storeAttachTool(attachTool);
	}

	public AttachTool loadAttachTool(Long attachToolId) {
		return this.attachToolDao.loadAttachTool(attachToolId);	
	}

	public void deleteAttachTool(AttachTool attachTool) {
		this.attachToolDao.deleteAttachTool(attachTool);
	}

	public void deleteAllAttachTool(Collection<AttachTool> attachTools) {
		this.attachToolDao.deleteAllAttachTool(attachTools);
	}

	public List<AttachTool> loadAllAttachTool(Long[] attachToolIds) {
		return this.attachToolDao.loadAllAttachTool(attachToolIds);
	}

}

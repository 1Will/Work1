package com.yongjun.tdms.dao.asset.device.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.asset.device.AttachToolDao;
import com.yongjun.tdms.model.asset.device.AttachTool;

/**
 * @author qs
 * @version $Id: HibernateAttachTool.java 7451 2007-09-19 01:08:48Z qsun $
 */
public class HibernateAttachTool extends BaseHibernateDao implements AttachToolDao {

	public void storeAttachTool(AttachTool attachTool) {
		this.store(attachTool);
	}

	public AttachTool loadAttachTool(Long attachToolId) {
		return this.load(AttachTool.class, attachToolId);
	}

	public void deleteAttachTool(AttachTool attachTool) {
		this.delete(attachTool);
	}
	
	public void deleteAllAttachTool(Collection <AttachTool> attachTools) {
		this.deleteAll(attachTools);
	}

	public List<AttachTool> loadAllAttachTool(Long[] attachToolIds) {
		return this.loadAll(AttachTool.class, attachToolIds);
	}
}

package com.yongjun.tdms.service.base.codevalue.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.base.codevalue.ToolingTypeDetailDao;
import com.yongjun.tdms.model.base.codevalue.ToolingType;
import com.yongjun.tdms.model.base.codevalue.ToolingTypeDetail;
import com.yongjun.tdms.service.base.codevalue.ToolingTypeDetailManager;

public class DefaultToolingTypeDetailManager extends BaseManager implements ToolingTypeDetailManager {
	private final ToolingTypeDetailDao toolingTypeDetailDao;
	
	public DefaultToolingTypeDetailManager(ToolingTypeDetailDao toolingTypeDetailDao) {
		this.toolingTypeDetailDao = toolingTypeDetailDao;
	}
	public ToolingTypeDetail loadToolingTypeDetail(Long toolingTypeDetailId) {
		return this.toolingTypeDetailDao.loadToolingTypeDetail(toolingTypeDetailId);
	}

	public List<ToolingTypeDetail> loadAllToolingTypeDetails(Long[] toolingTypeDetailIds) {
		return this.toolingTypeDetailDao.loadAllToolingTypeDetails(toolingTypeDetailIds);
	}

	public List<ToolingTypeDetail> loadAllToolingTypeDetails() {
		return this.toolingTypeDetailDao.loadAllToolingTypeDetails();
	}

	public void storeToolingTypeDetail(ToolingTypeDetail toolingTypeDetail) {
		this.toolingTypeDetailDao.storeToolingTypeDetail(toolingTypeDetail);
	}

	public void deleteToolingTypeDetail(ToolingTypeDetail toolingTypeDetail) {
		this.toolingTypeDetailDao.deleteToolingTypeDetail(toolingTypeDetail);
	}

	public void deleteAllToolingTypeDetails(Collection<ToolingTypeDetail> toolingTypeDetails) {
		this.toolingTypeDetailDao.deleteAllToolingTypeDetails(toolingTypeDetails);
	}
	public List createSelectToolingTypeDetail(String name) {
		List<ToolingTypeDetail> toolingTypeDetails = this.loadAllToolingTypeDetails();
		ToolingTypeDetail toolingTypeDetail = new ToolingTypeDetail();
		toolingTypeDetail.setId(Long.valueOf(-1L));
		toolingTypeDetail.setName(name);
		toolingTypeDetails.add(0,toolingTypeDetail);
		//ToolingType tooling=toolingTypeDetail.getToolingType();
//		if(tooling!=null){
//			toolingTypeDetails.remove(toolingTypeDetail.getToolingType());	
//		}
		return toolingTypeDetails;
	}

}

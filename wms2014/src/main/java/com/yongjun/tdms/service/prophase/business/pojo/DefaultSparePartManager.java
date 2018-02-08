package com.yongjun.tdms.service.prophase.business.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.prophase.business.SparePartDao;
import com.yongjun.tdms.model.prophase.business.SparePart;
import com.yongjun.tdms.service.prophase.business.SparePartManager;

public class DefaultSparePartManager extends BaseManager implements SparePartManager{
   private final SparePartDao  sparePartDao;
   public DefaultSparePartManager(SparePartDao  sparePartDao){
	   this.sparePartDao=sparePartDao;
   }
	public SparePart loadSparePart(Long id) {
		return sparePartDao.loadSparePart(id);
	}
	public List<SparePart> loadSpareParts(Long[] SparePartIds) {
		return sparePartDao.loadSpareParts(SparePartIds);
	}
	public void storeSparePart(SparePart sparePart) {
		   this.sparePartDao.storeSparePart(sparePart);
	}
	public void deleteSparePart(SparePart sparePart) {
		sparePartDao.deleteSparePart(sparePart);
	}
	public void deleteAllSparePart(Collection<SparePart> SparePartIds) {
		this.sparePartDao.deleteAllSparePart(SparePartIds);
	}
}

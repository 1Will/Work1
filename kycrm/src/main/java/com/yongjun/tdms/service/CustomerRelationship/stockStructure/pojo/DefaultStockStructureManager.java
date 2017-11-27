package com.yongjun.tdms.service.CustomerRelationship.stockStructure.pojo;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.tdms.dao.CustomerRelationship.stock.StockStructureDao;
import com.yongjun.tdms.model.CustomerRelationship.stock.StockStructure;
import com.yongjun.tdms.service.CustomerRelationship.stockStructure.StockStructureManager;

public class DefaultStockStructureManager extends BaseManager implements StockStructureManager {
	public final StockStructureDao StockStructureDao;
	public DefaultStockStructureManager(StockStructureDao StockStructureDao) {
		this.StockStructureDao = StockStructureDao;
	}

	public void storeStockStructure(StockStructure ca) {
		this.StockStructureDao.storeStockStructure(ca);
	}


	public StockStructure loadStockStructure(Long caId) {
		return this.StockStructureDao.loadStockStructure(caId);
	}

	public List<StockStructure> loadAllStockStructure() {
		return this.StockStructureDao.loadAllStockStructure();
	}

	public List<StockStructure> loadAllStockStructure(Long[] caIds) {
		return this.StockStructureDao.loadAllStockStructure(caIds);
	}

	public void deleteStockStructure(StockStructure ca) {
		this.StockStructureDao.deleteStockStructure(ca);
	}

	public void deleteAllStockStructure(List<StockStructure> caIds) {
		this.StockStructureDao.deleteAllStockStructure(caIds);
	}

	public List<StockStructure> loadByKey(String key, Object value) throws DaoException {
		return this.StockStructureDao.loadByKey(key, value);
	}

	public void disabledAllStockStructure(List<StockStructure> cas) {
		for (StockStructure archives : cas) {
			archives.setDisabled(true);
			this.StockStructureDao.storeStockStructure(archives);
		}
	}

	public void enabledAllStockStructure(List<StockStructure> cas) {
		for (StockStructure archives : cas) {
			archives.setDisabled(false);
			this.StockStructureDao.storeStockStructure(archives);
		}
	}

	public List<StockStructure> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return this.StockStructureDao.loadByKeyArray(keyNames, keyValues);
	}

	

	

}

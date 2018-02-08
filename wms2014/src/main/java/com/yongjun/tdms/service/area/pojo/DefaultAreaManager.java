package com.yongjun.tdms.service.area.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.area.Area;
import com.yongjun.tdms.dao.area.AreaDao;
import com.yongjun.tdms.service.area.AreaManager;

/**
 * @author qs
 * @version $Id: DefaultAreaManager.java 7034 2007-09-08 10:36:59Z wzou $
 */
public class DefaultAreaManager extends BaseManager implements AreaManager {
	protected final AreaDao areaDao;

	public DefaultAreaManager(AreaDao areaDao) {
		this.areaDao = areaDao;
	}

	public List<Area> loadAllAreas(Long[] areas) {
		return areaDao.loadAllAreas(areas);
	}

	public void deleteAllArea(Collection areas) {
		areaDao.deleteAllArea(areas);
	}

	public Area loadArea(Long id) {
		return areaDao.loadAllAreas(new Long[] { id }).get(0);
	}

	public void deleteArea(Area area) {
		areaDao.deleteArea(area);
	}

	public void storeArea(Area area) {
		areaDao.storeArea(area);
	}
}

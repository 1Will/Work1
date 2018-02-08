package com.yongjun.tdms.dao.area.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.area.Area;
import com.yongjun.tdms.dao.area.AreaDao;

/**
 * @author qs
 * @version $Id: HibernateAreaDao.java 7034 2007-09-08 10:36:59Z wzou $
 */
public class HibernateAreaDao extends BaseHibernateDao implements AreaDao {

	public void storeArea(Area area) {
		this.store(area);
	}

	public void deleteArea(Area area) {
		delete(area);
	}

	@SuppressWarnings("unchecked")
	public void deleteAllArea(Collection areas) {
		deleteAll(areas);
	}

	public List<Area> loadAllAreas(Long[] areaIds) {
		return loadAll(Area.class, areaIds);
	}
}

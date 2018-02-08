package com.yongjun.tdms.dao.area;

import java.util.Collection;
import java.util.List;

/**
 * @author qs
 * @version $Id: AreaDao.java 7034 2007-09-08 10:36:59Z wzou $
 */
public interface AreaDao {

	void storeArea(Area area);

	void deleteArea(Area area);

	void deleteAllArea(Collection areas);

	List<Area> loadAllAreas(Long[] areaIds);
}

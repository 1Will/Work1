package com.yongjun.tdms.service.area;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.dao.area.Area;


/**
 * @author qs
 * @version $Id: AreaManager.java 6936 2007-09-06 10:16:36Z qsun $
 */
@Transactional(readOnly = true)
public interface AreaManager  {
	
	List<Area> loadAllAreas(Long[] areaIds);

	void deleteAllArea(Collection areas);

	Area loadArea(Long id);

	@Transactional
	void deleteArea(Area area);

	@Transactional
	void storeArea(Area area);
}

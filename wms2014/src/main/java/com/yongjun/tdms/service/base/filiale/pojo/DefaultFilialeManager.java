package com.yongjun.tdms.service.base.filiale.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.Filiale;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.base.filiale.FilialeDao;
import com.yongjun.tdms.service.base.filiale.FilialeManager;

/**
 * <p>Title: DefaultDepartmentManager
 * <p>Description: 分公司管理业务实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author mfzhang@yj-technology.com
 * @version 
 * @see com.yongjun.tdms.service.base.filiale.FilialeManager
 */
public class DefaultFilialeManager extends BaseManager implements
		FilialeManager {

	private final FilialeDao filialeDao;
	
	public DefaultFilialeManager(FilialeDao filialeDao ){
		this.filialeDao = filialeDao;
	}
	
	public void deleteAllFiliale(Collection<Filiale> filiales) {
		this.filialeDao.deleteAllFiliale(filiales);
	}

	public void deleteFiliale(Filiale filiale) {
		this.filialeDao.deleteFiliale(filiale);
	}

	public List<Department> loadAllDepartment() {
		return this.filialeDao.loadAllDepartment();
	}

	public List<Filiale> loadAllFiliale(Long[] filialeIds) {
		return this.filialeDao.loadAllFiliale(filialeIds);
	}

	public Filiale loadFiliale(Long filialeId) {
		return this.filialeDao.loadFiliale(filialeId);
	}

	public void storeFiliale(Filiale filiale) {
		this.filialeDao.storeFiliale(filiale);
	}

	public List<Filiale> loadAllFiliale() {
	    return filialeDao.loadAllFiliale();
    }
	public List createSelectFilailes(String name) {
		List<Filiale> list = filialeDao.loadAllFiliale();
		Filiale filiale = new Filiale();
		filiale.setId(Long.valueOf(-1L));
		filiale.setName(name);
		list.add(0, filiale);
		return list;
	}
	public void disableAllFiliales(List<Filiale> filiales) {
		for(Filiale filiale : filiales){
			filiale.setDisabled(true);
			this.filialeDao.storeFiliale(filiale);
		}
	}

	public void enabledAllFiliales(Collection<Filiale> filiales) {
		for(Filiale filiale : filiales){
			filiale.setDisabled(false);
			this.filialeDao.storeFiliale(filiale);
		}
	}
	
}

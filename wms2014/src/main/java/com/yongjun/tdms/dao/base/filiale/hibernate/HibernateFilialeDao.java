/**
 * 
 */
package com.yongjun.tdms.dao.base.filiale.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.Filiale;
import com.yongjun.tdms.dao.base.filiale.FilialeDao;

/**
 *<p>Title:HibernateFilialeDao.java
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author yangli@yj-technology.com
 * @version 
 */
public class HibernateFilialeDao extends BaseHibernateDao implements FilialeDao {


	public void deleteAllFiliale(Collection<Filiale> filiales) {
		this.deleteAll(filiales);
	}


	public void deleteFiliale(Filiale filiale) {
		this.delete(filiale);
	}


	public List<Department> loadAllDepartment() {
		return null;
	}


	public Filiale loadFiliale(Long filialeId) {
		return this.load(Filiale.class, filialeId);
	}


	public void storeFiliale(Filiale filiale) {
		this.store(filiale);
	}


	public List<Filiale> loadAllFiliale(Long[] filialeIds) {
	    return this.loadAll(Filiale.class,filialeIds);
    }


	public List<Filiale> loadAllFiliale() {
	    return this.loadAll(Filiale.class);
    }
}

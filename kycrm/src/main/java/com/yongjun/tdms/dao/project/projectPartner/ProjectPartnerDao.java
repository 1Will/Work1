package com.yongjun.tdms.dao.project.projectPartner;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.project.projectPartner.ProjectPartner;

public interface ProjectPartnerDao {
	public abstract void storeProjectPartner(ProjectPartner paramProjectPartner);

	  public abstract ProjectPartner loadProjectPartner(Long paramLong);

	  public abstract List<ProjectPartner> loadAllProjectPartner(Long[] paramArrayOfLong);

	  public abstract List<ProjectPartner> loadAllProjectPartners();

	  public abstract void deleteProjectPartner(ProjectPartner paramProjectPartner);

	  public abstract void deleteAllProjectPartner(Collection<ProjectPartner> paramCollection);

	  public abstract List<ProjectPartner> loadByKey(String paramString, Object paramObject)
	    throws DaoException;

	  public abstract List<ProjectPartner> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
	    throws DaoException;
}

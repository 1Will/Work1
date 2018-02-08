package com.yongjun.tdms.service.base.additionalInformation;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.base.additionalInformation.AdditionalInformation;

public interface AdditionalInformationManager {

	public abstract void storeAdditionalInformation(AdditionalInformation additionalInformation);

	  public abstract AdditionalInformation loadAdditionalInformation(Long additionalInformationId);

	  public abstract List<AdditionalInformation> loadAllAdditionalInformation(Long[] additionalInformationIds);

	  public abstract List<AdditionalInformation> loadAllAdditionalInformation();

	  public abstract void deleteAdditionalInformation(AdditionalInformation additionalInformation);

	  public abstract void deleteAllAdditionalInformation(Collection<AdditionalInformation> additionalInformations);

	  public abstract List<AdditionalInformation> loadByKey(String key, Object value)
	    throws DaoException;

	  public abstract Long getLatestLogo();

	  public abstract List<AdditionalInformation> loadAdditionalInformationKeyProperty(Long paramLong);

	  public abstract List<AdditionalInformation> loadByKeyArray(String[] keyNames, Object[] keyValues)
	    throws DaoException;

    
}

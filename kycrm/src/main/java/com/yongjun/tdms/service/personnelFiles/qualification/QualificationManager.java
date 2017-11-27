package com.yongjun.tdms.service.personnelFiles.qualification;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.personnelFiles.qualification.Qualification;

public abstract interface QualificationManager {

	public abstract void storeQualification(Qualification paramQualification);

	public abstract void deleteQualification(Qualification paramQualification);

	public abstract void deleteAllQualification(Collection<Qualification> paramCollection);

	public abstract List<Qualification> loadAllQualification(Long[] paramArrayOfLong);

	public abstract Qualification loadQualification(Long paramLong);

	public abstract List<Qualification> loadAllQualification();

	public abstract List<Qualification> loadByKey(String paramString, Object paramObject) throws DaoException;

	public abstract List<Qualification> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
			throws DaoException;

}

package com.yongjun.tdms.service.base.document;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.base.document.ApplicationDoc;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public abstract interface ApplicationDocManager {
	@Transactional
	public abstract ApplicationDoc loadApplicationDoc(Long paramLong);

	public abstract List<ApplicationDoc> loadAllApplicationDocs(Long[] paramArrayOfLong);

	public abstract List<ApplicationDoc> loadAllApplicationDocs();

	@Transactional
	public abstract void storeApplicationDoc(ApplicationDoc paramApplicationDoc);

	@Transactional
	public abstract void deleteApplicationDoc(ApplicationDoc paramApplicationDoc);

	@Transactional
	public abstract void deleteAllApplicationDocs(Collection<ApplicationDoc> paramCollection);

	public abstract Integer getNumberOfManualDoc();

	public abstract boolean isMostNumberForTheManualDoc();

	public abstract Integer getMostUploadNumberForManualDoc();

	public abstract List<ApplicationDoc> getAllManualDoc();

	public abstract List<ApplicationDoc> loadByKey(String paramString, Object paramObject) throws DaoException;

	public abstract List<ApplicationDoc> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
			throws DaoException;
}

/*
 * Location: E:\crm2010\110\crm2009\WEB-INF\classes\ Qualified Name:
 * com.yongjun.tdms.service.base.document.ApplicationDocManager JD-Core Version:
 * 0.6.2
 */
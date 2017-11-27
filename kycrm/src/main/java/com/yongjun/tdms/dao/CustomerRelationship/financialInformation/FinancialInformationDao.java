package com.yongjun.tdms.dao.CustomerRelationship.financialInformation;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.CustomerRelationship.financialInformation.FinancialInformation;

import java.util.List;

public abstract interface FinancialInformationDao
{
  public abstract void storeFinancialInformation(FinancialInformation paramFinancialInformation);

  public abstract FinancialInformation loadFinancialInformation(Long paramLong);

  public abstract List<FinancialInformation> loadAllFinancialInformation();

  public abstract List<FinancialInformation> loadAllFinancialInformation(Long[] paramArrayOfLong);

  public abstract void deleteFinancialInformation(FinancialInformation paramFinancialInformation);

  public abstract void deleteAllFinancialInformation(List<FinancialInformation> paramList);

  public abstract List<FinancialInformation> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<FinancialInformation> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;
  
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.CustomerRelationship.contactArchives.ContactArchivesDao
 * JD-Core Version:    0.6.2
 */
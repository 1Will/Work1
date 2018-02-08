package com.yongjun.tdms.service.COM.VOC;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.COM.VOC.Voc;
import java.util.Collection;
import java.util.List;

public abstract interface VocManager
{
  public abstract void storeVoc(Voc paramVoc);

  public abstract void deleteVoc(Voc paramVoc);

  public abstract void deleteAllVoc(Collection<Voc> paramCollection);

  public abstract List<Voc> loadAllVoc(Long[] paramArrayOfLong);

  public abstract Voc loadVoc(Long paramLong);

  public abstract List<Voc> loadAllVoc();

  public abstract List<Voc> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<Voc> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract void disabledAllVocs(List<Voc> paramList);

  public abstract void enabledAllVoc(List<Voc> paramList);

  public abstract String getMaxPFCode(String paramString);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.COM.VOC.VocManager
 * JD-Core Version:    0.6.2
 */
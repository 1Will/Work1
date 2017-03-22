package com.yongjun.tdms.service.supplier;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.supplier.Supplier;
import java.util.Collection;
import java.util.List;

public abstract interface SupplierManager
{
  public abstract void storeSupplier(Supplier paramSupplier);

  public abstract Supplier loadSupplier(Long paramLong);

  public abstract void deleteSupplier(Supplier paramSupplier);

  public abstract void deleteAllSuppliers(Collection<Supplier> paramCollection);

  public abstract List<Supplier> loadAllSupplier(Long[] paramArrayOfLong);

  public abstract List<Supplier> loadAllSupplier();

  public abstract boolean disabledAllSupplier(Collection<Supplier> paramCollection);

  public abstract void enabledAllSupplier(Collection<Supplier> paramCollection);

  public abstract List<Supplier> loadByKey(String paramString, Object paramObject)
    throws DaoException;
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.supplier.SupplierManager
 * JD-Core Version:    0.6.2
 */
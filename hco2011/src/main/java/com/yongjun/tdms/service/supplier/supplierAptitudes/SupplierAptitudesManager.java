package com.yongjun.tdms.service.supplier.supplierAptitudes;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.supplier.supplierAptitudes.SupplierAptitudes;
import java.util.List;

public abstract interface SupplierAptitudesManager
{
  public abstract void storeSupplierAptitudes(SupplierAptitudes paramSupplierAptitudes);

  public abstract SupplierAptitudes loadSupplierAptitudes(Long paramLong);

  public abstract List<SupplierAptitudes> loadAllSupplierAptitudes();

  public abstract List<SupplierAptitudes> loadAllSupplierAptitudes(Long[] paramArrayOfLong);

  public abstract void deleteSupplierAptitudes(SupplierAptitudes paramSupplierAptitudes);

  public abstract void deleteAllSupplierAptitudes(List<SupplierAptitudes> paramList);

  public abstract List<SupplierAptitudes> loadByKey(String paramString, Object paramObject)
    throws DaoException;
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.supplier.supplierAptitudes.SupplierAptitudesManager
 * JD-Core Version:    0.6.2
 */
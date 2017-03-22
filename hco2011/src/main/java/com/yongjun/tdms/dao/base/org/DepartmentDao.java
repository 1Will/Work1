package com.yongjun.tdms.dao.base.org;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.security.Department;
import java.util.Collection;
import java.util.List;

public abstract interface DepartmentDao
{
  public abstract void storeDepartment(Department paramDepartment);

  public abstract void deleteDepartment(Department paramDepartment);

  public abstract void deleteAllDepartments(Collection<Department> paramCollection);

  public abstract List<Department> loadAllDepartment(Long[] paramArrayOfLong);

  public abstract Department loadDepartment(Long paramLong);

  public abstract List<Department> loadAllDepartments();

  public abstract List<Integer> getDeptStepAfterGroupingByStep();

  public abstract List<Department> getDeptsByStep(int paramInt);

  public abstract List<Department> getDeptsByParentDept(Long paramLong);

  public abstract List displaySortUser(Long paramLong);

  public abstract List<Department> InstitutionSelectDept(Long paramLong, boolean paramBoolean);

  public abstract List<Department> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract int getDeptSteps();

  public abstract List<Department> getDeptsByUserId(Long paramLong, String paramString)
    throws Exception;

  public abstract List<Department> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.base.org.DepartmentDao
 * JD-Core Version:    0.6.2
 */
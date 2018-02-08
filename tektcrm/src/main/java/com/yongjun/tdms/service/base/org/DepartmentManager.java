package com.yongjun.tdms.service.base.org;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.security.Department;
import java.util.Collection;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public abstract interface DepartmentManager
{
  public abstract void storeDepartment(Department paramDepartment);

  public abstract void storeDepartment(Department paramDepartment, boolean paramBoolean);

  public abstract void deleteDepartment(Department paramDepartment);

  public abstract void deleteAllDepartments(Collection<Department> paramCollection);

  public abstract List<Department> loadAllDepartments(Long[] paramArrayOfLong);

  public abstract Department loadDepartment(Long paramLong);

  public abstract List<Department> loadAllDepartments();

  public abstract List<Department> createSelectDepartments(String paramString);

  public abstract List createSelectParentGroups(String paramString);

  public abstract int getDeptDepth(Department paramDepartment);

  public abstract int getDeptChildNum(Department paramDepartment);

  public abstract List<Department> getDeptsOfAfterTraversal(Department paramDepartment);

  public abstract List<Integer> getDeptStepAfterGroupingByStep();

  public abstract List<Department> getDeptsByStep(int paramInt);

  public abstract List<Department> getDeptsByParentDept(Long paramLong);

  public abstract List<Department> getAllChilds(Department paramDepartment);

  public abstract void updateStepOfChildDepts(int paramInt, List<Department> paramList);

  public abstract List getParentChildRelationDept();

  @Transactional
  public abstract void joinUsersForDepartment(String[] paramArrayOfString, Department paramDepartment);

  public abstract List displaySortUser(Long paramLong);

  public abstract void disabledAllDepartment(Collection<Department> paramCollection);

  public abstract void enabledAllDepartment(Collection<Department> paramCollection);

  public abstract List<Department> InstitutionSelectDept(String paramString, boolean paramBoolean);

  public abstract List<Department> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<Department> getDepartments(boolean paramBoolean, String paramString, Long paramLong);

  public abstract List<Department> getDepartments();

  public abstract int getDeptSteps();

  public abstract List<Department> getDeptsByUserId(Long paramLong, String paramString)
    throws Exception;

  public abstract List<Department> getDeptsByInstIdInUser(Long paramLong, String paramString)
    throws Exception;

  public abstract List<Department> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.base.org.DepartmentManager
 * JD-Core Version:    0.6.2
 */
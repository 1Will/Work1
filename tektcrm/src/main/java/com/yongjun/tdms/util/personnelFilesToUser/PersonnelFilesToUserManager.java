package com.yongjun.tdms.util.personnelFilesToUser;

import java.util.Set;

import com.yongjun.pluto.model.security.User;


public abstract interface PersonnelFilesToUserManager
{
/**
 * 根据项目id查询出项目组成员，项目组成员是人事档案表中数据，再查询出对应用户表 中的user
 * @param projectInfoId
 * @param user 项目创建者
 * @return
 */
  public abstract String  loadUserIdToStrByProjectInfoId(Long projectInfoId,User user);
  /**
   * 查询出人事档案中所有非离职和非失效的人员，再查询出对象的用户表中的user
   * @return
   */
  public abstract String  loadUserIdToStrByEnable();
  
  /**
   * 查询出人事档案中所有非离职和非失效的人员，再查询出对象的用户表中的user
   * @code 军品或者民品的code
   * @return
   */
  public String loadUserIdToStrByType(String code);
  
  /**
   * 
   * @param uSet 传入的Users，Set类型
   * @param typeCode BusinessType的code
   * @return 返回新的Users，Set类型
   */
  public Set<User> getTypeUser(Set<User> uSet, String typeCode);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.util.personnelFilesToUser.PersonnelFilesToUserManager
 * JD-Core Version:    0.6.2
 */
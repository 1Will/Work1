package com.yongjun.tdms.dao.personnelFiles.relation;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.personnelFiles.SocialRelations;
import java.util.Collection;
import java.util.List;

public abstract interface SocialRelationsDao
{
  public abstract void storeSocialRelations(SocialRelations paramSocialRelations);

  public abstract void deleteSocialRelations(SocialRelations paramSocialRelations);

  public abstract void deleteAllSocialRelations(Collection<SocialRelations> paramCollection);

  public abstract List<SocialRelations> loadAllSocialRelations(Long[] paramArrayOfLong);

  public abstract SocialRelations loadSocialRelations(Long paramLong);

  public abstract List<SocialRelations> loadAllSocialRelations();

  public abstract List<SocialRelations> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<SocialRelations> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.personnelFiles.relation.SocialRelationsDao
 * JD-Core Version:    0.6.2
 */
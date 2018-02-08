package com.yongjun.tdms.dao.marketmanager.activity.survey;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.marketmanager.activity.survey.Survey;
import java.util.Collection;
import java.util.List;

public abstract interface SurveyDao
{
  public abstract void storeSurvey(Survey paramSurvey);

  public abstract void deleteSurvey(Survey paramSurvey);

  public abstract void deleteAllSurvey(Collection<Survey> paramCollection);

  public abstract List<Survey> loadAllSurvey(Long[] paramArrayOfLong);

  public abstract Survey loadSurvey(Long paramLong);

  public abstract List<Survey> loadAllSurvey();

  public abstract List<Survey> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<Survey> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract String getMaxPFCode(String paramString, Long paramLong);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.marketmanager.activity.survey.SurveyDao
 * JD-Core Version:    0.6.2
 */
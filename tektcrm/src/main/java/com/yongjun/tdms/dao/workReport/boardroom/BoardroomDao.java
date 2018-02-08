package com.yongjun.tdms.dao.workReport.boardroom;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.workReport.boardroom.Boardroom;

public abstract interface BoardroomDao
{
  public abstract void storeBoardroom(Boardroom paramBoardroom);

  public abstract void deleteBoardroom(Boardroom paramBoardroom);

  public abstract void deleteAllBoardroom(Collection<Boardroom> paramCollection);

  public abstract List<Boardroom> loadAllBoardroom(Long[] paramArrayOfLong);

  public abstract Boardroom loadBoardroom(Long paramLong);

  public abstract List<Boardroom> loadAllBoardroom();

  public abstract List<Boardroom> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<Boardroom> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;
}

package com.yongjun.tdms.dao.CustomerRelationship.participant;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.CustomerRelationship.participant.Participant;

public interface ParticipantDao {
	public abstract void storeParticipant(Participant paramParticipant);

	  public abstract Participant loadParticipant(Long paramLong);

	  public abstract List<Participant> loadAllParticipant();

	  public abstract List<Participant> loadAllParticipant(Long[] paramArrayOfLong);

	  public abstract void deleteParticipant(Participant paramParticipant);

	  public abstract void deleteAllParticipant(List<Participant> paramList);

	  public abstract List<Participant> loadByKey(String paramString, Object paramObject)
	    throws DaoException;

	  public abstract List<Participant> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
	    throws DaoException;
}

package com.yongjun.tdms.dao.CustomerRelationship.participant.hibernate;

import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.CustomerRelationship.participant.ParticipantDao;
import com.yongjun.tdms.model.CustomerRelationship.participant.Participant;

public class HibernateParticipant extends BaseHibernateDao implements ParticipantDao{
	public void storeParticipant(Participant ca) {
		super.store(ca);
	}

	public Participant loadParticipant(Long caId) {
		return (Participant) super.load(Participant.class, caId);
	}

	public List<Participant> loadAllParticipant() {
		return super.loadAll(Participant.class);
	}

	public List<Participant> loadAllParticipant(Long[] caIds) {
		return super.loadAll(Participant.class, caIds);
	}

	public void deleteParticipant(Participant ca) {
		super.delete(ca);
	}

	public void deleteAllParticipant(List<Participant> caIds) {
		super.deleteAll(caIds);
	}

	public List<Participant> loadByKey(String key, Object value) throws DaoException {
		return super.loadByKey(Participant.class, key, value);
	}

	public List<Participant> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return loadByKeyArray(Participant.class, keyNames, keyValues);
	}
}

package com.yongjun.tdms.service.CustomerRelationship.participant.pojo;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.CustomerRelationship.participant.ParticipantDao;
import com.yongjun.tdms.model.CustomerRelationship.participant.Participant;
import com.yongjun.tdms.service.CustomerRelationship.participant.ParticipantManager;

public class DefaultParticipantManager implements ParticipantManager{
	public final ParticipantDao ParticipantDao;

	public DefaultParticipantManager(ParticipantDao ParticipantDao) {
		this.ParticipantDao = ParticipantDao;
	}

	public void storeParticipant(Participant pa) {
		this.ParticipantDao.storeParticipant(pa);
	}

	public Participant loadParticipant(Long id) {
		return this.ParticipantDao.loadParticipant(id);
	}

	public List<Participant> loadAllParticipant() {
		return this.ParticipantDao.loadAllParticipant();
	}

	public List<Participant> loadAllParticipant(Long[] ids) {
		return this.ParticipantDao.loadAllParticipant(ids);
	}

	public void deleteParticipant(Participant pa) {
		this.ParticipantDao.deleteParticipant(pa);
	}

	public void deleteAllParticipant(List<Participant> ids) {
		this.ParticipantDao.deleteAllParticipant(ids);
	}

	public List<Participant> loadByKey(String key, Object value) throws DaoException {
		return this.ParticipantDao.loadByKey(key, value);
	}

	public void disabledAllParticipant(List<Participant> pas) {
		for (Participant participant : pas) {
			participant.setDisabled(true);
			this.ParticipantDao.storeParticipant(participant);
		}
	}

	public void enabledAllParticipant(List<Participant> pas) {
		for (Participant archives : pas) {
			archives.setDisabled(false);
			this.ParticipantDao.storeParticipant(archives);
		}
	}

	public List<Participant> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return this.ParticipantDao.loadByKeyArray(keyNames, keyValues);
	}
}

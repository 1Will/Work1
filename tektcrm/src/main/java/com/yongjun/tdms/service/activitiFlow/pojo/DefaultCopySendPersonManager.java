package com.yongjun.tdms.service.activitiFlow.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.activitiFlow.CopySendPersonDao;
import com.yongjun.tdms.model.activitiFlow.CopySendPerson;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.workflow.Flow;
import com.yongjun.tdms.service.activitiFlow.CopySendPersonManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.workflow.flow.FlowManager;

public class DefaultCopySendPersonManager extends BaseManager implements CopySendPersonManager {
	private CopySendPersonDao copySendPersonDao;
	private PersonnelFilesManager personnelFilesManager;
	private FlowManager flowManager;

	public DefaultCopySendPersonManager(CopySendPersonDao copySendPersonDao,PersonnelFilesManager personnelFilesManager,FlowManager flowManager) {
		this.copySendPersonDao = copySendPersonDao;
		this.personnelFilesManager = personnelFilesManager;
		this.flowManager = flowManager;
	}

	public void setCopySendPersonDao(CopySendPersonDao CopySendPersonDao) {
		this.copySendPersonDao = CopySendPersonDao;
	}

	public CopySendPersonDao geCopySendPersonDao() {
		return this.copySendPersonDao;
	}

	public void deleteCopySendPerson(CopySendPerson point) {
		this.copySendPersonDao.deleteCopySendPerson(point);
	}

	public List<CopySendPerson> loadAllCopySendPersons() {
		return this.copySendPersonDao.loadAllCopySendPersons();
	}

	public List<CopySendPerson> loadByKey(String keyName, Object keyValue) throws DaoException {
		return this.copySendPersonDao.loadByKey(keyName, keyValue);
	}

	public List<CopySendPerson> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return this.copySendPersonDao.loadByKeyArray(keyNames, keyValues);
	}

	public String disabled(List<CopySendPerson> pointList) {
		try {
			for (CopySendPerson point : pointList) {
				point.setDisabled(true);
				this.copySendPersonDao.storeCopySendPerson(point);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}

	public String enabled(List<CopySendPerson> pointList) {
		try {
			for (CopySendPerson point : pointList) {
				point.setDisabled(false);
				this.copySendPersonDao.storeCopySendPerson(point);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}

	public void storeCopySendPerson(CopySendPerson paramPoint) {
		// TODO Auto-generated method stub
		this.copySendPersonDao.storeCopySendPerson(paramPoint);

	}

	public void deleteAllCopySendPersons(Collection<CopySendPerson> paramCollection) {
		for (CopySendPerson point : paramCollection) {
			this.copySendPersonDao.deleteCopySendPerson(point);//

		}

	}

	public List<CopySendPerson> loadAllCopySendPersons(Long[] paramArrayOfLong) {
		// TODO Auto-generated method stub
		return this.copySendPersonDao.loadAllCopySendPersons(paramArrayOfLong);
	}

	public CopySendPerson loadCopySendPerson(Long paramLong) {
		// TODO Auto-generated method stub
		return this.copySendPersonDao.loadCopySendPerson(paramLong);
	}

	public void saveCopySendPerson(String idString,String flowId,String bussnissId) {
		if(idString != null && !"".equals(idString)){
			String[] id_arr = idString.split("\'");
			for (String id_this : id_arr) {
				CopySendPerson copySendPerson = new CopySendPerson();
				PersonnelFiles personnelFile = personnelFilesManager.loadPersonnel(Long.parseLong(id_this));
				Flow flow = flowManager.loadFlow(Long.parseLong(flowId));
				
				copySendPerson.setBussnessId(Long.parseLong(bussnissId));
				copySendPerson.setPerson(personnelFile);
				copySendPerson.setFlow(flow);
				
				this.storeCopySendPerson(copySendPerson);
			}
			
		}
	}
}

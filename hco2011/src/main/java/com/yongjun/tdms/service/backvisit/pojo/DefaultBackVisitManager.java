package com.yongjun.tdms.service.backvisit.pojo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.tdms.dao.CustomerRelationship.stepInfo.StepInfoDao;
import com.yongjun.tdms.dao.backvisit.BackVisitDao;
import com.yongjun.tdms.model.CustomerRelationship.stepInfo.StepInfo;
import com.yongjun.tdms.model.backvisit.BackVisit;
import com.yongjun.tdms.service.backvisit.BackVisitManager;

public class DefaultBackVisitManager extends BaseManager implements BackVisitManager {
	private BackVisitDao backVisitDao;
	private StepInfoDao stepInfoDao;
	private UserManager userManager;

	public void deleteAllBackVisit(Collection<BackVisit> backVisits) {
		this.backVisitDao.deleteAllBackVisit(backVisits);
	}

	public void deleteBackVisit(BackVisit backVisit) {
		this.backVisitDao.deleteBackVisit(backVisit);
	}

	public List<BackVisit> loadAllBackVisit(Long[] backVisitIds) {
		return this.backVisitDao.loadAllBackVisit(backVisitIds);
	}

	public List<BackVisit> loadAllBackVisits() {
		return this.backVisitDao.loadAllBackVisits();
	}

	public BackVisit loadBackVisit(Long backVisitId) {
		return this.backVisitDao.loadBackVisit(backVisitId);
	}

	public void storeBackVisit(BackVisit backVisit) {
		this.backVisitDao.storeBackVisit(backVisit);
	}

	public void setBackVisitDao(BackVisitDao backVisitDao) {
		this.backVisitDao = backVisitDao;
	}

	public void disabledBackVisits(List<BackVisit> backVisits) {
		for (BackVisit bv : backVisits) {
			bv.setDisabled(true);
			this.backVisitDao.storeBackVisit(bv);
		}
	}

	public void enableBackVisits(List<BackVisit> backVisits) {
		for (BackVisit bv : backVisits) {
			bv.setDisabled(false);
			this.backVisitDao.storeBackVisit(bv);
		}
	}

	public List<BackVisit> loadByKey(String keyName, Object keyValue) throws DaoException {
		return this.backVisitDao.loadByKey(keyName, keyValue);
	}

	public List<BackVisit> loadBackVisitByPj(String hqlWord){
		return this.backVisitDao.loadBackVisitByPj(hqlWord);
	}
	public List<BackVisit> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return this.backVisitDao.loadByKeyArray(keyNames, keyValues);
	}

	public void storeStepInfo(StepInfo stepInfo) {
		this.stepInfoDao.storeStepInfo(stepInfo);
	}

	public StepInfoDao getStepInfoDao() {
		return this.stepInfoDao;
	}

	public void setStepInfoDao(StepInfoDao stepInfoDao) {
		this.stepInfoDao = stepInfoDao;
	}

	public UserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public List<BackVisit> loadBackVisitByDate(String userId, String da) throws DaoException, ParseException {
		java.util.Date newDate = new SimpleDateFormat("yyyy-MM-dd").parse(da);
		String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(newDate);
		User user = userManager.loadUser(Long.parseLong(userId));
		String keyNames[] = { "backVisitDate", "employee.code" };
		Object[] keyValues = { date, user.getCode() };
		List<BackVisit> list = this.backVisitDao.loadByKeyArray(keyNames, keyValues);
		List<BackVisit> returnList = new ArrayList<BackVisit>();
		for (BackVisit visit : list) {
			BackVisit temp = new BackVisit();
			temp.setId(visit.getId());// id
			temp.setBackVisitContent(visit.getBackVisitContent());// 回访内容
			temp.setCaName(visit.getCaName());// 拜访人
			temp.setProjectName(visit.getProjectName());// 项目名称
			temp.setCustomerName(visit.getCustomerName());// 客户名称
			returnList.add(temp);
		}
		return returnList;
	}
}

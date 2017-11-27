package com.yongjun.tdms.service.workReport.meeting.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.workReport.meeting.MeetingMonthDao;
import com.yongjun.tdms.model.workReport.meeting.MeetingMonth;
import com.yongjun.tdms.service.workReport.meeting.MeetingMonthManager;

public class DefaultMeetingMonthManager extends BaseManager implements MeetingMonthManager{
	private final MeetingMonthDao meetingMonthDao;
	public DefaultMeetingMonthManager(MeetingMonthDao meetingMonthDao) {
		this.meetingMonthDao = meetingMonthDao;
	}

	public void storeMeetingMonth(MeetingMonth meetingMonth) {
		meetingMonthDao.storeMeetingMonth(meetingMonth);
	}

	public List<MeetingMonth> loadAllMeetingMonth(Long[] meetingMonthIds) {
		return meetingMonthDao.loadAllMeetingMonth(meetingMonthIds);
	}

	public void deleteMeetingMonth(MeetingMonth meetingMonth) {
		meetingMonthDao.deleteMeetingMonth(meetingMonth);
	}

	public void deleteAllMeetingMonth(Collection<MeetingMonth> meetingMonths) {
		meetingMonthDao.deleteAllMeetingMonth(meetingMonths);
	}

	public MeetingMonth loadMeetingMonth(Long meetingMonthId) {
		return meetingMonthDao.loadMeetingMonth(meetingMonthId);
	}

	public List<MeetingMonth> loadAllMeetingMonth() {
		return meetingMonthDao.loadAllMeetingMonth();
	}

	public List<MeetingMonth> loadByKey(String keyName, Object keyValue) throws DaoException {
		return meetingMonthDao.loadByKey(keyName, keyValue);
	}

	public List<MeetingMonth> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return meetingMonthDao.loadByKeyArray(keyNames, keyValues);
	}

	public long loadMaxId() {
		return meetingMonthDao.loadMaxId();
	}
	public boolean loadByDate(String date) {
		try {
			List<MeetingMonth> list=null;
			if(date.equals("")){
				return false;
			}
			System.out.println(date);
 			list =meetingMonthDao.loadByKey("date", date);
			if(list!=null){
			   return true;
			}
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return false;
	}
}

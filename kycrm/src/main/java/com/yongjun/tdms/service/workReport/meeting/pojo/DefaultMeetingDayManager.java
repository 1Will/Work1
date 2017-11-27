package com.yongjun.tdms.service.workReport.meeting.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.workReport.meeting.MeetingDayDao;
import com.yongjun.tdms.model.workReport.meeting.MeetingDay;
import com.yongjun.tdms.service.workReport.meeting.MeetingDayManager;

public class DefaultMeetingDayManager extends BaseManager implements MeetingDayManager{
    private final MeetingDayDao meetingDayDao;
	public DefaultMeetingDayManager(MeetingDayDao meetingDayDao) {
		super();
		this.meetingDayDao = meetingDayDao;
	}

	public void storeMeetingDay(MeetingDay meetingDay) {
		meetingDayDao.storeMeetingDay(meetingDay);
		
	}

	public void saveAllMeetingDay(List<MeetingDay> meetingDays) {
		for(int i=0;i<meetingDays.size();i++){
			MeetingDay meetingDay =meetingDays.get(i);
			meetingDayDao.storeMeetingDay(meetingDay);
       }
	}

	public List<MeetingDay> loadAllMeetingDay(Long[] meetingDayIds) {
		return meetingDayDao.loadAllMeetingDay(meetingDayIds);
	}

	public void deleteMeetingDay(MeetingDay meetingDay) {
		meetingDayDao.deleteMeetingDay(meetingDay);
	}

	public void deleteAllMeetingDay(Collection<MeetingDay> meetingDays) {
		meetingDayDao.deleteAllMeetingDay(meetingDays);
	}

	public MeetingDay loadMeetingDay(Long meetingDayId) {
		return meetingDayDao.loadMeetingDay(meetingDayId);
	}

	public List<MeetingDay> loadAllMeetingDay() {
		return meetingDayDao.loadAllMeetingDay();
	}

	public List<MeetingDay> loadByKey(String keyName, Object keyValue) throws DaoException {
		return meetingDayDao.loadByKey(keyName, keyValue);
	}

	public List<MeetingDay> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return meetingDayDao.loadByKeyArray(keyNames, keyValues);
	}

	public long loadMaxId() {
		return meetingDayDao.loadMaxId();
	}

}

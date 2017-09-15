package com.yongjun.tdms.service.workReport.meeting;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.workReport.meeting.MeetingDay;

public abstract interface MeetingDayManager {
	public abstract void storeMeetingDay(MeetingDay meetingDay);
	
	public abstract void saveAllMeetingDay(List<MeetingDay> meetingDays);
	
    public abstract List<MeetingDay> loadAllMeetingDay(Long[] meetingDayIds);
	
	public abstract void deleteMeetingDay(MeetingDay meetingDay);

	public abstract void deleteAllMeetingDay(Collection<MeetingDay> meetingDays);
	
	public abstract MeetingDay loadMeetingDay(Long meetingDayId);

	public abstract List<MeetingDay> loadAllMeetingDay();

	public abstract List<MeetingDay> loadByKey(String keyName, Object keyValue) throws DaoException;

	public abstract List<MeetingDay> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException;
	
	public abstract long loadMaxId();
}

package com.yongjun.tdms.service.workReport.meeting;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.workReport.meeting.MeetingMonth;

public abstract interface MeetingMonthManager {
    public abstract void storeMeetingMonth(MeetingMonth meetingMonth);
	
	public abstract List<MeetingMonth> loadAllMeetingMonth(Long[] meetingMonthIds);
	
	public abstract void deleteMeetingMonth(MeetingMonth meetingMonth);

	public abstract void deleteAllMeetingMonth(Collection<MeetingMonth> meetingMonths);
	
	public abstract MeetingMonth loadMeetingMonth(Long meetingMonthId);

	public abstract List<MeetingMonth> loadAllMeetingMonth();

	public abstract List<MeetingMonth> loadByKey(String keyName, Object keyValue) throws DaoException;

	public abstract List<MeetingMonth> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException;
	
	public abstract long loadMaxId();
	public boolean loadByDate(String date);
}

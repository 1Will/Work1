package com.yongjun.tdms.dao.workReport.meeting.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.workReport.meeting.MeetingDayDao;
import com.yongjun.tdms.model.workReport.meeting.MeetingDay;

public class HibernateMeetingDay extends BaseHibernateDao implements MeetingDayDao{
	public void storeMeetingDay(MeetingDay meetingDay) {
		super.store(meetingDay);
		
	}

	public List<MeetingDay> loadAllMeetingDay(Long[] meetingDayIds) {
		
		return super.loadAll(MeetingDay.class, meetingDayIds);
	}

	public MeetingDay loadMeetingDay(Long meetingDayId) {
		return (MeetingDay)super.load(MeetingDay.class, meetingDayId);
	}

	public List<MeetingDay> loadAllMeetingDay() {
		
		return super.loadAll(MeetingDay.class);
	}

	public List<MeetingDay> loadByKey(String keyName, Object keyValue) throws DaoException {
		
		return super.loadByKey(MeetingDay.class, keyName, keyValue);
	}

	public List<MeetingDay> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		
		return super.loadByKeyArray(MeetingDay.class, keyNames, keyValues);
	}

	public void deleteMeetingDay(MeetingDay meetingDay) {
		super.delete(meetingDay);
		
	}

	public void deleteAllMeetingDay(Collection<MeetingDay> meetingDays) {
		super.deleteAll(meetingDays);
	}
	public long loadMaxId() {
		String hql="select Isnull(max(m.id),0) from MeetingDay m ";
		return (Long) getHibernateTemplate().find(hql).get(0);
	}

}

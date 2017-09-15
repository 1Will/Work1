package com.yongjun.tdms.dao.workReport.meeting.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.workReport.meeting.MeetingMonthDao;
import com.yongjun.tdms.model.workReport.meeting.MeetingMonth;

public class HibernateMeetingMonth extends BaseHibernateDao implements MeetingMonthDao{

   public void storeMeetingMonth(MeetingMonth meetingMonth) {
		super.store(meetingMonth);
		
	}

	public List<MeetingMonth> loadAllMeetingMonth(Long[] meetingMonthIds) {
	     return super.loadAll(MeetingMonth.class, meetingMonthIds);
	}

	public void deleteMeetingMonth(MeetingMonth meetingMonth) {
		super.delete(meetingMonth);
		
	}

	public void deleteAllMeetingMonth(Collection<MeetingMonth> meetingMonths) {
		super.deleteAll(meetingMonths);
		
	}

	public MeetingMonth loadMeetingMonth(Long meetingMonthId) {
		return (MeetingMonth)super.load(MeetingMonth.class, meetingMonthId);
	}

	public List<MeetingMonth> loadAllMeetingMonth() {
		return super.loadAll(MeetingMonth.class);
	}

	public List<MeetingMonth> loadByKey(String keyName, Object keyValue) throws DaoException {
		return super.loadByKey(MeetingMonth.class, keyName, keyValue);
	}

	public List<MeetingMonth> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return super.loadByKeyArray(MeetingMonth.class, keyNames, keyValues);
	}


	public long loadMaxId() {
		String hql="select Isnull(max(mm.id),0) from MeetingMonth mm";
		return (Long) getHibernateTemplate().find(hql).get(0);
	}

}

package com.yongjun.tdms.model.workReport.meeting;

import java.util.Date;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;

public class MeetingDay extends BaseInfoEntity implements CreatedTimeTracking, CreatorTracking, LastOperatorTracking{
  private static final long serialVersionUID = 1L;
  private PersonnelFiles hostperson;
  private Date date;
  private String week;
  private MeetingMonth meetingMonth;
public MeetingDay() {
}
public MeetingDay(PersonnelFiles hostperson, Date date, String week, MeetingMonth meetingMonth) {
	super();
	this.date = date;
	this.week = week;
	this.hostperson = hostperson;
	this.meetingMonth = meetingMonth;
}
public PersonnelFiles getHostperson() {
	return hostperson;
}
public void setHostperson(PersonnelFiles hostperson) {
	this.hostperson = hostperson;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
public String getWeek() {
	return week;
}
public void setWeek(String week) {
	this.week = week;
}
public MeetingMonth getMeetingMonth() {
	return meetingMonth;
}
public void setMeetingMonth(MeetingMonth meetingMonth) {
	this.meetingMonth = meetingMonth;
}
@Override
public boolean equals(Object arg0) {
	return false;
}
@Override
public int hashCode() {
	return 0;
}
@Override
public String toString() {
	return "MeetingDay [hostperson=" + hostperson + ", date=" + date + ", week=" + week + ", meetingMonth="
			+ meetingMonth + "]";
}
  
}

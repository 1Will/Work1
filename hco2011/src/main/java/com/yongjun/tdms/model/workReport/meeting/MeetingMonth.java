package com.yongjun.tdms.model.workReport.meeting;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;

public class MeetingMonth extends BaseInfoEntity implements CreatedTimeTracking, CreatorTracking, LastOperatorTracking {
private static final long serialVersionUID = 1L;
   private String theme;
   private PersonnelFiles publisher;
   private String date;
   private String weekmeeting;
public String getWeekmeeting() {
	return weekmeeting;
}
public void setWeekmeeting(String weekmeeting) {
	this.weekmeeting = weekmeeting;
}
public MeetingMonth() {
}
public MeetingMonth(String theme, PersonnelFiles publisher, String date) {
	super();
	this.theme = theme;
	this.date = date;
	this.publisher = publisher;
}
public String getTheme() {
	return theme;
}
public void setTheme(String theme) {
	this.theme = theme;
}
public PersonnelFiles getPublisher() {
	return publisher;
}
public void setPublisher(PersonnelFiles publisher) {
	this.publisher = publisher;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
@Override
public boolean equals(Object arg0) {
	return false;
}
@Override
public int hashCode() {
	return 0;
}
   
}

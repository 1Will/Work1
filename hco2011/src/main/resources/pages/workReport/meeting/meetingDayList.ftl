<#include "../../includes/hco2011.ftl" />
<@framePage title="${action.getText('meetingMorning')}">
<@ww.form name="'listForm'" action="'searchMeetingDay'" namespace="'/workReport'" method="'post'">
	
	 <@list title="${action.getText('meeting.list')}" 
	 includeParameters="meetingDay.id|personnelFiles.name|meetingDay.theme|meetingDay.week|meetingMonth.id|meetingDay.date_end|meetingDay.date_start"
	  fieldMap="like:personnelFiles.name|meetingDay.theme" >
	   
		<@vcolumn title="${action.getText('meeting.hostperson')}" property="hostperson.name" sortable="desc">
			<@vlh.attribute name="width" value="5%" />
			<@alignLeft/>
		</@vcolumn>
		
		<@vcolumn title="${action.getText('meeting.date')}" property="date" format="yyyy-MM-dd"  sortable="desc">
			<@vlh.attribute name="width" value="10%" />
			<@alignLeft/>
		</@vcolumn>
		
		<@vcolumn title="${action.getText('meeting.week')}"  property="week" sortable="desc"  >
			<@vlh.attribute name="width" value="6%" />
			<@alignLeft/>
		</@vcolumn>
		
		<@vcolumn title="${action.getText('meeting.theme')}"  property="meetingMonth.theme" sortable="desc"  >
			<@vlh.attribute name="width" value="16%" />
			<@alignLeft/>
		</@vcolumn>
	 </@list>
	 
</@ww.form>

</@framePage>
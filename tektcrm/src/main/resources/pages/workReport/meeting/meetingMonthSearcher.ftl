<#include "../../includes/hco2011.ftl" />
  <@inputTable>
  	<tr>
  	<@pp.datePicker 
		label="'${action.getText('meeting.month')}'" 
		name="'meetingMonth.date'" 
	   	value="'${req.getParameter('meetingMonth.date')?if_exists}'"
		cssClass="'underline'" 
		dateFormat="'%Y-%m'"
		maxlength="10"/>
		<@textfield label="${action.getText('meeting.publisher')}" name="personnelFiles.name" value="${req.getParameter('personnelFiles.name')?if_exists}" cssClass="underline"/>
  	</tr>
  	<tr>
		<@textfield label="${action.getText('meeting.theme')}" name="meetingMonth.theme" value="${req.getParameter('meetingMonth.theme')?if_exists}" cssClass="underline"/>
  	</tr>
  </@inputTable>

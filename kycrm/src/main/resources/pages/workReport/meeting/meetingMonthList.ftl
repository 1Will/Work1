<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('meetingMorning')}">
<@ww.form name="'listForm'" action="'searchMeetingMonth'" namespace="'/workReport'" method="'post'">
	 <@ww.token name="'searchMeetingMonthToken'"/>
	    <#include "meetingMonthSearcher.ftl" />
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	  <@buttonBar>
			<@vsubmit name="'search'" value="'${action.getText('search')}'" onclick="'checkInvalidParms()'" />
			<#if !(action.isReadOnly())>
				<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/workReport/editMeeting.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
       		</#if>	
      </@buttonBar>
      <#assign itemNo=1/>
	 <@list title="${action.getText('meeting.list')}" 
	 includeParameters="meetingMonth.id|personnelFiles.name|meetingMonth.theme||meetingMonth.date"
	  fieldMap="like:personnelFiles.name|meetingMonth.theme|meetingMonth.date" >
	    <#if !(action.isReadOnly())>
            <@vlh.checkbox property="id" name="meetingMonthIds">
                <@vlh.attribute name="width" value="5%" />
            </@vlh.checkbox>
        </#if>
        <@vcolumn title="${action.getText('list')}" property="id"  >
              <a href="editMeeting.html?meetingMonth.id=#{object.id?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}" >#{itemNo}</a>
        <@alignLeft attributes="width:5%;"/>
        </@vcolumn >
         <#assign itemNo=itemNo + 1/>
		<@vcolumn title="${action.getText('meeting.month')}" property="date"   sortable="desc" >
			<@vlh.attribute name="width" value="22%" />
			<@alignLeft/>
		</@vcolumn>
		
		<@vcolumn title="${action.getText('meeting.theme')}"  property="theme" sortable="desc"  >
		    <@vlh.attribute name="width" value="50%" />
			<@alignLeft/>
		</@vcolumn>
		
		<@vcolumn title="${action.getText('meeting.publisher')}" property="publisher.name" sortable="desc">
			<@vlh.attribute name="width" value="23%" />
			<@alignLeft/>
		</@vcolumn>
	 </@list>
	 
	 <@buttonBar>
	 <#assign confirmMessage = "${action.getText('delete.msg')}" />
	 <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
           <@ww.param name="'onclick'" value="'return confirmDeletes(\"meetingMonthIds\", \"${confirmMessage}\");'"/>   
     </@vsubmit>
     </@buttonBar>
     
</@ww.form>
<script language="javascript">
function checkInvalidParms() {
  	return true;
	}
	
</script>



</@htmlPage>
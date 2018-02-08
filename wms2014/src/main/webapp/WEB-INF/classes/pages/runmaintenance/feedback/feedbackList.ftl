<#include "../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('feedbackSearch.title')}">
	 <@ww.form namespace="'/runmaintenance/feedback'" name="'listFeedback'" action="'searchFeedbacks'" method="'post'">
	 <@ww.token name="searchFeedbacksToken"/>
	 	<#include "feedbackSearcher.ftl"/>
         <@buttonBar>
	 		<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
	 		<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/runmaintenance/feedback/editFeedback.html"/>  
        </@buttonBar>
        	<@list title="${action.getText('feedback.list')}" 
        		includeParameters="feedbackNo|feedbackName|deviceNo|deviceName|feedbaceDatetime_start|feedbaceDatetime_end|department.id" 
        		fieldMap="like:feedbackNo|feedName|deviceNo|deviceName,date:feedbaceDatetime_start|feedbaceDatetime_end" >
        		<@vlh.checkbox property="id" name="feedbackIds">
	            	<@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
	            <@vcolumn title="${action.getText('feedbackNo')}" property="feedbackNo" sortable="desc">
	                <a href="editFeedback.html?feedback.id=#{object.id}">${object.feedbackNo}</a>
	            </@vcolumn>
	            <@vcolumn title="${action.getText('feedbackName')}" property="feedbackName" sortable="desc"/>
	            <@vcolumn title="${action.getText('device.no')}" property="device.deviceNo"/>
	            <@vcolumn title="${action.getText('device.name')}" property="device.name"/>
	            <@vcolumn title="${action.getText('device.specification')}" property="device.specification"/>
        		<@vcolumn title="${action.getText('department')}" property="device.department.name"/>
        		<@vcolumn title="${action.getText('feedbackDatetime')}" property="feedbaceDatetime" format="yyyy-MM-dd"/>
        		<@vcolumn title="${action.getText('manager')}" property="manager.name"/>
        	</@list>
        <#if !first>
        <@buttonBar>
            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('feedback')}?" />
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                <@ww.param name="'onclick'" value="'return validateDelete(confirmDeletes(\"feedbackIds\", \"${confirmMessage}\"), checkInvalidParms())'"/>
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
        </@buttonBar>
        </#if>
   <script language="javascript">
      function validateDelete(delFun, checkFun) {
        if (delFun) {
        	checkFun;
         	return true;
         }
        return false;
     }
   </script>   
     </@ww.form>
</@htmlPage>
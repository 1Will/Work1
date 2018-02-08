<#--$Id: logSearch.ftl 6148 2008-11-05 01:41:34Z zsmuig $ -->
<#include "../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('userLogSearch.title')}">
	<@ww.form namespace="'/base/userLog'" name="'listUserLog'" action="'searchUserLog'" method="'post'">
	<@ww.token name="searchUserLogToken"/>
	<@inputTable>
		<tr>
			<@ww.textfield label="'${action.getText('userLog.loginName')}'" name="'loginName'" value="'${req.getParameter('loginName')?if_exists}'" cssClass="'underline'"/>
			<@ww.textfield label="'${action.getText('userLog.userName')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
		</tr>
		<tr>
	    <@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
		    		value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
		            list="departments" emptyOption="false" disabled="false">
		</@ww.select> 
		<@pp.dateRanger label="'${action.getText('userLog.loginTime')}'" name="'userLog.loginTime'" 
			value="'${req.getParameter('userLog.loginTime_start')?if_exists}|${req.getParameter('userLog.loginTime_end')?if_exists}'"
		   	cssClass="'underline'" maxlength="10"/>
		</tr>
	</@inputTable>
		<@buttonBar>
            <@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
        </@buttonBar>
		<@list title="${action.getText('userLog.list')}" includeParameters="loginName|name|department.id|userLog.loginTime_start|userLog.loginTime_end" 
			fieldMap="like:loginName|name,date:userLog.loginTime_start|userLog.loginTime_end" >
	        <@vcolumn title="${action.getText('userLog.loginName')}" property="user.loginName" sortable="desc">
	            <@alignLeft/>
	        </@vcolumn>
	        <@vcolumn title="${action.getText('userLog.userName')}" property="user.name" sortable="desc">
	            <@alignLeft/>
	        </@vcolumn>
	        <@vcolumn title="${action.getText('userLog.dept')}" property="user.department.name" sortable="desc">
	            <@alignLeft/>
	        </@vcolumn>
	        <@vcolumn title="${action.getText('userLog.loginTime')}" property="time" format="yyyy-MM-dd HH:mm:ss" sortable="desc">
				<@alignCenter/>
            </@vcolumn>
            <@vcolumn title="${action.getText('userLog.eventType')}" property="eventType.name" sortable="desc">
				<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('userLog.moreInfo')}" property="moreInfo">
				<@alignLeft/>
            </@vcolumn>
		</@list>
		<script language="javascript">
			var selector = document.all("department.id");
    		groups = selector.options.length;
    		for (i=0; i<groups; i++) {
    		<#if req.getParameter('department.id')?exists>
    		if (selector.options[i].value == "${req.getParameter('department.id')?if_exists}") {
      		selector.options[i].selected="true";
    		}
    		</#if>
    		}
			function checkInvalidParms() {
				strStartMsg="${action.getText('dateFormate.error')}";
				strEndMsg="${action.getText('planCreatedTime.order.error')}";
				if(!queryDate("userLog.loginTime_start","userLog.loginTime_end",
				    strStartMsg,strEndMsg)){
				    	return false;
				}
				if (getObjByNameRe("department.id").value == -1) {
	  				getObjByNameRe("department.id").value = '';
	  			}
				return true;
			}
	    </script>
	</@ww.form>
</@htmlPage>
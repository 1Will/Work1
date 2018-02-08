<#include "../../../includes/macros.ftl" />
<@htmlPage title="${action.getText('checkPiontPlan.title')}">
	 <@ww.form name="'listForm'" action="'searchCheckPointPlan'" method="'post'">
	 	<@ww.token name="searchPlansToken"/>
	 	<#include "checkPointPlanSearcher.ftl"/>
        <@buttonBar> 
	 		<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>     
	 		<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/runmaintenance/checkPoint/editCheckPointPlan.html"/>
        </@buttonBar>
         <@list title="${action.getText('checkPointPlan.list')}" 
         		includeParameters="id|planNo|deviceNo|deviceName|name|department.id|docState.id|scheduleTime_start|scheduleTime_end" 
         		fieldMap="like:id|planNo|deviceNo|deviceName|name|,date:scheduleTime_start|scheduleTime_end" >
            <@vlh.checkbox property="id" name="checkPointPlanIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('plan.no')}" property="planNo" sortable="desc">
                <a href="editCheckPointPlan.html?plan.id=#{object.id}&plan.docStatus=#{object.docStatus}">${object.planNo}</a>
            </@vcolumn>
            <@vcolumn title="${action.getText('plan.name')}" property="name" sortable="desc"/>
            <@vcolumn title="${action.getText('device.no')}" property="device.deviceNo" />
            <@vcolumn title="${action.getText('device.name')}" property="device.name" />
            <@vcolumn title="${action.getText('plan.scheduleTime')}" property="scheduleTime" format="yyyy-MM-dd" />
            <@vcolumn title="${action.getText('plan.request')}" property="request" />
            <#--
            <@vcolumn title="${action.getText('createDate')}" property="createdTime" format="yyyy-MM-dd" />
            <@vcolumn title="${action.getText('creator')}" property="creator" />
            -->
            <@vcolumn title="${action.getText('device.department')}" property="device.department.name" />
            <#assign status = "${object.status}"/>
            <#if object.job?exists>
            	<#assign status="${object.job.docState.value}"/>
            </#if>
            <@vcolumn title="${action.getText('plan.status')}">${status}</@vcolumn>
        </@list>
        <#if !first>
        <@buttonBar>
            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('checkPointPlan')}?" />
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                <@ww.param name="'onclick'" value="'return confirmDeletes(\"checkPointPlanIds\", \"${confirmMessage}\"),checkInvalidParms();'"/>
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
        </@buttonBar>
        </#if>
      </@ww.form>
</@htmlPage>
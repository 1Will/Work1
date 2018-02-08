<#-- $Id: checkPointProcList.ftl 7919 2007-10-22 02:35:26Z qsun $ -->
<#--
   Copyright (c) 2001-2005 YongJun Digital Information Technology Co.,Ltd. All
   Rights Reserved.
   
   This software is the confidential and proprietary information of YongJun
   Digital Information Technology Co.,Ltd. ("Confidential Information"). You
   shall not disclose such Confidential Information and shall use it only in
   accordance with the terms of the license agreement you entered into with
   YongJun.
   
   YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
   SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
   WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
   NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
   LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
   DERIVATIVES.
 -->
<#--$Id: checkPointProcList.ftl 7919 2007-10-22 02:35:26Z qsun $ -->
<#include "../../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('checkPointProc.title')}">
	
	<@ww.form name="'listForm'" action="'searchCheckPointProcs'" method="'post'">
	<@ww.token name="searchCheckPointProcsToken"/>
	
	<#include "./checkPointProcSearch.ftl"/>
	
	<@buttonBar>
		<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
		<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/runmaintenance/checkPoint/editCheckPointProcs.html"/>
	</@buttonBar>
	 <@list title="${action.getText('checkPointProc.list')}" 
         		includeParameters="id|procNo|name|planNo|planName|procExecTime_start|procExecTime_end|deviceNo|deviceName|department.id|procExecTime|docState.code" 
         		fieldMap="like:id|procNo|name|planNo|planName|deviceNo|deviceName,date:procExecTime_start|procExecTime_end" >
            <@vlh.checkbox property="id" name="checkPointProcIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('checkPointProc.number')}" property="procNo" sortable="asc">
                <a href="editCheckPointProcs.html?proc.id=#{object.id}">${object.procNo}</a>
            </@vcolumn>
            <@vcolumn title="${action.getText('checkPointProc.name')}" property="name" sortable="desc"/>
            <@vcolumn title="${action.getText('checkPointPlan.number')}" property="plan.planNo" />
            <@vcolumn title="${action.getText('checkPointPlan.name')}" property="plan.name" />
            <@vcolumn title="${action.getText('device.number')}" property="device.deviceNo" />
            <@vcolumn title="${action.getText('device.name')}" property="device.name" />
            <@vcolumn title="${action.getText('checkPointPlan.time')}" property="plan.scheduleTime" format="yyyy-MM-dd" />
            <@vcolumn title="${action.getText('checkPointProc.time')}" property="procExecTime" format="yyyy-MM-dd" />
            <@vcolumn title="${action.getText('checkPoint.manager')}" property="plan.checker.name" />
            <@vcolumn title="${action.getText('device.department')}" property="device.department.name" />
            <#assign status = "${object.status}"/>
            <#if object.job?exists>
            	<#assign status="${object.job.docState.value}"/>
            </#if>
            <@vcolumn title="${action.getText('checkPointProc.status')}">${status}</@vcolumn>
        </@list>
        <#if !first>
        <@buttonBar>
            <#assign confirmMessage = "${action.getText('proc.delete')}" />
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                <@ww.param name="'onclick'" value="'return confirmDeletes(\"checkPointProcIds\", \"${confirmMessage}\"),checkInvalidParms();'"/>
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
        </@buttonBar>
        </#if>
     </@ww.form>
</@htmlPage>
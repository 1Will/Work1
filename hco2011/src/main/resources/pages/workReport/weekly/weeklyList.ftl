<#--
	Copyright (c) 2001-2009 YongJun Technology Pte.,Ltd. All
	Rights Reserved.
	
	This software is the confidential and proprietary information of 
	YongJun Technology Pte.,Ltd. ("Confidential Information"). You
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

<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('weekly.Manager')}">
	<@ww.form id="listFrom" name="'listFrom'" namespace="'/workReport'" action="'searchWeekly'" method="'post'">
		<@ww.token name="searchWeeklyToken"/>
		<#include "./weeklySearcher.ftl" />
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
        <@buttonBar>
			<@vsubmit name="'search'" value="'${action.getText('search')}'" onclick="'checkInvalidParms()'" />
			<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/workReport/editWeekly.html"/>
        </@buttonBar>
   	<@list title="${action.getText('weekly.List')}" includeParameters="code|name|rapporteur|inst.id|dept.id|duty.id|startDate|endDate|onlyInvalid|onlyValid" fieldMap="like:code|name|rapporteur" >
    	
    	<#if req.getParameter('projectFlag')?exists>
    	 <@vcolumn title="${action.getText('weekly.code')}" property="code" sortable="asc">
            <a href="javascript: returnDialog(new Array(#{object.id}, '${object.code}'))">${object.code}</a>
            <@alignLeft/>
        </@vcolumn>
    	
    	<#else>
    	<@vlh.checkbox property="id" name="weeklyIds">
            <@vlh.attribute name="width" value="30" />
        </@vlh.checkbox>
    	<@vcolumn title="${action.getText('weekly.code')}" property="code" sortable="asc">
	     <#if !object.disabled>
            <a href="${req.contextPath}/workReport/editWeekly.html?weekly.id=#{object.id}">${object.code}</a>
            <#else>
            ${object.code}
         </#if>
            <@alignLeft/>
        </@vcolumn>
        </#if>
        
        <@vcolumn title="${action.getText('weekly.name')}" property="name" sortable="asc">
        	<@vlh.attribute name="width" value="50" />
            <@alignLeft/>
        </@vcolumn>
        <@vcolumn title="${action.getText('weekly.rapporteur')}" property="rapporteur.name" sortable="asc">
            <@alignLeft/>
        </@vcolumn>
        <@vcolumn title="${action.getText('weekly.inst')}" property="inst.name" sortable="asc">
            <@alignLeft/>
        </@vcolumn>
        <@vcolumn title="${action.getText('weekly.dept')}" property="dept.name" sortable="asc">
            <@alignLeft/>
        </@vcolumn>
        <@vcolumn title="${action.getText('weekly.duty')}" property="duty.name" sortable="asc">
            <@alignLeft/>
        </@vcolumn>
        <@vcolumn title="${action.getText('weekly.startDate')}" sortable="asc">
            ${(object.startDate?string('yyyy-MM-dd'))}
        </@vcolumn>
        <@vcolumn title="${action.getText('weekly.endDate')}"  sortable="asc">
            ${(object.endDate?string('yyyy-MM-dd'))}
        </@vcolumn>
    </@list>
	
	<#if req.getParameter('projectFlag')?exists>
    <#else>
		<#if !first>
		  <#if !(action.isReadOnly())>
	        <@buttonBar>
			  <@crm_disabledOrEnabled_button message="${action.getText('weekly.info')}" boxName="weeklyIds" jsFunctionName="checkInvalidParms()"/>
	       </@buttonBar>
	       </#if>
		</#if>
	</#if>
	
	
	</@ww.form>
</@htmlPage>
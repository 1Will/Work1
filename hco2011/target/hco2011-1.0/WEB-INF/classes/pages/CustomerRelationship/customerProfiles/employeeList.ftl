<#--
	Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All
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

<#--$Id: employeeList.ftl 2009-12-14 10:00:17Z wliu $ -->

<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('employeeList.title')}">
<@ww.form name="'listForm'" action="'searchEmployee'" method="'post'">
	<@ww.token name="searchEmployeeToken"/>
	<#include "./employeeSearcher.ftl" />
	<#if sysUser?exists>
		<@ww.hidden name="'sysUser'" value="'${sysUser?if_exists}'"/>
		<@ww.hidden name="'validUser'" value="'${sysUser?if_exists}'"/>
	</#if>
    <@buttonBar>
        <@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'" />
    </@buttonBar>
    <@list title="${action.getText('employee.list')}" includeParameters="employeeNo|name|inst.id|dept.id|duty|onlyInvalid|onlyValid|sysUser|validUser" fieldMap="like:employeeNo|name" >
        <@vcolumn title="${action.getText('employee.no')}" property="employeeNo" sortable="asc">
            <a href="javascript: returnDialog(new Array(#{object.id},'${object.name}','${object.employeeNo}','${object.userName?if_exists}','${object.inst.id}','${object.dept.id}','${object.cellphone}','${(object.birthday?string('yyyy-MM-dd'))?if_exists}','${object.email?if_exists}'));">
            	${object.employeeNo}
            </a>
            <@alignLeft/>
        </@vcolumn>
        <#if sysUser?exists>
	        <@vcolumn title="${action.getText('employee.loginName')}" property="userName" sortable="desc">
	        	<@alignLeft/>
	        </@vcolumn>
        </#if>
        <@vcolumn title="${action.getText('employee.name')}" property="name" sortable="desc">
        	<@alignLeft/>
     	</@vcolumn> 
     	<#assign sex="" />
	     <#if object.sex>
	     	<#assign sex="${action.getText('employee.sex.man')}" />
	     <#else>
	     	<#assign sex="${action.getText('employee.sex.woman')}" />
	     </#if>    
     	<@vcolumn title="${action.getText('employee.sex')}" property="sex" sortable="desc">
     		${sex}
        	<@alignLeft/>
     	</@vcolumn>
		<@vcolumn title="${action.getText('employee.inst')}" property="inst.name" sortable="desc">
			<@alignLeft/>
		</@vcolumn>  
		<@vcolumn title="${action.getText('employee.dept')}" property="dept.name" sortable="desc">
			<@alignLeft/>
		</@vcolumn>  
		<@vcolumn title="${action.getText('employee.duty')}" property="duty.name" sortable="desc">
			<@alignLeft/>
		</@vcolumn>  
	</@list>
</@ww.form>
</@htmlPage>
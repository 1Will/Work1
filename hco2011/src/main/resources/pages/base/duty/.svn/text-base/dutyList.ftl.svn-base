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
 
<#--$Id: dutyList.ftl 2010-1-23  zsz $ -->
<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('duty.search')}">
	<@ww.form name="'listForm'" action="'searchDuty'" namespace="'/duty'" method="'post'">
        <@ww.token name="searchDutyToken"/>
         <@ww.hidden name="'dept.id'" value="${departmentId?if_exists}"/>
         <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
        <#include "./dutySearcher.ftl" />
        <@buttonBar>
            <@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'" />
            <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/duty/editDuty.html?dept.id=${departmentId?if_exists}"/>
        </@buttonBar>
        <@list title="${action.getText('duty.list')}" includeParameters="code|name|jobName.id|dept.id|onlyInvalid|onlyValid" fieldMap="like:code|name" >
	        <@vlh.checkbox property="id" name="dutyIds">
	            <@vlh.attribute name="width" value="30" />
	        </@vlh.checkbox>
	         <#if !object.disabled>
	        <@vcolumn title="${action.getText('duty.code')}" property="code" sortable="desc">
            	<a href="editDuty.html?duty.id=#{object.id}&amp;dept.id=${departmentId?if_exists}">${object.code}</a>
            	<@alignLeft/>
         	</@vcolumn>
         	<#else>
         	 <@vcolumn title="${action.getText('duty.code')}" property="code" sortable="desc">
            	${object.code}
            	<@alignLeft/>
         	</@vcolumn>
         	</#if>
            <@vcolumn title="${action.getText('duty.name')}" property="name" sortable="desc">
            	<@alignLeft/>
         	</@vcolumn> 
         
         <@vcolumn title="${action.getText('duty.jobName')}" property="jobName.name" sortable="desc">
            	<@alignLeft/>
         </@vcolumn>
         <@vcolumn title="${action.getText('duty.dept')}" property="dept.name" sortable="desc">
            	<@alignLeft/>
         </@vcolumn>
         <@vcolumn title="${action.getText('duty.perType')}" property="perType.name" sortable="desc">
            	<@alignLeft/>
         </@vcolumn>
        </@list>
        
        <#if !first>
        <#if !(action.isReadOnly())>
        <@buttonBar>
		  <@crm_disabledOrEnabled_button message="${action.getText('duty')}" boxName="dutyIds" jsFunctionName="checkInvalidParms()"/>
		  <script language="javascript">
			function validateInvalid(delFun, checkFun) {
			    if (delFun) {
			        checkFun;
			        return true;
			    }
			    return false;
		   }
		  </script>
       </@buttonBar>
       </#if>
       </#if>
    </@ww.form>
</@htmlPage>